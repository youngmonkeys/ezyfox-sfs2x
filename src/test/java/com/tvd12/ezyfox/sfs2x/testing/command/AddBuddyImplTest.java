package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.api.ISFSBuddyResponseApi;
import com.smartfoxserver.v2.api.SFSBuddyApi;
import com.smartfoxserver.v2.api.response.ISFSResponseApi;
import com.smartfoxserver.v2.buddylist.Buddy;
import com.smartfoxserver.v2.buddylist.BuddyList;
import com.smartfoxserver.v2.buddylist.BuddyListManager;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.AddBuddyImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class AddBuddyImplTest extends BaseCommandTest2 {
    
    @Test
    public void test() throws Exception {
        initEnviroment();
        AddBuddyImpl command = new AddBuddyImpl(context, api, extension);
        User sfsTargetUser = mock(User.class);
        ApiUser tartgetUser = new AppUser();
        tartgetUser.setName("buddy");
        when(sfsTargetUser.getProperty(APIKey.USER)).thenReturn(tartgetUser);
        when(api.getUserByName("buddy")).thenReturn(sfsTargetUser);
        when(sfsUser.getZone()).thenReturn(zone);
        
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        when(buddyListManager.getBuddyList(USER_NAME)).thenReturn(buddyList);
        when(buddyListManager.isActive()).thenReturn(true);
        
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        command.buddy("buddy")
            .fireClientEvent(true)
            .fireServerEvent(true)
            .owner(user)
            .owner(user.getName())
            .temp(false)
            .zone(apiZone)
            .execute();
    }
    
    @Test
    public void test2() throws Exception {
        initEnviroment();
        AddBuddyImpl command = new AddBuddyImpl(context, api, extension);
        User sfsTargetUser = mock(User.class);
        ApiUser tartgetUser = new AppUser();
        tartgetUser.setName("buddy1");
        when(sfsTargetUser.getProperty(APIKey.USER)).thenReturn(tartgetUser);
        when(api.getUserByName("buddy")).thenReturn(null);
        when(sfsUser.getZone()).thenReturn(zone);
        
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        when(buddyListManager.getBuddyList(USER_NAME)).thenReturn(buddyList);
        when(buddyListManager.isActive()).thenReturn(true);
        
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        command.buddy("buddy")
            .fireClientEvent(false)
            .fireServerEvent(false)
            .owner(user)
            .owner(user.getName())
            .temp(false)
            .zone(apiZone)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test3() throws Exception {
        initEnviroment();
        AddBuddyImpl command = new AddBuddyImpl(context, api, extension);
        User sfsTargetUser = mock(User.class);
        ApiUser tartgetUser = new AppUser();
        tartgetUser.setName("buddy");
        when(sfsTargetUser.getProperty(APIKey.USER)).thenReturn(tartgetUser);
        when(api.getUserByName("buddy")).thenReturn(null);
        when(sfsUser.getZone()).thenReturn(zone);
        
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        when(buddyListManager.getBuddyList(USER_NAME)).thenReturn(buddyList);
        when(buddyListManager.isActive()).thenReturn(false);
        
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        command.buddy("buddy")
            .fireClientEvent(false)
            .fireServerEvent(false)
            .owner(user)
            .owner(user.getName())
            .temp(false)
            .zone(apiZone)
            .execute();
    }
    
    @Test
    public void test4() throws Exception {
        initEnviroment();
        AddBuddyImpl command = new AddBuddyImpl(context, api, extension);
        User sfsTargetUser = mock(User.class);
        ApiUser tartgetUser = new AppUser();
        tartgetUser.setName("buddy1");
        when(sfsTargetUser.getProperty(APIKey.USER)).thenReturn(tartgetUser);
        when(api.getUserByName("buddy")).thenReturn(null);
        when(sfsUser.getZone()).thenReturn(zone);
        
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        doThrow(SFSBuddyListException.class).when(buddyList).addBuddy(any(Buddy.class));
        when(buddyListManager.getBuddyList(USER_NAME)).thenReturn(buddyList);
        when(buddyListManager.isActive()).thenReturn(true);
        
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        
        ISFSResponseApi responseApi = mock(ISFSResponseApi.class);
        when(api.getResponseAPI()).thenReturn(responseApi);
        
        command.buddy("buddy")
            .fireClientEvent(false)
            .fireServerEvent(false)
            .owner(user)
            .owner(user.getName())
            .temp(false)
            .zone(apiZone)
            .execute();
    }
    
    @Test
    public void test5() throws Exception {
        initEnviroment();
        AddBuddyImpl command = new AddBuddyImpl(context, api, extension);
        User sfsTargetUser = mock(User.class);
        ApiUser tartgetUser = new AppUser();
        tartgetUser.setName("buddy1");
        when(sfsTargetUser.getProperty(APIKey.USER)).thenReturn(tartgetUser);
        when(api.getUserByName("buddy")).thenReturn(null);
        when(sfsUser.getZone()).thenReturn(zone);
        
        BuddyListManager buddyListManager = mock(BuddyListManager.class);
        BuddyList buddyList = mock(BuddyList.class);
        doThrow(SFSBuddyListException.class).when(buddyList).addBuddy(any(Buddy.class));
        when(buddyListManager.getBuddyList(USER_NAME)).thenReturn(buddyList);
        when(buddyListManager.isActive()).thenReturn(true);
        
        when(zone.getBuddyListManager()).thenReturn(buddyListManager);
        
        ISFSResponseApi responseApi = mock(ISFSResponseApi.class);
        when(api.getResponseAPI()).thenReturn(responseApi);
        
        command.buddy("buddy")
            .fireClientEvent(true)
            .fireServerEvent(true)
            .owner(user)
            .owner(user.getName())
            .temp(false)
            .zone(apiZone)
            .execute();
    }
    
    public void initEnviroment() throws Exception {
        APIManager apiManager = new APIManager();
        Field apiManagerField = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        apiManagerField.setAccessible(true);
        apiManagerField.set(SmartFoxServer.getInstance(), apiManager);
        apiManager.init(null);
        ISFSBuddyResponseApi responseApi = mock(ISFSBuddyResponseApi.class);
        Field responseApiField = ReflectFieldUtil.getField("responseAPI", SFSBuddyApi.class);
        responseApiField.setAccessible(true);
        responseApiField.set(apiManager.getBuddyApi(), responseApi);
    }
    

}
