package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSRoomException;
import com.tvd12.ezyfox.sfs2x.command.impl.SpectatorToPlayerImpl;
import static org.mockito.Mockito.*;
/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class SpectatorToPlayerImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        SpectatorToPlayerImpl command = new SpectatorToPlayerImpl(context, api, extension);
        command.fireClientEvent(true)
            .fireServerEvent(true)
            .room(room)
            .user(user)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws SFSRoomException {
        doThrow(SFSRoomException.class)
        .when(api)
        .spectatorToPlayer(any(User.class), any(Room.class), any(boolean.class), any(boolean.class));
        SpectatorToPlayerImpl command = new SpectatorToPlayerImpl(context, api, extension);
        command.fireClientEvent(true)
            .fireServerEvent(true)
            .room(room)
            .user(user)
            .execute();
        doNothing()
        .when(api)
        .spectatorToPlayer(any(User.class), any(Room.class), any(boolean.class), any(boolean.class));
    }
    
    
}
