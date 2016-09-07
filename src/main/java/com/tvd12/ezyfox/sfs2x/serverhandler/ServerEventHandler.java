package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.List;

import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;

/**
 * Support to handler server event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public abstract class ServerEventHandler extends BaseServerEventHandler {

    // application context
	protected BaseAppContext context;
	
	// list of handler classes
	protected List<Class<?>> handlerClasses;
	
	/**
	 * @param context
	 */
	public ServerEventHandler(BaseAppContext context) {
		this.context = context;
		this.initHandlerClasses();
		this.init();
	}
	
	/**
	 * Get all event handler classes mapped to event name and initialize them
	 */
	protected void initHandlerClasses() {
	    this.handlerClasses = context.serverEventHandlerClasses(eventName());
	}
	
	/**
	 * Initialize 
	 */
	protected abstract void init();
	
	/**
	 * @return event name
	 */
	public abstract String eventName();
	
}
