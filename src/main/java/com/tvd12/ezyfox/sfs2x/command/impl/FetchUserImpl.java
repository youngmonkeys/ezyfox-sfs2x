package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FetchUser;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FetchUser
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class FetchUserImpl extends BaseCommandImpl implements FetchUser {

    private int userId;
    private String username;
    private String room;
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public FetchUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T execute() {
        Room sfsRoom = CommandUtil.getSfsRoom(room, extension);
        User sfsUser = null;
        if(username != null)
            sfsUser = sfsRoom.getUserByName(username);
        else
            sfsUser = sfsRoom.getUserById(userId);
        return (sfsUser != null) ? (T)sfsUser.getProperty(APIKey.USER) : null;
        
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUser#userId(int)
     */
    @Override
    public FetchUser userId(int userId) {
        this.userId = userId;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUser#username(java.lang.String)
     */
    @Override
    public FetchUser username(String name) {
        this.username = name;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.FetchUser#room(com.tvd12.ezyfox.core.entities.ApiRoom)
     */
    @Override
    public FetchUser room(ApiRoom room) {
        this.room = room.getName();
        return this;
    }

}
