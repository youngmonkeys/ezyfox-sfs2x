package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.tvd12.ezyfox.sfs2x.command.impl.ChangeRoomNameImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class ChangeRoomNameImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        ChangeRoomNameImpl command = new ChangeRoomNameImpl(context, api, extension);
        command.owner(user)
            .name("pass")
            .room(room)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws SFSRoomException {
        doThrow(SFSRoomException.class)
        .when(api)
        .changeRoomName(any(User.class), any(Room.class), any(String.class));
        ChangeRoomNameImpl command = new ChangeRoomNameImpl(context, api, extension);
        command.owner(user)
            .name("pass")
            .room(room)
            .execute();
        doNothing()
        .when(api)
        .changeRoomName(any(User.class), any(Room.class), any(String.class));
    }
    
}
