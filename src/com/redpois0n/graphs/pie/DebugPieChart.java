package com.redpois0n.graphs.pie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class DebugPieChart {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(350, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		List<PieEntry> entries = new ArrayList<PieEntry>();

		entries.add(new PieEntry(25, Color.red));
		entries.add(new PieEntry(25, Color.yellow));
		entries.add(new PieEntry(25, Color.blue));
		entries.add(new PieEntry(25, Color.green));

		PieChart graph = new PieChart(entries);
		graph.setBounds(0, 0, 300, 300);
		
		frame.add(graph);

		frame.setVisible(true);
	}

}
