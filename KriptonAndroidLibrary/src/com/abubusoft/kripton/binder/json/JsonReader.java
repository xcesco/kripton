package com.abubusoft.kripton.binder.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.binder.json.internal.JSONArray;
import com.abubusoft.kripton.binder.json.internal.JSONObject;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema.MapInfo;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.ReaderException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.common.TypeReflector;

/**
 * BinderReader implementation using org.json library,
 * 
 * JsonReader de-serialize JSON string into POJO, the de-serialization is guided
 * by mapping schema defined in the POJO using Nano annotations.
 * 
 * @author bulldog
 * 
 */
public class JsonReader implements BinderReader {

	/**
	 * format of json reader
	 */
	private Options format;

	public JsonReader() {
		this(new Options());
	}

	public JsonReader(Options format) {
		this.format = format;
	}

	public <T> T read(Class<? extends T> type, InputStream source) throws ReaderException, MappingException {

		try {
			return this.read(type, new InputStreamReader(source, format.getEncoding()));
		} catch (UnsupportedEncodingException e) {
			throw new ReaderException("Encoding is not supported", e);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T read(Class<? extends T> type, String source) throws ReaderException, MappingException {
		validate(type, source);

		try {
			Constructor con = TypeReflector.getConstructor(type);
			Object instance = con.newInstance();

			JSONObject jsonObj = new JSONObject(source);

			Object jsonValue = jsonObj;

			if (jsonValue instanceof JSONArray) {
				jsonValue = ((JSONArray) jsonValue).get(0);
			}

			this.readObject(instance, (JSONObject) jsonValue);

			return (T) instance;
		} catch (ReaderException re) {
			throw re;
		} catch (MappingException me) {
			throw me;
		} catch (Exception e) {
			throw new ReaderException("Error to read object", e);
		}
	}

	public <T> T read(Class<? extends T> type, Reader source) throws ReaderException, MappingException {
		if (source == null) {
			throw new ReaderException("Cannot read, reader is null!");
		}

		try {
			return this.read(type, StringUtil.reader2String(source));
		} catch (IOException e) {
			throw new ReaderException("IO error!", e);
		}

	}

	// private String reader2String(Reader source) throws IOException {
	// char[] cbuf = new char[65535];
	// StringBuffer stringbuf = new StringBuffer();
	//
	// int count = 0;
	// while ((count = source.read(cbuf, 0, 65535)) != -1) {
	// stringbuf.append(cbuf, 0, count);
	// }
	//
	// return stringbuf.toString();
	// }

	private <T> void validate(Class<? extends T> type, String source) throws ReaderException {
		if (type == null) {
			throw new ReaderException("Cannot read, type is null!");
		}
		if (StringUtil.isEmpty(source)) {
			throw new ReaderException("Source is empty!");
		}

		if (Transformer.isPrimitive(type)) {
			throw new ReaderException("Can not read primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
		}
	}

	private void readObject(Object instance, JSONObject jsonObj) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(instance);

		//this.readAttribute(instance, jsonObj, ms);
		//this.readValue(instance, jsonObj, ms);
		this.readElement(instance, jsonObj, ms);
	}

//	private void readAttribute(Object instance, JSONObject jsonObj, MappingSchema ms) throws Exception {
//		Map<String, ElementSchema> xml2AttributeSchemaMapping = ms.getXml2AttributeSchemaMapping();
//		for (String xmlName : xml2AttributeSchemaMapping.keySet()) {
//			Object jsonValue = jsonObj.opt("@" + xmlName);
//			if (jsonValue != null) {
//				if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
//					AttributeSchema as = xml2AttributeSchemaMapping.get(xmlName);
//					Field field = as.getField();
//					Object value = Transformer.read(String.valueOf(jsonValue), as.getFieldType());
//					field.set(instance, value);
//				}
//			}
//		}
//	}

//	private void readValue(Object instance, JSONObject jsonObj, MappingSchema ms) throws Exception {
//		ValueSchema vs = ms.getValueSchema();
//		if (vs != null) {
//			Object jsonValue = jsonObj.opt(JsonWriter.VALUE_KEY);
//			if (jsonValue != null) {
//				if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
//					Field field = vs.getField();
//					Object value = Transformer.read(String.valueOf(jsonValue), vs.getFieldType());
//					field.set(instance, value);
//				}
//			}
//		}
//	}

	private void readElement(Object instance, JSONObject jsonObj, MappingSchema ms) throws Exception {
		Map<String, ElementSchema> xml2SchemaMapping = ms.getXml2SchemaMapping();
		readElementInternal(instance, jsonObj, xml2SchemaMapping);
		Map<String, ElementSchema> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
		readElementInternal(instance, jsonObj, xmlWrapper2SchemaMapping);
	}

	private void readElementInternal(Object instance, JSONObject jsonObj, Map<String, ElementSchema> map) throws Exception {
		Class<?> type;
		for (String xmlName : map.keySet()) {
			Object jsonValue = jsonObj.opt(xmlName);
			if (jsonValue != null) {
				ElementSchema es = map.get(xmlName);
				Field field = es.getField();
				type = es.getFieldType();

				switch (es.getType()) {
				case LIST:
					// List
					if (jsonValue instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) jsonValue;
						if (jsonArray.length() > 0) {
							readList(instance, type, field, jsonArray);
						}
					}
					break;
				case SET:
					// Set
					if (jsonValue instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) jsonValue;
						if (jsonArray.length() > 0) {
							readSet(instance, type, field, jsonArray);
						}
					}

					break;
				case ARRAY:
					// Array
					if (jsonValue instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) jsonValue;
						if (jsonArray.length() > 0) {
							readArray(instance, type, field, jsonArray);
						}
					}
					break;
				case MAP:
					// Array
					if (jsonValue instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) jsonValue;
						if (jsonArray.length() > 0) {
							readMap(instance, type, field, jsonArray, es.getMapInfo());
						}
					}
					break;
				case ELEMENT:
					if (jsonValue instanceof JSONArray) {
						jsonValue = ((JSONArray) jsonValue).get(0);
					}
					if (Transformer.isPrimitive(type)) {
						if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
							Object value = Transformer.read(String.valueOf(jsonValue), type);
							field.set(instance, value);
						}
					} else if (jsonValue instanceof JSONObject) {
						Constructor<?> con = TypeReflector.getConstructor(type);
						Object subObj = con.newInstance();
						field.set(instance, subObj);
						this.readObject(subObj, (JSONObject) jsonValue);
					}
					break;
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readMap(Object instance, Class<?> type, Field field, JSONArray jsonArray, MapInfo mapInfo) throws Exception {
		Map map = (Map) field.get(instance);
		if (map == null) {
			map = new LinkedHashMap();
			field.set(instance, map);
		}
		Object objectValue;
		Object objectKey;
		Object value;
		Object key;
		Object entry;
		JSONObject jsonEntry;
		MappingSchema msKey;
		MappingSchema msValue;

		for (int i = 0; i < jsonArray.length(); i++) {
			entry = jsonArray.get(i);
			if (entry instanceof JSONObject) {
				jsonEntry = (JSONObject) entry;
				key = jsonEntry.get(mapInfo.keyName);

				if (Transformer.isPrimitive(key.getClass())) {
					// simple type
					objectKey = Transformer.read(String.valueOf(key), mapInfo.keyClazz);
				} else {
					objectKey = mapInfo.keyClazz.newInstance();
					msKey = MappingSchema.fromClass(mapInfo.keyClazz);
					readElementInternal(objectKey, (JSONObject) key, msKey.getField2SchemaMapping());
				}

				value = jsonEntry.opt(mapInfo.valueName);
				if (value == null) {
					objectValue = null;
				} else if (Transformer.isPrimitive(value.getClass())) {
					// simple type
					objectValue = Transformer.read(String.valueOf(value), mapInfo.valueClazz);
				} else {
					objectValue = mapInfo.valueClazz.newInstance();
					msValue = MappingSchema.fromClass(mapInfo.valueClazz);
					readElementInternal(objectValue, (JSONObject) value, msValue.getField2SchemaMapping());
					// key=read(instance, )
				}

				map.put(objectKey, objectValue);
				/*
				 * Constructor con = TypeReflector.getConstructor(type); subObj
				 * = con.newInstance(); Array.set(array, i, subObj);
				 * this.readObject(subObj, (JSONObject) jsonValue);
				 */
			} else {
				throw new MappingException("Badformat for map field " + field.getName() + " of class " + instance.getClass().getName());
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readSet(Object instance, Class<?> type, Field field, JSONArray jsonArray) throws Exception {
		if (Transformer.isPrimitive(type)) {
			for (int i = 0; i < jsonArray.length(); i++) {
				Object jsonValue = jsonArray.get(i);
				if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
					Object value = Transformer.read(String.valueOf(jsonValue), type);
					Set set = (Set) field.get(instance);
					if (set == null) {
						set = new LinkedHashSet();
						field.set(instance, set);
					}
					set.add(value);
				}
			}
		} else { // Object
			for (int i = 0; i < jsonArray.length(); i++) {
				Object jsonValue = jsonArray.get(i);
				if (jsonValue instanceof JSONObject) {
					Constructor con = TypeReflector.getConstructor(type);
					Object subObj = con.newInstance();
					Set set = (Set) field.get(instance);
					if (set == null) {
						set = new LinkedHashSet();
						field.set(instance, set);
					}
					set.add(subObj);
					this.readObject(subObj, (JSONObject) jsonValue);
				}
			}
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readList(Object instance, Class<?> type, Field field, JSONArray jsonArray) throws Exception {
		if (Transformer.isPrimitive(type)) {
			for (int i = 0; i < jsonArray.length(); i++) {
				Object jsonValue = jsonArray.get(i);
				if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
					Object value = Transformer.read(String.valueOf(jsonValue), type);
					List list = (List) field.get(instance);
					if (list == null) {
						list = new ArrayList();
						field.set(instance, list);
					}
					list.add(value);
				}
			}
		} else { // Object
			for (int i = 0; i < jsonArray.length(); i++) {
				Object jsonValue = jsonArray.get(i);
				if (jsonValue instanceof JSONObject) {
					Constructor con = TypeReflector.getConstructor(type);
					Object subObj = con.newInstance();
					List list = (List) field.get(instance);
					if (list == null) {
						list = new ArrayList();
						field.set(instance, list);
					}
					list.add(subObj);
					this.readObject(subObj, (JSONObject) jsonValue);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	private void readArray(Object instance, Class<?> type, Field field, JSONArray jsonArray) throws Exception {
		Object array = Array.newInstance(type, jsonArray.length());
		field.set(instance, array);

		Object subObj;
		Object jsonValue;
		for (int i = 0; i < jsonArray.length(); i++) {
			jsonValue = jsonArray.get(i);
			if (jsonValue instanceof JSONObject) {
				Constructor con = TypeReflector.getConstructor(type);
				subObj = con.newInstance();
				Array.set(array, i, subObj);
				this.readObject(subObj, (JSONObject) jsonValue);
			} else if (jsonValue instanceof JSONArray) {
				// TODO to implement
			} else {
				// (!(jsonValue instanceof JSONObject) && !()) {
				subObj = Transformer.read(String.valueOf(jsonValue), type);
				Array.set(array, i, subObj);

			}
		}
	}

}
