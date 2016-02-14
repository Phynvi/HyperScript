package org.hyperscript.object;

import org.hyperscript.interactable.Interactable;
import org.hyperscript.map.Locatable;

public interface GameObject extends Locatable, Interactable {

	public int getID();
	public String getName();
	public String[] getActions();
	
	public int getSizeX();
	public int getSizeY();
	public int getRotation();
	
}
