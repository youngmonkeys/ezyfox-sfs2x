package com.tvd12.ezyfox.sfs2x.clienthandler;

import java.util.List;

import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;

import lombok.Getter;

public abstract class ClientRequestHandler extends BaseClientRequestHandler {

    // request's command
    @Getter
    protected final String command;
    
    // application context
	protected BaseContext context;
	
	// list of listeners
	protected final List<RequestResponseClass> listeners;
	
	/**
	 * @param context application context
	 * @param command listener's command
	 */
	public ClientRequestHandler(BaseContext context, 
			String command) {
		this.context = context;
		this.command  = command;
		this.listeners = context.getClientRequestListeners(command);
	}
	
}
