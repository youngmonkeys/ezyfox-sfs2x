package com.tvd12.ezyfox.sfs2x.testing.command;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.util.IDisconnectionReason;
import com.tvd12.ezyfox.sfs2x.command.impl.DisconnectUserImpl;

import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author tavandung12
 * Created on May 31, 2016
 *
 */
public class DisconnectUserImplTest extends BaseCommandTest2 {
    
    @Test
    public void test() {
        setupEnviroment();
        DisconnectUserImpl command = new DisconnectUserImpl(context, api, extension);
        command.user(user)
            .user(USER_NAME)
            .reasonId((byte)1)
            .execute();
    }
    
    @Test
    public void test2() {
        setupEnviroment();
        DisconnectUserImpl command = new DisconnectUserImpl(context, api, extension);
        command.user(user)
            .user(USER_NAME)
            .reasonId((byte)-1)
            .execute();
    }
    
    public void setupEnviroment() {
        doAnswer(new Answer<Void>() {
            /* (non-Javadoc)
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                IDisconnectionReason reason = (IDisconnectionReason)invocation.getArguments()[1];
                reason.getByteValue();
                return null;
            }
        }).when(api).disconnectUser(any(User.class), any(IDisconnectionReason.class));
            
    }

}
