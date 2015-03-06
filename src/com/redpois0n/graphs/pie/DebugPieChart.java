package com.redpois0n.graphs.pie;

import javax.swing.JFrame;

public class DebugPieChart {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final PieChart graph = new PieChart();

		frame.add(graph);

		frame.setVisible(true);
	}

}
