package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.factory.UserAgentFactory;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * Support to handle join zone event 
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class UserJoinZoneEventHandler extends UserZoneEventHandler {

    /**
     * @param context
     */
	public UserJoinZoneEventHandler(AppContextImpl context) {
		super(context);
		
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserZoneEventHandler#fetchUserAgent(com.smartfoxserver.v2.entities.User)
	 */
	@Override
	protected ApiUser fetchUserAgent(User sfsUser) {
	    return createUserAgent(sfsUser);
	}
	
	/**
     * Create user agent object
     * 
     * @param sfsUser smartfox user object
     * @return user agent object
     */
    protected ApiUser createUserAgent(User sfsUser) {
        ApiUser answer = UserAgentFactory.newUserAgent(
                sfsUser.getName(),
                context.getUserAgentClass(), 
                context.getGameUserAgentClasses().values());
        sfsUser.setProperty(APIKey.USER, answer);
        answer.setId(sfsUser.getId());
        answer.setIp(sfsUser.getIpAddress());
        return answer;
    }
    
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
	 */
	@Override
	public String eventName() {
		return ServerEvent.USER_JOIN_ZONE;
	}
	
}
