package com.tvd12.ezyfox.sfs2x.testing.context;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.sfs2x.extension.ZoneExtension;

@AppContextConfiguration(clazz = AppConfig.class)
public class AppEntryPoint extends ZoneExtension {

    public AppEntryPoint() {
    }
    
}
