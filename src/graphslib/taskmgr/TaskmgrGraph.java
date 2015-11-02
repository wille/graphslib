package graphslib.taskmgr;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class TaskmgrGraph extends JComponent {

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
	private ITaskmgrColors colors;

	/**
	 * Where valued are saved to be drawn
	 */
	private final List<Integer> values = new ArrayList<Integer>();

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

	public TaskmgrGraph() {
		this(true);
	}

	public TaskmgrGraph(boolean repaintThread) {
		this.colors = new TaskmgrColors();
		values.add(0);

		if (repaintThread) {
			new RepaintThread().start();
		}
	}

	public TaskmgrGraph(ITaskmgrColors colors) {
		this();
		this.colors = colors;
	}

	public TaskmgrGraph(ITaskmgrColors colors, boolean repaintThread) {
		this(repaintThread);
		this.colors = colors;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (values.size() > MAXIMUM_VALUES) {
			for (int i = 0; i < MAXIMUM_VALUES / 10; i++) {
				values.remove(0);
			}
		}

		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, 67, this.getHeight() - 1);
		g.fillRect(72, 1, this.getWidth() - 1, this.getHeight() - 1);

		// draw meter squares
		g.setColor(colors.getNetColor());

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
		g.setColor(colors.getCurveColor());
		int index = values.size() - 1;
		int value = 0;

		// set line thickness

		((Graphics2D) g).setStroke(new BasicStroke(2));

		for (int i = this.getWidth() - 3; i > 71; i--) {
			if (index > 0) {
				int latest = value;
				value = values.get(index--);

				value = (int) (((float) value / (float) maximum) * this.getHeight());

				if (latest == 0) {
					latest = value;
				}

				g.drawLine(i, this.getHeight() - value, i + 5, this.getHeight() - latest);

				i -= 3;
			} else {
				break;
			}
		}

		// revert line thickness
		((Graphics2D) g).setStroke(new BasicStroke(1));

		// draw blocks in left meter
		int liney = 0;
		for (int x = 0; x < 34; x++) {

			if (x == 16) {
				x = 17;
			}
			liney = 0;
			for (int y = x >= 17 ? 0 : -1; y < this.getHeight() - 30; y++) {

				if (liney++ == 1) {
					y += 2;
					liney = 0;
				}

				if ((x + y) % 2 == 1) {
					g.setColor(colors.getGreenMeterColor());
				} else {
					g.setColor(colors.getInnerFillColor());
				}

				g.drawRect(17 + x, x >= 17 ? 6 + y : 7 + y, 0, 0);
			}
		}

		// draw meter percent
		value = getLastValue();

		int todraw = (int) (((float) value / (float) maximum) * (this.getHeight() - 33));
		int drawn = 0;

		if (value > 0) {
			for (int x = 0; x < 34; x++) {

				if (x == 16) {
					x = 17;
				}

				liney = -1;

				for (int y = this.getHeight() - 32; y > 2 && drawn * 2 < todraw; y--) {
					drawn++;

					if (liney++ == 1) {
						y -= 2;
						liney = 0;
					}

					g.setColor(colors.getInnerFillColor());

					g.drawRect(17, 7 + y, 15, 1);
					g.drawRect(34, 7 + y, 16, 1);

					g.setColor(colors.getCurveColor());

					g.drawRect(17, 8 + y, 15, 0);
					g.drawRect(34, 8 + y, 16, 0);

				}
			}

		}

		// cover up some shit
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, this.getHeight() - 22, 60, 4);
		g.fillRect(1, 6, 60, 3);

		// draw text
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

	public void dispose() {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public ITaskmgrColors getColors() {
		return colors;
	}

	public void setColors(ITaskmgrColors colors) {
		this.colors = colors;
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
					TaskmgrGraph.this.repaint();
					Thread.sleep(TICKS);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}