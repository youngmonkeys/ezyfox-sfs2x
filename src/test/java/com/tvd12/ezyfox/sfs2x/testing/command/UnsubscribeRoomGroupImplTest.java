package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.UnsubscribeRoomGroupImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class UnsubscribeRoomGroupImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        UnsubscribeRoomGroupImpl command = new UnsubscribeRoomGroupImpl(context, api, extension);
        command.groupId("1")
        .user(user)
        .execute();
    }
    
}
