package com.redpois0n.graphs.graph;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.redpois0n.graphs.utils.IconUtils;


public class DebugGraph {

	public static void main(String[] args) {
		boolean countries = JOptionPane.showConfirmDialog(null, "To display countries press Yes", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

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
			
			if (countries) {
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
			} else {
				graph.add(new GraphEntry("Windows 8", max, IconUtils.getIcon("os_win8")));
				graph.add(new GraphEntry("Mac OS X", max / 2, IconUtils.getIcon("os_mac")));
				graph.add(new GraphEntry("Linux", max / 3, IconUtils.getIcon("os_linux")));
				graph.add(new GraphEntry("Unknown", 1, IconUtils.getIcon("os_unknown")));
			}
	
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
