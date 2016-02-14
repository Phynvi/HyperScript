import java.awt.Point;

import org.hyperscript.map.Map;
import org.hyperscript.object.GameObject;


public class InternalObjectLibrary {
	
	public static int i_3_;
	public static int i_4_;
	public static int i_5_;
	public static boolean fakePacket;

	public static void doAction(GameObject obj, String action) {
		
		String[] actions = ((org.hyperscript.object.GameObject) obj).getActions();
		
		int index = -1;
		int offset = 0;
		
		for (int i = 0; i < actions.length; i++) {
			if (actions[i] == null) {
				offset++;
				continue;
			}
			if (actions[i].equalsIgnoreCase("Examine")) {
				offset++;
			}
			if (actions[i].equalsIgnoreCase(action)) {
				index = i;
				break;
			}
		}
		index -= offset;
		
		if (index == -1) {
			System.err.println("[Action '" + action + "' not found in GameObject[name='" + ((org.hyperscript.object.GameObject) obj).getName() + "']");
		}

		else if (action.equalsIgnoreCase("Examine")) {
			InternalPacketLibrary.i_34_ = 1002;
		}
		else {
			switch (index) {
				case 0:
					InternalPacketLibrary.i_34_ = 3;
					break;
				case 1:
					InternalPacketLibrary.i_34_ = 4;
					break;
				case 2:
					InternalPacketLibrary.i_34_ = 5;
					break;
				case 3:
					InternalPacketLibrary.i_34_ = 6;
					break;
				case 4:
					InternalPacketLibrary.i_34_ = 1001;
					break;
			}
		}
		
		Point p = obj.getLocation();
		
		InternalPacketLibrary.i_32_ = p.x;
		InternalPacketLibrary.i_33_ = p.y;
		InternalPacketLibrary.fakePacket = true;
		
		InternalObjectLibrary.i_3_ = 10; //Usually 10, but it was 22 when exiting puro-puro
		InternalObjectLibrary.i_4_ = obj.getRotation();//FIXME
		InternalObjectLibrary.i_5_ = obj.getID();
		InternalObjectLibrary.fakePacket = true;
		
		int i = -1040412347 * client.anInt8861 + -98735103 * client.anInt8734;
		int i_30_ = client.anInt8862 * 601707167 + client.anInt8853 * -938469429;
		short i_31_ = (short) 0; //Unused
		
		Class401.method4939(new Class298_Sub37_Sub15("", "", 0, 0, 0, 0L, 0, 0, false, false, 0L, false), i, i_30_, i_31_);

	}
	
	public static Point getLocation(Class409 obj) {
        int locationHash = obj.aClass341_5283.anInt3645 * 1008331379;
        int x = locationHash >> 28 & 0x3;
        int y = locationHash >> 14 & 0x3FFF;
        int height = locationHash & 0x3FFF;
		
		return new Point(x, y);
	}
	
	public static int stepsTo(Class409 obj) {
		return InternalMapLibrary.stepCount(Map.getLocalPlayer().getLocation(), getLocation(obj));
	}
	
	public static String getName(Class409 obj) {
		return "//TODO";
	}
	
	public static String[] getActions(Class409 obj) {
		return new String[]{"//TODO"};
	}
	
}
