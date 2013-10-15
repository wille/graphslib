package com.redpois0n.windows.graphs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Graph extends JComponent {
	
	public static final int MAXIMUM_VALUES = 1000;
	
	private final IColors colors;
	private final List<Integer> values = new ArrayList<Integer>();
	
	private int position = 8;
	private String text = "?";
	
	/**
	 * Max is 100 by default, 0 minimum
	 */
	private int maximum = 100; 

	public Graph(IColors colors) {
		this.colors = colors;
		new RepaintThread().start();
		values.add(0);
	}

	@Override
	public void paintComponent(Graphics g) {
		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, 67, this.getHeight() - 1);
		g.fillRect(72, 1, this.getWidth() - 1, this.getHeight() - 1);
		
		// draw meter squares
		g.setColor(colors.getNetColor());
		
		
		//decrease pos
		position -= 4;
		
		for (int p = 0; p < this.getWidth(); p += 13) {				
			g.drawLine(71, p, this.getWidth() - 1, p);
			g.drawLine(71 + p + position, 2, 71 + p + position, this.getHeight());
		}		
		
		//reset pos
		if (position == 0) {
			position = 8;
		}
		
		// draw first background rectangle
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, 68, this.getHeight());
		g.drawRect(71, 0, this.getWidth(), this.getHeight());
		
		//draw main curve 		
		g.setColor(colors.getCurveColor());
		int index = values.size() - 1;
		int value = 0;
		for (int i = this.getWidth() - 3; i > 71; i--) {
			if (index > 0) {
				int latest = value;
				value = values.get(index--);		
				
				value = (int) (((float) value / (float) maximum) * this.getHeight());
					
				if (latest == 0) {
					latest = value;
				}
				
				g.drawLine(i, this.getHeight() - value, i + 5, this.getHeight() - latest);
				
				i -= 4;
			} else {
				break;
			}
		}
		
		int liney = 0;
		
		//draw blocks in left meter
		for (int x = 0; x < 34; x++) {
			
			if (x == 16) {
				x = 18;
			}
			for (int y = 0; y < this.getHeight() - 30; y++) {
				
				if (liney++ == 1) {
					y += 2;
					liney = 0;
				}
				
				if ((x + y) % 2 == 1) {
					g.setColor(colors.getGreenMeterColor());
				} else {
					g.setColor(colors.getInnerFillColor());
				}
				
				g.drawRect(17 + x, 7 + y, 0, 0);
			}
		}
		
		
		
		//draw text
		g.setColor(colors.getCurveColor());
		g.drawString(text, 17, this.getHeight() - 10);

		g.dispose();

	}
	
	public void addValue(int i) {
		values.add(i);
	}
	
	public int getLastValue() {
		if (values.size() > 0) {
			return values.get(values.size() - 1);
		} else {
			return -1;
		}
	}
	
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
	public int getMaximum() {
		return this.maximum;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	class RepaintThread extends Thread {
		
		public RepaintThread() {
			super("Repaint thread");
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Graph.this.repaint();
					Thread.sleep(1000L);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}