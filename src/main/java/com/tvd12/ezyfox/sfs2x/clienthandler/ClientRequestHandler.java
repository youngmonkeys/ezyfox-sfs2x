package com.tvd12.ezyfox.sfs2x.clienthandler;

import java.util.List;

import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

import lombok.Getter;

public abstract class ClientRequestHandler extends BaseClientRequestHandler {

    @Getter
    protected final String command;
	protected AppContextImpl context;
	protected final List<RequestResponseClass> listeners;
	
	public ClientRequestHandler(AppContextImpl context, 
			String command) {
		this.context = context;
		this.command  = command;
		this.listeners = context.clientRequestListeners(command);
	}
	
}
