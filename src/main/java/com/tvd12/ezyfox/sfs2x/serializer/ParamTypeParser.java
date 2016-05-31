package com.tvd12.ezyfox.sfs2x.serializer;

import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isBool;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isByte;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isByteArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isChar;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isCharArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isCollection;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isDouble;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isFloat;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isInt;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isLong;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isObject;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isShort;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isString;
import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isStringArray;

import java.lang.reflect.Field;

import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.variables.VariableType;

public final class ParamTypeParser {

	private ParamTypeParser() {}
	
	public static SFSDataType getParamType(Field field) {
	    return getParamType(field.getType());
	}
	
	public static SFSDataType getParamType(Class<?> type) {
		if(isBool(type))
			return SFSDataType.BOOL;
		if(isByte(type))
			return SFSDataType.BYTE;
		if(isChar(type)) 
			return SFSDataType.BYTE;
		if(isDouble(type)) 
			return SFSDataType.DOUBLE;
		if(isFloat(type))
			return SFSDataType.FLOAT;
		if(isInt(type))
			return SFSDataType.INT;
		if(isLong(type))
			return SFSDataType.LONG;
		if(isShort(type))
			return SFSDataType.SHORT;
		if(isCollection(type))
			return SFSDataType.SFS_ARRAY;
		if(isString(type))
			return SFSDataType.UTF_STRING;
		if(isBoolArray(type))
			return SFSDataType.BOOL_ARRAY;
		if(isByteArray(type))
			return SFSDataType.BYTE_ARRAY;
		if(isCharArray(type))
			return SFSDataType.BYTE_ARRAY;
		if(isDoubleArray(type))
			return SFSDataType.DOUBLE_ARRAY;
		if(isFloatArray(type))
			return SFSDataType.FLOAT_ARRAY;
		if(isIntArray(type))
			return SFSDataType.INT_ARRAY;
		if(isLongArray(type))
			return SFSDataType.LONG_ARRAY;
		if(isShortArray(type))
			return SFSDataType.SHORT_ARRAY;
		if(isStringArray(type))
			return SFSDataType.UTF_STRING_ARRAY;
		if(isObject(type))
			return SFSDataType.SFS_OBJECT;
		return SFSDataType.SFS_ARRAY;
	}
	
	public static VariableType getVariableType(Class<?> type) {
		if(isBool(type)) 
			return VariableType.BOOL;
		if(isByte(type))
			return VariableType.INT;
		if(isChar(type)) 
			return VariableType.INT;
		if(isDouble(type))
			return VariableType.DOUBLE;
		if(isFloat(type))
			return VariableType.DOUBLE;
		if(isInt(type))
			return VariableType.INT;
		if(isLong(type))
			return VariableType.DOUBLE;
		if(isShort(type))
		    return VariableType.INT;
		if(isString(type))
			return VariableType.STRING;
		if(isArray(type)) 
			return VariableType.ARRAY;
		if(isCollection(type)) 
			return VariableType.ARRAY;
		if(isObject(type))
			return VariableType.OBJECT;
		return VariableType.NULL;
	}
	
}
