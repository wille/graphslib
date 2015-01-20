package com.redpois0n.graphs.country;

import javax.swing.JFrame;


public class DebugCountryGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(700, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final CountryGraph graph = new CountryGraph(new CountryColors());
				
		frame.add(graph);
		
		frame.setVisible(true);
				
		try {
			int max = 10;
			
			graph.add(new Country("gb", max));
			graph.add(new Country("se", max / 2));
			graph.add(new Country("no", max / 2));
			graph.add(new Country("us", max / 4));
			graph.add(new Country("ru", max / 4));	
			graph.add(new Country("co", max / 6));
			graph.add(new Country("ca", max / 6));
			graph.add(new Country("mx", max / 8));
			graph.add(new Country("de", max / 8));
			graph.add(new Country("ca", max / 10));
			graph.add(new Country("fi", max / 10));
	
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
