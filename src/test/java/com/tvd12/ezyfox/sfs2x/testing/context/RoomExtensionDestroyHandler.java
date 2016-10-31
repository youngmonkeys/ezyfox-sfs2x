package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * @author tavandung12
 * Created on Oct 31, 2016
 *
 */
@ServerEventHandler(event = ServerEvent.ROOM_EXTENSION_DESTROY)
public class RoomExtensionDestroyHandler {

    public void handle(AppContext context, ApiRoom zone) {
        
    }
    
}
