package com.tvd12.ezyfox.sfs2x.testing.utilities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.sfs2x.util.AgentUtil;
import com.tvd12.test.base.BaseTest;

public class AgentUtilTest extends BaseTest {

    @Test
    public void getUserAgentTestValidCase() {
        User sfsUser = mock(User.class);
        ExUser exUser = new ExUser();
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(exUser);
        assertEquals(AgentUtil.getUserAgent(sfsUser), exUser);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getUserAgentTestInvalidCase() {
        User sfsUser = mock(User.class);
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(null);
        AgentUtil.getUserAgent(sfsUser);
    }
    
    @Test
    public void getRoomAgentTestValidCase() {
        Room sfsRoom = mock(Room.class);
        ExRoom exRoom = new ExRoom();
        when(sfsRoom.getProperty(APIKey.ROOM)).thenReturn(exRoom);
        assertEquals(AgentUtil.getRoomAgent(sfsRoom), exRoom);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getRoomAgentTestInvalidCase() {
        Room sfsRoom = mock(Room.class);
        when(sfsRoom.getProperty(APIKey.ROOM)).thenReturn(null);
        AgentUtil.getRoomAgent(sfsRoom);
    }
    
    @Override
    public Class<?> getTestClass() {
        return AgentUtil.class;
    }
    
    public static class ExUser extends ApiUser {
        
    }
    
    public static class ExRoom extends ApiRoom {
        
    }
}
