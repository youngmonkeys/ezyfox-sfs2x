package com.tvd12.ezyfox.sfs2x.serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.smartfoxserver.v2.entities.variables.Variable;
import com.tvd12.ezyfox.core.structure.ClassUnwrapper;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;

public abstract class AgentUnwrapper extends ParameterUnwrapper {
    
    protected abstract <T extends Variable> T newVariable(
            String name, Object value, boolean isHidden);

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List unwrap(ClassUnwrapper unwrapper, Object agent) {
	    List variables = new ArrayList<>();
	    List<GetterMethodCover> methods = unwrapper.getMethods();
	    for(GetterMethodCover method : methods) {
	        Object value = method.invoke(agent);
	        if(value == null)
	            continue;
            variables.add(newVariable(method.getKey(), 
                    getValue(method, value), 
                    method.isHidden()));
	    }
	    return variables;
	}
	
	@SuppressWarnings("rawtypes")
    protected Object getValue(GetterMethodCover method, Object value) {
        if(method.isByte()) {
            value = ((Byte)value).intValue();
        }
        else if(method.isChar()) {
            value = (int)((Character)value).charValue();
        }
        else if(method.isFloat()) {
            value = ((Float)value).doubleValue();
        }
        else if(method.isLong()) {
            value = ((Long)value).doubleValue();
        }
        else if(method.isShort()) {
            value = ((Short)value).intValue();
        }
        else if(method.isObject()) {
            value = parseObject(method, value);
        }
        else if(method.isPrimitiveBooleanArray()) {
	        value = StringUtils.join((boolean[])value, ",");
	    }
	    else if(method.isPrimitiveByteArray()) {
	        value = new String((byte[])value);
	    }
	    else if(method.isPrimitiveCharArray()) {
	        value = new String((char[])value);
	    }
	    else if(method.isPrimitiveDoubleArray()) {
	        value = StringUtils.join((double[])value, ",");
	    }
	    else if(method.isPrimitiveFloatArray()) {
	        value = StringUtils.join((float[])value, ",");
	    }
	    else if(method.isPrimitiveIntArray()) {
	        value = StringUtils.join((int[])value, ",");
	    }
	    else if(method.isPrimitiveLongArray()) {
	        value = StringUtils.join((long[])value, ",");
	    }
	    else if(method.isPrimitiveShortArray()) {
	        value = StringUtils.join((short[])value, ",");
	    }
	    else if(method.isObjectArray()) {
            value = parseObjectArray(method, (Object[])value);
        }
	    else if(method.isArray()) {
	        value = StringUtils.join((Object[])value, ",");
	    }
	    else if(method.isArrayObjectCollection()) {
	        value = parseArrayObjectCollection(method, (Collection)value);
	    }
	    else if(method.isObjectCollection()) {
	        value = parseObjectCollection(method, (Collection)value);
	    }
        else if(method.isColection()) {
            value = toString((Collection)value);
        }
	    return value;
	}
	
	@SuppressWarnings("rawtypes")
    protected String toString(Collection value) {
	    return StringUtils.join(value, ",");
	}
	
}
