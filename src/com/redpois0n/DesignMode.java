package com.redpois0n;

import java.io.PrintWriter;
import java.io.StringWriter;

public class DesignMode {

	public static final boolean DESIGNERMODE;
	
	static {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		new RuntimeException().printStackTrace(pw);
		String s = sw.toString();
		DESIGNERMODE = s.contains("com.instantiations.designer");
	}


}
