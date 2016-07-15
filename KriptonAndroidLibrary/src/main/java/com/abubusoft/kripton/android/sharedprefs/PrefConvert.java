package com.abubusoft.kripton.android.sharedprefs;

public interface PrefConvert<T> {

	T readPref(Object value);
	
	void writePref(T value);
}
