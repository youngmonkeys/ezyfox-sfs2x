package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.buddylist.SFSBuddyEventParam;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiBuddyImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.BuddyAddEventHandler;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class BuddyAddEventHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() throws SFSException {
        BuddyAddEventHandler handler = new BuddyAddEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        when(event.getParameter(SFSBuddyEventParam.BUDDY)).thenReturn(new ApiBuddyImpl("dungtv", true));
        handler.handleServerEvent(event);
    }

}
