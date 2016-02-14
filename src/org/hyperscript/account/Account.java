package org.hyperscript.account;

import java.applet.Applet;

public class Account {

	public static void logout() {
		try {
			Applet applet = (Applet) Class.forName("ClientScriptMap").getDeclaredField("anApplet6044").get(null);
			Class.forName("Class466").getDeclaredMethod("method6021", new Class<?>[] { Applet.class, String.class, short.class }).invoke(null, new Object[]{ applet, "loggedout", (short)0 });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
