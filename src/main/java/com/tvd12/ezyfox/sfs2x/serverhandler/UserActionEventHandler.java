package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public abstract class UserActionEventHandler extends ServerUserEventHandler {

	public UserActionEventHandler(AppContextImpl context) {
		super(context);
	}
	
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		User sfsUser = (User) event.getParameter(SFSEventParam.USER);
		ApiUser apiUser = (ApiUser)sfsUser.getProperty(APIKey.USER);
		notifyHandlers(apiUser);
	}
	
	
    private void notifyHandlers(ApiUser apiUser) {
        for(ServerUserHandlerClass handler : serverUserEventHandler) {
            Object userAgent = checkUserAgent(handler, apiUser);
            ReflectMethodUtil.invokeHandleMethod(
                    handler.getHandleMethod(), 
                    handler.newInstance(), 
                    context, 
                    userAgent);
        }
    }
    
    @Override
    protected Object checkUserAgent(ServerUserHandlerClass handler, ApiUser userAgent) {
        Object agent = null;
        if(userAgent != null)
            agent = super.checkUserAgent(handler, userAgent);
        return handler.getUserClass().cast(agent);
    }
    
}
