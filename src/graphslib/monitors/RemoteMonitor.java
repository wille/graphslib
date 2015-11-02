package graphslib.monitors;

public class RemoteMonitor {
	
	private String label;
	private int x;
	private int y;
	private int width;
	private int height;
	
	public RemoteMonitor(String label, int x, int y, int width, int height) {
		this.label = label;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public static RemoteMonitor convert(Monitor monitor) {
		return new RemoteMonitor(monitor.getDevice().getIDstring(), monitor.getScreenBounds().x, monitor.getScreenBounds().y, monitor.getScreenBounds().width, monitor.getScreenBounds().height);
	}

}
