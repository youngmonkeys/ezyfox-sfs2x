package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerInitializingEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

public class ServerInitializingEventHandlerTest extends BaseHandlerTest {

    @Test
    public void test() throws SFSException {
        ServerInitializingEventHandler handler = new ServerInitializingEventHandler(context);
        handler.handle();
    }

}
