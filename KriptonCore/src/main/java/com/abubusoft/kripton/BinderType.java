package com.abubusoft.kripton;

public enum BinderType {
	XML(false, false), JSON(false, false), CBOR(false, true), YAML(false, false), PROPERTIES(true, false), SMILE(false, true);

	private BinderType(boolean value, boolean onlyBinaryValue) {
		onlyText = value;
		onlyBinary = onlyBinaryValue;
	}

	public final boolean onlyText;
	
	public final boolean onlyBinary;
}
