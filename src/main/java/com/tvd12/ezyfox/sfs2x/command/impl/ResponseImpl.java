/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.data.impl.ParamTransformer;

/**
 * @see Response
 * 
 * @author tavandung12
 *
 */
public class ResponseImpl extends BaseCommandImpl implements Response {

    private Object data;
    private String command;
    private boolean useUDP = false;
    
    private List<String> includedVars = new ArrayList<>();
    private List<String> excludedVars = new ArrayList<>();
    
    private Set<String> usernames = new HashSet<>();
    private Map<String, Object> addition = new HashMap<>();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);
    
    /**
     * @param context the context
     * @param api the api
     * @param extension the extension
     */
    public ResponseImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#command(java.lang.String)
     */
    @Override
    public Response command(String command) {
        this.command = command;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#data(java.lang.Object)
     */
    @Override
    public Response data(Object object) {
        this.data = object;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#param(java.lang.String, java.lang.Object)
     */
    @Override
    public Response param(String name, Object value) {
        addition.put(name, value);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#user(com.lagente.core.model.ApiBaseUser)
     */
    @Override
    public Response recipients(ApiBaseUser... users) {
        return recipients(Arrays.asList(users));
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#recipients(java.util.List)
     */
    @Override
    public <U extends ApiBaseUser> Response recipients(Collection<U> users) {
        for(ApiBaseUser user : users)
            this.usernames.add(user.getName());
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#recipient(com.lagente.core.model.ApiBaseUser)
     */
    @Override
    public Response recipients(String... usernames) {
        return recipients(Sets.newHashSet(usernames));
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#recipients(java.lang.Iterable)
     */
    @Override
    public Response recipients(Iterable<String> usernames) {
    	this.usernames.addAll(Sets.newHashSet(usernames));
    	return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#exclude(com.tvd12.ezyfox.core.entities.ApiBaseUser[])
     */
    @Override
    public Response exclude(ApiBaseUser... users) {
    	return exclude(Sets.newHashSet(users));
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#exclude(java.util.Collection)
     */
    @Override
    public <U extends ApiBaseUser> Response exclude(Collection<U> users) {
    	for(U user : users)
    		this.usernames.remove(user.getName());
    	return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#useUDP(boolean)
     */
    @Override
    public Response useUDP(boolean value) {
        this.useUDP = value;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#only(java.lang.String[])
     */
    @Override
    public Response only(String... params) {
        includedVars.addAll(Arrays.asList(params));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#ignore(java.lang.String[])
     */
    @Override
    public Response ignore(String... params) {
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
        List<User> users = new ArrayList<>();
        for(String username : usernames) {
            User sfsUser = CommandUtil.getSfsUser(username, api);
            if(sfsUser != null) users.add(sfsUser);
        }
        ISFSObject params = createResponseParams();
        extension.send(command, params, users, useUDP);
        logResponse(params);
        return Boolean.TRUE;
    }
    
    protected void logResponse(ISFSObject params) {
    	getLogger().debug("reponse command: {} with data {}", command, params.toJson());
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
    
    protected Logger getLogger() {
    	return LOGGER;
    }
    
}
