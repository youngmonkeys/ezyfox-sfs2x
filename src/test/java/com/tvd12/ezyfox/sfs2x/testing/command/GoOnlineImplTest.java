package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.api.ISFSBuddyApi;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSBuddyListException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.GoOnlineImpl;

import static org.mockito.Mockito.*;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class GoOnlineImplTest extends BaseCommandTest2 {

    @Test
    public void test() throws Exception {
        initEnviroment();
        GoOnlineImpl command = new GoOnlineImpl(context, api, extension);
        command.user(user)
        .user(USER_NAME)
        .fireServerEvent(true)
        .online(true)
        .execute();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void test2() throws Exception {
        initEnviroment2();
        GoOnlineImpl command = new GoOnlineImpl(context, api, extension);
        command.user(user)
        .user(USER_NAME)
        .fireServerEvent(true)
        .online(true)
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
        doNothing()
        .when(buddyApi)
        .goOnline(any(User.class), any(boolean.class), any(boolean.class));
    }
    
    public void initEnviroment2() throws Exception {
        APIManager apiManager = new APIManager();
        Field apiManagerField = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        apiManagerField.setAccessible(true);
        apiManagerField.set(SmartFoxServer.getInstance(), apiManager);
        ISFSBuddyApi buddyApi = mock(ISFSBuddyApi.class);
        Field buddyApiField = ReflectFieldUtil.getField("buddyApi", APIManager.class);
        buddyApiField.setAccessible(true);
        buddyApiField.set(apiManager, buddyApi);
        doThrow(SFSBuddyListException.class)
        .when(buddyApi)
        .goOnline(any(User.class), any(boolean.class), any(boolean.class));
    }
    
}
