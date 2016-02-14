package org.hyperscript.widgets;

import java.lang.reflect.InvocationTargetException;

public class InterfaceLoader {

	public static Interface getInterface(int id) {
		try {
			return (Interface) Class.forName("InternalInterfaceLibrary").getDeclaredMethod("getInterface", new Class[]{ int.class }).invoke(null, new Object[]{ id });
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Interface[] getInterfaces() {
		try {
			return (Interface[]) Class.forName("InternalInterfaceLibrary").getDeclaredMethod("getInterfaces", new Class[0]).invoke(null, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
