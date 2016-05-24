package com.abubusoft.kripton.processor.utils;

public abstract class StringUtility {

	public static boolean hasText(String value) {
		if (value==null || value.trim().length()==0) return false;
		return true;
	}
}
