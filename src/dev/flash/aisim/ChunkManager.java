package dev.flash.aisim;

import java.util.ArrayList;

/**
 * Created by Flash on 02/04/2017.
 */

public class ChunkManager {
	
	private ArrayList<Chunk> chunks;
	
	public ChunkManager() {
		chunks = new ArrayList<>();
	}
	
	public void addChunk(Chunk chunk) {
		chunks.add(chunk);
	}
	
	public void removeChunk(Chunk chunk) {
		chunks.remove(chunk);
	}
	
	public Chunk getChunk(int x, int y) {
		System.out.println(x+ " " +y);
		for(Chunk chunk : chunks) {
			if((int) x / 5 == chunk.getX() && (int) y / 5 == chunk.getY()) {
				return chunk;
			}
		}
		System.err.println("no chunk at " + x + " " + y);
		return null;
	}
	
}
