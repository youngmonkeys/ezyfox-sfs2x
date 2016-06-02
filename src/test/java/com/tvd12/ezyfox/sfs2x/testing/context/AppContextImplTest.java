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
import com.tvd12.ezyfox.core.config.ServerEvent;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.test.reflect.MethodInvoker;

public class AppContextImplTest {
    
    private AppContextImpl context 
        = new AppContextImpl(AppEntryPoint.class);
    
    public AppContextImplTest() {
        try {
            ISFSApi api = ApiModelFactory.createSFSApi();
            ISFSExtension extension = ApiModelFactory.createExtension();
            
            context.setApi(api);
            context.setExtension(extension);
        } catch (SFSCreateRoomException e) {
            e.printStackTrace();
        }
       
    }

    @Test
    public void test() throws SFSCreateRoomException {
        assertNotNull(context);
        context.set("a", "b");
        assertEquals(context.get("a", String.class), "b");
        assertTrue(context.getRoomAgentClasses().size() > 0);
        assertNotNull(context.getRoomAgentClass(PokerRoom.class));
        assertNotNull(context.getUserAgentClass(PokerUser.class));
        assertNotNull(context.getUserAgentClass(AppUser.class));
        assertTrue(context.clientRequestCommands().size() > 0);
        assertTrue(context.serverEventHandlerClasses(ServerEvent.PUBLIC_MESSAGE).size() > 0);
        
        assertNotNull(context.command(CreateRoom.class));
        
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
        Field commandField = ReflectFieldUtil.getField("commands", AppContextImpl.class);
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
