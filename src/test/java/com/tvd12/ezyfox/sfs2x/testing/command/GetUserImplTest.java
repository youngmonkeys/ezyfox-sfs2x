package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.sfs2x.command.impl.FindUserImpl;

public class GetUserImplTest extends BaseCommandTest {

    @Test
    public void test() {
        User sfsUser = mock(User.class);
        when(api.getUserByName("123")).thenReturn(sfsUser);
        FindUserImpl get = new FindUserImpl(context, api, extension);
        get.by("123");
    }
    
}
