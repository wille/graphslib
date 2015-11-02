package com.redpois0n.graphs.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorUtils {

	/**
	 * Returns the common color for the image
	 * @param image
	 * @return
	 */
	public static Color getCommonColor(BufferedImage image) {		
		int r = 0;
		int g = 0;
		int b = 0;
		
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int pixel = image.getRGB(x, y);
				Color c = new Color(pixel);
				r += c.getRed();
				g += c.getGreen();
				b += c.getBlue();
			}
		}
		
		int total = image.getWidth() * image.getHeight();
		r /= total;
		g /= total;
		b /= total;
		
		return new Color(r, g, b);
	}
}
