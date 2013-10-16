package com.redpois0n.graphs.country;

import javax.swing.JFrame;


public class DebugCountryGraph {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);
		
		final CountryGraph graph = new CountryGraph();
		
		graph.setBounds(1, 1, frame.getWidth() - 20, frame.getHeight() - 45);
		
		frame.setVisible(true);
	}

}
