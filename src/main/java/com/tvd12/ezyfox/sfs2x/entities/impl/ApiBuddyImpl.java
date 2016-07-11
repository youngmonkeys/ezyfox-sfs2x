/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.entities.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smartfoxserver.v2.buddylist.SFSBuddy;
import com.tvd12.ezyfox.core.entities.ApiBuddy;
import com.tvd12.ezyfox.core.entities.ApiUser;

import lombok.Getter;
import lombok.Setter;

/**
 * @see SFSBuddy
 * @see ApiBuddy
 * 
 * @author tavandung12
 *
 */
public class ApiBuddyImpl extends SFSBuddy implements ApiBuddy {
    
    @Getter @Setter
    private ApiUser user;
    
    @Getter @Setter
    private ApiUser owner;
    
    private Map<Object, Object> properties 
            = new ConcurrentHashMap<>();
    
    public ApiBuddyImpl(String name, boolean temp) {
        super(name, temp);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiBuddy#setTemp(boolean)
     */
    @Override
    public void setTemp(boolean temp) {
        setIsTemp(temp);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiProperties#setProperty(java.lang.Object, java.lang.Object)
     */
    @Override
    public void setProperty(Object key, Object value) {
        properties.put(key, value);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiProperties#getProperty(java.lang.Object)
     */
    @Override
    public Object getProperty(Object key) {
        return properties.get(key);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiProperties#getProperty(java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T getProperty(Object key, Class<T> clazz) {
        return clazz.cast(getProperty(key));
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiProperties#removeProperty(java.lang.Object)
     */
    @Override
    public void removeProperty(Object key) {
        properties.remove(key);
    }
}
