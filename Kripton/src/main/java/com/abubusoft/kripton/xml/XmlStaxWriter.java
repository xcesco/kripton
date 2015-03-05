package com.abubusoft.kripton.xml;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Format;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.binder.schema.AnyElementSchema;
import com.abubusoft.kripton.binder.schema.AttributeSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.schema.RootElementSchema;
import com.abubusoft.kripton.binder.schema.ValueSchema;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;

public class XmlStaxWriter implements BinderWriter {

	protected static final String IDENT_PROPERTY = "http://xml.apache.org/xslt#indent-amount";
	protected static final String PROPERTY_SERIALIZER_INDENTATION = "http://xmlpull.org/v1/doc/properties.html#serializer-indentation";

	protected Format format;

	protected XMLOutputFactory factory;

	public XmlStaxWriter() {
		this(new Format());
	}

	public XmlStaxWriter(Format format) {
		this.format = format;

		factory = XMLOutputFactory.newFactory();
	}

	public void write(Object source, Writer out) throws WriterException, MappingException {
		try {
			// entry validation
			validate(source, out);
			XMLStreamWriter serializer = null;
			if (format.isIndent()) {
				serializer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(out));
			} else {
				serializer = factory.createXMLStreamWriter(out);
			}

			// serializer.setOutput(out);
			serializer.writeStartDocument(format.getEncoding(), null);

			MappingSchema ms = MappingSchema.fromObject(source);
			RootElementSchema res = ms.getRootElementSchema();
			String namespace = res.getNamespace();
			String xmlName = res.getName();

			if (!StringUtil.isEmpty(namespace)) {
				// bind to default namespace
				serializer.setPrefix("", namespace);
			}

			serializer.writeStartElement(xmlName);
			this.writeObject(serializer, source, namespace);
			serializer.writeEndElement();

			serializer.writeEndDocument();
			serializer.close();
		} catch (MappingException me) {
			throw me;
		} catch (IllegalArgumentException iae) {
			throw new WriterException("Entry validation failure", iae);
		} catch (Exception e) {
			throw new WriterException("Error to write/serialize object", e);
		}
	}

	protected void validate(Object source, Writer out) {
		if (source == null) {
			throw new IllegalArgumentException("Can not write null instance!");
		}

		if (out == null) {
			throw new IllegalArgumentException("Writer is null!");
		}

		if (Transformer.isPrimitive(source.getClass())) {
			throw new IllegalArgumentException("Can not write primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
		}
	}

	public void write(Object source, OutputStream os) throws WriterException, MappingException {
		try {
			this.write(source, new OutputStreamWriter(os, format.getEncoding()));
		} catch (UnsupportedEncodingException e) {
			throw new WriterException("Error to write/serialize object", e);
		}
	}

	protected void writeObject(XMLStreamWriter serializer, Object source, String namespace) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(source);

		// write xml attributes first
		writeAttributes(serializer, source, ms);

		// write xml value if any
		writeValue(serializer, source, ms);

		// write xml elements
		writeElements(serializer, source, ms, namespace);

		// write any elements if has
		writeAnyElements(serializer, source, ms);
	}

	private void writeAnyElements(XMLStreamWriter serializer, Object source, MappingSchema ms) throws Exception {
		AnyElementSchema anyElementSchema = ms.getAnyElementSchema();
		if (anyElementSchema != null) {
			Field field = anyElementSchema.getField();
			List<?> list = (List<?>) field.get(source);
			if (list != null) {
				for (Object entry : list) {
					if (entry != null) {
						if (entry instanceof Element) {
							this.writeDomElement(serializer, (Element) entry);
						} else {
							this.writeAnyObject(serializer, entry);
						}
					}
				}
			}
		}
	}

	private void writeDomElement(XMLStreamWriter serializer, Element element) throws Exception {
		if (element == null)
			return; // be cautious

		if (!StringUtil.isEmpty(element.getLocalName())) {
			String namespace = element.getNamespaceURI();

			serializer.writeStartElement(namespace, element.getLocalName());

			NamedNodeMap nmap = element.getAttributes(); // write attributes
			for (int i = 0; i < nmap.getLength(); i++) {
				if (nmap.item(i).getNodeType() == Node.ATTRIBUTE_NODE) {
					String name = nmap.item(i).getNodeName();
					String value = nmap.item(i).getNodeValue();
					serializer.writeAttribute(null, name, value);
				}
			}

			if (element.hasChildNodes()) { // write children elements
				NodeList children = element.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					if (child.getNodeType() == Node.ELEMENT_NODE) {
						writeDomElement(serializer, (Element) child);
					} else if (child.getNodeType() == Node.TEXT_NODE) { // write
																		// element
																		// text
						String value = child.getNodeValue();
						if (!StringUtil.isEmpty(value)) {
							serializer.writeCharacters(value);
						}
					}
				}
			}

			serializer.writeEndElement();
		}
	}

	private void writeAnyObject(XMLStreamWriter serializer, Object source) throws Exception {
		if (source == null)
			return; // be cautious

		MappingSchema ms = MappingSchema.fromObject(source);
		RootElementSchema res = ms.getRootElementSchema();
		String namespace = res.getNamespace();
		String xmlName = res.getName();

		serializer.writeStartElement(namespace, xmlName);
		this.writeObject(serializer, source, namespace);
		serializer.writeEndElement();
	}

	private void writeAttributes(XMLStreamWriter serializer, Object source, MappingSchema ms) throws Exception {
		Map<String, AttributeSchema> field2AttributeSchemaMapping = ms.getField2AttributeSchemaMapping();
		for (String fieldName : field2AttributeSchemaMapping.keySet()) {
			AttributeSchema as = field2AttributeSchemaMapping.get(fieldName);
			Field field = as.getField();
			Object value = field.get(source);
			if (value != null) {
				String attValue = Transformer.write(value, field.getType());
				if (!StringUtil.isEmpty(attValue)) {
					serializer.writeAttribute(null, as.getName(), attValue);
				}
			}
		}
	}

	private void writeValue(XMLStreamWriter serializer, Object source, MappingSchema ms) throws Exception {
		ValueSchema vs = ms.getValueSchema();
		if (vs == null)
			return; // no ValueSchema, do nothing

		Field field = vs.getField();
		Object value = field.get(source);
		if (value != null) {
			String text = Transformer.write(value, field.getType());
			if (!StringUtil.isEmpty(text)) {
				if (vs.isData()) {
					serializer.writeCData(text);
				} else {
					serializer.writeCharacters(text);
				}
			}
		}
	}

	private void writeElements(XMLStreamWriter serializer, Object source, MappingSchema ms, String namespace) throws Exception {
		Map<String, Object> field2SchemaMapping = ms.getField2SchemaMapping();
		for (String fieldName : field2SchemaMapping.keySet()) {
			Object schemaObj = field2SchemaMapping.get(fieldName);
			if (schemaObj instanceof ElementSchema) {
				ElementSchema es = (ElementSchema) schemaObj;
				Field field = es.getField();
				Object value = field.get(source);
				if (value != null) {
					if (es.isList()) {
						// this.writeElementList(serializer, value, es,
						// namespace);
						this.writeElementList(serializer, value, es, namespace);
					} else if (es.isArray() && es.getParameterizedType() != Byte.TYPE) {
						this.writeElementArray(serializer, value, es, namespace);
					} else {
						this.writeElement(serializer, value, es, namespace);
					}
				}
			}
		}
	}

	private void writeElementList(XMLStreamWriter serializer, Object source, ElementSchema es, String namespace) throws Exception {
		// String xmlName = es.getXmlName()+"list";
		if (es.hasWrapperName()) {
			// serializer.writeStartElement(namespace, es.getWrapperName());
			serializer.writeStartElement(es.getWrapperName());
		}

		for (Object value : (List<?>) source) {
			this.writeElement(serializer, value, es, namespace);
		}

		if (es.hasWrapperName()) {
			serializer.writeEndElement();
		}

		// serializer.endTag(namespace, xmlName);
	}

	private void writeElementArray(XMLStreamWriter serializer, Object source, ElementSchema es, String namespace) throws Exception {
		// String xmlName = es.getXmlName()+"list";
		if (es.hasWrapperName()) {
			serializer.writeStartElement(es.getWrapperName());
		}

		int n = Array.getLength(source);
		for (int i = 0; i < n; i++) {
			this.writeElement(serializer, Array.get(source, i), es, namespace);
		}

		if (es.hasWrapperName()) {
			serializer.writeEndElement();
		}

		// serializer.endTag(namespace, xmlName);
	}

	private void writeElement(XMLStreamWriter serializer, Object source, ElementSchema es, String namespace) throws Exception {
		Class<?> type = null;
		if (es.isList() || (es.isArray() && es.getParameterizedType() != Byte.TYPE)) {
			type = es.getParameterizedType();
		} else {
			type = es.getField().getType();
		}

		if (source == null)
			return; // do nothing

		String xmlName = es.getName();

		// primitives
		if (Transformer.isPrimitive(type)) {
			String value = Transformer.write(source, type);
			if (StringUtil.isEmpty(value))
				return;

			// serializer.writeStartElement(namespace, xmlName);
			serializer.writeStartElement(xmlName);

			if (es.isData()) {
				serializer.writeCData(value);
			} else {
				serializer.writeCharacters(value);
			}
			serializer.writeEndElement();

			return;
		}

		// object
		// serializer.writeStartElement(namespace, xmlName);
		serializer.writeStartElement(xmlName);

		this.writeObject(serializer, source, namespace);
		serializer.writeEndElement();
	}

	@Override
	public String write(Object source) throws WriterException, MappingException {
		StringWriter out = new StringWriter();
		this.write(source, out);
		return out.toString();
	}
}
