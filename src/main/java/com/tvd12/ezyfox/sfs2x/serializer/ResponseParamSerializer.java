package com.tvd12.ezyfox.sfs2x.serializer;

/**
 * Support to serialize a response object to a SFSObject
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */

public final class ResponseParamSerializer extends ParameterSerializer {
	
	private static ResponseParamSerializer instance;
	
	private ResponseParamSerializer() {}
    
    public static ResponseParamSerializer getInstance() {
        if(instance == null)
            instance = new ResponseParamSerializer();
        return instance;
    }
    
    public static ResponseParamSerializer responseParamSerializer() {
        return getInstance();
    }
}