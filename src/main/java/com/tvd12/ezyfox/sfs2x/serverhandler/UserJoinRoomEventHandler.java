package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

/**
 * Support to handle user join room event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class UserJoinRoomEventHandler extends ServerRoomEventHandler {

    /**
     * @param context
     */
	public UserJoinRoomEventHandler(BaseAppContext context) {
		super(context);
	}
	
	/**
	 * @see ServerRoomEventHandler#eventName()
	 */
	@Override
	public String eventName() {
		return ServerEvent.USER_JOIN_ROOM;
	}
	
}
