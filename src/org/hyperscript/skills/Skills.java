package org.hyperscript.skills;

public class Skills {

	public static int getXP(Skill skill) {
		try {
			return ((int[]) Class.forName("client").getDeclaredField("anIntArray8829").get(null))[skill.getID()];
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static int getLevel(Skill skill) {
		try {
			return ((int[]) Class.forName("client").getDeclaredField("anIntArray8924").get(null))[skill.getID()];
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
