package org.hyperscript.item;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.hyperscript.ui.Console;

public class Item {

	private int id;
	
	public Item(int id) {
		this.id = id;
	}
	
	private Object getDefinition() {
		try {
			return Class.forName("InternalItemLibrary").getDeclaredMethod("getItemDefinition", new Class<?>[]{ int.class }).invoke(null, new Object[]{ this.id });
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String getName() {
		try {
			return (String) Class.forName("ItemDefinitions").getDeclaredField("aString5707").get(getDefinition());
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getInventoryOptions() {
		try {
			return (String[]) Class.forName("ItemDefinitions").getDeclaredField("aStringArray5723").get(getDefinition());
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getGroundOptions() {
		try {
			return (String[]) Class.forName("ItemDefinitions").getDeclaredField("aStringArray5732").get(getDefinition());
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void test() {
		try {
			for (Field f : Class.forName("ItemDefinitions").getDeclaredFields()) {
				f.setAccessible(true);
				if (java.lang.reflect.Modifier.isStatic(f.getModifiers()) == false) {
					Console.log(f.getName() + " = " + f.get(getDefinition()), Color.PINK.darker());
				}
			}
		} catch (SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getID() {
		return id;
	}
	
}
