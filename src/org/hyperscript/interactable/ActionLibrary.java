package org.hyperscript.interactable;

public class ActionLibrary {

	public static boolean hasAction(String action, String[] actions) {
		
		if (actions == null) {
			return false;
		}
		
		
		for (String act : actions) {
			if (act.equals(action)) {
				return true;
			}
		}
		return false;
	}
	
}
