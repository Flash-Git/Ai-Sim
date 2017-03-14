package dev.flash.aisim;

import java.awt.*;

/**
 * Created by Flash on 13/03/2017.
 */

public class Entity {
	
	private Vector2 pos;
	
	private int health;
	private int maxHealth;
	private int age;
	private int maxAge;
	
	
	private int aggro;
	private int hpReq;//%
	
	private int family;
	
	private Color color;
	
	public Entity(Vector2 pos, int family, int health, int age, int aggro, int hpReq) {
		this.pos = pos;
		this.family = family;
		this.health = health;
		this.maxHealth = health;
		this.age = age;
		this.maxAge = age;
		this.aggro = aggro;
		this.hpReq = hpReq;
		
		int col1 = 0;
		int col2 = 0;
		int col3 = 0;

		color = new Color((col1)%255, (col2)%255, (col3)%255);
	}
	
	public void tick(double delta) {
		
	}
	
	public void turn() {
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect((int) pos.x*8+1, (int) pos.y*8+1, 6, 6);
	}
	
	
	//GETTERS AND SETTERS
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getMaxAge() {
		return maxAge;
	}
	
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	
	public int getAggro() {
		return aggro;
	}
	
	public void setAggro(int aggro) {
		this.aggro = aggro;
	}
	
	public int getHpReq() {
		return hpReq;
	}
	
	public void setHpReq(int hpReq) {
		this.hpReq = hpReq;
	}
}
