package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.KickUserImpl;

public class KickUserImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        KickUserImpl command = new KickUserImpl(context, api, extension);
        command.delay(100)
            .message("kick")
            .modUser(user)
            .modUser("dungtv")
            .user(user)
            .user(USER_NAME)
            .execute();
        
        command.modUser((String)null)
            .execute();
    }
    
}
