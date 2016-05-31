package com.tvd12.ezyfox.sfs2x.serverhandler;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serializer.RequestParamParser;
import com.tvd12.ezyfox.sfs2x.serializer.ResponseParamParser;

/**
 * @author tavandung12
 * Created on May 26, 2016
 *
 */
public abstract class MessageEventHandler extends ServerBaseEventHandler {

    /**
     * @param context
     */
    public MessageEventHandler(AppContextImpl context) {
        super(context);
    }

    protected void notifyToHandlers(Object message, ISFSObject params) {
        if(params == null) params = new SFSObject();
        for(ServerHandlerClass handler : handlers) {
            notifyToHandler(handler, message, params);
        }
    }
    
    protected void notifyToHandler(ServerHandlerClass handler, 
            Object message, ISFSObject params) {
        Object object = handler.newInstance();
        MessageParamsClass paramsClass = context.getMessageParamsClass(object.getClass());
        if(paramsClass == null) paramsClass = new MessageParamsClass(object.getClass());
        RequestParamParser.getInstance().assignValues(paramsClass.getWrapper(), params, object);
        ReflectMethodUtil.invokeHandleMethod(
                handler.getHandleMethod(), 
                object,  context, message);
        ResponseParamParser.getInstance().parse(paramsClass.getUnwrapper(), object, params);
    }
    
}
