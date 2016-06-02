package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
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
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
	 */
	@Override
	public String eventName() {
		return ServerEvent.USER_JOIN_ZONE;
	}
	
}
