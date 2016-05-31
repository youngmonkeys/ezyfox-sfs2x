package com.tvd12.ezyfox.sfs2x.serializer;

import java.util.List;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.variables.Variable;
import com.tvd12.ezyfox.core.structure.AgentClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;

public class AgentWrapper extends ParameterWrapper {
    
    public <T extends Variable> Object wrap(AgentClassWrapper wrapper, 
            List<T> variables) {
        return wrap(wrapper, wrapper.newInstance(), variables);
    }
    
    protected <T extends Variable> Object wrap(AgentClassWrapper wrapper, 
            Object agent, List<T> variables) {
        for(T variable : variables) {
            SetterMethodCover method = wrapper.getMethod(variable.getName());
            if(method != null)
                method.invoke(agent, getValue(method, variable));
        }
        return agent;
    }
    
    protected Object getValue(SetterMethodCover method, Variable variable) {
        Object value = variable.getValue();
        if(method.isArray()) {
            value = assignValuesToArray(method, value);
        }
        else if(method.isColection()) {
            value = assignValuesToCollection(method, value);
        }
        else if(method.isObject()) {
            value = assignValuesToObject(method, (ISFSObject)value);
        }
        else if(method.isByte()) {
            value = variable.getIntValue().byteValue();
        }
        else if(method.isChar()) {
            value = (char)variable.getIntValue().byteValue();
        }
        else if(method.isFloat()) {
            value = variable.getDoubleValue().floatValue();
        }
        else if(method.isLong()) {
            value = variable.getDoubleValue().longValue();
        }
        else if(method.isShort()) {
            value = variable.getIntValue().shortValue();
        }
        return value;
    }
    
}
