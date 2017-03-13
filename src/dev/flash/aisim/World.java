package dev.flash.aisim;

import java.awt.*;

/**
 * Created by Flash on 11/03/2017.
 */

public class World {
	
	private int width, height;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}
	
	public void init() {
		Vector2[][] tiles = new Vector2[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = new Vector2(0, 0);
			}
		}
	}
	
	public void tick(double delta) {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}