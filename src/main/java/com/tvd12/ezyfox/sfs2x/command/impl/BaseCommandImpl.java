package com.tvd12.ezyfox.sfs2x.command.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */

public abstract class BaseCommandImpl {

    // smartfox api
	protected final ISFSApi api;
	
	// smartfox extension
	protected final ISFSExtension extension;
	
	// application context
	protected final AppContextImpl context;
	
	/**
	 * Construct with application context, smartfox api and smartfox extension
	 * 
	 * @param context application context
	 * @param api smartfox api
	 * @param extension smartfox extension
	 */
	public BaseCommandImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		this.api = api;
		this.extension = extension;
		this.context = context;
	}
	
}
