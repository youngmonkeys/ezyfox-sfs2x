package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.LogoutImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class LogoutImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        LogoutImpl command = new LogoutImpl(context, api, extension);
        command.user(user)
        .user(USER_NAME)
        .execute();
    }
    
}
