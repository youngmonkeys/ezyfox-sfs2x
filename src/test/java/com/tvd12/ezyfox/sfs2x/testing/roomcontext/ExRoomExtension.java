package com.tvd12.ezyfox.sfs2x.testing.roomcontext;

import com.smartfoxserver.v2.entities.Zone;
import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.extension.RoomExtension;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@RoomContextConfiguration(clazz = RoomConfig.class)
public class ExRoomExtension extends RoomExtension {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.extension.RoomExtension#getAppContext()
     */
    @Override
    protected AppContextImpl getAppContext() {
        return new AppContextImpl(AppEntryPoint.class);
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.BaseSFSExtension#getParentZone()
     */
    @Override
    public Zone getParentZone() {
        return mock(Zone.class);
    }
}
