package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.*;

import java.awt.List;
import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.api.ISFSBuddyApi;
import com.smartfoxserver.v2.buddylist.BuddyVariable;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.UpdateBuddyPropertiesImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class UpdateBuddyPropertiesImplTest extends BaseCommandTest2 {

    @Test
    public void test() throws Exception {
        initEnviroment();
        UpdateBuddyPropertiesImpl command = new UpdateBuddyPropertiesImpl(context, api, extension);
        AppUser owner = new AppUser();
        owner.setName(USER_NAME);
        command.owner(owner)
            .include("2", "3")
            .exclude("2", "3")
            .fireClientEvent(true)
            .fireServerEvent(true)
            .execute();
    }
    
    @Test
    public void test1() throws Exception {
        initEnviroment();
        UpdateBuddyPropertiesImpl command = new UpdateBuddyPropertiesImpl(context, api, extension);
        AppUser owner = new AppUser();
        owner.setName(USER_NAME);
        command.owner(owner)
            .fireClientEvent(true)
            .fireServerEvent(true)
            .execute();
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void test2() throws Exception {
        initEnviroment2();
        UpdateBuddyPropertiesImpl command = new UpdateBuddyPropertiesImpl(context, api, extension);
        AppUser owner = new AppUser();
        owner.setName(USER_NAME);
        command.owner(owner)
            .fireClientEvent(true)
            .fireServerEvent(true)
            .execute();
    }
    
    public void initEnviroment() throws Exception {
        APIManager apiManager = new APIManager();
        Field apiManagerField = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        apiManagerField.setAccessible(true);
        apiManagerField.set(SmartFoxServer.getInstance(), apiManager);
        ISFSBuddyApi buddyApi = mock(ISFSBuddyApi.class);
        Field buddyApiField = ReflectFieldUtil.getField("buddyApi", APIManager.class);
        buddyApiField.setAccessible(true);
        buddyApiField.set(apiManager, buddyApi);
    }
    
    @SuppressWarnings("unchecked")
    public void initEnviroment2() throws Exception {
        APIManager apiManager = new APIManager();
        Field apiManagerField = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        apiManagerField.setAccessible(true);
        apiManagerField.set(SmartFoxServer.getInstance(), apiManager);
        ISFSBuddyApi buddyApi = mock(ISFSBuddyApi.class);
        Field buddyApiField = ReflectFieldUtil.getField("buddyApi", APIManager.class);
        buddyApiField.setAccessible(true);
        buddyApiField.set(apiManager, buddyApi);
        doThrow(SFSBuddyListException.class).when(buddyApi)
            .setBuddyVariables(any(User.class), (java.util.List<BuddyVariable>) any(List.class), any(boolean.class), any(boolean.class));
    }
    

}
