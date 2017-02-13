package com.tvd12.ezyfox.sfs2x.testing.data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.tvd12.ezyfox.sfs2x.data.impl.SfsObjectMap;
import com.tvd12.ezyfox.sfs2x.data.impl.SfsObjectMap.SfsEntry;

/**
 * @author tavandung12
 * Created on Feb 13, 2017
 *
 */
public class SfsObjectMapTest {

	@Test
	public void test() {
		ISFSObject object = new SFSObject();
		object.putUtfStringArray("1", Lists.newArrayList("a", "b"));
		SfsObjectMap map = new SfsObjectMap(object);
		assertEquals(map.size(), 1);
		assertEquals(map.isEmpty(), false);
		assertEquals(map.containsKey("1"), true);
		assertEquals(map.containsKey("zzzz"), false);
		
		map.put("2", 2);
		assertEquals(map.get("2"), new Integer(2));
		assertEquals(map.get("1"), Lists.newArrayList("a", "b"));
		
		map.remove("2");
		assertFalse(map.containsKey("2"));
		
		Map<Object, Object> mm = new HashMap<>();
		mm.put("3", "abc");
		map.putAll(mm);
		
		Set<Entry<Object, Object>> entries = map.entrySet();
		for(Entry<Object, Object> entry : entries)
			System.out.println(entry.getKey() + ", " + entry.getValue());
		
		assertEquals(map.keySet().size(), 2);
		
		map.clear();
	}
	
	@Test
	public void test2() {
		ISFSObject object = new SFSObject();
		SfsObjectMap map = new SfsObjectMap(object);
		assertTrue(map.isEmpty());
	}
	
	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void test3() {
		ISFSObject object = new SFSObject();
		SfsObjectMap map = new SfsObjectMap(object);
		map.containsValue("abc");
	}
	
	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void test4() {
		SfsEntry entry = new SfsEntry(null);
		entry.setValue("abc");
	}
	
	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void test5() {
		ISFSObject object = new SFSObject();
		SfsObjectMap map = new SfsObjectMap(object);
		map.values();
	}
	
}
