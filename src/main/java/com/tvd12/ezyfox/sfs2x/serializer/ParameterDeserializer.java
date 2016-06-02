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
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveBool;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveChar;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveDouble;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveFloat;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveInt;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveLong;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveShort;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isString;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperBool;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperByte;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperChar;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperDouble;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperFloat;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperInt;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperLong;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperShort;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.structure.ClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;

/**
 * Support to deserialize a SFSObject to a java object
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public class ParameterDeserializer {
	
    /**
     * Deserialize a SFSObject to a java object
     * 
     * @param wrapper structure of java object
     * @param params the SFSObject
     * @return a java object
     */
    public Object deserialize(ClassWrapper wrapper, ISFSObject params) {
        return assignValuesToMethods(wrapper, params);
    }
    
    /**
     * Deserialize a SFSObject to a java object
     * 
     * @param wrapper structure of java object
     * @param params the SFSObject
     * @param result the java object
     * @return the java object
     */
    public Object deserialize(ClassWrapper wrapper,
            ISFSObject params, Object result) {
        List<SetterMethodCover> methods = wrapper.getMethods();
        for(SetterMethodCover method : methods) {
            SFSDataWrapper data = params.get(method.getKey());
            if(data == null)
                continue;
            assignValuesToMethod(result, method, data);
        }
        return result;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object  
     * 
     * @param wrapper structure of java class
     * @param params the SFSObject
     * @return the java object
     */
    protected Object assignValuesToMethods(ClassWrapper wrapper,
            ISFSObject params) {
        return deserialize(wrapper, params, wrapper.newInstance());
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param instance object to call setter method
     * @param method structure of setter method
     * @param data value to set
     */
    protected void assignValuesToMethod(Object instance,
            SetterMethodCover method, 
            SFSDataWrapper data) {
            Object value = data.getObject();
            if(method.isColection()) {
                value = assignValuesToCollection(method, value);
            }
            if(method.isArray()) {
                value = assignValuesToArray(method, value);
            }
            else if(method.isObject()) {
                value = assignValuesToObject(method, (ISFSObject)value);
            }
            else if(method.isChar()) {
                value = (char)((Number)value).byteValue();
            }
                
            method.invoke(instance, value);
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method setter method
     * @param array the value as array to set
     * @return the java object
     */
    @SuppressWarnings("unchecked")
    protected Object assignValuesToArray(SetterMethodCover method, 
            Object array) {
        if(method.isObjectArray()) {
            return assignValuesToObjectArray(method, (ISFSArray)array);
        }
        else if(method.isPrimitiveBooleanArray()) {
            return collectionToPrimitiveBoolArray((Collection<Boolean>)array);
        }
        else if(method.isPrimitiveCharArray()) {
            return byteArrayToCharArray((byte[])array);
        }
        else if(method.isPrimitiveDoubleArray()) {
            return collectionToPrimitiveDoubleArray((Collection<Double>)array);
        }
        else if(method.isPrimitiveFloatArray()) {
            return collectionToPrimitiveFloatArray((Collection<Float>)array);
        }
        else if(method.isPrimitiveIntArray()) {
            return collectionToPrimitiveIntArray((Collection<Integer>)array);
        }
        else if(method.isPrimitiveLongArray()) {
            return collectionToPrimitiveLongArray((Collection<Long>)array);
        }
        else if(method.isPrimitiveShortArray()) {
            return collectionToPrimitiveShortArray((Collection<Short>)array);
        }
        else if(method.isStringArray()) {
            return collectionToStringArray((Collection<String>)array);
        }
        else if(method.isWrapperByteArray()) {
            return toByteWrapperArray((byte[])array);
        }
        else if(method.isWrapperCharArray()) {
            return toCharWrapperArray((byte[])array);
        }
        else if(method.isWrapperBooleanArray()) {
            return collectionToWrapperBoolArray((Collection<Boolean>)array);
        }
        else if(method.isWrapperDoubleArray()) {
            return collectionToWrapperDoubleArray((Collection<Double>)array);
        }
        else if(method.isWrapperFloatArray()) {
            return collectionToWrapperFloatArray((Collection<Float>)array);
        }
        else if(method.isWrapperIntArray()) {
            return collectionToWrapperIntArray((Collection<Integer>)array);
        }
        else if(method.isWrapperLongArray()) {
            return collectionToWrapperLongArray((Collection<Long>)array);
        }
        else if(method.isWrapperShortArray()) {
            return collectionToWrapperShortArray((Collection<Short>)array);
        }
        return array;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of setter method
     * @param array the value as collection
     * @return the java object
     */
    protected Object assignValuesToCollection(SetterMethodCover method, 
            Object array) {
        if(method.isArrayObjectCollection()) {
            return assignValuesToArrayObjectCollection(method, (ISFSArray)array);
        }
        else if(method.isObjectCollection()) {
            return assignValuesToObjectCollection(method, (ISFSArray)array);
        }
        else if(method.isByteCollection()) {
            return arrayToList(array);
        }
        else if(method.isCharCollection()) {
            return byteArrayToCharList((byte[])array);
        }
        else if(method.isArrayCollection()) {
            return assignValuesToArrayCollection(method, (ISFSArray)array);
        }
        return array;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of setter method
     * @param sfsObject the SFSObject
     * @return the java object
     */
    protected Object assignValuesToObject(SetterMethodCover method, 
            ISFSObject sfsObject) {
        return deserialize(method.getParameterClass(), 
                sfsObject);
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of setter method
     * @param sfsArray smartfox array
     * @return the java object
     */
    private Object assignValuesToObjectArray(SetterMethodCover method, 
            ISFSArray sfsArray) {
        return assignValuesToObjectArray(method.getParameterClass(), 
                sfsArray);
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param wrapper structure of java class
     * @param sfsArray smartfox array to deserialize
     * @return the java object
     */
    private Object assignValuesToObjectArray(
            ClassWrapper wrapper, 
            ISFSArray sfsArray) {
        Object result = Array.newInstance(wrapper.getClazz(), sfsArray.size());
        for(int i = 0 ; i < sfsArray.size() ; i++) {
            Object value = deserialize(wrapper, 
                    sfsArray.getSFSObject(i));
            Array.set(result, i, value);
        }
        return result;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of setter method
     * @param sfsArray smartfox array
     * @return the java object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object assignValuesToObjectCollection(
            SetterMethodCover method, 
            ISFSArray sfsArray) {
        List result = new ArrayList<>();
        for(int i = 0 ; i < sfsArray.size() ; i++) {
            result.add(assignValuesToObject(
                    method, 
                    sfsArray.getSFSObject(i)));
        }
        return result;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of setter method
     * @param sfsArray smartfox array
     * @return the java object
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Object assignValuesToArrayObjectCollection(
            SetterMethodCover method, 
            ISFSArray sfsArray) {
        List result = new ArrayList<>();
        for(int i = 0 ; i < sfsArray.size() ; i++) {
            result.add(assignValuesToObjectArray(
                    method.getParameterClass(), 
                    sfsArray.getSFSArray(i)));
        }
        return result;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of java object
     * @param array smartfox array
     * @return the java object
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Object assignValuesToArrayCollection(SetterMethodCover method,
            ISFSArray array) {
        List result = new ArrayList();
        Class<?> type = method.getGenericType().getComponentType();
        for(int i = 0 ; i < array.size() ; i++) {
            Object value = array.get(i).getObject();
            if(isPrimitiveBool(type)) {
                value = collectionToPrimitiveBoolArray((Collection)value);
            }
            else if(isPrimitiveChar(type)) {
                value = byteArrayToCharArray((byte[])value);
            }
            else if(isPrimitiveDouble(type)) {
                value = collectionToPrimitiveDoubleArray((Collection)value);
            }
            else if(isPrimitiveFloat(type)) {
                value = collectionToPrimitiveFloatArray((Collection)value);
            }
            else if(isPrimitiveInt(type)) {
                value = collectionToPrimitiveIntArray((Collection)value);
            }
            else if(isPrimitiveLong(type)) {
                value = collectionToPrimitiveLongArray((Collection)value);
            }
            else if(isPrimitiveShort(type)) {
                value = collectionToPrimitiveShortArray((Collection)value);
            }
            else if(isString(type)) {
                value = collectionToStringArray((Collection)value);
            }
            else if(isWrapperBool(type)) {
                value = collectionToWrapperBoolArray((Collection)value);
            }
            else if(isWrapperByte(type)) {
                value = toByteWrapperArray((byte[])value);
            }
            else if(isWrapperChar(type)) {
                value = toCharWrapperArray((byte[])value);
            }
            else if(isWrapperDouble(type)) {
                value = collectionToWrapperDoubleArray((Collection)value);
            }
            else if(isWrapperFloat(type)) {
                value = collectionToWrapperFloatArray((Collection)value);
            }
            else if(isWrapperInt(type)) {
                value = collectionToWrapperIntArray((Collection)value);
            }
            else if(isWrapperLong(type)) {
                value = collectionToWrapperLongArray((Collection)value);
            }
            else if(isWrapperShort(type)) {
                value = collectionToWrapperShortArray((Collection)value);
            }
            result.add(value);
        }
        return result;
    }
}
