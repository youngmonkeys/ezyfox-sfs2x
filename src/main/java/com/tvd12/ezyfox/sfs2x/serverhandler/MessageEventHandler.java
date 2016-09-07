package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.serializer.RequestParamDeserializer;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamSerializer;

/**
 * Support to handle event related to message
 * 
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public abstract class MessageEventHandler extends ServerBaseEventHandler {

    /**
     * @param context
     */
    public MessageEventHandler(BaseAppContext context) {
        super(context);
    }

    /**
     * Propagate event to handlers
     * 
     * @param message message data
     * @param params addition data to send
     */
    protected void notifyToHandlers(Object message, ISFSObject params) {
        if(params == null) params = new SFSObject();
        for(ServerHandlerClass handler : handlers) {
            notifyToHandler(handler, message, params);
        }
    }
    
    /**
     * Propagate event to handler
     * 
     * @param handler structure of handler class
     * @param message message data
     * @param params addition data to send
     */
    protected void notifyToHandler(ServerHandlerClass handler, 
            Object message, ISFSObject params) {
        Object object = handler.newInstance();
        MessageParamsClass paramsClass = context.getMessageParamsClass(object.getClass());
        if(paramsClass == null) paramsClass = new MessageParamsClass(object.getClass());
        RequestParamDeserializer.getInstance().deserialize(paramsClass.getWrapper(), params, object);
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                object,  context, message);
        ResponseParamSerializer.getInstance().object2params(paramsClass.getUnwrapper(), object, params);
    }
    
}
