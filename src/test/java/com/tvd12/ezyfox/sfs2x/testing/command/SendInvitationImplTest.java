package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.APIManager;
import com.smartfoxserver.v2.api.ISFSGameApi;
import com.smartfoxserver.v2.api.SFSGameApi;
import com.smartfoxserver.v2.api.response.ISFSGameResponseApi;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.invitation.Invitation;
import com.smartfoxserver.v2.entities.invitation.InvitationManager;
import com.smartfoxserver.v2.entities.invitation.SFSInvitationManager;
import com.tvd12.ezyfox.core.command.SendInvitation.Callback;
import com.tvd12.ezyfox.core.entities.ApiInvitation;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.sfs2x.command.impl.SendInvitationImpl;

/**
 * @author tavandung12
 * Created on Jul 2, 2016
 *
 */
public class SendInvitationImplTest extends BaseCommandTest2 {

    @Test
    public void test() throws Exception {
        setupEnviroment();
        when(sfsUser.toSFSArray()).thenReturn(new SFSArray());
        SendInvitationImpl cmd = new SendInvitationImpl(context, api, extension);
        cmd.callback(new InvCallback())
        .expirySeconds(10)
        .invitees(user)
        .invitees(Lists.newArrayList(user))
        .inviter(user)
        .param("hello", "world")
        .execute();
        
    }
    
    private void setupEnviroment() throws Exception {
        APIManager apiManager = new APIManager();
        ISFSGameApi gameApi = new SFSGameApi(SmartFoxServer.getInstance());
        Field gameApiField = ReflectFieldUtil.getField("gameApi", APIManager.class);
        gameApiField.setAccessible(true);
        gameApiField.set(apiManager, gameApi);
        
        ISFSGameResponseApi gameResponseApi = mock(ISFSGameResponseApi.class);
        Field responseApiField = ReflectFieldUtil.getField("responseApi", SFSGameApi.class);
        responseApiField.setAccessible(true);
        responseApiField.set(gameApi, gameResponseApi);
        
        Field apiManagerField = ReflectFieldUtil.getField("apiManager", SmartFoxServer.class);
        apiManagerField.setAccessible(true);
        apiManagerField.set(SmartFoxServer.getInstance(), apiManager);
        
        Field invitationManagerField = ReflectFieldUtil.getField("invitationManager", SmartFoxServer.class);
        invitationManagerField.setAccessible(true);
        InvitationManager invitationManager = new SFSInvitationManager();
        invitationManagerField.set(SmartFoxServer.getInstance(), invitationManager);
        
        doAnswer(new Answer<Void>() {
            @Override 
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Invitation invitation = (Invitation) invocation.getArguments()[0];
                invitation.getCallback().onAccepted(invitation, new SFSObject());
                invitation.getCallback().onExpired(invitation);
                invitation.getCallback().onRefused(invitation, new SFSObject());
                return null;
            }       
        }).when(gameResponseApi).notifyInivitation(any(Invitation.class));
        
    }
    
    public static class InvCallback implements Callback {
        @Override
        public void onRefused(ApiInvitation invitation, Parameters params) {
            
        }
        
        @Override
        public void onExpired(ApiInvitation invitation) {
            
        }
        
        @Override
        public void onAccepted(ApiInvitation invitation, Parameters params) {
            
        }
    }
    
}
