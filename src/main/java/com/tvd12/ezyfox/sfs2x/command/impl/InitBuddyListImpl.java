package com.tvd12.ezyfox.sfs2x.command.impl;

import java.io.IOException;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.InitBuddyList;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see InitBuddyList
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class InitBuddyListImpl extends BaseCommandImpl implements InitBuddyList {

    private String username;
    private boolean fireServerEvent = true;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public InitBuddyListImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        try {
            SmartFoxServer.getInstance()
                .getAPIManager()
                .getBuddyApi()
                .initBuddyList(CommandUtil.getSfsUser(username, api), fireServerEvent);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.InitBuddyList#user(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public InitBuddyList user(ApiBaseUser user) {
        this.username = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.InitBuddyList#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public InitBuddyList user(String username) {
        this.username = username;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.InitBuddyList#fireServerEvent(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public InitBuddyList fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }

}
