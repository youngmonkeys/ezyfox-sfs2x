package com.tvd12.ezyfox.sfs2x.testing.command;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.ApiModelFactory;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;
import com.tvd12.test.base.BaseTest;

public class BaseCommandTest extends BaseTest {

    protected AppContextImpl context 
        = new AppContextImpl(AppEntryPoint.class);
    protected ISFSApi api;
    protected ISFSExtension extension;

    public BaseCommandTest() {
    try {
        api = ApiModelFactory.createSFSApi();
        extension = ApiModelFactory.createExtension();
        
        context.setApi(api);
        context.setExtension(extension);
    } catch (SFSCreateRoomException e) {
        e.printStackTrace();
    }
   
}
    
}
