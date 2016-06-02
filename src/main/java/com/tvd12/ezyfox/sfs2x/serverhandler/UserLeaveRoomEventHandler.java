package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * Support to handle user leave room event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class UserLeaveRoomEventHandler extends ServerRoomEventHandler {
	
    /**
     * @param context
     */
	public UserLeaveRoomEventHandler(AppContextImpl context) {
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
