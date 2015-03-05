package com.abubusoft.kripton;

/**
 * XML/JSON format like encoding and indent.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class Format {
	
	private static final String DEFAULT_ENCODING = "utf-8";
	
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
	 * encoding defaults to utf-8,
	 * indent defaults to true.
	 * 
	 */
	public Format() {
		this(true);
	}
	
	/**
	 * Builder for format class
	 * @return
	 */
	public static Format build()
	{
		return new Format();
	}

	
	/**
	 * Constructor,
	 * 
	 * encoding defaults to utf-8.
	 * 
	 * @param indent indicates if serialized xml should be indented or not.
	 */
	public Format(boolean indent) {
		this(indent, DEFAULT_ENCODING);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param indent indicates if serialized xml should be indented or not.
	 * @param encoding xml or json encoding.
	 */
	public Format(boolean indent, String encoding) {
		this.encoding = encoding;
		if (encoding == null) {
			this.encoding = DEFAULT_ENCODING;
		}
		this.indent = indent;
	}
	
	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public boolean isIndent() {
		return this.indent;
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
	public Format indent(boolean value) {
		this.indent=value;
		return this;
	}
	
	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public Format encoding(String value) {
		this.encoding=value;
		return this;
	}

}
