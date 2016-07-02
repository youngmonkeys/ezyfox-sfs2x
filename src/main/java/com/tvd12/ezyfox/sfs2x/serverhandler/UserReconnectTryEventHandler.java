/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * Support to handle user reconnect try event
 * 
 * @author tavandung12
 *
 */
public class UserReconnectTryEventHandler extends UserZoneEventHandler {
    
    /**
     * @param context
     */
    public UserReconnectTryEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserZoneEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        try {
            super.handleServerEvent(event);
        } catch(Exception e) {
            throw new SFSException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.USER_RECONNECTION_TRY;
    }

}
