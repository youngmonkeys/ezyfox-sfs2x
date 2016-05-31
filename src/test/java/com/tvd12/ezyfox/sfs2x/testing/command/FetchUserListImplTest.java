package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.sfs2x.command.impl.FetchUserListImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FetchUserListImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        when(sfsUser.containsProperty(APIKey.USER)).thenReturn(true);
        User user1 = mock(User.class);
        when(user1.containsProperty(APIKey.USER)).thenReturn(false);
        when(sfsRoom.getUserList()).thenReturn(Lists.newArrayList(sfsUser, user1));
        new FetchUserListImpl(context, api, extension).in(room);
    }
    
    @Test
    public void test2() {
        User user1 = mock(User.class);
        when(user1.containsProperty(APIKey.USER)).thenReturn(false);
        when(sfsRoom.getUserList()).thenReturn(Lists.newArrayList(sfsUser, user1));
        new FetchUserListImpl(context, api, extension).in(new PokerRoom());
    }
    
}
