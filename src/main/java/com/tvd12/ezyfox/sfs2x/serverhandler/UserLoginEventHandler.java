package com.tvd12.ezyfox.sfs2x.serverhandler;

import static com.tvd12.ezyfox.sfs2x.serializer.RequestParamDeserializer.requestParamDeserializer;
import static com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer.responseParamSerializer;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.IErrorCode;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.UserLoginEventHandlerCenter;
import com.tvd12.ezyfox.core.exception.BadRequestException;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.UserLoginHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

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
     * @param context
     */
    public UserLoginEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        loginHandlers = new UserLoginEventHandlerCenter()
                .addHandlers(handlerClasses, String.class, String.class);
    }

    /*
     * (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        String name = (String) event.getParameter(SFSEventParam.LOGIN_NAME);
        String pass = (String) event.getParameter(SFSEventParam.LOGIN_PASSWORD);
        ISFSObject inData = (ISFSObject)event.getParameter(SFSEventParam.LOGIN_IN_DATA);
        ISFSObject outData = (ISFSObject)event.getParameter(SFSEventParam.LOGIN_OUT_DATA);
        notifyHandler(name, pass, inData, outData);
    }

    /**
     * Propagate event to handler
     * 
     * @param username user name
     * @param password user's password
     * @param inData login in data
     * @param outData login out data
     * @throws SFSLoginException when has any errors
     */
    protected void notifyHandler(String username, String password, 
            ISFSObject inData, ISFSObject outData) throws SFSLoginException {
        for (UserLoginHandlerClass handler : loginHandlers) {
            try {
                notifyHanlder(handler, username, password, inData, outData);
            } catch(Exception e) {
                if(!isBadRequestException(e)) 
                    throw new SFSLoginException();
                final BadRequestException ex = getBadRequestException(e);
                throw new SFSLoginException(ex.getMessage(), new SFSErrorData(new IErrorCode() {
                    @Override
                    public short getId() {
                        return (short)ex.getCode();
                    }
                }));
            }
        }
    }

    /**
     * Propagate event to handler
     * 
     * @param handler structure of handler class
     * @param username user name
     * @param password password
     * @param inData login in data
     * @param outData login out data
     */
    protected void notifyHanlder(UserLoginHandlerClass handler, 
            String username, String password, ISFSObject inData, ISFSObject outData) {
        Object instance = requestParamDeserializer()
                .deserialize(handler.getRequestListenerClass(), inData);
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                instance,
                context, username, password);
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
