package com.redpois0n.graphs.monitors;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconUtils {

	private static final Map<String, Icon> CACHE = new HashMap<String, Icon>();

	public static final Icon NO_ICON;

	static {
		NO_ICON = getIcon("404");
	}

	public static Icon getIcon(String path) {
		Icon icon = null;

		if (CACHE.containsKey(path)) {
			icon = CACHE.get(path);
		} else {
			try {
				icon = new ImageIcon(IconUtils.class.getResource("/icons/" + path + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
				icon = NO_ICON;
			}

			CACHE.put(path, icon);
		}

		return icon;
	}

}
