package com.abubusoft.kripton.binder.xml;

/**
 * Specify type of xml binding.
 * 
 * @author xcesco
 *
 */
public enum XmlType { 
	/**
	 * by an attribute
	 */
	ATTRIBUTE,
	/**
	 * by a tag 
	 */
	TAG,
	/**
	 * in the value section
	 */
	VALUE,
	/**
	 * in the value section with CDATA 
	 */
	VALUE_CDATA
}
