package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.api.ISFSBuddyApi;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.command.impl.RemoveBuddyImpl;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class RemoveBuddyImplTest extends BaseCommandTest2 {
    
    @Test
    public void test() throws Exception {
        initEnviroment();
        RemoveBuddyImpl command = new RemoveBuddyImpl(context, api, extension);
        command.buddy("buddy")
            .owner(user)
            .owner(USER_NAME)
            .fireClientEvent(true)
            .fireServerEvent(true)
            .zone(apiZone)
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

}
