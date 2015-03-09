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
import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.binder.json.internal.JSONArray;
import com.abubusoft.kripton.binder.json.internal.JSONObject;
import com.abubusoft.kripton.binder.schema.AttributeSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.schema.RootElementSchema;
import com.abubusoft.kripton.binder.schema.ValueSchema;
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

			MappingSchema ms = MappingSchema.fromObject(instance);
			RootElementSchema res = ms.getRootElementSchema();
			String rootName = res.getName();

			Object jsonValue = jsonObj.opt(rootName);

			// se dobbiamo considerare solo i figli dell'elemento root, andiamo
			// avanti.
			if (res.isOnlyChildren()) {
				if (jsonValue == null) {
					jsonValue = jsonObj;
				}
			} else {
				if (jsonValue == null) {
					throw new ReaderException("Root name mismatch, can not find root name : " + rootName + " in the json string!");
				}
			}

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

		this.readAttribute(instance, jsonObj, ms);
		this.readValue(instance, jsonObj, ms);
		this.readElement(instance, jsonObj, ms);
	}

	private void readAttribute(Object instance, JSONObject jsonObj, MappingSchema ms) throws Exception {
		Map<String, AttributeSchema> xml2AttributeSchemaMapping = ms.getXml2AttributeSchemaMapping();
		for (String xmlName : xml2AttributeSchemaMapping.keySet()) {
			Object jsonValue = jsonObj.opt("@" + xmlName);
			if (jsonValue != null) {
				if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
					AttributeSchema as = xml2AttributeSchemaMapping.get(xmlName);
					Field field = as.getField();
					Object value = Transformer.read(String.valueOf(jsonValue), field.getType());
					field.set(instance, value);
				}
			}
		}
	}

	private void readValue(Object instance, JSONObject jsonObj, MappingSchema ms) throws Exception {
		ValueSchema vs = ms.getValueSchema();
		if (vs != null) {
			Object jsonValue = jsonObj.opt(JsonWriter.VALUE_KEY);
			if (jsonValue != null) {
				if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
					Field field = vs.getField();
					Object value = Transformer.read(String.valueOf(jsonValue), field.getType());
					field.set(instance, value);
				}
			}
		}
	}

	private void readElement(Object instance, JSONObject jsonObj, MappingSchema ms) throws Exception {
		Map<String, Object> xml2SchemaMapping = ms.getXml2SchemaMapping();
		readElementInternal(instance, jsonObj, xml2SchemaMapping);
		Map<String, Object> xmlWrapper2SchemaMapping = ms.getXmlWrapper2SchemaMapping();
		readElementInternal(instance, jsonObj, xmlWrapper2SchemaMapping);
	}

	private void readElementInternal(Object instance, JSONObject jsonObj, Map<String, Object> map) throws Exception {
		for (String xmlName : map.keySet()) {
			Object jsonValue = jsonObj.opt(xmlName);
			if (jsonValue != null) {
				Object schema = map.get(xmlName);
				if (schema instanceof ElementSchema) {
					ElementSchema es = (ElementSchema) schema;
					Field field = es.getField();
					if (es.isList()) {
						// List
						Class<?> type = es.getParameterizedType();
						if (jsonValue instanceof JSONArray) {
							JSONArray jsonArray = (JSONArray) jsonValue;
							if (jsonArray.length() > 0) {
								readList(instance, type, field, jsonArray);
							}
						}
					} else if (es.isArray() && es.getParameterizedType() != Byte.TYPE) {
						// Array
						Class<?> type = es.getParameterizedType();
						if (jsonValue instanceof JSONArray) {
							JSONArray jsonArray = (JSONArray) jsonValue;
							if (jsonArray.length() > 0) {
								readArray(instance, type, field, jsonArray);
							}
						}
					} else {
						if (jsonValue instanceof JSONArray) {
							jsonValue = ((JSONArray) jsonValue).get(0);
						}
						Class<?> type = field.getType();
						if (Transformer.isPrimitive(type)) {
							if (!(jsonValue instanceof JSONObject) && !(jsonValue instanceof JSONArray)) {
								Object value = Transformer.read(String.valueOf(jsonValue), field.getType());
								field.set(instance, value);
							}
						} else if (jsonValue instanceof JSONObject) {
							Constructor<?> con = TypeReflector.getConstructor(type);
							Object subObj = con.newInstance();
							field.set(instance, subObj);
							this.readObject(subObj, (JSONObject) jsonValue);
						}
					}
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
				//TODO to implement
			} else {
				// (!(jsonValue instanceof JSONObject) && !()) {
				subObj = Transformer.read(String.valueOf(jsonValue), type);
				Array.set(array, i, subObj);

			}
		}
	}

}
