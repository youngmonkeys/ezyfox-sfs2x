package com.tvd12.ezyfox.sfs2x.serializer;

import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.arrayToList;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.byteArrayToCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.byteArrayToCharList;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToStringArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToWrapperShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toByteWrapperArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toCharWrapperArray;

import java.util.List;

import com.smartfoxserver.v2.entities.variables.Variable;
import com.tvd12.ezyfox.core.structure.AgentClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;

/**
 * Support to deserialize list of smartfox variables to agent object
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public class AgentDeserializer extends ParameterDeserializer {
    
    /**
     * Deserialize list of smartfox variables to agent object
     * 
     * @param wrapper structure of agent class
     * @param variables list of smartfox variables
     * @return a agent object
     */
    public <T extends Variable> Object deserialize(AgentClassWrapper wrapper, 
            List<T> variables) {
        return deserialize(wrapper, wrapper.newInstance(), variables);
    }
    
    /**
     * Deserialize list of smartfox variables to agent object
     * 
     * @param wrapper structure of agent class
     * @param agent the agent object
     * @param variables list of smartfox variables
     * @return the agent object
     */
    protected <T extends Variable> Object deserialize(AgentClassWrapper wrapper, 
            Object agent, List<T> variables) {
        for(T variable : variables) {
            SetterMethodCover method = wrapper.getMethod(variable.getName());
            if(method != null)
                method.invoke(agent, getValue(method, variable));
        }
        return agent;
    }
    
    /**
     * Get value from the variable
     * 
     * @param method structure of setter method
     * @param variable the variable
     * @return a value
     */
    protected Object getValue(SetterMethodCover method, Variable variable) {
        if(method.isTwoDimensionsArray()) 
            return assignValuesToTwoDimensionsArray(method, variable.getSFSArrayValue());
        if(method.isArray())
            return assignValuesToArray(method, variable);
        
        if(method.isColection())
            return assignValuesToCollection(method, variable);

        if(method.isObject())
            return assignValuesToObject(method, variable.getSFSObjectValue());
        if(method.isByte())
            return variable.getIntValue().byteValue();
        if(method.isChar())
            return (char)variable.getIntValue().byteValue();
        if(method.isFloat())
            return variable.getDoubleValue().floatValue();
        if(method.isLong())
            return variable.getDoubleValue().longValue();
        if(method.isShort())
            return variable.getIntValue().shortValue();
        return variable.getValue();
    }
    
    private Object assignValuesToArray(SetterMethodCover method, Variable variable) {
        if(method.isObjectArray())
            return assignValuesToObjectArray(method, variable.getSFSArrayValue());
        if(method.isPrimitiveBooleanArray())
            return collectionToPrimitiveBoolArray(variable.getSFSArrayValue().getBoolArray(0));
        if(method.isPrimitiveByteArray())
            return variable.getSFSArrayValue().getByteArray(0);
        if(method.isPrimitiveCharArray())
            return byteArrayToCharArray(variable.getSFSArrayValue().getByteArray(0));
        if(method.isPrimitiveDoubleArray())
            return collectionToPrimitiveDoubleArray(variable.getSFSArrayValue().getDoubleArray(0));
        if(method.isPrimitiveFloatArray())
            return collectionToPrimitiveFloatArray(variable.getSFSArrayValue().getFloatArray(0));
        if(method.isPrimitiveIntArray())
            return collectionToPrimitiveIntArray(variable.getSFSArrayValue().getIntArray(0));
        if(method.isPrimitiveLongArray())
            return collectionToPrimitiveLongArray(variable.getSFSArrayValue().getLongArray(0));
        if(method.isPrimitiveShortArray())
            return collectionToPrimitiveShortArray(variable.getSFSArrayValue().getShortArray(0));
        if(method.isStringArray())
            return collectionToStringArray(variable.getSFSArrayValue().getUtfStringArray(0));
        
        if(method.isWrapperBooleanArray())
            return collectionToWrapperBoolArray(variable.getSFSArrayValue().getBoolArray(0));
        if(method.isWrapperByteArray())
            return toByteWrapperArray(variable.getSFSArrayValue().getByteArray(0));
        if(method.isWrapperCharArray())
            return toCharWrapperArray(variable.getSFSArrayValue().getByteArray(0));
        if(method.isWrapperDoubleArray())
            return collectionToWrapperDoubleArray(variable.getSFSArrayValue().getDoubleArray(0));
        if(method.isWrapperFloatArray())
            return collectionToWrapperFloatArray(variable.getSFSArrayValue().getFloatArray(0));
        if(method.isWrapperIntArray())
            return collectionToWrapperIntArray(variable.getSFSArrayValue().getIntArray(0));
        if(method.isWrapperLongArray())
            return collectionToWrapperLongArray(variable.getSFSArrayValue().getLongArray(0));
//        if(method.isWrapperShortArray())
        return collectionToWrapperShortArray(variable.getSFSArrayValue().getShortArray(0));
    }
    
    private Object assignValuesToCollection(SetterMethodCover method, Variable variable) {
        if(method.isArrayObjectCollection())
            return assignValuesToArrayObjectCollection(method, variable.getSFSArrayValue());
        if(method.isObjectCollection())
            return assignValuesToObjectCollection(method, variable.getSFSArrayValue());
        if(method.isBooleanCollection())
            return variable.getSFSArrayValue().getBoolArray(0);
        if(method.isByteCollection())
            return arrayToList(variable.getSFSArrayValue().getByteArray(0));
        if(method.isCharCollection())
            return byteArrayToCharList(variable.getSFSArrayValue().getByteArray(0));
        if(method.isDoubleCollection())
            return variable.getSFSArrayValue().getDoubleArray(0);
        if(method.isFloatCollection())
            return variable.getSFSArrayValue().getFloatArray(0);
        if(method.isIntCollection())
            return variable.getSFSArrayValue().getIntArray(0);
        if(method.isLongCollection())
            return variable.getSFSArrayValue().getLongArray(0);
        if(method.isShortCollection())
            return variable.getSFSArrayValue().getShortArray(0);
        if(method.isStringCollection())
            return variable.getSFSArrayValue().getUtfStringArray(0);
//        if(method.isArrayCollection())
        return assignValuesToArrayCollection(method, variable.getSFSArrayValue());
    }
}
