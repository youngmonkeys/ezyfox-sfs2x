/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiZoneImpl;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 *
 */
public class BaseCommandTest2 extends BaseCommandTest {

    protected User sfsUser;
    protected Room sfsRoom;
    protected Zone zone;
    protected ApiBaseUser user;
    protected ApiRoom room;
    protected ApiZoneImpl apiZone;
    
    protected static final String USER_NAME = "user";
    protected static final String ROOM_NAME = "room";
    
    public BaseCommandTest2() {
        super();
        sfsUser = mock(User.class);
        when(sfsUser.getName()).thenReturn(USER_NAME);
        sfsRoom = mock(Room.class);
        when(sfsRoom.getName()).thenReturn(ROOM_NAME);
        
        user = new ExUser();
        room = new ExRoom();
        
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(user);
        when(sfsRoom.getProperty(APIKey.ROOM)).thenReturn(room);
        
        when(api.getUserByName(USER_NAME)).thenReturn(sfsUser);
        
        zone = mock(Zone.class);
        when(zone.getId()).thenReturn(1);
        when(zone.getMaxAllowedRooms()).thenReturn(2);
        when(zone.getMaxAllowedUsers()).thenReturn(3);
        when(zone.getMaxRoomNameChars()).thenReturn(4);
        when(zone.getMaxRoomsCreatedPerUserLimit()).thenReturn(5);
        when(zone.getMaxRoomVariablesAllowed()).thenReturn(6);
        when(zone.getMaxUserIdleTime()).thenReturn(7);
        when(zone.getMaxUserVariablesAllowed()).thenReturn(8);
        when(zone.getMinRoomNameChars()).thenReturn(9);
        when(zone.getTotalRoomCount()).thenReturn(10);
        when(zone.getUserCount()).thenReturn(11);
        when(zone.getUserReconnectionSeconds()).thenReturn(12);
        when(zone.isActive()).thenReturn(true);
        when(zone.getName()).thenReturn("world");
        when(zone.getProperty("a")).thenReturn("b");
        
        apiZone = new ApiZoneImpl(zone);
        when(zone.getProperty(APIKey.ZONE)).thenReturn(apiZone);
        when(extension.getParentZone()).thenReturn(zone);
        when(zone.getRoomByName(ROOM_NAME)).thenReturn(sfsRoom);
        
    }
    
    public static class ExUser extends ApiUser {
        /* (non-Javadoc)
         * @see com.lagente.core.model.ApiUser#getName()
         */
        @Override
        public String getName() {
            return "user";
        }
    }
    
    public static class ExRoom extends ApiRoom {
        /* (non-Javadoc)
         * @see com.lagente.core.model.ApiRoom#getName()
         */
        @Override
        public String getName() {
            return "room";
        }
    }
}
