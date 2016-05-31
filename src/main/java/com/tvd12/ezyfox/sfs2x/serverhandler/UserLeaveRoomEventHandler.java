package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class UserLeaveRoomEventHandler extends ServerRoomEventHandler {
	
	public UserLeaveRoomEventHandler(AppContextImpl context) {
		super(context);
	}
	public String eventName() {
		return ServerEvent.USER_LEAVE_ROOM;
	}
	
}
