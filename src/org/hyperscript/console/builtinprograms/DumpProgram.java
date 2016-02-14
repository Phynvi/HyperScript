package org.hyperscript.console.builtinprograms;

import org.hyperscript.console.ConsoleProgram;
import org.hyperscript.ui.Console;

public class DumpProgram implements ConsoleProgram {

	@Override
	public String getCommand() {
		return "dump";
	}

	@Override
	public void run(String[] args) {
		if (args.length != 2) {
			Console.error("Incorrect number of arguments passed to 'dump'. Expected: 2 Received: " + args.length);
			usage();
		}
		//TODO
	}

	@Override
	public void usage() {
		Console.log("Usage: dump <property> <path>");
		Console.log("    <property>: one of " + getPropertiesString());
		Console.log("    <path>: the path of the file to dump the current value of the specified property to");
	}
	
	private String getPropertiesString() {
		String s = "";
		
		for (int i = 0; i < getProperties().length; i++) {
			if (i != 0) {
				s += ", ";
			}
			s += getProperties()[i];
		}
		
		return s;
	}
	
	private String[] getProperties() {
		return new String[]{"npcs", "players", "objects", "interfaces", "currentlocation", "chatbox", "inventory", "equipment", "prayers"};
	}

}
