package com.tvd12.ezyfox.sfs2x.extension;

import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.RoomExtensionDestroyEventHandler;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class RoomExtension extends BaseExtension {

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.extension.BaseExtension#createContext()
     */
    @Override
    protected BaseContext createContext() {
        AppContextImpl context = getAppContext();
        RoomContextImpl answer = new RoomContextImpl();
        answer.init(context, getClass());
        return answer;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.extension.ZoneExtension#newClientEventHandler(java.lang.String)
     */
    @Override
    protected ClientEventHandler newClientEventHandler(String command) {
        return new ClientRoomEventHandler(context, command);
    }
    
    protected AppContextImpl getAppContext() {
        return (AppContextImpl) ContextProvider
                .getInstance()
                .getContext(getZoneExtensionClass());
    }
    
    protected Class<?> getZoneExtensionClass() {
        return getParentRoom().getZone().getExtension().getClass();
    }
    
    /* (non-Javadoc)
     * @see com.smartfoxserver.v2.extensions.SFSExtension#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
        this.startZoneExtensionDestroyEventHandler();
    }
    
    /**
     * Handle initializing event
     */
    protected void startZoneExtensionDestroyEventHandler() {
        RoomExtensionDestroyEventHandler handler = 
                new RoomExtensionDestroyEventHandler(getAppContext());
        handler.handle(getParentRoom());
    }
    
}
