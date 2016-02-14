package org.hyperscript.entity;

import java.awt.Point;

import org.hyperscript.map.Locatable;

public interface Entity extends Locatable {

	public String getName();
	public Point getLocation();
	public boolean isNPC();
	public boolean isPlayer();
	
}
