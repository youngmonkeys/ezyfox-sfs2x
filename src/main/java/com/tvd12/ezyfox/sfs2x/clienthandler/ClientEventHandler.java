package com.tvd12.ezyfox.sfs2x.clienthandler;

import static com.tvd12.ezyfox.sfs2x.serializer.RequestParamParser.requestParamParser;
import static com.tvd12.ezyfox.sfs2x.serializer.ResponseParamParser.responseParamParser;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.tvd12.ezyfox.core.exception.BadRequestException;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.util.UserAgentUtil;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.util.AgentUtil;

public class ClientEventHandler extends ClientRequestHandler {
	
	public ClientEventHandler(AppContextImpl context, String command) {
		super(context, command);
	}

	@Override
	public void handleClientRequest(User user, ISFSObject params) {
		ApiUser apiUser = AgentUtil.getUserAgent(user);
		for(RequestResponseClass clazz : listeners) {
		    Object userAgent = checkUserAgent(clazz, apiUser);
		    try {
		        notifyListener(clazz, params, user, userAgent);
		    } catch(Exception e) {
		        if(isBadRequestException(e))
		            break;
		        throw new RuntimeException(e);
		    }
		}
	}
	
	private void notifyListener(RequestResponseClass clazz, 
	        ISFSObject params, User user, Object userAgent) throws Exception {
	    Object listener = requestParamParser()
	            .assignValues(clazz.getRequestListenerClass(), params);
	    ReflectMethodUtil.invokeExecuteMethod(
	            clazz.getExecuteMethod(), listener, context, userAgent);
	    responseClient(clazz, listener, user);
	}
	
	private Object checkUserAgent(RequestResponseClass clazz, ApiUser userAgent) {
        if(clazz.getUserClass() == userAgent.getClass())
            return userAgent;
        return UserAgentUtil.getGameUser(userAgent, clazz.getUserClass());
    }
	
	private void responseClient(RequestResponseClass clazz, Object listener, User user) {
		if(!clazz.isResponseToClient())
			return;
		String command = clazz.getResponseCommand();
		ISFSObject params = responseParamParser()
					.parse(clazz.getResponseHandlerClass(), listener);
		send(command, params, user);
	}
	
	private boolean isBadRequestException(Exception e) {
	    return ExceptionUtils.indexOfThrowable(e, BadRequestException.class) != -1;
	}
}
