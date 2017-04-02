package dev.flash.aisim;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flash on 11/03/2017.
 */

public class World {
	
	private int width, height;
	private EntityManager entityManager;
	private ChunkManager chunkManager;
	
	private FoodSpawner foodSpawner;
	private Handler handler;
	
	public static ArrayList<Node> allNodes;
	
	public Ai red;
	public Ai green;
	public Ai blue;
	
	public World(Handler handler, int width, int height) {
		this.handler = handler;
		this.width = width;
		this.height = height;
		
	}
	
	public void init() {
		chunkManager = new ChunkManager();
		entityManager = new EntityManager(handler);
		foodSpawner = new FoodSpawner(handler);
		
		allNodes = new ArrayList<>();
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				allNodes.add(new Node(x, y, null));
				if(x % 5 == 0 && y % 5 == 0) {
					chunkManager.addChunk(new Chunk(x/5, y/5));
					System.out.println("new chunk " + x/5 + " " +y/5);
					
				}
			}
		}
		
		entityManager.addEntity(green = new Ai(handler, new Vector2(45, 30), new Color(20, 200, 20), 3, 5, 4, 5));
		entityManager.addEntity(red = new Ai(handler, new Vector2(30, 25), new Color(200, 20, 2), 3, 5, 4, 5));
		entityManager.addEntity(blue = new Ai(handler, new Vector2(60, 20), new Color(20, 20, 200), 3, 5, 4, 5));
	}
	
	public void tick(double delta) {
		entityManager.tick(delta);
		foodSpawner.tick();
		
		//System.out.println();
	}
	
	public void render(Graphics g) {
		entityManager.render(g);
	}
	
	//GETTERS AND SETTERS
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public ChunkManager getChunkManager() {
		return chunkManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}