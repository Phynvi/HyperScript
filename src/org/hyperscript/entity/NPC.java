package org.hyperscript.entity;

import org.hyperscript.interactable.Interactable;

public interface NPC extends Entity, Interactable {

	public String[] getActions();
	public int getID();
	public void attack();
	public int getCombatLevel();
	public int getRenderAnimation();
	
}
