package com.tvd12.ezyfox.sfs2x.testing.clienthandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.ExRoomExtension2;

/**
 * @author tavandung12
 * Created on Aug 17, 2016
 *
 */
public class ClientRoomEventHandlerTest {

    @Test
    public void test() {
        User sfsUser = mock(User.class);
        when(sfsUser.containsProperty(APIKey.USER)).thenReturn(true);
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(new AppUser());
        Room sfsRoom = new SFSRoom("abc");
        sfsRoom.setProperty(APIKey.USER, new PokerRoom());
        RoomContextImpl context = new RoomContextImpl(ExRoomExtension2.class);
        ContextProvider.getInstance().addContext(ExRoomExtension2.class, context);
        ClientRoomEventHandler handler = new ClientRoomEventHandler(context, "abc");
        handler.setRoom(sfsRoom);
        handler.handleClientRequest(sfsUser, new SFSObject());
    }
    
}
