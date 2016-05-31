package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class ServerInitializingEventHandler extends ServerEventHandler {

	protected List<ServerHandlerClass> handlers;

	public ServerInitializingEventHandler(AppContextImpl context) {
		super(context);
	}
	
	@Override
	protected void init() {
	    handlers = new ServerEventHandlerCenter()
                .addListeners(handlerClasses);
	}
	
	public void handle() {
	    handleServerEvent(null);
	}
	
	@Override
	public void handleServerEvent(ISFSEvent param) {
	    notifyHandlers();
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
	
	public String eventName() {
		return ServerEvent.SERVER_INITIALIZING; 
	}
	
}
