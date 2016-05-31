package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.tvd12.ezyfox.sfs2x.command.impl.PlayerToSpectatorImpl;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class PlayerToSpectatorImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        PlayerToSpectatorImpl command = new PlayerToSpectatorImpl(context, api, extension);
        command.fireClientEvent(true)
            .fireServerEvent(true)
            .room(room)
            .user(user)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test1() throws SFSRoomException {
        doThrow(SFSRoomException.class)
        .when(api)
        .playerToSpectator(any(User.class), any(Room.class), any(boolean.class), any(boolean.class));
        PlayerToSpectatorImpl command = new PlayerToSpectatorImpl(context, api, extension);
        command.fireClientEvent(true)
            .fireServerEvent(true)
            .room(room)
            .user(user)
            .execute();
        doNothing()
        .when(api)
        .playerToSpectator(any(User.class), any(Room.class), any(boolean.class), any(boolean.class));
    }

}
