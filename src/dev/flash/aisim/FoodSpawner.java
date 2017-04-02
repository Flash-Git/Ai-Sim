package dev.flash.aisim;

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
		this.timer = new Timer(true, 15);
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
			spawnFood();
		}
	}
}
