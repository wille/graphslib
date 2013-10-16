package com.redpois0n.graphs.taskmgr;


import java.util.Random;

import javax.swing.JFrame;


public class DebugTaskmgrGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1500, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);
		
		final TaskmgrGraph graph = new TaskmgrGraph(new TaskmgrColors());
		
		graph.setBounds(1, 1, frame.getWidth() - 20, frame.getHeight() - 45);
		
		new Thread("Memory thread") {
			@Override
			public void run() {
				while (true) {
					Runtime rt = Runtime.getRuntime();
					
					long current = (rt.totalMemory() - rt.freeMemory()) / 1024L / 1024L;
					long max = rt.totalMemory() / 1024L / 1024L;
					int percent =  (int) (((float) current / (float) max) * 100);
					
					
					graph.setMaximum((int) max);
										
					graph.addValue((int) current);
					
					graph.setText(current + " mb");
					
					System.out.println("Current usage: " + current + ", Maximum usage: " + max + ", Percent: " + percent);
					
					try {
						Thread.sleep(1000L);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};//.start();
		
		new Thread("Random thread") {
			@Override
			public void run() {
				while (true) {				
					graph.setMaximum(10000);
					
					int value = new Random().nextInt(10000);
					
					graph.addValue(value);
					
					graph.setText(value + "");
									
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
