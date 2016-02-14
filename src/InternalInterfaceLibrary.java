import java.util.ArrayList;
import java.util.List;

import org.hyperscript.widgets.Interface;


public class InternalInterfaceLibrary {

	public static Interface getInterface(int id) {
		/*Class460 class460 = new Class460(client.aClass437_8841);
		for (Interface interface_ = (Interface)class460.method5979(-2012602178); interface_ != null; interface_ = (Interface)class460.next()) {
			if (-1617025021 * interface_.interfaceId == id) {
				return interface_;
			}
		}
		*/return null;
	}
	
	public static Interface[] getInterfaces() {
		List<Interface> interfaces = new ArrayList<Interface>();
		
		/*Class460 class460 = new Class460(client.aClass437_8841);
		for (Interface interface_ = (Interface)class460.method5979(-2012602178); interface_ != null; interface_ = (Interface)class460.next()) {
			interfaces.add(interface_);
		}*/
		
		return interfaces.toArray(new Interface[0]);
	}
	
}
