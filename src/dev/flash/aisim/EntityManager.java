package dev.flash.aisim;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flash on 13/03/2017.
 */

public class EntityManager {
	
	private ArrayList<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<>();
	}
	
	public void tick(double delta) {
		for(Entity e : entities) {
			e.tick(delta);
		}
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) {
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public Entity getEntity(int x, int y) {
		for(Entity e : entities) {
			if(e.getPos().x == x && e.getPos().y == y) {
				return e;
			}
		}
		return null;
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	
}
