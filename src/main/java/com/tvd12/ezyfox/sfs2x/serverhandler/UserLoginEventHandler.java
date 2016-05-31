package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

public class UserLoginEventHandler extends ServerBaseEventHandler {

    public UserLoginEventHandler(AppContextImpl context) {
        super(context);
    }
    
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addListeners(handlerClasses, String.class, String.class);
    }

    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        String name = (String) event.getParameter(SFSEventParam.LOGIN_NAME);
        String pass = (String) event.getParameter(SFSEventParam.LOGIN_PASSWORD);
        notifyHandler(name, pass);
    }

    protected void notifyHandler(String username, String password) {
        for (ServerHandlerClass handler : handlers) {
            notifyHanlder(handler, username, password);
        }
    }

    protected void notifyHanlder(ServerHandlerClass handler, 
            String username, String password) {
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                handler.newInstance(),
                context, username, password);
    }

    @Override
    public String eventName() {
        return ServerEvent.USER_LOGIN;
    }

}
