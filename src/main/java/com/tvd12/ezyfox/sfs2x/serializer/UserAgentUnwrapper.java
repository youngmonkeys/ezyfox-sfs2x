package com.tvd12.ezyfox.sfs2x.serializer;

import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;

public final class UserAgentUnwrapper extends AgentUnwrapper {
	
	private static UserAgentUnwrapper instance;
	
	private UserAgentUnwrapper() {}
	
    @Override
    @SuppressWarnings("unchecked")
	protected UserVariable newVariable(String name, Object value, boolean isHidden) {
	    UserVariable var = new SFSUserVariable(name, value);
	    var.setHidden(isHidden);
	    return var;
	}
	
	public static UserAgentUnwrapper getInstance() {
		if(instance == null) 
			instance = new UserAgentUnwrapper();
		return instance;
	}
	
	public static UserAgentUnwrapper userAgentUnwrapper() {
		return getInstance();
	}
	
}
