package com.tvd12.ezyfox.sfs2x.content.impl;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.content.impl.BaseRoomContext;

import lombok.Setter;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */

public class RoomContextImpl extends BaseRoomContext implements SmartFoxContext {
    
    @Setter
    private ISFSApi api;
    @Setter
    private ISFSExtension extension;
    
}
