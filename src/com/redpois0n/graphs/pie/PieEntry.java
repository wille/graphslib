package com.redpois0n.graphs.pie;

import java.awt.Color;

public class PieEntry {
	
	private double value;
	private Color color;
	
	public PieEntry(double value, Color color) {
		this.value = value;
		this.color = color;
	}
	
	public double getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

}
