package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.sfs2x.command.impl.FetchUserListImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import java.util.List;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FetchUserListImplTest extends BaseCommandTest2 {

    @Test(priority = 1)
    public void test() {
        when(sfsUser.containsProperty(APIKey.USER)).thenReturn(true);
        User user1 = mock(User.class);
        when(user1.containsProperty(APIKey.USER)).thenReturn(false);
        when(sfsRoom.getUserList()).thenReturn(Lists.newArrayList(sfsUser, user1));
        new FetchUserListImpl(context, api, extension).in(room);
    }
    
    @Test(priority = 2, expectedExceptions = {IllegalStateException.class})
    public void test2() {
        User user1 = mock(User.class);
        when(user1.containsProperty(APIKey.USER)).thenReturn(false);
        when(sfsRoom.getUserList()).thenReturn(Lists.newArrayList(sfsUser, user1));
        new FetchUserListImpl(context, api, extension).in(new PokerRoom());
    }
    
    @Test(priority = 3)
    public void test3() {
        ((ApiUser)user).addChild(new VideoPokerUser());
        when(sfsUser.containsProperty(APIKey.USER)).thenReturn(true);
        User user1 = mock(User.class);
        when(user1.containsProperty(APIKey.USER)).thenReturn(false);
        when(sfsRoom.getUserList()).thenReturn(Lists.newArrayList(sfsUser, user1));
        List<VideoPokerUser> users = new FetchUserListImpl(
                context, api, extension).in(room, VideoPokerUser.class);
        assertEquals(users.size(), 1);
    }
    
    public static class VideoPokerUser extends ApiGameUser {
        
    }
}
