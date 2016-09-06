package com.tvd12.ezyfox.sfs2x.testing.data;

import static org.testng.Assert.assertEquals;

import java.util.Collection;

import org.testng.annotations.Test;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;
import com.tvd12.ezyfox.sfs2x.data.impl.ParamTransformer;
import com.tvd12.ezyfox.sfs2x.testing.command.BaseCommandTest;

import lombok.Data;

/**
 * @author tavandung12
 * Created on Sep 5, 2016
 *
 */
public class ParamTransformerTest extends BaseCommandTest {
    
    public ParamTransformerTest() {
        super();
        init();
    }

    @Test
    public void test() {
        ParamTransformer transformer = new ParamTransformer(context);
        SFSDataWrapper wrapper = transformer.transform(new ResponseData());
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_OBJECT);
        ISFSObject value = (ISFSObject)wrapper.getObject();
        assertEquals(value.getBool("a0"), Boolean.TRUE);
        assertEquals(value.getByte("a1"), new Byte((byte)1));
        assertEquals(value.getByte("a2"), new Byte((byte)2));
        assertEquals(value.getDouble("a3"), 3D);
        assertEquals(value.getFloat("a4"), 4F);
        assertEquals(value.getInt("a5"), new Integer(5));
        assertEquals(value.getLong("a6"), new Long(6));
        assertEquals(value.getShort("a7"), new Short((short)7));
        assertEquals(value.getUtfString("a8"), "8");
        
        Collection<Boolean> a9 = value.getBoolArray("a9");
        assertEquals(a9.toArray(new Boolean[2]), new Boolean[]{true, false});
        
        assertEquals(value.getByteArray("a10"), new byte[] {1, 2});
        assertEquals(value.getByteArray("a11"), new byte[] {1, 2});
        
        Collection<Double> a12 = value.getDoubleArray("a12");
        assertEquals(a12.toArray(new Double[2]), new Double[]{1D, 2D});
        
        Collection<Float> a13 = value.getFloatArray("a13");
        assertEquals(a13.toArray(new Float[2]), new Float[]{1F, 2F});
        
        Collection<Integer> a14 = value.getIntArray("a14");
        assertEquals(a14.toArray(new Integer[2]), new Integer[]{1, 2});
        
        Collection<Long> a15 = value.getLongArray("a15");
        assertEquals(a15.toArray(new Long[2]), new Long[]{1L, 2L});
        
        Collection<Short> a16 = value.getShortArray("a16");
        assertEquals(a16.toArray(new Short[2]), new Short[]{1, 2});
        
        Collection<String> a17 = value.getUtfStringArray("a17");
        assertEquals(a17.toArray(new String[2]), new String[]{"1", "2"});
        
        ISFSObject object = value.getSFSObject("object");
        assertEquals(object.getUtfString("value"), "value");
        
        ISFSArray array = value.getSFSArray("array");
        assertEquals(array.size(), 2);
        assertEquals(array.getSFSObject(0).getUtfString("value"), "value");
        assertEquals(array.getSFSObject(1).getUtfString("value"), "value");
    }
    
    private void init() {
        context.addObjectSerializer(Second.class, new SecondSerializer());
        context.addObjectSerializer(ResponseData.class, new ResponseDataSerializer());
    }
    
    public static class SecondSerializer implements ObjectSerializer {
        @Override
        public Parameters serialize(Object object) {
            Second second = (Second)object;
            Parameters answer = new ParameterWrapper();
            answer.set("value", second.getValue());
            return answer;
        }
    }
    
    public static class ResponseDataSerializer implements ObjectSerializer {
        @Override
        public Parameters serialize(Object object) {
            ResponseData data = (ResponseData)object;
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
        
        private Second object = new Second();
        private Second array[] = {
                new Second(), new Second()
        };
        
    }
    
    @Data
    public static class Second {
        private String value = "value";
    }
    
}
