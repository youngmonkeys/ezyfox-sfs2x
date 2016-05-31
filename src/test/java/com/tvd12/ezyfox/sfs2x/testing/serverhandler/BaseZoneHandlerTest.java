package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiZoneImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class BaseZoneHandlerTest extends BaseHandlerTest {

    protected Zone sfsZone;
    
    public BaseZoneHandlerTest() {
        super();
        sfsZone = mock(Zone.class);
        when(sfsZone.getId()).thenReturn(1);
        when(sfsZone.getMaxAllowedRooms()).thenReturn(2);
        when(sfsZone.getMaxAllowedUsers()).thenReturn(3);
        when(sfsZone.getMaxRoomNameChars()).thenReturn(4);
        when(sfsZone.getMaxRoomsCreatedPerUserLimit()).thenReturn(5);
        when(sfsZone.getMaxRoomVariablesAllowed()).thenReturn(6);
        when(sfsZone.getMaxUserIdleTime()).thenReturn(7);
        when(sfsZone.getMaxUserVariablesAllowed()).thenReturn(8);
        when(sfsZone.getMinRoomNameChars()).thenReturn(9);
        when(sfsZone.getTotalRoomCount()).thenReturn(10);
        when(sfsZone.getUserCount()).thenReturn(11);
        when(sfsZone.getUserReconnectionSeconds()).thenReturn(12);
        when(sfsZone.isActive()).thenReturn(true);
        when(sfsZone.getName()).thenReturn("world");
        when(sfsZone.getProperty("a")).thenReturn("b");
        when(sfsZone.getProperty(APIKey.ZONE)).thenReturn(new ApiZoneImpl(sfsZone));
    }
    
    
}
