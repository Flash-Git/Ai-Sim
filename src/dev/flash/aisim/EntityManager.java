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
				
			}
		}
		if(tempTimer.isDone()) {
			for(Ai e : entities) {
				getTarget(e);
				//e.getNewTarget();
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
				Chunk testChunk = chunkManager.getChunk((x + aiChunk.getX()) * 5, (y + aiChunk.getY()) * 5);
				if(testChunk == null) {
					continue;
				}
				aiList.addAll(testChunk.getAis());
			}
		}
		//System.out.println(aiList.size());
		Ai aiHighestScore = ai;
		int highestScore = -1000;
		for(Ai targetAi : aiList) {
			if(ai.equals(targetAi)) {
				continue;
			}
			int[] colourScore = getColourScore(ai.getColor(), targetAi.getColor());
			System.out.println(colourScore[0] + " " + colourScore[1]);
			if(highestScore < colourScore[1]) {
				highestScore = colourScore[1];
				aiHighestScore = targetAi;
			}
			if(aiHighestScore.equals(ai)){
				return;
			}
			ai.setTarget(new Vector2(aiHighestScore.getPos().x, aiHighestScore.getPos().y));
			
			System.out.println(aiHighestScore.getPos().x + ", " + aiHighestScore.getPos().y + " : " + colourScore[0] + ", " + colourScore[1]);
		}
	}
	
	public int[] getColourScore(Color colour1, Color colour2) {
		
		int[] colourScore = new int[2];
		
		int red = (colour1.getRed() - colour2.getRed()) - (colour1.getGreen() - colour2.getGreen());
		int green = (colour1.getGreen() - colour2.getGreen()) - (colour1.getBlue() - colour2.getBlue());
		int blue = (colour1.getBlue() - colour2.getBlue()) - (colour1.getRed() - colour2.getRed());
		
		if(red > blue) {
			if(red > green) {
				colourScore[0] = 1;
				colourScore[1] = red;
			} else {
				colourScore[0] = 2;
				colourScore[1] = green;
			}
		} else {
			if(blue > green) {
				colourScore[0] = 3;
				colourScore[1] = blue;
				
			} else {
				colourScore[0] = 2;
				colourScore[1] = green;
			}
		}
		
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
