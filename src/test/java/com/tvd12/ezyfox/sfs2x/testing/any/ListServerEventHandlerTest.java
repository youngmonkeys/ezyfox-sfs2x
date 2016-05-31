/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.any;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.tvd12.ezyfox.sfs2x.serverhandler.ServerEventHandler;
import com.tvd12.ezyfox.sfs2x.testing.ClassFinder;

/**
 * @author tavandung12
 *
 */
public class ListServerEventHandlerTest {

    public static void main(String[] args) throws IOException {
        new ListServerEventHandlerTest().test();
    }
    
    @Test
    public void test() throws IOException {
        File file = new File(FileUtils.getFile("").getAbsolutePath() + "/src/main/resources/ezyfox/config/server-event-handlers.properties");
        List<Class<?>> classes = ClassFinder.find("com.tvd12.ezyfox.sfs2x.serverhandler");
        StringBuilder builder = new StringBuilder();
        for(Class<?> clazz : classes) {
            if(!Modifier.isAbstract(clazz.getModifiers())) {
                ServerEventHandler handler = (ServerEventHandler) mock(clazz, CALLS_REAL_METHODS);
                System.out.println(handler.eventName() + "=" + clazz.getName());
                if(!handler.eventName().equals("SERVER_INIT"))
                    builder.append(handler.eventName() + "=" + clazz.getName()).append("\n");
            }
                
        }
        FileUtils.write(file, Base64Coder.encodeString(builder.toString().trim()));
    }
    
}
