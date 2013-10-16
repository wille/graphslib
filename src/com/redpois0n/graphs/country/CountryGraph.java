package com.redpois0n.graphs.country;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CountryGraph extends JComponent {

	private final List<Country> countries = new ArrayList<Country>();

	private ICountryColors colors;

	public CountryGraph(ICountryColors colors) {
		this.colors = colors;
	}

	@Override
	public void paintComponent(Graphics g) {
		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, 100, this.getHeight() - 1);
		g.fillRect(105, 1, this.getWidth() - 1, this.getHeight() - 1);

		// draw background rectangles
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, 101, this.getHeight() - 1);
		g.drawRect(104, 0, this.getWidth() - 106, this.getHeight() - 1);

		
		int max = 0;
		
		List<Country> sorted = new ArrayList<Country>();
		
		for (int i = 0; i < countries.size() && sorted.size() != countries.size(); i++) {
			Country country = countries.get(i);
			
			if (country.getNumber() > max) {
				sorted.add(0, country);
				max = country.getNumber();
			}
		}
				
		for (int i = 0; i < sorted.size(); i++) {
			Country country = countries.get(i);
			
			int value = (int) (((float) country.getNumber() / (float) max) * this.getHeight());
			
			System.out.println(value);

			g.setColor(getMainColor(country.getFlag()));
			g.fillRect(115 + (i), value, 10, this.getWidth() - 1);
		}
		
	}

	public void add(Country country) {
		if (!countries.contains(country)) {
			countries.add(country);
		}
	}

	public void remove(Country country) {
		countries.remove(country);
	}

	public static Color getMainColor(ImageIcon icon) {
		BufferedImage image = new BufferedImage(BufferedImage.TYPE_INT_RGB, icon.getIconWidth(), icon.getIconHeight());
		image.createGraphics();
		image.getGraphics().drawImage(icon.getImage(), 0, 0, null);
		int clr = image.getRGB(image.getWidth() / 2, image.getHeight() / 2);
		int red = (clr & 0x00ff0000) >> 16;
		int green = (clr & 0x0000ff00) >> 8;
		int blue = clr & 0x000000ff;

		image.getGraphics().dispose();
		
		return new Color(clr);
	}

}
