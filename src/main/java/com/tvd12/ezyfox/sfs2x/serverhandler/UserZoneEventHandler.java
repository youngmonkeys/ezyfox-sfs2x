package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.lang.reflect.Method;
import java.util.List;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ZoneEventHandlerCenter;
import com.tvd12.ezyfox.core.factory.UserAgentFactory;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ZoneHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public abstract class UserZoneEventHandler extends ServerUserEventHandler {

    private List<ZoneHandlerClass> handlers;
    
	public UserZoneEventHandler(AppContextImpl context) {
		super(context);
		
	}
	
	@Override
	protected void init() {
	    handlers = new ZoneEventHandlerCenter().addHandlers(
                handlerClasses, 
                context.getUserClass(), context.getGameUserClasses());
	}
	
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException {
		User sfsUser = (User) event.getParameter(SFSEventParam.USER);
		Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
		ApiZone apiZone = getApiZone(sfsZone);
		notifyHandlers(apiZone, sfsUser);
		
	}
	
	private void notifyHandlers(ApiZone apiZone, User sfsUser) {
	    ApiUser apiUser = createUserAgent(sfsUser);
        for(ZoneHandlerClass handler : handlers) {
            Object userAgent = checkUserAgent(handler, apiUser);
            if(!checkHandler(handler, apiZone, userAgent))
                continue;
            sfsUser.setProperty(APIKey.USER, userAgent);
            notifyHandler(handler, apiZone, userAgent);
        }
	}
	
	private void notifyHandler(ZoneHandlerClass handler, 
	        ApiZone apiZone, Object userAgent) {
        Object instance = handler.newInstance();
        callHandleMethod(handler.getHandleMethod(), 
                instance, apiZone, userAgent);
	}
	
	protected boolean checkHandler(ZoneHandlerClass handler, 
	        ApiZone apiZone, Object userAgent) {
        return apiZone.getName().startsWith(handler.getZoneName());
    }
	
	private void callHandleMethod(Method method, Object instance, 
	        ApiZone apiZone, Object userAgent) {
	    ReflectMethodUtil.invokeHandleMethod(method, 
                    instance, context, apiZone, userAgent);
	}

	private ApiUser createUserAgent(User sfsUser) {
	    ApiUser answer = UserAgentFactory.newUserAgent(
	            sfsUser.getName(),
	            context.getUserAgentClass(), 
	            context.getGameUserAgentClasses().values());
	    answer.setId(sfsUser.getId());
	    return answer;
	}
	
	private ApiZone getApiZone(Zone sfsZone) {
		return (ApiZone)sfsZone.getProperty(APIKey.ZONE);
	}
	
}
