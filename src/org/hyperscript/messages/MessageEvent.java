package org.hyperscript.messages;

import java.lang.reflect.Field;
import java.util.List;

public class MessageEvent {

	private String message;
	private long time;
	
	public MessageEvent(String message) {
		this.message = message;
		this.time = System.currentTimeMillis();
		
		try {
			Field f = MessageHandler.class.getDeclaredField("messageListeners");
			f.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<MessageListener> messageListeners = (List<MessageListener>)f.get(null);
			
			for (MessageListener ml : messageListeners) {
				if (ml.filter(this) == false) {
					ml.onMessage(this);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public long getTime() { 
		return this.time;
	}
	
}
