package org.hyperscript.widgets;

import java.awt.Rectangle;

public interface Interface {

	public Rectangle getBounds();
	public String[] getActions();
	public void doAction(String action);
	public int getID();
	
}
