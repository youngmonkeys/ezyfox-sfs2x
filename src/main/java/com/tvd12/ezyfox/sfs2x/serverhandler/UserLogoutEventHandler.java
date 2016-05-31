package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class UserLogoutEventHandler extends UserActionEventHandler {

	public UserLogoutEventHandler(AppContextImpl context) {
		super(context);
	}

	@Override
	public String eventName() {
		return ServerEvent.USER_LOGOUT;
	}
    
}
