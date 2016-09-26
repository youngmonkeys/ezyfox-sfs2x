package com.tvd12.ezyfox.sfs2x.serverhandler;

import static com.tvd12.ezyfox.sfs2x.serializer.RequestParamDeserializer.requestParamDeserializer;
import static com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer.responseParamSerializer;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.smartfoxserver.bitswarm.sessions.ISession;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.IErrorCode;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.tvd12.ezyfox.core.config.UserLoginEventHandlerCenter;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.entities.ApiLogin;
import com.tvd12.ezyfox.core.entities.ApiLoginImpl;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.exception.BadRequestException;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.UserLoginHandlerClass;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiSessionImpl;

/**
 * Support to handle user login event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public class UserLoginEventHandler extends ServerBaseEventHandler {

    // list of structures of login handler classes
    private List<UserLoginHandlerClass> loginHandlers;
    
    /**
     * @param context the context
     */
    public UserLoginEventHandler(BaseAppContext context) {
        super(context);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        loginHandlers = new UserLoginEventHandlerCenter()
                .addHandlers(handlerClasses, ApiLogin.class);
    }

    /*
     * (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        ApiLogin wrapper = wrapLoginData(event);
        ISFSObject inData = (ISFSObject)event.getParameter(SFSEventParam.LOGIN_IN_DATA);
        ISFSObject outData = (ISFSObject)event.getParameter(SFSEventParam.LOGIN_OUT_DATA);
        notifyHandler(wrapper, inData, outData);
    }
    
    /**
     * Wrap the login data
     * 
     * @param event the event
     * @return the wrapper
     */
    private ApiLogin wrapLoginData(ISFSEvent event) {
        Zone zone = (Zone) event.getParameter(SFSEventParam.ZONE);
        ISession session = (ISession) event.getParameter(SFSEventParam.SESSION);
        String name = (String) event.getParameter(SFSEventParam.LOGIN_NAME);
        String pass = (String) event.getParameter(SFSEventParam.LOGIN_PASSWORD);
        ApiLoginImpl apiLogin = new ApiLoginImpl();
        apiLogin.setZone((ApiZone) zone.getProperty(APIKey.ZONE));
        apiLogin.setSession(new ApiSessionImpl(session));
        apiLogin.setUsername(name);
        apiLogin.setPassword(pass);
        return apiLogin;
    }

    /**
     * Propagate event to handler
     * 
     * @param wrapper the login data wrapper
     * @param inData login in data
     * @param outData login out data
     * @throws SFSLoginException when has any errors
     */
    protected void notifyHandler(ApiLogin wrapper, 
            ISFSObject inData, ISFSObject outData) throws SFSLoginException {
        try {
            for (UserLoginHandlerClass handler : loginHandlers)
                notifyHanlder(handler, wrapper, inData, outData);
        } catch(Exception e) { throw processException(e); }
    }
    
    /**
     * Process the exception
     * 
     * @param e the exception
     * @return the SFSLoginException exception
     */
    protected SFSLoginException processException(Exception e) {
        if(!isBadRequestException(e)) 
            return new SFSLoginException();
        final BadRequestException ex = getBadRequestException(e);
        return new SFSLoginException(ex.getMessage(), new SFSErrorData(new IErrorCode() {
            @Override
            public short getId() {
                return (short)ex.getCode();
            }
        }));
    }

    /**
     * Propagate event to handler
     * 
     * @param handler structure of handler class
     * @param wrapper the login data wrapper
     * @param inData login in data
     * @param outData login out data
     */
    protected void notifyHanlder(UserLoginHandlerClass handler, 
            ApiLogin wrapper, ISFSObject inData, ISFSObject outData) {
        Object instance = requestParamDeserializer()
                .deserialize(handler.getRequestListenerClass(), inData);
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                instance,
                context, wrapper);
        responseParamSerializer()
            .object2params(handler.getResponseHandlerClass(), instance, outData);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.USER_LOGIN;
    }
    
    /**
     * Check if has any handlers throw a BadRequestException
     * 
     * @param e exception
     * @return true or false
     */
    private boolean isBadRequestException(Exception e) {
        return ExceptionUtils.indexOfThrowable(e, BadRequestException.class) != -1;
    }
    
    /**
     * Get BadRequestException from the exception
     * 
     * @param ex the exception
     * @return BadRequestException
     */
    private BadRequestException getBadRequestException(Exception ex) {
        return (BadRequestException) ExceptionUtils
                .getThrowables(ex)[ExceptionUtils.indexOfThrowable(ex, BadRequestException.class)];
    }

}
