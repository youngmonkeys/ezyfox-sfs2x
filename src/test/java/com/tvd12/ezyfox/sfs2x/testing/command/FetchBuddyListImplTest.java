package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.buddylist.BuddyList;
import com.smartfoxserver.v2.buddylist.BuddyListManager;
import com.tvd12.ezyfox.core.entities.ApiBuddy;
import com.tvd12.ezyfox.sfs2x.command.impl.FetchBuddyListImpl;
import com.tvd12.ezyfox.sfs2x.entities.impl.ApiBuddyImpl;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FetchBuddyListImplTest extends BaseCommandTest2 {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test() {
        when(extension.getParentZone()).thenReturn(zone);
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        when(buddyList.getBuddies()).thenReturn((List)Lists.newArrayList(new ApiBuddyImpl("abc", true)));
        when(buddyListManager.getBuddyList(any(String.class))).thenReturn(buddyList);
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        FetchBuddyListImpl command = new FetchBuddyListImpl(context, api, extension);
        List<ApiBuddy> buddies = command.user(user)
            .user(USER_NAME).execute();
        assertEquals(buddies.size(), 1);
        
    }

}
