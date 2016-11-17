package kripton70;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

import kripton70.contexts.JacksonContext;
import kripton70.contexts.XmlBinderContext;
import kripton70.core.AbstractMapper;
import kripton70.persistence.JacksonParser;
import kripton70.persistence.JacksonSerializer;
import kripton70.persistence.XmlParser;
import kripton70.persistence.XmlSerializer;

import org.robolectric.res.XmlLoader.XmlContext;

import com.abubusoft.kripton.android.annotation.BindMapper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonToken;

@BindMapper
public final class BeanKriptonMapper extends AbstractMapper<Bean> {

	/*
	 * if ("date".equals(fieldName)) { instance.date = getjava_util_Date_type_converter().parse(jsonParser); } else if ("intToIgnoreForSerialization".equals(fieldName)){ instance.intToIgnoreForSerialization = jsonParser.getValueAsInt(); }
	 * else if ("object-array-with-dashes".equals(fieldName)) { if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) { List<Object> collection1 = new ArrayList<Object>(); while (jsonParser.nextToken() != JsonToken.END_ARRAY) { Object
	 * value1; value1 = COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. parse(jsonParser); collection1.add(value1); } Object[] array = collection1.toArray(new Object[collection1.size()]); instance.objectArrayWithDashes =
	 * array; } else { instance.objectArrayWithDashes = null; } } else if ("object-list-with-dashes".equals(fieldName)) { if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) { ArrayList<Object> collection1 = new ArrayList<Object>();
	 * while (jsonParser.nextToken() != JsonToken.END_ARRAY) { Object value1; value1 = COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. parse(jsonParser); collection1.add(value1); } instance.objectListWithDashes =
	 * collection1; } else { instance.objectListWithDashes = null; } } else if ("object_map".equals(fieldName)) { if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) { HashMap<String, Object> map1 = new HashMap<String, Object>();
	 * while (jsonParser.nextToken() != JsonToken.END_OBJECT) { String key1 = jsonParser.getText(); jsonParser.nextToken(); if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) { map1.put(key1, null); } else { map1.put(key1,
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. parse(jsonParser)); } } instance.objectMap = map1; } else { instance.objectMap = null; } } else if ("object-map-with-dashes".equals(fieldName)) { if
	 * (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) { HashMap<String, Object> map1 = new HashMap<String, Object>(); while (jsonParser.nextToken() != JsonToken.END_OBJECT) { String key1 = jsonParser.getText();
	 * jsonParser.nextToken(); if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) { map1.put(key1, null); } else { map1.put(key1, COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. parse(jsonParser)); } }
	 * instance.objectMapWithDashes = map1; } else { instance.objectMapWithDashes = null; } } else if ("string".equals(fieldName)){ instance.string = jsonParser.getValueAsString(null); } else if ("test_double".equals(fieldName)){
	 * instance.testDouble = jsonParser.getValueAsDouble(); } else if ("test_double_obj".equals(fieldName)){ instance.testDoubleObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null :
	 * Double.valueOf(jsonParser.getValueAsDouble()); } else if ("test_float".equals(fieldName)){ instance.testFloat = (float)jsonParser.getValueAsDouble(); } else if ("test_float_obj".equals(fieldName)){ instance.testFloatObj =
	 * jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : new Float(jsonParser.getValueAsDouble()); } else if ("test_int".equals(fieldName)){ instance.testInt = jsonParser.getValueAsInt(); } else if
	 * ("test_int_obj".equals(fieldName)){ instance.testIntObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Integer.valueOf(jsonParser.getValueAsInt()); } else if ("test_long".equals(fieldName)){ instance.testLong =
	 * jsonParser.getValueAsLong(); } else if ("test_long_obj".equals(fieldName)){ instance.testLongObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Long.valueOf(jsonParser.getValueAsLong()); } else if
	 * ("test_string".equals(fieldName)){ instance.testString = jsonParser.getValueAsString(null); }
	 */
	// }

	// public void parseField(Bean instance, String fieldName, BinderParser
	// parser)

	@Override
	public Bean createInstance() {
		return new Bean();
	}

	@Override
	public void serialize(JacksonContext context, Bean object, JacksonSerializer jacksonSerializer, boolean writeStartAndEnd) {
		try {
			if (writeStartAndEnd)
				jacksonSerializer.writeStartObject();

			// field id
			jacksonSerializer.writeFieldName("id");
			jacksonSerializer.writeNumber(object.id);

			// field bean
			if (object.valueBean != null) {
				jacksonSerializer.writeFieldName("bean");
				context.mapperFor(Bean.class).serialize(context, object.valueBean, jacksonSerializer, true);
			}

			if (writeStartAndEnd)
				jacksonSerializer.writeEndObject();

		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}

	@Override
	public void serializeOnlyText(JacksonContext context, Bean object, JacksonSerializer jacksonSerializer, boolean writeStartAndEnd) {
		try {
			if (writeStartAndEnd)
				jacksonSerializer.writeStartObject();

			// field id
			jacksonSerializer.writeFieldName("id");
			jacksonSerializer.writeString(String.valueOf(object.id));

			// field bean
			if (object.valueBean != null) {
				jacksonSerializer.writeFieldName("bean");
				context.mapperFor(Bean.class).serialize(context, object.valueBean, jacksonSerializer, true);
			}

			if (writeStartAndEnd)
				jacksonSerializer.writeEndObject();

		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}

	@Override
	public void serialize(XmlBinderContext context, Bean object, XmlSerializer xmlSerializer, boolean writeStartAndEnd) {
		try {
			if (writeStartAndEnd)
				xmlSerializer.writeStartObject("bean");

			// field id
			xmlSerializer.writeStartElement("id");
			xmlSerializer.writeNumber(object.id);
			xmlSerializer.writeEndElement();

			// field bean
			if (object.valueBean != null) {
				xmlSerializer.writeStartElement("bean");
				context.mapperFor(Bean.class).serialize(context, object.valueBean, xmlSerializer, false);
				xmlSerializer.writeEndElement();
			}

			if (writeStartAndEnd)
				xmlSerializer.writeEndObject();

		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

	}

	@Override
	public Bean parse(JacksonContext context, JacksonParser jacksonParser, boolean writeStartAndEnd) {
		try {
			jacksonParser.nextToken();

			Bean instance = createInstance();
			if (jacksonParser.getCurrentToken() == null) {
				jacksonParser.nextToken();
			}
			if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
				jacksonParser.skipChildren();
				return instance;
			}
			while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
				String fieldName = jacksonParser.getCurrentName();
				jacksonParser.nextToken();
				// field management
				switch (fieldName) {
				case "id":
					instance.id = jacksonParser.getLongValue();
					break;
				case "description":
					instance.description = jacksonParser.getText();
					break;
				case "valueByteType":
					instance.valueByteType = jacksonParser.getByteValue();
					break;
				case "valueShortType":
					instance.valueShortType = jacksonParser.getShortValue();
					break;
				case "valueCharType":
					instance.valueCharType = jacksonParser.getCharValue();
					break;
				case "valueBean":
					instance.valueBean = context.mapperFor(Bean.class).parse(context, jacksonParser);
					break;
				case "valueStringList":
					if (jacksonParser.getCurrentToken() == JsonToken.START_ARRAY) {
						ArrayList<String> collection = new ArrayList<String>();
						while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
							collection.add(jacksonParser.getText());
						}
						instance.valueStringList = collection;
					}
					break;
				case "valueStringArray":
					if (jacksonParser.getCurrentToken() == JsonToken.START_ARRAY) {
						ArrayList<String> collection = new ArrayList<String>();
						while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
							collection.add(jacksonParser.getText());
						}
						instance.valueStringArray = collection.toArray(new String[collection.size()]);
					}
					break;
				default:
					jacksonParser.skipChildren();
				}
			}
			return instance;
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	@Override
	public Bean parseOnlyText(JacksonContext context, JacksonParser jacksonParser, boolean writeStartAndEnd) {
		try {
			jacksonParser.nextToken();

			Bean instance = createInstance();
			if (jacksonParser.getCurrentToken() == null) {
				jacksonParser.nextToken();
			}
			if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
				jacksonParser.skipChildren();
				return instance;
			}
			while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
				String fieldName = jacksonParser.getCurrentName();
				jacksonParser.nextToken();
				// field management
				switch (fieldName) {
				case "id":
					instance.id = Long.valueOf(jacksonParser.getText());
					break;
				case "description":
					instance.description = jacksonParser.getText();
					break;
				case "valueByteType":
					instance.valueByteType = Byte.valueOf(jacksonParser.getByteValue());
					break;
				case "valueShortType":
					instance.valueShortType = Short.valueOf(jacksonParser.getShortValue());
					break;
				case "valueCharType":
					instance.valueCharType = Character.valueOf(jacksonParser.getCharValue());
					break;
				case "valueBean":
					instance.valueBean = context.mapperFor(Bean.class).parseOnlyText(context, jacksonParser, false);
					break;
				case "valueStringList":
					if (jacksonParser.getCurrentToken() == JsonToken.START_ARRAY) {
						ArrayList<String> collection = new ArrayList<String>();
						while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
							collection.add(jacksonParser.getText());
						}
						instance.valueStringList = collection;
					}
					break;
				case "valueStringArray":
					if (jacksonParser.getCurrentToken() == JsonToken.START_ARRAY) {
						ArrayList<String> collection = new ArrayList<String>();
						while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
							collection.add(jacksonParser.getText());
						}
						instance.valueStringArray = collection.toArray(new String[collection.size()]);
					}
					break;
				default:
					jacksonParser.skipChildren();
					break;
				}
			}
			
			
			return instance;
		} catch (IOException e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

	}

	@Override
	public Bean parse(XmlBinderContext context, XmlParser xmlParser, boolean writeStartAndEnd) {
		// TODO Auto-generated method stub
		return null;

	}

	// @Override
	// public void visit(BinderContext context, Bean object, JacksonSerializer jacksonSerializer, boolean writeStartAndEnd, boolean onlyText) {
	// if (writeStartAndEnd) {
	// jacksonSerializer.writeStartObject("bean");
	// }

	/*
	 * 
	 * // field id jacksonSerializer.writeAttribute("id", true, object.id); //longMapper.serialize(serializer, true, "id", object.id);
	 * 
	 * // field description if (object.description != null) { stringMapper.serialize(jacksonSerializer, true, "description", object.description); }
	 * 
	 * // field valueByteType byteMapper.serialize(jacksonSerializer, true, "valueByteType", object.valueByteType);
	 * 
	 * // field valueShortType shortMapper.serialize(jacksonSerializer, true, "valueShortType", object.valueShortType);
	 * 
	 * // field valueCharType characterMapper.serialize(serializer, true, "valueCharType", object.valueCharType);
	 * 
	 * // field bean if (object.valueBean != null) { serializer.writeFieldName("valueBean"); context.mapperFor(Bean.class).serialize(context, object.valueBean, serializer, true); }
	 * 
	 * // field string list if (object.valueStringList != null) { //serializer.writeFieldName("valueStringList"); serializer.writeStartArray("valueStringList");
	 * 
	 * for (int i = 0; i < object.valueStringList.size(); i++) { stringMapper.serialize(serializer, false, null, object.valueStringList.get(i)); } serializer.writeEndArray(); }
	 * 
	 * // field string array if (object.valueStringArray != null) { //serializer.writeFieldName("valueStringArray"); serializer.writeStartArray("valueStringArray");
	 * 
	 * for (int i = 0; i < object.valueStringArray.length; i++) { stringMapper.serialize(serializer, false, null, object.valueStringArray[i]); } serializer.writeEndArray(); }
	 * 
	 * // field string map if (object.valueStringMap != null) { Map<String, String> map = object.valueStringMap; //serializer.writeFieldName("valueStringMap"); serializer.writeStartArray("valueStringMap"); for (Map.Entry<String, String>
	 * entry : map.entrySet()) { serializer.writeStartObject();
	 * 
	 * serializer.writeFieldName("key"); stringMapper.serialize(serializer, false, null, entry.getKey()); if (entry.getValue() != null) { serializer.writeFieldName("value"); stringMapper.serialize(serializer, false, null, entry.getValue());
	 * }
	 * 
	 * serializer.writeEndObject(); } serializer.writeEndArray(); }
	 * 
	 * if (writeStartAndEnd) { serializer.writeEndObject(); }
	 */

	/*
	 * if (writeStartAndEnd) { jsonGenerator.writeStartObject(); } if (object.date != null) { getjava_util_Date_type_converter().serialize(object.date, "date", true, jsonGenerator); } jsonGenerator.writeNumberField("intToIgnoreForParse",
	 * object.intToIgnoreForParse); final Object[] lslocalobject_array_with_dashes = object.objectArrayWithDashes; if (lslocalobject_array_with_dashes != null) { jsonGenerator.writeFieldName("object-array-with-dashes");
	 * jsonGenerator.writeStartArray(); for (Object element1 : lslocalobject_array_with_dashes) { if (element1 != null) { COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. serialize(element1, jsonGenerator, false); } }
	 * jsonGenerator.writeEndArray(); } final List<Object> lslocalobject_list_with_dashes = object.objectListWithDashes; if (lslocalobject_list_with_dashes != null) { jsonGenerator.writeFieldName("object-list-with-dashes");
	 * jsonGenerator.writeStartArray(); for (Object element1 : lslocalobject_list_with_dashes) { if (element1 != null) { COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. serialize(element1, jsonGenerator, false); } }
	 * jsonGenerator.writeEndArray(); } final Map<String, Object> lslocalobject_map = object.objectMap; if (lslocalobject_map != null) { jsonGenerator.writeFieldName("object_map"); jsonGenerator.writeStartObject(); for (Map.Entry<String,
	 * Object> entry1 : lslocalobject_map.entrySet()) { jsonGenerator.writeFieldName(entry1.getKey().toString()); if (entry1.getValue() != null) { COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * serialize(entry1.getValue(), jsonGenerator, false); } } jsonGenerator.writeEndObject(); } final Map<String, Object> lslocalobject_map_with_dashes = object.objectMapWithDashes; if (lslocalobject_map_with_dashes != null) {
	 * jsonGenerator.writeFieldName("object-map-with-dashes"); jsonGenerator.writeStartObject(); for (Map.Entry<String, Object> entry1 : lslocalobject_map_with_dashes.entrySet()) { jsonGenerator.writeFieldName(entry1.getKey().toString());
	 * if (entry1.getValue() != null) { COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER. serialize(entry1.getValue(), jsonGenerator, false); } } jsonGenerator.writeEndObject(); } if (object.string != null) {
	 * jsonGenerator.writeStringField("string", object.string); } jsonGenerator.writeNumberField("test_double", object.testDouble); if (object.testDoubleObj != null) { jsonGenerator.writeNumberField("test_double_obj", object.testDoubleObj);
	 * } jsonGenerator.writeNumberField("test_float", object.testFloat); if (object.testFloatObj != null) { jsonGenerator.writeNumberField("test_float_obj", object.testFloatObj); } jsonGenerator.writeNumberField("test_int", object.testInt);
	 * if (object.testIntObj != null) { jsonGenerator.writeNumberField("test_int_obj", object.testIntObj); } jsonGenerator.writeNumberField("test_long", object.testLong); if (object.testLongObj != null) {
	 * jsonGenerator.writeNumberField("test_long_obj", object.testLongObj); } if (object.testString != null) { jsonGenerator.writeStringField("test_string", object.testString); } if (writeStartAndEnd) { jsonGenerator.writeEndObject(); }
	 */
	// }

	/*
	 * private static final TypeConverter<Date> getjava_util_Date_type_converter() { if (java_util_Date_type_converter == null) { java_util_Date_type_converter = LoganSquare.typeConverterFor(Date.class); } return
	 * java_util_Date_type_converter; }
	 */
}