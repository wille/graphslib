package com.redpois0n.graphs.taskmgr;

import java.awt.Color;

public class TaskmgrColors implements ITaskmgrColors {

	@Override
	public Color getInnerFillColor() {
		return Color.black;
	}

	@Override
	public Color getBorderColor() {
		return Color.white;
	}

	@Override
	public Color getNetColor() {
		return new Color(0x008040);
	}

	@Override
	public Color getCurveColor() {
		return new Color(0x00FF00);
	}
	
	public Color getGreenMeterColor() {
		return new Color(0x008000);
	}
	
}
