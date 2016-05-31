package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;

@RoomName("hello")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class UserJoinRoomHandler4 {

    public void handle(AppContext context, PokerRoom room, AppUser user) {
        
    }
    
}
