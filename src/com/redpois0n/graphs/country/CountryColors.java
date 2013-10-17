package com.redpois0n.graphs.country;

import java.awt.Color;

public class CountryColors implements ICountryColors {

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
