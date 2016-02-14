package org.hyperscript.script;

public class ScriptRunner {
	
	private static Script running;
	private static Thread scriptThread;

	public static void run(final Script s) {
		s.init();
		scriptThread = new Thread(new Runnable() {

			@Override
			public void run() {
				s.run();
			}
			
		});
		running = s;
		scriptThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public static void stop() {
		if (scriptThread != null) {
			scriptThread.stop();
		}
		if (running != null) {
			running.cleanup();
			running = null;
		}
		scriptThread = null;
	}
	
}
