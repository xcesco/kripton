package com.abubusoft.kripton.binder.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Options;
import com.abubusoft.kripton.binder.json.internal.JSONArray;
import com.abubusoft.kripton.binder.json.internal.JSONObject;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;
import com.abubusoft.kripton.binder.schema.AttributeSchema;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.schema.ValueSchema;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * BinderWriter implementation using org.json library,
 * </p>
 * 
 * <p>
 * JsonWriter serializes POJO into JSON string, the serialization is guided by
 * mapping schema defined in the POJO using Nano annotations.
 * </p>
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
public class JsonWriter implements BinderWriter {

	static final String VALUE_KEY = "__value__";

	static final int DEFAULT_INDENTATION = 4;

	protected Options options;

	public JsonWriter() {
		this(Options.build());
	}

	public JsonWriter(Options options) {
		this.options = options;
	}

	public void write(Object source, Writer out) throws WriterException, MappingException {
		if (out == null) {
			throw new WriterException("Entry validation failure, Writer is null!");
		}

		String result = this.write(source);
		try {
			StringUtil.string2Writer(result, out);
		} catch (IOException e) {
			throw new WriterException("IO error", e);
		}

	}

	public void write(Object source, OutputStream os) throws WriterException, MappingException {

		if (os == null) {
			throw new WriterException("Entry validation failure, OutputStream is null!");
		}

		try {
			this.write(source, new OutputStreamWriter(os, options.getEncoding()));
		} catch (UnsupportedEncodingException e) {
			throw new WriterException("Error to serialize object", e);
		}
	}

	private void writeObject(JSONObject jsonObject, Object source) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(source);

		// write attributes first
		writeAttributes(jsonObject, source, ms);

		// write value if any
		writeValue(jsonObject, source, ms);

		// write elements last
		writeElements(jsonObject, source, ms);

	}

	private void writeAttributes(JSONObject jsonObject, Object source, MappingSchema ms) throws Exception {
		Map<String, AttributeSchema> field2AttributeSchemaMapping = ms.getField2AttributeSchemaMapping();
		for (String fieldName : field2AttributeSchemaMapping.keySet()) {
			AttributeSchema as = field2AttributeSchemaMapping.get(fieldName);
			Field field = as.getField();
			Object value = field.get(source);
			if (value != null) {
				String key = "@" + as.getName();
				Object jsonValue = this.getJSONValue(value, as.getFieldType());
				jsonObject.put(key, jsonValue);
			}
		}
	}

	private void writeValue(JSONObject jsonObject, Object source, MappingSchema ms) throws Exception {
		ValueSchema vs = ms.getValueSchema();
		if (vs == null)
			return; // no ValueSchema, do nothing

		Field field = vs.getField();
		Object value = field.get(source);
		if (value != null) {
			Object jsonValue = getJSONValue(value, vs.getFieldType());
			jsonObject.put(VALUE_KEY, jsonValue);
		}
	}

	private void writeElements(JSONObject jsonObject, Object source, MappingSchema ms) throws Exception {
		Map<String, Object> field2SchemaMapping = ms.getField2SchemaMapping();
		for (String fieldName : field2SchemaMapping.keySet()) {
			Object schemaObj = field2SchemaMapping.get(fieldName);
			if (schemaObj instanceof ElementSchema) {
				ElementSchema es = (ElementSchema) schemaObj;
				Field field = es.getField();
				Object value = field.get(source);
				if (value != null) {
					switch (es.getType()) {
					case LIST:
						this.writeElementList(jsonObject, value, es);
						break;
					case SET:
						this.writeElementSet(jsonObject, value, es);
						break;
					case ARRAY:
						this.writeElementArray(jsonObject, value, es);
						break;
					case MAP:
						this.writeElementMap(jsonObject, value, es);
						break;
					case DEFAULT:
					case CDATA:
						this.writeElement(jsonObject, value, es);
						break;
					}
				}
			}
		}
	}

	private void writeElementMap(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		@SuppressWarnings("rawtypes")
		Map<?, ?> map = (Map) source;
		if (map.size() > 0) {

			JSONArray jsonEntryArray = new JSONArray();
			JSONObject jsonEntry;
			Object jsonKey = null;
			Object jsonValue = null;
			Object key;
			Object value;

			for (@SuppressWarnings("rawtypes")
			Entry item : map.entrySet()) {
				jsonEntry = new JSONObject();

				key = item.getKey();
				value = item.getValue();

				// key
				if (Transformer.isPrimitive(key.getClass())) {
					jsonKey = getJSONValue(key, key.getClass());
				} else {
					MappingSchema msKey = MappingSchema.fromClass(key.getClass());
					jsonKey = new JSONObject();
					writeElements((JSONObject) jsonKey, key, msKey);
				}

				// value
				if (Transformer.isPrimitive(value.getClass())) {
					jsonValue = getJSONValue(value, value.getClass());
				} else {
					MappingSchema msValue = MappingSchema.fromClass(value.getClass());
					jsonValue = new JSONObject();
					writeElements((JSONObject) jsonValue, value, msValue);
				}

				jsonEntry.put("key", jsonKey);
				jsonEntry.put("value", jsonValue);

				jsonEntryArray.put(jsonEntry);
			}

			String mapKey = es.getWrapperName();
			mapKey = mapKey == null ? es.getName() : mapKey;
			jsonObject.put(mapKey, jsonEntryArray);
		}

	}

	private void writeElementSet(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		Set<?> set = (Set<?>) source;
		if (set.size() > 0) {
			// TODO wrapper
			// String key = es.getName();
			String key = es.getWrapperName();
			key = key == null ? es.getName() : key;

			JSONArray jsonArray = new JSONArray();
			jsonObject.put(key, jsonArray);
			for (Object value : set) {
				if (value == null)
					continue;

				Class<?> type = es.getFieldType();

				// primitives
				if (Transformer.isPrimitive(type)) {
					Object jsonValue = getJSONValue(value, type);

					jsonArray.put(jsonValue);

					continue;
				}

				// object
				JSONObject childJsonObject = new JSONObject();
				this.writeObject(childJsonObject, value);

				jsonArray.put(childJsonObject);
			}
		}

	}

	private void writeElementArray(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		int size = Array.getLength(source);

		if (size > 0) {
			// TODO wrapper
			// String key = es.getName();
			String key = es.getWrapperName();
			key = key == null ? es.getName() : key;

			JSONArray jsonArray = new JSONArray();
			jsonObject.put(key, jsonArray);
			Object value;

			for (int i = 0; i < size; i++) {
				value = Array.get(source, i);
				if (value == null)
					continue;

				Class<?> type = es.getFieldType();

				// primitives
				if (Transformer.isPrimitive(type)) {
					Object jsonValue = getJSONValue(value, type);
					jsonArray.put(jsonValue);
					continue;
				}

				// object
				JSONObject childJsonObject = new JSONObject();
				this.writeObject(childJsonObject, value);

				jsonArray.put(childJsonObject);
			}
		}

	}

	private void writeElementList(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		List<?> list = (List<?>) source;
		if (list.size() > 0) {
			// TODO wrapper
			// String key = es.getName();
			String key = es.getWrapperName();
			key = key == null ? es.getName() : key;

			JSONArray jsonArray = new JSONArray();
			jsonObject.put(key, jsonArray);
			for (Object value : list) {
				if (value == null)
					continue;

				Class<?> type = es.getFieldType();

				// primitives
				if (Transformer.isPrimitive(type)) {
					Object jsonValue = getJSONValue(value, type);

					jsonArray.put(jsonValue);

					continue;
				}

				// object
				JSONObject childJsonObject = new JSONObject();
				this.writeObject(childJsonObject, value);

				jsonArray.put(childJsonObject);
			}
		}
	}

	private void writeElement(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		Class<?> type = es.getFieldType();

		// primitives
		if (Transformer.isPrimitive(type)) {
			String key = es.getName();
			Object jsonValue = getJSONValue(source, type);

			jsonObject.put(key, jsonValue);

			return;
		}

		String key = es.getName();
		JSONObject childJsonObject = new JSONObject();
		jsonObject.put(key, childJsonObject);

		// object
		this.writeObject(childJsonObject, source);
	}

	private Object getJSONValue(Object value, Class<?> type) throws Exception {
		if (value instanceof Number || value instanceof Boolean) {
			return value;
		}
		String stringValue = Transformer.write(value, type);
		return stringValue;
	}

	@Override
	public String write(Object source) throws WriterException, MappingException {
		try {
			if (source == null) {
				throw new IllegalArgumentException("Can not write null instance!");
			}

			if (Transformer.isPrimitive(source.getClass())) {
				throw new IllegalArgumentException("Can not write primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
			}

			JSONObject childJsonObject = new JSONObject();
			writeObject(childJsonObject, source);
			if (this.options.isIndent()) {
				return childJsonObject.toString(DEFAULT_INDENTATION);
			} else {
				return childJsonObject.toString();
			}

		} catch (MappingException me) {
			throw me;
		} catch (IllegalArgumentException iae) {
			throw new WriterException("Entry validation failure", iae);
		} catch (Exception e) {
			throw new WriterException("Error to serialize object", e);
		}
	}

}
