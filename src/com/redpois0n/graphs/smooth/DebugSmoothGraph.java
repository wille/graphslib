package com.redpois0n.graphs.smooth;

import javax.swing.JFrame;

public class DebugSmoothGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final SmoothGraph graph = new SmoothGraph(new SmoothColors());

		new Thread("Random thread") {
			@Override
			public void run() {
				int lastAvailable = 0;
				int lastUsed = 0;
				while (true) {

					lastUsed = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
					lastAvailable = (int) Runtime.getRuntime().totalMemory();

					graph.addValues(lastUsed, lastAvailable);

					try {
						Thread.sleep(50L);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		frame.add(graph);

		frame.setVisible(true);
	}

}
