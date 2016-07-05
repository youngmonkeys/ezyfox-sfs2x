package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;

@ServerEventHandler(event = ServerEvent.USER_JOIN_ZONE)
public class UserJoinZoneHandler {

    public void handle(AppContext context, ApiZone zone, AppUser user) {
        
    }
    
}
