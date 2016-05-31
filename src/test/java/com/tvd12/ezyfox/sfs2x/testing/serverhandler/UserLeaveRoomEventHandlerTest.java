package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserLeaveRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

public class UserLeaveRoomEventHandlerTest extends BaseHandlerTest {
    
    @Test
    public void test() throws SFSException {
        UserLeaveRoomEventHandler handler = new UserLeaveRoomEventHandler(context);
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
}
