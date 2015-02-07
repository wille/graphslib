package com.redpois0n.graphs.graph;

import javax.swing.JFrame;


public class DebugGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(700, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final Graph graph = new Graph(new GraphColors());
				
		frame.add(graph);
		
		frame.setVisible(true);
				
		try {
			int max = 10;
			
			graph.add(new GraphEntry("gb", max, "/flags/"));
			graph.add(new GraphEntry("se", max / 2, "/flags/"));
			graph.add(new GraphEntry("no", max / 2, "/flags/"));
			graph.add(new GraphEntry("us", max / 4, "/flags/"));
			graph.add(new GraphEntry("ru", max / 4, "/flags/"));	
			graph.add(new GraphEntry("co", max / 6, "/flags/"));
			graph.add(new GraphEntry("ca", max / 6, "/flags/"));
			graph.add(new GraphEntry("mx", max / 8, "/flags/"));
			graph.add(new GraphEntry("de", max / 8, "/flags/"));
			graph.add(new GraphEntry("ca", max / 10, "/flags/"));
			graph.add(new GraphEntry("fi", max / 10, "/flags/"));
	
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
