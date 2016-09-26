/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.data.impl;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public class ParamTransformer extends SimpleTransformer {
    
    public ParamTransformer() {
        super();
    }
    
    public ParamTransformer(BaseContext context) {
        super(context);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer#transformNotNullValue(java.lang.Object)
     */
    protected SFSDataWrapper transformNotNullValue(Object object) {
        Object value = serializeObject(object);
        if(value instanceof Parameters)
            return transformParams((Parameters)value);
        if(value instanceof Parameters[])
            return transformParamsArray((Parameters[])value);
        return super.transformNotNullValue(value);
    }
    
    private Object serializeObject(Object object) {
        Object value = object;
        ObjectSerializer serializer = getObjectSerializer(object);
        if(serializer != null)
            value = serializer.serialize(object);
        return value;
    }
    
    private ObjectSerializer getObjectSerializer(Object value) {
        try {
            if(context == null) return null;
            return context.getObjectSerializer(value.getClass());
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
    
    private SFSDataWrapper transformParams(Parameters params) {
        ISFSObject result = new SFSObject();
        for(Object key : params.keys())
            result.put(key.toString(), transform(params.get(key)));
        return new SFSDataWrapper(SFSDataType.SFS_OBJECT, result);
    }
    
    private SFSDataWrapper transformParamsArray(Parameters[] paramss) {
        ISFSArray result = new SFSArray();
        for(Parameters params : paramss)
            result.add(transformParams(params));
        return new SFSDataWrapper(SFSDataType.SFS_ARRAY, result);
    }
    
}
