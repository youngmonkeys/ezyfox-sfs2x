package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.SubscribeRoomGroupImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class SubscribeRoomGroupImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        SubscribeRoomGroupImpl command = new SubscribeRoomGroupImpl(context, api, extension);
        command.groupId("1")
        .user(user)
        .execute();
    }
    
}
