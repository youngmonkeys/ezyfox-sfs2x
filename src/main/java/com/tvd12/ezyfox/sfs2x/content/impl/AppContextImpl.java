package com.tvd12.ezyfox.sfs2x.content.impl;

import java.lang.reflect.Constructor;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;

/**
 * @see AppContext
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */

public class AppContextImpl extends BaseAppContext {

    // smartfox api
	private ISFSApi api;
	
	// smartfox extension
	private ISFSExtension extension;
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getCommandConstructor(java.lang.Class)
	 */
	@Override
    protected Constructor<?> getCommandConstructor(Class<?> commandClass) {
        try {
            return ReflectClassUtil.getConstructor(
                    commandClass, 
                    AppContextImpl.class, ISFSApi.class, ISFSExtension.class);
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not get constructor of command class " 
                    + commandClass);
        }
    }
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getCommand(java.lang.Class)
	 */
	@Override
    protected <T> T getCommand(Class<T> clazz) {
        return getCommand(commands, clazz, this, api, extension);
    }
	
	/**
	 * Set smartfox api
	 * 
	 * @param api smartfox api
	 */
	public void setApi(ISFSApi api) {
		this.api = api;
	}
	
	/**
	 * Set smartfox extension
	 * 
	 * @param extension smartfox extension
	 */
	public void setExtension(ISFSExtension extension) {
		this.extension = extension;
	}
	
}
