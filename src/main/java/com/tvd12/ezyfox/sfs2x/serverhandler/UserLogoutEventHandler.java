package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

public class UserLogoutEventHandler extends UserActionEventHandler {

	public UserLogoutEventHandler(BaseAppContext context) {
		super(context);
	}

	@Override
	public String eventName() {
		return ServerEvent.USER_LOGOUT;
	}
    
}
