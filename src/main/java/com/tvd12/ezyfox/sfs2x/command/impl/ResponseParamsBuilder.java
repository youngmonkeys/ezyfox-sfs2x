package com.tvd12.ezyfox.sfs2x.command.impl;

import java.util.List;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.sfs2x.data.impl.ParamTransformer;

/**
 * @author tavandung12
 * Created on Sep 5, 2016
 *
 */
public class ResponseParamsBuilder {
    
    private Object data;
    private List<String> includedVars;
    private List<String> excludedVars;
    private Map<String, Object> addition;
    private ParamTransformer transformer;
    
    public static ResponseParamsBuilder create() {
        return new ResponseParamsBuilder();
    }
    
    public ISFSObject build() {
        ISFSObject answer = createFromData();
        addAdditionParams(answer);
        checkAllExcluedVars(answer);
        removeExcludedParams(answer);
        return answer;
    }
    
    private void addAdditionParams(ISFSObject answer) {
        for(String key : addition.keySet())
            answer.put(key, transformer.transform(addition.get(key)));
    }
    
    private ISFSObject createFromData() {
        return data != null 
                ? (ISFSObject) transformer.transform(data).getObject()
                : new SFSObject();
    }
    
    private void checkAllExcluedVars(ISFSObject answer) {
        if(includedVars.size() == 0) return;
        excludedVars.addAll(answer.getKeys());
        excludedVars.removeAll(includedVars);
    }
    
    private void removeExcludedParams(ISFSObject answer) {
        for(String p : excludedVars) answer.removeElement(p);
    }

    public ResponseParamsBuilder data(Object data) {
        this.data = data; return this;
    }
    public ResponseParamsBuilder includedVars(List<String> includedVars) {
        this.includedVars = includedVars; return this;
    }
    public ResponseParamsBuilder excludedVars(List<String> excludedVars) {
        this.excludedVars = excludedVars; return this;
    }
    public ResponseParamsBuilder addition(Map<String, Object> addition) {
        this.addition = addition; return this;
    }
    public ResponseParamsBuilder transformer(ParamTransformer transformer) {
        this.transformer = transformer; return this;
    }
}
