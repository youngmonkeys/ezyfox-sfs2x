package com.tvd12.ezyfox.sfs2x.content.impl;

import java.util.List;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.content.impl.BaseRoomContext;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */

public class RoomContextImpl extends BaseRoomContext implements SmartFoxContext {
    
    @Getter @Setter
    private Class<?> userClass;
    @Getter @Setter
    private List<Class<?>> roomClasses;
    @Getter @Setter
    private List<Class<?>> gameUserClasses;

    @Setter
    private ISFSApi api;
    @Setter
    private ISFSExtension extension;
    
}
