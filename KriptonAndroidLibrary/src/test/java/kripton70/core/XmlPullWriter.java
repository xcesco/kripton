/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton70.core;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map.Entry;

import com.abubusoft.kripton.BinderOptions;

/**
 * BinderWriter implementation using kxml pull parser.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class XmlPullWriter {

	protected BinderOptions options;

	public XmlPullWriter() {
		this(BinderOptions.build());
	}

	public XmlPullWriter(BinderOptions options) {
		this.options = options;
	}
	

	public void write(Object source, Writer out) {
			// if nothing to persist, exist now!
			if (source==null) return;
			// entry validation
			validate(source, out);

			XmlSerializer serializer = new XmlSerializerImpl();
			if (options.isIndent()) {
				serializer.setProperty(XmlSerializerImpl.PROPERTY_SERIALIZER_INDENTATION, "    ");
				serializer.setProperty(XmlSerializerImpl.PROPERTY_SERIALIZER_LINE_SEPARATOR, "\n");
			} else {
				serializer.setProperty(XmlSerializerImpl.PROPERTY_SERIALIZER_INDENTATION, "");
				serializer.setProperty(XmlSerializerImpl.PROPERTY_SERIALIZER_LINE_SEPARATOR, "");
			}

			// use ' or " to delimit string
			serializer.setFeature(XmlSerializerImpl.FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE, options.isUseApostrophe());
/*
			serializer.setOutput(out);
			serializer.startDocument(options.getEncoding(), null);

			MappingSchema ms = MappingSchema.fromObject(source);
			TypeElementSchema res = ms.getRootElementSchema();
			String namespace = res.xmlInfo.getNamespace();
			String xmlName = res.xmlInfo.getName();

			if (!StringUtil.isEmpty(namespace)) { // bind to default namespace
				serializer.setPrefix("", namespace);
			}

			serializer.startTag(namespace, xmlName);
			this.writeObject(serializer, source, namespace);
			serializer.endTag(namespace, xmlName);

			serializer.endDocument();*/
		
	}

	protected void validate(Object source, Writer out) {
//		if (source == null) {
//			throw new IllegalArgumentException("Can not write null instance!");
//		}
//
//		if (out == null) {
//			throw new IllegalArgumentException("Writer is null!");
//		}
//
//		if (Transformer.isPrimitive(source.getClass())) {
//			throw new IllegalArgumentException("Can not write primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
//		}
	}

	public void write(Object source, OutputStream os)  {
//		try {
//			this.write(source, new OutputStreamWriter(os, options.getEncoding()));
//		} catch (UnsupportedEncodingException e) {
//			throw new WriterException("Error to write/serialize object", e);
//		}
	}

	protected void writeObject(XmlSerializer serializer, Object source, String namespace) throws Exception {
//		MappingSchema ms = MappingSchema.fromObject(source);
//
//		// write xml attributes first
//		writeAttributes(serializer, source, ms);
//
//		// write xml value if any
//		writeValue(serializer, source, ms);
//
//		// write xml elements
//		writeElements(serializer, source, ms, namespace);

	}

	/**
	 * Write attributes
	 * 
	 * @param serializer
	 * @param source
	 * @param ms
	 * @throws Exception
	 */
	private void writeAttributes(XmlSerializer serializer, Object source) throws Exception {
		//
		//serializer.attribute(null, as.getName(), attValue);
	}

	private void writeValue(XmlSerializer serializer, Object source) throws Exception {
		//serializer.cdsect(text);
		//serializer.text(text);
	}

	private void writeElements(XmlSerializer serializer, Object source, String namespace) throws Exception {
		//this.writeElementList(serializer, value, es, namespace);
		//this.writeElementSet(serializer, value, es, namespace);
		//this.writeElementMap(serializer, value, es, namespace);
		//this.writeElementArray(serializer, value, es, namespace);
		//this.writeElement(serializer, value, es, namespace);
	}

	private void writeElementMap(XmlSerializer serializer, Object source, String namespace) throws Exception {
//		if (es.hasWrapperName()) {
//			serializer.startTag(namespace, es.getWrapperName());
//		}
//
//		for (Entry<?, ?> value : ((Map<?, ?>) source).entrySet()) {
//			writeElementMapElement(serializer, value, es, namespace);
//		}
//
//		if (es.hasWrapperName()) {
//			serializer.endTag(namespace, es.getWrapperName());
//		}

	}

	private void writeElementSet(XmlSerializer serializer, Object source, String namespace) throws Exception {
//		if (es.hasWrapperName()) {
//			serializer.startTag(namespace, es.getWrapperName());
//		}
//
//		for (Object value : (Set<?>) source) {
//			this.writeElement(serializer, value, es, namespace);
//		}
//
//		if (es.hasWrapperName()) {
//			serializer.endTag(namespace, es.getWrapperName());
//		}
	}

	private void writeElementList(XmlSerializer serializer, Object source, String namespace) throws Exception {
//		if (es.hasWrapperName()) {
//			serializer.startTag(namespace, es.getWrapperName());
//		}
//
//		for (Object value : (List<?>) source) {
//			this.writeElement(serializer, value, es, namespace);
//		}
//
//		if (es.hasWrapperName()) {
//			serializer.endTag(namespace, es.getWrapperName());
//		}
	}

	private void writeElementArray(XmlSerializer serializer, Object source, String namespace) throws Exception {
//		if (es.hasWrapperName()) {
//			serializer.startTag(namespace, es.getWrapperName());
//		}
//
//		int n = Array.getLength(source);
//		Object value;
//
//		for (int i = 0; i < n; i++) {
//			value = Array.get(source, i);
//			this.writeElement(serializer, value, es, namespace);
//		}
//
//		if (es.hasWrapperName()) {
//			serializer.endTag(namespace, es.getWrapperName());
//		}
	}

	private void writeElementMapElement(XmlSerializer serializer, Entry<?, ?> source, String namespace) throws Exception {
//		MapInfo type = es.getMapInfo();
//		if (source == null)
//			return; // do nothing
//
//		String xmlName = es.getName();
//
//		serializer.startTag(namespace, xmlName);
//
//		String keyName = es.getMapInfo().keyName;
//		String valueName = es.getMapInfo().valueName;
//
//		switch (es.getMapInfo().entryStrategy) {
//		case ELEMENTS:
//			// key
//			if (Transformer.isPrimitive(type.keyClazz)) {
//				String value = Transformer.write(source.getKey(), type.keyClazz);
//
//				serializer.startTag(namespace, keyName);
//
//				switch (es.getXmlInfo().type) {
//				case TAG:
//				case VALUE:
//					serializer.text(value);
//					break;
//				case VALUE_CDATA:
//					serializer.cdsect(value);
//					break;
//				default:
//					throw new MappingException(es.getXmlInfo().type + " is not supported for xml rapresentation of " + es.getName());
//				}
//
//				serializer.endTag(namespace, keyName);
//			} else {
//				// object
//				serializer.startTag(namespace, keyName);
//				this.writeObject(serializer, source.getKey(), namespace);
//				serializer.endTag(namespace, keyName);
//			}
//
//			// value 
//			if (Transformer.isPrimitive(type.valueClazz)) {
//				String value = Transformer.write(source.getValue(), type.valueClazz);
//				if (!StringUtil.isEmpty(value)) {
//					serializer.startTag(namespace, valueName);
//					switch (es.getXmlInfo().type) {
//					case TAG:
//					case VALUE:
//						serializer.text(value);
//						break;
//					case VALUE_CDATA:
//						serializer.cdsect(value);
//						break;
//					default:
//						throw new MappingException(es.getXmlInfo().type + " is not supported for xml rapresentation of " + es.getName());
//					}
//					serializer.endTag(namespace, valueName);
//				}
//			} else {
//				// object				
//				if (source.getValue() != null) {
//					serializer.startTag(namespace, valueName);
//					this.writeObject(serializer, source.getValue(), namespace);
//					serializer.endTag(namespace, valueName);
//				}
//				
//			}
//			break;
//		case ATTRIBUTES:
//		// key
//		{
//			String value = Transformer.write(source.getKey(), type.keyClazz);
//			serializer.attribute(namespace, keyName, value);
//		}
//
//			// value
//			{
//				String value = Transformer.write(source.getValue(), type.valueClazz);
//				if (!StringUtil.isEmpty(value)) {
//					serializer.attribute(namespace, valueName, value);
//				}
//			}
//			break;
//		}
//
//		serializer.endTag(namespace, xmlName);

	}

	private void writeElement(XmlSerializer serializer, Object source, String namespace) throws Exception {
//		Class<?> type = es.getFieldType();
//
//		if (source == null)
//			return; // do nothing
//
//		String xmlName = es.getName();
//		
//		if (!es.getXmlInfo().enabled) return;
//
//		// primitives
//		if (Transformer.isPrimitive(type)) {
//			String value = Transformer.write(source, type);
//			if (StringUtil.isEmpty(value))
//				return;
//
//			serializer.startTag(namespace, xmlName);
//			switch (es.getXmlInfo().type) {
//			case VALUE_CDATA:
//				serializer.cdsect(value);
//				break;
//			default:
//				serializer.text(value);
//				break;
//			}
//			serializer.endTag(namespace, xmlName);
//
//			return;
//		} else {
//			// object
//			serializer.startTag(namespace, xmlName);
//			this.writeObject(serializer, source, namespace);
//			serializer.endTag(namespace, xmlName);
//		}

	}

	public String write(Object source) {
//		StringWriter out = new StringWriter();
//		this.write(source, out);
//		return out.toString();
		return null;
	}

}
