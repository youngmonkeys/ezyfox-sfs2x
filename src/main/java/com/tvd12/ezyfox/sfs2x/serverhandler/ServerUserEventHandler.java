                            package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.tvd12.ezyfox.core.config.ServerUserEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;
import com.tvd12.ezyfox.core.util.UserAgentUtil;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public abstract class ServerUserEventHandler extends ServerBaseEventHandler {

    protected List<ServerUserHandlerClass> serverUserEventHandler;
    
    public ServerUserEventHandler(AppContextImpl context) {
        super(context);
    }
    
    @Override
    protected void init() {
        serverUserEventHandler = new ServerUserEventHandlerCenter()
                .addHandlers(handlerClasses, 
                context.getUserClass(), context.getGameUserClasses());
    }
    
    protected Object checkUserAgent(ServerUserHandlerClass handler, ApiUser userAgent) {
        if(handler.getUserClass() == userAgent.getClass())
            return userAgent;
        return UserAgentUtil.getGameUser(userAgent, handler.getUserClass());
    }
}
