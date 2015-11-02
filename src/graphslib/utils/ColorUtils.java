package graphslib.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ColorUtils {

	public static Color getCommonColor(Image aimage) {
		if (aimage instanceof BufferedImage) {
			return getCommonColor((BufferedImage) aimage);
		}
		
		BufferedImage image = new BufferedImage(aimage.getWidth(null), aimage.getHeight(null), BufferedImage.TYPE_INT_RGB);

		Graphics g = image.createGraphics();
		g.drawImage(aimage, 0, 0, null);
		g.dispose();
		
		return getCommonColor(image);
	}

	/**
	 * Returns the common color for the image
	 * 
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
