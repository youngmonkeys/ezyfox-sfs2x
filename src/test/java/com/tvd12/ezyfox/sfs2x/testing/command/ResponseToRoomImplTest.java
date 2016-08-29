package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.command.impl.ResponseToRoomImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.VideoPokerRoom;

/**
 * @author tavandung12
 * Created on Jun 29, 2016
 *
 */
public class ResponseToRoomImplTest extends BaseCommandTest2 {

    @Test(priority = 1)
    public void test() {
        ResponseToRoomImpl command = new ResponseToRoomImpl(context, api, extension);
        command.command("hello")
            .data(new VideoPokerRoom())
            .room(room)
            .exclude(user)
            .param("a", "b")
            .execute();
    }
    
    @Test(priority = 2)
    public void test2() {
        ResponseToRoomImpl command = new ResponseToRoomImpl(context, api, extension);
        command.command("hello")
            .data(null)
            .room(room)
            .execute();
    }
    
    @Test(priority = 3, expectedExceptions = {IllegalStateException.class})
    public void test3() {
        ResponseToRoomImpl command = new ResponseToRoomImpl(context, api, extension);
        command.command("")
            .data(null)
            .room(room)
            .execute();
    }
    
    @Test(priority = 5, expectedExceptions = {IllegalStateException.class})
    public void test5() {
        ResponseToRoomImpl command = new ResponseToRoomImpl(context, api, extension);
        command.command(null)
            .data(null)
            .room(room)
            .execute();
    }
    
    @Test(priority = 6, expectedExceptions = {IllegalStateException.class})
    public void test6() {
        ResponseToRoomImpl command = new ResponseToRoomImpl(context, api, extension);
        command.command("hello")
            .data(new VideoPokerRoom())
            .room(new ApiRoom() {
                /* (non-Javadoc)
                 * @see com.tvd12.ezyfox.core.entities.ApiRoom#getName()
                 */
                @Override
                public String getName() {
                    return "abc";
                }
            })
            .exclude(user)
            .param("a", "b")
            .execute();
    }
    
}
