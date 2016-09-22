package com.tvd12.ezyfox.sfs2x.testing.parser;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoom;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.structure.AgentClassWrapper;
import com.tvd12.ezyfox.sfs2x.serializer.AgentDeserializer;
import com.tvd12.ezyfox.sfs2x.testing.TypeMap;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

public class RoomAgentWrapperTest extends BaseTest {

    @Test
    public void test() throws SFSVariableException {
        
        Room sfsRoom = new SFSRoom("lobby");
        sfsRoom.setMaxRoomVariablesAllowed(60);
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
        
        sfsRoom.setVariable(createVariableWithSFSArray("a9", Lists.newArrayList(true, false)));
        sfsRoom.setVariable(createVariableWithSFSArray("a10", new byte[] {1, 2}));
        sfsRoom.setVariable(createVariableWithSFSArray("a11", new byte[] {1, 2}));
        sfsRoom.setVariable(createVariableWithSFSArray("a12", Lists.newArrayList(1D, 2D)));
        sfsRoom.setVariable(createVariableWithSFSArray("a13", Lists.newArrayList(1F, 2F)));
        sfsRoom.setVariable(createVariableWithSFSArray("a14", Lists.newArrayList(1, 2)));
        sfsRoom.setVariable(createVariableWithSFSArray("a15", Lists.newArrayList(1L, 2L)));
        sfsRoom.setVariable(createVariableWithSFSArray("a16", Lists.newArrayList((short)1, (short)2)));
        sfsRoom.setVariable(createVariableWithSFSArray("a17", Lists.newArrayList("1", "2")));
        
        sfsRoom.setVariable(createVariableWithSFSArray("a18", Lists.newArrayList(true, false)));
        sfsRoom.setVariable(createVariableWithSFSArray("a19", new byte[] {1, 2}));
        sfsRoom.setVariable(createVariableWithSFSArray("a20", new byte[] {1, 2}));
        sfsRoom.setVariable(createVariableWithSFSArray("a21", Lists.newArrayList(1D, 2D)));
        sfsRoom.setVariable(createVariableWithSFSArray("a22", Lists.newArrayList(1F, 2F)));
        sfsRoom.setVariable(createVariableWithSFSArray("a23", Lists.newArrayList(1, 2)));
        sfsRoom.setVariable(createVariableWithSFSArray("a24", Lists.newArrayList(1L, 2L)));
        sfsRoom.setVariable(createVariableWithSFSArray("a25", Lists.newArrayList((short)1, (short)2)));
        
        sfsRoom.setVariable(createVariableWithSFSArray("a26", Lists.newArrayList(true, false)));
        sfsRoom.setVariable(createVariableWithSFSArray("a27", new byte[] {1, 2}));
        sfsRoom.setVariable(createVariableWithSFSArray("a28", new byte[] {1, 2}));
        sfsRoom.setVariable(createVariableWithSFSArray("a29", Lists.newArrayList(1D, 2D)));
        sfsRoom.setVariable(createVariableWithSFSArray("a30", Lists.newArrayList(1F, 2F)));
        sfsRoom.setVariable(createVariableWithSFSArray("a31", Lists.newArrayList(1, 2)));
        sfsRoom.setVariable(createVariableWithSFSArray("a32", Lists.newArrayList(1L, 2L)));
        sfsRoom.setVariable(createVariableWithSFSArray("a33", Lists.newArrayList((short)1, (short)2)));
        sfsRoom.setVariable(createVariableWithSFSArray("a34", Lists.newArrayList("1", "2")));
        
        sfsRoom.setVariable(createVariableWithSFSArray("a35", Lists.newArrayList(1, 2)));
        
        ISFSObject a361o1 = new SFSObject();
        ISFSArray a361 = new SFSArray();
        a361.addSFSObject(a361o1);
        sfsRoom.setVariable(createVariableWithSFSArray("a36", SFSDataType.SFS_ARRAY, a361));
        
        sfsRoom.setVariable(createVariableWithSFSArray("a37", SFSDataType.SFS_OBJECT, a361o1));
        
        sfsRoom.setVariable(createVariableWithSFSArray("a38", Lists.newArrayList(1, 2)));
        
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
        
        @Variable
        private boolean[] a9;
        @Variable
        private byte[] a10;
        @Variable
        private char[] a11;
        @Variable
        private double[] a12;
        @Variable
        private float[] a13;
        @Variable
        private int[] a14;
        @Variable
        private long[] a15;
        @Variable
        private short[] a16;
        @Variable
        private String[] a17;
        
        @Variable
        private Boolean[] a18;
        @Variable
        private Byte[] a19;
        @Variable
        private Character[] a20;
        @Variable
        private Double[] a21;
        @Variable
        private Float[] a22;
        @Variable
        private Integer[] a23;
        @Variable
        private Long[] a24;
        @Variable
        private Short[] a25;
        
        @Variable
        private List<Boolean> a26;
        @Variable
        private List<Byte> a27;
        @Variable
        private List<Character> a28;
        @Variable
        private List<Double> a29;
        @Variable
        private List<Float> a30;
        @Variable
        private List<Integer> a31;
        @Variable
        private List<Long> a32;
        @Variable
        private List<Short> a33;
        @Variable
        private List<String> a34;
        @Variable
        private int[][] a35;
        @Variable
        private List<ClassB[]> a36;
        @Variable
        private List<ClassB> a37;
        @Variable
        private List<int[]> a38;
    }
    
    private RoomVariable createVariableWithSFSArray(String name, Object data) {
        return createVariableWithSFSArray(name, TypeMap.getDataType(data.getClass()), data);
    }
    
    private RoomVariable createVariableWithSFSArray(String name, SFSDataType type, Object data) {
        ISFSArray array = new SFSArray();
        array.add(new SFSDataWrapper(type, data));
        return new SFSRoomVariable(name, array);
    }
    
    @Data
    public static class ClassB {
        @VariableParam
        private String value;
    }
    
}
