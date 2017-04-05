package dev.flash.aisim;

import java.awt.*;
import java.util.Random;

/**
 * Created by Flash on 28/03/2017.
 */

public class FoodSpawner {
	
	Handler handler;
	Timer timer;
	Random random;
	
	
	public FoodSpawner(Handler handler) {
		this.handler = handler;
		this.timer = new Timer(true, 100);
		random = new Random();
	}
	
	public void tick() {
		if(timer.isDone()) {
			spawnFood();
			
		}
	}
	
	public void spawnFood() {
		Vector2 foodPos = new Vector2(random.nextInt(handler.getWorld().getWidth()), random.nextInt(handler.getWorld().getHeight()));
		if(handler.getEntityManager().posClear(foodPos)) {
			handler.getEntityManager().addFood(new Food(handler, foodPos));
		} else {
			spawnAi();
		}
	}
	
	public void spawnAi() {
		Vector2 aiPos = new Vector2(random.nextInt(handler.getWorld().getWidth()), random.nextInt(handler.getWorld().getHeight()));
		if(handler.getEntityManager().posClear(aiPos)) {
			handler.getEntityManager().addEntity(new Ai(handler, aiPos, new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)),3, 5, 4, 5));
		} else {
			spawnFood();
		}
	}
}
