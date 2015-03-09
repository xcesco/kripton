package com.abubusoft.kripton;

/**
 * XML/JSON format like encoding and indent.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class Options {

	private static final String DEFAULT_ENCODING = "utf-8";

	/**
	 * Builder for format class
	 * 
	 * @return
	 */
	public static Options build() {
		return new Options();
	}

	/**
	 * Encoding for the xml
	 */
	private String encoding;

	/**
	 * Indicates if serialized xml should be indented or not
	 */
	private boolean indent;

	/**
	 * Constructor,
	 * 
	 * encoding defaults to utf-8, indent defaults to false.
	 * 
	 */
	public Options() {
		encoding = DEFAULT_ENCODING;
		indent = false;
	}

	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public Options encoding(String value) {
		this.encoding = value;
		return this;
	}

	/**
	 * Get the xml encoding setting
	 * 
	 * @return xml encoding setting
	 */
	public String getEncoding() {
		return this.encoding;
	}

	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public Options indent(boolean value) {
		this.indent = value;
		return this;
	}

	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public boolean isIndent() {
		return this.indent;
	}

}
