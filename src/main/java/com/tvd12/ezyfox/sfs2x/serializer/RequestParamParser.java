package com.tvd12.ezyfox.sfs2x.serializer;

public final class RequestParamParser extends ParameterWrapper {
	
	private static RequestParamParser instance;
	
	private RequestParamParser() {}
    
    public static RequestParamParser getInstance() {
        if(instance == null)
            instance = new RequestParamParser();
        return instance;
    }
    
    public static RequestParamParser requestParamParser() {
        return getInstance();
    }
}
