package com.tvd12.ezyfox.sfs2x.testing.context;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.api.ISFSApi;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.command.CreateRoom;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.test.reflect.MethodInvoker;

public class AppContextImplTest {
    
    private AppContextImpl context;
    
    public AppContextImplTest() {
        try {
            ISFSApi api = ApiModelFactory.createSFSApi();
            ISFSExtension extension = ApiModelFactory.createExtension();
            context = newAppContext();
            context.setApi(api);
            context.setExtension(extension);
            context.addAppCommand(AppCommand.class, AppCommand.class);
            context.addAppCommand(AppCommand2.class, AppCommand2.class);
        } catch (SFSCreateRoomException e) {
            e.printStackTrace();
        }
       
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }

    @Test
    public void test() throws SFSCreateRoomException {
        assertNotNull(context);
        context.set("a", "b");
        assertEquals(context.get("a", String.class), "b");
        assertNotNull(context.getRoomAgentClass(PokerRoom.class));
        assertNotNull(context.getUserAgentClass(PokerUser.class));
        assertNotNull(context.getUserAgentClass(AppUser.class));
        assertTrue(context.getClientRequestCommands().size() > 0);
        assertTrue(context.getServerEventHandlerClasses(ServerEvent.PUBLIC_MESSAGE).size() > 0);
        
        assertNotNull(context.command(CreateRoom.class));
        assertNotNull(context.command(AppCommand.class));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getAppCommandInvalidCase() {
        context.command(Class.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getAppCommandInvalidCase2() {
        context.command(AppCommand2.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void addAppCommandInvalidCase() {
        context.addAppCommand(AppCommand4.class, AppCommand4.class);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getCommandConstructorInvalidCaseTest() {
        MethodInvoker.create()
            .object(context)
            .method("getCommandConstructor")
            .param(ClassA.class)
            .invoke();
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void getCommandInvalidCaseTest() {
        MethodInvoker.create()
            .object(context)
            .method("getCommand")
            .param(ClassA.class)
            .invoke();
    }
    
    @SuppressWarnings("unchecked")
    @Test(expectedExceptions = {RuntimeException.class})
    public void getCommandInvalidCase2Test() throws ExtensionException, IllegalArgumentException, IllegalAccessException {
        Field commandField = ReflectFieldUtil.getField("commands", BaseAppContext.class);
        commandField.setAccessible(true);
        Map<Object, Constructor<?>> value = (Map<Object, Constructor<?>>)
                commandField.get(context);
        value.put(ClassA.class.getName(), ReflectClassUtil.getDefaultConstructor(ClassB.class));
        MethodInvoker.create()
            .object(context)
            .method("getCommand")
            .param(ClassB.class)
            .invoke();
    }
    
    public static class ClassA {
        
    }
    
    public static class ClassB {
        
    }
    
}
