package com.tvd12.ezyfox.sfs2x.content.impl;

import java.util.List;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;

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

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#newExtensionConfiguration()
     */
    @Override
    protected ExtensionConfiguration newExtensionConfiguration() {
        return new RoomExtensionConfiguration();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#initObjectSerializerMapper()
     */
    @Override
    protected void initObjectSerializerMapper() {
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#initObjectDeserializerMapper()
     */
    @Override
    protected void initObjectDeserializerMapper() {
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#initAppCommands()
     */
    @Override
    protected void initAppCommands() {
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#initFixedCommands()
     */
    @Override
    protected void initFixedCommands() {
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#addFixedCommand(java.lang.Class, java.lang.Class)
     */
    @Override
    protected void addFixedCommand(Class<?> base, Class<?> impl) {
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl#initCommands()
     */
    @Override
    protected void initCommands() {}
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#addObjectDeserializer(java.lang.Class, com.tvd12.ezyfox.core.serialize.ObjectDeserializer)
     */
    @Override
    public void addObjectDeserializer(Class<?> clazz, ObjectDeserializer deserializer) {
        appContext.addObjectDeserializer(clazz, deserializer);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#addObjectSerializer(java.lang.Class, com.tvd12.ezyfox.core.serialize.ObjectSerializer)
     */
    @Override
    public void addObjectSerializer(Class<?> clazz, ObjectSerializer serializer) {
        appContext.addObjectSerializer(clazz, serializer);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getObjectDeserializer(java.lang.Class)
     */
    @Override
    public ObjectDeserializer getObjectDeserializer(Class<?> clazz) {
        return appContext.getObjectDeserializer(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseAppContext#getObjectSerializer(java.lang.Class)
     */
    @Override
    public ObjectSerializer getObjectSerializer(Class<?> clazz) {
        return appContext.getObjectSerializer(clazz);
    }
    
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
