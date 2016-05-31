package com.tvd12.ezyfox.sfs2x.serializer;

import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;

public final class RoomAgentUnwrapper extends AgentUnwrapper {
	
	private static RoomAgentUnwrapper instance;
	
	private RoomAgentUnwrapper() {}
	
	@Override
	@SuppressWarnings("unchecked")
    protected RoomVariable newVariable(
            String name, Object value, boolean isHidden) {
	    RoomVariable var = new SFSRoomVariable(name, value);
        var.setHidden(isHidden);
        return var;
	}
	
	public static RoomAgentUnwrapper getInstance() {
        if(instance == null) 
            instance = new RoomAgentUnwrapper();
        return instance;
    }
    
    public static RoomAgentUnwrapper roomAgentUnwrapper() {
        return getInstance();
    }

}
