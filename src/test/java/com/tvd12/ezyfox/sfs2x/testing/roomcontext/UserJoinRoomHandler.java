package com.tvd12.ezyfox.sfs2x.testing.roomcontext;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class UserJoinRoomHandler {

    public void handle(AppContext context, PokerRoom room, AppUser user) {
        
    }
    
}
