package graphslib.monitors;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;

public class Monitor {
	
	private static Monitor[] monitors;
	
	static {
		try {
			updateMonitors();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private GraphicsDevice device;
	private Robot robot;
	private Rectangle screenBounds;
	
	public Monitor(GraphicsDevice device) throws Exception {
		this.device = device;
		this.robot = new Robot(device);
		this.screenBounds = device.getDefaultConfiguration().getBounds();
	}

	public GraphicsDevice getDevice() {
		return device;
	}

	public void setDevice(GraphicsDevice device) {
		this.device = device;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Rectangle getScreenBounds() {
		return screenBounds;
	}

	public void setScreenBounds(Rectangle screenBounds) {
		this.screenBounds = screenBounds;
	}
	
	public static Monitor[] getLocalMonitors() throws Exception {
		if (monitors == null) {
			updateMonitors();
		}
		
		return monitors;
	}
	
	public static void updateMonitors() throws Exception {
		GraphicsDevice[] graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		
		monitors = new Monitor[graphicsDevices.length];
		
		for (int i = 0; i < graphicsDevices.length; i++) {
			GraphicsDevice graphicsDevice = graphicsDevices[i];
			monitors[i] = new Monitor(graphicsDevice);
		}
	}
	
	public static Location getHighestLocations() {
		int topX = 0;
		int topY = 0;
		
		for (Monitor monitor : monitors) {
			if (monitor.getScreenBounds().x > topX) {
				topX = monitor.getScreenBounds().x;
			}
			
			if (monitor.getScreenBounds().y > topY) {
				topX = monitor.getScreenBounds().y;
			}
		}
		
		return new Location(topX, topY);
	}
	
	public static Location getLowestLocations() {
		int topX = 0;
		int topY = 0;
		
		for (Monitor monitor : monitors) {
			if (monitor.getScreenBounds().x < topX) {
				topX = monitor.getScreenBounds().x;
			}
			
			if (monitor.getScreenBounds().y < topY) {
				topX = monitor.getScreenBounds().y;
			}
		}
		
		return new Location(topX, topY);
	}
	
	public static RemoteMonitor[] convertAll() {
		RemoteMonitor[] monitor = new RemoteMonitor[monitors.length];
		
		for (int i = 0; i < monitors.length; i++) {
			monitor[i] = RemoteMonitor.convert(monitors[i]);
		}
		
		return monitor;
	}
	
	public static Monitor getMonitorFromId(String id) {
		for (Monitor monitor : monitors) {
			if (monitor.getDevice().getIDstring().equals(id)) {
				return monitor;
			}
		}
		
		return null;
	}

}
