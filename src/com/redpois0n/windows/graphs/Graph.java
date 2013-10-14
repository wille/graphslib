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
		// draw first background rectangle
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, 68, 200);
		g.drawRect(71, 0, 450, 200);

		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, 67, 199);
		g.fillRect(72, 1, 449, 199);
		
		// draw meter squares
		
		

		g.dispose();
	}

	class RepaintThread extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Graph.this.repaint();
					Thread.sleep(1000L);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}