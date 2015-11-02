package graphslib.pie;

import java.awt.Color;

public class PieSlice {
	
	private double value;
	private Color color;
	private String text;
	
	public PieSlice(double value, Color color, String text) {
		this.value = value;
		this.color = color;
		this.text = text;
	}
	
	public PieSlice(double value, Color color) {
		this(value, color, null);
	}
	
	public double getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}
	
	public String getText() {
		return text;
	}

}
