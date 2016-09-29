package com.tvd12.ezyfox.sfs2x.command.impl;

import org.slf4j.LoggerFactory;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.Log;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see Log
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class LogImpl extends BaseCommandImpl implements Log {

	private Class<?> from = api.getClass(); 
	
	/**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
	public LogImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#error(java.lang.Object)
     */
	@Override
	public Log from(Object obj) {
		this.from = obj.getClass();
		return this;
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#info(java.lang.String)
     */
	@Override
	public void info(String msg) {
		LoggerFactory.getLogger(from).info(msg);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#debug(java.lang.String)
     */
	@Override
	public void debug(String msg) {
		LoggerFactory.getLogger(from).debug(msg);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#warn(java.lang.String)
     */
	@Override
	public void warn(String msg) {
		LoggerFactory.getLogger(from).warn(msg);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#error(java.lang.String)
     */
	@Override
	public void error(String msg) {
		LoggerFactory.getLogger(from).error(msg);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#info(java.lang.String, java.lang.Throwable)
     */
	@Override
	public void info(String msg, Throwable e) {
		LoggerFactory.getLogger(from).info(msg, e);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#debug(java.lang.String, java.lang.Throwable)
     */
	@Override
	public void debug(String msg, Throwable e) {
		LoggerFactory.getLogger(from).debug(msg, e);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#warn(java.lang.String, java.lang.Throwable)
     */
	@Override
	public void warn(String msg, Throwable e) {
		LoggerFactory.getLogger(from).warn(msg);
	}

	/* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Log#error(java.lang.String, java.lang.Throwable)
     */
	@Override
	public void error(String msg, Throwable e) {
		LoggerFactory.getLogger(from).error(msg);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.Log#info(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void info(String msg, Object... args) {
	    LoggerFactory.getLogger(from).info(msg, args);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.Log#debug(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void debug(String msg, Object... args) {
	    LoggerFactory.getLogger(from).debug(msg, args);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.Log#warn(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void warn(String msg, Object... args) {
	    LoggerFactory.getLogger(from).warn(msg, args);
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.Log#error(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void error(String msg, Object... args) {
	    LoggerFactory.getLogger(from).error(msg, args);
	}

}
