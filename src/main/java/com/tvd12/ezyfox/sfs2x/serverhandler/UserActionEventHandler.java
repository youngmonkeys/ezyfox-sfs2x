package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * Support to handle event related to user action
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public abstract class UserActionEventHandler extends ServerUserEventHandler {

    /**
     * @param context
     */
	public UserActionEventHandler(AppContextImpl context) {
		super(context);
	}
	
	/**
	 * @see BaseServerEventHandler#handleServerEvent(ISFSEvent)
	 */
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		User sfsUser = (User) event.getParameter(SFSEventParam.USER);
		ApiUser apiUser = (ApiUser)sfsUser.getProperty(APIKey.USER);
		notifyHandlers(apiUser);
	}
	
	/**
     * Propagate event to handlers
     * 
     * @param apiUser user agent object
     */
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
    
    /**
     * @see ServerUserEventHandler#checkUserAgent(ServerUserHandlerClass, ApiUser)
     */
    @Override
    protected Object checkUserAgent(ServerUserHandlerClass handler, ApiUser userAgent) {
        Object agent = null;
        if(userAgent != null)
            agent = super.checkUserAgent(handler, userAgent);
        return handler.getUserClass().cast(agent);
    }
    
}
