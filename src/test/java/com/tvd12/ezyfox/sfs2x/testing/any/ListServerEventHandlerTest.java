/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.any;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.sfs2x.content.impl.AppContextImpl;
import com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.ClassFinder;
import com.tvd12.ezyfox.sfs2x.testing.context.AppEntryPoint;

/**
 * @author tavandung12
 *
 */
public class ListServerEventHandlerTest {

    public static void main(String[] args) throws Exception {
        new ListServerEventHandlerTest().test();
    }
    
    @Test
    public void test() throws Exception {
        File file = new File(FileUtils.getFile("").getAbsolutePath() + "/src/main/resources/ezyfox/config/handlers.properties");
        List<Class<?>> classes = ClassFinder.find("com.tvd12.ezyfox.sfs2x.serverhandler");
        StringBuilder builder = new StringBuilder();
        for(Class<?> clazz : classes) {
            if(!Modifier.isAbstract(clazz.getModifiers())
                    && Modifier.isPublic(clazz.getModifiers())
                    && !Modifier.isStatic(clazz.getModifiers())) {
                ServerEventHandler handler = (ServerEventHandler) clazz.getDeclaredConstructor(
                        BaseAppContext.class).newInstance(newAppContext());
                System.out.println(handler.eventName() + "=" + clazz.getName());
                if(!handler.eventName().equals(ServerEvent.SERVER_INITIALIZING) &&
                        !handler.eventName().equals(ServerEvent.ZONE_EXTENSION_DESTROY) &&
                        !handler.eventName().equals(ServerEvent.ROOM_EXTENSION_DESTROY) &&
                        !handler.eventName().equals(ServerEvent.ROOM_USER_DISCONNECT) &&
                		!handler.eventName().equals(ServerEvent.ROOM_USER_RECONNECT))
                    builder.append(handler.eventName() + "=" + clazz.getName()).append("\n");
            }
                
        }
        FileUtils.write(file, Base64Coder.encodeString(builder.toString().trim()));
    }
    
    private AppContextImpl newAppContext() {
        AppContextImpl answer = new AppContextImpl();
        answer.initialize(AppEntryPoint.class);
        return answer;
    }
    
}
