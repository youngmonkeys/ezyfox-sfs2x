package com.tvd12.ezyfox.sfs2x.testing.any;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;

import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isObject;
import static org.testng.Assert.*;

public class PrimitiveTypeTest {
	@Test
	public void test() {
		assertTrue(isObject(new PrimitiveTypeTest().getClass()));
		assertFalse(isObject(new Integer(1).getClass()));
	}
	
	public static void main(String[] args) {
		Map<Class<?>, String> map = new HashMap<>();
		List<Field> fields = ReflectFieldUtil.getFieldsWithAnnotation(Sequence.class, RequestParam.class);
		map.put(Boolean.TYPE, "12");
		map.put(Boolean.class, "1");
		System.out.println(map.get(fields.get(0).getType()));
	}
}

enum Sequence {
	
	ONE(true), TWO(false);
	
	@RequestParam
	private boolean value;
	
	public boolean getValue() {
		return value;
	}
	
	private Sequence(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Boolean.toString(value);
	}
}
