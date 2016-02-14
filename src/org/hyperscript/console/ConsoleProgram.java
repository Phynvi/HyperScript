package org.hyperscript.console;

public interface ConsoleProgram {

	public String getCommand();
	public void run(String[] args);
	public void usage();
	
}
