package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.sfs2x.extension.ZoneExtension;

@ContextConfiguration(clazz = AppConfig.class)
public class AppEntryPoint extends ZoneExtension {

    public AppEntryPoint() {
    }
    
}
