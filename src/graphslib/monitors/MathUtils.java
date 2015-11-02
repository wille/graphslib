package graphslib.monitors;

public class MathUtils {
	
	public static int roundUp(int num, double nearest) {
	    return (int) (Math.ceil(num / nearest) * (int) nearest);
	}

}
