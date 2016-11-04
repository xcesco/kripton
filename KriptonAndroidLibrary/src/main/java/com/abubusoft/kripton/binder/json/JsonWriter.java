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
package com.abubusoft.kripton.binder.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.abubusoft.kripton.BinderJsonWriter;
import com.abubusoft.kripton.BinderOptions;
import com.abubusoft.kripton.binder.json.internal.JSONArray;
import com.abubusoft.kripton.binder.json.internal.JSONObject;
import com.abubusoft.kripton.binder.schema.ElementSchema;
import com.abubusoft.kripton.binder.schema.MappingSchema;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.exception.MappingException;
import com.abubusoft.kripton.exception.WriterException;

/**
 * <p>
 * BinderWriter implementation using org.json library,
 * </p>
 * 
 * <p>
 * JsonWriter serializes POJO into JSON string, the serialization is guided by mapping schema defined in the POJO using Nano annotations.
 * </p>
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
public class JsonWriter implements BinderJsonWriter {

	static final int DEFAULT_INDENTATION = 4;

	protected BinderOptions options;

	public JsonWriter() {
		this(BinderOptions.build());
	}

	public JsonWriter(BinderOptions options) {
		this.options = options;
	}

	private void writeObject(JSONObject jsonObject, Object source) throws Exception {
		MappingSchema ms = MappingSchema.fromObject(source);

		writeElements(jsonObject, source, ms);
	}

	private void writeElements(JSONObject jsonObject, Object source, MappingSchema ms) throws Exception {
		Map<String, ElementSchema> field2SchemaMapping = ms.getField2SchemaMapping();
		for (String fieldName : field2SchemaMapping.keySet()) {
			ElementSchema es = field2SchemaMapping.get(fieldName);
			if (es != null) {

				if (!es.getJsonInfo().enabled)
					continue;

				Field field = es.getField();
				Object value = field.get(source);
				if (value != null) {
					switch (es.getType()) {
					case LIST:
						writeElementList(jsonObject, value, es);
						break;
					case SET:
						writeElementSet(jsonObject, value, es);
						break;
					case ARRAY:
						writeElementArray(jsonObject, value, es);
						break;
					case MAP:
						writeElementMap(jsonObject, value, es);
						break;
					case ELEMENT:
						writeElement(jsonObject, value, es);
						break;
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void writeElementMap(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		Map<?, ?> map = (Map) source;
		if (map.size() > 0) {

			JSONArray jsonEntryArray = new JSONArray();
			JSONObject jsonEntry;

			String keyName = es.getMapInfo().keyName;
			String valueName = es.getMapInfo().valueName;

			for (Entry item : map.entrySet()) {
				jsonEntry = writeMapEntry(keyName, valueName, item);

				jsonEntryArray.put(jsonEntry);
			}

			String mapKey = es.getWrapperName();
			mapKey = mapKey == null ? es.getName() : mapKey;
			jsonObject.put(mapKey, jsonEntryArray);
		}

	}

	/**
	 * @param keyName
	 * @param valueName
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	JSONObject writeMapEntry(String keyName, String valueName, Entry item) throws Exception {
		JSONObject jsonEntry;
		Object jsonKey;
		Object jsonValue;
		Object key;
		Object value;
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
		jsonEntry.put(keyName, jsonKey);

		// value
		if (value != null) {
			if (Transformer.isPrimitive(value.getClass())) {
				jsonValue = getJSONValue(value, value.getClass());
			} else {
				MappingSchema msValue = MappingSchema.fromClass(value.getClass());
				jsonValue = new JSONObject();
				writeElements((JSONObject) jsonValue, value, msValue);
			}
			jsonEntry.put(valueName, jsonValue);
		}
		return jsonEntry;
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
		return String.valueOf(writeInternal(source));
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

	@Override
	public void writeCollection(Collection<?> source, Writer out) throws WriterException, MappingException {
		JSONArray array = new JSONArray();

		for (Object item : source) {
			array.put((Object) writeInternal(item));
		}

		try {
			out.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new WriterException(e);
		}

	}

	@Override
	public void writeCollection(Collection<?> source, OutputStream os) throws WriterException, MappingException {
		JSONArray array = new JSONArray();

		for (Object item : source) {
			array.put((Object) writeInternal(item));
		}

		write(array.toString(), os);

	}

	@Override
	public String writeCollection(Collection<?> source) throws WriterException, MappingException {
		JSONArray array = new JSONArray();

		for (Object item : source) {
			array.put((Object) writeInternal(item));
		}

		return array.toString();

	}

	private Object writeInternal(Object source) throws WriterException, MappingException {
		try {

			if (source == null) {
				// "Can not write null instance!");
				return null;
			}

			if (Transformer.isPrimitive(source.getClass())) {
				Class<?> type = source.getClass();

				// primitives
				Object jsonValue = getJSONValue(source, type);
				return jsonValue;
			}

			JSONObject childJsonObject = new JSONObject();
			writeObject(childJsonObject, source);
			if (this.options.isIndent()) {
				return childJsonObject;// .toString(DEFAULT_INDENTATION);
			} else {
				return childJsonObject;// .toString();
			}

		} catch (MappingException me) {
			throw me;
		} catch (IllegalArgumentException iae) {
			throw new WriterException("Entry validation failure", iae);
		} catch (Exception e) {
			throw new WriterException("Error to serialize object", e);
		}
	}

	protected boolean isList(Object obj) {
		if (obj == null)
			return false;

		if (List.class.isAssignableFrom(obj.getClass())) {
			return true;
		}

		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void writeMap(Map<?, ?> source, Writer out) throws WriterException, MappingException {
		JSONArray array = new JSONArray();

		try {
			for (Entry item : source.entrySet()) {
				array.put(writeMapEntry("key", "value", item));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			out.write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new WriterException(e);
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void writeMap(Map<?, ?> source, OutputStream os) throws WriterException, MappingException {
		JSONArray array = new JSONArray();

		try {
			for (Entry item : source.entrySet()) {
				array.put(writeMapEntry("key", "value", item));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		write(array.toString(), os);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String writeMap(Map<?, ?> source) throws WriterException, MappingException {
		JSONArray array = new JSONArray();

		try {
			for (Entry item : source.entrySet()) {
				array.put(writeMapEntry("key", "value", item));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return array.toString();
	}

}
