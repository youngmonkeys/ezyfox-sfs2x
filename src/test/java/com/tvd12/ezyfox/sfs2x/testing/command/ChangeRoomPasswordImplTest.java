package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.tvd12.ezyfox.sfs2x.command.impl.ChangeRoomPasswordImpl;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class ChangeRoomPasswordImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        ChangeRoomPasswordImpl command = new ChangeRoomPasswordImpl(context, api, extension);
        command.owner(user)
            .password("pass")
            .room(room)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws SFSRoomException {
        doThrow(SFSRoomException.class)
        .when(api)
        .changeRoomPassword(any(User.class), any(Room.class), any(String.class));
        ChangeRoomPasswordImpl command = new ChangeRoomPasswordImpl(context, api, extension);
        command.owner(user)
            .password("pass")
            .room(room)
            .execute();
        doNothing()
        .when(api)
        .changeRoomPassword(any(User.class), any(Room.class), any(String.class));
    }
    
}
