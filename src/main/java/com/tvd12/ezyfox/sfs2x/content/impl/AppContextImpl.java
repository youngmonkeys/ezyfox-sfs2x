package com.tvd12.ezyfox.sfs2x.content.impl;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.config.CommandProvider;
import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RequestListenerCenter;
import com.tvd12.ezyfox.core.config.ServerEventHandlerClasses;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

public class AppContextImpl implements AppContext {

	private ExtensionConfiguration extensionConfig;
	private RequestListenerCenter actionListenerCenter;
	private ServerEventHandlerClasses serverEventHandlerClasses;
	
	private Map<Object, Constructor<?>> commands;
	private Map<Object, Object> properties
	        = new ConcurrentHashMap<>();
	
	public AppContextImpl(Class<?> entryPoint) {
		this.initExtensionConfig(entryPoint);
		this.initEventHandlerClasses();
		this.initRequestListenerCenter();
		
		this.initCommands();
		this.initProperties();
	}
	
	@Override
	public void set(Object key, Object value) {
		properties.put(key, value);
	}
	
	@Override
	public <T> T get(Object key, Class<T> clazz) {
		return clazz.cast(properties.get(key));
	}
	
	public UserAgentClass getUserAgentClass() {
		return extensionConfig.getUserAgentClass();
	}
	
	public Map<Class<?>, AgentClass> getRoomAgentClasses() {
		return extensionConfig.getRoomAgentClasses();
	}
	
	public Map<Class<?>, UserAgentClass> getGameUserAgentClasses() {
	    return extensionConfig.getGameUserAgentClasses();
	}
	
	public AgentClass getRoomAgentClass(Class<?> clazz) {
	    return getRoomAgentClasses().get(clazz);
	}
	
	public UserAgentClass getUserAgentClass(Class<?> clazz) {
	    if(clazz == getUserClass())
	        return getUserAgentClass();
	    return getGameUserAgentClasses().get(clazz);
	}
	
    public Class<?> getUserClass() {
        return extensionConfig.getUserClass();
    }
    
    public List<Class<?>> getRoomClasses() {
        return extensionConfig.getRoomClasses();
    }
    
    public List<Class<?>> getGameUserClasses() {
        return extensionConfig.getGameUserClasses();
    }
    
    public Map<Class<?>, ResponseParamsClass> getResponseParamsClasses() {
        return extensionConfig.getResponseParamsClasses();
    }
    
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
        return getResponseParamsClasses().get(clazz);
    }
    
    public Map<Class<?>, MessageParamsClass> getMessageParamsClasses() {
        return extensionConfig.getMessageParamsClasses();
    }
    
    public MessageParamsClass getMessageParamsClass(Class<?> clazz) {
        return getMessageParamsClasses().get(clazz);
    }
	
	public List<RequestResponseClass> clientRequestListeners(String command) {
		return actionListenerCenter.getListeners(command);
	}
	
    public List<Class<?>> serverEventHandlerClasses(String event) {
        return serverEventHandlerClasses.getHandlers(event);
    }

	public Set<String> clientActionCommands() {
		return actionListenerCenter.getCommands();
	}
	
	private void initExtensionConfig(Class<?> entryPoint) {
		extensionConfig = new ExtensionConfiguration();
		extensionConfig.load(entryPoint);
	}
	
	private void initRequestListenerCenter() {
		actionListenerCenter
			= new RequestListenerCenter();
		actionListenerCenter.addListeners(extensionConfig
				.getRequestResponseClientClasses());
	}
	
	private void initEventHandlerClasses() {
		serverEventHandlerClasses 
            = new ServerEventHandlerClasses();
		serverEventHandlerClasses.addHandlers(extensionConfig
            .getServerEventHandlerClasses());
	}
	
	private void initCommands() {
	    Map<Object, Class<?>> commandsClass 
	            = CommandProvider.provide(getClass());
	    commands = new HashMap<>();
	    for(Entry<Object, Class<?>> entry : commandsClass.entrySet()) {
	        Constructor<?> constructor = getCommandConstructor(entry.getValue());
	        commands.put(entry.getKey(), constructor);
	    }
	}
	
	private void initProperties() {
		properties = new HashMap<>();
	}
	
	private Constructor<?> getCommandConstructor(Class<?> commandClass) {
	    try {
            return ReflectClassUtil.getConstructor(
                    commandClass, 
                    AppContextImpl.class, ISFSApi.class, ISFSExtension.class);
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not get constructor of command class " 
                    + commandClass);
        }
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getCommand(Class<T> clazz) {
		Constructor<?> constructor = commands.get(clazz.getName());
		if(constructor == null)
			throw new RuntimeException("Can not retrive implementation of command " + clazz);
		try {
			return (T)ReflectClassUtil.newInstance(constructor,
					this, api, extension);
		} catch (ExtensionException e) {
			throw new RuntimeException("Can not retrive implementation of command " + clazz, e);
		}
	}
	
	public <T> T command(Class<T> clazz) {
		return getCommand(clazz);
	}
	
	private ISFSApi api;
	private ISFSExtension extension;
	public void setApi(ISFSApi api) {
		this.api = api;
	}
	public void setExtension(ISFSExtension extension) {
		this.extension = extension;
	}
	
}
