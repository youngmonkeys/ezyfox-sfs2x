package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.*;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerReadyEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

public class ServerReadyHandlerTest extends BaseHandlerTest {

    @Test
    public void test() throws SFSException {
        ServerReadyEventHandler handler = new ServerReadyEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        handler = spy(handler);
        SFSExtension ext = mock(SFSExtension.class);
        when(handler.getParentExtension()).thenReturn(ext);
        handler.handleServerEvent(event);
    }
    
    @Test
    public void test1() throws SFSException {
        ServerReadyEventHandler handler = new ServerReadyEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        handler = spy(handler);
        when(handler.getParentExtension()).thenReturn(sfsExtension);
        handler.handleServerEvent(event);
    }
    
}
