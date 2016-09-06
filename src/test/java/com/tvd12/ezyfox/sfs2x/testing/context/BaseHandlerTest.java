package com.tvd12.ezyfox.sfs2x.testing.context;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Properties;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.tvd12.ezyfox.core.constants.APIKey;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.factory.UserAgentFactory;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.test.base.BaseTest;

public class BaseHandlerTest extends BaseTest {

    protected User sfsUser;
    protected SFSExtension sfsExtension;
    
    protected AppContextImpl context;
    
    protected ApiUser apiUser;
    
    public BaseHandlerTest() {
        context = newAppContext();
        apiUser = UserAgentFactory.newUserAgent("player", 
                context.getUserAgentClass(), context.getGameUserAgentClasses().values());
        sfsUser = mock(User.class);
        when(sfsUser.getProperty(APIKey.USER)).thenReturn(apiUser);
        sfsExtension = mock(SFSExtension.class);
        Properties properties = new Properties();
        properties.put("hello", "world");
        when(sfsExtension.getConfigProperties()).thenReturn(properties);
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
}
