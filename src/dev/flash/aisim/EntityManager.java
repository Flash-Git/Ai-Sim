package dev.flash.aisim;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flash on 13/03/2017.
 */

public class EntityManager {
	
	private ArrayList<Ai> entities;
	private ArrayList<Food> foods;
	private Timer turnTimer;
	private Timer tempTimer;
	private Handler handler;
	
	public EntityManager(Handler handler) {
		this.handler = handler;
		entities = new ArrayList<>();
		foods = new ArrayList<>();
		turnTimer = new Timer(true, 500);
		tempTimer = new Timer(true, 4000);
	}
	
	public void tick(double delta) {
		for(Ai e : entities) {
			e.tick(delta);
		}
		if(turnTimer.isDone()) {
			for(Ai e : entities) {
				e.turn();
				getTarget(e);
			}
		}
		if(tempTimer.isDone()) {
			for(Ai e : entities) {
				e.getNewTarget();
			}
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
	
	public void getTarget(Ai ai) {
		ChunkManager chunkManager = handler.getChunkManager();
		Chunk aiChunk = chunkManager.getChunk((int) ai.getPos().x, (int) ai.getPos().y);
		//System.out.println(aiChunk.getX());
		ArrayList<Ai> aiList = new ArrayList<>();
		for(int x = -3; x < 7; x++) {
			for(int y = -3; y < 7; y++) {
				Chunk testChunk = chunkManager.getChunk((x + aiChunk.getX())*5, (y + aiChunk.getY())*5);
				if(testChunk == null) {
					continue;
				}
				aiList.addAll(testChunk.getAis());
			}
		}
		System.out.println(aiList.size());
		for(Ai targetAi : aiList) {
			getColourScore(ai.getColor(), targetAi.getColor());
		}
	}
	
	public int[] getColourScore(Color colour1, Color colour2) {
		int red = colour1.getRed() - colour2.getRed();
		int green = colour1.getGreen() - colour2.getGreen();
		int blue = colour1.getBlue() - colour2.getBlue();
		int[] colourScore = new int[1];
		colourScore[0] = 5;
		
		/*
		if(red > green && red > blue) {
			return 0;
		}
		if(green > red && green > blue) {
			return 1;
		}
		if(blue > red && blue > green) {
			return 2;
		}
		return 3;
		*/
		return colourScore;
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
