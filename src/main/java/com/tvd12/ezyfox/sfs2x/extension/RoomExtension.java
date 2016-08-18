package com.tvd12.ezyfox.sfs2x.extension;

import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class RoomExtension extends ZoneExtension {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.extension.ZoneExtension#createAppContext()
     */
    @Override
    protected AppContextImpl createAppContext() {
        AppContextImpl context = getAppContext();
        RoomContextImpl answer = new RoomContextImpl(getClass());
        answer.setUserClass(context.getUserClass());
        answer.setGameUserClasses(context.getGameUserClasses());
        answer.setRoomClasses(context.getRoomClasses());
        answer.setAppContext(context);
        return answer;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.extension.ZoneExtension#newClientEventHandler(java.lang.String)
     */
    @Override
    protected ClientEventHandler newClientEventHandler(String command) {
        ClientRoomEventHandler handler = new ClientRoomEventHandler(context, command);
        handler.setRoom(getParentRoom());
        return handler;
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
     * @see com.tvd12.ezyfox.sfs2x.extension.ZoneExtension#addAgent()
     */
    @Override
    protected void addAgent() {}
}
