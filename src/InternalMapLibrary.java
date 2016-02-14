import java.awt.Point;

import org.hyperscript.map.Map;
import org.hyperscript.ui.Console;



public class InternalMapLibrary {
	
	private static Object WALKING_WAIT_OBJECT = new Object();
	private static Point CURRENT_WALKING_TARGET = null;

	public static int getGameSceneBaseX() {
		return client.aClass283_8716.method2628(681479919).gameSceneBaseX;
	}
	
	public static int getGameSceneBaseY() {
		return client.aClass283_8716.method2628(681479919).gameSceneBaseY;
	}
	
	public static void walkBy(Point p) {
		Point player = Map.getLocalPlayer().getLocation();
		walkToLocal(new Point(player.x + p.x, player.y + p.y));
	}
	
	public static void walkToLocal(Point p) {
		// Type 1 = Minimap
		// Type 0 = 3D View
		int type = 0;
		
		
		InternalMapLibrary.CURRENT_WALKING_TARGET = Map.convertToGlobalLocation(p);
		
		
		
		Class277.sendWalkPacket(type, Class350.method4214(p.x, p.y, (short)808));

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					while (true) {
						synchronized (InternalMapLibrary.WALKING_WAIT_OBJECT) {
							if ((InternalMapLibrary.CURRENT_WALKING_TARGET != null) &&
								(Map.convertToGlobalLocation(Map.getLocalPlayer().getLocation()).equals(InternalMapLibrary.CURRENT_WALKING_TARGET))) {
								InternalMapLibrary.CURRENT_WALKING_TARGET = null;
								Thread.sleep(500);
								InternalMapLibrary.WALKING_WAIT_OBJECT.notify();
							}
						}
						Thread.sleep(500);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		synchronized (InternalMapLibrary.WALKING_WAIT_OBJECT) {
			try {
				WALKING_WAIT_OBJECT.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void walkToGlobal(Point p) {
		walkToLocal(new Point(p.x - getGameSceneBaseX(), p.y - getGameSceneBaseY()));
	}
	
	public static int stepCount(Point p1, Point p2) {
		Class336 class336 = Class325.method3963(p2.x, p2.y, 1, 1, 0, (byte)-51);
		int stepsCount = Class298_Sub37.calculateRoute(p1.x, p1.y, 1, class336, client.aClass283_8716.getSceneClipDataPlane(Class287.myPlayer.plane), true, client.calculatedScenePositionXs, client.calculatedScenePositionYs);
		return stepsCount;
	}
	
	private static int runEnergy = 0;
	
	public static int readRunPacket(RsBitsBuffer stream) {
		int i = stream.readUnsignedByte();
		InternalMapLibrary.runEnergy = i;
		return i;
	}
	
	public static int getRunEnergy() {
		return InternalMapLibrary.runEnergy;
	}
	
	public static int readUnsignedShort(RsByteBuffer stream) {
		int i = stream.readUnsignedShort();
		System.out.println("Short: " + i);
		return i;
	}
	
}
