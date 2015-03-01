package com.abubusoft.kripton.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.abubusoft.kripton.binder.BinderReader;
import com.abubusoft.kripton.binder.Format;
import com.abubusoft.kripton.binder.annotation.schema.AnyElementSchema;
import com.abubusoft.kripton.binder.annotation.schema.AttributeSchema;
import com.abubusoft.kripton.binder.annotation.schema.ElementSchema;
import com.abubusoft.kripton.binder.annotation.schema.MappingSchema;
import com.abubusoft.kripton.binder.annotation.schema.RootElementSchema;
import com.abubusoft.kripton.binder.annotation.schema.ValueSchema;
import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.ReaderException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.reflect.TypeReflector;
import com.abubusoft.kripton.util.StringUtil;

/**
 * BinderReader implementation using DOM xml parser.
 * 
 * @author bulldog
 *
 */
public class XmlDOMReader implements BinderReader {

	protected Format format;

	protected static final ThreadLocal<DocumentBuilder> builderLocal = new ThreadLocal<DocumentBuilder>() {
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
		this(new Format());
	}

	public XmlDOMReader(Format format) {
		this.format = format;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T read(Class<? extends T> type, InputStream source) throws ReaderException, MappingException {

		this.validate(type, source);

		try {
			DocumentBuilder db = builderLocal.get();
			Document doc = db.parse(source);

			Element rootElement = doc.getDocumentElement();
			MappingSchema ms = MappingSchema.fromClass(type);
			RootElementSchema res = ms.getRootElementSchema();

			String xmlName = res.getName();

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
			return; // no further read if xml text presents
		}

		List<Element> anyElements = new ArrayList<Element>();

		this.readElement(obj, element, anyElements);

		this.readAnyElement(obj, anyElements);
	}

	private void readAttribute(Object obj, Element element) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(obj);

		// read xml attributes
		Map<String, AttributeSchema> asm = ms.getXml2AttributeSchemaMapping();
		if (!asm.isEmpty() && element.hasAttributes()) {
			for (String attrXmlName : asm.keySet()) {
				AttributeSchema as = asm.get(attrXmlName);
				String attrValue = element.getAttributeNS(null, attrXmlName);
				if (!StringUtil.isEmpty(attrValue)) {
					Field field = as.getField();
					Object filedValue = Transformer.read(attrValue, field.getType());
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
		ValueSchema vs = ms.getValueSchema();
		if (vs != null) {
			Field field = vs.getField();
			String text = element.getTextContent();
			if (!StringUtil.isEmpty(text)) {
				Object fieldValue = Transformer.read(text, field.getType());
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
		Map<String, Object> xml2SchemaMapping = ms.getXml2SchemaMapping();
		Map<String, Object> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
		NodeList nodeList = element.getChildNodes();
		
		int n=nodeList.getLength();
		
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

					Object schemaObj = xml2SchemaMapping.get(localName);

					if (schemaObj != null && schemaObj instanceof ElementSchema) { 
						// found match element
						ElementSchema es = (ElementSchema) schemaObj;
						Field field = es.getField();
						Class<?> fieldType = field.getType();

						if (es.isList()) { // collection
							@SuppressWarnings("unchecked")
							List<Object> list = (List<Object>) field.get(obj);
							if (list == null) {
								list = new ArrayList<Object>();
								field.set(obj, list);
							}

							Class<?> parameterizedType = es.getParameterizedType();

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

						} else if (es.isArray()  && es.getParameterizedType()!=Byte.TYPE) {
							// se è un array, deve essere assolutamente wrappato.
							// se è wrappato, l'elenco dei nodi a questo livello è il numero di
							// elementi dell'array
							Object[] array = (Object[]) field.get(obj);
							if (array == null) {
								array = (Object[]) Array.newInstance(es.getParameterizedType(), n);
								field.set(obj, array);
							}

							Class<?> parameterizedType = es.getParameterizedType();

							// primitive
							if (Transformer.isPrimitive(parameterizedType)) {
								String xmlValue = childElement.getTextContent();
								if (!StringUtil.isEmpty(xmlValue)) {
									Object fieldValue = Transformer.read(xmlValue, parameterizedType);
									if (fieldValue != null) {
										array[i]=fieldValue;
									}
								}
							} else {
								Object newObj = this.buildObjectFromType(parameterizedType);
								this.read(newObj, childElement);
								array[i]=newObj;								
							}

						} else { 
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
						}
					} else if (anyChildElements != null) {
						anyChildElements.add(childElement);
					}

				}
			}
		}
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
