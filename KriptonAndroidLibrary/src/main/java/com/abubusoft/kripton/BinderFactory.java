package com.abubusoft.kripton;

import com.abubusoft.kripton.binder.json.JsonReader;
import com.abubusoft.kripton.binder.json.JsonWriter;
import com.abubusoft.kripton.binder.xml.XmlDOMReader;
import com.abubusoft.kripton.binder.xml.XmlPullWriter;
import com.abubusoft.kripton.binder.xml.XmlSAXReader;

/**
 * Factory class to get BinderReader/BinderWriter instance, main entry of the Nano framework.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class BinderFactory {

	/**
	 * Get BinderReader instance with default format(encoding is utf-8), the BinderReader instance can be used to read XML into Java POJO.
	 * 
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getXMLReader(XmlReaderType type) {
		if (type == XmlReaderType.SAX) {
			return new XmlSAXReader();
		} else {
			return new XmlDOMReader();
		}
	}

	/**
	 * Get BinderReader instance with default format(encoding is utf-8), the BinderReader instance can be used to read XML into Java POJO.
	 * 
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getXMLReader() {
		return getXMLReader(XmlReaderType.SAX);
	}

	/**
	 * Get BinderReader instance with specific format, the BinderReader instance can be used to read XML into Java POJO.
	 * 
	 * @param format
	 *            info about encoding and indent
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getXMLReader(XmlReaderType type, BinderOptions format) {
		if (type == XmlReaderType.SAX) {
			return new XmlSAXReader(format);
		} else {
			return new XmlDOMReader(format);
		}
	}

	/**
	 * Get BinderReader instance with default format(encoding is utf-8), the BinderReader instance cna be used to read JSON into POJO.
	 * 
	 * @return an instance of BinderReader implementation
	 */
	public static BinderReader getJSONReader() {
		return new JsonReader();
	}

	/**
	 * Get BinderReader instance with specific format, the BinderReader instance can be used to read JSON into POJO.
	 * 
	 * @param format
	 *            info about encoding
	 * @return an instance of BinderReader implementation.
	 */
	public static BinderReader getJSONReader(BinderOptions format) {
		return new JsonReader(format);
	}

	/**
	 * Get BinderWriter instance with default format(encoding is utf-8, indent is true), the BinderWriter instance can be used to write Java POJO into XML.
	 * 
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getXMLWriter() {
		return new XmlPullWriter();
	}

	/**
	 * Get BinderWriter instance with default specific format, the BinderWriter instance can be used to write Java POJO into XML.
	 * 
	 * @param options
	 *            info about encoding and indent
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getXMLWriter(BinderOptions options) {
		return new XmlPullWriter(options);
	}

	/**
	 * Get BinderWriter instance with default format(encoding is utf-8), the BinderWriter instance can be used to write Java POJO into JSON.
	 * 
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getJSONWriter() {
		return new JsonWriter();
	}

	/**
	 * Get BinderWriter instance with specific format, the BinderWriter instance can be used to write Java POJO into JSON.
	 * 
	 * @return an instance of BinderWriter implementation
	 */
	public static BinderWriter getJSONWriter(BinderOptions options) {
		return new JsonWriter(options);
	}

	public enum XmlReaderType {
		SAX, DOM;
	}
}
