package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.buddylist.BuddyList;
import com.smartfoxserver.v2.buddylist.BuddyListManager;
import com.tvd12.ezyfox.sfs2x.command.impl.FindBuddyImpl;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class FindBuddyImplTest extends BaseCommandTest2 {

    @Test
    public void test() {
        when(extension.getParentZone()).thenReturn(zone);
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        when(buddyListManager.getBuddyList(any(String.class))).thenReturn(buddyList);
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        FindBuddyImpl command = new FindBuddyImpl(context, api, extension);
        command.buddy("buddy")
            .owner(user)
            .owner(USER_NAME)
            .execute();
    }
    
}
