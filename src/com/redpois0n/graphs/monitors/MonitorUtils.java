package com.redpois0n.graphs.monitors;


public class MonitorUtils {
	
	public static Location getHighestLocations(RemoteMonitor[] monitors) {
		int topX = 0;
		int topY = 0;
		
		for (RemoteMonitor monitor : monitors) {
			if (monitor.getX() > topX) {
				topX = monitor.getX();
			}
			
			if (monitor.getY() > topY) {
				topY = monitor.getY();
			}
		}
		
		return new Location(topX, topY);
	}
	
	public static Location getLowestLocations(RemoteMonitor[] monitors) {
		int topX = 0;
		int topY = 0;
		
		for (RemoteMonitor monitor : monitors) {
			if (monitor.getX() < topX) {
				topX = monitor.getX();
			}
			
			if (monitor.getY() < topY) {
				topY = monitor.getY();
			}
		}
		
		return new Location(topX, topY);
	}

	public static RemoteMonitor getMonitorAt(RemoteMonitor[] monitors, int x, int y) {
		for (RemoteMonitor monitor : monitors) {
			if (x >= monitor.getX() && y >= monitor.getY() && x <= monitor.getX() + monitor.getWidth() && y <= monitor.getY() + monitor.getHeight()) {
				return monitor;
			}
		}
		
		return null;
	}
}
