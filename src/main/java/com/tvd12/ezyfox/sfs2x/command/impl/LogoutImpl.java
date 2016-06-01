package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.Logout;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see Logout
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class LogoutImpl extends BaseCommandImpl implements Logout {

    private String username;
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public LogoutImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        api.logout(CommandUtil.getSfsUser(username, api));
        return Boolean.TRUE;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Logout#user(com.tvd12.ezyfox.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Logout user(ApiBaseUser user) {
        this.username = user.getName();
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Logout#user(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Logout user(String username) {
        this.username = username;
        return this;
    }

}
