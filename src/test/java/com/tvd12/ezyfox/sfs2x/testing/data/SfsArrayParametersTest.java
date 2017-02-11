package com.tvd12.ezyfox.sfs2x.testing.data;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.transport.Arraymeters;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.sfs2x.data.impl.SfsArrayParameters;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on Feb 11, 2017
 *
 */
public class SfsArrayParametersTest {

	@Test
	public void test() {
		ISFSArray array = new SFSArray();
		array.addBool(true);
		array.addByte((byte)1);
		array.addDouble(1D);
		array.addFloat(1F);
		array.addInt(1);
		array.addLong(1L);
		array.addShort((short)1);
		array.addUtfString("1");
		
		SfsArrayParameters meters = new SfsArrayParameters(array);
		
		assertEquals(meters.get(0), Boolean.TRUE);
		
		assertEquals(meters.get(0, Boolean.class), Boolean.TRUE);
		assertEquals(meters.get(1, Byte.class), new Byte((byte)1));
		assertEquals(new Character((char)array.getByte(1).byteValue()), new Character((char)1));
		assertEquals(meters.get(1, Character.class), new Character((char)1));
		assertEquals(meters.get(2, Double.class), 1D);
		assertEquals(meters.get(3, Float.class), 1F);
		assertEquals(meters.get(4, Integer.class), new Integer(1));
		assertEquals(meters.get(5, Long.class), new Long(1));
		assertEquals(meters.get(6, Short.class), new Short((short)1));
		assertEquals(meters.get(7, String.class), "1");
		
		assertEquals(meters.get(0, boolean.class), Boolean.TRUE);
		assertEquals(meters.get(1, byte.class), new Byte((byte)1));
		assertEquals(new Character((char)array.getByte(1).byteValue()), new Character((char)1));
		assertEquals(meters.get(1, char.class), new Character((char)1));
		assertEquals(meters.get(2, double.class), 1D);
		assertEquals(meters.get(3, float.class), 1F);
		assertEquals(meters.get(4, int.class), new Integer(1));
		assertEquals(meters.get(5, long.class), new Long(1));
		assertEquals(meters.get(6, short.class), new Short((short)1));
		assertEquals(meters.get(7, String.class), "1");
		
		array.addBoolArray(Lists.newArrayList(true));
		array.addByteArray(new byte[] {1});
		array.addDoubleArray(Lists.newArrayList(1D));
		array.addFloatArray(Lists.newArrayList(1F));
		array.addIntArray(Lists.newArrayList(1));
		array.addLongArray(Lists.newArrayList(1L));
		array.addShortArray(Lists.newArrayList((short)1));
		array.addUtfStringArray(Lists.newArrayList("1"));
		
		assertEquals(meters.get(8, boolean[].class), new boolean[] {Boolean.TRUE});
		assertEquals(meters.get(9, byte[].class),  new byte[] {new Byte((byte)1)});
		assertEquals(meters.get(9, char[].class),  new char[] {new Character((char)1)});
		assertEquals(meters.get(10, double[].class),  new double[] {1D});
		assertEquals(meters.get(11, float[].class),  new float[] {1F});
		assertEquals(meters.get(12, int[].class),  new int[] {new Integer(1)});
		assertEquals(meters.get(13, long[].class),  new long[] {new Long(1)});
		assertEquals(meters.get(14, short[].class),  new short[] {new Short((short)1)});
		assertEquals(meters.get(15, String[].class),  new String[] {"1"});
		
		assertEquals(meters.get(8, Boolean[].class), new Boolean[] {Boolean.TRUE});
		assertEquals(meters.get(9, Byte[].class),  new Byte[] {new Byte((byte)1)});
		assertEquals(meters.get(9, Character[].class),  new Character[] {new Character((char)1)});
		assertEquals(meters.get(10, Double[].class),  new Double[] {1D});
		assertEquals(meters.get(11, Float[].class),  new Float[] {1F});
		assertEquals(meters.get(12, Integer[].class),  new Integer[] {new Integer(1)});
		assertEquals(meters.get(13, Long[].class),  new Long[] {new Long(1)});
		assertEquals(meters.get(14, Short[].class),  new Short[] {new Short((short)1)});
		assertEquals(meters.get(15, String[].class),  new String[] {"1"});
		
		ISFSObject object1 = new SFSObject();
		object1.putInt("a", 1);
		array.addSFSObject(object1);
		assertEquals(meters.get(16, Parameters.class).get("a"),  1);
		
		ISFSObject object2 = new SFSObject();
		object2.putInt("b", 2);
		ISFSObject object3 = new SFSObject();
		object3.putInt("c", 3);
		ISFSArray array2 = new SFSArray();
		array2.addSFSObject(object2);
		array2.addSFSObject(object3);
		array.addSFSArray(array2);
		
		assertEquals(array.getSFSArray(17).getSFSObject(0).getInt("b"), new Integer(2));
		assertEquals(meters.get(17,  Parameters[].class)[0].get("b"), 2);
		assertEquals(meters.get(17,  Parameters[].class)[1].get("c"), 3);
		
		assertEquals(meters.get(17,  Arraymeters.class).get(0, Parameters.class).get("b"), 2);
		assertEquals(meters.get(17,  Arraymeters.class).get(1, Parameters.class).get("c"), 3);
		
		assertEquals(meters.size(), 18);
		meters.add(new Integer(1), new Integer(2));
		assertEquals(meters.size(), 20);
		assertEquals(meters.get(18), 1);
		assertEquals(meters.get(19), 2);
		meters.remove(19);
		assertEquals(meters.size(), 19);
		
	}
	
	@Test
	public void test2() {
		SfsArrayParameters meters = new SfsArrayParameters(new SFSArray());
		assertEquals(meters.size(), 0);
	}
	
	@Test(expectedExceptions = UnsupportedOperationException.class)
	public void test3() {
		SfsArrayParameters meters = new SfsArrayParameters(new SFSArray());
		meters.set(0, 1);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test4() {
		SfsArrayParameters meters = new SfsArrayParameters(new SFSArray());
		meters.get(0, Void.class);
	}
	
}
