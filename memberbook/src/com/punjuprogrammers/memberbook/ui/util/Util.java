package com.punjuprogrammers.memberbook.ui.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static List<String> array2StringList(Object... ary) {
		List<String> list = new ArrayList<String>();
		for (Object obj : ary) {
			if (obj != null) {
				list.add(obj.toString());
			}
		}
		return list;
	}
}
