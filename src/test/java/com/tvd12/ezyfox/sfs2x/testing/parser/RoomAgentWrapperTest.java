package com.tvd12.ezyfox.sfs2x.testing.parser;

import java.util.List;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.structure.AgentClassWrapper;
import com.tvd12.ezyfox.sfs2x.serializer.AgentDeserializer;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

public class RoomAgentWrapperTest extends BaseTest {

    @Test
    public void test() throws SFSVariableException {
        
        Room sfsRoom = new SFSRoom("lobby");
        sfsRoom.setMaxRoomVariablesAllowed(30);
        sfsRoom.setHidden(false);
        ISFSArray array = new SFSArray();
        ISFSObject object = new SFSObject();
        object.putUtfString("value", "1");
        array.addSFSObject(object);
        sfsRoom.setVariable(new SFSRoomVariable("a0", array));
        
        array = new SFSArray();
        object = new SFSObject();
        object.putUtfString("value", "2");
        array.addSFSObject(object);
        sfsRoom.setVariable(new SFSRoomVariable("a1", array));
        
        object = new SFSObject();
        object.putUtfString("value", "2");
        sfsRoom.setVariable(new SFSRoomVariable("a2", object));
        
        sfsRoom.setVariable(new SFSRoomVariable("a3", 1));
        sfsRoom.setVariable(new SFSRoomVariable("a4", 2));
        sfsRoom.setVariable(new SFSRoomVariable("a5", 1.0D));
        sfsRoom.setVariable(new SFSRoomVariable("a6", 2D));
        sfsRoom.setVariable(new SFSRoomVariable("a7", 1));
        sfsRoom.setVariable(new SFSRoomVariable("a8", "1"));
        
        AgentClassWrapper wrapper = new AgentClassWrapper(ClassA.class);
        new AgentDeserializer().deserialize(wrapper, sfsRoom.getVariables());
        
    }
    
    @Data
    public static class ClassA {
        @Variable
        private ClassB[] a0;
        @Variable
        private List<ClassB> a1;
        @Variable
        private ClassB a2;
        @Variable
        private byte a3;
        @Variable
        private char a4;
        @Variable
        private float a5;
        @Variable
        private long a6;
        @Variable
        private short a7;
        @Variable
        private String a8;
    }
    
    @Data
    public static class ClassB {
        @VariableParam
        private String value;
    }
    
}
