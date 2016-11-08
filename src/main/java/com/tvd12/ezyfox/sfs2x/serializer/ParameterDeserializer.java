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
import java.util.List;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
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
            if(params.containsKey(method.getKey()))
                assignValuesToMethod(result, method, params);
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
            SetterMethodCover method, ISFSObject data) {
            Object value = null;
            String key = method.getKey();
            if(method.isColection())
                value = assignValuesToCollection(method, data);
            else if(method.isTwoDimensionsArray())
                value = assignValuesToTwoDimensionsArray(method, data);
            else if(method.isArray())
                value = assignValuesToArray(method, data);
            else if(method.isObject())
                value = assignValuesToObject(method, data.getSFSObject(key));
            else if(method.isBoolean())
                value = data.getBool(key);
            else if(method.isByte())
                value = data.getByte(key);
            else if(method.isChar())
                value = (char)data.getByte(key).byteValue();
            else if(method.isDouble())
                value = data.getDouble(key);
            else if(method.isFloat())
                value = data.getFloat(key);
            else if(method.isInt())
                value = data.getInt(key);
            else if(method.isLong())
                value = data.getLong(key);
            else if(method.isShort())
                value = data.getShort(key);
            else
                value = data.getUtfString(key);
                
            method.invoke(instance, value);
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method setter method
     * @param data the SFSObject
     * @return the java object
     */
    protected Object assignValuesToTwoDimensionsArray(SetterMethodCover method, 
            ISFSObject data) {
        return assignValuesToTwoDimensionsArray(method, data.getSFSArray(method.getKey()));
    }
    
    /**
     * Get value from SFSArray and call setter method to set value to java object
     * 
     * @param method setter method
     * @param array the SFSArray object
     * @return the java object
     */
    protected Object assignValuesToTwoDimensionsArray(SetterMethodCover method, 
            ISFSArray array) {
        Class<?> componentType = method.getComponentType();
        Class<?> type = componentType.getComponentType();
        Object result = Array.newInstance(componentType, array.size());
        for(int i = 0 ; i < array.size() ; i++)
            Array.set(result, i, parseValueByType(type, array, i));
        return result;
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method setter method
     * @param data the value as array to set
     * @return the java object
     */
    protected Object assignValuesToArray(SetterMethodCover method, 
            ISFSObject data) {
        String key = method.getKey();
        if(method.isObjectArray())
            return assignValuesToObjectArray(method, data.getSFSArray(key));
        if(method.isPrimitiveBooleanArray())
            return collectionToPrimitiveBoolArray(data.getBoolArray(key));
        if(method.isPrimitiveByteArray())
            return data.getByteArray(key);
        if(method.isPrimitiveCharArray())
            return byteArrayToCharArray(data.getByteArray(key));
        if(method.isPrimitiveDoubleArray())
            return collectionToPrimitiveDoubleArray(data.getDoubleArray(key));
        if(method.isPrimitiveFloatArray())
            return collectionToPrimitiveFloatArray(data.getFloatArray(key));
        if(method.isPrimitiveIntArray())
            return collectionToPrimitiveIntArray(data.getIntArray(key));
        if(method.isPrimitiveLongArray())
            return collectionToPrimitiveLongArray(data.getLongArray(key));
        if(method.isPrimitiveShortArray())
            return collectionToPrimitiveShortArray(data.getShortArray(key));
        if(method.isStringArray())
            return collectionToStringArray(data.getUtfStringArray(key));
        
        if(method.isWrapperBooleanArray())
            return collectionToWrapperBoolArray(data.getBoolArray(key));
        if(method.isWrapperByteArray())
            return toByteWrapperArray(data.getByteArray(key));
        if(method.isWrapperCharArray())
            return toCharWrapperArray(data.getByteArray(key));
        if(method.isWrapperDoubleArray())
            return collectionToWrapperDoubleArray(data.getDoubleArray(key));
        if(method.isWrapperFloatArray())
            return collectionToWrapperFloatArray(data.getFloatArray(key));
        if(method.isWrapperIntArray())
            return collectionToWrapperIntArray(data.getIntArray(key));
        if(method.isWrapperLongArray())
            return collectionToWrapperLongArray(data.getLongArray(key));
//        if(method.isWrapperShortArray())
            return collectionToWrapperShortArray(data.getShortArray(key));
    }
    
    /**
     * Get value from SFSObject and call setter method to set value to java object
     * 
     * @param method structure of setter method
     * @param data the value as collection
     * @return the java object
     */
    protected Object assignValuesToCollection(SetterMethodCover method, 
            ISFSObject data) {
        String key = method.getKey();
        if(method.isArrayObjectCollection())
            return assignValuesToArrayObjectCollection(method, data.getSFSArray(key));
        if(method.isObjectCollection())
            return assignValuesToObjectCollection(method, data.getSFSArray(key));
        if(method.isBooleanCollection())
            return data.getBoolArray(key);
        if(method.isByteCollection())
            return arrayToList(data.getByteArray(key));
        if(method.isCharCollection())
            return byteArrayToCharList(data.getByteArray(key));
        if(method.isDoubleCollection())
            return data.getDoubleArray(key);
        if(method.isFloatCollection())
            return data.getFloatArray(key);
        if(method.isIntCollection())
            return data.getIntArray(key);
        if(method.isLongCollection())
            return data.getLongArray(key);
        if(method.isShortCollection())
            return data.getShortArray(key);
        if(method.isStringCollection())
            return data.getUtfStringArray(key);
//        if(method.isArrayCollection())
        return assignValuesToArrayCollection(method, data.getSFSArray(key));
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
    protected Object assignValuesToObjectArray(SetterMethodCover method, 
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
    protected Object assignValuesToObjectCollection(
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
    protected Object assignValuesToArrayObjectCollection(
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
    protected Object assignValuesToArrayCollection(SetterMethodCover method,
            ISFSArray array) {
        List result = new ArrayList();
        Class<?> type = method.getGenericType().getComponentType();
        for(int i = 0 ; i < array.size() ; i++)
            result.add(parseValueByType(type, array, i));
        return result;
    }
    
    private Object parseValueByType(Class<?> type, ISFSArray array, int index) {
        if(isPrimitiveBool(type))
            return collectionToPrimitiveBoolArray(array.getBoolArray(index));
        if(isPrimitiveChar(type))
            return byteArrayToCharArray(array.getByteArray(index));
        if(isPrimitiveDouble(type))
            return collectionToPrimitiveDoubleArray(array.getDoubleArray(index));
        if(isPrimitiveFloat(type))
            return collectionToPrimitiveFloatArray(array.getFloatArray(index));
        if(isPrimitiveInt(type))
            return collectionToPrimitiveIntArray(array.getIntArray(index));
        if(isPrimitiveLong(type))
            return collectionToPrimitiveLongArray(array.getLongArray(index));
        if(isPrimitiveShort(type))
            return collectionToPrimitiveShortArray(array.getShortArray(index));
        if(isString(type))
            return collectionToStringArray(array.getUtfStringArray(index));
        if(isWrapperBool(type))
            return collectionToWrapperBoolArray(array.getBoolArray(index));
        if(isWrapperByte(type))
            return toByteWrapperArray(array.getByteArray(index));
        if(isWrapperChar(type))
            return toCharWrapperArray(array.getByteArray(index));
        if(isWrapperDouble(type))
            return collectionToWrapperDoubleArray(array.getDoubleArray(index));
        if(isWrapperFloat(type))
            return collectionToWrapperFloatArray(array.getFloatArray(index));
        if(isWrapperInt(type))
            return collectionToWrapperIntArray(array.getIntArray(index));
        if(isWrapperLong(type))
            return collectionToWrapperLongArray(array.getLongArray(index));
        if(isWrapperShort(type))
            return collectionToWrapperShortArray(array.getShortArray(index));
        return array.get(index).getObject();
    }
}
