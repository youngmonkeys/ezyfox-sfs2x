package com.tvd12.ezyfox.sfs2x.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericTypeTest {

	public static void main(String[] args) {
		List<String> strs = new ArrayList<>();
		strs.add(new String("dung"));
		
		Collection<?> cl = (Collection<?>)(strs);
		Object ob = cl.toArray()[0];
		System.out.println(ob);
		System.out.println(ob.getClass());
		
		int a = 10;
		System.out.println(Integer.class.cast(a).intValue());
		
	}
	
}
