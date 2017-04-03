package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.ISFSEventListener;
import com.smartfoxserver.v2.core.ISFSEventParam;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.ExtensionLevel;
import com.smartfoxserver.v2.extensions.ExtensionReloadMode;
import com.smartfoxserver.v2.extensions.ExtensionType;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.smartfoxserver.v2.util.ClientDisconnectionReason;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.serverhandler.InternalEventHandler;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserDisconnectEventHandler;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

/**
 * @author tavandung12
 * Created on Jun 30, 2016
 *
 */
public class UserDisconnectEventHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() throws SFSException {
        UserDisconnectEventHandler handler = new UserDisconnectEventHandler(context);
        Room room = new SFSRoom("abc");
        Room room1 = new SFSRoom("xyz");
        room.setProperty(APIKey.ROOM, new ApiRoom() {});
        Map<Room, Integer> ids = new HashMap<>();
        ids.put(room, 1);
        ids.put(room1, 2);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        when(event.getParameter(SFSEventParam.JOINED_ROOMS)).thenReturn(Lists.newArrayList(room, room1));
        when(event.getParameter(SFSEventParam.PLAYER_IDS_BY_ROOM)).thenReturn(ids);
        when(event.getParameter(SFSEventParam.DISCONNECTION_REASON)).thenReturn(ClientDisconnectionReason.BAN);
        handler.handleServerEvent(event);
    }
    
    @Test
    public void test1() throws SFSException {
        UserDisconnectEventHandler handler = new UserDisconnectEventHandler(context);
        Room room = newRoomWithExtension0("abc");
        Room room1 = newRoomWithExtension("xyz");
        room.setProperty(APIKey.ROOM, new ApiRoom() {});
        Map<Room, Integer> ids = new HashMap<>();
        ids.put(room, 1);
        ids.put(room1, 2);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        when(event.getParameter(SFSEventParam.JOINED_ROOMS)).thenReturn(Lists.newArrayList(room, room1));
        when(event.getParameter(SFSEventParam.PLAYER_IDS_BY_ROOM)).thenReturn(ids);
        when(event.getParameter(SFSEventParam.DISCONNECTION_REASON)).thenReturn(ClientDisconnectionReason.BAN);
        handler.handleServerEvent(event);
    }
    
    private Room newRoomWithExtension(final String name) {
    	return new SFSRoom(name) {
    		/* (non-Javadoc)
    		 * @see com.smartfoxserver.v2.entities.SFSRoom#getExtension()
    		 */
    		@Override
    		public ISFSExtension getExtension() {
    			return new RoomExt();
    		}
    	};
    }
    
    private Room newRoomWithExtension0(final String name) {
    	return new SFSRoom(name) {
    		/* (non-Javadoc)
    		 * @see com.smartfoxserver.v2.entities.SFSRoom#getExtension()
    		 */
    		@Override
    		public ISFSExtension getExtension() {
    			return new RoomExt0();
    		}
    	};
    }
    
    
    
    private static class RoomExt extends RoomExt0 implements InternalEventHandler {

		/* (non-Javadoc)
		 * @see com.tvd12.ezyfox.sfs2x.serverhandler.InternalEventHandler#handleEvent(java.lang.String, java.util.Map)
		 */
		@Override
		public void handleEvent(String event, Map<ISFSEventParam, Object> params) {
			// TODO Auto-generated method stub
			
		}

		
    	
    }
    
    private static class RoomExt0 implements ISFSExtension {

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#addEventListener(com.smartfoxserver.v2.core.SFSEventType, com.smartfoxserver.v2.core.ISFSEventListener)
		 */
		@Override
		public void addEventListener(SFSEventType arg0, ISFSEventListener arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#destroy()
		 */
		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getConfigProperties()
		 */
		@Override
		public Properties getConfigProperties() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getExtensionFileName()
		 */
		@Override
		public String getExtensionFileName() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getLevel()
		 */
		@Override
		public ExtensionLevel getLevel() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getName()
		 */
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getParentRoom()
		 */
		@Override
		public Room getParentRoom() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getParentZone()
		 */
		@Override
		public Zone getParentZone() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getPropertiesFileName()
		 */
		@Override
		public String getPropertiesFileName() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getReloadMode()
		 */
		@Override
		public ExtensionReloadMode getReloadMode() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#getType()
		 */
		@Override
		public ExtensionType getType() {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#handleClientRequest(java.lang.String, com.smartfoxserver.v2.entities.User, com.smartfoxserver.v2.entities.data.ISFSObject)
		 */
		@Override
		public void handleClientRequest(String arg0, User arg1, ISFSObject arg2) throws SFSException {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#handleInternalMessage(java.lang.String, java.lang.Object)
		 */
		@Override
		public Object handleInternalMessage(String arg0, Object arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#init()
		 */
		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#isActive()
		 */
		@Override
		public boolean isActive() {
			// TODO Auto-generated method stub
			return false;
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#removeEventListener(com.smartfoxserver.v2.core.SFSEventType, com.smartfoxserver.v2.core.ISFSEventListener)
		 */
		@Override
		public void removeEventListener(SFSEventType arg0, ISFSEventListener arg1) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#send(java.lang.String, com.smartfoxserver.v2.entities.data.ISFSObject, com.smartfoxserver.v2.entities.User)
		 */
		@Override
		public void send(String arg0, ISFSObject arg1, User arg2) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#send(java.lang.String, com.smartfoxserver.v2.entities.data.ISFSObject, java.util.List)
		 */
		@Override
		public void send(String arg0, ISFSObject arg1, List<User> arg2) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#send(java.lang.String, com.smartfoxserver.v2.entities.data.ISFSObject, com.smartfoxserver.v2.entities.User, boolean)
		 */
		@Override
		public void send(String arg0, ISFSObject arg1, User arg2, boolean arg3) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#send(java.lang.String, com.smartfoxserver.v2.entities.data.ISFSObject, java.util.List, boolean)
		 */
		@Override
		public void send(String arg0, ISFSObject arg1, List<User> arg2, boolean arg3) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setActive(boolean)
		 */
		@Override
		public void setActive(boolean arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setExtensionFileName(java.lang.String)
		 */
		@Override
		public void setExtensionFileName(String arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setLevel(com.smartfoxserver.v2.extensions.ExtensionLevel)
		 */
		@Override
		public void setLevel(ExtensionLevel arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setName(java.lang.String)
		 */
		@Override
		public void setName(String arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setParentRoom(com.smartfoxserver.v2.entities.Room)
		 */
		@Override
		public void setParentRoom(Room arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setParentZone(com.smartfoxserver.v2.entities.Zone)
		 */
		@Override
		public void setParentZone(Zone arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setPropertiesFileName(java.lang.String)
		 */
		@Override
		public void setPropertiesFileName(String arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setReloadMode(com.smartfoxserver.v2.extensions.ExtensionReloadMode)
		 */
		@Override
		public void setReloadMode(ExtensionReloadMode arg0) {
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see com.smartfoxserver.v2.extensions.ISFSExtension#setType(com.smartfoxserver.v2.extensions.ExtensionType)
		 */
		@Override
		public void setType(ExtensionType arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
}
