package org.hyperscript.internal;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClassLoader {
	
	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new Transformer());
	}
	
	public static void agentmain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new Transformer());
	}

	public static Class<?>[] loadClasses() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		java.lang.reflect.Field classesField = java.lang.ClassLoader.class.getDeclaredField("classes");
		classesField.setAccessible(true);
		@SuppressWarnings("unchecked")
		Vector<Class<?>> classes = (Vector<Class<?>>) classesField.get(java.lang.ClassLoader.getSystemClassLoader());
		List<Class<?>> output = new ArrayList<Class<?>>();
		
		for (Class<?> clazz : classes) {
			if (clazz.getPackage().getName().startsWith("org.hyperscript") == false) {
				output.add(clazz);
			}
		}
		
		return output.toArray(new Class<?>[0]);
	}
	
}
