package com.tvd12.ezyfox.sfs2x.testing.parser;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.structure.ResponseHandlerClass;
import com.tvd12.ezyfox.sfs2x.serializer.ParameterUnwrapper;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

public class ParameterUnwrapperTest extends BaseTest {
    
    @Test
    public void test() {
        ResponseHandlerClass clazz = new ResponseHandlerClass(ClassA.class);
        new ParameterUnwrapper().parse(clazz, new ClassA());
    }

    @Data
    @ClientRequestListener(command = "abc")
    public static class ClassA {
        @ResponseParam
        public String a0 = "1";
        @ResponseParam
        public boolean a1 = true;
        @ResponseParam
        public byte a2 = (byte)1;
        @ResponseParam
        public char a3 = (char)1;
        @ResponseParam
        public double a4 = 1.0;
        @ResponseParam
        public float a5 = 1.0f;
        @ResponseParam
        public int a6 = 1;
        @ResponseParam
        public long a7 = 1;
        @ResponseParam
        public short a8 = (short)1;
        
        @ResponseParam
        public String a9 = "1";
        @ResponseParam
        public Boolean a10 = true;
        @ResponseParam
        public Byte a11 = (byte)1;
        @ResponseParam
        public Character a12 = (char)1;
        @ResponseParam
        public Double a13 = 1D;
        @ResponseParam
        public Float a14 = 1F;
        @ResponseParam
        public Integer a15 = 1;
        @ResponseParam
        public Long a16 = 1L;
        @ResponseParam
        public Short a17 = (short)1;
        
        @ResponseParam
        public String[] a18 = new String[] {"1"};
        @ResponseParam
        public boolean[] a19 = new boolean[] {true};
        @ResponseParam
        public byte[] a20 = new byte[] {(byte)1};
        @ResponseParam
        public char[] a21 = new char[] {(char)1};
        @ResponseParam
        public double[] a22 = new double[] {1.0};
        @ResponseParam
        public float[] a23 = new float[] {1.0f};
        @ResponseParam
        public int[] a24 = new int[] {1};
        @ResponseParam
        public long[] a25 = new long[] {1};
        @ResponseParam
        public short[] a26 = new short[] {(short)1};
        
        @ResponseParam
        public String[] a27 = new String[] {"1"};
        @ResponseParam
        public Boolean[] a28 = new Boolean[] {true};
        @ResponseParam
        public Byte[] a29 = new Byte[] {(byte)1};
        @ResponseParam
        public Character[] a30 = new Character[] {(char)1};
        @ResponseParam
        public Double[] a31 = new Double[] {1.0};
        @ResponseParam
        public Float[] a32 = new Float[] {1.0f};
        @ResponseParam
        public Integer[] a33 = new Integer[] {1};
        @ResponseParam
        public Long[] a34 = new Long[] {1L};
        @ResponseParam
        public Short[] a35 = new Short[] {(short)1};
        
        @ResponseParam
        public List<String> a36 = Lists.newArrayList("1");
        @ResponseParam
        public List<Boolean> a37 = Lists.newArrayList(true);
        @ResponseParam
        public List<Byte> a38 = Lists.newArrayList((byte)1);
        @ResponseParam
        public List<Character> a39 = Lists.newArrayList((char)1);
        @ResponseParam
        public List<Double> a40 = Lists.newArrayList(1.0D);
        @ResponseParam
        public List<Float> a41 = Lists.newArrayList(1.0f);
        @ResponseParam
        public List<Integer> a42 = Lists.newArrayList(1);
        @ResponseParam
        public List<Long> a43 = Lists.newArrayList(1L);
        @ResponseParam
        public List<Short> a44 = Lists.newArrayList((short)1);
 
        @ResponseParam
        public ClassB[] a45 = new ClassB[] {new ClassB("1")};
        @ResponseParam
        public List<ClassB> a46 = Lists.newArrayList(new ClassB("1"));
        @ResponseParam
        public List<byte[]> a47 = Lists.newArrayList(new byte[] {(byte)1});
        @ResponseParam
        public List<ClassB[]> a48 = Lists.newArrayList(new ClassB[][] {new ClassB[] {new ClassB("1")}});
        
        @ResponseParam
        public List<boolean[]> a49 = Lists.newArrayList(new boolean[][] {new boolean[] {true}});
        @ResponseParam
        public List<char[]> a50 = Lists.newArrayList(new char[][] {new char[] {(char)1}});
        @ResponseParam
        public List<double[]> a51 = Lists.newArrayList(new double[][] {new double[] {1.0}});
        @ResponseParam
        public List<float[]> a52 = Lists.newArrayList(new float[][] {new float[] {1.0f}});
        @ResponseParam
        public List<int[]> a53 = Lists.newArrayList(new int[][] {new int[] {1}});
        @ResponseParam
        public List<long[]> a54 = Lists.newArrayList(new long[][] {new long[] {1L}});
        @ResponseParam
        public List<short[]> a55 = Lists.newArrayList(new short[][] {new short[] {(short)1}});
        
        @ResponseParam
        public List<Boolean[]> a56 = Lists.newArrayList(new Boolean[][] {new Boolean[] {true}});
        @ResponseParam
        public List<Byte[]> a57 = Lists.newArrayList(new Byte[][] {new Byte[] {(byte)1}});
        @ResponseParam
        public List<Character[]> a58 = Lists.newArrayList(new Character[][] {new Character[] {(char)1}});
        @ResponseParam
        public List<Double[]> a59 = Lists.newArrayList(new Double[][] {new Double[] {1D}});
        @ResponseParam
        public List<Float[]> a60 = Lists.newArrayList(new Float[][] {new Float[] {1F}});
        @ResponseParam
        public List<Integer[]> a61 = Lists.newArrayList(new Integer[][] {new Integer[] {1}});
        @ResponseParam
        public List<Long[]> a62 = Lists.newArrayList(new Long[][] {new Long[] {1L}});
        @ResponseParam
        public List<Short[]> a63 = Lists.newArrayList(new Short[][] {new Short[] {(short)1}});
        @ResponseParam
        public List<String[]> a64  = Lists.newArrayList(new String[][] {new String[] {"1"}});
    }
    
    @Data
    public static class ClassB {
        public ClassB() {}
        public ClassB(String value) {
            this.value = value;
        }
        @ResponseParam
        public String value;
    }
    
}
