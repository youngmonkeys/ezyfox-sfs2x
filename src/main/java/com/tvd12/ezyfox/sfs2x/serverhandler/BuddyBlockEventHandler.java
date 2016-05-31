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
public class BuddyBlockEventHandler extends BuddyEventHandler {

    /**
     * @param context
     */
    public BuddyBlockEventHandler(AppContextImpl context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.BUDDY_BLOCK;
    }
}
