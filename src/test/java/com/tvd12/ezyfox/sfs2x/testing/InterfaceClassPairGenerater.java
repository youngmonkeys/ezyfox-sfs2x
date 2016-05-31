package com.tvd12.ezyfox.sfs2x.testing;

import java.lang.reflect.Modifier;
import java.util.List;

public class InterfaceClassPairGenerater {

	public static void main(String[] args) {

	}

	public static String pairs(String packageName) {
		List<Class<?>> classes = ClassFinder.find(packageName);
		StringBuilder builder = new StringBuilder();
		for (Class<?> c : classes) {
			if (c.getModifiers() != Modifier.PUBLIC
					|| Modifier.isAbstract(c.getModifiers()))
				continue;
			Class<?> itfs[] = c.getInterfaces();
			String result = " = " + c.getName();
			for (Class<?> it : itfs) {
				if (c.getSimpleName().startsWith(it.getSimpleName())) {
					result = it.getName() + result;
					System.out.println(result);
					builder.append(result).append("\n");
					break;
				}
			}

		}
		
		return builder.toString().trim();

	}

	protected static String decapitalize(String string) {
		if (string == null || string.length() == 0) {
			return string;
		}
		char c[] = string.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		return new String(c);
	}

}
