package com.tvd12.ezyfox.sfs2x.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.variables.Variable;
import com.smartfoxserver.v2.entities.variables.VariableType;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.sfs2x.data.SfsTransformer;

/**
 * @author tavandung12
 * Created on Sep 5, 2016
 *
 */
public class SfsVariableTransformer {
    
    private SfsObjectTransformer objectTransformer;
    private Map<VariableType, SfsTransformer> transformers;
    
    public SfsVariableTransformer() {
        this(null);
    }
    
    public SfsVariableTransformer(BaseAppContext context) {
        init(context);
    }
    
    protected void init(BaseAppContext context) {
        transformers = defaultTransformers();
        objectTransformer = new SfsObjectTransformer();
        
    }
    
    @SuppressWarnings("unchecked")
    public Parameters transform(Object value) {
        Parameters answer = new ParameterWrapper();
        for(Variable var : (List<Variable>)value)
            answer.set(var.getName(), transformVariable(var));
        return answer;
    }
    
    protected Object transformVariable(Variable variable) {
        return transformVariableValue(variable);
    }
    
    protected Object transformVariableValue(Variable variable) {
        return transformers.get(variable.getType()).transform(variable.getValue());
    }

    private Map<VariableType, SfsTransformer> defaultTransformers() {
        Map<VariableType, SfsTransformer> answer = new HashMap<>();
        answer.put(VariableType.NULL, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(VariableType.BOOL, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(VariableType.DOUBLE, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(VariableType.INT, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(VariableType.STRING, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return value;
            }
        });
        answer.put(VariableType.ARRAY, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return objectTransformer.transformArray((ISFSArray)value);
            }
        });
        answer.put(VariableType.OBJECT, new SfsTransformer() {
            @Override
            public Object transform(Object value) {
                return objectTransformer.transformObject((ISFSObject)value);
            }
        });
        return answer;
    }
    
}
