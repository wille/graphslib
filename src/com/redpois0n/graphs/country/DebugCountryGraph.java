package com.redpois0n.graphs.country;

import java.util.Random;

import javax.swing.JFrame;


public class DebugCountryGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(700, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);
		
		final CountryGraph graph = new CountryGraph(new CountryColors());
		
		graph.setBounds(1, 1, frame.getWidth() - 20, frame.getHeight() - 45);
		
		frame.add(graph);
		
		frame.setVisible(true);
		
		Random random = new Random();
		
		try {
			graph.add(new Country("se", 100));
			graph.add(new Country("no", 75));
			graph.add(new Country("us", 50));
			graph.add(new Country("ru", 25));
			graph.add(new Country("gb", 0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		new Thread("Debug repaint thread") {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100L);
						graph.repaint();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}.start();
	}

}
