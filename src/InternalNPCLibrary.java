import java.awt.Point;

public class InternalNPCLibrary {
	
	public static void doAction(NPC npc, String action) {

		
		String[] actions = ((org.hyperscript.entity.NPC) npc).getActions();
		
		int index = -1;
		int offset = 0;
		
		for (int i = 0; i < actions.length; i++) {
			if (actions[i] == null) {
				offset++;
				continue;
			}
			if (actions[i].equalsIgnoreCase("Attack") || actions[i].equalsIgnoreCase("Examine")) {
				offset++;
			}
			if (actions[i].equalsIgnoreCase(action)) {
				index = i;
				break;
			}
		}
		index -= offset;
		
		if ((index == -1) && (action.equalsIgnoreCase("Attack") == false)) {
			System.err.println("[Action '" + action + "' not found in NPC[name='" + ((org.hyperscript.entity.NPC) npc).getName() + "']");
		}
		
		if (action.equalsIgnoreCase("Attack")) {
			InternalPacketLibrary.i_34_ = 10;
		}
		else if (action.equalsIgnoreCase("Examine")) {
			InternalPacketLibrary.i_34_ = 1003;
		}
		else {
			switch (index) {
				case 0:
					InternalPacketLibrary.i_34_ = 9;
					break;
				case 1:
					InternalPacketLibrary.i_34_ = 11;
					break;
				case 2:
					InternalPacketLibrary.i_34_ = 12;
					break;
				case 3:
					InternalPacketLibrary.i_34_ = 13;
					break;
			}
		}
		
		Point p = ((org.hyperscript.entity.NPC) npc).getLocation();

		InternalPacketLibrary.i_32_ = p.x;
		InternalPacketLibrary.i_33_ = p.y;
		InternalPacketLibrary.i_35_ = (int)((-4396451777151645697L * ((long)1888274983) * ((long)npc.anInt10064)) * 2236412381003659263L);
		InternalPacketLibrary.fakePacket = true;
		
		int i = -1040412347 * client.anInt8861 + -98735103 * client.anInt8734;
		int i_30_ = client.anInt8862 * 601707167 + client.anInt8853 * -938469429;
		short i_31_ = (short) 0; //Unused
		
		Class401.method4939(new Class298_Sub37_Sub15("", "", 0, 0, 0, 0L, 0, 0, false, false, 0L, false), i, i_30_, i_31_);
	}
	
	public static void attack(NPC npc) {
		doAction(npc, "Attack");
	}
	
	public static int stepsTo(NPC npc) {
		Class336 class336 = Class325.method3963(npc.scenePositionXQueue[0], npc.scenePositionYQueue[0], npc.getSize(), npc.getSize(), 0, (byte)-51);
		int stepsCount = Class298_Sub37.calculateRoute(Class287.myPlayer.scenePositionXQueue[0], Class287.myPlayer.scenePositionYQueue[0], Class287.myPlayer.getSize(), class336, client.aClass283_8716.getSceneClipDataPlane(Class287.myPlayer.plane), true, client.calculatedScenePositionXs, client.calculatedScenePositionYs);
		return stepsCount;
	}
	
}
