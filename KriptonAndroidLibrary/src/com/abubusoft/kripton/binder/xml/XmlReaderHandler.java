package com.abubusoft.kripton.binder.xml;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.schema.TypeElementSchema;
import com.abubusoft.kripton.binder.schema.SchemaArray;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.binder.xml.internal.MapEntry;
import com.abubusoft.kripton.binder.xml.internal.MapEntryImpl;
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
		Map<String, ElementSchema> attributeSchemaMapping = ms.getXml2AttributeSchemaMapping();

		for (int index = 0; index < attrs.getLength(); index++) {
			String attrName = attrs.getLocalName(index);

			ElementSchema as = attributeSchemaMapping.get(attrName);
			if (as == null)
				continue;

			String attrValue = attrs.getValue(index);

			Object value = Transformer.read(attrValue, as.getFieldType());

			Field field = as.getField();
			field.set(obj, value);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void startElement(String uri, String localName, String name, Attributes attrs) throws SAXException {
		try {
			Object obj = helper.valueStack.peek();
			MappingSchema ms = MappingSchema.fromObject(obj);

			Map<String, ElementSchema> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
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
				TypeElementSchema res = ms.getRootElementSchema();
				String xmlName = res.xmlInfo.getName();
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
				Map<String, ElementSchema> xml2SchemaMapping = ms.getXml2SchemaMapping();

				Object schema = xml2SchemaMapping.get(localName);
				if (schema != null && schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;

					// detect type
					Class<?> type = es.getFieldType();
					if (ms.xmlInfo.isMapEntryStub()) {
						MapEntry mapStrategy = (MapEntry) obj;

						if (mapStrategy.isKey(localName)) {
							type = mapStrategy.getMapInfo().keyClazz;
						} else if (mapStrategy.isValue(localName)) {
							type = mapStrategy.getMapInfo().valueClazz;
						}
					}

					if (!Transformer.isPrimitive(type)) {
						Object newObj = null;
						if (es.isMap()) {
							Map map = (Map) es.getField().get(obj);
							if (map == null) {
								map = new LinkedHashMap();
								es.getField().set(obj, map);
							}

							switch (es.getMapInfo().entryStrategy) {
							case ATTRIBUTES: {
								MapEntryImpl mapPolicy = new MapEntryImpl();
								mapPolicy.set(map, es.getMapInfo());

								if (attrs != null && attrs.getLength() > 0) {
									this.populateAttributesForMap(mapPolicy, attrs);
								}

								if (mapPolicy.isEntryReady()) {
									map.put(mapPolicy.getEntryKey(), mapPolicy.getEntryValue());
									mapPolicy.clear();
								}
							}
								// exit, DO NOT PUSH OBJECT!
								return;
							case ELEMENTS: {
								MapEntryImpl mapPolicy = new MapEntryImpl();
								mapPolicy.set(map, es.getMapInfo());

								newObj = mapPolicy;
							}
								break;
							}

						} else {
							MappingSchema newMs = MappingSchema.fromClass(type);
							Constructor con = null;
							try {
								con = TypeReflector.getConstructor(type);
							} catch (NoSuchMethodException nsme) {
								throw new ReaderException("No-arg constructor is missing, type = " + type.getName());
							}
							newObj = con.newInstance();
							if (attrs != null && attrs.getLength() > 0) {
								this.populateAttributes(newObj, attrs, newMs);
							}
						}

						helper.valueStack.push(newObj);
					}

				}
			}

		} catch (Exception ex) {
			throw new SAXException("Reading exception in startElement, " + ex.getMessage(), ex);
		}
	}

	private void populateAttributesForMap(MapEntryImpl mapPolicy, Attributes attrs) throws Exception {
		for (int index = 0; index < attrs.getLength(); index++) {
			String attrName = attrs.getLocalName(index);
			String attrValue = attrs.getValue(index);

			if (mapPolicy.isValue(attrName)) {
				Object value = Transformer.read(attrValue, mapPolicy.getMapInfo().valueClazz);
				mapPolicy.setEntryValue(value);
			} else if (mapPolicy.isKey(attrName)) {
				Object value = Transformer.read(attrValue, mapPolicy.getMapInfo().keyClazz);
				mapPolicy.setEntryKey(value);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void endElement(String uri, String localName, String name) throws SAXException {
		try {
			{
				Object obj = helper.valueStack.peek();
				MappingSchema ms = MappingSchema.fromObject(obj);

				Map<String, ElementSchema> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
				if (xmlWrapper2SchemaMapping.containsKey(localName)) {
					// ArrayList array = helper.listStack.pop();
					return;
				}

				SchemaArray lastArray = helper.arrayStack.size() > 0 ? helper.arrayStack.peek() : null;
				if (lastArray != null && lastArray.value0.getField().getDeclaringClass() == obj.getClass() && !lastArray.value0.getName().equals(localName)) {
					ElementSchema es = lastArray.value0;
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

			if (helper.depth > helper.valueStack.size() + 1) {
				// unexpected xml element, just ignore
				helper.depth--;
				return;
			} else if (helper.depth == helper.valueStack.size() + 1) {
				// handle primitive field
				// Object obj = helper.valueStack.peek();
				Object obj = helper.valueStack.peek();
				MappingSchema ms = MappingSchema.fromObject(obj);

				Map<String, ElementSchema> xml2SchemaMapping = ms.getXml2SchemaMapping();
				Object schema = xml2SchemaMapping.get(localName);

				if (schema != null && schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;
					Field field = es.getField();
					String xmlData = helper.textBuilder.toString();
					if (!StringUtil.isEmpty(xmlData)) {
						if (ms.xmlInfo.isMapEntryStub()) {
							MapEntry mapStrategy = (MapEntry) obj;
							Class<?> type = null;
							Object value = null;

							if (mapStrategy.isKey(localName)) {
								type = mapStrategy.getMapInfo().keyClazz;
								value = Transformer.read(xmlData, type);
								mapStrategy.setEntryKey(value);
							} else if (mapStrategy.isValue(localName)) {
								type = mapStrategy.getMapInfo().valueClazz;
								value = Transformer.read(xmlData, type);
								mapStrategy.setEntryValue(value);
							}

							if (mapStrategy.isEntryReady()) {
								mapStrategy.getMap().put(mapStrategy.getEntryKey(), mapStrategy.getEntryValue());
								mapStrategy.clear();
							}

						} else {

							if (es.isList()) {
								Class<?> paramizedType = es.getFieldType();
								Object value = Transformer.read(xmlData, paramizedType);
								List list = (List) field.get(obj);
								if (list == null) {
									list = new ArrayList();
									field.set(obj, list);
								}
								list.add(value);
							} else if (es.isSet()) {
								Class<?> paramizedType = es.getFieldType();
								Object value = Transformer.read(xmlData, paramizedType);
								Set set = (Set) field.get(obj);
								if (set == null) {
									set = new LinkedHashSet();
									field.set(obj, set);
								}
								set.add(value);
							} else if (es.isArray() && es.getFieldType() != Byte.TYPE) {
								SchemaArray schemaArray = helper.arrayStack.size() > 0 ? helper.arrayStack.peek() : null;

								if (schemaArray == null || schemaArray.value0 != es) {
									schemaArray = new SchemaArray(es, new ArrayList());
									helper.arrayStack.add(schemaArray);
								}
								Class<?> paramizedType = es.getFieldType();
								// Object value = Transformer.read(xmlData,
								// paramizedType);
								Object value;
								// --------
								if (Transformer.isPrimitive(paramizedType)) {
									value = Transformer.read(xmlData, paramizedType);
								} else {
									value = obj;
								}
								// --------

								schemaArray.value1.add(value);

							} else {
								Object value = Transformer.read(xmlData, field.getType());
								field.set(obj, value);
							}
						}
					}
				}
			} else if (helper.depth == helper.valueStack.size()) {
				// handle object field
				Object obj = helper.valueStack.pop();
				MappingSchema ms = MappingSchema.fromObject(obj);

				ElementSchema vs = ms.getValueSchema();
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

				// retrieve parent object
				Object parentObj = helper.valueStack.peek();
				MappingSchema parentMs = MappingSchema.fromObject(parentObj);
				Map<String, ElementSchema> parentXml2SchemaMapping = parentMs.getXml2SchemaMapping();

				Object schema = parentXml2SchemaMapping.get(localName);
				if (schema != null && schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;
					Field field = es.getField();
					if (ms.xmlInfo.isMapEntryStub()) {
						// do nothing... all is done!
					} else if (parentMs.xmlInfo.isMapEntryStub()) {
						MapEntry mapStrategy = (MapEntry) parentObj;
						Object value = null;

						if (mapStrategy.isKey(localName)) {
							value = obj;
							mapStrategy.setEntryKey(value);
						} else if (mapStrategy.isValue(localName)) {
							value = obj;
							mapStrategy.setEntryValue(value);
						}

						if (mapStrategy.isEntryReady()) {
							mapStrategy.getMap().put(mapStrategy.getEntryKey(), mapStrategy.getEntryValue());
							mapStrategy.clear();
						}
					} else if (es.isList()) {
						List list = (List) field.get(parentObj);
						if (list == null) {
							list = new ArrayList();
							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							field.set(parentObj, list);
						}
						list.add(obj);
					} else if (es.isSet()) {
						Set set = (Set) field.get(parentObj);
						if (set == null) {
							set = new LinkedHashSet();
							if (!field.isAccessible()) {
								field.setAccessible(true);
							}
							field.set(parentObj, set);
						}
						set.add(obj);
					} else if (es.isArray() && es.getFieldType() != Byte.TYPE) {
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
					} else {
						field.set(parentObj, obj);
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
