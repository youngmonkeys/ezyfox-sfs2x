package com.tvd12.ezyfox.sfs2x.testing.parser;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.serializer.RoomAgentUnwrapper;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

public class RoomAgentUnwrapperTest extends BaseTest {

    @Test
    public void test() {
        AgentClassUnwrapper unwrapper = new AgentClassUnwrapper(ClassA.class);
        RoomAgentUnwrapper.getInstance();
        RoomAgentUnwrapper.roomAgentUnwrapper()
                .unwrap(unwrapper, new ClassA());
    }
    
    @Override
    public Class<?> getTestClass() {
        return RoomAgentUnwrapper.class;
    }
    
    @Data
    @RoomAgent
    public static class ClassA {
        @Variable
        private String value = "hello";
    }
    
}
