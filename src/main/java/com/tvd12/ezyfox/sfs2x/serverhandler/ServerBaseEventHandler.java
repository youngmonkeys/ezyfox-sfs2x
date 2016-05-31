package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public abstract class ServerBaseEventHandler extends ServerEventHandler {

    protected List<ServerHandlerClass> handlers;

    public ServerBaseEventHandler(AppContextImpl context) {
        super(context);
    }
    
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addListeners(handlerClasses);
    }
    
    protected void notifyHandlers() {
        for(ServerHandlerClass handler : handlers) {
            notifyHandler(handler);
        }
    }
    
    protected void notifyHandler(ServerHandlerClass handler) {
        ReflectMethodUtil.invokeHandleMethod(handler.getHandleMethod(), 
                handler.newInstance(), context);
    } 
    
}
