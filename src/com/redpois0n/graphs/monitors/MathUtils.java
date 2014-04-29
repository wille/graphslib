package com.redpois0n.graphs.monitors;

public class MathUtils {
	
	public static int roundUp(int num, double nearest) {
	    return (int) (Math.ceil(num / nearest) * (int) nearest);
	}

}
