package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.GetIpAddressImpl;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class GetIpAddressImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        GetIpAddressImpl command = new GetIpAddressImpl(context, api, extension);
        command.user(user)
            .user(USER_NAME)
            .execute();
        
        command
            .user("")
            .execute();
    }
    
}
