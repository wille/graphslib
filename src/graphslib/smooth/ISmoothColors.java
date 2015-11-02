package graphslib.smooth;

import java.awt.Color;

public abstract interface ISmoothColors {
	
	public abstract Color getInnerFillColor();
	
	public abstract Color getBorderColor();
	
	public abstract Color getFreeColor();
	
	public abstract Color getUsedColor();
			
	public abstract Color getGridColor();
}
