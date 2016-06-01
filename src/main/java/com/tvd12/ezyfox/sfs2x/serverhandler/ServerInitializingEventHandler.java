package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * Support to handle server initializing event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public class ServerInitializingEventHandler extends ServerEventHandler {

    // list of structures of handler classes
	protected List<ServerHandlerClass> handlers;

	/**
	 * @param context
	 */
	public ServerInitializingEventHandler(AppContextImpl context) {
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
	 * Handle event
	 */
	public void handle() {
	    handleServerEvent(null);
	}
	
	/**
     * @see BaseServerEventHandler#handleServerEvent(ISFSEvent)
     */
	@Override
	public void handleServerEvent(ISFSEvent param) {
	    notifyHandlers();
	}
	
	/**
	 * Propagate event to handlers
	 */
	protected void notifyHandlers() {
	    for(ServerHandlerClass handler : handlers) {
	        notifyHandler(handler);
	    }
	}
	
	/**
	 * Propagate event to handler
	 * 
	 * @param handler structure of handler class
	 */
	protected void notifyHandler(ServerHandlerClass handler) {
	    ReflectMethodUtil.invokeHandleMethod(handler.getHandleMethod(), 
	            handler.newInstance(), context);
	}
	
	/**
	 * @see ServerEventHandler#eventName()
	 */
	@Override
	public String eventName() {
		return ServerEvent.SERVER_INITIALIZING; 
	}
	
}
