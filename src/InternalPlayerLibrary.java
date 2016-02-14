import org.hyperscript.map.Map;


public class InternalPlayerLibrary {

	public static ItemDefinitions[] inventory;
	
	public static int stepsTo(Player p) {
		return InternalMapLibrary.stepCount(Map.getLocalPlayer().getLocation(), ((org.hyperscript.entity.Player) p).getLocation());
	}
	
}
