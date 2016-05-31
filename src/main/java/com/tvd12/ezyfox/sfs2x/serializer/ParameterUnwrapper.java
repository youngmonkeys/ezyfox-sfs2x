package com.tvd12.ezyfox.sfs2x.serializer;

import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.arrayToList;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.charArrayToByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.charCollectionToPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.charWrapperArrayToPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToBoolCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToDoubleCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToFloatCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToIntCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToLongCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.primitiveArrayToShortCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.stringArrayToCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.toPrimitiveByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.wrapperArrayToCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isPrimitiveChar;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperByte;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isWrapperChar;

import java.util.Collection;
import java.util.List;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.structure.ClassUnwrapper;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.ezyfox.core.structure.MethodCover;

public class ParameterUnwrapper {
	
    public ISFSObject parse(ClassUnwrapper unwrapper, Object object) {
        return parseMethods(unwrapper, object);
    }
    
    public ISFSObject parse(ClassUnwrapper unwrapper,
            Object object, ISFSObject result) {
        List<GetterMethodCover> methods = unwrapper.getMethods();
        for(GetterMethodCover method : methods) {
            Object value = method.invoke(object);
            if(value == null)
                continue;
            parseMethod(value, method, result);
        }
        return result;
    }
    
    protected ISFSObject parseMethods(ClassUnwrapper unwrapper,
            Object object) {
        return parse(unwrapper, object, new SFSObject());
        
    }
    
    @SuppressWarnings("rawtypes")
    protected void parseMethod(Object value, GetterMethodCover method,
            ISFSObject sfsObject) {
        
        if(method.isColection()) {
            value = parseCollection(method, (Collection)value);
        }
        if(method.isArray()) {
            value = parseArray(method, value);
        }
        else if(method.isObject()) {
            value = parseObject(method, value);
        }
        else if(method.isChar()) {
            value = (byte)(((Character)value).charValue());
        }
        SFSDataType type = getSFSDataType(method);
        sfsObject.put(method.getKey(), new SFSDataWrapper(type, value));
    }
    
    protected Object parseArray(GetterMethodCover method, 
            Object array) {
        if(method.isObjectArray()) {
            return parseObjectArray(method, (Object[])array);
        }
        else if(method.isPrimitiveBooleanArray()) {
            return primitiveArrayToBoolCollection((boolean[])array);
        }
        else if(method.isPrimitiveCharArray()) {
            return charArrayToByteArray((char[])array);
        }
        else if(method.isPrimitiveDoubleArray()) {
            return primitiveArrayToDoubleCollection((double[])array);
        }
        else if(method.isPrimitiveFloatArray()) {
            return primitiveArrayToFloatCollection((float[])array);
        }
        else if(method.isPrimitiveIntArray()) {
            return primitiveArrayToIntCollection((int[])array);
        }
        else if(method.isPrimitiveLongArray()) {
            return primitiveArrayToLongCollection((long[])array);
        }
        else if(method.isPrimitiveShortArray()) {
            return primitiveArrayToShortCollection((short[])array);
        }
        else if(method.isStringArray()) {
            return stringArrayToCollection((String[])array);
        }
        else if(method.isWrapperByteArray()) {
            return toPrimitiveByteArray((Byte[])array);
        }
        else if(method.isWrapperCharArray()) {
            return charWrapperArrayToPrimitiveByteArray((Character[])array);
        }
        else if(method.isWrapperBooleanArray()) {
            return wrapperArrayToCollection((Boolean[])array);
        }
        else if(method.isWrapperDoubleArray()) {
            return wrapperArrayToCollection((Double[])array);
        }
        else if(method.isWrapperFloatArray()) {
            return wrapperArrayToCollection((Float[])array);
        }
        else if(method.isWrapperIntArray()) {
            return wrapperArrayToCollection((Integer[])array);
        }
        else if(method.isWrapperLongArray()) {
            return wrapperArrayToCollection((Long[])array);
        }
        else if(method.isWrapperShortArray()) {
            return wrapperArrayToCollection((Short[])array);
        }
        return array;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Object parseCollection(GetterMethodCover method, 
            Collection collection) {
        if(method.isArrayObjectCollection()) {
            return parseArrayObjectCollection(method, collection);
        }
        else if(method.isObjectCollection()) {
            return parseObjectCollection(method, collection);
        }
        else if(method.isByteCollection()) {
            return collectionToPrimitiveByteArray(collection);
        }
        else if(method.isCharCollection()) {
            return charCollectionToPrimitiveByteArray(collection);
        }
        else if(method.isArrayCollection()) {
            return parseArrayCollection(method, collection);
        }
        return collection;
    }
    
    protected ISFSObject parseObject(GetterMethodCover method, 
            Object object) {
        return parse(method.getReturnClass(), 
                object);
    }
    
    protected ISFSArray parseObjectArray(GetterMethodCover method, 
            Object[] array) {
        return parseObjectArray(method.getReturnClass(), 
                array);
    }
    
    private ISFSArray parseObjectArray(ClassUnwrapper unwrapper, 
            Object[] array) {
        ISFSArray result = new SFSArray();
        for(Object obj : array) {
            result.addSFSObject(parse(unwrapper, obj));
        }
        return result;
    }
    
    @SuppressWarnings({ "rawtypes" })
    protected ISFSArray parseObjectCollection(GetterMethodCover method, 
            Collection collection) {
        ISFSArray result = new SFSArray();
        for(Object obj : collection) {
            result.addSFSObject(parseObject(
                    method, obj));
        }
        return result;
    }
    
    @SuppressWarnings({ "rawtypes" })
    protected ISFSArray parseArrayObjectCollection(GetterMethodCover method, 
            Collection collection) {
        ISFSArray result = new SFSArray();
        for(Object obj : collection) {
            result.addSFSArray(parseObjectArray(
                    method, 
                    (Object[])obj));
        }
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    private ISFSArray parseArrayCollection(GetterMethodCover method,
            Collection collection) {
        ISFSArray result = new SFSArray();
        SFSDataType dataType = ParamTypeParser
                .getParamType(method.getGenericType());
        Class<?> type = method.getGenericType().getComponentType();
        for(Object obj : collection) {
            Object value = obj;
            if(isPrimitiveChar(type)) {
                value = charArrayToByteArray((char[])value);
            }
            else if(isWrapperByte(type)) {
                value = toPrimitiveByteArray((Byte[])value);
            }
            else if(isWrapperChar(type)) {
                value = charWrapperArrayToPrimitiveByteArray((Character[])value);
            }
            else {
                value = arrayToList(value);
            }
            result.add(new SFSDataWrapper(dataType, value));
        }
        return result;
    }
	
    private SFSDataType getSFSDataType(MethodCover method) {
	    if(method.isBoolean()) 
	        return SFSDataType.BOOL;
	    if(method.isByte()) 
	        return SFSDataType.BYTE;
	    if(method.isChar())
	        return SFSDataType.BYTE;
	    if(method.isDouble())
	        return SFSDataType.DOUBLE;
	    if(method.isFloat())
	        return SFSDataType.FLOAT;
	    if(method.isInt())
	        return SFSDataType.INT;
	    if(method.isLong())
	        return SFSDataType.LONG;
	    if(method.isShort())
	        return SFSDataType.SHORT;
	    
	    if(method.isString())
	        return SFSDataType.UTF_STRING;
	    
	    if(method.isObject())
	        return SFSDataType.SFS_OBJECT;
	    
	    if(method.isBooleanArray())
	        return SFSDataType.BOOL_ARRAY;
	    if(method.isByteArray())
	        return SFSDataType.BYTE_ARRAY;
	    if(method.isCharArray())
	        return SFSDataType.BYTE_ARRAY;
	    if(method.isDoubleArray())
	        return SFSDataType.DOUBLE_ARRAY;
	    if(method.isFloatArray())
	        return SFSDataType.FLOAT_ARRAY;
	    if(method.isIntArray())
	        return SFSDataType.INT_ARRAY;
	    if(method.isLongArray())
	        return SFSDataType.LONG_ARRAY;
	    if(method.isShortArray())
	        return SFSDataType.SHORT_ARRAY;
	    if(method.isStringArray())
	        return SFSDataType.UTF_STRING_ARRAY;
	    
	    if(method.isObjectArray())
	        return SFSDataType.SFS_ARRAY;
	    
	    if(method.isBooleanCollection())
	        return SFSDataType.BOOL_ARRAY;
	    if(method.isByteCollection())
	        return SFSDataType.BYTE_ARRAY;
	    if(method.isCharCollection())
	        return SFSDataType.BYTE_ARRAY;
	    if(method.isDoubleCollection())
	        return SFSDataType.DOUBLE_ARRAY;
	    if(method.isFloatCollection())
	        return SFSDataType.FLOAT_ARRAY;
	    if(method.isIntCollection())
	        return SFSDataType.INT_ARRAY;
	    if(method.isLongCollection()) 
	        return SFSDataType.LONG_ARRAY;
	    if(method.isShortCollection())
	        return SFSDataType.SHORT_ARRAY;
	    if(method.isStringCollection())
	        return SFSDataType.UTF_STRING_ARRAY;
	    
	    if(method.isObjectCollection())
	        return SFSDataType.SFS_ARRAY;
	    if(method.isArrayObjectCollection())
            return SFSDataType.SFS_ARRAY;
//	    if(method.isArrayCollection()) 
	    return SFSDataType.SFS_ARRAY;
//	    return SFSDataType.SFS_OBJECT;
	}
}
