package org.hyperscript.map;

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.hyperscript.entity.Entity;
import org.hyperscript.entity.NPC;
import org.hyperscript.entity.Player;
import org.hyperscript.object.GameObject;
import org.hyperscript.ui.Console;

public class Map {

	@SuppressWarnings("unchecked")
	public static Entity[] getEntities() {
		try {
			ArrayList<Entity> entities = ((java.util.ArrayList<Entity>) Class.forName("Entity").getDeclaredField("instances").get(null));
			
			ArrayList<Entity> output = new ArrayList<Entity>();
			
			for (Entity e : entities) {
				if (e != null) {
					output.add(e);
				}
			}
			
			return output.toArray(new Entity[0]);
			
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static NPC[] getNPCs() {
		Entity[] entities = getEntities();
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		
		for (Entity e : entities) {
			if (e.isNPC()) {
				npcs.add((NPC) e);
			}
		}
		
		return npcs.toArray(new NPC[0]);
	}
	
	public static Player[] getPlayers() {
		Entity[] entities = getEntities();
		ArrayList<Player> players = new ArrayList<Player>();
		
		for (Entity e : entities) {
			if (e.isPlayer()) {
				players.add((Player) e);
			}
		}
		
		return players.toArray(new Player[0]);
	}
	
	public static Entity getNearestEntity(String name) {
		return getNearestEntity(name, getLocalPlayer().getLocation());
	}
	
	public static Entity getNearestEntity(String name, Point p) {
		Entity nearest = null;
		
		for (Entity entity : getEntities()) {
			if (entity.getName().equals(name)) {
				if (nearest == null) {
					nearest = entity;
				}
				else if (Map.stepCount(entity.getLocation(), p) < Map.stepCount(nearest.getLocation(), p)) {
					nearest = entity;
				}
			}
		}
		
		return nearest;
	}
	
	public static Player getPlayer(String name) {
		for (Player player : getPlayers()) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		
		return null;
	}
	
	public static NPC getNearestNPC(String name) {
		return getNearestNPC(name, getLocalPlayer().getLocation());
	}
	
	public static NPC getNearestNPC(String name, Point p) {
		NPC nearest = null;
		
		for (NPC npc : getNPCs()) {
			if ((npc != null) && npc.getName().equals(name)) {
				if (nearest == null) {
					nearest = npc;
				}
				else if (Map.stepCount(npc.getLocation(), p) < Map.stepCount(nearest.getLocation(), p)) {
					nearest = npc;
				}
			}
		}
		
		return nearest;
	}
	

	public static NPC getNearestNPC(String[] names) {
		return getNearestNPC(names, getLocalPlayer().getLocation());
	}
	
	public static NPC getNearestNPC(String[] names, Point p) {
		NPC[] npcs = new NPC[names.length];
		
		for (int i = 0; i < names.length; i++) {
			npcs[i] = getNearestNPC(names[i]);
		}
		
		NPC nearest = null;
		
		for (int i = 0; i < npcs.length; i++) {
			if (nearest == null) {
				nearest = npcs[i];
			}
			else if ((npcs[i] != null) && (Map.stepCount(npcs[i].getLocation(), p) < Map.stepCount(nearest.getLocation(), p))) {
				nearest = npcs[i];
			}
		}
		
		return nearest;
	}
	
	public static NPC getNearestNPC(int id) {
		return getNearestNPC(id, getLocalPlayer().getLocation());
	}
	
	public static NPC getNearestNPC(int id, Point p) {
		NPC nearest = null;
		
		for (NPC npc : getNPCs()) {
			if ((npc != null) && (npc.getID() == id)) {
				if (nearest == null) {
					nearest = npc;
				}
				else if (Map.stepCount(npc.getLocation(), p) < Map.stepCount(nearest.getLocation(), p)) {
					nearest = npc;
				}
			}
		}
		
		return nearest;
	}
	
	public static NPC getNearestNPC(int[] ids) {
		return getNearestNPC(ids, getLocalPlayer().getLocation());
	}
	
	public static NPC getNearestNPC(int[] ids, Point p) {
		NPC[] npcs = new NPC[ids.length];
		
		for (int i = 0; i < ids.length; i++) {
			npcs[i] = getNearestNPC(ids[i]);
		}
		
		NPC nearest = null;
		
		for (int i = 0; i < npcs.length; i++) {
			if (nearest == null) {
				nearest = npcs[i];
			}
			else if ((npcs[i] != null) && (Map.stepCount(npcs[i].getLocation(), p) < Map.stepCount(nearest.getLocation(), p))) {
				nearest = npcs[i];
			}
		}
		
		return nearest;
	}
	
	public static Player getLocalPlayer() {
		try {
			return (Player) Class.forName("Class287").getDeclaredField("myPlayer").get(null);
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getRunEnergy() {
		try {
			return (int) Class.forName("InternalMapLibrary").getDeclaredMethod("getRunEnergy", new Class[0]).invoke(null, new Object[0]);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public static Point convertToGlobalLocation(Point p) {
		try {
			Class<?> clazz = Class.forName("InternalMapLibrary");
			int gameSceneBaseX = (int) clazz.getDeclaredMethod("getGameSceneBaseX").invoke(null, new Object[0]);
			int gameSceneBaseY = (int) clazz.getDeclaredMethod("getGameSceneBaseY").invoke(null, new Object[0]);
			return new Point((gameSceneBaseX * -1760580017) + p.x, (gameSceneBaseY * 283514611) + p.y);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static Point convertToLocalLocation(Point p) {
		try {
			Class<?> clazz = Class.forName("InternalMapLibrary");
			int gameSceneBaseX = (int) clazz.getDeclaredMethod("getGameSceneBaseX").invoke(null, new Object[0]);
			int gameSceneBaseY = (int) clazz.getDeclaredMethod("getGameSceneBaseY").invoke(null, new Object[0]);
			return new Point(p.x - (gameSceneBaseX * -1760580017), p.y - (gameSceneBaseY * 283514611));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void walkBy(Point p) {
		try {
			Class.forName("InternalMapLibrary").getDeclaredMethod("walkBy", new Class[]{Point.class}).invoke(null, new Object[]{p});
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void walkToLocal(Point p) {
		try {
			Class.forName("InternalMapLibrary").getDeclaredMethod("walkToLocal", new Class[]{Point.class}).invoke(null, new Object[]{p});
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void walkToGlobal(Point p) {
		try {
			Class.forName("InternalMapLibrary").getDeclaredMethod("walkToGlobal", new Class[]{Point.class}).invoke(null, new Object[]{p});
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void walkPath(Path path) {
		for (Point p : path.getPoints()) {
			walkToGlobal(p);
		}
	}
	
	public static int stepCount(Point p1, Point p2) {
		try {
			return (int) Class.forName("InternalMapLibrary").getDeclaredMethod("stepCount", new Class[]{Point.class, Point.class}).invoke(null, new Object[]{p1, p2});
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public static GameObject[] getObjects() {
		try {
			@SuppressWarnings("unchecked")
			List<GameObject> objs = (List<GameObject>) Class.forName("Class409").getDeclaredField("instances").get(null);
			
			List<GameObject> filtered = new ArrayList<GameObject>();
			
			for (GameObject obj : objs) {
				if (obj != null) {
					filtered.add(obj);
				}
			}
			
			return filtered.toArray(new GameObject[0]);
			
		} catch (IllegalArgumentException | IllegalAccessException
				| NoSuchFieldException | SecurityException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
}
