package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.buddylist.SFSBuddyEventParam;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.model.ApiBuddyProperties;
import com.tvd12.ezyfox.sfs2x.serverhandler.BuddyOnlineStateUpdateEventHandler;

/**
 * @author tavandung12
 * Created on Jun 7, 2016
 *
 */
public class BuddyOnlineStateChangeHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() throws SFSException {
        apiUser.setBuddyProperties(new ApiBuddyProperties());
        BuddyOnlineStateUpdateEventHandler handler = new BuddyOnlineStateUpdateEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        when(event.getParameter(SFSBuddyEventParam.BUDDY_IS_ONLINE)).thenReturn(Boolean.TRUE);
        handler.handleServerEvent(event);
    }
    
}
