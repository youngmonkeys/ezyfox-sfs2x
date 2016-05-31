package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.tvd12.ezyfox.sfs2x.command.impl.SendBuddyMessageImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerMessages;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class SendBuddyMessageImplTest extends BaseCommandTest2 {
    
    @Test
    public void test() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message("msg")
            .params(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test2() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message("msg")
            .params(new ClassA())
            .recipient(user)
            .recipient("abc")
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test3() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message("msg")
            .params(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender("abc")
            .execute();
    }
    
    @Test
    public void test4() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message("msg")
            .params(new ClassA())
            .recipient(user)
            .recipient("abc")
            .sender(user)
            .sender("abc")
            .execute();
    }
    
    @Test
    public void test5() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message(null)
            .params(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test6() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message(null)
            .params(null)
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class}, priority = 100)
    public void test8() throws SFSBuddyListException {
        doThrow(SFSBuddyListException.class).when(api).sendBuddyMessage(any(User.class), any(User.class), any(String.class), any(SFSObject.class));
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message("msg")
            .params(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
        doNothing().when(api).sendBuddyMessage(any(User.class), any(User.class), any(String.class), any(SFSObject.class));
    }
    
    @Test
    public void test9() {
        SendBuddyMessageImpl command = new SendBuddyMessageImpl(context, api, extension);
        command.message("msg")
            .params(new PokerMessages())
            .recipient(user)
            .recipient(USER_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    public static class ClassA {
        
    }

}
