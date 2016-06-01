package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.GoOnline;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see GoOnline
 * 
 * @author tavandung12
 * Created on May 28, 2016
 *
 */
public class GoOnlineImpl extends BaseCommandImpl implements GoOnline {

    private String user;
    private boolean online;
    private boolean fireServerEvent;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public GoOnlineImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
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
                .getAPIManager().getBuddyApi()
                .goOnline(CommandUtil.getSfsUser(user, api), online, fireServerEvent);
        } catch (SFSBuddyListException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.FALSE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GoOnline#user(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public GoOnline user(ApiBaseUser user) {
        this.user = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GoOnline#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public GoOnline user(String username) {
        this.user = username;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GoOnline#online(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public GoOnline online(boolean online) {
        this.online = online;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.GoOnline#fireServerEvent(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public GoOnline fireServerEvent(boolean fireServerEvent) {
        this.fireServerEvent = fireServerEvent;
        return this;
    }

}
