package com.abubusoft.kripton.processor.element;

import javax.lang.model.element.Name;

public class KriptonName implements Name {
	
	private String value;

	public KriptonName(String value) {
		this.value=value;
	}

	@Override
	public int length() {
		return value.length();
	}

	@Override
	public char charAt(int index) {
		return value.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return value.subSequence(start, end);
	}

	@Override
	public boolean contentEquals(CharSequence cs) {
		return value.equals(cs);
	}

}
