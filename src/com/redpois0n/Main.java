package com.redpois0n;


import javax.swing.JFrame;

import com.redpois0n.windows.graphs.DefaultColors;
import com.redpois0n.windows.graphs.Graph;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(750, 450);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		final Graph graph = new Graph(new DefaultColors());
		
		new Thread("Memory thread") {
			@Override
			public void run() {
				while (true) {
					Runtime rt = Runtime.getRuntime();
					
					long current = (rt.totalMemory() - rt.freeMemory()) / 1024L / 1024L;
					long max = rt.totalMemory() / 1024L / 1024L;
					
					graph.setMaximum((int) max);
					
					graph.addValue((int) current);
					
					System.out.println("Current usage: " + current + ", Maximum usage: " + max);
					
					try {
						Thread.sleep(1000L);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		frame.add(graph);
	}

}
