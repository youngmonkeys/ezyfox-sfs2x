package com.tvd12.ezyfox.sfs2x.serializer;

public final class ResponseParamParser extends ParameterUnwrapper {
	
	private static ResponseParamParser instance;
	
	private ResponseParamParser() {}
    
    public static ResponseParamParser getInstance() {
        if(instance == null)
            instance = new ResponseParamParser();
        return instance;
    }
    
    public static ResponseParamParser responseParamParser() {
        return getInstance();
    }
}