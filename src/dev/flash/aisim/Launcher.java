package dev.flash.aisim;

/**
 * Created by Flash on 11/03/2017.
 */

public class Launcher {
	
	public static void main(String[] args) {
		Instance instance = new Instance("AI SIM", 1100, 600);
		instance.start();
	}
}
