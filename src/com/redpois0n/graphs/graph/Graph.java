package com.redpois0n.graphs.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class Graph extends JComponent {

	private final List<GraphEntry> entries = new ArrayList<GraphEntry>();

	/**
	 * Colors
	 */
	private IGraphColors colors;
	
	/**
	 * Shows the number
	 */
	private boolean drawNumber = true;
	
	/**
	 * Is active
	 */
	private boolean isActive = true;
	
	/**
	 * 
	 */
	private boolean drawBackgroundSquare = false;
	
	public Graph() {
		this.colors = new GraphColors();
		
		final JPopupMenu popup = new JPopupMenu();
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	
		final JCheckBoxMenuItem toggleNumber = new JCheckBoxMenuItem("Draw number", true);
		toggleNumber.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawNumber = toggleNumber.isSelected();
				repaint();
			}
		});
		
		popup.add(toggleNumber);
		
		this.add(popup);
	}

	public Graph(IGraphColors colors) {
		this();
		this.colors = colors;		
	}

	@Override
	public void paintComponent(Graphics g) {	
		// draw inner color
		g.setColor(colors.getInnerFillColor());
		g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

		// draw background rectangles
		g.setColor(colors.getBorderColor());
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

		int max = 0;

		// sort countries
		Collections.sort(entries, new Comparator<GraphEntry>() {
			public int compare(GraphEntry country, GraphEntry country1) {
				return country.getNumber() - country1.getNumber();
			}
		});

		// get highest
		for (int i = 0; i < entries.size(); i++) {
			GraphEntry country = entries.get(i);

			if (country.getNumber() > max) {
				max = country.getNumber();
			}
		}
		
		

		// draw lines and flags
		int pos = 0;
		
		for (int i = entries.size() - 1; i >= 0 && i < entries.size(); i--) {
			GraphEntry entry = entries.get(i);

			int value = (int) (((float) entry.getNumber() / (float) max) * this.getHeight()) - 20;

			int x = 15 + pos++ * 20;		
			
			if (value < 1) {
				value = 1;
			}
			
			g.setColor(getMainColor(entry.getIcon()));
			g.fillRect(x, this.getHeight() - value - 10, 10, this.getHeight());

			if (drawBackgroundSquare) {
				g.setColor(Color.white);
				g.fillRect(x - 3, this.getHeight() - value - 14, 16, 16);
			}
			g.drawImage(entry.getIcon().getImage(), x - 3, this.getHeight() - value - 14, entry.getIcon().getIconWidth(), entry.getIcon().getIconHeight(), null);

			if (drawNumber) {
				AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(-90));
				Font f = new Font("Arial", Font.BOLD, 12);
				g.setFont(value < 20 ? f : f.deriveFont(at));
					
				
				g.setColor(entry.getNumberColor() == null ? colors.getTextColor() : entry.getNumberColor());
				g.drawString(entry.getNumber() + "", value < 20 ? x + 2 : x + 10, value < 20 ? this.getHeight() - value - 17: this.getHeight() - value + 25);
			}
		}

		onUpdate(entries, 15 + entries.size() * 20);
	}

	/**
	 * Called when this component updates
	 * @param sortedList sorted country list
	 * @param recommendedX recommended width
	 */
	public void onUpdate(List<GraphEntry> sortedList, int recommendedX) {

	}
	
	/**
		When component is active
	**/
	public void setActive(boolean b) {
		isActive = b;
	}
	
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Adds a country to the list
	 * @param country
	 */
	public void add(GraphEntry country) {
		for (int i = 0; i < entries.size(); i++) {
			GraphEntry old = entries.get(i);
			if (old.equals(country)) {
				old.setNumber(country.getNumber());
				return;
			}
		}
		
		entries.add(country);
	}

	public void remove(GraphEntry country) {
		entries.remove(country);
	}

	public boolean isDrawNumber() {
		return drawNumber;
	}

	public void setDrawNumber(boolean drawNumber) {
		this.drawNumber = drawNumber;
	}

	public boolean isDrawBackgroundSquare() {
		return drawBackgroundSquare;
	}

	public void setDrawBackgroundSquare(boolean drawBackgroundSquare) {
		this.drawBackgroundSquare = drawBackgroundSquare;
	}

	public void clear() {
		entries.clear();
	}
	
	public IGraphColors getColors() {
		return colors;
	}

	public void setColors(IGraphColors colors) {
		this.colors = colors;
	}

	public static Color getMainColor(ImageIcon icon) {
		BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

		image.createGraphics();
		image.getGraphics().drawImage(icon.getImage(), 0, 0, null);
		int clr = image.getRGB(image.getWidth() / 2, image.getHeight() / 2);

		image.getGraphics().dispose();

		return new Color(clr);
	}

}