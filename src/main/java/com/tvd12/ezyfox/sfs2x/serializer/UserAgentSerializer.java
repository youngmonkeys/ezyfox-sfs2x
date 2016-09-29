package com.tvd12.ezyfox.sfs2x.serializer;

import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;

/**
 * Support to serialize a user agent object to a list of variables
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public final class UserAgentSerializer extends AgentSerializer {
	
	/**
	 * @see AgentSerializer#newVariable(String, Object, boolean)
	 */
    @Override
    @SuppressWarnings("unchecked")
	protected UserVariable newVariable(String name, Object value, boolean isHidden) {
	    UserVariable var = new SFSUserVariable(name, value);
	    var.setHidden(isHidden);
	    return var;
	}
	
}
