package com.tvd12.ezyfox.sfs2x.testing.parser;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.structure.AgentClassUnwrapper;
import com.tvd12.ezyfox.sfs2x.serializer.UserAgentUnwrapper;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import lombok.Data;

public class UserAgentUnwrapperTest extends BaseTest {

    private AgentClassUnwrapper unwrapper 
            = new AgentClassUnwrapper(ClassA.class);
    
    @Test
    public void test() {
        unwrapper 
            = new AgentClassUnwrapper(ClassA.class);
        UserAgentUnwrapper.userAgentUnwrapper()
            .unwrap(unwrapper, new ClassA());
    }
    
    @Override
    public Class<?> getTestClass() {
        return UserAgentUnwrapper.class;
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
        public List<byte[]> a47 = Lists.newArrayList(new byte[] {(byte)1});
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
                        UserAgentUnwrapper.getInstance()
                        .unwrap(unwrapper, new ClassC());
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
