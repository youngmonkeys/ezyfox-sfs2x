package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.sfs2x.command.impl.RoomInfoImpl;

/**
 * @author tavandung12
 * Created on Jun 30, 2016
 *
 */
public class RoomInfoImplTest extends BaseCommandTest2 {

    private Room room1;
    private ExRoom exRoom;
    
    /**
     * 
     */
    public RoomInfoImplTest() {
        super();
        room1 = mock(Room.class);
        when(zone.getRoomByName("abc")).thenReturn(room1);
        exRoom = new ExRoom();
        exRoom.setName("abc");
    }
    
    @Test
    public void test() {
        
        RoomVariable var = new SFSRoomVariable("abc", 1D);
        when(sfsRoom.getVariables()).thenReturn(Lists.newArrayList(var));
        
        RoomInfoImpl command = new RoomInfoImpl(context, api, extension);
        command.room(room);
        command.addUser(user);
        command.addUser(user, true);
        command.removeUser(user);
        command.removeVariable(USER_NAME);
        command.setActive(true);
        assertTrue(room.isActive());
        command.setCapacity(5, 6);
        assertEquals(room.getMaxUsers(), 5);
        assertEquals(room.getMaxSpectators(), 6);
        command.setDynamic(false);
        assertEquals(room.isDynamic(), false);
        command.setGame(false);
        assertFalse(room.isGame());
        command.setGroupId("abc");
        assertEquals(room.getGroupdId(), "abc");
        command.setHidden(true);
        assertEquals(room.isHidden(), true);
        command.setMaxRoomVariablesAllowed(8);
        assertEquals(room.getMaxRoomVariablesAllowed(), 8);
        command.setMaxSpectators(11);
        assertEquals(room.getMaxSpectators(), 11);
        command.setMaxUsers(12);
        assertEquals(room.getMaxUsers(), 12);
        command.setName("zzz");
        assertEquals(room.getName(), "zzz");
        room.setName(ROOM_NAME);
        command.setOwner(user);
        assertEquals(room.getOwner(), user);
        command.setPassword("123");
        assertEquals(room.getPassword(), "123");
        command.setUseWordsFilter(false);
        assertEquals(room.isUseWordsFilter(), false);
        command.removeAllVariables();
        command.switchPlayerToSpectator(user);
        command.switchSpectatorToPlayer(user);
        command.destroy();
        
        User sfsUser1 = mock(User.class);
        when(sfsUser1.getName()).thenReturn("abc");
        
        when(sfsRoom.getZone()).thenReturn(zone);
        when(sfsRoom.containsUser(USER_NAME)).thenReturn(true);
        when(sfsRoom.containsVariable("1")).thenReturn(true);
        when(sfsRoom.getSpectatorsList()).thenReturn(Lists.newArrayList(sfsUser));
        when(sfsRoom.getPlayersList()).thenReturn(Lists.newArrayList(sfsUser, sfsUser));
        when(sfsRoom.getUserList()).thenReturn(Lists.newArrayList(sfsUser, sfsUser, sfsUser1));
        when(sfsRoom.getLifeTime()).thenReturn(10L);
        when(sfsRoom.getVariablesCount()).thenReturn(9);
        when(sfsRoom.isEmpty()).thenReturn(Boolean.TRUE);
        when(sfsRoom.isFull()).thenReturn(Boolean.TRUE);
        when(sfsUser.containsProperty(APIKey.USER)).thenReturn(Boolean.TRUE);
        when(sfsRoom.isPasswordProtected()).thenReturn(true);
        assertEquals(command.getZone(), apiZone);
        assertEquals(command.containsUser(user), true);
        assertEquals(command.containsUser(USER_NAME), true);
        assertEquals(command.containsVariable("1"), true);
        assertEquals(command.getSpectatorsList().size(), 1);
        assertEquals(command.getPlayersList().size(), 2);
        assertEquals(command.getUserList().size(), 2);
        assertEquals(command.getLifeTime(), 10L);
        assertEquals(command.getVariablesCount(), 9);
        assertEquals(command.isEmpty(), true);
        assertEquals(command.isFull(), true);
        assertEquals(command.isPasswordProtected(), true);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws SFSJoinRoomException {
        RoomInfoImpl command = new RoomInfoImpl(context, api, extension);
        doThrow(SFSJoinRoomException.class).when(room1).addUser(any(User.class));
        command.room(exRoom).addUser(user);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test3() throws SFSJoinRoomException {
        RoomInfoImpl command = new RoomInfoImpl(context, api, extension);
        doThrow(SFSJoinRoomException.class).when(room1).addUser(any(User.class), any(boolean.class));
        command.room(exRoom).addUser(user, true);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test4() throws SFSRoomException {
        RoomInfoImpl command = new RoomInfoImpl(context, api, extension);
        doThrow(SFSRoomException.class).when(room1).switchPlayerToSpectator(any(User.class));
        command.room(exRoom).switchPlayerToSpectator(user);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test5() throws SFSRoomException {
        RoomInfoImpl command = new RoomInfoImpl(context, api, extension);
        doThrow(SFSRoomException.class).when(room1).switchSpectatorToPlayer(any(User.class));
        command.room(exRoom).switchSpectatorToPlayer(user);
    }
    
}
