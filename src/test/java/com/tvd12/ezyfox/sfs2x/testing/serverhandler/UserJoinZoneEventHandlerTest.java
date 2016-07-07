package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiZoneImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserJoinZoneEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

import static org.mockito.Mockito.*;

import java.util.Properties;

public class UserJoinZoneEventHandlerTest extends BaseHandlerTest {

    @Test
    public void test() throws SFSException {
        ISFSApi api = mock(ISFSApi.class);
        context.setApi(api);
        UserJoinZoneEventHandler hander = new ExUserJoinZoneEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        Zone sfsZone = mockZone();
        ApiZone apiZone = new ApiZoneImpl(sfsZone);
        when(sfsZone.getProperty(APIKey.ZONE)).thenReturn(apiZone);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        
        hander.handleServerEvent(event);
        
    }
    
    public Zone mockZone() {
        Zone zone = mock(Zone.class);
        when(zone.getId()).thenReturn(1);
        when(zone.getMaxAllowedRooms()).thenReturn(2);
        when(zone.getMaxAllowedUsers()).thenReturn(3);
        when(zone.getMaxRoomNameChars()).thenReturn(4);
        when(zone.getMaxRoomsCreatedPerUserLimit()).thenReturn(5);
        when(zone.getMaxRoomVariablesAllowed()).thenReturn(6);
        when(zone.getMaxUserIdleTime()).thenReturn(7);
        when(zone.getMaxUserVariablesAllowed()).thenReturn(8);
        when(zone.getMinRoomNameChars()).thenReturn(9);
        when(zone.getTotalRoomCount()).thenReturn(10);
        when(zone.getUserCount()).thenReturn(11);
        when(zone.getUserReconnectionSeconds()).thenReturn(12);
        when(zone.isActive()).thenReturn(true);
        when(zone.getName()).thenReturn("world");
        when(zone.getProperty("a")).thenReturn("b");
        
        return zone;
    }
    
    public static class ExUserJoinZoneEventHandler extends UserJoinZoneEventHandler {

        public ExUserJoinZoneEventHandler(AppContextImpl context) {
            super(context);
        }
        
        @Override
        public SFSExtension getParentExtension() {
            SFSExtension extension = mock(SFSExtension.class);
            when(extension.getConfigProperties()).thenReturn(new Properties());
            return extension;
        }
        
    }
}
