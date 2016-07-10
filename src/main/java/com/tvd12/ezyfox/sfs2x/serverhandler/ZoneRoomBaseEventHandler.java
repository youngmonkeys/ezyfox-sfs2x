package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.ZoneRoomHandlerCenter;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;

/**
 * @author tavandung12
 * Created on Jul 3, 2016
 *
 */
public abstract class ZoneRoomBaseEventHandler extends ServerBaseEventHandler {

    /**
     * @param context
     */
    public ZoneRoomBaseEventHandler(AppContextImpl context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        Class<?>[] roomClasses = context.getRoomClasses()
                .toArray(new Class[context.getRoomClasses().size()]);
        handlers = new ZoneRoomHandlerCenter()
                .addHandlers(handlerClasses, roomClasses);
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        Room sfsRoom = (Room) event.getParameter(SFSEventParam.ROOM);
        notifyHandlers((ApiZone)sfsZone.getProperty(APIKey.ZONE), 
                (ApiRoom)sfsRoom.getProperty(APIKey.ROOM));
    }
    
    protected void notifyHandlers(ApiZone zone, ApiRoom room) {
        for(ServerHandlerClass handler : handlers) {
            notifyHandler(handler, zone, room);
        }
    }
    
    protected void notifyHandler(ServerHandlerClass handler, 
            ApiZone zone, ApiRoom room) {
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                handler.newInstance(), 
                context, zone, room);
    }

}
