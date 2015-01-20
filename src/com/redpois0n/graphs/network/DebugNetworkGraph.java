package com.redpois0n.graphs.network;


import java.util.Random;

import javax.swing.JFrame;


public class DebugNetworkGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1000, 750);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final NetworkGraph graph = new NetworkGraph(new NetworkColors());
		
		new Thread("Random thread") {
			@Override
			public void run() {
				int lastUp = 0;
				int lastDown = 0;
				while (true) {				
					graph.setMaximum(10000);
					
					lastUp = (new Random()).nextInt(10000);
					lastDown = (new Random()).nextInt(10000);
					
					System.out.println(lastUp + "" + lastDown);
					graph.addValues(lastUp, lastDown);
					
					//graph.setText(value + "");
									
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
