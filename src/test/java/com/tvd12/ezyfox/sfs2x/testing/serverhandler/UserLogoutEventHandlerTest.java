package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserLogoutEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

public class UserLogoutEventHandlerTest extends BaseHandlerTest {
    
    @Test
    public void testWithNotNullUser() throws SFSException {
        UserLogoutEventHandler handler = new UserLogoutEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        handler.handleServerEvent(event);
        
    }
    
    @Test
    public void testWithNullUser() throws SFSException {
        UserLogoutEventHandler handler = new UserLogoutEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(null);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        handler.handleServerEvent(event);
        
    }
    
}
