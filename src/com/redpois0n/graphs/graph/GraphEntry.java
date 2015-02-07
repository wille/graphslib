package com.redpois0n.graphs.graph;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class GraphEntry {
	
	public static final Map<String, ImageIcon> ICON_CACHE = new HashMap<String, ImageIcon>();
	
	private String path;
	private final ImageIcon icon;	
	private final String display;
	private int number;
	private Color numberColor;
	
	public GraphEntry(String display, int number, String path) throws Exception {
		this.display = display;
		this.number = number;
		this.path = path;
		this.icon = loadIcon(display);
	}
	
	public GraphEntry(String display, int number) throws Exception {
		this(display, number, "/icons/");
	}
	
	public GraphEntry(String display, int number, ImageIcon icon) throws Exception {
		this.display = display;
		this.number = number;
		this.icon = icon;
	}
	
	public String getDisplay() {
		return display;
	}
	
	public ImageIcon getIcon() {
		return icon;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Color getNumberColor() {
		return numberColor;
	}

	public void setNumberColor(Color numberColor) {
		this.numberColor = numberColor;
	}

	public ImageIcon loadIcon(String iconName) throws Exception {
		ImageIcon icon;
		
		if (ICON_CACHE.containsKey(iconName.toLowerCase())) {
			icon = ICON_CACHE.get(iconName.toLowerCase());
		} else {
			icon = new ImageIcon(GraphEntry.class.getResource(path + iconName.toLowerCase() + ".png"));
			ICON_CACHE.put(iconName.toLowerCase(), icon);
		}
				
		return icon;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof GraphEntry) {
			return ((GraphEntry)o).display.equals(this.display);
		} else {
			return false;
		}
	}
}
