package com.abubusoft.kripton.android.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.abubusoft.kripton.binder.BinderWriter;
import com.abubusoft.kripton.binder.Format;
import com.abubusoft.kripton.binder.annotation.schema.AttributeSchema;
import com.abubusoft.kripton.binder.annotation.schema.ElementSchema;
import com.abubusoft.kripton.binder.annotation.schema.MappingSchema;
import com.abubusoft.kripton.binder.annotation.schema.RootElementSchema;
import com.abubusoft.kripton.binder.annotation.schema.ValueSchema;
import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.WriterException;
import com.abubusoft.kripton.binder.transform.Transformer;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>BinderWriter implementation using org.json library,</p>
 * 
 * <p>JsonWriter serializes POJO into JSON string, the serialization is guided by
 * mapping schema defined in the POJO using Nano annotations.</p>
 * 
 * @author bulldog
 * @author xcesco
 * 
 */
public class JsonWriter implements BinderWriter {

	static final String VALUE_KEY = "__value__";

	static final int DEFAULT_INDENTATION = 4;

	private Format format;

	public JsonWriter() {
		this(new Format());
	}

	public JsonWriter(Format format) {
		this.format = format;
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
			this.write(source, new OutputStreamWriter(os, format.getEncoding()));
		} catch (UnsupportedEncodingException e) {
			throw new WriterException("Error to serialize object", e);
		}
	}

	// private void string2Writer(String source, Writer out) throws IOException
	// {
	//
	// char[] buffer = source.toCharArray();
	// for(int i = 0; i < buffer.length; i++) {
	// out.append(buffer[i]);
	// }
	// out.flush();
	// }

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
				Object jsonValue = this.getJSONValue(value, field.getType());
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
			Object jsonValue = getJSONValue(value, field.getType());
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
					if (es.isList()) {
						this.writeElementList(jsonObject, value, es);
					} else if (es.isArray() && es.getParameterizedType()!=Byte.TYPE) {
						this.writeElementArray(jsonObject, value, es);
					} else {
						this.writeElement(jsonObject, value, es);
					}
				}
			}
		}
	}

	private void writeElementArray(JSONObject jsonObject, Object source, ElementSchema es) throws Exception {
		Object[] list = (Object[]) source;
		if (list.length > 0) {
			//TODO wrapper
			//String key = es.getName();
			String key = es.getWrapperName();
			key=key==null? es.getName(): key;
			
			JSONArray jsonArray = new JSONArray();
			jsonObject.put(key, jsonArray);
			for (Object value : list) {
				if (value == null)
					continue;

				Class<?> type = es.getParameterizedType();
				
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
			//TODO wrapper
			//String key = es.getName();
			String key = es.getWrapperName();
			key=key==null? es.getName(): key;
			
			JSONArray jsonArray = new JSONArray();
			jsonObject.put(key, jsonArray);
			for (Object value : list) {
				if (value == null)
					continue;

				Class<?> type = es.getParameterizedType();

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
		Class<?> type = es.getField().getType();

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

			JSONObject jsonObject = new JSONObject();

			MappingSchema ms = MappingSchema.fromObject(source);
			RootElementSchema res = ms.getRootElementSchema();

			JSONObject childJsonObject = new JSONObject();
			jsonObject.put(res.getName(), childJsonObject);

			this.writeObject(childJsonObject, source);

			if (res.isOnlyChildren()) {
				// dobbiamo ignorare il root
				if (this.format.isIndent()) {
					return childJsonObject.toString(DEFAULT_INDENTATION);
				} else {
					return childJsonObject.toString();
				}
			} else {
				// dobbiamo scrivere tutto
				if (this.format.isIndent()) {
					return jsonObject.toString(DEFAULT_INDENTATION);
				} else {
					return jsonObject.toString();
				}
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
