/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    /**
     * @param context
     * @param api
     * @param extension
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
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Response, U extends ApiBaseUser> T recipients(List<U> users) {
        for(ApiBaseUser user : users)
            this.usernames.add(user.getName());
        return (T)this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#recipient(com.lagente.core.model.ApiBaseUser)
     */
    @Override
    public Response recipients(String... usernames) {
        this.usernames.addAll(Arrays.asList(usernames));
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

        return Boolean.TRUE;
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
    
}
