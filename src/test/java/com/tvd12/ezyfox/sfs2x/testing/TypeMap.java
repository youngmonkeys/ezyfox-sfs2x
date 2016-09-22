package com.tvd12.ezyfox.sfs2x.testing;

import java.util.HashMap;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.SFSDataType;

/**
 * @author tavandung12
 * Created on Sep 22, 2016
 *
 */
public class TypeMap {
    
    private static Map<Class<?>, SFSDataType> TYPE_MAP
            = createTypeMap();

    private TypeMap() {
    }
    
    public static SFSDataType getDataType(Class<?> javaType) {
        return TYPE_MAP.get(javaType);
    }
    
    private static Map<Class<?>, SFSDataType> createTypeMap() {
        Map<Class<?>, SFSDataType> answer = new HashMap<>();
        
        answer.put(int.class, SFSDataType.INT);
        answer.put(byte.class, SFSDataType.BYTE);
        answer.put(char.class, SFSDataType.BYTE);
        answer.put(double.class, SFSDataType.DOUBLE);
        answer.put(float.class, SFSDataType.FLOAT);
        answer.put(int.class, SFSDataType.INT);
        answer.put(long.class, SFSDataType.LONG);
        answer.put(short.class, SFSDataType.SHORT);
        
        answer.put(Integer.class, SFSDataType.INT);
        answer.put(Byte.class, SFSDataType.BYTE);
        answer.put(Character.class, SFSDataType.BYTE);
        answer.put(Double.class, SFSDataType.DOUBLE);
        answer.put(Float.class, SFSDataType.FLOAT);
        answer.put(Integer.class, SFSDataType.INT);
        answer.put(Long.class, SFSDataType.LONG);
        answer.put(Short.class, SFSDataType.SHORT);
        
        answer.put(boolean[].class, SFSDataType.BOOL_ARRAY);
        answer.put(byte[].class, SFSDataType.BYTE_ARRAY);
        answer.put(char[].class, SFSDataType.BOOL_ARRAY);
        answer.put(double[].class, SFSDataType.BYTE_ARRAY);
        answer.put(float[].class, SFSDataType.FLOAT_ARRAY);
        answer.put(int[].class, SFSDataType.INT_ARRAY);
        answer.put(long[].class, SFSDataType.LONG_ARRAY);
        answer.put(short[].class, SFSDataType.SHORT_ARRAY);
        
        answer.put(Boolean[].class, SFSDataType.BOOL_ARRAY);
        answer.put(Byte[].class, SFSDataType.BYTE_ARRAY);
        answer.put(Character[].class, SFSDataType.BYTE_ARRAY);
        answer.put(Double[].class, SFSDataType.DOUBLE_ARRAY);
        answer.put(Float[].class, SFSDataType.FLOAT_ARRAY);
        answer.put(Integer[].class, SFSDataType.INT_ARRAY);
        answer.put(Long[].class, SFSDataType.LONG_ARRAY);
        answer.put(Short[].class, SFSDataType.SHORT_ARRAY);
        answer.put(String[].class, SFSDataType.UTF_STRING_ARRAY);
        
        return answer;
    }
    
}
