package com.tvd12.ezyfox.sfs2x.testing.data;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.core.transport.Arraymeters;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.sfs2x.data.impl.SfsParameters;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tavandung12
 * Created on Feb 9, 2017
 *
 */
public class SfsParametersTest {

	@Test
	public void test() {
		ISFSObject object = new SFSObject();
		object.putBool("00", true);
		object.putByte("01", (byte)'a');
		object.putDouble("02", 1.0D);
		object.putFloat("03", 1.0F);
		object.putInt("04", 1);
		object.putLong("05", 1L);
		object.putShort("06", (short)1);
		object.putUtfString("07", "1");
		
		Parameters params = new SfsParameters(object);
		assertEquals(params.get("00", boolean.class), Boolean.TRUE);
		assertEquals(params.get("01", byte.class), new Byte((byte)'a'));
		assertEquals(params.get("01", char.class), new Character('a'));
		assertEquals(params.get("02", double.class), new Double(1.0D));
		assertEquals(params.get("03", float.class), new Float(1.0F));
		assertEquals(params.get("04", int.class), new Integer(1));
		assertEquals(params.get("05", long.class), new Long(1));
		assertEquals(params.get("06", short.class), new Short((short)1));
		assertEquals(params.get("07", String.class), new String("1"));
		
		assertEquals(params.get("00", Boolean.class), Boolean.TRUE);
		assertEquals(params.get("01", Byte.class), new Byte((byte)'a'));
		assertEquals(params.get("01", Character.class), new Character('a'));
		assertEquals(params.get("02", Double.class), new Double(1.0D));
		assertEquals(params.get("03", Float.class), new Float(1.0F));
		assertEquals(params.get("04", Integer.class), new Integer(1));
		assertEquals(params.get("05", Long.class), new Long(1));
		assertEquals(params.get("06", Short.class), new Short((short)1));
		assertEquals(params.get("07", String.class), new String("1"));
		
		assertTrue(params.size() > 0);
		assertFalse(params.isEmpty());
		assertTrue(params.contain("00"));
		assertTrue(params.keys().contains("00"));
		
		params.clear();
		
		params.set("set1", 10);
		Map<Object, Object> map = new HashMap<>();
		map.put("set2", 11);
		params.setAll(map);
		assertEquals(params.get("set1", Integer.class), new Integer(10));
		assertEquals(params.get("set2", Integer.class), new Integer(11));
		
		object.putBoolArray("08", Lists.newArrayList(true));
		object.putByteArray("09", new byte[] {(byte)'a'});
		object.putDoubleArray("10", Lists.newArrayList(1.0D));
		object.putFloatArray("11", Lists.newArrayList(1.0F));
		object.putIntArray("12", Lists.newArrayList(1));
		object.putLongArray("13", Lists.newArrayList(1L));
		object.putShortArray("14", Lists.newArrayList((short)1));
		object.putUtfStringArray("15", Lists.newArrayList("1"));
		
		assertEquals(params.get("08", Boolean[].class), new Boolean[] {true});
		assertEquals(params.get("09", Byte[].class), new Byte[] {(byte)'a'});
		assertEquals(params.get("09", Character[].class), new Character[] {'a'});
		assertEquals(params.get("10", Double[].class), new Double[] {1.0D});
		assertEquals(params.get("11", Float[].class), new Float[] {1.0F});
		assertEquals(params.get("12", Integer[].class), new Integer[] {1});
		assertEquals(params.get("13", Long[].class), new Long[] {1L});
		assertEquals(params.get("14", Short[].class), new Short[] {(short)1});
		assertEquals(params.get("15", String[].class), new String[] {"1"});
		
		assertEquals(params.get("09", char[].class), new char[] {'a'});
		
		
		ISFSArray array1 = new SFSArray();
		array1.addBool(true);
		array1.addByte((byte)1);
		array1.addDouble(1D);
		array1.addFloat(1F);
		array1.addInt(1);
		array1.addLong(1L);
		array1.addShort((short)1);
		array1.addUtfString("1");
		
		object.putSFSArray("array1", array1);
		
		Arraymeters array = params.get("array1", Arraymeters.class);
		
		assertEquals(array.get(0, Boolean.class), Boolean.TRUE);
	}
	
	@Test
	public void test2() {
		ISFSObject object = new SFSObject();
		Parameters params = new SfsParameters(object);
		assertTrue(params.isEmpty());
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void test3() {
		ISFSObject object = new SFSObject();
		Parameters params = new SfsParameters(object);
		params.get("abc", Void.class);
	}
	
	@Test(expectedExceptions = UnsupportedOperationException.class)
	public void test4() {
		ISFSObject object = new SFSObject();
		Parameters params = new SfsParameters(object);
		params.values();
	}
	
	@Test(expectedExceptions = UnsupportedOperationException.class)
	public void test5() {
		ISFSObject object = new SFSObject();
		Parameters params = new SfsParameters(object);
		params.toMap();
	}
	
}
