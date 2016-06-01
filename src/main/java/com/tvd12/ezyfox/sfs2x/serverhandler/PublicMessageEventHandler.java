package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.model.ApiMessage;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiMessageImpl;

/**
 * Support to handle public message event
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public class PublicMessageEventHandler extends MessageEventHandler {

    /**
     * @param context
     */
    public PublicMessageEventHandler(AppContextImpl context) {
        super(context);
    }

    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerBaseEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addHandlers(handlerClasses, ApiMessage.class);
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.IServerEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        Room sfsRoom = (Room) event.getParameter(SFSEventParam.ROOM);
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        String message = (String)event.getParameter(SFSEventParam.MESSAGE);
        ISFSObject params = (ISFSObject)event.getParameter(SFSEventParam.OBJECT);
        ApiMessageImpl messageObject = new ApiMessageImpl();
        messageObject.setContent(message);
        messageObject.setZone((ApiZone)sfsZone.getProperty(APIKey.ZONE));
        messageObject.setRoom((ApiRoom)sfsRoom.getProperty(APIKey.ROOM));
        messageObject.setSender((ApiUser)sfsUser.getProperty(APIKey.USER));
        notifyToHandlers(messageObject, params);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.PUBLIC_MESSAGE;
    }
}
