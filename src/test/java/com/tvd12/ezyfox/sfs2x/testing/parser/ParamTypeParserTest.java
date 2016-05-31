package com.tvd12.ezyfox.sfs2x.testing.parser;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.variables.VariableType;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.sfs2x.serializer.ParamTypeParser;
import com.tvd12.test.base.BaseTest;

import static com.tvd12.ezyfox.sfs2x.serializer.ParamTypeParser.*;
import static org.testng.Assert.*;

import java.lang.reflect.Field;
import java.util.List;

public class ParamTypeParserTest extends BaseTest {

    @Test
    public void test() throws ExtensionException {
        assertEquals(SFSDataType.BOOL, getParamType(Boolean.class));
        assertEquals(SFSDataType.BYTE, getParamType(Byte.class));
        assertEquals(SFSDataType.BYTE, getParamType(Character.class));
        assertEquals(SFSDataType.DOUBLE, getParamType(Double.class));
        assertEquals(SFSDataType.FLOAT, getParamType(Float.class));
        assertEquals(SFSDataType.INT, getParamType(Integer.class));
        assertEquals(SFSDataType.LONG, getParamType(Long.class));
        assertEquals(SFSDataType.SHORT, getParamType(Short.class));
        assertEquals(SFSDataType.SFS_OBJECT, getParamType(ClassA.class));
        assertEquals(SFSDataType.SFS_ARRAY, getParamType(ClassA[].class));
        assertEquals(SFSDataType.SFS_ARRAY, getParamType(List.class));
        
        assertEquals(VariableType.BOOL, getVariableType(Boolean.class));
        assertEquals(VariableType.INT, getVariableType(Byte.class));
        assertEquals(VariableType.INT, getVariableType(Character.class));
        assertEquals(VariableType.DOUBLE, getVariableType(Double.class));
        assertEquals(VariableType.DOUBLE, getVariableType(Float.class));
        assertEquals(VariableType.INT, getVariableType(Integer.class));
        assertEquals(VariableType.DOUBLE, getVariableType(Long.class));
        assertEquals(VariableType.INT, getVariableType(Short.class));
        assertEquals(VariableType.STRING, getVariableType(String.class));
        assertEquals(VariableType.ARRAY, getVariableType(ClassA[].class));
        assertEquals(VariableType.ARRAY, getVariableType(List.class));
        assertEquals(VariableType.OBJECT, getVariableType(ClassA.class));
        assertEquals(VariableType.NULL, getVariableType(Void.class));
        
        Field field = ReflectFieldUtil.getField("value", ClassA.class);
        assertEquals(SFSDataType.UTF_STRING, getParamType(field));
        
    }
    
    @Override
    public Class<?> getTestClass() {
        return ParamTypeParser.class;
    }
    
    public static class ClassA {
        public String value;
    }
    
}
