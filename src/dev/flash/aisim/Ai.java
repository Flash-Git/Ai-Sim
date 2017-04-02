package dev.flash.aisim;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Flash on 13/03/2017.
 */

public class Ai {
	
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	
	private Vector2 pos;
	
	private int health;
	private int maxHealth;
	private int age;
	private int maxAge;
	
	
	private int aggro;
	private int hpReq;//%
	
	private int family;
	
	private Color color;
	
	private Handler handler;
	
	private Vector2 target;
	
	private ArrayList<Node> path;
	
	public Ai(Handler handler, Vector2 pos, Color color, int health, int age, int aggro, int hpReq) {
		this.handler = handler;
		this.pos = pos;
		this.color = color;
		this.health = health;
		this.maxHealth = health;
		this.age = age;
		this.maxAge = age;
		this.aggro = aggro;
		this.hpReq = hpReq;
		path = new ArrayList<>();
		target = new Vector2(pos.x, (int) pos.y);
		handler.getChunkManager().getChunk((int) pos.x, (int) pos.y).getAis().add(this);
		System.out.println(handler.getChunkManager().getChunk((int) pos.x, (int) pos.y).getX() + " " + handler.getChunkManager().getChunk((int) pos.x, (int) pos.y).getY());
		
	}
	
	public void tick(double delta) {
		
	}
	
	public void turn() {
		AStar.generatePath(handler, path, new Vector2((int) pos.x, (int) pos.y), target);
		//Random direction = new Random();
		//move(direction.nextInt(4));
		
		if(path.size() == 0)
			return;
		
		move(path.get(0).getX(), path.get(0).getY());
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) pos.x * 8 + 1, (int) pos.y * 8 + 1, 6, 6);
	}
	
	public boolean move(int x, int y) {
		try {
			handler.getChunkManager().getChunk((int) pos.x, (int) pos.y).getAis().remove(this);
			handler.getChunkManager().getChunk(x, y).getAis().add(this);
			pos.x = x;
			pos.y = y;
			return true;
			
		} catch(Exception e) {
			System.err.println("failed to move ai " + e);
			return false;
		}
	}
	
	public boolean move(int direction) {
		Vector2 newPos;
		switch(direction) {
			case UP:
				newPos = new Vector2(pos.x, pos.y - 1);
				break;
			case RIGHT:
				newPos = new Vector2(pos.x + 1, pos.y);
				break;
			case DOWN:
				newPos = new Vector2(pos.x, pos.y + 1);
				break;
			case LEFT:
				newPos = new Vector2(pos.x - 1, pos.y);
				break;
			default:
				newPos = pos;
		}
		if(!handler.getEntityManager().posClear(newPos)) {
			return false;
		}
		pos = newPos;
		return true;
	}
	
	public boolean getNewTarget() {
		Ai red = handler.getWorld().red;
		Ai blue = handler.getWorld().blue;
		Ai green = handler.getWorld().green;
		if(this.equals(handler.getWorld().red)) {
			target.x = green.pos.x;
			target.y = green.pos.y;
			return true;
		}
		if(this.equals(handler.getWorld().green)) {
			target.x = blue.pos.x;
			target.y = blue.pos.y;
			return true;
			
		}
		if(this.equals(handler.getWorld().blue)) {
			target.x = red.pos.x;
			target.y = red.pos.y;
			return true;
			
		}
		return false;
		
		/*
		Random random = new Random();
		
		Vector2 newPos = new Vector2(random.nextInt(handler.getWorld().getWidth()), random.nextInt(handler.getWorld().getHeight()));
		
		if(!handler.getEntityManager().posClear(newPos)) {
			return false;
		}
		target = newPos;
		return true;
		*/
	}
	
	
	//GETTERS AND SETTERS
	
	
	public Color getColor() {
		return color;
	}
	
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
