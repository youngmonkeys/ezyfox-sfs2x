package com.tvd12.ezyfox.sfs2x.serializer;

/**
 * Support to deserialize a SFSObject to a java object
 * 
 * @author tavandung12
 * Created on Jun 1, 2016
 *
 */
public final class RequestParamDeserializer extends ParameterDeserializer {
	
	private static RequestParamDeserializer instance;
	
	private RequestParamDeserializer() {}
    
    public static RequestParamDeserializer getInstance() {
        if(instance == null)
            instance = new RequestParamDeserializer();
        return instance;
    }
    
    public static RequestParamDeserializer requestParamDeserializer() {
        return getInstance();
    }
}
