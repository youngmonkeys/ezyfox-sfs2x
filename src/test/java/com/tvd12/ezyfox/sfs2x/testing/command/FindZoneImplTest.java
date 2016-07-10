package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.managers.IZoneManager;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.FindZoneImpl;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FindZoneImplTest extends BaseCommandTest2 {
    
    private FindZoneImpl command;
    
    /**
     * 
     */
    public FindZoneImplTest() {
        super();
        command = new FindZoneImpl(context, api, extension);
    }

    @Test
    public void test1() throws Exception {
        setupEnviroment();
        command.current();
        command.find(1);
        command.find(2);
        command.find(3);
        command.find("1");
        command.find("2");
        command.find("3");
        command.all();
        
    }
    
    public void setupEnviroment() throws Exception {
        IZoneManager zoneManager = mock(IZoneManager.class);
        when(zoneManager.getZoneList()).thenReturn(Lists.newArrayList(zone));
        Zone zone1 = mock(Zone.class);
        when(zone1.getProperty(APIKey.ZONE)).thenReturn(apiZone);
        when(zone1.containsProperty(APIKey.ZONE)).thenReturn(true);
        Zone zone2 = mock(Zone.class);
        
        when(zoneManager.getZoneById(1)).thenReturn(zone1);
        when(zoneManager.getZoneById(2)).thenReturn(zone2);
        when(zoneManager.getZoneById(3)).thenReturn(null);
        when(zoneManager.getZoneByName("1")).thenReturn(zone1);
        when(zoneManager.getZoneByName("2")).thenReturn(zone2);
        when(zoneManager.getZoneByName("3")).thenReturn(null);
        Field zoneManagerField = ReflectFieldUtil.getField("zoneManager", SmartFoxServer.class);
        zoneManagerField.setAccessible(true);
        zoneManagerField.set(SmartFoxServer.getInstance(), zoneManager);
    }
    
}
