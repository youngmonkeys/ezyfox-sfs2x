/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.serverhandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.util.ClientDisconnectionReason;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.entities.ApiDisconnection;
import com.tvd12.ezyfox.core.entities.ApiDisconnectionImpl;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.command.impl.CommandUtil;
import com.tvd12.ezyfox.sfs2x.constants.Constants;

/**
 * This handler handles user disconnect from server event
 * 
 * @author tavandung12
 *
 */
public class UserDisconnectEventHandler extends UserActionEventHandler {

    /**
     * @param context the context
     */
    public UserDisconnectEventHandler(BaseAppContext context) {
        super(context);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.ServerUserEventHandler#init()
     */
    @Override
    protected void init() {
        handlers = new ServerEventHandlerCenter()
                .addHandlers(handlerClasses, ApiDisconnection.class);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserActionEventHandler#handleServerEvent(com.smartfoxserver.v2.core.ISFSEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException {
        Zone sfsZone = (Zone) event.getParameter(SFSEventParam.ZONE);
        User sfsUser = (User) event.getParameter(SFSEventParam.USER);
        List<Room> sfsRooms = (List<Room>)event.getParameter(SFSEventParam.JOINED_ROOMS);
        Map<Room, Integer> sfsIds = (Map<Room, Integer>)event.getParameter(SFSEventParam.PLAYER_IDS_BY_ROOM);
        ClientDisconnectionReason sfsReason = (ClientDisconnectionReason)event.getParameter(SFSEventParam.DISCONNECTION_REASON);
        ApiUser apiUser = (ApiUser) sfsUser.getProperty(APIKey.USER);
        List<ApiRoom> apiRooms = CommandUtil.getApiRoomList(sfsRooms);
        apiUser.setProperty(APIKey.USER_JOOMS_JOINED, apiRooms);
        apiUser.setProperty(Constants.USER_ROOMS_JOINED, sfsRooms);
        ApiDisconnectionImpl apiDisconnection = new ApiDisconnectionImpl();
        apiDisconnection.setZone((ApiZone) sfsZone.getProperty(APIKey.ZONE));
        apiDisconnection.setUser(apiUser);
        apiDisconnection.setJoinedRooms(apiRooms);
        apiDisconnection.setPlayerIdsByRoom(convertPlayerIdsByRoom(sfsIds));
        apiDisconnection.setReason(sfsReason.toString());
        notifyHandlers(apiUser, apiDisconnection);
        detachUserData(sfsUser);
    }
    
    /**
     * Propagate event to handlers
     * 
     * @param apiUser user agent object
     */
    protected void notifyHandlers(ApiUser apiUser, ApiDisconnectionImpl disconnection) {
        for(ServerHandlerClass handler : handlers) {
            ReflectMethodUtil.invokeHandleMethod(
                    handler.getHandleMethod(), 
                    handler.newInstance(), 
                    context, 
                    disconnection);
        }
    }
    
    /**
     * detach api user from sfs user
     * 
     * @param sfsUser the sfs user
     */
    protected void detachUserData(User sfsUser) {
    	sfsUser.removeProperty(APIKey.USER);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.UserLogoutEventHandler#eventName()
     */
    @Override
    public String eventName() {
        return ServerEvent.USER_DISCONNECT;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Map convertPlayerIdsByRoom(Map<Room, Integer> sfsIds) {
        Map answer = new HashMap<>();
        for(Entry<Room, Integer> entry : sfsIds.entrySet()) {
            if(entry.getKey().containsProperty(APIKey.ROOM))
                answer.put(entry.getKey().getProperty(APIKey.ROOM), entry.getValue());
        }
        return answer;
    }

}
