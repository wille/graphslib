package com.redpois0n.graphs.smooth;

public class ValuePair {
	
	private long used;
	private long available;

	public ValuePair(long used, long available) {
		this.used = used;
		this.available = available;
	}

	public long getAvailable() {
		return available;
	}

	public long getUsed() {
		return used;
	}

}
