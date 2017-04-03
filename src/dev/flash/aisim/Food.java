package dev.flash.aisim;

import java.awt.*;

/**
 * Created by Flash on 28/03/2017.
 */

public class Food {
	
	private Vector2 pos;
	
	public Food(Handler handler, Vector2 pos) {
		this.pos = pos;
		handler.getChunkManager().getChunk((int) pos.x, (int) pos.y).getFoods().add(this);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) pos.x * 8, (int) pos.y * 8, 7, 7);
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
}
