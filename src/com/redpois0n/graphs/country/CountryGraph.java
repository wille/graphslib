package com.redpois0n.graphs.country;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CountryGraph extends JComponent {

	private final List<Country> countries = new ArrayList<Country>();

	private ICountryColors colors;

	private boolean showIso2 = true;
	private boolean drawNumber = true;

	public CountryGraph(ICountryColors colors) {
		this.colors = colors;
	}

	@Override
	public void paintComponent(Graphics g) {
		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

		// draw background rectangles
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

		int max = 0;

		List<Country> sorted = new ArrayList<Country>();

		// sort countries
		Collections.sort(countries, new Comparator<Country>() {
			public int compare(Country country, Country country1) {
				return country.getNumber() - country1.getNumber();
			}
		});

		// get highest
		for (int i = 0; i < countries.size(); i++) {
			Country country = countries.get(i);

			if (country.getNumber() > max) {
				max = country.getNumber();
			}
		}

		// draw lines and flags
		for (int i = 0; i < countries.size(); i++) {
			Country country = countries.get(i);

			int value = (int) (((float) country.getNumber() / (float) max) * this.getHeight()) - 20;

			int x = 15 + i * 20;

			if (value < 20) {
				value += 20;
			}

			g.setColor(getMainColor(country.getFlag()));
			g.fillRect(x, this.getHeight() - value - 1, 10, this.getHeight());

			g.drawImage(country.getFlag().getImage(), x - 3, this.getHeight() - value - 14, country.getFlag().getIconWidth(), country.getFlag().getIconHeight(), null);
		}

		onListUpdate(countries);
	}

	public void onListUpdate(List<Country> sortedList) {

	}

	public void add(Country country) {
		if (!countries.contains(country)) {
			countries.add(country);
		}
	}

	public void remove(Country country) {
		countries.remove(country);
	}

	public boolean isShowIso2() {
		return showIso2;
	}

	public void setShowIso2(boolean showIso2) {
		this.showIso2 = showIso2;
	}

	public boolean isDrawNumber() {
		return drawNumber;
	}

	public void setDrawNumber(boolean drawNumber) {
		this.drawNumber = drawNumber;
	}

	public static Color getMainColor(ImageIcon icon) {
		BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

		image.createGraphics();
		image.getGraphics().drawImage(icon.getImage(), 0, 0, null);
		int clr = image.getRGB(image.getWidth() / 2, image.getHeight() / 2);

		image.getGraphics().dispose();

		return new Color(clr);
	}

}