package com.tvd12.ezyfox.sfs2x.testing.extension;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
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
    public void test2() {
        AppContextImpl ctx = newAppContext();
        ContextProvider.getInstance().addContext(AppEntryPoint.class, ctx);
        ExRoomExtension2 ex2 = new ExRoomExtension2();
        ex2.init();
        ex2.destroy();
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
}
