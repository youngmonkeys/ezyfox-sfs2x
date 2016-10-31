package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;

/**
 * @author tavandung12
 * Created on Oct 31, 2016
 *
 */
@ServerEventHandler(event = ServerEvent.ZONE_EXTENSION_DESTROY)
public class ZoneExtensionDestroyHandler {

    public void handle(AppContext context, ApiZone zone) {
        
    }
    
}
