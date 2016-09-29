package com.tvd12.ezyfox.sfs2x.testing.roomcontext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.extension.RoomExtension;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@ContextConfiguration(clazz = RoomConfig.class)
public class ExRoomExtension extends RoomExtension {
    
    private Zone zone;
    private Room room;
    private ISFSExtension extension;
    
    public ExRoomExtension() {
        extension = new AppEntryPoint();
        zone = mock(Zone.class);
        when(zone.getExtension()).thenReturn(extension);
        room = mock(Room.class);
        when(room.getZone()).thenReturn(zone);
        ContextProvider.getInstance().addContext(AppEntryPoint.class, newAppContext());
    }

    protected AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.BaseSFSExtension#getParentRoom()
     */
    @Override
    public Room getParentRoom() {
        return room;
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.BaseSFSExtension#getParentZone()
     */
    @Override
    public Zone getParentZone() {
        return zone;
    }
}
