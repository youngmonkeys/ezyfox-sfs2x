package com.tvd12.ezyfox.sfs2x.testing.command;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.sfs2x.command.impl.SendObjectMessageImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerMessages;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class SendObjectMessageImplTest extends BaseCommandTest2 {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test() {
        SendObjectMessageImpl command = new SendObjectMessageImpl(context, api, extension);
        command.jsonMessage("{\"value\" : 1}")
            .message("dung")
            .message(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .recipients(USER_NAME)
            .recipients((List)Lists.newArrayList(new AppUser()))
            .room(new PokerRoom())
            .room(ROOM_NAME)
            .room(1)
            .sender(user)
            .execute();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test2() {
        SendObjectMessageImpl command = new SendObjectMessageImpl(context, api, extension);
        command.jsonMessage("{\"value\" : 1}")
            .message("dung")
            .message(new ClassA())
            .recipient(user)
            .recipient(USER_NAME)
            .recipients(USER_NAME)
            .recipients((List)Lists.newArrayList(new AppUser()))
            .room(new PokerRoom())
            .room("")
            .room(1)
            .sender(user)
            .execute();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test3() {
        SendObjectMessageImpl command = new SendObjectMessageImpl(context, api, extension);
        command.jsonMessage("{\"value\" : 1}")
            .message("dung")
            .message((Object)null)
            .recipient(user)
            .recipient(USER_NAME)
            .recipients(USER_NAME)
            .recipients((List)Lists.newArrayList(new AppUser()))
            .room(new PokerRoom())
            .room(ROOM_NAME)
            .room(1)
            .sender(user)
            .execute();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test4() {
        SendObjectMessageImpl command = new SendObjectMessageImpl(context, api, extension);
        command.jsonMessage(null)
            .message("dung")
            .message((Object)null)
            .recipient(user)
            .recipient(USER_NAME)
            .recipients(USER_NAME)
            .recipients((List)Lists.newArrayList(new AppUser()))
            .room(new PokerRoom())
            .room(ROOM_NAME)
            .room(1)
            .sender(user)
            .execute();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test5() {
        SendObjectMessageImpl command = new SendObjectMessageImpl(context, api, extension);
        command.jsonMessage(null)
            .message((Object)null)
            .message((String)null)
            .recipient(user)
            .recipient(USER_NAME)
            .recipients(USER_NAME)
            .recipients((List)Lists.newArrayList(new AppUser()))
            .room(new PokerRoom())
            .room(ROOM_NAME)
            .room(1)
            .sender(user)
            .execute();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test6() {
        SendObjectMessageImpl command = new SendObjectMessageImpl(context, api, extension);
        command.jsonMessage("{\"value\" : 1}")
            .message("dung")
            .message(new PokerMessages())
            .recipient(user)
            .recipient(USER_NAME)
            .recipients(USER_NAME)
            .recipients((List)Lists.newArrayList(new AppUser()))
            .room(new PokerRoom())
            .room(ROOM_NAME)
            .room(1)
            .sender(user)
            .execute();
    }
    
    public static class ClassA {
        
    }

}
