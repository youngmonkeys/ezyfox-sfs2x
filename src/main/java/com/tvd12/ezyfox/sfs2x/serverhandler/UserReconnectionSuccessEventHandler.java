/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 *
 */
public class UserReconnectionSuccessEventHandler extends UserZoneEventHandler {

    /**
     * @param context
     */
    public UserReconnectionSuccessEventHandler(AppContextImpl context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserJoinRoomEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.USER_RECONNECTION_SUCCESS;
    }
    
}
