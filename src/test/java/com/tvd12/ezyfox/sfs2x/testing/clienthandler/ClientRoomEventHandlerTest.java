package com.tvd12.ezyfox.sfs2x.testing.clienthandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.command.BaseCommandTest2;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.BetRequestListener;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.ExRoomExtension2;

/**
 * @author tavandung12
 * Created on Aug 17, 2016
 *
 */
public class ClientRoomEventHandlerTest extends BaseCommandTest2 {

    @Test
    public void test() {
        User sfsUser = mock(User.class);
        when(sfsUser.containsProperty(APIKey.USER)).thenReturn(true);
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(new AppUser());
        Room sfsRoom = new SFSRoom("abc");
        sfsRoom.setProperty(APIKey.USER, new PokerRoom());
        RoomContextImpl roomContext = newRoomContext();
        ContextProvider.getInstance().addContext(ExRoomExtension2.class, context);
        ClientRoomEventHandler handler = new ClientRoomEventHandler(roomContext, "abc");
        handler.setRoom(sfsRoom);
        handler.setParentExtension(mock(SFSExtension.class));
        handler.handleClientRequest(sfsUser, new SFSObject());
    }
    
    private RoomContextImpl newRoomContext() {
        RoomContextImpl answer = new RoomContextImpl();
        answer.init(context, ExRoomExtension2.class);
        context.addObjectDeserializer(BetRequestListener.class, new ObjectDeserializer() {
            
            @SuppressWarnings("unchecked")
            @Override
            public BetRequestListener deserialize(Object object, Parameters params) {
                BetRequestListener b = (BetRequestListener)object;
                return b;
            }
        });
        context.addObjectSerializer(BetRequestListener.class, new ObjectSerializer() {
            
            @Override
            public Parameters serialize(Object object) {
                BetRequestListener b = (BetRequestListener)object;
                Parameters answer = new ParameterWrapper();
                answer.set("id", b.getId());
                return answer;
            }
        });
        return answer;
    }
    
}
