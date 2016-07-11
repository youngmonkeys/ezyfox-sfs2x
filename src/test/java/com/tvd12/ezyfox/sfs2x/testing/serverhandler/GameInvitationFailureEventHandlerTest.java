package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.sfs2x.serverhandler.GameInvitationFailureEventHandler;

/**
 * @author tavandung12
 * Created on Jul 3, 2016
 *
 */
public class GameInvitationFailureEventHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() throws SFSException {
        GameInvitationFailureEventHandler handler = new GameInvitationFailureEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSEventParam.ROOM)).thenReturn(new SFSRoom("abc"));
        handler.handleServerEvent(event);
    }
    
}
