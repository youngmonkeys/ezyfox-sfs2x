/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.tvd12.ezyfox.sfs2x.command.impl.JoinRoomImpl;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 *
 */
public class JoinRoomImplTest extends BaseCommandTest2 {

    @Test(priority = 1)
    public void test() {
        JoinRoomImpl command = new JoinRoomImpl(context, api, extension);
        command.asSpectator(false)
            .fireClientEvent(true)
            .fireServerEvent(true)
            .password("123")
            .user(USER_NAME)
            .user(user)
            .roomToJoin(ROOM_NAME)
            .roomToJoin(room)
            .roomToLeave(room)
            .roomToLeave("Lobby")
            .execute();
        command.roomToLeave(ROOM_NAME)
            .execute();
        command.roomToLeave((String)null)
            .execute();
        when(sfsUser.getLastJoinedRoom()).thenReturn(sfsRoom);
        command.execute();
        
    }
    
    @Test(expectedExceptions = {RuntimeException.class}, priority = 2)
    public void executeThrowExceptionCase() throws SFSJoinRoomException {
        doThrow(SFSJoinRoomException.class)
        .when(api).joinRoom(any(User.class), any(Room.class), any(String.class), 
                any(Boolean.TYPE), any(Room.class), any(Boolean.TYPE), any(Boolean.TYPE));
        JoinRoomImpl command = new JoinRoomImpl(context, api, extension);
        command.asSpectator(false)
            .fireClientEvent(true)
            .fireServerEvent(true)
            .password("123")
            .user(USER_NAME)
            .user(user)
            .roomToJoin(ROOM_NAME)
            .roomToJoin(room)
            .roomToLeave(room)
            .roomToLeave("Lobby")
            .execute();
    }
}
