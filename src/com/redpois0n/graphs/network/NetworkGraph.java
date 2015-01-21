package com.redpois0n.graphs.network;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class NetworkGraph extends JComponent {

	/**
	 * Time between ticks in ms
	 */
	public static final long TICKS = 1000L;

	/**
	 * Maximum values saved in memory
	 */
	public static final int MAXIMUM_VALUES = 1000;

	/**
	 * Colors to use
	 */
	private INetworkColors colors;

	/**
	 * Where valued are saved to be drawn
	 */
	private final List<ValuePair> valuePairs = new ArrayList<ValuePair>();

	/**
	 * Current position to draw net at
	 */
	private int position = 9;

	/**
	 * Text to draw
	 */
	private String text = "?";

	/**
	 * Max is 100 by default, 0 minimum
	 */
	private int maximum = 10000;

	/**
	 * Is this component still active
	 */
	private boolean running = true;
	
	/**
	 * Show upload bars
	 */
	private boolean showUp = true;
	
	/**
	 * Show download bars
	 */
	private boolean showDown = true;

	public NetworkGraph() {
		this(true);
	}

	public NetworkGraph(boolean repaintThread) {
		this.colors = new NetworkColors();
		valuePairs.add(new ValuePair(0, 0));

		if (repaintThread) {
			new RepaintThread().start();
		}
	}

	public NetworkGraph(INetworkColors colors) {
		this();
		this.colors = colors;
	}

	public NetworkGraph(INetworkColors colors, boolean repaintThread) {
		this(repaintThread);
		this.colors = colors;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (valuePairs.size() > MAXIMUM_VALUES) {
			for (int i = 0; i < MAXIMUM_VALUES / 10; i++) {
				valuePairs.remove(0);
			}
		}

		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

		// draw meter squares
		g.setColor(new Color(0x000000));

		// decrease pos
		position -= 3;

		// draw lines
		for (int p = 0; p < this.getWidth(); p += 13) {
			g.drawLine(71, p, this.getWidth() - 1, p);
			g.drawLine(71 + p + position, 2, 71 + p + position, this.getHeight());
		}

		// reset pos
		if (position == 0) {
			position = 9;
		}

		// draw background rectangles
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, 68, this.getHeight());
		g.drawRect(71, 0, this.getWidth(), this.getHeight());

		// draw main curve
		int index = valuePairs.size() - 1;
		ValuePair value = new ValuePair(0, 0);
		// set line thickness

		((Graphics2D) g).setStroke(new BasicStroke(2));

		int latestUp = 0;
		int latestDown = 0;
		int drawValueUp = 0;
		int drawValueDown = 0;

		for (int i = this.getWidth() - 3; i > 0; i--) {
			if (index > 0) {
				ValuePair latest = value;
				value = valuePairs.get(index--);

				drawValueUp = (int) (((float) value.getUp() / (float) maximum) * this.getHeight());
				drawValueDown = (int) (((float) value.getDown() / (float) maximum) * this.getHeight());

				if (latest.getUp() == 0) {
					latestUp = drawValueUp;
				}
				
				if (latest.getDown() == 0) {
					latestDown = drawValueDown;
				}

				if (drawUploadBars()) {
					g.setColor(colors.getDownloadColor());
					g.fillRect(i, this.getHeight() - drawValueDown, 10, this.getHeight());
				}
				
				if (drawDownloadBars()) {
					g.setColor(colors.getUploadColor());
					g.fillRect(i, this.getHeight() - drawValueUp, 10, this.getHeight());
				}
				
				latestUp = drawValueUp;
				latestDown = drawValueDown;
				i -= 10;
			} else {
				break;
			}
		}

		// cover up some shit
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, this.getHeight() - 22, 60, 4);
		g.fillRect(1, 6, 60, 3);

		// draw text
	//	g.setColor(colors.getCurveColor());
		//g.drawString(text, 17, this.getHeight() - 10);

		g.dispose();

	}

	public void addValues(int i, int l) {
		valuePairs.add(new ValuePair(i, l));
	}

	public ValuePair getLastValue() {
		if (valuePairs.size() > 0) {
			return valuePairs.get(valuePairs.size() - 1);
		} else {
			return null;
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

	public void dispose() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public INetworkColors getColors() {
		return colors;
	}

	public void setColors(INetworkColors colors) {
		this.colors = colors;
	}

	public boolean drawUploadBars() {
		return showUp;
	}

	public void setDrawUploadBars(boolean showUp) {
		this.showUp = showUp;
	}

	public boolean drawDownloadBars() {
		return showDown;
	}

	public void setDrawDownloadBars(boolean showDown) {
		this.showDown = showDown;
	}

	class RepaintThread extends Thread {

		public RepaintThread() {
			super("Repaint thread");
		}

		/**
		 * Repaints each tick
		 */
		@Override
		public void run() {
			while (running) {
				try {
					NetworkGraph.this.repaint();
					Thread.sleep(TICKS);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}