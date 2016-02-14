package org.hyperscript.entity;

import org.hyperscript.combat.Attackable;
import org.hyperscript.interactable.Interactable;

public interface Player extends Entity, Interactable, Attackable {
	
	public String getNamePrefix();
	public String getNameSuffix();
	public boolean isInWilderness();
	
}
