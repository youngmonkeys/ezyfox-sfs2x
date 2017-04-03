package com.tvd12.ezyfox.sfs2x.testing.extension;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.smartfoxserver.bitswarm.sessions.Session;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.ISFSEventParam;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.SFSUser;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.ExampleRoom;
import com.tvd12.ezyfox.sfs2x.testing.ExampleUser;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.ExRoomExtension;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.ExRoomExtension2;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class RoomExtensionTest {

    @Test
    public void test() {
        new ExRoomExtension().init();
    }
    
    @Test
    public void test2() throws SFSJoinRoomException {
        AppContextImpl ctx = newAppContext();
        ContextProvider.getInstance().addContext(AppEntryPoint.class, ctx);
        ExRoomExtension2 ex2 = new ExRoomExtension2();
        ex2.init();
        ex2.handleEvent(ServerEvent.ROOM_USER_RECONNECT, newReconnectParams());
        assertTrue(ex2.containsClientRequestHandler("abc"));
        assertNotNull(ex2.getClientRequestHandler("abc"));
        ex2.destroy();
    }
    
    @Test
    public void test3() throws SFSJoinRoomException {
        AppContextImpl ctx = newAppContext();
        ContextProvider.getInstance().addContext(AppEntryPoint.class, ctx);
        ExRoomExtension2 ex2 = new ExRoomExtension2();
        ex2.init();
        ex2.addExtendedEventHandler("error", new BaseServerEventHandler() {
			
			@Override
			public void handleServerEvent(ISFSEvent arg0) throws SFSException {
				throw new SFSException();
			}
		});
        ex2.handleEvent("error", newReconnectParams());
        assertTrue(ex2.containsClientRequestHandler("abc"));
        assertNotNull(ex2.getClientRequestHandler("abc"));
        ex2.destroy();
    }
    
    private Map<ISFSEventParam, Object> newReconnectParams() throws SFSJoinRoomException {
    	Map<ISFSEventParam, Object> params = new HashMap<>();
    	User sfsUser = new SFSUser(new Session());
    	sfsUser.setProperty(APIKey.USER, new ExampleUser());
    	Room sfsRoom = new SFSRoom("abc");
    	sfsRoom.setMaxUsers(100);
    	sfsRoom.setCapacity(100, 100);
    	sfsRoom.addUser(sfsUser);
    	sfsRoom.setProperty(APIKey.ROOM, new ExampleRoom());
    	params.put(SFSEventParam.USER, sfsUser);
    	params.put(SFSEventParam.ROOM, sfsRoom);
    	return params;
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
}
