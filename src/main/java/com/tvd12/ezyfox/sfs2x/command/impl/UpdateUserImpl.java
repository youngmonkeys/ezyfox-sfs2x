package com.tvd12.ezyfox.sfs2x.command.impl;

import static com.tvd12.ezyfox.sfs2x.serializer.UserAgentUnwrapper.userAgentUnwrapper;

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

public class UpdateUserImpl extends BaseCommandImpl implements UpdateUser {

	private ApiBaseUser agent;
	private boolean toClient = true;
	
	public UpdateUserImpl(AppContextImpl context, ISFSApi api, ISFSExtension extension) {
		super(context, api, extension);
	}

	@SuppressWarnings("unchecked")
    @Override
	public <T> T execute() {
		User sfsUser = CommandUtil.getSfsUser(agent, api);
		if(sfsUser == null) return null;
		try {
		    AgentClassUnwrapper unwrapper = context.getUserAgentClass(agent.getClass())
		            .getUnwrapper();
			List<UserVariable> variables = userAgentUnwrapper().unwrap(unwrapper, agent);
			
			//update user variables on server and notify to client
			if(toClient) api.setUserVariables(sfsUser, variables);
			
			// only update user variables on server
			else sfsUser.setVariables(variables);
		} 
		catch (SFSVariableException e) {
		    throw new IllegalStateException(e);
		}
		return (T)agent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UpdateUser toClient(boolean value) {
		this.toClient = value;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UpdateUser user(ApiBaseUser user) {
		this.agent = user;
		return this;
	}

}
