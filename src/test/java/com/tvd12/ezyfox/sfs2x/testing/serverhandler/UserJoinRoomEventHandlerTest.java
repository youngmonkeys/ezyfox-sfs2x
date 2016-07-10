package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserJoinRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

public class UserJoinRoomEventHandlerTest extends BaseHandlerTest {

    @Test
    public void test() throws SFSException {
        UserJoinRoomEventHandler handler = new ExUserJoinRoomEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        ApiRoom apiRoom = createRoom();
        Room sfsRoom = mock(Room.class);
        when(sfsRoom.getProperty(APIKey.ROOM)).thenReturn(apiRoom);
        when(event.getParameter(SFSEventParam.ROOM)).thenReturn(sfsRoom);
        handler.handleServerEvent(event);
    }
    
    public ApiRoom createRoom() {
        PokerRoom room = new PokerRoom();
        room.setName("Lobby");
        return room;
    }
    
    public static class ExUserJoinRoomEventHandler extends UserJoinRoomEventHandler {

        public ExUserJoinRoomEventHandler(AppContextImpl context) {
            super(context);
        }
        
    }
    
}
