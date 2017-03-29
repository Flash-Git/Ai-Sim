package dev.flash.aisim;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flash on 13/03/2017.
 */

public class EntityManager {
	
	private ArrayList<Ai> entities;
	private ArrayList<Food> foods;
	
	public EntityManager() {
		entities = new ArrayList<>();
		foods = new ArrayList<>();
	}
	
	public void tick(double delta) {
		for(Ai e : entities) {
			e.tick(delta);
		}
	}
	
	public void render(Graphics g) {
		for(Ai e : entities) {
			e.render(g);
		}
		for(Food f : foods) {
			f.render(g);
		}
	}
	
	public void addEntity(Ai e) {
		entities.add(e);
	}
	
	public void removeEntity(Ai e) {
		entities.remove(e);
	}
	
	public void addFood(Food f) {
		foods.add(f);
	}
	
	public void removeFood(Food f) {
		foods.remove(f);
	}
	
	public boolean posClear(Vector2 pos) {
		for(Ai e : entities) {
			if(e.getPos().x == pos.x && e.getPos().y == pos.y) {
				return false;
			}
		}
		for(Food f : foods) {
			if(f.getPos().x == pos.x && f.getPos().y == pos.y) {
				return false;
			}
		}
		return true;
	}
	
	/*public Ai getEntity(int x, int y) {
		for(Ai e : entities) {
			if(e.getPos().x == x && e.getPos().y == y) {
				return e;
			}
		}
		return null;
	}
	
	public ArrayList<Ai> getEntities() {
		return entities;
	}
	*/
	
}
