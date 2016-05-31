package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.SendPrivateMessageImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerMessages;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class SendPrivateMessageImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        SendPrivateMessageImpl command = new SendPrivateMessageImpl(context, api, extension);
        command.message("hello")
            .params(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test2() {
        SendPrivateMessageImpl command = new SendPrivateMessageImpl(context, api, extension);
        command.message("hello")
            .params(new PokerMessages())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test3() {
        SendPrivateMessageImpl command = new SendPrivateMessageImpl(context, api, extension);
        command.message("hello")
            .params(null)
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    public static class ClassA {
        
    }

}
