package com.tvd12.ezyfox.sfs2x.data.impl;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import static com.smartfoxserver.v2.entities.data.SFSDataType.*;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
public class ParametersUtil {

    private ParametersUtil() {}
    
    public static Parameters sfsobject2parameters(ISFSObject sfsobject) {
        Parameters answer = new ParameterWrapper();
        for(String key : sfsobject.getKeys()) 
            answer.set(key, getValue(sfsobject.get(key)));
        return answer;
    }
    
    public static Parameters sfsarray2parameters(ISFSArray sfsarray) {
        Parameters answer = new ParameterWrapper();
        for(int i = 0 ; i < sfsarray.size() ; i++) 
            answer.set(i, getValue(sfsarray.get(i)));
        return answer;
    }
    
    private static Object getValue(SFSDataWrapper wrapper) {
        SFSDataType type = wrapper.getTypeId();
        Object value = wrapper.getObject();
        if(type == SFS_OBJECT)
            return sfsobject2parameters((ISFSObject) value);
        else if(type == SFS_ARRAY)
            return sfsarray2parameters((ISFSArray) value);
        return value;
    }
    
}
