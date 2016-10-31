package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.ISFSEventParam;
import com.smartfoxserver.v2.core.SFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;

/**
 * Support to handle server initializing event
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public class RoomExtensionDestroyEventHandler extends ServerEventHandler {

    // list of structures of handler classes
	protected List<ServerHandlerClass> handlers;

	/**
	 * @param context the context
	 */
	public RoomExtensionDestroyEventHandler(BaseAppContext context) {
		super(context);
	}
	
	/**
	 * @see ServerEventHandler#init()
	 */
	@Override
	protected void init() {
	    handlers = new ServerEventHandlerCenter()
                .addHandlers(handlerClasses, ApiRoom.class);
	}
	
	/**
	 * Handle event
	 */
	public void handle(Room room) {
	    handleServerEvent(new SFSEvent(null, createParams(room)));
	}
	
	/**
     * @see BaseServerEventHandler#handleServerEvent(ISFSEvent)
     */
	@Override
	public void handleServerEvent(ISFSEvent params) {
	    Room zone = (Room) params.getParameter(SFSEventParam.ROOM);
	    notifyHandlers(zone.getProperty(APIKey.ROOM));
	}
	
	/**
	 * Propagate event to handlers
	 */
	protected void notifyHandlers(Object zoneAgent) {
	    for(ServerHandlerClass handler : handlers) {
	        notifyHandler(handler, zoneAgent);
	    }
	}
	
	/**
	 * Propagate event to handler
	 * 
	 * @param handler structure of handler class
	 */
	protected void notifyHandler(ServerHandlerClass handler, Object zoneAgent) {
	    ReflectMethodUtil.invokeHandleMethod(handler.getHandleMethod(), 
	            handler.newInstance(), context, zoneAgent);
	}
	
	/**
	 * @see ServerEventHandler#eventName()
	 */
	@Override
	public String eventName() {
		return ServerEvent.ROOM_EXTENSION_DESTROY; 
	}
	
	private Map<ISFSEventParam, Object> createParams(Room room) {
        Map<ISFSEventParam, Object> answer = new HashMap<>();
        answer.put(SFSEventParam.ROOM, room);
        return answer;
    }
	
}
