package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;

@ServerEventHandler(event = ServerEvent.USER_LOGOUT)
public class UserLogoutHandler {

    public void handle(AppContext context, AppUser user) {
        
    }
    
}
