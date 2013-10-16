package com.redpois0n.graphs.country;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Country {
	
	public static final Map<String, ImageIcon> FLAGS_CACHE = new HashMap<String, ImageIcon>();
	
	public static final String PATH = "/flags/";
	
	private final ImageIcon flag;
	
	private final String iso2;
	
	private int number;
	
	public Country(String iso2, int number) throws Exception {
		this.iso2 = iso2;
		this.flag = loadFlag(iso2);
		this.number = number;
	}
	
	public String getIso() {
		return iso2;
	}
	
	public ImageIcon getFlag() {
		return flag;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public static ImageIcon loadFlag(String flag) throws Exception {
		ImageIcon icon;
		
		if (FLAGS_CACHE.containsKey(flag.toLowerCase())) {
			icon = FLAGS_CACHE.get(flag.toLowerCase());
		} else {
			icon = new ImageIcon(Country.class.getResource(PATH + flag.toLowerCase() + ".png"));
			FLAGS_CACHE.put(flag.toLowerCase(), icon);
		}
				
		return icon;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Country) {
			return ((Country)o).iso2.equals(this.iso2);
		} else {
			return false;
		}
	}
}
