package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.ZoneName;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiZone;

@ZoneName("")
@ServerEventHandler(event = ServerEvent.USER_JOIN_ZONE)
public class UserJoinZoneHandler4 {

    public void handle(AppContext context, ApiZone zone, PokerUser user) {
        
    }
    
}
