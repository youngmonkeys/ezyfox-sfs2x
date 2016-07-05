package com.tvd12.ezyfox.sfs2x.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiPrivateMessageImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerRoom;

import static org.testng.Assert.*;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class ApiPrivateMessageImplTest {

    @Test
    public void test() {
        ApiPrivateMessageImpl impl = new ApiPrivateMessageImpl();
        impl.setContent("abc");
        impl.setRecipient(new AppUser());
        impl.setRoom(new PokerRoom());
        impl.setZone(mock(ApiZone.class));
        impl.setSender(new AppUser());
        
        assertEquals(impl.content(), "abc");
        assertNotNull(impl.recipient());
        assertNotNull(impl.room());
        assertNotNull(impl.zone());
        assertNotNull(impl.sender());
    }
    

}
