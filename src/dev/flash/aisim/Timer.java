package dev.flash.aisim;

/**
 * Created by Flash on 28/03/2017.
 */

public class Timer {
	
	private boolean on;
	private int length;
	private long lastTime;
	
	public Timer(boolean on, int length) {
		this.on = on;
		this.length = length;
		lastTime = System.currentTimeMillis();
	}
	
	
	public void tick(double delta) {
	
	}
	
	public boolean isDone() {
		if(!on)
			return false;
		if(lastTime + length < System.currentTimeMillis()) {
			lastTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}
	
	
	//GETTERS AND SETTERS
	
	public boolean isOn() {
		return on;
	}
	
	public void setOn(boolean on) {
		this.on = on;
		lastTime = System.currentTimeMillis();
	}
	
	public int getLength() {
		return length;
	}
}
