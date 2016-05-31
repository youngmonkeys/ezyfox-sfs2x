package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.FetchUserImpl;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FetchUserImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        when(sfsRoom.getUserByName(USER_NAME)).thenReturn(sfsUser);
        FetchUserImpl command = new FetchUserImpl(context, api, extension);
        command.room(room)
            .userId(1)
            .username(USER_NAME)
            .execute();
    }
    
    @Test
    public void test1() {
        FetchUserImpl command = new FetchUserImpl(context, api, extension);
        when(sfsRoom.getUserById(1)).thenReturn(null);
        command.room(room)
            .userId(1)
            .username(null)
            .execute();
    }
    
}
