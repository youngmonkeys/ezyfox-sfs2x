package com.tvd12.ezyfox.sfs2x.testing.model;

import com.smartfoxserver.bitswarm.sessions.ISession;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiSessionImpl;
import com.tvd12.test.base.BaseTest;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

/**
 * @author tavandung12
 * Created on Jul 9, 2016
 *
 */
public class ApiSessionImplTest extends BaseTest {

    @Test
    public void test() {
        ISession ss = mock(ISession.class);
        ApiSessionImpl session = new ApiSessionImpl(ss);

        when(ss.getId()).thenReturn(1);
        assertEquals(session.getId(), 1);
        
        when(ss.getHashId()).thenReturn("123");
        assertEquals(session.getHashId(), "123");
        
        session.setHashId("123");

        when(ss.isLocal()).thenReturn(true);
        assertEquals(session.isLocal(), true);

        when(ss.isLoggedIn()).thenReturn(true);
        assertEquals(session.isLoggedIn(), true);
        
        when(ss.getCreationTime()).thenReturn(2L);
        assertEquals(session.getCreationTime(), 2L);

        when(ss.isConnected()).thenReturn(true);
        assertEquals(session.isConnected(), true);
        
        when(ss.getLastActivityTime()).thenReturn(3L);
        assertEquals(session.getLastActivityTime(), 3L);

        when(ss.getLastLoggedInActivityTime()).thenReturn(4L);
        assertEquals(session.getLastLoggedInActivityTime(), 4L);

        when(ss.getLastReadTime()).thenReturn(5L);
        assertEquals(session.getLastReadTime(), 5L);
        
        when(ss.getLastWriteTime()).thenReturn(6L);
        assertEquals(session.getLastWriteTime(), 6L);

        when(ss.getReadBytes()).thenReturn(7L);
        assertEquals(session.getReadBytes(), 7L);
        
        when(ss.getWrittenBytes()).thenReturn(8L);
        assertEquals(session.getWrittenBytes(), 8L);

        when(ss.getDroppedMessages()).thenReturn(9);
        assertEquals(session.getDroppedMessages(), 9);
        
        when(ss.getMaxIdleTime()).thenReturn(10);
        assertEquals(session.getMaxIdleTime(), 10);

        when(ss.getMaxLoggedInIdleTime()).thenReturn(11);
        assertEquals(session.getMaxLoggedInIdleTime(), 11);

        when(ss.isMarkedForEviction()).thenReturn(true);
        assertEquals(session.isMarkedForEviction(), true);

        when(ss.isIdle()).thenReturn(true);
        assertEquals(session.isIdle(), true);

        when(ss.isFrozen()).thenReturn(true);
        assertEquals(session.isFrozen(), true);

        when(ss.getFreezeTime()).thenReturn(12L);
        assertEquals(session.getFreezeTime(), 12L);

        when(ss.isReconnectionTimeExpired()).thenReturn(true);
        assertTrue(session.isReconnectionTimeExpired());

        when(ss.getSystemProperty("1")).thenReturn("2");
        assertEquals(session.getSystemProperty("1"), "2");
        
        session.setSystemProperty("1", "2");
        
        session.removeSystemProperty("1");

        when(ss.getProperty("2")).thenReturn("3");
        assertEquals(session.getProperty("2"), "3");
        
        session.setProperty("2", "3");
        session.removeProperty("2");

        when(ss.getFullIpAddress()).thenReturn("1.2.3.4:1122");
        assertEquals(session.getFullIpAddress(), "1.2.3.4:1122");

        when(ss.getAddress()).thenReturn("1.2.3.4");
        assertEquals(session.getAddress(), "1.2.3.4");

        when(ss.getClientPort()).thenReturn(1122);
        assertEquals(session.getClientPort(), 1122);

        when(ss.getServerAddress()).thenReturn("5.6.7.8");
        assertEquals(session.getServerAddress(), "5.6.7.8");

        when(ss.getServerPort()).thenReturn(9933);
        assertEquals(session.getServerPort(), 9933);

        when(ss.getFullServerIpAddress()).thenReturn("5.6.7.8:9933");
        assertEquals(session.getFullServerIpAddress(), "5.6.7.8:9933");

        when(ss.getReconnectionSeconds()).thenReturn(13);
        assertEquals(session.getReconnectionSeconds(), 13);

        when(ss.isEncrypted()).thenReturn(true);
        assertEquals(session.isEncrypted(), true);
        
        when(ss.getCryptoKey()).thenReturn("abc");
        assertEquals(session.getCryptoKey(), "abc");
        
        session.setCryptoKey("abc");
        
        when(ss.getNodeId()).thenReturn("zzz");
        assertEquals(session.getNodeId(), "zzz");
    }    
}
