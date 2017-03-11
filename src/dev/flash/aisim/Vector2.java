package dev.flash.aisim;

/**
 * Created by Flash on 11/03/2017.
 */

public class Vector2 {
	
	public double x, y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector2 add(Vector2 vec1, Vector2 vec2) {
		return new Vector2(vec1.x + vec2.x, vec1.y + vec2.y);
	}
	public Vector2 add(Vector2 vec) {
		this.x+=vec.x;
		this.y+=vec.y;
		return this;
	}
	
	public static Vector2 sub(Vector2 vec1, Vector2 vec2) {
		return new Vector2(vec1.x - vec2.x, vec1.y - vec2.y);
	}
	
	public Vector2 sub(Vector2 vec2) {
		this.x -= vec2.x;
		this.y -= vec2.y;
		return this;
	}
	
	public static Vector2 norm(Vector2 vec) {
		double div = Math.sqrt(vec.x * vec.x + vec.y * vec.y);
		return new Vector2(vec.x / div, vec.y / div);
	}
	public void norm() {
		double div = Math.sqrt(this.x * this.x + this.y * this.y);
		this.x /= div;
		this.y /= div;
	}
	
	public static Vector2 mult(Vector2 vec, float f) {
		return new Vector2(vec.x * f, vec.y * f);
	}
	
	public Vector2 mult(float f) {
		this.x *= f;
		this.y *= f;
		return this;
	}
	
	public static boolean equ(Vector2 vec1, Vector2 vec2) {
		if (vec1.x == vec2.x && vec1.y == vec2.y) {
			return true;
		}
		return false;
	}
	
	public static float magn(Vector2 vec) {
		return (float) Math.sqrt(vec.x * vec.x + vec.y * vec.y);
	}
	
	public float magn() {
		return magn(this);
	}
	
	
	public float incrMagn(float incr) {
		float magn = this.magn();
		float mult = (this.magn()+incr)/this.magn();
		mult(mult);
		return incr+magn;
	}
}
