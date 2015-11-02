package graphslib.network;


import java.util.Random;

import javax.swing.JFrame;


public class DebugNetworkGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final NetworkGraph graph = new NetworkGraph(new NetworkColors());
		
		new Thread("Random thread") {
			@Override
			public void run() {
				int lastUp = 0;
				int lastDown = 0;
				while (true) {									
					lastUp = (new Random()).nextInt((int) Math.pow(1024, 3) / 2);
					lastDown = (new Random()).nextInt((int) Math.pow(1024, 4));

					graph.addValues(lastUp, lastDown);
														
					try {
						Thread.sleep(1000L);
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
