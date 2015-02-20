package com.redpois0n.graphs.smooth;

import java.awt.Color;

public class SmoothColors implements ISmoothColors {

	@Override
	public Color getInnerFillColor() {
		return Color.white;
	}

	@Override
	public Color getBorderColor() {
		return Color.gray;
	}

	@Override
	public Color getFreeColor() {
		return new Color(0xB8CADA);
	}

	@Override
	public Color getUsedColor() {
		return new Color(0x75A9D6);
	}
	
	@Override
	public Color getGridColor() {
		return Color.gray;
	}
	
}
