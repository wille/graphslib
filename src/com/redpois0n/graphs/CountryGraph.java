package com.redpois0n.graphs;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.redpois0n.Country;

@SuppressWarnings("serial")
public class CountryGraph extends JComponent {
	
	private final List<Country> countries = new ArrayList<Country>();
	
	public CountryGraph() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
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
