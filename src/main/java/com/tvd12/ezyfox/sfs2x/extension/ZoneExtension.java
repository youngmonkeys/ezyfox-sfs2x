package com.tvd12.ezyfox.sfs2x.extension;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.smartfoxserver.v2.controllers.SystemRequest;
import com.smartfoxserver.v2.controllers.filter.ISystemFilterChain;
import com.smartfoxserver.v2.controllers.filter.SysControllerFilterChain;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.config.ServerEventHandlerProvider;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiZoneImpl;
import com.tvd12.ezyfox.sfs2x.filter.BaseSysControllerFilter;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerInitializingEventHandler;

/**
 * Application entry point, any extensions should extends this class
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class ZoneExtension extends BaseExtension {

	/**
	 * @see SFSExtension#init()
	 */
	@Override
	public final void init() {
		initContext();
		before();
		addServerEventHandlers();
		addClientRequestHandlers();
		addAgent();
		addSysControllerFilters();
		startServerInitializingEventHandler();
		config();
		after();
	}
	
	/**
	 * Add server event handlers
	 */
	protected void addServerEventHandlers() {
		Map<Object, Class<?>> handlers = ServerEventHandlerProvider
				.provide(getClass());
		Set<Entry<Object, Class<?>>> entries = handlers.entrySet();
		for(Entry<Object, Class<?>> entry : entries) {
			SFSEventType type = SFSEventType.valueOf(
					entry.getKey().toString());
			ServerEventHandler handler = createServerEventHandler(
					type, entry.getValue());
			addEventHandler(type, handler);
		}
	}
	
	/**
	 * Handle initializing event
	 */
	protected void startServerInitializingEventHandler() {
		ServerInitializingEventHandler handler = 
				new ServerInitializingEventHandler(appContext());
		handler.handle();
	}
	
	/**
	 * Create server event handler by type and handler class
	 * 
	 * @param type event type
	 * @param clazz handler class
	 * @return a ServerEventHandler object 
	 */
	private ServerEventHandler createServerEventHandler(
			SFSEventType type, Class<?> clazz) {
		try {
			return (ServerEventHandler)
					ReflectClassUtil.newInstance(
					clazz, BaseAppContext.class, context);
		} catch (ExtensionException e) {
		    getLogger().error("Error when create server event handlers", e);
			throw new RuntimeException("Can not create event handler of class "
					+ clazz, e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.extension.BaseExtension#createContext()
	 */
	@Override
	protected BaseAppContext createContext() {
	    return (BaseAppContext)ContextProvider
                .getInstance()
                .addContext(getClass(), newAppContext());
	}
	
	protected BaseAppContext newAppContext() {
	    AppContextImpl answer = new AppContextImpl();
	    answer.initialize(getClass());
	    return answer;
	}
	
	/**
	 * Initialize ApiZone object and bind it to smartfox zone
	 */
	protected void addAgent() {
		getParentZone().setProperty(APIKey.ZONE, 
				new ApiZoneImpl(getParentZone()));
	}
	
	/**
	 * Add System Controller Filters
	 */
	public void addSysControllerFilters() {
	    for(SystemRequest rq : SystemRequest.values()) {
	        ISystemFilterChain filterChain = new SysControllerFilterChain();
	        filterChain.addFilter("EzyFoxFilterChain#" + rq, 
	                new BaseSysControllerFilter(appContext(), rq));
	        getParentZone().setFilterChain(rq, filterChain);
	    }
	}
	
	private BaseAppContext appContext() {
	    return (BaseAppContext)context;
	}
	
}
