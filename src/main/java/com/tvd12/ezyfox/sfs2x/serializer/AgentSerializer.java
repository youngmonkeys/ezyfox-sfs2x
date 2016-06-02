package com.tvd12.ezyfox.sfs2x.serializer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.smartfoxserver.v2.entities.variables.Variable;
import com.tvd12.ezyfox.core.structure.ClassUnwrapper;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;

/**
 * Support to serialize a java object to a list of smartfox variables 
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public abstract class AgentSerializer extends ParameterSerializer {
    
    /**
     * Create new variable object
     * 
     * @param name name of variable
     * @param value value of variable
     * @param isHidden hidden or visible?
     * @return a variable object
     */
    protected abstract <T extends Variable> T newVariable(
            String name, Object value, boolean isHidden);

    /**
     * Serialize java object
     * 
     * @param unwrapper structure of agent class
     * @param agent the agent object
     * @return List of variables
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List serialize(ClassUnwrapper unwrapper, Object agent) {
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
	
    /**
     * Call getter method and get value
     * 
     * @param method structure of getter method
     * @param value object to call method
     * @return a value
     */
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
	
	/**
	 * Convert a collection of value csv string
	 * 
	 * @param value the collection of values
	 * @return csv string
	 */
	@SuppressWarnings("rawtypes")
    protected String toString(Collection value) {
	    return StringUtils.join(value, ",");
	}
	
}
