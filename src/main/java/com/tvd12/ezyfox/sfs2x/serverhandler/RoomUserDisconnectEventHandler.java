package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

public class RoomUserDisconnectEventHandler extends ServerRoomEventHandler {

	public RoomUserDisconnectEventHandler(BaseAppContext context) {
		super(context);
	}

	@Override
	public String eventName() {
		return ServerEvent.ROOM_USER_DISCONNECT;
	}

}
