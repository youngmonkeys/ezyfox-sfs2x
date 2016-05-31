package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.api.ISFSBuddyApi;
import com.smartfoxserver.v2.entities.User;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.InitBuddyListImpl;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class InitBuddyListImplTest extends BaseCommandTest2 {

    @Test
    public void test() throws Exception {
        initEnviroment();
        InitBuddyListImpl command = new InitBuddyListImpl(context, api, extension);
        command.fireServerEvent(true)
            .user(user)
            .user(USER_NAME)
            .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws Exception {
        initEnviroment2();
        InitBuddyListImpl command = new InitBuddyListImpl(context, api, extension);
        command.fireServerEvent(true)
            .user(user)
            .user(USER_NAME)
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
    
    public void initEnviroment2() throws Exception {
        APIManager apiManager = new APIManager();
        Field apiManagerField = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        apiManagerField.setAccessible(true);
        apiManagerField.set(SmartFoxServer.getInstance(), apiManager);
        ISFSBuddyApi buddyApi = mock(ISFSBuddyApi.class);
        doThrow(IOException.class).when(buddyApi).initBuddyList(any(User.class), any(boolean.class));
        Field buddyApiField = ReflectFieldUtil.getField("buddyApi", APIManager.class);
        buddyApiField.setAccessible(true);
        buddyApiField.set(apiManager, buddyApi);
    }
    

}
