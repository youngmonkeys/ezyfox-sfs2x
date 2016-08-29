/**
 * 
 */
package com.tvd12.ezyfox.sfs2x.testing.command;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.ISFSExtension;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.sfs2x.command.impl.ResponseImpl;

import lombok.Data;

/**
 * @author tavandung12
 *
 */
public class ResponseImplTest2 extends BaseCommandTest2 {

    @Test(priority = 1)
    public void test() {
        ResponseImpl command = new ResponseImpl(context, api, mockExtension());
        command.command("hello")
            .data(new MyUser())
            .recipients(user)
            .recipients("dungtv")
            .useUDP(false)
            .param("d", "d")
            .only("a", "b")
            .ignore("b")
            .execute();
    }
    
    @SuppressWarnings("unchecked")
    public ISFSExtension mockExtension() {
        extension = mock(ISFSExtension.class);
        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                ISFSObject params = (ISFSObject) invocation.getArguments()[1];
                assertEquals(params.size(), 1);
                assertEquals(params.getUtfString("a"), "a");
                return null;
            }
        }).when(extension).send(
                any(String.class), 
                any(ISFSObject.class), 
                any(List.class), 
                any(boolean.class));
        return extension;
    }
    
    @Data
    public static class MyUser {
        @ResponseParam
        public String a = "a";
        @ResponseParam
        public String b = "b";
        @ResponseParam
        public String c = "c";
    }
}
