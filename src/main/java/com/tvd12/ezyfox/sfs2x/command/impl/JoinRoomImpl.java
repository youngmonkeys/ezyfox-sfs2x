/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.JoinRoom;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 *
 */
public class JoinRoomImpl extends BaseCommandImpl implements JoinRoom {
    
    private String userToJoin = null;
    private String roomToJoin = null;
    private String roomToLeave = null;
    private String password = "";
    private boolean asSpectator = false;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;

    /**
     * @param context
     * @param api
     * @param extension
     */
    public JoinRoomImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#user(com.lagente.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom user(ApiBaseUser user) {
        this.userToJoin = user.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom user(String username) {
        this.userToJoin = username;
        return this;
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#roomToJoin(com.lagente.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom roomToJoin(ApiRoom room) {
        this.roomToJoin = room.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#roomToJoin(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom roomToJoin(String roomName) {
        this.roomToJoin = roomName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#password(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom password(String password) {
        this.password = password;
        return this;
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#asSpectator(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom asSpectator(boolean value) {
        this.asSpectator = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#roomToLeave(com.lagente.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom roomToLeave(ApiRoom room) {
        this.roomToLeave = room.getName();
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#roomToLeave(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom roomToLeave(String roomName) {
        this.roomToLeave = roomName;
        return this;
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#fireClientEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom fireClientEvent(boolean value) {
        this.fireClientEvent = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.JoinRoom#fireServerEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public JoinRoom fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        User sfsUser = CommandUtil.getSfsUser(userToJoin, api);
        Room sfsRoomToJoin = CommandUtil.getSfsRoom(roomToJoin, extension);
        Room sfsRoomToLeave = (roomToLeave != null)
                ? CommandUtil.getSfsRoom(roomToLeave, extension) : null;
        sfsRoomToLeave = (sfsRoomToLeave == null) 
                ? sfsUser.getLastJoinedRoom() : sfsRoomToLeave;
        try {
            api.joinRoom(sfsUser, 
                    sfsRoomToJoin, 
                    password, 
                    asSpectator, 
                    sfsRoomToLeave, 
                    fireClientEvent, 
                    fireServerEvent);
        } catch (SFSJoinRoomException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

}
