package com.redpois0n.graphs.smooth;

public class ValuePair {
	
	private int used;
	private int available;

	public ValuePair(int used, int available) {
		this.used = used;
		this.available = available;
	}

	public int getAvailable() {
		return available;
	}

	public int getUsed() {
		return used;
	}

}
