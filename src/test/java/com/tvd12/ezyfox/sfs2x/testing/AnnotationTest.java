package com.tvd12.ezyfox.sfs2x.testing;

import com.tvd12.ezyfox.core.annotation.Variable;

public class AnnotationTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		System.out.println(ClassA.class.getDeclaredField("field").getAnnotation(Variable.class));
		System.out.println(ClassB.class.getSuperclass().getDeclaredField("field").getAnnotation(Variable.class));
	}
	
	public static class ClassA {
		@Variable
		public int field;
	}
	
	public static class ClassB extends ClassA {
		public ClassB() {
			this.field = 10;
		}
	}
}


