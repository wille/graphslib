package graphslib.pie;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class PieChart extends JComponent {

	private List<PieSlice> entries;
	private int startAngle = 90;

	public PieChart(List<PieSlice> entries) {
		this.entries = entries;
	}
	
	public PieChart() {
		this.entries = new ArrayList<PieSlice>();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int p = startAngle;
		
		int total = -1;
		
		for (PieSlice entry : entries) {
			total += entry.getValue();
		}

		for (int i = 0; i < entries.size(); i++) {
			PieSlice entry = entries.get(i);
			
			
			g2d.setColor(entry.getColor());

			double val = entry.getValue();
			double angle = (val / total) * 360;

			g2d.fillArc(0, 0, super.getWidth(), super.getWidth(), p, (int) angle);

			p = (int) (p + angle);
		}

	}
}