/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

/**
 * Support to handle user reconnection success event
 * 
 * @author tavandung12
 *
 */
public class UserReconnectionSuccessEventHandler extends UserZoneEventHandler {

    /**
     * @param context the context
     */
    public UserReconnectionSuccessEventHandler(BaseAppContext context) {
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
