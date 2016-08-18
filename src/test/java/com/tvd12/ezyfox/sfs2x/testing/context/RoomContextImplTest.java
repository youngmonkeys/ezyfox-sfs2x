package com.tvd12.ezyfox.sfs2x.testing.context;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.roomcontext.ExRoomExtension;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on Aug 18, 2016
 *
 */
public class RoomContextImplTest {

    @Test
    public void test() {
        AppContextImpl context = new AppContextImpl(AppEntryPoint.class);
        context.addAppCommand(AppCommand.class, AppCommand.class);
        RoomContextImpl roomContext = new RoomContextImpl(ExRoomExtension.class);
        roomContext.setAppContext(context);
        roomContext.set("hello", "world");
        assertEquals(roomContext.getAppContext(), context);
        assertNotNull(roomContext.command(AppCommand.class));
        assertEquals(context.get("hello", String.class), "world");
        assertEquals(roomContext.get("hello", String.class), "world");
    }
    
}
