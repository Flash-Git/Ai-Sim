package dev.flash.aisim;

import java.util.Random;

/**
 * Created by Flash on 28/03/2017.
 */

public class FoodSpawner {
	
	Handler handler;
	EntityManager entityManager;
	Timer timer;
	Random random;
	
	
	public FoodSpawner(Handler handler) {
		this.handler = handler;
		this.timer = new Timer(true, 100);
		random = new Random();
		entityManager = handler.getInstance().getEntityManager();
	}
	
	public void tick() {
		if(timer.isDone()) {
			spawnFood();
			
		}
	}
	
	public void spawnFood() {
		Vector2 foodPos = new Vector2(random.nextInt(200), random.nextInt(200));
		if(entityManager.posClear(foodPos)) {
			entityManager.addFood(new Food(foodPos));
		} else {
			spawnFood();
		}
	}
}
