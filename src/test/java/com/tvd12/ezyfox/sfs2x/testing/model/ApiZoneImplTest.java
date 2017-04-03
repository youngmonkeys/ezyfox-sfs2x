package com.tvd12.ezyfox.sfs2x.testing.model;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiZoneImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class ApiZoneImplTest {

    @Test
    public void test() {
        Zone zone = mock(Zone.class);
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
        
        User sfsUser1 = mock(User.class);
        when(sfsUser1.containsProperty(APIKey.USER)).thenReturn(true);
        when(sfsUser1.getProperty(APIKey.USER)).thenReturn(new ApiUser() {});
        when(zone.getUserById(1)).thenReturn(sfsUser1);
        User sfsUser2 = mock(User.class);
        when(sfsUser2.getProperty(APIKey.USER)).thenReturn(null);
        when(zone.getUserById(2)).thenReturn(sfsUser2);
        when(zone.getUserById(3)).thenReturn(null);
        
        User sfsUser3 = mock(User.class);
        when(sfsUser3.containsProperty(APIKey.USER)).thenReturn(true);
        when(sfsUser3.getProperty(APIKey.USER)).thenReturn(new ApiUser() {});
        when(zone.getUserByName("1")).thenReturn(sfsUser3);
        User sfsUser5 = mock(User.class);
        when(sfsUser5.getProperty(APIKey.USER)).thenReturn(null);
        when(zone.getUserByName("2")).thenReturn(sfsUser5);
        when(zone.getUserByName("3")).thenReturn(null);
        
        when(zone.getUsersInGroup("1")).thenReturn(Lists.newArrayList(sfsUser1, sfsUser2));
        when(zone.getUserList()).thenReturn(Lists.newArrayList(sfsUser1, sfsUser2));
        
        User sfsUser6 = mock(User.class);
        when(sfsUser6.isNpc()).thenReturn(true);
        User sfsUser7 = mock(User.class);
        when(sfsUser7.isNpc()).thenReturn(false);
        when(zone.getUserByName("5")).thenReturn(sfsUser6);
        when(zone.getUserByName("6")).thenReturn(sfsUser7);
        
        Room sfsRoom1 = mock(Room.class);
        when(sfsRoom1.containsProperty(APIKey.ROOM)).thenReturn(true);
        when(sfsRoom1.getProperty(APIKey.ROOM)).thenReturn(new PokerRoom());
        Room sfsRoom2 = mock(Room.class);
        when(sfsRoom2.containsProperty(APIKey.ROOM)).thenReturn(false);
        when(zone.getRoomById(1)).thenReturn(sfsRoom1);
        when(zone.getRoomById(2)).thenReturn(sfsRoom2);
        when(zone.getRoomById(3)).thenReturn(null);
        
        Room sfsRoom3 = mock(Room.class);
        when(sfsRoom3.containsProperty(APIKey.ROOM)).thenReturn(true);
        when(sfsRoom3.getProperty(APIKey.ROOM)).thenReturn(new PokerRoom());
        Room sfsRoom5 = mock(Room.class);
        when(sfsRoom5.containsProperty(APIKey.ROOM)).thenReturn(false);
        when(zone.getRoomByName("1")).thenReturn(sfsRoom3);
        when(zone.getRoomByName("2")).thenReturn(sfsRoom5);
        when(zone.getRoomByName("3")).thenReturn(null);
        
        when(zone.getRoomListFromGroup("1")).thenReturn(
                Lists.newArrayList(sfsRoom1, sfsRoom2));
        when(zone.getRoomList()).thenReturn(
                Lists.newArrayList(sfsRoom1, sfsRoom2));
        
        ApiZoneImpl apiZone = new ApiZoneImpl(zone);
        assertEquals(apiZone.getId(), 1);
        assertEquals(apiZone.getMaxAllowedRooms(), 2);
        assertEquals(apiZone.getMaxAllowedUsers(), 3);
        assertEquals(apiZone.getMaxRoomNameChars(), 4);
        assertEquals(apiZone.getMaxRoomsCreatedPerUserLimit(), 5);
        assertEquals(apiZone.getMaxRoomVariablesAllowed(), 6);
        assertEquals(apiZone.getMaxUserIdleTime(), 7);
        assertEquals(apiZone.getMaxUserVariablesAllowed(), 8);
        assertEquals(apiZone.getMinRoomNameChars(), 9);
        assertEquals(apiZone.getTotalRoomCount(), 10);
        assertEquals(apiZone.getUserCount(), 11);
        assertEquals(apiZone.getUserReconnectionSeconds(), 12);
        assertEquals(apiZone.isActive(), true);
        assertEquals(apiZone.getName(), "world");
        assertEquals(apiZone.getProperty("a"), "b");
        assertTrue(apiZone.containsKey("a"));
        assertFalse(apiZone.containsKey("nonono"));
        
        assertNotNull(apiZone.getUserById(1));
        assertNull(apiZone.getUserById(2));
        assertNull(apiZone.getUserById(3));
        
        assertNotNull(apiZone.getUserByName("1"));
        assertNull(apiZone.getUserByName("2"));
        assertNull(apiZone.getUserByName("3"));
        
        assertEquals(apiZone.getUsersInGroup("1").size(), 1);
        assertEquals(apiZone.getUserList().size(), 1);
        
        assertTrue(apiZone.isNPC("5"));
        assertFalse(apiZone.isNPC("6"));
        assertFalse(apiZone.isNPC("8"));
        
        assertNotNull(apiZone.getRoomById(1));
        assertNull(apiZone.getRoomById(2));
        assertNull(apiZone.getRoomById(3));
        
        assertNotNull(apiZone.getRoomByName("1"));
        assertNull(apiZone.getRoomByName("2"));
        assertNull(apiZone.getRoomByName("3"));
        
        assertEquals(apiZone.getRoomsInGroup("1").size(), 1);
        assertEquals(apiZone.getRoomList().size(), 1);
        
        when(zone.getProperty(APIKey.ZONE)).thenReturn(apiZone);
        
        apiZone.setProperty(APIKey.ZONE, "abc");
        assertNotEquals(apiZone.getProperty(APIKey.ZONE), "abc");
        assertEquals(apiZone.getProperty(APIKey.ZONE, ApiZone.class), apiZone);
        apiZone.removeProperty(APIKey.ZONE);
        assertEquals(apiZone.getProperty(APIKey.ZONE, ApiZone.class), apiZone);
        
        apiZone.setProperty("hello", "world");
        when(zone.getProperty("hello")).thenReturn("world");
        assertEquals(apiZone.getProperty("hello"), "world");
        apiZone.removeProperty("hello");
    }
    
}
