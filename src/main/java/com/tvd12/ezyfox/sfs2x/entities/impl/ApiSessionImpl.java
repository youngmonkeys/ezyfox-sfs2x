package com.tvd12.ezyfox.sfs2x.entities.impl;

import com.smartfoxserver.bitswarm.sessions.ISession;
import com.tvd12.ezyfox.core.entities.ApiSession;

/**
 * @author tavandung12
 * Created on Jul 9, 2016
 *
 */
public class ApiSessionImpl implements ApiSession {

    private ISession session;
    
    public ApiSessionImpl(ISession session) {
        this.session = session;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getId()
     */
    @Override
    public int getId() {
        return session.getId();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getHashId()
     */
    @Override
    public String getHashId() {
        return session.getHashId();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#setHashId(java.lang.String)
     */
    @Override
    public void setHashId(String hashId) {
        session.setHashId(hashId);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isLocal()
     */
    @Override
    public boolean isLocal() {
        return session.isLocal();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isLoggedIn()
     */
    @Override
    public boolean isLoggedIn() {
        return session.isLoggedIn();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getCreationTime()
     */
    @Override
    public long getCreationTime() {
        return session.getCreationTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isConnected()
     */
    @Override
    public boolean isConnected() {
        return session.isConnected();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getLastActivityTime()
     */
    @Override
    public long getLastActivityTime() {
        return session.getLastActivityTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getLastLoggedInActivityTime()
     */
    @Override
    public long getLastLoggedInActivityTime() {
        return session.getLastLoggedInActivityTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getLastReadTime()
     */
    @Override
    public long getLastReadTime() {
        return session.getLastReadTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getLastWriteTime()
     */
    @Override
    public long getLastWriteTime() {
        return session.getLastWriteTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getReadBytes()
     */
    @Override
    public long getReadBytes() {
        return session.getReadBytes();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getWrittenBytes()
     */
    @Override
    public long getWrittenBytes() {
        return session.getWrittenBytes();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getDroppedMessages()
     */
    @Override
    public int getDroppedMessages() {
        return session.getDroppedMessages();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getMaxIdleTime()
     */
    @Override
    public int getMaxIdleTime() {
        return session.getMaxIdleTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getMaxLoggedInIdleTime()
     */
    @Override
    public int getMaxLoggedInIdleTime() {
        return session.getMaxLoggedInIdleTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isMarkedForEviction()
     */
    @Override
    public boolean isMarkedForEviction() {
        return session.isMarkedForEviction();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isIdle()
     */
    @Override
    public boolean isIdle() {
        return session.isIdle();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isFrozen()
     */
    @Override
    public boolean isFrozen() {
        return session.isFrozen();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getFreezeTime()
     */
    @Override
    public long getFreezeTime() {
        return session.getFreezeTime();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isReconnectionTimeExpired()
     */
    @Override
    public boolean isReconnectionTimeExpired() {
        return session.isReconnectionTimeExpired();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getSystemProperty(java.lang.String)
     */
    @Override
    public Object getSystemProperty(String name) {
        return session.getSystemProperty(name);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#setSystemProperty(java.lang.String, java.lang.Object)
     */
    @Override
    public void setSystemProperty(String name, Object value) {
        session.setSystemProperty(name, value);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#removeSystemProperty(java.lang.String)
     */
    @Override
    public void removeSystemProperty(String name) {
        session.removeSystemProperty(name);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getProperty(java.lang.String)
     */
    @Override
    public Object getProperty(String name) {
        return session.getProperty(name);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#setProperty(java.lang.String, java.lang.Object)
     */
    @Override
    public void setProperty(String name, Object value) {
        session.setProperty(name, value);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#removeProperty(java.lang.String)
     */
    @Override
    public void removeProperty(String name) {
        session.removeProperty(name);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getFullIpAddress()
     */
    @Override
    public String getFullIpAddress() {
        return session.getFullIpAddress();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getAddress()
     */
    @Override
    public String getAddress() {
        return session.getAddress();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getClientPort()
     */
    @Override
    public int getClientPort() {
        return session.getClientPort();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getServerAddress()
     */
    @Override
    public String getServerAddress() {
        return session.getServerAddress();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getServerPort()
     */
    @Override
    public int getServerPort() {
        return session.getServerPort();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getFullServerIpAddress()
     */
    @Override
    public String getFullServerIpAddress() {
        return session.getFullServerIpAddress();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getReconnectionSeconds()
     */
    @Override
    public int getReconnectionSeconds() {
        return session.getReconnectionSeconds();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#isEncrypted()
     */
    @Override
    public boolean isEncrypted() {
        return session.isEncrypted();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getCryptoKey()
     */
    @Override
    public Object getCryptoKey() {
        return session.getCryptoKey();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#setCryptoKey(java.lang.Object)
     */
    @Override
    public void setCryptoKey(Object key) {
        session.setCryptoKey(key);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.command.Session#getNodeId()
     */
    @Override
    public String getNodeId() {
        return session.getNodeId();
    }
}
