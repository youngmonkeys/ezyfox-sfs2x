package com.tvd12.ezyfox.sfs2x.serializer;

import com.smartfoxserver.v2.buddylist.BuddyVariable;
import com.smartfoxserver.v2.buddylist.SFSBuddyVariable;

/**
 * @author tavandung12
 * Created on May 27, 2016
 *
 */
public class BuddyAgentUnwrapper extends AgentUnwrapper {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serializer.AgentUnwrapper#newVariable(java.lang.String, java.lang.Object, boolean)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected BuddyVariable newVariable(String name, Object value, boolean isHidden) {
        return new SFSBuddyVariable(name, value);
    }
    
    private static BuddyAgentUnwrapper instance;
    
    private BuddyAgentUnwrapper() {}
    
    public static BuddyAgentUnwrapper getInstance() {
        if(instance == null) 
            instance = new BuddyAgentUnwrapper();
        return instance;
    }
    
    public static BuddyAgentUnwrapper buddyAgentUnwrapper() {
        return getInstance();
    }

}
