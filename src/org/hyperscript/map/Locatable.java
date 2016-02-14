package org.hyperscript.map;

import java.awt.Point;

public interface Locatable {

	public Point getLocation();
	public int stepsTo();
	public int getSize();
	
}
