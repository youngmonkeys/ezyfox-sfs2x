package com.tvd12.ezyfox.sfs2x.content.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;

/**
 * @author tavandung12
 * Created on Sep 7, 2016
 *
 */
public interface SmartFoxContext {

    void setApi(ISFSApi api);
    void setExtension(ISFSExtension extension);
    
}
