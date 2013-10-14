package com.redpois0n.windows.graphs;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Graph extends JComponent {
	
	@Override
	public void paintComponent(Graphics g) {
		//draw first background rectangle
		g.setColor(Color.gray);
		g.drawRect(0, 0, 100, 200);
		
		
		g.dispose();
	}


}
