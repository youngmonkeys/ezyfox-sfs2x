package com.tvd12.ezyfox.sfs2x.extension;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ServerEventHandlerProvider;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRequestHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiZoneImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerInitializingEventHandler;

public class ZoneExtension extends SFSExtension {

	protected AppContextImpl context;
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ZoneExtension.class);
	
	@Override
	public void init() {
		LOGGER.info("================ start init " + getName() + "===========");
		initContext();
		before();
		addServerEventHandlers();
		addClientEventHandlers();
		addZoneAgent();
		startServerInitializingEventHandler();
		after();
		LOGGER.info("================ end init " + getName() + "===========");
	}
	
	protected void before() {}
	
	protected void after() {}
	
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
	
	protected void startServerInitializingEventHandler() {
		ServerInitializingEventHandler handler = 
				new ServerInitializingEventHandler(context);
		handler.handle();
	}
	
	private ServerEventHandler createServerEventHandler(
			SFSEventType type, Class<?> clazz) {
		try {
			return (ServerEventHandler)
					ReflectClassUtil.newInstance(
					clazz, AppContextImpl.class, context);
		} catch (ExtensionException e) {
			throw new RuntimeException("Can not create event handler of class "
					+ clazz, e);
		}
	}
	
	private void initContext() {
		context = (AppContextImpl)ContextProvider
				.getInstance()
				.addContext(getClass(), new AppContextImpl(getClass()));
		context.setApi(getApi());
	}
	
	protected void addClientEventHandlers() {
		Set<String> commands = 
				context.clientActionCommands();
		for(String command : commands)
			addClientEventHandler(command);
	}
	
	protected void addClientEventHandler(String command) {
		addClientEventHandler(new ClientEventHandler(context, command));
	}
	
	protected void addClientEventHandler(ClientRequestHandler handler) {
		addRequestHandler(handler.getCommand(), handler);
	}
	
	private void addZoneAgent() {
		getParentZone().setProperty(APIKey.ZONE, 
				new ApiZoneImpl(getParentZone()));
	}
	
	
}
