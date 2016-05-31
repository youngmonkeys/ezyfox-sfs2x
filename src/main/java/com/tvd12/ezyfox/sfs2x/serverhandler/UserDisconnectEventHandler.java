/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * This handler handles user disconnect from server event
 * 
 * @author tavandung12
 *
 */
public class UserDisconnectEventHandler extends UserActionEventHandler {

    public UserDisconnectEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserLogoutEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.USER_DISCONNECT;
    }

}
