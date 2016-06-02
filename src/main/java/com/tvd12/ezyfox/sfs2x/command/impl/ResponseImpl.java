/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.Response;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

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
    private Set<String> usernames
            = new HashSet<>();
    private Map<String, SFSDataWrapper> addition 
            = new HashMap<>();
    
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
    @SuppressWarnings("unchecked")
    @Override
    public Response command(String command) {
        this.command = command;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#data(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Response data(Object object) {
        this.data = object;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Response#param(java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Response param(String name, Object value) {
        addition.put(name, TRANSFORMER.transform(value));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#user(com.lagente.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Response recipient(ApiBaseUser user) {
        this.usernames.add(user.getName());
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#recipient(com.lagente.core.model.ApiBaseUser)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Response recipient(String username) {
        this.usernames.add(username);
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.command.Response#useUDP(boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Response useUDP(boolean value) {
        this.useUDP = value;
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
        ISFSObject answer = (data != null) 
                ? ResponseParamSerializer.getInstance()
                        .object2params(context.getResponseParamsClass(data.getClass()), data)
                : new SFSObject();
        for(Entry<String, SFSDataWrapper> entry : addition.entrySet())
            answer.put(entry.getKey(), entry.getValue());
        return answer;
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
 
    private static final SimpleTransformer TRANSFORMER
            = new SimpleTransformer();
}
