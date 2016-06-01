package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.tvd12.ezyfox.core.config.ServerUserEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;
import com.tvd12.ezyfox.core.util.UserAgentUtil;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * Support to handle event related to user
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public abstract class ServerUserEventHandler extends ServerBaseEventHandler {

    // list of structures of handler classes
    protected List<ServerUserHandlerClass> serverUserEventHandler;
    
    /**
     * @param context
     */
    public ServerUserEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /**
     * @see ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        serverUserEventHandler = new ServerUserEventHandlerCenter()
                .addHandlers(handlerClasses, 
                context.getUserClass(), context.getGameUserClasses());
    }
    
    /**
     * Check whether context of user agent is application or game
     * 
     * @param handler structure of handler
     * @param userAgent user agent object
     * @return user agent or game user agent object
     */
    protected Object checkUserAgent(ServerUserHandlerClass handler, ApiUser userAgent) {
        if(handler.getUserClass() == userAgent.getClass())
            return userAgent;
        return UserAgentUtil.getGameUser(userAgent, handler.getUserClass());
    }
}
