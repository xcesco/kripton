package com.abubusoft.kripton.binder.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.annotation.XmlType;
import com.abubusoft.kripton.binder.schema.AnyElementSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema.MapInfo;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.schema.TypeElementSchema;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.common.TypeReflector;

/**
 * BinderReader implementation using DOM xml parser.
 * 
 * @author bulldog
 * @author xcesco
 *
 */
public class XmlDOMReader implements BinderReader {

	protected Options format;

	protected static final ThreadLocal<DocumentBuilder> localBuilder = new ThreadLocal<DocumentBuilder>() {
		@Override
		protected DocumentBuilder initialValue() {
			try {

				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);

				return dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new RuntimeException("Failed to create DocumentBuilder!", e);
			}
		}
	};

	public XmlDOMReader() {
		this(new Options());
	}

	public XmlDOMReader(Options options) {
		this.format = options;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T read(Class<? extends T> type, InputStream source) throws ReaderException, MappingException {

		this.validate(type, source);

		try {
			DocumentBuilder db = localBuilder.get();
			Document doc = db.parse(source);

			Element rootElement = doc.getDocumentElement();
			MappingSchema ms = MappingSchema.fromClass(type);
			TypeElementSchema res = ms.getRootElementSchema();

			String xmlName = res.xmlInfo.getName();

			// simple validation only for root element
			if (!xmlName.equalsIgnoreCase(rootElement.getLocalName())) {
				throw new ReaderException("Root element name mismatch, " + rootElement.getLocalName() + " != " + xmlName);
			}

			Object obj = this.buildObjectFromType(type);

			this.read(obj, rootElement);

			return (T) obj;
		} catch (MappingException me) {
			throw me;
		} catch (Exception e) {
			throw new ReaderException("Error to read/descrialize object", e);
		}
	}

	@Override
	public <T> T read(Class<? extends T> type, String source) throws ReaderException, MappingException {
		try {
			return this.read(type, new ByteArrayInputStream(source.getBytes(format.getEncoding())));
		} catch (UnsupportedEncodingException e) {
			throw new ReaderException("Encoding is not supported", e);
		}
	}

	@Override
	public <T> T read(Class<? extends T> type, Reader source) throws ReaderException, MappingException {
		if (source == null) {
			throw new ReaderException("Reader is null!");
		}

		try {
			return this.read(type, StringUtil.reader2String(source));
		} catch (IOException e) {
			throw new ReaderException("IO error event converting reader to string!", e);
		}
	}

	protected <T> void validate(Class<? extends T> type, InputStream source) throws ReaderException {
		if (type == null) {
			throw new ReaderException("Can not read, type is null!");
		}

		if (source == null) {
			throw new ReaderException("InputStream is null!");
		}

		if (Transformer.isPrimitive(type)) {
			throw new ReaderException("Can not read primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
		}
	}

	protected void read(Object obj, Element element) throws Exception {
		this.readAttribute(obj, element);

		boolean hasText = this.readText(obj, element);
		if (hasText) {
			// no further read if xml text presents
			return;
		}

		List<Element> anyElements = new ArrayList<Element>();

		this.readElement(obj, element, anyElements);

		this.readAnyElement(obj, anyElements);
	}

	private void readAttribute(Object obj, Element element) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(obj);

		// read xml attributes
		Map<String, ElementSchema> asm = ms.getXml2AttributeSchemaMapping();
		if (!asm.isEmpty() && element.hasAttributes()) {
			for (String attrXmlName : asm.keySet()) {
				ElementSchema as = asm.get(attrXmlName);
				String attrValue = element.getAttributeNS(null, attrXmlName);
				if (!StringUtil.isEmpty(attrValue)) {
					Field field = as.getField();
					Object filedValue = Transformer.read(attrValue, as.getFieldType());
					if (filedValue != null) {
						field.set(obj, filedValue);
					}
				}
			}
		}
	}

	private boolean readText(Object obj, Element element) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(obj);

		// read xml value if any
		ElementSchema vs = ms.getValueSchema();
		if (vs != null) {
			Field field = vs.getField();
			String text = element.getTextContent();
			if (!StringUtil.isEmpty(text)) {
				Object fieldValue = Transformer.read(text, vs.getFieldType());
				if (fieldValue != null) {
					field.set(obj, fieldValue);
				}
			}

			return true;
		} else {
			return false;
		}
	}

	private void readElement(Object obj, Element element, List<Element> anyChildElements) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(obj);

		// read xml element
		Map<String, ElementSchema> xml2SchemaMapping = ms.getXml2SchemaMapping();
		Map<String, ElementSchema> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
		NodeList nodeList = element.getChildNodes();

		int n = nodeList.getLength();
		int index = 0;

		if (n > 0) {

			for (int i = 0; i < n; i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) node;
					String localName = node.getLocalName();

					if (xmlWrapper2SchemaMapping.containsKey(localName)) {
						// ASSERT: stiamo su un nodo wrapper. Che ignoriamo
						readElement(obj, childElement, anyChildElements);
						continue;
					}

					ElementSchema schemaObj = xml2SchemaMapping.get(localName);

					if (schemaObj.getXmlInfo().type==XmlType.TAG) {
						// found match element
						ElementSchema es = (ElementSchema) schemaObj;
						Field field = es.getField();
						Class<?> fieldType = es.getFieldType();

						switch (es.getType()) {
						case LIST:
							@SuppressWarnings("unchecked")
							List<Object> list = (List<Object>) field.get(obj);
							if (list == null) {
								list = new ArrayList<Object>();
								field.set(obj, list);
							}

							Class<?> parameterizedType = es.getFieldType();

							// primitive
							if (Transformer.isPrimitive(parameterizedType)) {
								String xmlValue = childElement.getTextContent();
								if (!StringUtil.isEmpty(xmlValue)) {
									Object fieldValue = Transformer.read(xmlValue, parameterizedType);
									if (fieldValue != null) {
										list.add(fieldValue);
									}
								}
							} else {
								Object newObj = this.buildObjectFromType(parameterizedType);
								this.read(newObj, childElement);
								list.add(newObj);
							}
							break;
						case SET:
							@SuppressWarnings("unchecked")
							Set<Object> set = (Set<Object>) field.get(obj);
							if (set == null) {
								set = new LinkedHashSet<Object>();
								field.set(obj, set);
							}

							Class<?> setParameterizedType = es.getFieldType();

							// primitive
							if (Transformer.isPrimitive(setParameterizedType)) {
								String xmlValue = childElement.getTextContent();
								if (!StringUtil.isEmpty(xmlValue)) {
									Object fieldValue = Transformer.read(xmlValue, setParameterizedType);
									if (fieldValue != null) {
										set.add(fieldValue);
									}
								}
							} else {
								Object newObj = this.buildObjectFromType(setParameterizedType);
								this.read(newObj, childElement);
								set.add(newObj);
							}
							break;
						case MAP:
							// TODO map reading
							@SuppressWarnings("unchecked")
							Map<Object, Object> map = (Map<Object, Object>) field.get(obj);
							if (map == null) {
								map = new LinkedHashMap<Object, Object>();
								field.set(obj, map);
							}

							MapInfo mapInfo = es.getMapInfo();

							Object keyValue = null;
							Object valueValue = null;

							switch (mapInfo.entryStrategy) {
							case ATTRIBUTES:
								String key = childElement.getAttributeNS(null,  es.getMapInfo().keyName);
								String value = childElement.getAttributeNS(null,  es.getMapInfo().valueName);

								keyValue = Transformer.read(key, es.getMapInfo().keyClazz);
								valueValue = Transformer.read(value, es.getMapInfo().valueClazz);
								break;
							case ELEMENTS:
								int a = 0;
								for (int j = 0; j < childElement.getChildNodes().getLength(); j++) {
									if (childElement.getChildNodes().item(j).getNodeType() == Node.ELEMENT_NODE) {
										if (es.getMapInfo().keyName.equals(childElement.getChildNodes().item(j).getNodeName())) {
											keyValue = readSubElement((Element) childElement.getChildNodes().item(j), es.getMapInfo().keyClazz);
											a++;
										} else if (es.getMapInfo().valueName.equals(childElement.getChildNodes().item(j).getNodeName())) {
											valueValue = readSubElement((Element) childElement.getChildNodes().item(j), es.getMapInfo().valueClazz);
											a++;
										}
										if (a > 1) {
											break;
										}
									}
								}
								break;
							}
							map.put(keyValue, valueValue);

							break;
						case ARRAY:
							Object array = field.get(obj);
							if (array == null) {
								// if array, we first count how many element of
								// this type there are, then, set field find
								// effective ELEMENT_NODE

								// reset index
								index = 0;
								int effectiveSize = 0;
								{
									Node nodeTemp;
									for (int j = 0; j < n; j++) {
										nodeTemp = nodeList.item(j);
										// increment counter only if it has same
										// tag name
										if (nodeTemp.getNodeType() == Node.ELEMENT_NODE && es.getName().equals(nodeTemp.getLocalName())) {
											effectiveSize++;
										}
									}
								}

								array = Array.newInstance(es.getFieldType(), effectiveSize);
								field.set(obj, array);
							}

							Class<?> arrayParameterizedType = es.getFieldType();

							// primitive
							if (Transformer.isPrimitive(arrayParameterizedType)) {
								String xmlValue = childElement.getTextContent();
								if (!StringUtil.isEmpty(xmlValue)) {
									Object fieldValue = Transformer.read(xmlValue, arrayParameterizedType);
									if (fieldValue != null) {
										Array.set(array, index++, fieldValue);
									}
								}
							} else {
								Object newObj = this.buildObjectFromType(arrayParameterizedType);
								this.read(newObj, childElement);
								Array.set(array, index++, newObj);
							}
							break;
						case ELEMENT:
							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							// single field value
							// primitive
							if (Transformer.isPrimitive(fieldType)) {
								String xmlValue = childElement.getTextContent();
								if (!StringUtil.isEmpty(xmlValue)) {
									Object fieldValue = Transformer.read(xmlValue, fieldType);
									if (fieldValue != null) {
										field.set(obj, fieldValue);
									}
								}
							} else { 
								Object newObj = this.buildObjectFromType(fieldType);
								this.read(newObj, childElement);
								field.set(obj, newObj);
							}
							break;
						}
					} else if (anyChildElements != null) {
						anyChildElements.add(childElement);
					}

				}
			}
		}
	}

	protected Object readSubElement(Element element, Class<?> type) throws Exception {
		// primitive
		if (Transformer.isPrimitive(type)) {
			String xmlValue = element.getTextContent();
			if (!StringUtil.isEmpty(xmlValue)) {
				Object fieldValue = Transformer.read(xmlValue, type);

				return fieldValue;
			}
		}
		Object newObj = this.buildObjectFromType(type);
		this.read(newObj, element);

		return newObj;
	}

	protected void readAnyElement(Object obj, List<Element> anyElements) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(obj);

		AnyElementSchema aes = ms.getAnyElementSchema();
		if (aes != null && anyElements != null && anyElements.size() > 0) {
			Field field = aes.getField();
			field.set(obj, anyElements);
		}
	}

	protected Object buildObjectFromType(Class<?> type) throws Exception {
		try {
			Constructor<?> con = TypeReflector.getConstructor(type);
			Object obj = con.newInstance();
			return obj;
		} catch (NoSuchMethodException nsme) {
			throw new ReaderException("No-arg contructor is missing, type = " + type.getName());
		}
	}

}
