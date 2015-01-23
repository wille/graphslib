package com.redpois0n.graphs.network;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
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
	private int maximum = 100;

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
	
	/**
	 * Grids to show
	 */
	private int grids = 5;

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
		while (valuePairs.size() * 11 > this.getWidth() - 50) {
			if (valuePairs.size() > 0) {
				valuePairs.remove(0);
			} else {
				break;
			}
		}

		int max = 0;
		
		for (ValuePair vp : valuePairs) {
			if (vp.getDown() > max && drawDownloadBars()) {
				max = vp.getDown();
			}
			
			if (vp.getUp() > max && drawUploadBars()) {
				max = vp.getUp();
			}
		}
		
		if (max < 10) {
			max = 10;
		}
		
		setMaximum(max);
		
		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
		
		// draw grid
		g.setColor(colors.getGridColor());
		for (int i = 0; i < this.getHeight(); i += this.getHeight() / grids) {
			g.drawLine(0, i, this.getWidth(), i);
		}

		// draw data size strings
		int lineNumber = 0;
		for (int i = 0; i < this.getHeight(); i += this.getHeight() / grids) {
			int part = max / grids;
			int what = max - (part * (lineNumber++));
			g.drawString(DataUnits.getAsString(what) + "", 5, i + 15);
		}
		
		// decrease pos
		position -= 3;

		// reset pos
		if (position == 0) {
			position = 9;
		}

		// draw main curve
		int index = valuePairs.size() - 1;
		ValuePair value = new ValuePair(0, 0);
		// set line thickness

		((Graphics2D) g).setStroke(new BasicStroke(2));
        ((Graphics2D) g).setComposite(AlphaComposite.SrcOver.derive(0.5F));

		int drawValueUp = 0;
		int drawValueDown = 0;

		for (int i = this.getWidth() - 11; i > 50; i--) {
			if (index > 0) {
				value = valuePairs.get(index--);

				drawValueUp = (int) (((float) value.getUp() / (float) maximum) * this.getHeight());
				drawValueDown = (int) (((float) value.getDown() / (float) maximum) * this.getHeight());
				
				boolean drawDownFirst = drawValueDown > drawValueUp;
				
				if (drawDownFirst && drawDownloadBars() || !drawDownFirst && drawUploadBars()) {
					g.setColor(drawDownFirst ? colors.getDownloadColor() : colors.getUploadColor());
					g.fillRect(i, this.getHeight() - (drawDownFirst ? drawValueDown : drawValueUp), 10, this.getHeight());
				}
				
				if (!drawDownFirst && drawDownloadBars() || drawDownFirst && drawUploadBars()) {
					g.setColor(!drawDownFirst ? colors.getDownloadColor() : colors.getUploadColor());
					g.fillRect(i, this.getHeight() - (!drawDownFirst ? drawValueDown : drawValueUp), 10, this.getHeight());
				}
					
				i -= 10;
			} else {
				break;
			}
		}

		// draw text
		//	g.setColor(colors.getCurveColor());
		//g.drawString(text, 17, this.getHeight() - 10);


		// draw background rectangles
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		
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

	public int getGrids() {
		return grids;
	}

	public void setGrids(int grids) {
		this.grids = grids;
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