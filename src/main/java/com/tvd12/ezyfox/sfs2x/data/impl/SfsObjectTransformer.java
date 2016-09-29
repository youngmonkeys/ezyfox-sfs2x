package com.tvd12.ezyfox.sfs2x.data.impl;

import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveBoolArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveDoubleArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveFloatArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveIntArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveLongArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToPrimitiveShortArray;
import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.collectionToStringArray;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.content.impl.BaseContext;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.sfs2x.data.SfsTransformer;

/**
 * @author tavandung12
 * Created on Sep 5, 2016
 *
 */
public class SfsObjectTransformer {

    private Map<SFSDataType, SfsTransformer> transformers;
    
    public SfsObjectTransformer() {
        this(null);
    }
    
    public SfsObjectTransformer(BaseContext context) {
        this.init();
    }
    
    protected void init() {
        initTransformers();
    }
    
    public Parameters transform(Object value) {
        if(value == null) 
            return transformNullValue(value);
        return transformNotNullValue(value);
    }
    
    protected Parameters transformNullValue(Object value) {
        return null;
    }
    
    protected Parameters transformNotNullValue(Object value) {
        return transformObject((ISFSObject)value);
    }
    
    protected Object transform(SFSDataWrapper wrapper) {
        Object value = wrapper.getObject();
        SFSDataType type = wrapper.getTypeId();
        if(type == SFSDataType.SFS_OBJECT)
            return transformObject((ISFSObject)value);
        if(type == SFSDataType.SFS_ARRAY)
            return transformArray((ISFSArray)value);
        return transformSimpleValue(wrapper);
    }
    
    protected Object transformSimpleValue(SFSDataWrapper wrapper) {
        return transformers.get(wrapper.getTypeId()).transform(wrapper.getObject());
    }
    
    protected Parameters transformObject(ISFSObject object) {
        Parameters answer = new ParameterWrapper();
        for(String key : object.getKeys())
            answer.set(key, transform(object.get(key)));
        return answer;
    }
    
    protected Parameters[] transformArray(ISFSArray array) {
        Parameters[] answer = new Parameters[array.size()];
        for(int i = 0 ; i < array.size() ; i++)
            answer[i] = transformItemInArray(array.get(i));
        return answer;
    }
    
    protected Parameters transformItemInArray(SFSDataWrapper wrapper) {
        Object value = wrapper.getObject();
        SFSDataType type = wrapper.getTypeId();
        if(type == SFSDataType.SFS_OBJECT)
            return transformObject((ISFSObject)value);
        if(type == SFSDataType.SFS_ARRAY)
            return createParameters(transformArray((ISFSArray)value));
        return createParameters(transformSimpleValue(wrapper));
    }
    
    protected Parameters createParameters(Object value) {
        ParameterWrapper answer = new ParameterWrapper();
        answer.set("value", value);
        return answer;
    }
    
    private void initTransformers() {
        transformers = defaultTransformers();
    }
    
    @SuppressWarnings("unchecked")
    private Map<SFSDataType, SfsTransformer> defaultTransformers() {
        
        //=============== simple data =================
        Map<SFSDataType, SfsTransformer> answer = new HashMap<>();
        answer.put(SFSDataType.BOOL, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.BYTE, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.DOUBLE, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.FLOAT, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.INT, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.LONG, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.SHORT, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.UTF_STRING, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        //=======================================
        
        //================ simple array data =====
        answer.put(SFSDataType.BOOL_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToPrimitiveBoolArray((Collection<Boolean>)value);
            }
        });
        answer.put(SFSDataType.BYTE_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(SFSDataType.DOUBLE_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToPrimitiveDoubleArray((Collection<Double>)value);
            }
        });
        answer.put(SFSDataType.FLOAT_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToPrimitiveFloatArray((Collection<Float>)value);
            }
        });
        answer.put(SFSDataType.INT_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToPrimitiveIntArray((Collection<Integer>)value);
            }
        });
        answer.put(SFSDataType.LONG_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToPrimitiveLongArray((Collection<Long>)value);
            }
        });
        answer.put(SFSDataType.SHORT_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToPrimitiveShortArray((Collection<Short>)value);
            }
        });
        answer.put(SFSDataType.UTF_STRING_ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return collectionToStringArray((Collection<String>)value);
            }
        });
        //====================================================
        
        answer.put(SFSDataType.NULL, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        
        return answer;
    }
}
