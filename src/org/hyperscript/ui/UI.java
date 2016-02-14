package org.hyperscript.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.hyperscript.account.Account;
import org.hyperscript.entity.NPC;
import org.hyperscript.entity.Player;
import org.hyperscript.interactable.ActionLibrary;
import org.hyperscript.item.Item;
import org.hyperscript.map.Map;
import org.hyperscript.map.Path;
import org.hyperscript.object.GameObject;
import org.hyperscript.script.Script;
import org.hyperscript.script.ScriptRunner;
import org.hyperscript.skills.Skill;
import org.hyperscript.skills.Skills;
import org.hyperscript.timer.Timer;
import org.hyperscript.widgets.Interface;
import org.hyperscript.widgets.InterfaceLoader;

public class UI {
	
	private static Path p = new Path();

	public static void addMenubar(JFrame frame) {
		JMenuBar menu = new JMenuBar();
		
		//BEGIN: Script Menu
		JMenu script = new JMenu("Script");
		
		JMenuItem runScript = new JMenuItem("Run Script");
		runScript.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Script gameObjectHookTest = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						GameObject[] gameObjects = Map.getObjects();
						
						for (GameObject obj : gameObjects) {
							Console.log(obj.getID() + " @ " + obj.getLocation(), Color.ORANGE);
						}
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(gameObjectHookTest);
				
				/*Script walker = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						Map.walkBy(new Point(5, 5));
						Map.walkBy(new Point(-5, -5));
						NPC rockCrab = Map.getNearestNPC("Rock Crab");
						rockCrab.attack();
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(walker);*/
				
				/*Script dungeoneeringTrainerAIO = new Script() {

					private String npcName = null;
					
					@Override
					public void init() {
						int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
	                               "Rangers or Warriors?", //Object message,
	                               "Select an NPC", //String title
	                               JOptionPane.YES_NO_OPTION, //int optionType
	                               JOptionPane.INFORMATION_MESSAGE, //int messageType
	                               null, //Icon icon,
	                               new String[]{"Forgotten ranger","Forgotten warrior"}, //Object[] options,
	                               "Forgotten ranger");//Object initialValue 
						if (choice == 0) {
							npcName = "Forgotten ranger";
						}
						else {
							npcName = "Forgotten warrior";
						}
					}
					@Override
					public void run() {
						if (npcName != null) {
							while (true) {
								NPC npc = Map.getNearestNPC(npcName);
								
								if (npc == null) {
									Console.log("[NPC '" + npcName + "' not found]", Color.RED.darker());
								}
								else {
									npc.attack();
								}
								
								Timer.sleep(10);
							}
						}
						else {
							throw new RuntimeException("Unknown cause of error: npcName was null");
						}
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}

				};
				ScriptRunner.run(dungeoneeringTrainerAIO);*/
				
				/*for (GameObject obj : Map.getObjects()) {
					System.out.println("[id=" + obj.getID() + "] (" + obj.getLocation().x + ", " + obj.getLocation().y + ")");
				}*/
				
				/*Script walker = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						Path path = new Path();
						path.add(new Point(2605, 3094));
						path.add(new Point(2606, 3096));
						path.add(new Point(2611, 3100));
						path.add(new Point(2618, 3107));
						path.add(new Point(2625, 3122));
						path.add(new Point(2625, 3129));
						path.add(new Point(2626, 3130));
						path.walk();
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(walker);*/
				
				/*while (true) {
					NPC[] npcs = Map.getNPCs();
					
					NPC trader = null;
					
					for (NPC npc : npcs) {
						if (npc != null) {
							if (ActionLibrary.hasAction("Pickpocket", npc.getActions()) == false) {
								continue;
							}
							if (trader == null) {
								trader = npc;
							}
							else if (npc.stepsTo() < trader.stepsTo()) {
								trader = npc;
							}
						}
					}

					if (trader != null) {
						trader.doAction("Pickpocket");
					}
					else {
						Console.log("[Couldn't Find Trader]");
					}
					Timer.sleepRandom(4000);
				}*/
				
				/*Script guardTheiver = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						while (true) {
							NPC guard = Map.getNearestNPC("Guard");
							
							if (guard != null) {
								guard.doAction("Pickpocket");
								Timer.sleepRandom(4000);
							}
							
						}
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(guardTheiver);*/
				
				/*Interface runButton = InterfaceLoader.getInterface(750);
				
				if (runButton == null) {
					Console.log("[Couldn't find run button]");
				}
				
				for (String s : runButton.getActions()) {
					Console.log("[Interface Option]: " + s);
				}*/
				
				/*Script rockCrabber = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						
						long strengthXP = Skills.getXP(Skill.STRENGTH);
						
						while (true) {
							NPC rockCrab = Map.getNearestNPC("Rock Crab");
							
							if (rockCrab != null) {
								rockCrab.attack();
							}
							
							Timer.sleep(7000);
							
							Console.log("XP Gained: " + (Skills.getXP(Skill.STRENGTH) - strengthXP));
							
							if (Skills.getXP(Skill.STRENGTH) >= 200000000) {
								System.out.println("[Bot Completed @" + new Date().toString() + "]");
								System.exit(0);
							}
						}
					}
					

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(rockCrabber);*/
				
				
				/*Script implingCatcher = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {	
						Rectangle UNREACHABLE = new Rectangle(2584, 4312, 15, 15); // Blocked by Magical Wheat
						
						while (true) {
							NPC[] npcs = Map.getNPCs();
							
							NPC impling = null;
							
							for (NPC npc : npcs) {
								if (npc != null) {
									if (npc.getName().endsWith("mpling") && (UNREACHABLE.contains(Map.convertToGlobalLocation(npc.getLocation())) == false)) {
										if (impling == null) {
											impling = npc;
										}
										else if (npc.stepsTo() < impling.stepsTo()) {
											impling = npc;
										}
									}
								}
							}

							if (impling != null) {
								impling.doAction("Catch");
							}
							else {
								System.out.println("[Couldn't Find Impling]");
							}
							Timer.sleepRandom(4000);
						}
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(implingCatcher);
				*/
				
				/*Script runespan = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						while (true) {
							
							Map.getRunEnergy();
							
							NPC npc = Map.getNearestNPC("Soul esswraith");
							
							if (npc != null) {
								npc.doAction("Siphon");
							}
							else {
								Console.log("Error: Couldn't find NPC 'Soul esswraith'.");
							}
							
							Timer.sleepRandom(10000);
						}
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(runespan);*/
				
				
				/*Script cowKiller = new Script() {

					@Override
					public void init() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void run() {
						while (true) {
							NPC cow = Map.getNearestNPC("Cow");
							if (cow != null) {
								cow.attack();
							}
							else {
								Console.log("[Couldn't find Cow]");
							}
							Timer.sleepRandom(3000);
						}
					}

					@Override
					public void cleanup() {
						// TODO Auto-generated method stub
						
					}
					
				};
				ScriptRunner.run(cowKiller);*/
				
				
				/*try {
					Class<?> clazz = Class.forName("Entity");
					for (Player player : Map.getPlayers()) {
						for (Field f : clazz.getDeclaredFields()) {
							f.setAccessible(true);
							if (java.lang.reflect.Modifier.isStatic(f.getModifiers()) == false) {
								if (f.getType().equals(int.class)) {
									int combatLevel = (Integer) f.get(player);
									if ((player.getName() != null) && player.getName().equalsIgnoreCase("TickleMeTeal") && ((combatLevel == 138) || (combatLevel == 126))) {
										System.out.println("[Found Combat Level Hook]    Entity." + f.getName());
									}
									else {
										System.out.println("Entity." + f.getName() + " is NOT the hook.");
									}
								}
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}*/
				
				/*try {
					Class<?> clazz = Class.forName("NPCDefinitions");
					Field def = Class.forName("NPC").getDeclaredField("aClass503_10190");
					def.setAccessible(true);
					for (NPC npc : Map.getNPCs()) {
						for (Field f : clazz.getDeclaredFields()) {
							f.setAccessible(true);
							if (java.lang.reflect.Modifier.isStatic(f.getModifiers()) == false) {
								if (f.getType().equals(int.class)) {
									int combatLevel = (Integer) f.get(def.get(npc));
									if ((npc.getName() != null) && npc.getName().equalsIgnoreCase("Hero") && ((combatLevel == 69) || (combatLevel == 80))) {
										System.out.println("[Found Combat Level Hook]    NPCDefinitions." + f.getName());
									}
									else {
										System.out.println("NPCDefinitions." + f.getName() + " is NOT the hook.");
									}
								}
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}*/
			}
			
		});
		script.add(runScript);
		
		JMenuItem stopScript = new JMenuItem("Stop Script");
		stopScript.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScriptRunner.stop();
			}
			
		});
		script.add(stopScript);
		
		menu.add(script);
		//END: Script Menu
		
		//BEGIN: View Menu
		JMenu view = new JMenu("View");
		
		JMenu console = new JMenu("Console");
		JMenuItem show = new JMenuItem("Show");
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Console.show();
			}
			
		});
		console.add(show);
		JMenuItem hide = new JMenuItem("Hide");
		hide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Console.hide();
			}
			
		});
		console.add(hide);
		
		view.add(console);
		
		JMenu list = new JMenu("List");
		JMenuItem npcList = new JMenuItem("NPCs");
		npcList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				NPCList.show();
			}
			
		});
		list.add(npcList);
		JMenuItem playerList = new JMenuItem("Players");
		playerList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerList.show();
			}
			
		});
		list.add(playerList);
		JMenuItem objectList = new JMenuItem("Objects");
		objectList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ObjectList.show();
			}
			
		});
		list.add(objectList);
		JMenuItem interfaceList = new JMenuItem("Interfaces");
		interfaceList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InterfaceList.show();
			}
			
		});
		list.add(interfaceList);

		view.add(list);
		
		menu.add(view);
		//END: View Menu
		
		//BEGIN: Account Menu
		JMenu account = new JMenu("Account");
		
		JMenuItem logout = new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Account.logout();
			}
			
		});
		account.add(logout);
		
		menu.add(account);
		
		//END: Account Menu

		
		//BEGIN: Developer Menu
		JMenu developer = new JMenu("Developer");
		
		JMenu pathCreator = new JMenu("Path Creator");
		JMenuItem addCurrentLocationToPath = new JMenuItem("Add Current Location");
		addCurrentLocationToPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.add(Map.convertToGlobalLocation(Map.getLocalPlayer().getLocation()));
			}
			
		});
		pathCreator.add(addCurrentLocationToPath);
		JMenuItem dumpPathAsJavaCode = new JMenuItem("Dump Path");
		dumpPathAsJavaCode.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.dumpPath();
			}
			
		});
		pathCreator.add(dumpPathAsJavaCode);
		JMenuItem clearPath = new JMenuItem("Clear Path");
		clearPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.clear();
			}
			
		});
		pathCreator.add(clearPath);
		developer.add(pathCreator);
		
		menu.add(developer);
		
		//END: Developer Menu
		
		
		frame.setJMenuBar(menu);
		frame.setSize(frame.getWidth(), frame.getHeight() + menu.getHeight());
		menu.setVisible(true);
	}
	
	public static void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 100, 100);
	}
	
	public static void openHyperionControlPanel() {
		try {
			JFrame cp = (JFrame) Class.forName("ControlPanel").newInstance();
			cp.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
