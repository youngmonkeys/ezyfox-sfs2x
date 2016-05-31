package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.tvd12.ezyfox.sfs2x.command.impl.ChangeRoomCapacityImpl;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class ChangeRoomCapacityImplTest extends BaseCommandTest2{

    @Test
    public void test() {
        ChangeRoomCapacityImpl command = new ChangeRoomCapacityImpl(context, api, extension);
        command.maxSpectators(3)
            .maxUsers(3)
            .room(room)
            .owner(user)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws SFSRoomException {
        doThrow(SFSRoomException.class)
        .when(api)
        .changeRoomCapacity(any(User.class), any(Room.class), any(int.class), any(int.class));
        ChangeRoomCapacityImpl command = new ChangeRoomCapacityImpl(context, api, extension);
        command.maxSpectators(3)
            .maxUsers(3)
            .room(room)
            .owner(user)
            .execute();
        doNothing()
        .when(api)
        .changeRoomCapacity(any(User.class), any(Room.class), any(int.class), any(int.class));
    }
    
}
