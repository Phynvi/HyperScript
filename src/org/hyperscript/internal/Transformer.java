package org.hyperscript.internal;

import java.awt.Graphics;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Field;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

import org.hyperscript.map.Map;
import org.hyperscript.ui.Console;
import org.hyperscript.widgets.Interface;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class Transformer implements ClassFileTransformer {

	@Override
	public byte[] transform(java.lang.ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		
		byte[] byteCode = classfileBuffer;
		
        //BEGIN: Loader
		if (className.equals("Loader")) {
            try {
        		ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("Loader");
				
				CtMethod main = cc.getDeclaredMethod("main");
				main.insertAfter("{ org.hyperscript.ui.UI.addMenubar(Loader.instance.client_frame); }");
				
                byteCode = cc.toBytecode();
                
                cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        //END: Loader
		
		//BEGIN: Entity
		else if (className.equals("Entity")) {
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get("Entity");
                
                CtField instances = new CtField(cp.get("java.util.ArrayList"), "instances", cc);//CtField.make("private static java.util.List<Entity> instances;", cc);
                instances.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
                cc.addField(instances, CtField.Initializer.byNew(cp.get("java.util.ArrayList")));

                for (CtConstructor c : cc.getConstructors()) {
                	c.insertAfter("{ if (instances.contains(this) == false) { instances.add(this); } }");
                }
                
                cc.addInterface(cp.get("org.hyperscript.entity.Entity"));
                CtMethod getName = CtNewMethod.make("public String getName() { if (this instanceof NPC) { return ((NPC) this).getName(); }\n else if (this instanceof Player) { return ((Player) this).getName(); }\n else { return null; }\n }", cc);
                cc.addMethod(getName);
                CtMethod getLocation = CtNewMethod.make("public java.awt.Point getLocation() { return new java.awt.Point(this.scenePositionXQueue[0], this.scenePositionYQueue[0]); }", cc);
                cc.addMethod(getLocation);
                CtMethod isNPC = CtNewMethod.make("public boolean isNPC() { return (this instanceof NPC); }", cc);
                cc.addMethod(isNPC);
                CtMethod isPlayer = CtNewMethod.make("public boolean isPlayer() { return (this instanceof Player); }", cc);
                cc.addMethod(isPlayer);
                
                byteCode = cc.toBytecode();
                
                cc.detach();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //END: Entity
        
        //BEGIN: NPC
		else if (className.equals("NPC")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("NPC");

				cc.addInterface(cp.get("org.hyperscript.entity.NPC"));
				CtMethod getName = CtNewMethod.make("public String getName() { return this.changedName; }", cc);
				cc.addMethod(getName);
				CtMethod getOptions = CtNewMethod.make("public String[] getActions() { try { return this.aClass503_10190.aStringArray6142; } catch (java.lang.NullPointerException e) { return new String[]{\"Error: No NPC Definition Found\"}; } }", cc);
				cc.addMethod(getOptions);
				CtMethod getID = CtNewMethod.make("public int getID() { return -1; }", cc); //TODO //FIXME
				cc.addMethod(getID);
				CtMethod doAction = CtNewMethod.make("public void doAction(String action) { InternalNPCLibrary.doAction(this, action);  }", cc);
				cc.addMethod(doAction);
				CtMethod attack = CtNewMethod.make("public void attack() { InternalNPCLibrary.attack(this); }", cc);
				cc.addMethod(attack);
				CtMethod stepsTo = CtNewMethod.make("public int stepsTo() { return InternalNPCLibrary.stepsTo(this); }", cc);
				cc.addMethod(stepsTo);
				CtMethod getCombatLevel = CtNewMethod.make("public int getCombatLevel() { return this.changedCombatLevel; } ", cc);
				cc.addMethod(getCombatLevel);
				CtMethod getRenderAnimation = CtNewMethod.make("public int getRenderAnimation() { return -1317338937 * this.renderAnimation; } ", cc);
				cc.addMethod(getRenderAnimation);
				

				byteCode = cc.toBytecode();

				cc.detach();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
        //END: NPC
      
        //BEGIN: Player
		else if (className.equals("Player")) {
            try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get("Player");

                cc.addInterface(cp.get("org.hyperscript.entity.Player"));
                CtMethod getNamePrefix = CtNewMethod.make("public String getNamePrefix() { return this.aString10199; }", cc);
                cc.addMethod(getNamePrefix);
                CtMethod getName = CtNewMethod.make("public String getName() { return this.aString10195; }", cc);
                cc.addMethod(getName);
                CtMethod getNameSuffix = CtNewMethod.make("public String getNameSuffix() { return this.aString10197; }", cc);
                cc.addMethod(getNameSuffix);
				CtMethod stepsTo = CtNewMethod.make("public int stepsTo() { return InternalPlayerLibrary.stepsTo(this); }", cc);
				cc.addMethod(stepsTo);
                
                
				CtMethod receiveAppearance = cc.getDeclaredMethod("sendAppearence");
				receiveAppearance.insertAt(154, "{ InternalPlayerLibrary.inventory = class468s; System.out.println(\"Inventory Size: \" + InternalPlayerLibrary.inventory.length); }");
				receiveAppearance.instrument(new ExprEditor() {
					
					@Override
					public void edit(MethodCall m) throws CannotCompileException {
						if (m.getMethodName().equals("readUnsignedShort")) {
							m.replace("$_ = InternalMapLibrary.readUnsignedShort(stream);");
					    }
					}
					
				});
                
                byteCode = cc.toBytecode();
                
                cc.detach();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //END: Player
        
        //BEGIN: Outgoing Packets
		else if (className.equals("Class401")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("Class401");
			
				CtMethod sendPacket = cc.getDeclaredMethod("method4939");
				
				sendPacket.insertAt(187, "{ if (InternalPacketLibrary.fakePacket) { i_32_ = InternalPacketLibrary.i_32_; i_33_ = InternalPacketLibrary.i_33_; i_34_ = InternalPacketLibrary.i_34_; i_35_ = InternalPacketLibrary.i_35_; InternalPacketLibrary.fakePacket = false; } }");
				
				sendPacket.insertAt(423, "{ if (InternalObjectLibrary.fakePacket) { i_3_ = InternalObjectLibrary.i_3_; i_4_ = InternalObjectLibrary.i_4_; i_5_ = InternalObjectLibrary.i_5_; InternalObjectLibrary.fakePacket = false; } }");
				/*NOTE:
				 * 
				 * i_3_ is ??????? (usually 10 but it was 22 when exiting puro-puro)
				 * i_4_ is the object orientation/rotation
				 * i_5_ is the object ID
				 * 
				 */
				
				sendPacket.insertAt(423, "{ System.out.println(\"i_3_ = \" + i_3_); System.out.println(\"i_4_ = \" + i_4_); System.out.println(\"i_5_ = \" + i_5_); }");
				
				byteCode = cc.toBytecode();
				
				cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //END: Outgoing Packets
 
        //BEGIN: Incoming Packets
		else if (className.equals("PacketsDecoder")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("PacketsDecoder");
			
				CtMethod handlePacket = cc.getDeclaredMethod("method4548");
				handlePacket.insertAt(129, "{ System.out.println(i_80_ + \", \" + i_81_); new org.hyperscript.messages.MessageEvent(string_84_); }");
				
				handlePacket.instrument(new ExprEditor() {
					
					@Override
					public void edit(MethodCall m) throws CannotCompileException {
						if (m.getLineNumber() == 877) {
							m.replace("$_ = InternalMapLibrary.readRunPacket(stream);");
					    }
					}
					
				});
				
				//Display Message: Class242_Sub1.method2282(i_80_, i_81_, string, string_83_, string, string_84_, -1468983571);
				
				byteCode = cc.toBytecode();
				
				cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //END: Incoming Packets
        
        //BEGIN: Interface
		else if (className.equals("Interface")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("Interface");
				
				cc.addInterface(cp.get("org.hyperscript.widgets.Interface"));
			
				CtMethod getDefinition = CtNewMethod.make("public IComponentDefinition getDefinition() { return Class50.getIComponentDefinitions((int)(7051297995265073167L * this.aLong3188), (byte)-3); }", cc);
				cc.addMethod(getDefinition);
				
				CtMethod getActions = CtNewMethod.make("public String[] getActions() { return getDefinition().aStringArray1195;  }", cc); //TODO //FIXME
				cc.addMethod(getActions);
				
				CtMethod getBounds = CtNewMethod.make("public java.awt.Rectangle getBounds() { return null;  }", cc); //TODO //FIXME
				cc.addMethod(getBounds);
				
				CtMethod doAction = CtNewMethod.make("public void doAction(String action) {   }", cc); //TODO //FIXME
				cc.addMethod(doAction);
				
				CtMethod getID = CtNewMethod.make("public int getID() { return -1617025021 * this.interfaceId;  }", cc); //TODO //FIXME
				cc.addMethod(getID);
				
				byteCode = cc.toBytecode();
				
				cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //END: Interface
        
        //BEGIN: Applet
		else if (className.equals("Class291")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("Class291");
				
				//CtMethod start = cc.getDeclaredMethod("start");
				//start.insertAfter("{ while (true) { ClientScriptMap.anApplet6044.repaint(); } }");
				
				byteCode = cc.toBytecode();
				
				cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //END: Applet
        

        //BEGIN: Canvas
		else if (className.equals("Canvas_Sub1")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("Canvas_Sub1");
				
				CtMethod update = cc.getDeclaredMethod("update");
				update.insertAfter("{ org.hyperscript.ui.UI.paint(graphics); }");
				
				//CtMethod start = cc.getDeclaredMethod("start");
				//start.insertAfter("{ while (true) { ClientScriptMap.anApplet6044.repaint(); } }");
				
				byteCode = cc.toBytecode();
				
				cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //END: Canvas
        
        //BEGIN: Player Update
		else if (className.equals("Class247")) {
			try {
				ClassPool cp = ClassPool.getDefault();
				CtClass cc = cp.get("Class247");
				
				//CtMethod decodePlayerUpdate = cc.getDeclaredMethod("decodePlayerUpdate");
				//decodePlayerUpdate.insertAfter("{ synchronized (InternalMapLibrary.WALKING_WAIT_OBJECT) { try { if (org.hyperscript.map.Map.convertToGlobalLocation(org.hyperscript.map.Map.getLocalPlayer().getLocation()).equals(InternalMapLibrary.CURRENT_WALKING_TARGET)) { InternalMapLibrary.WALKING_WAIT_OBJECT.notify(); } } catch (java.lang.Exception e) { e.printStackTrace(); } } }");
				
				byteCode = cc.toBytecode();
				
				cc.detach();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        //END: Player Update
        
        //BEGIN: Object      
        else if (className.equals("Class409")) {
        	try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get("Class409");

                cc.addInterface(cp.get("org.hyperscript.object.GameObject"));
                
                CtField instances = new CtField(cp.get("java.util.ArrayList"), "instances", cc);
                instances.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
                cc.addField(instances, CtField.Initializer.byNew(cp.get("java.util.ArrayList")));

                for (CtConstructor c : cc.getConstructors()) {
                	c.insertAfter("{ if (instances.contains(this) == false) { instances.add(this); } }");
                }
                
                CtMethod getName = CtNewMethod.make("public String getName() { return InternalObjectLibrary.getName(this); }", cc);
                cc.addMethod(getName);
                CtMethod getID = CtNewMethod.make("public int getID() { return -1949334017 * this.anInt5282; }", cc);
                cc.addMethod(getID);
                CtMethod getActions = CtNewMethod.make("public String[] getActions() { return InternalObjectLibrary.getActions(this); }", cc);
                cc.addMethod(getActions);
                CtMethod getLocation = CtNewMethod.make("public java.awt.Point getLocation() { return InternalObjectLibrary.getLocation(this); }", cc);
                cc.addMethod(getLocation);
				CtMethod stepsTo = CtNewMethod.make("public int stepsTo() { return InternalObjectLibrary.stepsTo(this); }", cc);
				cc.addMethod(stepsTo);
				CtMethod doAction = CtNewMethod.make("public void doAction(String action) { return InternalObjectLibrary.doAction(this, action); }", cc);
				cc.addMethod(doAction);
                
                byteCode = cc.toBytecode();
                
                cc.detach();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //END: Object
        
        //BEGIN: Location
        else if (className.equals("Class341")) {
        	try {
                ClassPool cp = ClassPool.getDefault();
                CtClass cc = cp.get("Class341");

                for (CtConstructor c : cc.getDeclaredConstructors()) {
                	 c.insertAfter("{ int locationHash = this.anInt3645; int x = locationHash >> 28 & 0x3; int y = locationHash >> 14 & 0x3FFF; int z = locationHash & 0x3FFF; org.hyperscript.ui.Console.log(\"(\" + x + \", \" + y + \", \" + z + \")\"); StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace(); org.hyperscript.ui.Console.log(stackTraceElements[2].toString()); }");
                }
                
                
                byteCode = cc.toBytecode();
                
                cc.detach();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }      
        //END: Location
       
        return byteCode;
        
	}

}
