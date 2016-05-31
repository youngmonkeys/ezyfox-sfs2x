package com.tvd12.ezyfox.sfs2x.testing.clienthandler;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.sfs2x.clienthandler.ClientEventHandler;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;
import com.tvd12.ezyfox.sfs2x.testing.context.AppUser;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;
import com.tvd12.ezyfox.sfs2x.testing.context.PokerUser;

public class ClientEventHandlerTest extends BaseHandlerTest {
    
    @Test(priority = 1)
    public void test() {
        context = new AppContextImpl(AppEntryPoint.class);
        ClientEventHandler handler = new ExClientEventHandler(context, "bet");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
        assertEquals(handler.getCommand(), "bet");
    }
    
    @Test(expectedExceptions = {RuntimeException.class}, priority = 2)
    public void testInvalidCase() {
        context = new AppContextImpl(AppEntryPoint.class);
        context = spy(context);
        when(context.clientRequestListeners("abc")).thenReturn(
                Lists.newArrayList(new RequestResponseClass(ClassA.class, AppUser.class, new ArrayList<Class<?>>())));
        ClientEventHandler handler = new ExClientEventHandler(context, "abc");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    @Test(priority = 3)
    public void test2() {
        context = new AppContextImpl(AppEntryPoint.class);
        context = spy(context);
        when(context.clientRequestListeners("abc1")).thenReturn(new ArrayList<RequestResponseClass>());
        ClientEventHandler handler = new ExClientEventHandler(context, "abc1");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    @Test(priority = 4)
    public void test3() {
        context = new AppContextImpl(AppEntryPoint.class);
        context = spy(context);
        List<Class<?>> gameUserClasses = new ArrayList<>();
        gameUserClasses.add(PokerUser.class);
        when(context.clientRequestListeners("xyz")).thenReturn(
                Lists.newArrayList(new RequestResponseClass(ClassB.class, AppUser.class, gameUserClasses)));
        ClientEventHandler handler = new ExClientEventHandler(context, "xyz");
        ISFSObject params = new SFSObject();
        handler.handleClientRequest(sfsUser, params);
    }
    
    @Test(priority = 5)
    public void test4() {
        context = new AppContextImpl(AppEntryPoint.class);
        context = spy(context);
        List<Class<?>> gameUserClasses = new ArrayList<>();
        gameUserClasses.add(PokerUser.class);
        when(context.clientRequestListeners("xyz")).thenReturn(
                Lists.newArrayList(new RequestResponseClass(ClassC.class, AppUser.class, gameUserClasses)));
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
        public void execute(AppContext context, PokerUser user) {
        }
    }
    
}
