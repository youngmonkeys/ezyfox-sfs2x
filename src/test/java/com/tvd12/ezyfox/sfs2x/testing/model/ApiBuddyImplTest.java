package com.tvd12.ezyfox.sfs2x.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.sfs2x.model.impl.ApiBuddyImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class ApiBuddyImplTest {
    
    @Test
    public void test() {
        ApiBuddyImpl buddy = new ApiBuddyImpl("dungtv", false);
        assertFalse(buddy.isTemp());
        buddy.setTemp(true);
        assertTrue(buddy.isTemp());
        buddy.setProperty("1", "2");
        assertEquals(buddy.getProperty("1", String.class), "2");
        buddy.removeProperty("1");
        assertNull(buddy.getProperty("1"));
        buddy.setOwner(new AppUser());
        buddy.setUser(new AppUser());
        assertNotNull(buddy.getOwner());
        assertNotNull(buddy.getUser());
    }

}
