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
	public Color getDownloadColor() {
		return new Color(0x0000FF);
	}

	@Override
	public Color getUploadColor() {
		return new Color(0x00FF00);
	}
	
	@Override
	public Color getGridColor() {
		return Color.gray;
	}
	
}
