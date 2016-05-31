package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;

@ServerEventHandler(event = ServerEvent.USER_LOGIN)
public class UserLoginHandler2 {
    
    public void handle(AppContext context, String username, String password) {
        
    }
    
}
