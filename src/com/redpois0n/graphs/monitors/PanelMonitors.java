package com.redpois0n.graphs.monitors;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelMonitors extends JPanel {

	private RemoteMonitor[] monitors;
	private RemoteMonitor selected;
	private PanelMonitor selectedPanel;
	private List<MonitorListener> listeners = new ArrayList<MonitorListener>();
	private List<PanelMonitor> panels = new ArrayList<PanelMonitor>();
	private boolean draggable;
	
	public PanelMonitors(RemoteMonitor[] m, boolean draggable) {
		this.monitors = m;
		this.draggable = draggable;
		
		setLayout(null);

		reload();
	}
	
	public void shift(int shiftX, int shiftY) {	
		for (PanelMonitor panel : panels) {
			panel.setLocation(panel.getLocation().x + shiftX, panel.getLocation().y + shiftY);
		}
	}
	
	public void reload() {
		panels.clear();
		
		for (Component c : getComponents()) {
			remove(c);
		}
		
		int highestX = 0;
		int highestY = 0;
		
		Location lowest = MonitorUtils.getLowestLocations(monitors);
		Location highest = MonitorUtils.getHighestLocations(monitors);

		for (int i = 0; i < monitors.length; i++) {
			RemoteMonitor monitor = monitors[i];
			
			int x = monitor.getX() + Math.abs(lowest.x);
			int y = monitor.getY() + Math.abs(lowest.y);
			
			if (x + monitor.getWidth() > highestX) {
				highestX = x + monitor.getWidth();
			}
			
			if (y + monitor.getHeight() > highestY) {
				highestY = y + monitor.getHeight();
			}
		}	
		
		for (int i = 0; i < monitors.length; i++) {
			RemoteMonitor monitor = monitors[i];
			PanelMonitor panel = new PanelMonitor(monitor, i + 1);
			
			int x = monitor.getX() + Math.abs(lowest.x);
			int y = monitor.getY() + Math.abs(lowest.y);
			
			panel.setBounds(x / 20, y / 20, monitor.getWidth() / 20, monitor.getHeight() / 20);
			panel.setSize(monitor.getWidth() / 20, monitor.getHeight() / 20);
			
			add(panel);
			panels.add(panel);
		}
		
		System.out.println(highest.x + ", " + highest.y + " " + lowest.x + ", " + lowest.y);
		
		Dimension d = new Dimension(highest.x / 20 + 100, highest.y / 20 + 100);
		
		super.setPreferredSize(d);
		super.setSize(d);
	}

	public void addListener(MonitorListener listener) {
		listeners.add(listener);
	}

	public void removeListener(MonitorListener listener) {
		listeners.remove(listener);
	}
	
	@Override
	public void repaint() {
		super.repaint();
		
		if (panels != null) {
			for (PanelMonitor panel : panels) {
				panel.repaint();
			}
		}
	}
	
	public RemoteMonitor getSelectedMonitor() {
		return selected;
	}
	
	public PanelMonitor getPanel() {
		return selectedPanel;
	}

	public boolean isDraggable() {
		return draggable;
	}

	public List<PanelMonitor> getPanels() {
		return panels;
	}

	public class PanelMonitor extends JPanel {

		private int x;
		private int y;
		private int number;
		
		private RemoteMonitor monitor;
		
		public PanelMonitor(RemoteMonitor m, int number) {
			this.monitor = m;
			this.number = number;
			
			super.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent event) {
					x = event.getX();
					y = event.getY();
					selected = PanelMonitor.this.monitor;
					selectedPanel = PanelMonitor.this;
					PanelMonitors.this.repaint();
					PanelMonitors.this.remove(PanelMonitor.this);
					PanelMonitors.this.add(PanelMonitor.this, 0);
					
					for (MonitorListener listener : listeners) {
						listener.onMonitorChange(PanelMonitor.this.monitor);
					}

				}
			});
			
			if (draggable) {
				super.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent event) {
						event.translatePoint(event.getComponent().getLocation().x - x, event.getComponent().getLocation().y - y);
						
						int x = MathUtils.roundUp(event.getX(), 1D);
						int y = MathUtils.roundUp(event.getY(), 1D);
						
						PanelMonitor.this.setLocation(x, y);
						
						monitor.setX(x * 20);
						monitor.setY(y * 20);
					}
				});
			}
		}
		
		public RemoteMonitor getMonitor() {
			return monitor;
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Graphics2D g2 = (Graphics2D) g;
			
			ImageIcon icon;
			
			if (monitor.getX() == 0 && monitor.getY() == 0 && selected != null && selected.equals(monitor)) {
				icon = (ImageIcon) IconUtils.getIcon("monitor_main_selected");
			} else if (monitor.getX() == 0 && monitor.getY() == 0) {
				icon = (ImageIcon) IconUtils.getIcon("monitor_main");
			} else if (selected != null && selected.equals(monitor)) {
				icon = (ImageIcon) IconUtils.getIcon("monitor_selected");
			} else {
				icon = (ImageIcon) IconUtils.getIcon("monitor_normal");
			}
			
			g2.drawImage(icon.getImage(), 0, 0, super.getWidth(), super.getHeight(), null);
			
			g2.setColor(Color.white);
			Font f = new Font("Arial", Font.BOLD, 24);
			g2.setFont(f);
			g2.drawString(number + "", getWidth() / 2 - 6, getHeight() / 2 + 8);
		}
		
	}

}
