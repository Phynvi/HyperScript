package org.hyperscript.console.builtinprograms;

import org.hyperscript.console.ConsoleProgram;
import org.hyperscript.ui.Console;

public class UsageProgram implements ConsoleProgram {

	@Override
	public String getCommand() {
		return "usage";
	}

	@Override
	public void run(String[] args) {
		if (args.length != 1) {
			Console.error("Incorrect number of arguments passed to 'usage'. Expected: 1 Received: " + args.length);

			usage();
		}
		else {
			ConsoleProgram cp = Console.getProgram(args[0]);
			
			if (cp != null) {
				cp.usage();
			}
		}
	}

	@Override
	public void usage() {
		Console.log("Usage: usage <program>");
		Console.log("    <program>: the program to show the usage of");
	}

}
