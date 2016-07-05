package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;

/**
 * @author tavandung12
 * Created on Jun 30, 2016
 *
 */
@ServerEventHandler(event = ServerEvent.USER_RECONNECTION_TRY)
public class UserReconnectionTryHandler {

    public void handle(AppContext context, ApiZone zone, AppUser user) {
    }
    
}
