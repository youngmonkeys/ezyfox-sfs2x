package com.tvd12.ezyfox.sfs2x.data.impl;

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

import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.sfs2x.data.Transformer;

/**
 * Support to transform a primitive type value, wrapper type value, array of values or list of value  
 * to SFSDataWrapper object
 * 
 * @author tavandung12
 * Created on May 28, 2016
 *
 */
public class SimpleTransformer {

    // map of types their transformer
    private Map<Class<?>, Transformer> transformers
         = new HashMap<>();
    
    /**
     * Call initialize map of transformers in default constructor
     */
    public SimpleTransformer() {
        init();
    }
    
    /**
     * Transform the value to SFSDataWrapper object
     * 
     * @param value the value
     * @return a SFSDataWrapper object
     */
    public SFSDataWrapper transform(Object value) {
        if(value == null)   
            return new SFSDataWrapper(SFSDataType.NULL, value);
        if(isCollection(value.getClass())) {
            return transformCollection(value);
        }
        return transformObjectOrArray(value);
    }

    /**
     * Transform a collection of value to SFSDataWrapper object 
     * 
     * @param value the collection of value
     * @return a SFSDataWrapper object
     */
    @SuppressWarnings("unchecked")
    protected SFSDataWrapper transformCollection(Object value) {
        Collection<?> collection = (Collection<?>)value;
        if(collection.isEmpty())    
            return new SFSDataWrapper(SFSDataType.NULL, value);
        Iterator<?> it = collection.iterator();
        Object firstItem = it.next();
        if(firstItem instanceof Boolean)
            return new SFSDataWrapper(SFSDataType.BOOL_ARRAY, value);
        if(firstItem instanceof Byte)
            return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, collectionToPrimitiveByteArray((Collection<Byte>)value));
        if(firstItem instanceof Character)
            return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, charCollectionToPrimitiveByteArray((Collection<Character>)value));
        if(firstItem instanceof Double)
            return new SFSDataWrapper(SFSDataType.DOUBLE_ARRAY, value);
        if(firstItem instanceof Float)
            return new SFSDataWrapper(SFSDataType.FLOAT_ARRAY, value);
        if(firstItem instanceof Integer)
            return new SFSDataWrapper(SFSDataType.INT_ARRAY, value);
        if(firstItem instanceof Long)
            return new SFSDataWrapper(SFSDataType.LONG_ARRAY, value);
        if(firstItem instanceof Short)
            return new SFSDataWrapper(SFSDataType.SHORT_ARRAY, value);
        if(firstItem instanceof String) 
            return new SFSDataWrapper(SFSDataType.UTF_STRING_ARRAY, value);
        
        throw new IllegalArgumentException("Can not transform value of " + value.getClass());
    }
    
    /**
     * Transform a value or array of values to SFSDataWrapper object
     * 
     * @param value a value or array of values
     * @return a SFSDataWrapper object
     */
    protected SFSDataWrapper transformObjectOrArray(Object value) {
        return findTransformer(value.getClass()).transform(value);
    }
    
    /**
     * Find transformer of a type
     * 
     * @param clazz the type
     * @return a transformer reference
     */
    protected Transformer findTransformer(Class<?> clazz) {
        Transformer answer = transformers.get(clazz);
        if(answer == null)
            throw new IllegalArgumentException("Can not transform value of " + clazz);
        return answer;
    }
    
    /**
     * Initialize map of transformers
     */
    protected void init() {
        initWithWrapperType();
        initWithPrimitiveTypeArray();
        initWithWrapperTypArray();
        initWithStringType();
    }
    
    /**
     * Add transformers of wrapper type to the map
     */
    protected void initWithWrapperType() {
     // =========== wrapper type ==============
        transformers.put(Boolean.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BOOL, value);
            }
        });
        transformers.put(Byte.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BYTE, value);
            }
        });
        transformers.put(Character.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BYTE, (byte)((Character)value).charValue());
            }
        });
        transformers.put(Double.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.DOUBLE, value);
            }
        });
        transformers.put(Float.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.FLOAT, value);
            }
        });
        transformers.put(Integer.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.INT, value);
            }
        });
        transformers.put(Long.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.LONG, value);
            }
        });
        transformers.put(Short.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.SHORT, value);
            }
        });
    }
    
    /**
     * Add transformers of array of primitive values to the map
     */
    protected void initWithPrimitiveTypeArray() {
     // =========== primitve array type ==============
        transformers.put(boolean[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BOOL_ARRAY, primitiveArrayToBoolCollection((boolean[])value));
            }
        });
        transformers.put(byte[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, value);
            }
        });
        transformers.put(char[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, charArrayToByteArray((char[])value));
            }
        });
        transformers.put(double[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.DOUBLE_ARRAY, primitiveArrayToDoubleCollection((double[])value));
            }
        });
        transformers.put(float[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.FLOAT_ARRAY, primitiveArrayToFloatCollection((float[])value));
            }
        });
        transformers.put(int[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.INT_ARRAY, primitiveArrayToIntCollection((int[])value));
            }
        });
        transformers.put(long[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.LONG_ARRAY, primitiveArrayToLongCollection((long[])value));
            }
        });
        transformers.put(short[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.SHORT_ARRAY, primitiveArrayToShortCollection((short[])value));
            }
        });
    }
    
    /**
     * Add transformers of array of wrapper values to the map
     */
    protected void initWithWrapperTypArray() {
     // =========== wrapper array type ==============
        transformers.put(Boolean[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BOOL_ARRAY, wrapperArrayToCollection((Boolean[])value));
            }
        });
        transformers.put(Byte[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, toPrimitiveByteArray((Byte[])value));
            }
        });
        transformers.put(Character[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.BYTE_ARRAY, charWrapperArrayToPrimitiveByteArray((Character[])value));
            }
        });
        transformers.put(Double[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.DOUBLE_ARRAY, wrapperArrayToCollection((Double[])value));
            }
        });
        transformers.put(Float[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.FLOAT_ARRAY, wrapperArrayToCollection((Float[])value));
            }
        });
        transformers.put(Integer[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.INT_ARRAY, wrapperArrayToCollection((Integer[])value));
            }
        });
        transformers.put(Long[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.LONG_ARRAY, wrapperArrayToCollection((Long[])value));
            }
        });
        transformers.put(Short[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.SHORT_ARRAY, wrapperArrayToCollection((Short[])value));
            }
        });
    }
 
    /**
     * Add transformer of string and transformer of array of strings to the map
     */
    protected void initWithStringType() {
        transformers.put(String.class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.UTF_STRING, value);
            }
        });
        
        transformers.put(String[].class, new Transformer() {
            @Override
            public SFSDataWrapper transform(Object value) {
                return new SFSDataWrapper(SFSDataType.UTF_STRING_ARRAY, stringArrayToCollection((String[])value));
            }
        });
    }
    
}
