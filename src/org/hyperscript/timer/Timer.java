package org.hyperscript.timer;

public class Timer {
	
	private static final float DEFAULT_RANDOM = 0.5f;
	public static final long ERROR_TIMER_WAS_NOT_RUNNING = -1;
	
	private static long startTime;
	private static boolean running = false;

	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sleepRandom(long ms) {
		sleepRandom(ms, DEFAULT_RANDOM);
	}
	
	public static void sleepRandom(long ms, float random) { // For example, if random is 0.5f, then the range will be from 0.5*ms to 1.5*ms
		sleep((long) ((ms*random) + (Math.random() * 2*random * ms)));
	}
	
	public static boolean isRunning() {
		return running;
	}
	
	public static void start() {
		running = true;
		startTime = System.currentTimeMillis();
	}
	
	public static long stop() {
		if (running) {
			long elapsed = System.currentTimeMillis() - startTime;
			startTime = 0;
			running = false;
			return elapsed;
		}
		else {
			return ERROR_TIMER_WAS_NOT_RUNNING;
		}
	}
	
	public static long getTimeElapsed() {
		if (running) {
			return System.currentTimeMillis() - startTime;
		}
		else {
			return ERROR_TIMER_WAS_NOT_RUNNING;
		}
	}
	
}
