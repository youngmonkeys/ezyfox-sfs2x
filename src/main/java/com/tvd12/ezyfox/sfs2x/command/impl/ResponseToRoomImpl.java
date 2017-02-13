package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Sets;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.ResponseToRoom;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.data.impl.ParamTransformer;

/**
 * @author tavandung12
 * Created on Jun 29, 2016
 *
 */
public class ResponseToRoomImpl extends BaseCommandImpl implements ResponseToRoom {

    private Object data;
    private String command;
    private String roomName;
    private boolean useUDP = false;
    
    private List<String> includedVars = new ArrayList<>();
    private List<String> excludedVars = new ArrayList<>();
    
    private List<String> excludedUsers = new ArrayList<>();
    private Map<String, Object> addition = new HashMap<>();
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public ResponseToRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#command(java.lang.String)
     */
    @Override
    public ResponseToRoom command(String command) {
        this.command = command;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#data(java.lang.Object)
     */
    @Override
    public ResponseToRoom data(Object object) {
        this.data = object;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#param(java.lang.String, java.lang.Object)
     */
    @Override
    public ResponseToRoom param(String name, Object value) {
        addition.put(name, value);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#room(com.lagente.core.model.ApiRoom)
     */
    @Override
    public ResponseToRoom room(ApiRoom room) {
        this.roomName = room.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#exclude(com.tvd12.ezyfox.core.entities.ApiBaseUser[])
     */
    @Override
    public ResponseToRoom exclude(ApiBaseUser... users) {
    	return exclude(Sets.newHashSet(users));
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#exclude(java.util.Collection)
     */
    @Override
    public <U extends ApiBaseUser> ResponseToRoom exclude(Collection<U> users) {
    	for(U user : users)
    		this.excludedUsers.add(user.getName());
    	return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#useUDP(boolean)
     */
    @Override
    public ResponseToRoom useUDP(boolean value) {
        this.useUDP = value;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#only(java.lang.String[])
     */
    @Override
    public ResponseToRoom only(String... params) {
        includedVars.addAll(Arrays.asList(params));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#ignore(java.lang.String[])
     */
    @Override
    public ResponseToRoom ignore(String... params) {
        excludedVars.addAll(Arrays.asList(params));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        validateCommand();
        Room room = CommandUtil.getSfsRoom(roomName, extension);
        validateRoom(room);
        List<User> recipients = room.getUserList();
        for(String exUser : excludedUsers) 
            recipients.remove(room.getUserByName(exUser));
        ISFSObject params = createResponseParams();
        api.sendExtensionResponse(command, params, recipients, room, useUDP);
        return Boolean.TRUE;
    }
    
    /**
     * Create smartfox object to response to client
     * 
     * @return smartfox parameter object
     */
    private ISFSObject createResponseParams() {
        return ResponseParamsBuilder.create()
                .addition(addition)
                .excludedVars(excludedVars)
                .includedVars(includedVars)
                .transformer(new ParamTransformer(context))
                .data(data)
                .build();
    }
    
    /**
     * Validate command
     * 
     * @throws IllegalStateException when command is null or empty
     */
    private void validateCommand() {
        if(command == null || command.trim().isEmpty())
            throw new IllegalStateException("Invalid command");
    }
    
    /**
     * Validate room
     * 
     * @throws IllegalStateException when room is null
     */
    private void validateRoom(Room room) {
        if(room == null)
            throw new IllegalStateException("Room name " + roomName + " not found");
    }
    
}
