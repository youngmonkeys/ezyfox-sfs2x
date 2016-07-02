package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.serializer.UserAgentSerializer.userAgentSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.UpdateUser;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @see UpdateUser
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class UpdateUserImpl extends BaseCommandImpl implements UpdateUser {

	private ApiBaseUser agent;
	private boolean toClient = true;
	private List<String> includedVars = new ArrayList<>();
	private List<String> excludedVars = new ArrayList<>();
	
	/**
	 * @param context
	 * @param api
	 * @param extension
	 */
	public UpdateUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	/**
	 * Execute to update user variables
	 */
	@SuppressWarnings("unchecked")
    @Override
	public <T> T execute() {
		User sfsUser = CommandUtil.getSfsUser(agent, api);
		if(sfsUser == null) return null;
		try {
		    AgentClassUnwrapper unwrapper = context.getUserAgentClass(agent.getClass())
		            .getUnwrapper();
			List<UserVariable> variables = userAgentSerializer().serialize(unwrapper, agent);
			List<UserVariable> answer = variables;
			if(includedVars.size() > 0) 
			    answer = getVariables(variables, includedVars);
			answer.removeAll(getVariables(answer, excludedVars));
			
			//update user variables on server and notify to client
			if(toClient) api.setUserVariables(sfsUser, answer);
			
			// only update user variables on server
			else sfsUser.setVariables(answer);
		} 
		catch (SFSVariableException e) {
		    throw new IllegalStateException(e);
		}
		return (T)agent;
	}
	
	private List<UserVariable> getVariables(List<UserVariable> variables, List<String> varnames) {
	    List<UserVariable> answer = new ArrayList<>();
        for(String ic : varnames) {
            UserVariable var = null;
            for(UserVariable v : variables) {
                if(v.getName().equals(ic)) {
                    var = v; break;
                }
            }
            if(var != null) answer.add(var);
        }
        return answer;
	}

	/**
	 * @see UpdateUser#toClient(boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UpdateUser toClient(boolean value) {
		this.toClient = value;
		return this;
	}

	/**
	 * @see UpdateUser#user(ApiBaseUser)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UpdateUser user(ApiBaseUser user) {
		this.agent = user;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.UpdateUser#include(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
    @Override
	public UpdateUser include(String... varnames) {
	    includedVars.addAll(Arrays.asList(varnames));
	    return this;
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.command.UpdateUser#exclude(java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
    @Override
	public UpdateUser exclude(String... varnames) {
	    excludedVars.addAll(Arrays.asList(varnames));
	    return this;
	}

}
