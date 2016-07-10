package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.command.UserInfo;
import com.tvd12.ezyfox.core.constants.APIEvent;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.factory.UserAgentFactory;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiSessionImpl;

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
	 * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserZoneEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
	 */
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
	    super.handleServerEvent(event);
	    if(context.isAutoResponse(APIEvent.ZONE_JOIN))
	        send(APIEvent.ZONE_JOIN, new SFSObject(), (User)event.getParameter(SFSEventParam.USER));
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
        answer.setSession(new ApiSessionImpl(sfsUser.getSession()));
        answer.setCommand(context.command(UserInfo.class).user(sfsUser.getId()));
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
