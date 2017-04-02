package dev.flash.aisim;

import java.util.ArrayList;

/**
 * Created by Flash on 02/04/2017.
 */

public class Chunk {
	
	private int x, y;
	private ArrayList<Ai> ais;
	private ArrayList<Food> foods;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		this.ais = new ArrayList<>();
		this.foods = new ArrayList<>();
	}
	
	//GETTERS AND SETTERS
	
	public ArrayList<Ai> getAis() {
		return ais;
	}
	
	public void setAis(ArrayList<Ai> ais) {
		this.ais = ais;
	}
	
	public ArrayList<Food> getFoods() {
		return foods;
	}
	
	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
