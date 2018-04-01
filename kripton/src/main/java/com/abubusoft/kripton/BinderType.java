package com.abubusoft.kripton;

/**
 * Represents supported data format by Kripton. Note that
 * 
 * @author xcesco
 *
 */
public enum BinderType {
	XML(false, false), JSON(false, false), CBOR(false, true), YAML(false, false), PROPERTIES(true, false), SMILE(false, true);

	private BinderType(boolean value, boolean onlyBinaryValue) {
		onlyText = value;
		onlyBinary = onlyBinaryValue;
	}

	public final boolean onlyText;

	public final boolean onlyBinary;
}
