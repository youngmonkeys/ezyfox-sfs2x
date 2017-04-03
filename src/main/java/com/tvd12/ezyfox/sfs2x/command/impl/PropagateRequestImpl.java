/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartfoxserver.bitswarm.sessions.Session;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.SFSUser;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.IClientRequestHandler;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.bridge.ClientRequestHandlers;
import com.tvd12.ezyfox.core.command.PropagateRequest;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.data.impl.ParamTransformer;

/**
 * @see Response
 * 
 * @author tavandung12
 *
 */
public class PropagateRequestImpl extends BaseCommandImpl implements PropagateRequest {

	private ApiBaseUser user;
	
    private Object data;
    private String command;
    
    private List<String> includedVars = new ArrayList<>();
    private List<String> excludedVars = new ArrayList<>();
    
    private Map<String, Object> addition = new HashMap<>();
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public PropagateRequestImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.PropagateRequest#command(java.lang.String)
     */
    @Override
    public PropagateRequest command(String command) {
        this.command = command;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.PropagateRequest#data(java.lang.Object)
     */
    @Override
    public PropagateRequest data(Object object) {
        this.data = object;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PropagateRequest#param(java.lang.String, java.lang.Object)
     */
    @Override
    public PropagateRequest param(String name, Object value) {
        addition.put(name, value);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PropagateRequest#user(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public PropagateRequest user(ApiBaseUser user) {
    	this.user = user;
    	return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PropagateRequest#only(java.lang.String[])
     */
    @Override
    public PropagateRequest only(String... params) {
        includedVars.addAll(Arrays.asList(params));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.PropagateRequest#ignore(java.lang.String[])
     */
    @Override
    public PropagateRequest ignore(String... params) {
        excludedVars.addAll(Arrays.asList(params));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        validateCommand();
        User sender = CommandUtil.getSfsUser(user, api);
        sender = sender != null ? sender : newFakeUser();
        ISFSObject params = createResponseParams();
        getRequestHandler().handleClientRequest(sender, params);
        return Boolean.TRUE;
    }
    
    private User newFakeUser() {
    	FakeUser sfsUser = new FakeUser();
    	sfsUser.setName(user.getName());
    	sfsUser.setProperty(APIKey.USER, user);
    	return sfsUser;
    }
    
    private IClientRequestHandler getRequestHandler() {
    	ClientRequestHandlers handlers = getRequestHandlers();
    	if(handlers.containsClientRequestHandler(command))
    		return (IClientRequestHandler)handlers.getClientRequestHandler(command);
    	throw new IllegalStateException("has no handler for command: " + command);
    }
    
    private ClientRequestHandlers getRequestHandlers() {
    	return context.get(ClientRequestHandlers.class);
    }
    
    /**
     * Create smartfox object to response to client
     * 
     * @return smartfox parameter object
     */
    private ISFSObject createResponseParams() {
        return ResponseParamsBuilder.create()
                .addition(addition)
                .excludedVars(excludedVars)
                .includedVars(includedVars)
                .transformer(new ParamTransformer(context))
                .data(data)
                .build();
    }
    
    /**
     * Validate command
     * 
     * @throws IllegalStateException when command is null or empty
     */
    private void validateCommand() {
        if(command == null || command.trim().isEmpty())
            throw new IllegalStateException("Invalid command");
    }
    
    public static class FakeUser extends SFSUser {

    	public FakeUser() {
			super(new Session());
		}
    	
    }
    
}
