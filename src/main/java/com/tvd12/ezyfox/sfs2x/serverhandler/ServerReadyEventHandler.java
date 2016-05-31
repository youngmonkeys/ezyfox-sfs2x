package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.annotation.parser.ConfigPropertyParser;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class ServerReadyEventHandler extends ServerBaseEventHandler {
    
	public ServerReadyEventHandler(AppContextImpl context) {
		super(context);
	}
	
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		AppContextImpl appContext = (AppContextImpl)context;
		appContext.setExtension(getParentExtension());
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
	
	protected void assignDataToHandler(ServerHandlerClass handler, Object instance) {
	    if(getParentExtension().getConfigProperties() != null) {
	        ConfigPropertyParser.assignValue(
	                handler.getPropertiesClassWrapper(), 
	                instance, 
	                getParentExtension().getConfigProperties());
	    }
    }
	
	@Override
	public String eventName() {
		return ServerEvent.SERVER_READY;
	}
	
}
