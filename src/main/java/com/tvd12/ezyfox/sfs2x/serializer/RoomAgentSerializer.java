package com.tvd12.ezyfox.sfs2x.serializer;

import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;

/**
 * Support to serialize a room agent object to a list of variables
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public final class RoomAgentSerializer extends AgentSerializer {
	
	private static RoomAgentSerializer instance;
	
	private RoomAgentSerializer() {}
	
	/**
	 * @see AgentSerializer#newVariable(String, Object, boolean)
	 */
	@Override
	@SuppressWarnings("unchecked")
    protected RoomVariable newVariable(
            String name, Object value, boolean isHidden) {
	    RoomVariable var = new SFSRoomVariable(name, value);
        var.setHidden(isHidden);
        return var;
	}
	
	public static RoomAgentSerializer getInstance() {
        if(instance == null) 
            instance = new RoomAgentSerializer();
        return instance;
    }
    
    public static RoomAgentSerializer roomAgentSerializer() {
        return getInstance();
    }

}
