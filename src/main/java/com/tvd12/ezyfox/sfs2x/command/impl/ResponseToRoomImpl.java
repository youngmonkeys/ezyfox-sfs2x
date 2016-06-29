package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.ResponseToRoom;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

/**
 * @author tavandung12
 * Created on Jun 29, 2016
 *
 */
public class ResponseToRoomImpl extends BaseCommandImpl implements ResponseToRoom {

    private Object data;
    private String command;
    private String roomName;
    private String sender;
    private List<String> excludedUsers = 
            new ArrayList<>();
    private Map<String, SFSDataWrapper> addition 
            = new HashMap<>();
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public ResponseToRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#command(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseToRoom command(String command) {
        this.command = command;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#data(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseToRoom data(Object object) {
        this.data = object;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#param(java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseToRoom param(String name, Object value) {
        addition.put(name, TRANSFORMER.transform(value));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#sender(com.lagente.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseToRoom sender(ApiBaseUser user) {
        this.sender = user.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.ResponseToRoom#room(com.lagente.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseToRoom room(ApiRoom room) {
        this.roomName = room.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.ResponseToRoom#exclude(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseToRoom exclude(ApiBaseUser user) {
        this.excludedUsers.add(user.getName());
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
        User sfsSender = CommandUtil.getSfsUser(sender, api);
        List<User> recipients = room.getUserList();
        for(String exUser : excludedUsers) 
            recipients.remove(room.getUserByName(exUser));
        ISFSObject params = createResponseParams();
        api.sendObjectMessage(room, sfsSender, params, recipients);
        return Boolean.TRUE;
    }
    
    /**
     * Create smartfox object to response to client
     * 
     * @return smartfox parameter object
     */
    private ISFSObject createResponseParams() {
        ISFSObject answer = (data != null) 
                ? ResponseParamSerializer.getInstance()
                        .object2params(context.getResponseParamsClass(data.getClass()), data)
                : new SFSObject();
        for(Entry<String, SFSDataWrapper> entry : addition.entrySet())
            answer.put(entry.getKey(), entry.getValue());
        return answer;
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
 
    private static final SimpleTransformer TRANSFORMER
            = new SimpleTransformer();
}
