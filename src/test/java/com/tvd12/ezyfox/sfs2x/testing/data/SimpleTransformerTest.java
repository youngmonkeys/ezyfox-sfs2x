package com.tvd12.ezyfox.sfs2x.testing.data;

import static org.testng.Assert.assertEquals;

import java.util.Collection;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.data.SFSDataType;
import com.smartfoxserver.v2.entities.data.SFSDataWrapper;
import com.tvd12.ezyfox.sfs2x.data.impl.SimpleTransformer;

/**
 * @author tavandung12
 * Created on May 30, 2016
 *
 */
public class SimpleTransformerTest {
    
    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        SimpleTransformer transformer = new SimpleTransformer();
        assertEquals(transformer.transform(null).getTypeId(), SFSDataType.NULL);
        
        // primitive type
        boolean booleanValue = true;
        SFSDataWrapper wrapper = null;
        wrapper = transformer.transform(booleanValue);
        assertEquals(wrapper.getTypeId(), SFSDataType.BOOL);
        assertEquals(wrapper.getObject(), true);
        
        wrapper = transformer.transform((byte)1);
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE);
        assertEquals(wrapper.getObject(), (byte)1);
        
        wrapper = transformer.transform((char)1);
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE);
        assertEquals(wrapper.getObject(), (byte)1);
        
        wrapper = transformer.transform(1.0);
        assertEquals(wrapper.getTypeId(), SFSDataType.DOUBLE);
        assertEquals(wrapper.getObject(), 1.0);
        
        wrapper = transformer.transform(1.0f);
        assertEquals(wrapper.getTypeId(), SFSDataType.FLOAT);
        assertEquals(wrapper.getObject(), 1.0f);
        
        wrapper = transformer.transform(1);
        assertEquals(wrapper.getTypeId(), SFSDataType.INT);
        assertEquals(wrapper.getObject(), 1);
        
        wrapper = transformer.transform(1L);
        assertEquals(wrapper.getTypeId(), SFSDataType.LONG);
        assertEquals(wrapper.getObject(), 1L);
        
        wrapper = transformer.transform((short)1);
        assertEquals(wrapper.getTypeId(), SFSDataType.SHORT);
        assertEquals(wrapper.getObject(), (short)1);
        
        // wrapper type test
        wrapper = transformer.transform(new Boolean(true));
        assertEquals(wrapper.getTypeId(), SFSDataType.BOOL);
        assertEquals(wrapper.getObject(), true);
        
        wrapper = transformer.transform(new Byte((byte)1));
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE);
        assertEquals(wrapper.getObject(), (byte)1);
        
        wrapper = transformer.transform(new Character((char)1));
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE);
        assertEquals(wrapper.getObject(), (byte)1);
        
        wrapper = transformer.transform(new Double(1.0));
        assertEquals(wrapper.getTypeId(), SFSDataType.DOUBLE);
        assertEquals(wrapper.getObject(), 1.0);
        
        wrapper = transformer.transform(new Float(1.0f));
        assertEquals(wrapper.getTypeId(), SFSDataType.FLOAT);
        assertEquals(wrapper.getObject(), 1.0f);
        
        wrapper = transformer.transform(new Integer(1));
        assertEquals(wrapper.getTypeId(), SFSDataType.INT);
        assertEquals(wrapper.getObject(), 1);
        
        wrapper = transformer.transform(new Long(1L));
        assertEquals(wrapper.getTypeId(), SFSDataType.LONG);
        assertEquals(wrapper.getObject(), 1L);
        
        wrapper = transformer.transform(new Short((short)1));
        assertEquals(wrapper.getTypeId(), SFSDataType.SHORT);
        assertEquals(wrapper.getObject(), (short)1);
        
        // primitive array
        wrapper = transformer.transform(new boolean[] {true});
        assertEquals(wrapper.getTypeId(), SFSDataType.BOOL_ARRAY);
        assertEquals(((Collection<Boolean>)wrapper.getObject()).toArray(new Boolean[1]), new Boolean[] {true});
        
        wrapper = transformer.transform(new byte[] {(byte)1});
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE_ARRAY);
        assertEquals(wrapper.getObject(), new byte[] {(byte)1});
        
        wrapper = transformer.transform(new char[] {(char)1});
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE_ARRAY);
        assertEquals(wrapper.getObject(), new byte[] {(byte)1});
        
        wrapper = transformer.transform(new double[] {1.0});
        assertEquals(wrapper.getTypeId(), SFSDataType.DOUBLE_ARRAY);
        assertEquals(((Collection<Double>)wrapper.getObject()).toArray(new Double[1]), new Double[] {1.0});
        
        wrapper = transformer.transform(new float[] {1.0f});
        assertEquals(wrapper.getTypeId(), SFSDataType.FLOAT_ARRAY);
        assertEquals(((Collection<Float>)wrapper.getObject()).toArray(new Float[1]), new Float[] {1.0f});
        
        wrapper = transformer.transform(new int[] {1});
        assertEquals(wrapper.getTypeId(), SFSDataType.INT_ARRAY);
        assertEquals(((Collection<Integer>)wrapper.getObject()).toArray(new Integer[1]), new Integer[] {1});
        
        wrapper = transformer.transform(new long[] {1L});
        assertEquals(wrapper.getTypeId(), SFSDataType.LONG_ARRAY);
        assertEquals(((Collection<Long>)wrapper.getObject()).toArray(new Long[1]), new Long[] {1L});
        
        wrapper = transformer.transform(new short[] {(short)1});
        assertEquals(wrapper.getTypeId(), SFSDataType.SHORT_ARRAY);
        assertEquals(((Collection<Short>)wrapper.getObject()).toArray(new Short[1]), new Short[] {(short)1});
        
      //wrapper array 
        wrapper = transformer.transform(new Boolean[] {true});
        assertEquals(wrapper.getTypeId(), SFSDataType.BOOL_ARRAY);
        assertEquals(((Collection<Boolean>)wrapper.getObject()).toArray(new Boolean[1]), new Boolean[] {true});
        
        wrapper = transformer.transform(new Byte[] {(byte)1});
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE_ARRAY);
        assertEquals(wrapper.getObject(), new byte[] {(byte)1});
        
        wrapper = transformer.transform(new Character[] {(char)1});
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE_ARRAY);
        assertEquals(wrapper.getObject(), new byte[] {(byte)1});
        
        wrapper = transformer.transform(new Double[] {1.0});
        assertEquals(wrapper.getTypeId(), SFSDataType.DOUBLE_ARRAY);
        assertEquals(((Collection<Double>)wrapper.getObject()).toArray(new Double[1]), new Double[] {1.0});
        
        wrapper = transformer.transform(new Float[] {1.0f});
        assertEquals(wrapper.getTypeId(), SFSDataType.FLOAT_ARRAY);
        assertEquals(((Collection<Float>)wrapper.getObject()).toArray(new Float[1]), new Float[] {1.0f});
        
        wrapper = transformer.transform(new Integer[] {1});
        assertEquals(wrapper.getTypeId(), SFSDataType.INT_ARRAY);
        assertEquals(((Collection<Integer>)wrapper.getObject()).toArray(new Integer[1]), new Integer[] {1});
        
        wrapper = transformer.transform(new Long[] {1L});
        assertEquals(wrapper.getTypeId(), SFSDataType.LONG_ARRAY);
        assertEquals(((Collection<Long>)wrapper.getObject()).toArray(new Long[1]), new Long[] {1L});
        
        wrapper = transformer.transform(new Short[] {(short)1});
        assertEquals(wrapper.getTypeId(), SFSDataType.SHORT_ARRAY);
        assertEquals(((Collection<Short>)wrapper.getObject()).toArray(new Short[1]), new Short[] {(short)1});
        
      //collection 
        wrapper = transformer.transform(Lists.newArrayList(true));
        assertEquals(wrapper.getTypeId(), SFSDataType.BOOL_ARRAY);
        assertEquals(((Collection<Boolean>)wrapper.getObject()).toArray(new Boolean[1]), new Boolean[] {true});
        
        wrapper = transformer.transform(Lists.newArrayList((byte)1));
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE_ARRAY);
        assertEquals(wrapper.getObject(), new byte[] {(byte)1});
        
        wrapper = transformer.transform(Lists.newArrayList((char)1));
        assertEquals(wrapper.getTypeId(), SFSDataType.BYTE_ARRAY);
        assertEquals(wrapper.getObject(), new byte[] {(byte)1});
        
        wrapper = transformer.transform(Lists.newArrayList(1.0));
        assertEquals(wrapper.getTypeId(), SFSDataType.DOUBLE_ARRAY);
        assertEquals(((Collection<Double>)wrapper.getObject()).toArray(new Double[1]), new Double[] {1.0});
        
        wrapper = transformer.transform(Lists.newArrayList(1.0f));
        assertEquals(wrapper.getTypeId(), SFSDataType.FLOAT_ARRAY);
        assertEquals(((Collection<Float>)wrapper.getObject()).toArray(new Float[1]), new Float[] {1.0f});
        
        wrapper = transformer.transform(Lists.newArrayList(1));
        assertEquals(wrapper.getTypeId(), SFSDataType.INT_ARRAY);
        assertEquals(((Collection<Integer>)wrapper.getObject()).toArray(new Integer[1]), new Integer[] {1});
        
        wrapper = transformer.transform(Lists.newArrayList(1L));
        assertEquals(wrapper.getTypeId(), SFSDataType.LONG_ARRAY);
        assertEquals(((Collection<Long>)wrapper.getObject()).toArray(new Long[1]), new Long[] {1L});
        
        wrapper = transformer.transform(Lists.newArrayList((short)1));
        assertEquals(wrapper.getTypeId(), SFSDataType.SHORT_ARRAY);
        assertEquals(((Collection<Short>)wrapper.getObject()).toArray(new Short[1]), new Short[] {(short)1});
        
        //string
        wrapper = transformer.transform("abc");
        assertEquals(wrapper.getTypeId(), SFSDataType.UTF_STRING);
        assertEquals(wrapper.getObject(), "abc");
        
        wrapper = transformer.transform(new String[] {"abc"});
        assertEquals(wrapper.getTypeId(), SFSDataType.UTF_STRING_ARRAY);
        assertEquals(((Collection<String>)wrapper.getObject()).toArray(new String[1]), new String[] {"abc"});
        
        wrapper = transformer.transform(Lists.newArrayList("abc"));
        assertEquals(wrapper.getTypeId(), SFSDataType.UTF_STRING_ARRAY);
        assertEquals(((Collection<String>)wrapper.getObject()).toArray(new String[1]), new String[] {"abc"});
        
        wrapper = transformer.transform(Lists.newArrayList());
        assertEquals(wrapper.getTypeId(), SFSDataType.NULL);
        
        wrapper = transformer.transform(Lists.newArrayList(new int[] {10}, new int[] {11}));
        assertEquals(wrapper.getTypeId(), SFSDataType.SFS_ARRAY);
    }
    
    @Test
    public void testValidCase1() {
        SimpleTransformer transformer = new SimpleTransformer();
        transformer.transform(new Object());
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testInvalidCase2() {
        SimpleTransformer transformer = new SimpleTransformer();
        transformer.transform(Lists.newArrayList(new Object()));
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testInvalidCase3() {
        SimpleTransformer transformer = new SimpleTransformer();
        transformer.transform(new Void[1]);
    }
    
}
