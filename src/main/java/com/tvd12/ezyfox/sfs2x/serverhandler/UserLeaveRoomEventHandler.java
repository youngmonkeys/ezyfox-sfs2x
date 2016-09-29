package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

/**
 * Support to handle user leave room event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class UserLeaveRoomEventHandler extends ServerRoomEventHandler {
	
    /**
     * @param context the context
     */
	public UserLeaveRoomEventHandler(BaseAppContext context) {
		super(context);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
	 */
	public String eventName() {
		return ServerEvent.USER_LEAVE_ROOM;
	}
	
}
