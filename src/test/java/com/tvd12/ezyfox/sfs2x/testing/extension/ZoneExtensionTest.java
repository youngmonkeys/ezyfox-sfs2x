package com.tvd12.ezyfox.sfs2x.testing.extension;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.testing.command.BaseCommandTest;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;
import com.tvd12.test.reflect.MethodInvoker;

import static org.mockito.Mockito.*;

public class ZoneExtensionTest extends BaseCommandTest {
    
    private Zone zone;
    
    public ZoneExtensionTest() throws IllegalArgumentException, IllegalAccessException, ExtensionException {
        Field field = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        field.setAccessible(true);
        APIManager apiManager = mock(APIManager.class);
        when(apiManager.getSFSApi()).thenReturn(api);
        field.set(SmartFoxServer.getInstance(), apiManager);
        
        zone = mock(Zone.class);
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
        
        when(extension.getParentZone()).thenReturn(zone);
    }

    @Test
    public void test() {
        AppEntryPoint entryPoint = new AppEntryPoint();
        entryPoint.setParentZone(zone);
        entryPoint.init();
        entryPoint.destroy();
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void createServerEventHandlerInvalidCaseTest() {
        AppEntryPoint entryPoint = new AppEntryPoint();
        entryPoint.setParentZone(zone);
        MethodInvoker.create()
            .object(entryPoint)
            .method("createServerEventHandler")
            .param(SFSEventType.BUDDY_ADD)
            .param(ClassA.class)
            .invoke();
    }
    
    public static class ClassA {
        
    }
}
