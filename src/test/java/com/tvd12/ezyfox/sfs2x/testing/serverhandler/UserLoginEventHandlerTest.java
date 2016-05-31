package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserLoginEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

import static org.mockito.Mockito.*;

public class UserLoginEventHandlerTest extends BaseHandlerTest {

    @Test
    public void test() throws SFSException {
        UserLoginEventHandler handler = new UserLoginEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.LOGIN_NAME)).thenReturn("username");
        when(event.getParameter(SFSEventParam.LOGIN_PASSWORD)).thenReturn("password");
        handler.handleServerEvent(event);
    }
    
}
