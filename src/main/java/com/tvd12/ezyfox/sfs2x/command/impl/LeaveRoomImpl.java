package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.LeaveRoom;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see LeaveRoom
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class LeaveRoomImpl extends BaseCommandImpl implements LeaveRoom {

    private String user;
    private String room;
    private boolean fireClientEvent;
    private boolean fireServerEvent;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public LeaveRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        api.leaveRoom(CommandUtil.getSfsUser(user, api), 
                CommandUtil.getSfsRoom(room, extension), 
                fireClientEvent, fireServerEvent);
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.LeaveRoom#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public LeaveRoom user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.LeaveRoom#user(java.lang.String)
     */
    @Override
    public LeaveRoom user(String username) {
        this.user = username;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.LeaveRoom#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public LeaveRoom room(ApiRoom room) {
        this.room = room.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.LeaveRoom#room(java.lang.String)
     */
    @Override
    public LeaveRoom room(String roomName) {
        this.room = roomName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.LeaveRoom#fireClientEvent(boolean)
     */
    @Override
    public LeaveRoom fireClientEvent(boolean value) {
        this.fireClientEvent = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.LeaveRoom#fireServerEvent(boolean)
     */
    @Override
    public LeaveRoom fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }

}
