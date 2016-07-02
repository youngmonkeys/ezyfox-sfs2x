package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserReconnectTryEventHandler;
import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on Jun 30, 2016
 *
 */
public class UserReconnectTryHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() {
        UserReconnectTryEventHandler handler = new UserReconnectTryEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        try {
            handler.handleServerEvent(event);
        } catch(Exception e) {e.printStackTrace();}
    }
    
    @Test(expectedExceptions = {SFSException.class})
    public void test1() throws SFSException {
        UserReconnectTryEventHandler handler = new UserReconnectTryEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(null);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(null);
        handler.handleServerEvent(event);
    }
    
}
