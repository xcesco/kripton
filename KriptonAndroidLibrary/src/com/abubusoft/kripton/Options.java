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
	 * @return
	 */
	public static Options build()
	{
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
	 * reader/writer need only one instance, thus they are used in single thread.
	 */
	private boolean singleThread;

	/**
	 * Constructor,
	 * 
	 * encoding defaults to utf-8,
	 * indent defaults to true.
	 * 
	 */
	public Options() {
		this(false, false, DEFAULT_ENCODING);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param indent indicates if serialized xml should be indented or not.
	 * @param singleThread 
	 * @param encoding xml or json encoding.
	 */
	public Options(boolean indent, boolean singleThread, String encoding) {
		this.encoding = encoding;
		if (encoding == null) {
			this.encoding = DEFAULT_ENCODING;
		}
		this.singleThread=singleThread;
		this.indent = indent;
	}
	
	/**
	 * Indicates if serialized xml should be indented or not.
	 * 
	 * @return
	 */
	public Options encoding(String value) {
		this.encoding=value;
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
		this.indent=value;
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
	
	public boolean isSingleThread() {
		return singleThread;
	}
	
	/**
	 * reader/writer need only one instance, thus they are used in single thread.
	 * 
	 * @return
	 */
	public Options singleThread(boolean value) {
		this.singleThread=value;
		return this;
	}

}
