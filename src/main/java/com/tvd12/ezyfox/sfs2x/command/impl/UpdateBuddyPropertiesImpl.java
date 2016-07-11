package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.serializer.BuddyAgentSerializer.buddyAgentSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.buddylist.BuddyVariable;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UpdateBuddyProperties;
import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.structure.BuddyClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see UpdateBuddyProperties
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class UpdateBuddyPropertiesImpl extends BaseCommandImpl implements UpdateBuddyProperties {

    private ApiBaseUser owner;
    private boolean fireClientEvent = true;
    private boolean fireServerEvent = true;
    private List<String> includedVars = new ArrayList<>();
    private List<String> excludedVars = new ArrayList<>();
    
    /**
     * @param context
     * @param api
     * @param extension
     */
    public UpdateBuddyPropertiesImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
        super(context, api, extension);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.BaseCommand#execute()
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean execute() {
        BuddyClassUnwrapper unwrapper = context.getUserAgentClass(owner.getClass())
                .getBuddyClass().getUnwrapper();
        List<BuddyVariable> variables = buddyAgentSerializer().serialize(unwrapper, owner);
        List<BuddyVariable> answer = variables;
        if(includedVars.size() > 0) 
            answer = getVariables(variables, includedVars);
        answer.removeAll(getVariables(answer, excludedVars));
        try {
            User sfsUser = CommandUtil.getSfsUser(owner, api);
            SmartFoxServer.getInstance().getAPIManager().getBuddyApi()
                    .setBuddyVariables(sfsUser, answer, 
                            fireClientEvent, fireServerEvent);
        } catch (SFSBuddyListException e) {
            throw new IllegalStateException(e);
        }
        return Boolean.TRUE;
    }
    
    private List<BuddyVariable> getVariables(List<BuddyVariable> variables, List<String> varnames) {
        List<BuddyVariable> answer = new ArrayList<>();
        for(String ic : varnames) {
            BuddyVariable var = null;
            for(BuddyVariable v : variables) {
                if(v.getName().equals(ic)) {
                    var = v; break;
                }
            }
            if(var != null) answer.add(var);
        }
        return answer;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#owner(com.tvd12.ezyfox.core.entities.ApiBaseUser)
     */
    @Override
    public UpdateBuddyProperties owner(ApiBaseUser user) {
        this.owner = user;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#fireClientEvent(boolean)
     */
    @Override
    public UpdateBuddyProperties fireClientEvent(boolean value) {
        this.fireClientEvent = value;
        return this;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#fireServerEvent(boolean)
     */
    @Override
    public UpdateBuddyProperties fireServerEvent(boolean value) {
        this.fireServerEvent = value;
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#include(java.lang.String[])
     */
    @Override
    public UpdateBuddyProperties include(String... varnames) {
        this.includedVars.addAll(Arrays.asList(varnames));
        return this;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.UpdateBuddyProperties#exclude(java.lang.String[])
     */
    @Override
    public UpdateBuddyProperties exclude(String... varnames) {
        this.excludedVars.addAll(Arrays.asList(varnames));
        return this;
    }

}
