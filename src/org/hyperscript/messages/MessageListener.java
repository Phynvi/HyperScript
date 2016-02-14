package org.hyperscript.messages;

public interface MessageListener {

	public void onMessage(MessageEvent e);
	public boolean filter(MessageEvent e); // returns true iff the MessageEvent should not be sent to onMessage due to programmed constraints (message regex match, time, etc.)
	
}
