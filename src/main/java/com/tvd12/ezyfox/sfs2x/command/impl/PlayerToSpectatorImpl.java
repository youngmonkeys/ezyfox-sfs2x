package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.PlayerToSpectator;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class PlayerToSpectatorImpl extends BaseCommandImpl implements PlayerToSpectator {

    private String user;
    private String targetRoom;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public PlayerToSpectatorImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            api.playerToSpectator(CommandUtil.getSfsUser(user, api), 
                    CommandUtil.getSfsRoom(targetRoom, extension), 
                    fireClientEvent, fireServerEvent);
        } catch (SFSRoomException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PlayerToSpectator#user(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PlayerToSpectator user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PlayerToSpectator#room(com.tvd12.ezyfox.core.model.ApiRoom)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PlayerToSpectator room(ApiRoom room) {
        this.targetRoom = room.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PlayerToSpectator#fireClientEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PlayerToSpectator fireClientEvent(boolean value) {
        this.fireClientEvent = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PlayerToSpectator#fireServerEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public PlayerToSpectator fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }

}
