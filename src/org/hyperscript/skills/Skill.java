package org.hyperscript.skills;

public enum Skill {

	ATTACK, DEFENCE, STRENGTH, CONSTITUTION, RANGED, PRAYER, MAGIC, COOKING, WOODCUTTING, FLETCHING, FISHING, FIREMAKING, CRAFTING, SMITHING, MINING, HERBLORE, AGILITY, THEIVING, SLAYER, FARMING, RUNECRAFTING, HUNTER, CONSTRUCTION, SUMMONING, DUNGEONEERING;
	
	public int getID() {
		return this.ordinal();
	}
	
	public String getReadableName() {
		return this.toString();
	}
	
}
