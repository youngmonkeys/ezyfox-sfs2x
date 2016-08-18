package com.tvd12.ezyfox.sfs2x.testing.roomcontext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.sfs2x.extension.RoomExtension;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@RoomContextConfiguration(clazz = RoomConfig.class)
public class ExRoomExtension2 extends RoomExtension {

    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.BaseSFSExtension#getParentZone()
     */
    @Override
    public Zone getParentZone() {
        return mock(Zone.class);
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.BaseSFSExtension#getParentRoom()
     */
    @Override
    public Room getParentRoom() {
        Room room = mock(Room.class);
        Zone zone = mock(Zone.class);
        ISFSExtension extension = new AppEntryPoint();
        when(zone.getExtension()).thenReturn(extension);
        when(room.getZone()).thenReturn(zone);
        return room;
    }
}
