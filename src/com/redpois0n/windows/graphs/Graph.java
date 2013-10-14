package com.redpois0n.windows.graphs;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Graph extends JComponent {
	
	public static final Color BORDER_COLOR = Color.gray;
	public static final Color INNER_COLOR = Color.black;
	
	@Override
	public void paintComponent(Graphics g) {
		//draw first background rectangle
		g.setColor(BORDER_COLOR);
		g.drawRect(0, 0, 100, 250);
		g.drawRect(105, 0, 550, 250);
		
		//draw inner color
		g.setColor(INNER_COLOR);
		
		
		g.dispose();
	}


}
