package com.tvd12.ezyfox.sfs2x.testing.model;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.ezyfox.sfs2x.model.impl.ApiBuddyMessageImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class ApiBuddyMessageImplTest {

    @Test
    public void test() {
        ApiBuddyMessageImpl impl = new ApiBuddyMessageImpl();
        impl.setContent("abc");
        impl.setRecipient(new AppUser());
        impl.setZone(mock(ApiZone.class));
        impl.setSender(new AppUser());
        
        assertEquals(impl.content(), "abc");
        assertNotNull(impl.recipient());
        assertNotNull(impl.zone());
        assertNotNull(impl.sender());
    }
    
}
