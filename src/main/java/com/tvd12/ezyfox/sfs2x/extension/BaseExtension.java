package com.tvd12.ezyfox.sfs2x.extension;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.smartfoxserver.v2.extensions.IClientRequestHandler;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.bridge.ClientRequestHandlers;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRequestHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.SmartFoxContext;

/**
 * @author tavandung12
 * Created on Sep 26, 2016
 *
 */
public abstract class BaseExtension extends SFSExtension implements ClientRequestHandlers {
    
    protected BaseContext context;
    protected Map<Object, Object> clientRequestHandlers;

    @Override
    public void init() {
    	initComponent();
        initContext();
        addToContext();
        before();
        addClientRequestHandlers();
        config();
        after();
    }
    
    protected void initComponent() {
    	this.clientRequestHandlers = new ConcurrentHashMap<>();
    }
    
    /**
     * Initialize application context
     */
    protected void initContext() {
        context = createContext();
        SmartFoxContext sfsContext = (SmartFoxContext)context;
        sfsContext.setApi(getApi());
        sfsContext.setExtension(this);
    }
    
    protected void addToContext() {
    	this.context.set(ClientRequestHandlers.class, this);
    }
    
    /**
     * Add client request handlers
     */
    protected void addClientRequestHandlers() {
        Set<String> commands = 
                context.getClientRequestCommands();
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
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.SFSExtension#addRequestHandler(java.lang.String, com.smartfoxserver.v2.extensions.IClientRequestHandler)
     */
    @Override
    protected void addRequestHandler(String requestId, IClientRequestHandler requestHandler) {
    	super.addRequestHandler(requestId, requestHandler);
    	registerClientRequestHandler(requestId, requestHandler);
    }
    
	/**
	 * Add the handler that handle the request to map
	 * 
	 * @param cmd the request command
	 * @param handler the handler
	 */
    protected void registerClientRequestHandler(Object cmd, Object handler) {
    	this.clientRequestHandlers.put(cmd, handler);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.bridge.ClientRequestHandlers#getClientRequestHandler(java.lang.Object)
     */
    @Override
    public Object getClientRequestHandler(Object cmd) {
    	return this.clientRequestHandlers.get(cmd);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.bridge.ClientRequestHandlers#containsClientRequestHandler(java.lang.Object)
     */
    @Override
    public boolean containsClientRequestHandler(Object cmd) {
    	return this.clientRequestHandlers.containsKey(cmd);
    }
    
    /**
     * Create the context
     * 
     * @return the context
     */
    protected abstract BaseContext createContext();
    
    /**
     * Override this function to add custom configuration
     */
    protected void config() {
    }
    
    /**
     * Invoke after initializing application and before initialize anything
     */
    protected void before() {
    }
    
    /**
     * Invoke after initialized all
     */
    protected void after() {
    }
    
}
