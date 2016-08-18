package com.tvd12.ezyfox.sfs2x.content.impl;

import java.util.List;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
@Getter @Setter
public class RoomContextImpl extends AppContextImpl {
    
    private Class<?> userClass;
    
    private List<Class<?>> roomClasses;
    
    private List<Class<?>> gameUserClasses;
    
    private AppContextImpl appContext;

    /**
     * @param entryPoint
     */
    public RoomContextImpl(Class<?> entryPoint) {
        super(entryPoint);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#newExtensionConfiguration()
     */
    @Override
    protected ExtensionConfiguration newExtensionConfiguration() {
        return new RoomExtensionConfiguration();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#initCommands()
     */
    @Override
    protected void initCommands() {}
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#command(java.lang.Class)
     */
    @Override
    public <T> T command(Class<T> clazz) {
        return appContext.command(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public void set(Object key, Object value) {
        appContext.set(key, value);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#get(java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return appContext.get(key, clazz);
    }
}
