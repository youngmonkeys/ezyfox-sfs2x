package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;

/**
 * Support to handle server event and notify to listeners
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public abstract class ServerBaseEventHandler extends ServerEventHandler {

    // list of structures of server event handler classes
    protected List<ServerHandlerClass> handlers;

    /**
     * @param context the context
     */
    public ServerBaseEventHandler(BaseAppContext context) {
        super(context);
    }
    
    /**
     * @see ServerEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addHandlers(handlerClasses);
    }
    
    /**
     * Notify server event to handlers (listeners)
     */
    protected void notifyHandlers() {
        for(ServerHandlerClass handler : handlers) {
            notifyHandler(handler);
        }
    }
    
    /**
     * Notify server event to handler (listener)
     * 
     * @param handler structure of handler class
     */
    protected void notifyHandler(ServerHandlerClass handler) {
        ReflectMethodUtil.invokeHandleMethod(handler.getHandleMethod(), 
                handler.newInstance(), context);
    } 
    
}
