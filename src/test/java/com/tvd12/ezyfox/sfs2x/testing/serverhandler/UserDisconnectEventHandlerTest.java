package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.util.ClientDisconnectionReason;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserDisconnectEventHandler;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/**
 * @author tavandung12
 * Created on Jun 30, 2016
 *
 */
public class UserDisconnectEventHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() throws SFSException {
        UserDisconnectEventHandler handler = new UserDisconnectEventHandler(context);
        Room room = new SFSRoom("abc");
        Room room1 = new SFSRoom("xyz");
        room.setProperty(APIKey.ROOM, new ApiRoom() {});
        Map<Room, Integer> ids = new HashMap<>();
        ids.put(room, 1);
        ids.put(room1, 2);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        when(event.getParameter(SFSEventParam.JOINED_ROOMS)).thenReturn(Lists.newArrayList(room));
        when(event.getParameter(SFSEventParam.PLAYER_IDS_BY_ROOM)).thenReturn(ids);
        when(event.getParameter(SFSEventParam.DISCONNECTION_REASON)).thenReturn(ClientDisconnectionReason.BAN);
        handler.handleServerEvent(event);
    }
    
}
