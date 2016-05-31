package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public abstract class ServerEventHandler extends BaseServerEventHandler {

	protected AppContextImpl context;
	protected List<Class<?>> handlerClasses;
	
	public ServerEventHandler(AppContextImpl context) {
		this.context = context;
		this.initHandlerClasses();
		this.init();
	}
	
	protected void initHandlerClasses() {
	    this.handlerClasses = context.serverEventHandlerClasses(eventName());
	}
	
	protected abstract void init();
	
	public abstract String eventName();
	
}
