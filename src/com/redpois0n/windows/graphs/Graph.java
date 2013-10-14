package com.redpois0n.windows.graphs;

import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Graph extends JComponent {

	private final IColors colors;
	
	public Graph(IColors colors) {
		this.colors = colors;
	}

	@Override
	public void paintComponent(Graphics g) {
		//draw first background rectangle
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, 100, 250);
		g.drawRect(105, 0, 550, 250);
		
		//draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, 99, 249);
		g.fillRect(106, 1, 549, 249);
		
		g.dispose();
	}


}
