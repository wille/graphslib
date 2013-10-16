package com.redpois0n.graphs.country;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

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
	}

	public void add(Country country) {
		if (!countries.contains(country)) {
			countries.add(country);
		}
	}

	public void remove(Country country) {
		countries.remove(country);
	}

}
