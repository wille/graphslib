package graphslib.pie;

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

		List<PieSlice> entries = new ArrayList<PieSlice>();

		entries.add(new PieSlice(25.2D, Color.red));
		entries.add(new PieSlice(25.1D, Color.yellow));
		entries.add(new PieSlice(25.9D, Color.blue));
		entries.add(new PieSlice(35.5D, Color.green));

		PieChart graph = new PieChart(entries);
		graph.setBounds(0, 0, 300, 300);
		
		frame.add(graph);

		frame.setVisible(true);
	}

}
