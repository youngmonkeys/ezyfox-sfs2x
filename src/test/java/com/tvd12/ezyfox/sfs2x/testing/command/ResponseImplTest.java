/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.ResponseImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.VideoPokerRoom;

/**
 * @author tavandung12
 *
 */
public class ResponseImplTest extends BaseCommandTest2 {

    @Test(priority = 1)
    public void test() {
        ResponseImpl command = new ResponseImpl(context, api, extension);
        command.command("hello")
            .data(new VideoPokerRoom())
            .recipient(user)
            .recipient("dungtv")
            .useUDP(false)
            .param("a", "b")
            .execute();
    }
    
    @Test(priority = 2)
    public void test2() {
        ResponseImpl command = new ResponseImpl(context, api, extension);
        command.command("hello")
            .data(null)
            .recipient(user)
            .recipient("dungtv")
            .useUDP(false)
            .execute();
    }
    
    @Test(priority = 3, expectedExceptions = {IllegalStateException.class})
    public void test3() {
        ResponseImpl command = new ResponseImpl(context, api, extension);
        command.command("")
            .data(null)
            .recipient(user)
            .recipient("dungtv")
            .useUDP(false)
            .execute();
    }
    
    @Test(priority = 5, expectedExceptions = {IllegalStateException.class})
    public void test5() {
        ResponseImpl command = new ResponseImpl(context, api, extension);
        command.command(null)
            .data(null)
            .recipient(user)
            .recipient("dungtv")
            .useUDP(false)
            .execute();
    }
    
    public static void main(String[] args) {
        new ResponseImplTest().test();
    }
}
