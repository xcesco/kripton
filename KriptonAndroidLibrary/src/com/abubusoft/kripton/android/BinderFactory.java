package com.abubusoft.kripton.android;

import com.abubusoft.kripton.android.json.JsonReader;
import com.abubusoft.kripton.android.json.JsonWriter;
import com.abubusoft.kripton.android.xml.XmlDOMReader;
import com.abubusoft.kripton.android.xml.XmlPullWriter;
import com.abubusoft.kripton.android.xml.XmlSAXReader;
import com.abubusoft.kripton.binder.BinderReader;
import com.abubusoft.kripton.binder.BinderWriter;
import com.abubusoft.kripton.binder.Format;

/**
 * Factory class to get BinderReader/BinderWriter instance,
 * main entry of the Nano framework.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class BinderFactory {
	
	/**
	 * Reader type setting, current supports DOM(BinderDefault) and SAX.
	 */
	public static ReaderType readerType = ReaderType.DOM;
	
	/**
	 * Get BinderReader instance with default format(encoding is utf-8),
	 * the BinderReader instance can be used to read XML into Java POJO.
	 * 
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getXMLReader() {
		if (readerType == ReaderType.SAX) {
			return new XmlSAXReader();
		} else {
			return new XmlDOMReader();
		}
	}
	
	/**
     * Get BinderReader instance with specific format,
	 * the BinderReader instance can be used to read XML into Java POJO.
	 * 
	 * @param format info about encoding and indent
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getXMLReader(Format format) {
		if (readerType == ReaderType.SAX) {
			return new XmlSAXReader(format);
		} else {
			return new XmlDOMReader(format);
		}
	}
	
	/**
	 * Get BinderReader instance with default format(encoding is utf-8),
	 * the BinderReader instance cna be used to read JSON into POJO.
	 * 
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getJSONReader() {
		return new JsonReader();
	}
	
	/**
	 * Get BinderReader instance with specific format,
	 * the BinderReader instance can be used to read JSON into POJO.
	 * 
	 * @param format info about encoding
	 * @return an instance of BinderReader implementation.
	 */
	public static BinderReader getJSONReader(Format format) {
		return new JsonReader(format);
	}
	
	/**
	 * Get BinderWriter instance with default format(encoding is utf-8, indent is true),
	 * the BinderWriter instance can be used to write Java POJO into XML.
	 * 
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getXMLWriter() {
		return new XmlPullWriter();
	}
	
	/**
	 * Get BinderWriter instance with default specific format,
	 * the BinderWriter instance can be used to write Java POJO into XML.
	 * 
	 * @param format info about encoding and indent
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getXMLWriter(Format format) {
		return new XmlPullWriter(format);
	}
	
	/**
	 * Get BinderWriter instance with default format(encoding is utf-8),
	 * the BinderWriter instance can be used to write Java POJO into JSON.
	 * 
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getJSONWriter() {
		return new JsonWriter();
	}

	/**
	 * Get BinderWriter instance with specific format,
	 * the BinderWriter instance can be used to write Java POJO into JSON.
	 * 
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getJSONWriter(Format format) {
		return new JsonWriter(format);
	}
	
	public enum ReaderType {
		SAX,
		DOM;
	}
}
