package com.tvd12.ezyfox.sfs2x.testing.clienthandler;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.BadRequestException;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;
import com.tvd12.ezyfox.sfs2x.testing.context.BettingRequestListener;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerUser;

public class ClientEventHandlerTest extends BaseHandlerTest {
    
    @Test(priority = 1)
    public void test() {
        context = newAppContext();
        ClientEventHandler handler = new ExClientEventHandler(context, "bet");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
        assertEquals(handler.getCommand(), "bet");
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        answer.addObjectDeserializer(BettingRequestListener.class, new ObjectDeserializer() {
            
            @SuppressWarnings("unchecked")
            @Override
            public BettingRequestListener deserialize(Object object, Parameters params) {
                BettingRequestListener b = (BettingRequestListener)object;
                return b;
            }
        });
        answer.addObjectSerializer(BettingRequestListener.class, new ObjectSerializer() {
            
            @Override
            public Parameters serialize(Object object) {
                BettingRequestListener b = (BettingRequestListener)object;
                Parameters answer = new ParameterWrapper();
                answer.set("id", b.getId());
                return answer;
            }
        });
        return answer;
    }
    
    @Test(expectedExceptions = {RuntimeException.class}, priority = 2)
    public void testInvalidCase() {
        context = newAppContext();
        context = spy(context);
        RequestResponseClass rclass = new RequestResponseClass();
        rclass.init(ClassA.class);
        rclass.checkExecuteMethod(AppUser.class, new HashSet<Class<?>>());
        when(context.getClientRequestListeners("abc")).thenReturn(
                Lists.newArrayList(rclass));
        ClientEventHandler handler = new ExClientEventHandler(context, "abc");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    @Test(priority = 3)
    public void test2() {
        context = newAppContext();
        context = spy(context);
        when(context.getClientRequestListeners("abc1")).thenReturn(new ArrayList<RequestResponseClass>());
        ClientEventHandler handler = new ExClientEventHandler(context, "abc1");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    @Test(priority = 4)
    public void test3() {
        context = newAppContext();
        context = spy(context);
        Set<Class<?>> gameUserClasses = new HashSet<>();
        gameUserClasses.add(PokerUser.class);
        RequestResponseClass rclass = new RequestResponseClass();
        rclass.init(ClassB.class);
        rclass.checkExecuteMethod(AppUser.class, gameUserClasses);
        when(context.getClientRequestListeners("xyz")).thenReturn(
                Lists.newArrayList(rclass));
        ClientEventHandler handler = new ExClientEventHandler(context, "xyz");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    @Test(priority = 5)
    public void test4() {
        context = newAppContext();
        context = spy(context);
        Set<Class<?>> gameUserClasses = new HashSet<>();
        gameUserClasses.add(PokerUser.class);
        RequestResponseClass rclass = new RequestResponseClass();
        rclass.init(ClassC.class);
        rclass.checkExecuteMethod(AppUser.class, gameUserClasses);
        when(context.getClientRequestListeners("xyz")).thenReturn(
                Lists.newArrayList(rclass));
        ClientEventHandler handler = new ExClientEventHandler(context, "xyz");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    public static class ExClientEventHandler extends ClientEventHandler {

        public ExClientEventHandler(AppContextImpl context, String command) {
            super(context, command);
        }
        
        @Override
        protected void send(String cmdName, ISFSObject params, User recipient) {
        }
        
    }
    
    @ClientResponseHandler
    @ClientRequestListener(command = "abc")
    public static class ClassA {
        public void execute(AppContext context, AppUser user) {
            throw new NullPointerException();
        }
    }
    
    @ClientRequestListener(command = "xyz")
    public static class ClassB {
        public void execute(AppContext context, PokerUser user) {
        }
    }
    
    @ClientResponseHandler
    @ClientRequestListener(command = "xyz")
    public static class ClassC {
        public void execute(AppContext context, PokerUser user) throws Exception {
            throw new BadRequestException();
        }
    }
    
}
