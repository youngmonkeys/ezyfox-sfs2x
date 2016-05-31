package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.BanUserImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class BanUserImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        BanUserImpl command = new BanUserImpl(context, api, extension);
        command.byAddress()
            .delay(3)
            .duration(3)
            .message("msg")
            .modUser(USER_NAME)
            .user(user)
            .user(USER_NAME)
            .execute();
    }
    
    @Test
    public void test2() {
        BanUserImpl command = new BanUserImpl(context, api, extension);
        command.byName()
            .delay(3)
            .duration(3)
            .message("msg")
            .modUser(USER_NAME)
            .user(user)
            .user(USER_NAME)
            .execute();
    }
    
}
