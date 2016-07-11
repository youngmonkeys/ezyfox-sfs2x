package com.tvd12.ezyfox.sfs2x.filter;

import com.smartfoxserver.v2.controllers.SystemRequest;
import com.smartfoxserver.v2.controllers.filter.SysControllerFilter;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.filter.FilterAction;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on Jul 1, 2016
 *
 */
public class BaseSysControllerFilter extends SysControllerFilter {

    // request handler
    protected ClientEventHandler handler;
    /**
     * @param context application context
     * @param request request name
     */
    public BaseSysControllerFilter(AppContextImpl context, 
            SystemRequest request) {
        handler = new ClientEventHandler(context, request.toString());
    }
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.controllers.filter.ISystemFilter#handleClientRequest(com.smartfoxserver.v2.entities.User, com.smartfoxserver.v2.entities.data.ISFSObject)
     */
    @Override
    public FilterAction handleClientRequest(User user, ISFSObject params) throws SFSException {
        try {
            handler.handleClientRequest(user, params);
        }
        catch(Exception e) { 
            return FilterAction.HALT; 
        }
        return FilterAction.CONTINUE;
    }
    
    
    
}
