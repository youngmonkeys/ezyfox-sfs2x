package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.lang.reflect.Method;
import java.util.List;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.RoomEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.RoomHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.util.AgentUtil;

public abstract class ServerRoomEventHandler extends ServerUserEventHandler {

    private List<RoomHandlerClass> handlers;
    
    public ServerRoomEventHandler(AppContextImpl context) {
        super(context);
    }
    
    @Override
    protected void init() {
        handlers = new RoomEventHandlerCenter().addHandlers(
                handlerClasses, 
                context.getRoomClasses(), 
                context.getUserClass(), context.getGameUserClasses());
    }

    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        Room sfsRoom = (Room) event.getParameter(SFSEventParam.ROOM);
        notifyHandlers(sfsRoom, sfsUser);
    }
    
    private void notifyHandlers(Room sfsRoom, User sfsUser) {
        ApiRoom apiRoom = AgentUtil.getRoomAgent(sfsRoom);
        ApiUser apiUser = AgentUtil.getUserAgent(sfsUser);
        for(RoomHandlerClass handler : handlers) {
            Object userAgent = checkUserAgent(handler, apiUser);
            if(!checkHandler(handler, apiRoom, userAgent))
                continue;
            Object instance = handler.newInstance();
            callHandleMethod(handler.getHandleMethod(), 
                    instance, apiRoom, userAgent);
        }
    }
    
    protected boolean checkHandler(RoomHandlerClass handler, 
            ApiRoom roomAgent, Object user) {
        return (handler.getRoomClass() == roomAgent.getClass()) &&
               (roomAgent.getName().startsWith(handler.getRoomName()));
    }
    
    private void callHandleMethod(Method method, Object instance, 
            Object roomAgent, Object userAgent) {
        ReflectMethodUtil.invokeHandleMethod(method, 
                    instance, context, roomAgent, userAgent);
    }

}
