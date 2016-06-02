package com.tvd12.ezyfox.sfs2x.testing.serverhandler;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.BadRequestException;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.UserLoginEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.context.BaseHandlerTest;

public class UserLoginEventHandlerTest extends BaseHandlerTest {

    @Test(priority = 1)
    public void test() throws SFSException {
        UserLoginEventHandler handler = new UserLoginEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.LOGIN_NAME)).thenReturn("username");
        when(event.getParameter(SFSEventParam.LOGIN_PASSWORD)).thenReturn("password");
        when(event.getParameter(SFSEventParam.LOGIN_IN_DATA)).thenReturn(new SFSObject());
        when(event.getParameter(SFSEventParam.LOGIN_OUT_DATA)).thenReturn(new SFSObject());
        handler.handleServerEvent(event);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(priority = 2, expectedExceptions = {SFSLoginException.class})
    public void test2() throws SFSException {
        context = mock(AppContextImpl.class);
        when(context.serverEventHandlerClasses(ServerEvent.USER_LOGIN))
            .thenReturn((List)Lists.newArrayList(ClassA.class));
        UserLoginEventHandler handler = new UserLoginEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.LOGIN_NAME)).thenReturn("username");
        when(event.getParameter(SFSEventParam.LOGIN_PASSWORD)).thenReturn("password");
        when(event.getParameter(SFSEventParam.LOGIN_IN_DATA)).thenReturn(new SFSObject());
        when(event.getParameter(SFSEventParam.LOGIN_OUT_DATA)).thenReturn(new SFSObject());
        handler.handleServerEvent(event);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(priority = 3, expectedExceptions = {SFSLoginException.class})
    public void test3() throws SFSException {
        context = mock(AppContextImpl.class);
        when(context.serverEventHandlerClasses(ServerEvent.USER_LOGIN))
            .thenReturn((List)Lists.newArrayList(ClassB.class));
        UserLoginEventHandler handler = new UserLoginEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.LOGIN_NAME)).thenReturn("username");
        when(event.getParameter(SFSEventParam.LOGIN_PASSWORD)).thenReturn("password");
        when(event.getParameter(SFSEventParam.LOGIN_IN_DATA)).thenReturn(new SFSObject());
        when(event.getParameter(SFSEventParam.LOGIN_OUT_DATA)).thenReturn(new SFSObject());
        handler.handleServerEvent(event);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(priority = 5)
    public void test5() throws SFSException {
        context = mock(AppContextImpl.class);
        when(context.serverEventHandlerClasses(ServerEvent.USER_LOGIN))
            .thenReturn((List)Lists.newArrayList(ClassA.class));
        UserLoginEventHandler handler = new UserLoginEventHandler(context);
        ISFSEvent event = mock(ISFSEvent.class);
        when(event.getParameter(SFSEventParam.LOGIN_NAME)).thenReturn("username");
        when(event.getParameter(SFSEventParam.LOGIN_PASSWORD)).thenReturn("password");
        when(event.getParameter(SFSEventParam.LOGIN_IN_DATA)).thenReturn(new SFSObject());
        when(event.getParameter(SFSEventParam.LOGIN_OUT_DATA)).thenReturn(new SFSObject());
        try {
            handler.handleServerEvent(event);
        }
        catch(SFSLoginException e) {
            assertEquals(e.getErrorData().getCode().getId(), (short)1);
        }
    }
    
    @ServerEventHandler(event = ServerEvent.USER_LOGIN)
    public static class ClassA {
        public void handle(AppContext context, String username, String password) throws Exception {
            throw new BadRequestException(1);
        }
    }
    
    @ServerEventHandler(event = ServerEvent.USER_LOGIN)
    public static class ClassB {
        public void handle(AppContext context, String username, String password) throws Exception {
            throw new NullPointerException();
        }
    }
    
}
