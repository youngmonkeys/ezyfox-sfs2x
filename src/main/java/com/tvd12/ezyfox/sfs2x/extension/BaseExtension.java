package com.tvd12.ezyfox.sfs2x.extension;

import java.util.Set;

import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRequestHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.SmartFoxContext;

/**
 * @author tavandung12
 * Created on Sep 26, 2016
 *
 */
public abstract class BaseExtension extends SFSExtension {
    
    protected BaseContext context;

    @Override
    public void init() {
        initContext();
        before();
        addClientRequestHandlers();
        config();
        after();
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
    
    /**
     * Initialize application context
     */
    protected void initContext() {
        context = createContext();
        SmartFoxContext sfsContext = (SmartFoxContext)context;
        sfsContext.setApi(getApi());
        sfsContext.setExtension(this);
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
