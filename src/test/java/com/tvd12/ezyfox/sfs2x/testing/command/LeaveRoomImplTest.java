package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.LeaveRoomImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class LeaveRoomImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        LeaveRoomImpl command = new LeaveRoomImpl(context, api, extension);
        command.fireClientEvent(true)
        .fireServerEvent(true)
        .room(room)
        .room(ROOM_NAME)
        .user(user)
        .user(USER_NAME)
        .execute();
    }
    
}
