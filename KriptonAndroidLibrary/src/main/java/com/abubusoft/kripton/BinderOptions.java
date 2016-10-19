package com.abubusoft.kripton;

/**
 * XML/JSON format like encoding and indent.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class BinderOptions {

	public static final String DEFAULT_ENCODING = "utf-8";

	public static final String ENCODING_UTF_8 = DEFAULT_ENCODING;

	/**
	 * Builder for format class
	 * 
	 * @return options
	 */
	public static BinderOptions build() {
		return new BinderOptions();
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
	 * if true use apostrophe to delimit strings. Used only for xml format.
	 */
	private boolean useApostrophe;

	/**
	 * 
	 * @return if true use apostrophe to delimit strings. Used only for xml format.
	 */
	public boolean isUseApostrophe() {
		return useApostrophe;
	}

	/**
	 * if true use apostrophe to delimit strings. Used only for xml format.
	 */
	public BinderOptions useApostrophe(boolean useApostrophe) {
		this.useApostrophe = useApostrophe;

		return this;
	}

	/**
	 * Constructor,
	 * 
	 * encoding defaults to utf-8, indent defaults to false.
	 * 
	 */
	public BinderOptions() {
		encoding = DEFAULT_ENCODING;
		indent = false;
	}

	/**
	 * 
	 * 
	 * @return Get the file encoding setting.
	 */
	public BinderOptions encoding(String value) {
		this.encoding = value;
		return this;
	}

	/**
	 * Get the file encoding setting
	 * 
	 * @return file encoding setting
	 */
	public String getEncoding() {
		return this.encoding;
	}

	/**
	 * 
	 * 
	 * @return Indicates if serialized xml should be indented or not.
	 */
	public BinderOptions indent(boolean value) {
		this.indent = value;
		return this;
	}

	/**
	 * 
	 * 
	 * @return Indicates if serialized xml should be indented or not.
	 */
	public boolean isIndent() {
		return this.indent;
	}

}
