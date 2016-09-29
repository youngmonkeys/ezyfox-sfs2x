package com.tvd12.ezyfox.sfs2x.testing.parser;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.entities.variables.VariableType;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.serializer.UserAgentSerializer;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import static org.testng.Assert.*;

import lombok.Data;

public class UserAgentUnwrapperTest extends BaseTest {

    private AgentClassUnwrapper unwrapper 
            = new AgentClassUnwrapper(ClassA.class);
    
    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        unwrapper 
            = new AgentClassUnwrapper(ClassA.class);
        List<UserVariable> vars = 
                new UserAgentSerializer()
                    .serialize(unwrapper, new ClassA());
        assertEquals(vars.get(0).getStringValue(), "1");
        assertEquals(vars.get(1).getBoolValue(), Boolean.TRUE);
        assertEquals(vars.get(2).getIntValue(), new Integer(1));
        assertEquals(vars.get(3).getIntValue(), new Integer(1));
        assertEquals(vars.get(4).getDoubleValue(), 1D);
        assertEquals(vars.get(5).getDoubleValue(), 1D);
        assertEquals(vars.get(6).getIntValue(), new Integer(1));
        assertEquals(vars.get(7).getDoubleValue(), 1.D);
        assertEquals(vars.get(8).getIntValue(), new Integer(1));
        
        assertEquals(vars.get(9).getStringValue(), "1");
        assertEquals(vars.get(10).getBoolValue(), Boolean.TRUE);
        assertEquals(vars.get(11).getIntValue(), new Integer(1));
        assertEquals(vars.get(12).getIntValue(), new Integer(1));
        assertEquals(vars.get(13).getDoubleValue(), 1D);
        assertEquals(vars.get(14).getDoubleValue(), 1D);
        assertEquals(vars.get(15).getIntValue(), new Integer(1));
        assertEquals(vars.get(16).getDoubleValue(), 1.D);
        assertEquals(vars.get(17).getIntValue(), new Integer(1));
        
        assertEquals(vars.get(18).getSFSArrayValue().getUtfStringArray(0).toArray(new String[1]), new String[] {"1"});
        assertEquals(vars.get(19).getSFSArrayValue().getBoolArray(0).toArray(new Boolean[1]), new Boolean[] {Boolean.TRUE});
        assertEquals(vars.get(20).getSFSArrayValue().getByteArray(0), new byte[] {(byte)1});
        assertEquals(vars.get(21).getSFSArrayValue().getByteArray(0), new byte[] {(byte)1});
        assertEquals(vars.get(22).getSFSArrayValue().getDoubleArray(0).toArray(new Double[1]), new Double[] {1D});
        assertEquals(vars.get(23).getSFSArrayValue().getFloatArray(0).toArray(new Float[1]), new Float[] {1F});
        assertEquals(vars.get(24).getSFSArrayValue().getIntArray(0).toArray(new Integer[1]), new Integer[] {1});
        assertEquals(vars.get(25).getSFSArrayValue().getLongArray(0).toArray(new Long[1]), new Long[] {1L});
        assertEquals(vars.get(26).getSFSArrayValue().getShortArray(0).toArray(new Short[1]), new Short[] {(short)1});
        
        assertEquals(vars.get(27).getSFSArrayValue().getUtfStringArray(0).toArray(new String[1]), new String[] {"1"});
        assertEquals(vars.get(28).getSFSArrayValue().getBoolArray(0).toArray(new Boolean[1]), new Boolean[] {Boolean.TRUE});
        assertEquals(vars.get(29).getSFSArrayValue().getByteArray(0), new byte[] {(byte)1});
        assertEquals(vars.get(30).getSFSArrayValue().getByteArray(0), new byte[] {(byte)1});
        assertEquals(vars.get(31).getSFSArrayValue().getDoubleArray(0).toArray(new Double[1]), new Double[] {1D});
        assertEquals(vars.get(32).getSFSArrayValue().getFloatArray(0).toArray(new Float[1]), new Float[] {1F});
        assertEquals(vars.get(33).getSFSArrayValue().getIntArray(0).toArray(new Integer[1]), new Integer[] {1});
        assertEquals(vars.get(34).getSFSArrayValue().getLongArray(0).toArray(new Long[1]), new Long[] {1L});
        assertEquals(vars.get(35).getSFSArrayValue().getShortArray(0).toArray(new Short[1]), new Short[] {(short)1});
        
        assertEquals(vars.get(36).getSFSArrayValue().getUtfStringArray(0).toArray(new String[1]), new String[] {"1"});
        assertEquals(vars.get(37).getSFSArrayValue().getBoolArray(0).toArray(new Boolean[1]), new Boolean[] {Boolean.TRUE});
        assertEquals(vars.get(38).getSFSArrayValue().getByteArray(0), new byte[] {(byte)1});
        assertEquals(vars.get(39).getSFSArrayValue().getByteArray(0), new byte[] {(byte)1});
        assertEquals(vars.get(40).getSFSArrayValue().getDoubleArray(0).toArray(new Double[1]), new Double[] {1D});
        assertEquals(vars.get(41).getSFSArrayValue().getFloatArray(0).toArray(new Float[1]), new Float[] {1F});
        assertEquals(vars.get(42).getSFSArrayValue().getIntArray(0).toArray(new Integer[1]), new Integer[] {1});
        assertEquals(vars.get(43).getSFSArrayValue().getLongArray(0).toArray(new Long[1]), new Long[] {1L});
        assertEquals(vars.get(44).getSFSArrayValue().getShortArray(0).toArray(new Short[1]), new Short[] {(short)1});
        
        assertEquals(vars.get(45).getType(), VariableType.ARRAY);
        assertEquals(vars.get(46).getType(), VariableType.ARRAY);
        assertEquals(vars.get(47).getSFSArrayValue().size(), 2);
    
    }
    
    public static void main(String[] args) {
        new UserAgentUnwrapperTest().test();
    }
    
    @Override
    public Class<?> getTestClass() {
        return UserAgentSerializer.class;
    }
    
    @Data
    @ClientRequestListener(command = "abc")
    public static class ClassA {
        @Variable
        public String a0 = "1";
        @Variable
        public boolean a1 = true;
        @Variable
        public byte a2 = (byte)1;
        @Variable
        public char a3 = (char)1;
        @Variable
        public double a4 = 1.0;
        @Variable
        public float a5 = 1.0f;
        @Variable
        public int a6 = 1;
        @Variable
        public long a7 = 1;
        @Variable
        public short a8 = (short)1;
        
        @Variable
        public String a9 = "1";
        @Variable
        public Boolean a10 = true;
        @Variable
        public Byte a11 = (byte)1;
        @Variable
        public Character a12 = (char)1;
        @Variable
        public Double a13 = 1D;
        @Variable
        public Float a14 = 1F;
        @Variable
        public Integer a15 = 1;
        @Variable
        public Long a16 = 1L;
        @Variable
        public Short a17 = (short)1;
        
        @Variable
        public String[] a18 = new String[] {"1"};
        @Variable
        public boolean[] a19 = new boolean[] {true};
        @Variable
        public byte[] a20 = new byte[] {(byte)1};
        @Variable
        public char[] a21 = new char[] {(char)1};
        @Variable
        public double[] a22 = new double[] {1.0};
        @Variable
        public float[] a23 = new float[] {1.0f};
        @Variable
        public int[] a24 = new int[] {1};
        @Variable
        public long[] a25 = new long[] {1};
        @Variable
        public short[] a26 = new short[] {(short)1};
        
        @Variable
        public String[] a27 = new String[] {"1"};
        @Variable
        public Boolean[] a28 = new Boolean[] {true};
        @Variable
        public Byte[] a29 = new Byte[] {(byte)1};
        @Variable
        public Character[] a30 = new Character[] {(char)1};
        @Variable
        public Double[] a31 = new Double[] {1.0};
        @Variable
        public Float[] a32 = new Float[] {1.0f};
        @Variable
        public Integer[] a33 = new Integer[] {1};
        @Variable
        public Long[] a34 = new Long[] {1L};
        @Variable
        public Short[] a35 = new Short[] {(short)1};
        
        @Variable
        public List<String> a36 = Lists.newArrayList("1");
        @Variable
        public List<Boolean> a37 = Lists.newArrayList(true);
        @Variable
        public List<Byte> a38 = Lists.newArrayList((byte)1);
        @Variable
        public List<Character> a39 = Lists.newArrayList((char)1);
        @Variable
        public List<Double> a40 = Lists.newArrayList(1.0D);
        @Variable
        public List<Float> a41 = Lists.newArrayList(1.0f);
        @Variable
        public List<Integer> a42 = Lists.newArrayList(1);
        @Variable
        public List<Long> a43 = Lists.newArrayList(1L);
        @Variable
        public List<Short> a44 = Lists.newArrayList((short)1);
 
        @Variable
        public ClassB[] a45 = new ClassB[] {new ClassB("1")};
        @Variable
        public List<ClassB> a46 = Lists.newArrayList(new ClassB("1"));
        @Variable
        public List<byte[]> a47 = Lists.newArrayList(new byte[] {(byte)1}, new byte[] {(byte)1});
        @Variable
        public List<ClassB[]> a48 = Lists.newArrayList(new ClassB[][] {new ClassB[] {new ClassB("1")}});
        
        @Variable
        public List<boolean[]> a49 = Lists.newArrayList(new boolean[][] {new boolean[] {true}});
        @Variable
        public List<char[]> a50 = Lists.newArrayList(new char[][] {new char[] {(char)1}});
        @Variable
        public List<double[]> a51 = Lists.newArrayList(new double[][] {new double[] {1.0}});
        @Variable
        public List<float[]> a52 = Lists.newArrayList(new float[][] {new float[] {1.0f}});
        @Variable
        public List<int[]> a53 = Lists.newArrayList(new int[][] {new int[] {1}});
        @Variable
        public List<long[]> a54 = Lists.newArrayList(new long[][] {new long[] {1L}});
        @Variable
        public List<short[]> a55 = Lists.newArrayList(new short[][] {new short[] {(short)1}});
        
        @Variable
        public List<Boolean[]> a56 = Lists.newArrayList(new Boolean[][] {new Boolean[] {true}});
        @Variable
        public List<Byte[]> a57 = Lists.newArrayList(new Byte[][] {new Byte[] {(byte)1}});
        @Variable
        public List<Character[]> a58 = Lists.newArrayList(new Character[][] {new Character[] {(char)1}});
        @Variable
        public List<Double[]> a59 = Lists.newArrayList(new Double[][] {new Double[] {1D}});
        @Variable
        public List<Float[]> a60 = Lists.newArrayList(new Float[][] {new Float[] {1F}});
        @Variable
        public List<Integer[]> a61 = Lists.newArrayList(new Integer[][] {new Integer[] {1}});
        @Variable
        public List<Long[]> a62 = Lists.newArrayList(new Long[][] {new Long[] {1L}});
        @Variable
        public List<Short[]> a63 = Lists.newArrayList(new Short[][] {new Short[] {(short)1}});
        @Variable
        public List<String[]> a64  = Lists.newArrayList(new String[][] {new String[] {"1"}});
        @Variable Object a65 = null;
    }
    
    @Data
    public static class ClassB {
        public ClassB() {}
        public ClassB(String value) {
            this.value = value;
        }
        @VariableParam
        public String value;
    }
    
    @Test
    public void performanceTest() {
        unwrapper 
        = new AgentClassUnwrapper(ClassC.class);
        long time = Performance.create()
                .loop(1000000)
                .test(new Script() {
                    @Override
                    public void execute() {
                        new UserAgentSerializer()
                        .object2params(unwrapper, new ClassC());
                    }
                }).getTime();
        INFO("testing time = " + time);
    }
    
    @Data
    @ClientRequestListener(command = "abc")
    public static class ClassC {
        @Variable
        public String a0 = "1";
//        @Variable
//        public boolean a1 = true;
//        @Variable
//        public byte a2 = (byte)1;
//        @Variable
//        public char a3 = (char)1;
//        @Variable
//        public double a4 = 1.0;
//        @Variable
//        public float a5 = 1.0f;
//        @Variable
//        public int a6 = 1;
//        @Variable
//        public long a7 = 1;
//        @Variable
//        public short a8 = (short)1;
        
        @Variable
        public String[] a27 = new String[] {"1"};
        @Variable
        public List<ClassB> a48 = Lists.newArrayList(new ClassB[] {new ClassB("1")});
        
    }
}
