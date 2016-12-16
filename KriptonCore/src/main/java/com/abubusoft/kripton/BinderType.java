package com.abubusoft.kripton;

public enum BinderType {
	XML(false), JSON(false), CBOR(false), YAML(false), PROPERTIES(true);

	private BinderType(boolean value) {
		onlyText = value;
	}

	public final boolean onlyText;
}
