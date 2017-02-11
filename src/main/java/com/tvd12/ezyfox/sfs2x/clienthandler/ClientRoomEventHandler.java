package com.tvd12.ezyfox.sfs2x.clienthandler;

import java.lang.reflect.Method;

import com.smartfoxserver.v2.entities.Room;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class ClientRoomEventHandler extends ClientEventHandler {
    
    /**
     * @param context the room context
     * @param command the command
     */
    public ClientRoomEventHandler(BaseContext context, String command) {
        super(context, command);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler#invokeExecuteMethod(java.lang.reflect.Method, java.lang.Object, java.lang.Object)
     */
    @Override
    protected void invokeExecuteMethod(Method method, Object listener, Object userAgent) {
        ReflectMethodUtil.invokeExecuteMethod(
                method, 
                listener, 
                context, getRoom().getProperty(APIKey.ROOM), userAgent);
    }
    
    protected Room getRoom() {
        return getParentExtension().getParentRoom();
    }

}
