/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.v117.roomconfig1;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class V117UserJoinRoomHandler1 {

    public void handle(AppContext context, V117Room1 room, V117GameUser1 user) {
        
    }
    
}
