package com.tvd12.ezyfox.sfs2x.testing.context;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.ExRoomExtension;

/**
 * @author tavandung12
 * Created on Aug 18, 2016
 *
 */
public class RoomContextImplTest {

    @Test
    public void test() {
        AppContextImpl context = newAppContext();
        context.addAppCommand(AppCommand.class, AppCommand.class);
        RoomContextImpl roomContext = newRoomContext();
        roomContext.init(context, ExRoomExtension.class);
        roomContext.set("hello", "world");
        assertNotNull(roomContext.command(AppCommand.class));
        assertEquals(context.get("hello", String.class), "world");
        assertEquals(roomContext.get("hello", String.class), "world");
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
    private RoomContextImpl newRoomContext() {
        RoomContextImpl answer = new RoomContextImpl();
        return answer;
    }
}
