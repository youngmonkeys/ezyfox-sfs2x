package com.tvd12.ezyfox.sfs2x.extension;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartfoxserver.v2.controllers.SystemRequest;
import com.smartfoxserver.v2.controllers.filter.ISystemFilterChain;
import com.smartfoxserver.v2.controllers.filter.SysControllerFilterChain;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.config.ServerEventHandlerProvider;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRequestHandler;
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
public class ZoneExtension extends SFSExtension {

    // application context
	protected AppContextImpl context;
	
	private static final Logger LOGGER
	        = LoggerFactory.getLogger(ZoneExtension.class);
	
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
	 * Override this function to add custom configuration
	 */
	protected void config() {
	    
	}
	
	/**
	 * Invoke after initializing application and before initialize anything
	 */
	protected void before() {}
	
	/**
	 * Invoke after initialized all
	 */
	protected void after() {}
	
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
				new ServerInitializingEventHandler(context);
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
					clazz, AppContextImpl.class, context);
		} catch (ExtensionException e) {
		    LOGGER.error("Error when create server event handlers", e);
			throw new RuntimeException("Can not create event handler of class "
					+ clazz, e);
		}
	}
	
	/**
	 * Initialize application context
	 */
	private void initContext() {
		context = createAppContext();
		context.setApi(getApi());
		context.setExtension(this);
	}
	
	/**
	 * Create application context
	 * 
	 * @return the context
	 */
	protected AppContextImpl createAppContext() {
	    return (AppContextImpl)ContextProvider
                .getInstance()
                .addContext(getClass(), newAppContext());
	}
	
	protected AppContextImpl newAppContext() {
	    AppContextImpl answer = new AppContextImpl();
	    answer.initialize(getClass());
	    return answer;
	}
	
	/**
	 * Add client request handlers
	 */
	protected void addClientRequestHandlers() {
		Set<String> commands = 
				context.clientRequestCommands();
		for(String command : commands)
			addClientRequestHandler(command);
	}
	
	/**
	 * Add client request handler and map its to the command
	 * 
	 * @param command the command
	 */
	protected void addClientRequestHandler(String command) {
		addClientRequestHandler(newClientEventHandler(command));
	}
	
	/**
	 * Create new client event handler
	 * 
	 * @param command the command
	 * @return the client event handler
	 */
	protected ClientEventHandler newClientEventHandler(String command) {
        return new ClientEventHandler(context, command);
    }
	
	/**
	 * Add client request handle
	 * 
	 * @param handler client request handle
	 */
	protected void addClientRequestHandler(ClientRequestHandler handler) {
		addRequestHandler(handler.getCommand(), handler);
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
	private void addSysControllerFilters() {
	    for(SystemRequest rq : SystemRequest.values()) {
	        ISystemFilterChain filterChain = new SysControllerFilterChain();
	        filterChain.addFilter("EzyFoxFilterChain#" + rq, 
	                new BaseSysControllerFilter(context, rq));
	        getParentZone().setFilterChain(rq, filterChain);
	    }
	}
	
}
