package org.hyperscript.messages;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

	private static List<MessageListener> messageListeners = new ArrayList<MessageListener>();
	
	public static void addMessageListener(MessageListener ml) {
		messageListeners.add(ml);
	}
	
}
