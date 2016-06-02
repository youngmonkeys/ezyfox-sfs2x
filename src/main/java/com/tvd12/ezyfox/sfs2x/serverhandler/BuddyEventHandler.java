/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.buddylist.SFSBuddyEventParam;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiBuddy;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiBuddyImpl;

/**
 * Support to handle event related to buddy
 * 
 * @author tavandung12
 *
 */
public abstract class BuddyEventHandler extends ServerBaseEventHandler {

    /**
     * @param context
     */
    public BuddyEventHandler(AppContextImpl context) {
        super(context);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addHandlers(handlerClasses, ApiZone.class, ApiBuddy.class);
    }

    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        ApiBuddyImpl buddy = (ApiBuddyImpl) event.getParameter(SFSBuddyEventParam.BUDDY);
        notifyToHandlers((ApiZone) sfsZone.getProperty(APIKey.ZONE), buddy);
    }
    
    /**
     * Notify event to handlers (listeners)
     * 
     * @param zone api zone reference
     * @param buddy api buddy reference
     */
    protected void notifyToHandlers(ApiZone zone, ApiBuddyImpl buddy) {
        for(ServerHandlerClass handler : handlers) {
            notifyToHandler(handler, zone, buddy);
        }
    }
    
    /**
     * Notify event to handler
     * 
     * @param handler structure of handler class
     * @param zone api zone reference
     * @param buddy api buddy reference
     */
    protected void notifyToHandler(ServerHandlerClass handler, 
            ApiZone zone, ApiBuddyImpl buddy) {
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                handler.newInstance(), 
                context, zone, buddy);
    }

}
