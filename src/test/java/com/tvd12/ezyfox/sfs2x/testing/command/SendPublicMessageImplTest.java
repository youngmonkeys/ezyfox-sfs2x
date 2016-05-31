package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.command.impl.SendPublicMessageImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerMessages;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class SendPublicMessageImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message("msg")
            .params(new ClassA())
            .room(room)
            .room(ROOM_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test1() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message((String)null)
            .params((Object)null)
            .room(room)
            .room(ROOM_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test2() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message((String)null)
            .params((Object)null)
            .room(room)
            .room("")
            .sender(user)
            .sender("")
            .execute();
    }
    
    @Test
    public void test3() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message((String)null)
            .params((Object)null)
            .room(room)
            .room("")
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test5() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message((String)null)
            .params((Object)null)
            .room(room)
            .room(ROOM_NAME)
            .sender(user)
            .sender("")
            .execute();
    }
    
    @Test
    public void test6() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message("msg")
            .params(new PokerMessages())
            .room(room)
            .room(ROOM_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }
    
    @Test
    public void test8() {
        SendPublicMessageImpl command = new SendPublicMessageImpl(context, api, extension);
        command.message("msg")
            .params(null)
            .room(room)
            .room(ROOM_NAME)
            .sender(user)
            .sender(USER_NAME)
            .execute();
    }

    public static class ClassA {
        
    }
    
}
