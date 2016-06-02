package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.FindUser;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see FindUser
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FindUserImpl extends BaseCommandImpl implements FindUser {
	
    /**
     * @param context
     * @param api
     * @param extension
     */
	public FindUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.GetUser#by(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
    @Override
    public <T> T by(String name) {
        User user = api.getUserByName(name);
        return (T)user.getProperty(APIKey.USER);
    }
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.GetUser#by(int)
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T> T by(int userId) {
	    User sfsUser = api.getUserById(userId);
	    if(sfsUser != null)
	        return (T)sfsUser.getProperty(APIKey.USER);
	    return null;
	}

}
