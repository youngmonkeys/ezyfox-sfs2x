package com.tvd12.ezyfox.sfs2x.testing.data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.reflect.ReflectConvertUtil;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.sfs2x.data.impl.ParamTransformer;
import com.tvd12.ezyfox.sfs2x.data.impl.SfsParameters;
import com.tvd12.ezyfox.sfs2x.testing.command.BaseCommandTest;

import lombok.Data;

/**
 * @author tavandung12
 * Created on Sep 5, 2016
 *
 */
public class SfsObjectTransformerTest extends BaseCommandTest {
    
    public SfsObjectTransformerTest() {
        super();
        init();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        ParamTransformer transformer = new ParamTransformer(context);
        assertNull(transformer.transform(null).getObject());
        SFSDataWrapper wrapper = transformer.transform(new ResponseData());
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_OBJECT);
        ISFSObject value = (ISFSObject)wrapper.getObject();
        value.putNull("nullField");
        
        Parameters params = new SfsParameters(value);
        
        ResponseData data = new ResponseData();
        context.getObjectDeserializer(ResponseData.class).deserialize(data, params);
        
        assertEquals(data.isA0(), true);
        assertEquals(data.getA1(), (byte)1);
        assertEquals(data.getA2(), (byte)2);
        assertEquals(data.getA3(), 3D);
        assertEquals(data.getA4(), 4F);
        assertEquals(data.getA5(), 5);
        assertEquals(data.getA6(), 6);
        assertEquals(data.getA7(), (short)7);
        assertEquals(data.getA8(), "8");
        
        assertEquals(data.getA9(), new boolean[]{true, false});
        
        assertEquals(data.getA10(), new byte[] {1, 2});
        assertEquals(data.getA11(), new char[] {1, 2});
        
        assertEquals(data.getA12(), new double[]{1D, 2D});
        
        assertEquals(data.getA13(), new float[]{1F, 2F});
        
        assertEquals(data.getA14(), new int[]{1, 2});
        
        assertEquals(data.getA15(), new long[]{1L, 2L});
        
        assertEquals(data.getA16(), new short[]{1, 2});
        
        assertEquals(data.getA17(), new String[]{"1", "2"});
        
        assertEquals(data.getObject().getValue(), "value");
        
        assertEquals(data.getArray().length, 2);
        assertEquals(data.getArray()[0].getValue(), "value");
        assertEquals(data.getArray()[1].getValue(), "value");
    }
    
    private void init() {
        context.addObjectSerializer(Second.class, new SecondSerializer());
        context.addObjectSerializer(ResponseData.class, new ResponseDataSerializer());
        context.addObjectDeserializer(Second.class, new SecondDeserializer());
        context.addObjectDeserializer(ResponseData.class, new ResponseDataDeserializer());
    }
    
    public static class SecondDeserializer implements ObjectDeserializer<Second> {
        
        @Override
        public Second deserialize(Second second, Parameters params) {
            second.setValue(params.get("value", String.class));
            return second;
        }
        
    }
    
    public static class ResponseDataDeserializer implements ObjectDeserializer<ResponseData> {
        
        @Override
        public ResponseData deserialize(ResponseData data, Parameters params) {
            data.setA0(params.get("a0", boolean.class));
            data.setA1(params.get("a1", byte.class));
            data.setA2((char)params.get("a2", byte.class).byteValue());
            data.setA3(params.get("a3", double.class));
            data.setA4(params.get("a4", float.class));
            data.setA5(params.get("a5", int.class));
            data.setA6(params.get("a6", long.class));
            data.setA7(params.get("a7", short.class));
            data.setA8(params.get("a8", String.class));
            data.setA9(params.get("a9", boolean[].class));
            data.setA10(params.get("a10", byte[].class));
            data.setA11(ReflectConvertUtil.byteArrayToCharArray(params.get("a11", byte[].class)));
            data.setA12(params.get("a12", double[].class));
            data.setA13(params.get("a13", float[].class));
            data.setA14(params.get("a14", int[].class));
            data.setA15(params.get("a15", long[].class));
            data.setA16(params.get("a16", short[].class));
            data.setA17(params.get("a17", String[].class));
            
            data.getObject().setValue(params.get("object", Parameters.class).get("value", String.class));
            Parameters[] arrayParams = params.get("array", Parameters[].class);
            
            Second array[] = new Second[arrayParams.length];
            int count = 0;
            for(Parameters p : arrayParams) {
                Second s = new Second();
                s.setValue(p.get("value", String.class));
                array[count ++] = s;
            }
            data.setArray(array);
            
            data.setNullField(params.get("nullField", String.class));
            
            return data;
        }
    }
    
    public static class SecondSerializer implements ObjectSerializer<Second> {
        @Override
        public Parameters serialize(Second second) {
            Parameters answer = new ParameterWrapper();
            answer.set("value", second.getValue());
            return answer;
        }
    }
    
    public static class ResponseDataSerializer implements ObjectSerializer<ResponseData> {
        @Override
        public Parameters serialize(ResponseData data) {
            Parameters answer = new ParameterWrapper();
            answer.set("a0", data.isA0());
            answer.set("a1", data.getA1());
            answer.set("a2", data.getA2());
            answer.set("a3", data.getA3());
            answer.set("a4", data.getA4());
            answer.set("a5", data.getA5());
            answer.set("a6", data.getA6());
            answer.set("a7", data.getA7());
            answer.set("a8", data.getA8());
            answer.set("a9", data.getA9());
            answer.set("a10", data.getA10());
            answer.set("a11", data.getA11());
            answer.set("a12", data.getA12());
            answer.set("a13", data.getA13());
            answer.set("a14", data.getA14());
            answer.set("a15", data.getA15());
            answer.set("a16", data.getA16());
            answer.set("a17", data.getA17());
            answer.set("a18", data.getA18());
            answer.set("a19", data.getA19());
            
            Parameters objectParams = new ParameterWrapper();
            objectParams.set("value", data.getObject().getValue());
            answer.set("object", objectParams);
            
            Parameters[] arrayParams = new Parameters[data.getArray().length];
            int count = 0;
            for(Second second : data.getArray()) {
                Parameters tmp = new ParameterWrapper();
                tmp.set("value", second.getValue());
                arrayParams[count ++] = tmp;
            }
            
            answer.set("array", arrayParams);
            
            return answer;
        }
    }
    
    @Data
    public static class ResponseData {
        private boolean a0 = true;
        private byte a1 = 1;
        private char a2 = 2;
        private double a3 = 3;
        private float a4 = 4;
        private int a5 = 5;
        private long a6 = 6;
        private short a7 = 7;
        private String a8 = "8";
        
        private boolean a9[] = {true, false};
        private byte a10[] = {1, 2};
        private char a11[] = {1, 2};
        private double a12[] = {1, 2};
        private float a13[] = {1, 2};
        private int a14[] = {1, 2};
        private long a15[] = {1, 2};
        private short a16[] = {1, 2};
        private String a17[] = {"1", "2"};
        private String a18[][] = {{"1", "2"}, {"1", "2"}};
        private Second[][] a19 = {{new Second(), new Second()}, {new Second(), new Second()}};
        
        private Second object = new Second();
        private Second array[] = {
                new Second(), new Second()
        };
        
        private String nullField;
        
        public void reset() {
            setA0(false);
            setA1((byte)0);
            setA2((char)0);
            setA3(0D);
            setA4(0F);
            setA5(0);
            setA6(0);
            setA7((short)0);
            setA8(null);
            
            setA9(null);
            setA10(null);
            setA11(null);
            setA12(null);
            setA13(null);
            setA14(null);
            setA15(null);
            setA16(null);
            setA17(null);
            
            getObject().setValue(null);
            setArray(null);
            setNullField("abc");
        }
        
    }
    
    @Data
    public static class Second {
        private String value = "value";
        
        public void reset() {
            setValue(null);
        }
    }
    
}
