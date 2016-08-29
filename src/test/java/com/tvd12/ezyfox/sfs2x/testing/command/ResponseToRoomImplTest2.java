/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.sfs2x.command.impl.ResponseToRoomImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.VideoPokerRoom;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 *
 */
public class ResponseToRoomImplTest2 extends BaseCommandTest2 {

    @Test(priority = 1)
    public void test() {
        ResponseToRoomImpl command = new ResponseToRoomImpl(context, mockApi(), extension);
        command.command("hello")
            .data(new MyRoom())
            .room(new MyRoom())
            .useUDP(false)
            .param("d", "d")
            .only("a", "b")
            .ignore("b")
            .execute();
    }
    
    @SuppressWarnings("unchecked")
    public ISFSApi mockApi() {
        api = mock(ISFSApi.class);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ISFSObject params = (ISFSObject) invocation.getArguments()[1];
                assertEquals(params.size(), 1);
                assertEquals(params.getUtfString("a"), "a");
                return null;
            }
        }).when(api).sendExtensionResponse(
                any(String.class), 
                any(ISFSObject.class), 
                any(List.class), 
                any(Room.class), 
                any(boolean.class));
        return api;
    }
    
    @Setter @Getter
    public static class MyRoom extends VideoPokerRoom {
        @ResponseParam
        public String a = "a";
        @ResponseParam
        public String b = "b";
        @ResponseParam
        public String c = "c";
        
        @Override
        public String getName() {
            return ROOM_NAME;
        }
    }
}
