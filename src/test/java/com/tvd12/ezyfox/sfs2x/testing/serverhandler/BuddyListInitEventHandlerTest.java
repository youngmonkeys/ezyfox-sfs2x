package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.buddylist.BuddyList;
import com.smartfoxserver.v2.buddylist.BuddyListManager;
import com.smartfoxserver.v2.buddylist.BuddyProperties;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.tvd12.ezyfox.core.config.APIKey;
import com.tvd12.ezyfox.core.model.ApiBuddyProperties;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.sfs2x.serverhandler.BuddyListInitEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class BuddyListInitEventHandlerTest extends BaseZoneHandlerTest {

    @Test
    public void test() throws SFSException {
        BuddyListInitEventHandler handler = new BuddyListInitEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        AppUser user = new AppUser();
        user.setBuddyProperties(new ApiBuddyProperties());
        BuddyProperties bdp = mock(BuddyProperties.class);
        when(sfsUser.getBuddyProperties()).thenReturn(bdp);
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(user);
        
        when(sfsUser.getZone()).thenReturn(sfsZone);
        BuddyListManager bdm = mock(BuddyListManager.class);
        BuddyList bdl = mock(BuddyList.class);
        when(bdm.getBuddyList(sfsUser.getName())).thenReturn(bdl);
        when(sfsZone.getBuddyListManager()).thenReturn(bdm);
        
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        handler.handleServerEvent(event);
    }
    
    @Test
    public void test2() throws SFSException {
        User sfsUser1 = mock(User.class);
        when(sfsUser1.getName()).thenReturn("1");
        when(sfsUser1.getProperty(APIKey.USER)).thenReturn(null);
        BuddyListInitEventHandler handler = new BuddyListInitEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(sfsUser1.getProperty(APIKey.USER)).thenReturn(new AppUser());
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser1);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        
        when(sfsUser1.getZone()).thenReturn(sfsZone);
        BuddyListManager bdm = mock(BuddyListManager.class);
        BuddyList bdl = mock(BuddyList.class);
        when(bdm.getBuddyList(sfsUser1.getName())).thenReturn(bdl);
        when(sfsZone.getBuddyListManager()).thenReturn(bdm);
        
        handler.handleServerEvent(event);
    }
    
    @Test
    public void test3() throws SFSException {
        User sfsUser1 = mock(User.class);
        when(sfsUser1.getName()).thenReturn("2");
        BuddyProperties sfsProperties = mock(BuddyProperties.class);
        ApiUser user = new AppUser();
        user.setBuddyProperties(new ApiBuddyProperties());
        when(sfsUser1.getProperty(APIKey.USER)).thenReturn(user);
        when(sfsUser1.getBuddyProperties()).thenReturn(sfsProperties);
        BuddyListInitEventHandler handler = new BuddyListInitEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(sfsUser1.getProperty(APIKey.USER)).thenReturn(new AppUser());
        when(event.getParameter(SFSEventParam.USER)).thenReturn(sfsUser1);
        when(event.getParameter(SFSEventParam.ZONE)).thenReturn(sfsZone);
        
        when(sfsUser1.getZone()).thenReturn(sfsZone);
        BuddyListManager bdm = mock(BuddyListManager.class);
        BuddyList bdl = mock(BuddyList.class);
        when(bdm.getBuddyList(sfsUser1.getName())).thenReturn(bdl);
        when(sfsZone.getBuddyListManager()).thenReturn(bdm);
        
        handler.handleServerEvent(event);
    }
    
}
