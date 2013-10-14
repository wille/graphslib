package com.redpois0n.windows.graphs;

import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Graph extends JComponent {

	private final IColors colors;
	private int position = 10;

	public Graph(IColors colors) {
		this.colors = colors;
		new RepaintThread().start();
	}

	@Override
	public void paintComponent(Graphics g) {
		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, 67, this.getHeight() - 1);
		g.fillRect(72, 1, this.getWidth() - 1, this.getHeight() - 1);
		
		// draw meter squares
		g.setColor(colors.getNetColor());
		
		
		//decrease pos
		position -= 2;
		
		for (int p = 0; p < this.getWidth(); p += 13) {				
			g.drawLine(71, p, this.getWidth() - 1, p);
			g.drawLine(71 + p + position, 2, 71 + p + position, this.getHeight());
		}		
		
		//reset pos
		if (position == 0) {
			position = 10;
		}
		
		// draw first background rectangle
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, 68, this.getHeight());
		g.drawRect(71, 0, this.getWidth(), this.getHeight());

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