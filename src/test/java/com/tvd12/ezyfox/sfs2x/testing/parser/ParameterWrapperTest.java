package com.tvd12.ezyfox.sfs2x.testing.parser;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.structure.RequestListenerClass;
import com.tvd12.ezyfox.sfs2x.serializer.ParameterDeserializer;

import lombok.Data;

public class ParameterWrapperTest {
    
    @Test
    public void assignValuesToMethodTest() {
        ISFSObject params = new SFSObject();
        params.putUtfString("a0", "1");
        params.putBool("a1", true);
        params.putByte("a2", (byte)1);
        params.putByte("a3", (byte)1);
        params.putDouble("a4", 1.0);
        params.putFloat("a5", 1.0f);
        params.putInt("a6", 1);
        params.putLong("a7", 1L);
        params.putShort("a8", (short)1);
        
        params.putUtfString("a9", "1");
        params.putBool("a10", true);
        params.putByte("a11", (byte)1);
        params.putByte("a12", (byte)1);
        params.putDouble("a13", 1.0);
        params.putFloat("a14", 1.0f);
        params.putInt("a15", 1);
        params.putLong("a16", 1L);
        params.putShort("a17", (short)1);
        
        params.putUtfString("a9", "1");
        params.putBool("a10", true);
        params.putByte("a11", (byte)1);
        params.putByte("a12", (byte)1);
        params.putDouble("a13", 1.0);
        params.putFloat("a14", 1.0f);
        params.putInt("a15", 1);
        params.putLong("a16", 1L);
        params.putShort("a17", (short)1);
        
        params.putUtfStringArray("a18", Lists.newArrayList("1"));
        params.putBoolArray("a19", Lists.newArrayList(Boolean.TRUE));
        params.putByteArray("a20", new byte[] {1});
        params.putByteArray("a21", new byte[] {1});
        params.putDoubleArray("a22", Lists.newArrayList(1.0D));
        params.putFloatArray("a23", Lists.newArrayList(1.0F));
        params.putIntArray("a24", Lists.newArrayList(1));
        params.putLongArray("a25", Lists.newArrayList(1L));
        params.putShortArray("a26", Lists.newArrayList(new Short((short)1)));
        
        params.putUtfStringArray("a27", Lists.newArrayList("1"));
        params.putBoolArray("a28", Lists.newArrayList(Boolean.TRUE));
        params.putByteArray("a29", new byte[] {1});
        params.putByteArray("a30", new byte[] {1});
        params.putDoubleArray("a31", Lists.newArrayList(1.0D));
        params.putFloatArray("a32", Lists.newArrayList(1.0F));
        params.putIntArray("a33", Lists.newArrayList(1));
        params.putLongArray("a34", Lists.newArrayList(1L));
        params.putShortArray("a35", Lists.newArrayList(new Short((short)1)));
        
        params.putUtfStringArray("a36", Lists.newArrayList("1"));
        params.putBoolArray("a37", Lists.newArrayList(Boolean.TRUE));
        params.putByteArray("a38", new byte[] {1});
        params.putByteArray("a39", new byte[] {1});
        params.putDoubleArray("a40", Lists.newArrayList(1.0D));
        params.putFloatArray("a41", Lists.newArrayList(1.0F));
        params.putIntArray("a42", Lists.newArrayList(1));
        params.putLongArray("a43", Lists.newArrayList(1L));
        params.putShortArray("a44", Lists.newArrayList(new Short((short)1)));
        
        ISFSArray array = new SFSArray();
        ISFSObject object = new SFSObject();
        object.putUtfString("value", "1");
        array.addSFSObject(object);
        params.putSFSArray("a45", array);
        
        array = new SFSArray();
        object = new SFSObject();
        object.putUtfString("value", "1");
        params.putSFSArray("a46", array);
        
        array = new SFSArray();
        array.addByteArray(new byte[] {1});
        params.putSFSArray("a47", array);
        
        array = new SFSArray();
        ISFSArray array1 = new SFSArray();
        object = new SFSObject();
        object.putUtfString("value", "1");
        array1.addSFSObject(object);
        array.addSFSArray(array1);
        params.putSFSArray("a48", array);
        
        array = new SFSArray();
        array.addBoolArray(Lists.newArrayList(Boolean.TRUE));
        params.putSFSArray("a49", array);
        
        array = new SFSArray();
        array.addByteArray(new byte[] {(byte)1});
        params.putSFSArray("a50", array);
        
        array = new SFSArray();
        array.addDoubleArray(Lists.newArrayList(1D));
        params.putSFSArray("a51", array);
        
        array = new SFSArray();
        array.addFloatArray(Lists.newArrayList(1F));
        params.putSFSArray("a52", array);
        
        array = new SFSArray();
        array.addIntArray(Lists.newArrayList(1));
        params.putSFSArray("a53", array);
        
        array = new SFSArray();
        array.addLongArray(Lists.newArrayList(1L));
        params.putSFSArray("a54", array);
        
        array = new SFSArray();
        array.addShortArray(Lists.newArrayList(new Short((short)1)));
        params.putSFSArray("a55", array);
        
        // list of wrapper array
        array = new SFSArray();
        array.addBoolArray(Lists.newArrayList(Boolean.TRUE));
        params.putSFSArray("a56", array);
        
        array = new SFSArray();
        array.addByteArray(new byte[] {(byte)1});
        params.putSFSArray("a57", array);
        
        array = new SFSArray();
        array.addByteArray(new byte[] {(byte)1});
        params.putSFSArray("a58", array);
        
        array = new SFSArray();
        array.addDoubleArray(Lists.newArrayList(1D));
        params.putSFSArray("a59", array);
        
        array = new SFSArray();
        array.addFloatArray(Lists.newArrayList(1F));
        params.putSFSArray("a60", array);
        
        array = new SFSArray();
        array.addIntArray(Lists.newArrayList(1));
        params.putSFSArray("a61", array);
        
        array = new SFSArray();
        array.addLongArray(Lists.newArrayList(1L));
        params.putSFSArray("a62", array);
        
        array = new SFSArray();
        array.addShortArray(Lists.newArrayList(new Short((short)1)));
        params.putSFSArray("a63", array);
        
        array = new SFSArray();
        array.addUtfStringArray(Lists.newArrayList("1"));
        params.putSFSArray("a64", array);
        
        RequestListenerClass wrapper = new RequestListenerClass(ClassA.class);
        new ParameterDeserializer().deserialize(wrapper, params);
    }
    
    @Data
    @ClientRequestListener(command = "abc")
    public static class ClassA {
        @RequestParam
        public String a0;
        @RequestParam
        public boolean a1;
        @RequestParam
        public byte a2;
        @RequestParam
        public char a3;
        @RequestParam
        public double a4;
        @RequestParam
        public float a5;
        @RequestParam
        public int a6;
        @RequestParam
        public long a7;
        @RequestParam
        public short a8;
        
        @RequestParam
        public String a9;
        @RequestParam
        public Boolean a10;
        @RequestParam
        public Byte a11;
        @RequestParam
        public Character a12;
        @RequestParam
        public Double a13;
        @RequestParam
        public Float a14;
        @RequestParam
        public Integer a15;
        @RequestParam
        public Long a16;
        @RequestParam
        public Short a17;
        
        @RequestParam
        public String[] a18;
        @RequestParam
        public boolean[] a19;
        @RequestParam
        public byte[] a20;
        @RequestParam
        public char[] a21;
        @RequestParam
        public double[] a22;
        @RequestParam
        public float[] a23;
        @RequestParam
        public int[] a24;
        @RequestParam
        public long[] a25;
        @RequestParam
        public short[] a26;
        
        @RequestParam
        public String[] a27;
        @RequestParam
        public Boolean[] a28;
        @RequestParam
        public Byte[] a29;
        @RequestParam
        public Character[] a30;
        @RequestParam
        public Double[] a31;
        @RequestParam
        public Float[] a32;
        @RequestParam
        public Integer[] a33;
        @RequestParam
        public Long[] a34;
        @RequestParam
        public Short[] a35;
        
        @RequestParam
        public List<String> a36;
        @RequestParam
        public List<Boolean> a37;
        @RequestParam
        public List<Byte> a38;
        @RequestParam
        public List<Character> a39;
        @RequestParam
        public List<Double> a40;
        @RequestParam
        public List<Float> a41;
        @RequestParam
        public List<Integer> a42;
        @RequestParam
        public List<Long> a43;
        @RequestParam
        public List<Short> a44;
 
        @RequestParam
        public ClassB[] a45;
        @RequestParam
        public List<ClassB> a46;
        @RequestParam
        public List<byte[]> a47;
        @RequestParam
        public List<ClassB[]> a48;
        
        @RequestParam
        public List<boolean[]> a49;
        @RequestParam
        public List<char[]> a50;
        @RequestParam
        public List<double[]> a51;
        @RequestParam
        public List<float[]> a52;
        @RequestParam
        public List<int[]> a53;
        @RequestParam
        public List<long[]> a54;
        @RequestParam
        public List<short[]> a55;
        
        @RequestParam
        public List<Boolean[]> a56;
        @RequestParam
        public List<Byte[]> a57;
        @RequestParam
        public List<Character[]> a58;
        @RequestParam
        public List<Double[]> a59;
        @RequestParam
        public List<Float[]> a60;
        @RequestParam
        public List<Integer[]> a61;
        @RequestParam
        public List<Long[]> a62;
        @RequestParam
        public List<Short[]> a63;
        @RequestParam
        public List<String[]> a64;
    }
    
    @Data
    public static class ClassB {
        @RequestParam
        public String value;
    }
    
}
