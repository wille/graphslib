package com.redpois0n.graphs;

import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class CountryGraph extends JComponent {
	
	public CountryGraph() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
	}
	
	public void dispose() {
		running = false;
	}
	
	class RepaintThread extends Thread {

		public RepaintThread() {
			super("Repaint thread");
		}

		/**
		 * Repaints each tick
		 */
		@Override
		public void run() {
			while (running) {
				try {
					CountryGraph.this.repaint();
					Thread.sleep(TICKS);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}


}
