package dev.flash.aisim;

import java.awt.*;

/**
 * Created by Flash on 02/04/2017.
 */

public class Colour {
	
	public static int getColourScore(Color colour1, Color colour2) {
		int red = Math.abs(colour1.getRed() - colour2.getRed());
		int green = Math.abs(colour1.getGreen() - colour2.getGreen());
		int blue = Math.abs(colour1.getBlue() - colour2.getBlue());
		return red + green + blue;
	}
	
	
}
