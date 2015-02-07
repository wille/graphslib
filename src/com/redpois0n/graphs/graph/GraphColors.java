package com.redpois0n.graphs.graph;

import java.awt.Color;

public class GraphColors implements IGraphColors {

	@Override
	public Color getInnerFillColor() {
		return Color.white;
	}

	@Override
	public Color getBorderColor() {
		return Color.gray;
	}

	@Override
	public Color getTextColor() {
		return Color.black;
	}
	
}
