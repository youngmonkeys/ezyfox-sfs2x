package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.bitswarm.sessions.ISession;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSUser;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.sfs2x.command.impl.UserInfoImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

/**
 * @author tavandung12
 * Created on Jun 9, 2016
 *
 */
public class UserInfoCommandImpleTest extends BaseCommandTest2 {

    @Test
    public void test() {
        UserInfoImpl c = new UserInfoImpl(context, api, extension);
        ISession session = mock(ISession.class);
        User user = new SFSUser("user", session);
        when(api.getUserByName("user")).thenReturn(user);
        c.user(1).user(new AppUser()).user("user");
        c.setBadWordsWarnings(10);
        assertEquals(c.getBadWordsWarnings(), 10);
        c.setBeingKicked(true);
        assertEquals(c.isBeingKicked(), true);
        c.setConnected(true);
        assertEquals(c.isConnected(), true);
        c.setFloodWarnings(11);
        assertEquals(c.getFloodWarnings(), 11);
        c.setJoining(true);
        assertEquals(c.isJoining(), true);
        c.setLastLoginTime(12345);
        assertEquals(c.getLoginTime(), 12345);
        when(session.getLastLoggedInActivityTime()).thenReturn((long)123456);
        c.setLastRequestTime(123456);
        assertEquals(c.getLastRequestTime(), (long)123456);
        c.setMaxAllowedVariables(3);
        assertEquals(c.getMaxAllowedVariables(), 3);
        user = spy(user);
        when(user.getProperty(APIKey.USER)).thenReturn(this.user);
        when(user.getLastJoinedRoom()).thenReturn(sfsRoom);
        when(sfsRoom.getId()).thenReturn(new Integer(1));
        when(api.getUserByName("user")).thenReturn(user);
        c.user("user");
        c.setPlayerId(2);
        assertEquals(c.getPlayerId(), 2);
        c.setPlayerId(5, room);
        assertEquals(c.getPlayerId(room), 5);
        when(user.getLastJoinedRoom()).thenReturn(sfsRoom);
        assertEquals(c.getPlayerId(), 5);
        c.setPrivilegeId((short)1);
        assertEquals(c.getPrivilegeId(), (short)1);
        c.setReconnectionSeconds(3);
        when(session.getReconnectionSeconds()).thenReturn(3);
        assertEquals(c.getReconnectionSeconds(), 3);
        user = mock(User.class);
        when(api.getUserByName("user")).thenReturn(user);
        c.user(1).user(new AppUser()).user("user");
        when(user.isSpectator()).thenReturn(true);
        assertEquals(c.isSpectator(), true);
        when(user.isSpectator(sfsRoom)).thenReturn(true);
        assertEquals(c.isSpectator(room), true);
        when(user.isSubscribedToGroup("1")).thenReturn(true);
        assertEquals(c.isSubscribedToGroup("1"), true);
        c.subscribeGroup("1");
        c.unsubscribeGroup("1");
        c.removeCreatedRoom(room);
        c.removeJoinedRoom(room);
        assertEquals(c.getCreatedRooms().size(), 0);
        when(user.getCreatedRooms()).thenReturn(Lists.newArrayList(sfsRoom));
        assertEquals(c.getCreatedRooms().size(), 1);
        assertEquals(c.getJoinedRooms().size(), 0);
        when(user.getJoinedRooms()).thenReturn(Lists.newArrayList(sfsRoom));
        assertEquals(c.getJoinedRooms().size(), 1);
        when(user.isPlayer()).thenReturn(true);
        assertEquals(c.isPlayer(), true);
        when(user.getLastJoinedRoom()).thenReturn(sfsRoom);
        c.getLastJoinedRoom();
        c.getPlayerId();
        c.isJoinedInRoom(room);
        
        c.updateLastRequestTime();
        c.addCreatedRoom(room);
        c.addJoinedRoom(room);
        
        when(user.getVariablesCount()).thenReturn(3);
        assertEquals(c.getVariablesCount(), 3);
        when(user.getSubscribedGroups()).thenReturn(Lists.newArrayList("1", "2"));
        assertEquals(c.getSubscribedGroups().size(), 2);
        when(user.getOwnedRoomsCount()).thenReturn(5);
        assertEquals(c.getOwnedRoomsCount(), 5);
        when(user.isSuperUser()).thenReturn(true);
        assertEquals(c.isSuperUser(), true);
        when(user.isNpc()).thenReturn(true);
        assertEquals(c.isNPC(), true);
        when(user.isLocal()).thenReturn(true);
        assertEquals(c.isLocal(), true);
        when(user.getZone()).thenReturn(zone);
        c.getZone();
        when(user.isPlayer(sfsRoom)).thenReturn(true);
        assertEquals(c.isPlayer(room), true);
        Map<Room, Integer> m = new HashMap<Room, Integer>();
        m.put(sfsRoom, 1);
        when(user.getPlayerIds()).thenReturn(m);
        c.getPlayerIds();
        UserVariable var = new SFSUserVariable("abc", 1D);
        when(user.getVariables()).thenReturn(Lists.newArrayList(var));
        c.removeAllVariables();
        
    }
    
}
