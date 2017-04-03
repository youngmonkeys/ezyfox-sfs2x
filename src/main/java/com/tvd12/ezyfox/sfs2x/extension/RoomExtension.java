package com.tvd12.ezyfox.sfs2x.extension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.ISFSEventParam;
import com.smartfoxserver.v2.core.SFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.IServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.ContextProvider;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientRoomEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.content.impl.RoomContextImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.InternalEventHandler;
import com.tvd12.ezyfox.sfs2x.serverhandler.RoomExtensionDestroyEventHandler;
import com.tvd12.ezyfox.sfs2x.serverhandler.RoomUserReconnectEventHandler;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class RoomExtension extends BaseExtension implements InternalEventHandler {

	private Map<String, IServerEventHandler> extendedEventHandlers;
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.extension.BaseExtension#initComponent()
	 */
	@Override
	protected void initComponent() {
		super.initComponent();
		extendedEventHandlers = new ConcurrentHashMap<>();
	}
	
	/* (non-Javadoc)
	 * @see com.tvd12.ezyfox.sfs2x.extension.BaseExtension#config()
	 */
	@Override
	protected void config() {
		addExtendedEventHandler(
				ServerEvent.ROOM_USER_RECONNECT, 
				new RoomUserReconnectEventHandler(getAppContext()));
	}

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
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.serverhandler.InternalEventHandler#handleEvent(java.lang.String, java.util.Map)
     */
    @Override
	public void handleEvent(String event, Map<ISFSEventParam, Object> params) {
		handleExtendedEvent(event, new SFSEvent(null, params));
	}
	
	protected void handleExtendedEvent(String event, ISFSEvent data) {
		try {
			extendedEventHandlers.get(event).handleServerEvent(data);
		} catch (SFSException e) {
			getLogger().error("handle extened event error", e);
		}
	}
    
	public void addExtendedEventHandler(String event, IServerEventHandler handler) {
		extendedEventHandlers.put(event, handler);
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
