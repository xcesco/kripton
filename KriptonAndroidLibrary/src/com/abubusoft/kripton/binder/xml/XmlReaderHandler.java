package com.abubusoft.kripton.binder.xml;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.binder.schema.AttributeSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.schema.RootElementSchema;
import com.abubusoft.kripton.binder.schema.SchemaArray;
import com.abubusoft.kripton.binder.schema.ValueSchema;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.common.TypeReflector;

/**
 * SAX handler implementation for XmlSaxReader
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
class XmlReaderHandler extends DefaultHandler {

	private XmlReaderHelper helper;

	@Override
	public void endDocument() throws SAXException {
	}

	public XmlReaderHandler(XmlReaderHelper helper) {
		this.helper = helper;
	}

	private void populateAttributes(Object obj, Attributes attrs, MappingSchema ms) throws Exception {
		Map<String, AttributeSchema> xml2AttributeSchemaMapping = ms.getXml2AttributeSchemaMapping();
		for (int index = 0; index < attrs.getLength(); index++) {
			String attrName = attrs.getLocalName(index);

			AttributeSchema as = xml2AttributeSchemaMapping.get(attrName);
			if (as == null)
				continue;

			String attrValue = attrs.getValue(index);

			Object value = Transformer.read(attrValue, as.getFieldType());

			Field field = as.getField();
			field.set(obj, value);
		}
	}

	@SuppressWarnings("rawtypes")
	public void startElement(String uri, String localName, String name, Attributes attrs) throws SAXException {
		try {
			Object obj = helper.valueStack.peek();
			MappingSchema ms = MappingSchema.fromObject(obj);

			Map<String, Object> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
			if (xmlWrapper2SchemaMapping.containsKey(localName)) {
				// SchemaArray value = new SchemaArray((AbstractSchema)
				// xmlWrapper2SchemaMapping.get(localName), new ArrayList());
				// helper.arrayStack.add(value);
				return;
			}

			helper.depth++;
			// clear the textBuilder
			helper.clearTextBuffer();

			if (helper.depth > helper.valueStack.size() + 1) {
				// unexpected xml element, just ignore
				return;
			}

			if (helper.isRoot()) { // first time root element mapping
				RootElementSchema res = ms.getRootElementSchema();
				String xmlName = res.getName();
				// String namespace = res.getNamespace();
				// validation only for root element
				// String srcXmlFullname = StringUtil.isEmpty(uri)?localName:"{"
				// + uri + "}#" + localName;
				// String targetXmlFullname =
				// StringUtil.isEmpty(namespace)?xmlName:"{" + namespace + "}#"
				// + xmlName;
				// if (!srcXmlFullname.equals(targetXmlFullname)) {
				// throw new ReaderException("Root element name mismatch, " +
				// targetXmlFullname + " != " + srcXmlFullname);
				// }

				// simple validation only for root element
				if (!xmlName.equalsIgnoreCase(localName)) {
					throw new ReaderException("Root element name mismatch, " + localName + " != " + xmlName);
				}

				if (attrs != null && attrs.getLength() > 0) {
					this.populateAttributes(obj, attrs, ms);
				}
			} else { // sub element mapping
				Map<String, Object> xml2SchemaMapping = ms.getXml2SchemaMapping();

				Object schema = xml2SchemaMapping.get(localName);
				if (schema != null && schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;

					// detect type
					Class<?> type = es.getFieldType();

					if (!Transformer.isPrimitive(type)) {

						MappingSchema newMs = MappingSchema.fromClass(type);
						Constructor con = null;
						try {
							con = TypeReflector.getConstructor(type);
						} catch (NoSuchMethodException nsme) {
							throw new ReaderException("No-arg constructor is missing, type = " + type.getName());
						}
						Object newObj = con.newInstance();
						if (attrs != null && attrs.getLength() > 0) {
							this.populateAttributes(newObj, attrs, newMs);
						}

						helper.valueStack.push(newObj);
					}

				}
			}

		} catch (Exception ex) {
			throw new SAXException("Reading exception in startElement, " + ex.getMessage(), ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void endElement(String uri, String localName, String name) throws SAXException {
		try {
			{
				Object obj = helper.valueStack.peek();
				MappingSchema ms = MappingSchema.fromObject(obj);

				Map<String, Object> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
				if (xmlWrapper2SchemaMapping.containsKey(localName)) {
					// ArrayList array = helper.listStack.pop();
					return;
				}

				SchemaArray lastArray = helper.arrayStack.size() > 0 ? helper.arrayStack.peek() : null;
				if (lastArray != null) {
					if (lastArray.value0.getField().getDeclaringClass() == obj.getClass() && !lastArray.value0.getName().equals(localName)) {
						Object schema = lastArray.value0;
						if (schema != null && schema instanceof ElementSchema) {
							ElementSchema es = (ElementSchema) schema;
							Field field = es.getField();

							int n = lastArray.value1.size();
							Object value = Array.newInstance(es.getFieldType(), lastArray.value1.size());
							// lastArray.value1.toArray();
							// System.arraycopy(lastArray.value1.toArray(), 0,
							// value, 0, n);
							for (int i = 0; i < n; i++) {
								Array.set(value, i, lastArray.value1.get(i));
							}

							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							field.set(obj, value);
							helper.arrayStack.pop();
						}

					}
				}

			}

			if (helper.depth > helper.valueStack.size() + 1) {
				// unexpected xml element, just ignore
				helper.depth--;
				return;
			} else if (helper.depth == helper.valueStack.size() + 1) {
				// handle primitive field
				// Object obj = helper.valueStack.peek();
				Object obj = helper.valueStack.peek();
				MappingSchema ms = MappingSchema.fromObject(obj);

				Map<String, Object> xml2SchemaMapping = ms.getXml2SchemaMapping();
				Object schema = xml2SchemaMapping.get(localName);
				if (schema != null && schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;
					Field field = es.getField();
					String xmlData = helper.textBuilder.toString();
					if (!StringUtil.isEmpty(xmlData)) {
						Class<?> paramizedType;

						switch (es.getType()) {
						case LIST:
							paramizedType = es.getFieldType();
							Object value = Transformer.read(xmlData, paramizedType);
							List list = (List) field.get(obj);
							if (list == null) {
								list = new ArrayList();
								field.set(obj, list);
							}
							list.add(value);
							break;
						case MAP:
							// TODO
							break;
						case SET:
							paramizedType = es.getFieldType();
							value = Transformer.read(xmlData, paramizedType);
							Set set = (Set) field.get(obj);
							if (set == null) {
								set = new LinkedHashSet();
								field.set(obj, set);
							}
							set.add(value);
							break;
						case ARRAY:
							SchemaArray schemaArray = helper.arrayStack.size() > 0 ? helper.arrayStack.peek() : null;

							if (schemaArray == null || schemaArray.value0 != es) {
								schemaArray = new SchemaArray(es, new ArrayList());
								helper.arrayStack.add(schemaArray);
							}
							paramizedType = es.getFieldType();
							// Object value = Transformer.read(xmlData,
							// paramizedType);

							// --------
							if (Transformer.isPrimitive(paramizedType)) {
								value = Transformer.read(xmlData, paramizedType);
							} else {
								value = obj;
							}
							// --------

							schemaArray.value1.add(value);

							break;
						case CDATA:
						case DEFAULT:
							value = Transformer.read(xmlData, field.getType());
							field.set(obj, value);
							break;
						}

					}
				}
			} else if (helper.depth == helper.valueStack.size()) {
				// handle object field
				Object obj = helper.valueStack.pop();
				MappingSchema ms = MappingSchema.fromObject(obj);

				ValueSchema vs = ms.getValueSchema();
				if (vs != null) {
					Field field = vs.getField();
					String xmlData = helper.textBuilder.toString();
					if (!StringUtil.isEmpty(xmlData)) {
						Object value = Transformer.read(xmlData, vs.getFieldType());
						field.set(obj, value);
					}
				}

				if (helper.valueStack.size() == 0) { // the end
					helper.valueStack.push(obj);
					helper.depth--;
					return;
				}

				Object parentObj = helper.valueStack.peek();
				MappingSchema parentMs = MappingSchema.fromObject(parentObj);
				Map<String, Object> parentXml2SchemaMapping = parentMs.getXml2SchemaMapping();

				Object schema = parentXml2SchemaMapping.get(localName);
				if (schema != null && schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;
					Field field = es.getField();

					switch (es.getType()) {
					case LIST:
						List list = (List) field.get(parentObj);
						if (list == null) {
							list = new ArrayList();
							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							field.set(parentObj, list);
						}
						list.add(obj);
						break;
					case SET:
						Set set = (Set) field.get(parentObj);
						if (set == null) {
							set = new LinkedHashSet();
							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							field.set(parentObj, set);
						}
						set.add(obj);
						break;
					case ARRAY:
						SchemaArray schemaArray = helper.arrayStack.size() > 0 ? helper.arrayStack.peek() : null;

						if (schemaArray == null || schemaArray.value0 != es) {
							schemaArray = new SchemaArray(es, new ArrayList());
							helper.arrayStack.add(schemaArray);
						}
						Class<?> paramizedType = es.getFieldType();
						// Object value = Transformer.read(xmlData,
						// paramizedType);
						Object value = null;
						// --------
						if (Transformer.isPrimitive(paramizedType)) {
							// value = Transformer.read(xmlData, paramizedType);
							// not here
						} else {
							value = obj;
						}
						// --------

						schemaArray.value1.add(value);
						break;
					case MAP:
						// TODO
						break;
					case CDATA:
					case DEFAULT:
						field.set(parentObj, obj);
						break;
					}
				}

			}

			helper.depth--;
		} catch (Exception ex) {
			throw new SAXException("Reading Exception in endElement, " + ex.getMessage(), ex);
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String text = new String(ch, start, length);
		helper.textBuilder.append(text);
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
