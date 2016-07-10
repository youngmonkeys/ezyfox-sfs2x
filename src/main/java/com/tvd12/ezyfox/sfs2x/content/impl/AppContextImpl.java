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

/**
 * @see AppContext
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */

public class AppContextImpl implements AppContext {

    // extension configuration object
	private ExtensionConfiguration extensionConfig;
	
	// holds all request listeners's structure
	private RequestListenerCenter requestListenerCenter;
	
	// holds all structures of server event handler classes
	private ServerEventHandlerClasses serverEventHandlerClasses;
	
	// map of interface names and constructor of command implementation classes
	private Map<Object, Constructor<?>> commands;
	
	// map of interface names and constructor of command implementation classes in a specific application
	private Map<Object, Constructor<?>> appCommands
	        = new ConcurrentHashMap<>();
	
	// properties object
	private Map<Object, Object> properties
	        = new ConcurrentHashMap<>();
	
	/**
	 * Application entry point's class
	 * 
	 * @param entryPoint entry point's class
	 */
	public AppContextImpl(Class<?> entryPoint) {
		this.initExtensionConfig(entryPoint);
		this.initEventHandlerClasses();
		this.initRequestListenerCenter();
		
		this.initCommands();
		this.initProperties();
	}
	
	/**
	 * Indicate this event will be auto response
	 * 
	 * @param event the event
	 * @return true or false
	 */
	public boolean isAutoResponse(String event) {
	    return extensionConfig.getAutoResponseEvents().contains(event);
	}
	
	/**
	 * @see AppContext#set(Object, Object)
	 */
	@Override
	public void set(Object key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * @see AppContext#get(Object, Class)
	 */
	@Override
	public <T> T get(Object key, Class<T> clazz) {
		return clazz.cast(properties.get(key));
	}
	
	/**
	 * Get structure of user agent class
	 * 
	 * @return structure of user agent class
	 */
	public UserAgentClass getUserAgentClass() {
		return extensionConfig.getUserAgentClass();
	}
	
	/**
	 * Get map of user agent classes and their structure
	 * 
	 * @return a map
	 */
	public Map<Class<?>, AgentClass> getRoomAgentClasses() {
		return extensionConfig.getRoomAgentClasses();
	}
	
	/**
	 * Get map of game user agent classes and their structure
	 * 
	 * @return a map
	 */
	public Map<Class<?>, UserAgentClass> getGameUserAgentClasses() {
	    return extensionConfig.getGameUserAgentClasses();
	}
	
	/**
	 * Get structure of room agent class map to the class
	 * 
	 * @param clazz room agent class
	 * @return structure of room agent class
	 */
	public AgentClass getRoomAgentClass(Class<?> clazz) {
	    return getRoomAgentClasses().get(clazz);
	}
	
	/**
	 * Get structure of user agent class map to the class
	 * 
	 * @param clazz room agent class
	 * @return structure of user agent class
	 */
	public UserAgentClass getUserAgentClass(Class<?> clazz) {
	    if(clazz == getUserClass())
	        return getUserAgentClass();
	    return getGameUserAgentClasses().get(clazz);
	}
	
	/**
	 * @return user agent's class
	 */
    public Class<?> getUserClass() {
        return extensionConfig.getUserClass();
    }
    
    /**
     * @return list of room agent classes
     */
    public List<Class<?>> getRoomClasses() {
        return extensionConfig.getRoomClasses();
    }
    
    /**
     * @return list of game user agent classes
     */
    public List<Class<?>> getGameUserClasses() {
        return extensionConfig.getGameUserClasses();
    }
    
    /**
     * @return map of response parameter classes and their structure
     */
    public Map<Class<?>, ResponseParamsClass> getResponseParamsClasses() {
        return extensionConfig.getResponseParamsClasses();
    }
    
    /**
     * Get structure of response parameter class map to the class
     * 
     * @param clazz response parameter class
     * @return structure of response parameter class
     */
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
        return getResponseParamsClasses().get(clazz);
    }
    
    /**
     * @return map of message parameter classes and their structure
     */
    public Map<Class<?>, MessageParamsClass> getMessageParamsClasses() {
        return extensionConfig.getMessageParamsClasses();
    }
    
    /**
     * Get structure of message parameter class map to the class
     * 
     * @param clazz message parameter class
     * @return structure of message parameter class
     */
    public MessageParamsClass getMessageParamsClass(Class<?> clazz) {
        return getMessageParamsClasses().get(clazz);
    }
	
    /**
     * Get list of request listeners related to the command
     * 
     * @param command request's command
     * @return list of request listeners
     */
	public List<RequestResponseClass> clientRequestListeners(String command) {
		return requestListenerCenter.getListeners(command);
	}
	
	/**
	 * Get list of server event handler classes related to the event
	 * 
	 * @param event the event
	 * @return list of server event handler classes
	 */
    public List<Class<?>> serverEventHandlerClasses(String event) {
        return serverEventHandlerClasses.getHandlers(event);
    }

    /**
     * @return set of client request commands
     */
	public Set<String> clientRequestCommands() {
		return requestListenerCenter.getCommands();
	}
	
	/**
	 * Initialize extension configuration
	 * 
	 * @param entryPoint application's entry point class
	 */
	private void initExtensionConfig(Class<?> entryPoint) {
		extensionConfig = new ExtensionConfiguration();
		extensionConfig.load(entryPoint);
	}
	
	/**
	 * Get all request listener classes and read their structure
	 */
	private void initRequestListenerCenter() {
		requestListenerCenter
			= new RequestListenerCenter();
		requestListenerCenter.addListeners(extensionConfig
				.getRequestResponseClientClasses());
	}
	
	/**
	 * Get all event handler classes and read their structure
	 */
	private void initEventHandlerClasses() {
		serverEventHandlerClasses 
            = new ServerEventHandlerClasses();
		serverEventHandlerClasses.addHandlers(extensionConfig
            .getServerEventHandlerClasses());
	}
	
	/**
	 * Read configuration file and add all constructors to map
	 */
	private void initCommands() {
	    Map<Object, Class<?>> commandsClass 
	            = CommandProvider.provide(getClass());
	    commands = new HashMap<>();
	    for(Entry<Object, Class<?>> entry : commandsClass.entrySet()) {
	        Constructor<?> constructor = getCommandConstructor(entry.getValue());
	        commands.put(entry.getKey(), constructor);
	    }
	}
	
	/**
	 * initialize properties object
	 */
	private void initProperties() {
		properties = new HashMap<>();
	}
	
	/**
	 * Get constructor of the command implementation class
	 * 
	 * @param commandClass command implementation class
	 * @return a constructor object
	 */
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
	
	/**
	 * Get command by interface class
	 * 
	 * @param clazz interface class
	 * @return a command instance
	 */
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
	
	/**
     * Get application command by base class
     * 
     * @param clazz the base class
     * @return a command instance
     */
    @SuppressWarnings("unchecked")
    private <T> T getAppCommand(Class<T> clazz) {
        Constructor<?> constructor = appCommands.get(clazz.getName());
        if(constructor == null)
            throw new RuntimeException("Can not retrive implementation of command " + clazz);
        try {
            return (T)ReflectClassUtil.newInstance(constructor);
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not retrive implementation of command " + clazz, e);
        }
    }
	
	/**
	 * Add the application command to the map
	 * 
	 * @param baseClass the interface or abstract class
	 * @param implementation the implementation class of command
	 */
	public void addAppCommand(Class<?> baseClass, Class<?> implementation) {
	    try {
            Constructor<?> constructor = ReflectClassUtil.getConstructor(implementation);
            appCommands.put(baseClass.getName(), constructor);
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not get constructor of command class " 
                    + implementation);
        }
	}
	
	/**
	 * Get command by interface class
	 */
	public <T> T command(Class<T> clazz) {
	    try {
	        return getCommand(clazz);
	    }
	    catch(Exception e) {
	        return getAppCommand(clazz);
	    }
	}
	
	// smartfox api
	private ISFSApi api;
	
	// smartfox extension
	private ISFSExtension extension;
	
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
