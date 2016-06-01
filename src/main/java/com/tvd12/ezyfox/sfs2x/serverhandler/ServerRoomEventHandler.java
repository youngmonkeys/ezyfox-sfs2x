package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.lang.reflect.Method;
import java.util.List;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.config.RoomEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.RoomHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.util.AgentUtil;

/**
 * Support to handle event related to room
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public abstract class ServerRoomEventHandler extends ServerUserEventHandler {

    // list of structures of handler classes
    private List<RoomHandlerClass> handlers;
    
    /**
     * @param context
     */
    public ServerRoomEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /**
     * @see ServerUserEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new RoomEventHandlerCenter().addHandlers(
                handlerClasses, 
                context.getRoomClasses(), 
                context.getUserClass(), context.getGameUserClasses());
    }

    /**
     * @see BaseServerEventHandler#handleServerEvent(ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        Room sfsRoom = (Room) event.getParameter(SFSEventParam.ROOM);
        notifyHandlers(sfsRoom, sfsUser);
    }
    
    /**
     * Propagate event to handlers
     * 
     * @param sfsRoom smartfox room object
     * @param sfsUser smartfox user object
     */
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
    
    /**
     * Validate the handler
     * 
     * @param handler structure of handler class
     * @param roomAgent room agent object
     * @param user user agent object
     * @return true or false
     */
    protected boolean checkHandler(RoomHandlerClass handler, 
            ApiRoom roomAgent, Object user) {
        return (handler.getRoomClass() == roomAgent.getClass()) &&
               (roomAgent.getName().startsWith(handler.getRoomName()));
    }
    
    /**
     * Invoke handle method
     * 
     * @param method handle method
     * @param instance object to invoke method
     * @param roomAgent room agent object
     * @param userAgent user agent object
     */
    private void callHandleMethod(Method method, Object instance, 
            Object roomAgent, Object userAgent) {
        ReflectMethodUtil.invokeHandleMethod(method, 
                    instance, context, roomAgent, userAgent);
    }

}
