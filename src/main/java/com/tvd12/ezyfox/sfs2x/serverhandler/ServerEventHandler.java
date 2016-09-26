package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.Set;

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
	protected Set<Class<?>> handlerClasses;
	
	/**
	 * @param context the context
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
	    this.handlerClasses = context.getServerEventHandlerClasses(eventName());
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
