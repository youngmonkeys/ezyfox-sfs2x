package com.tvd12.ezyfox.sfs2x.clienthandler;

import static com.tvd12.ezyfox.sfs2x.serializer.RequestParamDeserializer.requestParamDeserializer;
import static com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer.responseParamSerializer;

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

/**
 * 
 * This class handle request from client and notify that request to all listener that it manages
 * 
 * @author tavandung12
 * Created on May 31, 2016
 *
 */

public class ClientEventHandler extends ClientRequestHandler {
	
    /**
     * @param context application context
     * @param command request's command
     */
	public ClientEventHandler(AppContextImpl context, String command) {
		super(context, command);
	}

	/**
	 * Handle request from client
	 */
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
	
	/**
	 * Notify all listeners
	 * 
	 * @param clazz structure of listener class
	 * @param params request parameters
	 * @param user request user
	 * @param userAgent user agent's object
	 * @throws Exception when has any error from listener
	 */
	private void notifyListener(RequestResponseClass clazz, 
	        ISFSObject params, User user, Object userAgent) throws Exception {
	    Object listener = requestParamDeserializer()
	            .deserialize(clazz.getRequestListenerClass(), params);
	    ReflectMethodUtil.invokeExecuteMethod(
	            clazz.getExecuteMethod(), listener, context, userAgent);
	    responseClient(clazz, listener, user);
	}
	
	/**
	 * For each listener, we may use a class of user agent, so we need check it 
	 * 
	 * @param clazz structure of listener class
	 * @param userAgent user agent object
	 * @return instance of user agent
	 */
	private Object checkUserAgent(RequestResponseClass clazz, ApiUser userAgent) {
        if(clazz.getUserClass() == userAgent.getClass())
            return userAgent;
        return UserAgentUtil.getGameUser(userAgent, clazz.getUserClass());
    }
	
	/**
	 * Response information to client
	 * 
	 * @param clazz structure of listener class
	 * @param listener listener object
	 * @param user smartfox user
	 */
	private void responseClient(RequestResponseClass clazz, Object listener, User user) {
		if(!clazz.isResponseToClient())
			return;
		String command = clazz.getResponseCommand();
		ISFSObject params = responseParamSerializer()
					.object2params(clazz.getResponseHandlerClass(), listener);
		send(command, params, user);
	}
	
	/**
	 * Check if has any listeners throw a BadRequestException
	 * 
	 * @param e exception
	 * @return true or false
	 */
	private boolean isBadRequestException(Exception e) {
	    return ExceptionUtils.indexOfThrowable(e, BadRequestException.class) != -1;
	}
}
