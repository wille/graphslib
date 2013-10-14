package com.redpois0n;


import javax.swing.JFrame;

import com.redpois0n.windows.graphs.Graph;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(750, 450);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		Graph graph = new Graph();
		
		frame.add(graph);
	}

}
