package com.tvd12.ezyfox.sfs2x.testing.paramtypeparser;

import java.lang.reflect.Field;
import java.util.List;

import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.variables.VariableType;
import com.tvd12.ezyfox.sfs2x.serializer.ParamTypeParser;

import static org.testng.Assert.*;

public class ParamTypeParserTest {
    
    public static SFSDataType EXPECTED_DATATYPE[] = {
            SFSDataType.UTF_STRING, SFSDataType.BOOL, SFSDataType.BYTE, 
            SFSDataType.BYTE, SFSDataType.DOUBLE, SFSDataType.FLOAT,
            SFSDataType.INT, SFSDataType.LONG, SFSDataType.SHORT,
            
            SFSDataType.UTF_STRING, SFSDataType.BOOL, SFSDataType.BYTE, 
            SFSDataType.BYTE, SFSDataType.DOUBLE, SFSDataType.FLOAT,
            SFSDataType.INT, SFSDataType.LONG, SFSDataType.SHORT,
            
            SFSDataType.UTF_STRING_ARRAY, SFSDataType.BOOL_ARRAY, SFSDataType.BYTE_ARRAY, 
            SFSDataType.BYTE_ARRAY, SFSDataType.DOUBLE_ARRAY, SFSDataType.FLOAT_ARRAY,
            SFSDataType.INT_ARRAY, SFSDataType.LONG_ARRAY, SFSDataType.SHORT_ARRAY,
            
            SFSDataType.SFS_ARRAY, SFSDataType.SFS_ARRAY, SFSDataType.SFS_ARRAY, 
            SFSDataType.SFS_ARRAY, SFSDataType.SFS_ARRAY, SFSDataType.SFS_ARRAY,
            SFSDataType.SFS_ARRAY, SFSDataType.SFS_ARRAY, SFSDataType.SFS_ARRAY,
            
            SFSDataType.SFS_OBJECT, SFSDataType.SFS_OBJECT, SFSDataType.SFS_ARRAY
    };
    
    public static VariableType EXPECTED_VARIABLETYPE[] = {
            VariableType.STRING, VariableType.BOOL, VariableType.INT, 
            VariableType.INT, VariableType.DOUBLE, VariableType.DOUBLE,
            VariableType.INT, VariableType.DOUBLE, VariableType.INT,
            
            VariableType.STRING, VariableType.BOOL, VariableType.INT, 
            VariableType.INT, VariableType.DOUBLE, VariableType.DOUBLE,
            VariableType.INT, VariableType.DOUBLE, VariableType.INT,
            
            VariableType.ARRAY, VariableType.ARRAY, VariableType.ARRAY, 
            VariableType.ARRAY, VariableType.ARRAY, VariableType.ARRAY,
            VariableType.ARRAY, VariableType.ARRAY, VariableType.ARRAY,
            
            VariableType.ARRAY, VariableType.ARRAY, VariableType.ARRAY, 
            VariableType.ARRAY, VariableType.ARRAY, VariableType.ARRAY,
            VariableType.ARRAY, VariableType.ARRAY, VariableType.ARRAY,
            
            VariableType.OBJECT, VariableType.OBJECT, VariableType.ARRAY
    };
    
    public void testDataTypeValidCase(String[] args) {
        Field fields[] = ClassA.class.getDeclaredFields();
        for(int i = 0 ; i < fields.length ; i++) {
            SFSDataType type = ParamTypeParser.getParamType(fields[i]);
            assertEquals(EXPECTED_DATATYPE[i], type);
        }
    }
    
    public void testVariableTypeValidCase(String[] args) {
        Field fields[] = ClassA.class.getDeclaredFields();
        for(int i = 0 ; i < fields.length ; i++) {
            VariableType type = ParamTypeParser.getVariableType(fields[i].getType());
            assertEquals(EXPECTED_VARIABLETYPE[i], type);
        }
    }

    public static class ClassA {
        public String a0;
        public boolean a1;
        public byte a2;
        public char a3;
        public double a4;
        public float a5;
        public int a6;
        public long a7;
        public short a8;
        
        public String a9;
        public Boolean a10;
        public Byte a11;
        public Character a12;
        public Double a13;
        public Float a14;
        public Integer a15;
        public Long a16;
        public Short a17;
        
        public String[] a18;
        public boolean[] a19;
        public byte[] a20;
        public char[] a21;
        public double[] a22;
        public float[] a23;
        public int[] a24;
        public long[] a25;
        public short[] a26;
        
        public String[] a27;
        public Boolean[] a28;
        public Byte[] a29;
        public Character[] a30;
        public Double[] a31;
        public Float[] a32;
        public Integer[] a33;
        public Long[] a34;
        public Short[] a35;
        
        public List<String> a36;
        public List<Boolean> a37;
        public List<Byte> a38;
        public List<Character> a39;
        public List<Double> a40;
        public List<Float> a41;
        public List<Integer> a42;
        public List<Long> a43;
        public List<Short> a44;
        
        public Object a45;
        public ClassA a46;
        
        public int[][] ab7;
    }
    
}
