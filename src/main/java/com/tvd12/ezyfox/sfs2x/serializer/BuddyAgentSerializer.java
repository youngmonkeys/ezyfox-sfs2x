package com.tvd12.ezyfox.sfs2x.serializer;

import com.smartfoxserver.v2.buddylist.BuddyVariable;
import com.smartfoxserver.v2.buddylist.SFSBuddyVariable;

/**
 * Support to serialize a java object to list of buddy variables
 * 
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class BuddyAgentSerializer extends AgentSerializer {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serializer.AgentUnwrapper#newVariable(java.lang.String, java.lang.Object, boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected BuddyVariable newVariable(String name, Object value, boolean isHidden) {
        return new SFSBuddyVariable(name, value);
    }
    
    private static BuddyAgentSerializer instance;
    
    private BuddyAgentSerializer() {}
    
    public static BuddyAgentSerializer getInstance() {
        if(instance == null) 
            instance = new BuddyAgentSerializer();
        return instance;
    }
    
    public static BuddyAgentSerializer buddyAgentSerializer() {
        return getInstance();
    }

}
