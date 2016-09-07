package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.annotation.parser.ConfigPropertyParser;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;

/**
 * Support to handle server ready event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class ServerReadyEventHandler extends ServerBaseEventHandler {
    
    /**
     * @param context
     */
	public ServerReadyEventHandler(BaseAppContext context) {
		super(context);
	}
	
	/**
	 * @see BaseServerEventHandler#handleServerEvent(ISFSEvent)
	 */
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		notifyHandlers();
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#notifyHandler(com.tvd12.ezyfox.core.structure.ServerHandlerClass)
	 */
	@Override
	protected void notifyHandler(ServerHandlerClass handler) {
	    Object instance = handler.newInstance();
	    assignDataToHandler(handler, instance);
	    ReflectMethodUtil.invokeHandleMethod(handler.getHandleMethod(), 
                instance, context);
	}
	
	/**
	 * Map configuration properties to handler object
	 * 
	 * @param handler structure of handler class
	 * @param instance a handler instance
	 */
	protected void assignDataToHandler(ServerHandlerClass handler, Object instance) {
	    if(getParentExtension().getConfigProperties() != null) {
	        ConfigPropertyParser.assignValue(
	                handler.getPropertiesClassWrapper(), 
	                instance, 
	                getParentExtension().getConfigProperties());
	    }
    }
	
	/**
	 * @see ServerBaseEventHandler#eventName()
	 */
	@Override
	public String eventName() {
		return ServerEvent.SERVER_READY;
	}
	
}
