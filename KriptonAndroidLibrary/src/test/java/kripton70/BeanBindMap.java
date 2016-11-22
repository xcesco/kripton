package kripton70;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.ximpleware.extended.xpath.parser;

@BindMap
public final class BeanBindMap extends AbstractMapper<Bean> {

	/*
	 * if ("date".equals(fieldName)) { instance.date =
	 * getjava_util_Date_type_converter().parse(jsonParser); } else if
	 * ("intToIgnoreForSerialization".equals(fieldName)){
	 * instance.intToIgnoreForSerialization = jsonParser.getValueAsInt(); } else
	 * if ("object-array-with-dashes".equals(fieldName)) { if
	 * (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) { List<Object>
	 * collection1 = new ArrayList<Object>(); while (jsonParser.nextToken() !=
	 * JsonToken.END_ARRAY) { Object value1; value1 =
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * parse(jsonParser); collection1.add(value1); } Object[] array =
	 * collection1.toArray(new Object[collection1.size()]);
	 * instance.objectArrayWithDashes = array; } else {
	 * instance.objectArrayWithDashes = null; } } else if
	 * ("object-list-with-dashes".equals(fieldName)) { if
	 * (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
	 * ArrayList<Object> collection1 = new ArrayList<Object>(); while
	 * (jsonParser.nextToken() != JsonToken.END_ARRAY) { Object value1; value1 =
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * parse(jsonParser); collection1.add(value1); }
	 * instance.objectListWithDashes = collection1; } else {
	 * instance.objectListWithDashes = null; } } else if
	 * ("object_map".equals(fieldName)) { if (jsonParser.getCurrentToken() ==
	 * JsonToken.START_OBJECT) { HashMap<String, Object> map1 = new
	 * HashMap<String, Object>(); while (jsonParser.nextToken() !=
	 * JsonToken.END_OBJECT) { String key1 = jsonParser.getText();
	 * jsonParser.nextToken(); if (jsonParser.getCurrentToken() ==
	 * JsonToken.VALUE_NULL) { map1.put(key1, null); } else { map1.put(key1,
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * parse(jsonParser)); } } instance.objectMap = map1; } else {
	 * instance.objectMap = null; } } else if
	 * ("object-map-with-dashes".equals(fieldName)) { if
	 * (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
	 * HashMap<String, Object> map1 = new HashMap<String, Object>(); while
	 * (jsonParser.nextToken() != JsonToken.END_OBJECT) { String key1 =
	 * jsonParser.getText(); jsonParser.nextToken(); if
	 * (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) { map1.put(key1,
	 * null); } else { map1.put(key1,
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * parse(jsonParser)); } } instance.objectMapWithDashes = map1; } else {
	 * instance.objectMapWithDashes = null; } } else if
	 * ("string".equals(fieldName)){ instance.string =
	 * jsonParser.getValueAsString(null); } else if
	 * ("test_double".equals(fieldName)){ instance.testDouble =
	 * jsonParser.getValueAsDouble(); } else if
	 * ("test_double_obj".equals(fieldName)){ instance.testDoubleObj =
	 * jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null :
	 * Double.valueOf(jsonParser.getValueAsDouble()); } else if
	 * ("test_float".equals(fieldName)){ instance.testFloat =
	 * (float)jsonParser.getValueAsDouble(); } else if
	 * ("test_float_obj".equals(fieldName)){ instance.testFloatObj =
	 * jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : new
	 * Float(jsonParser.getValueAsDouble()); } else if
	 * ("test_int".equals(fieldName)){ instance.testInt =
	 * jsonParser.getValueAsInt(); } else if ("test_int_obj".equals(fieldName)){
	 * instance.testIntObj = jsonParser.getCurrentToken() ==
	 * JsonToken.VALUE_NULL ? null :
	 * Integer.valueOf(jsonParser.getValueAsInt()); } else if
	 * ("test_long".equals(fieldName)){ instance.testLong =
	 * jsonParser.getValueAsLong(); } else if
	 * ("test_long_obj".equals(fieldName)){ instance.testLongObj =
	 * jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null :
	 * Long.valueOf(jsonParser.getValueAsLong()); } else if
	 * ("test_string".equals(fieldName)){ instance.testString =
	 * jsonParser.getValueAsString(null); }
	 */
	// }

	// public void parseField(Bean instance, String fieldName, BinderParser
	// parser)

	@Override
	public Bean createInstance() {
		return new Bean();
	}

	@Override
	public void serializeOnJackson(JacksonContext context, Bean object, JacksonWrapperSerializer wrapper) {

		try {
			JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;

			// write begin object
			jacksonSerializer.writeStartObject();

			// field id
			jacksonSerializer.writeNumberField("id", object.id);

			// field name
			jacksonSerializer.writeStringField("name", object.name);

			// field valueByteType
			jacksonSerializer.writeNumberField("valueByteType", object.valueByteType);

			// field valueCharType
			jacksonSerializer.writeStringField("valueCharType", Character.toString(object.valueCharType));

			// field valueShortType
			jacksonSerializer.writeNumberField("valueShortType", object.valueShortType);

			// field valueIntType
			jacksonSerializer.writeNumberField("valueIntType", object.valueIntType);

			// field valueLongType
			jacksonSerializer.writeNumberField("valueLongType", object.valueLongType);

			// field valueFloatType
			jacksonSerializer.writeNumberField("valueFloatType", object.valueFloatType);

			// field valueDoubleType
			jacksonSerializer.writeNumberField("valueDoubleType", object.valueDoubleType);

			// field valueString
			if (object.valueString != null)
				jacksonSerializer.writeStringField("valueString", StringEscapeUtils.escapeXml10(object.valueString));

			// field valueByte
			if (object.valueByte != null)
				jacksonSerializer.writeNumberField("valueByteType", object.valueByte);

			// field valueChar
			if (object.valueChar != null)
				jacksonSerializer.writeStringField("valueCharType", Character.toString(object.valueChar));

			// field valueShort
			if (object.valueShort != null)
				jacksonSerializer.writeNumberField("valueShortType", object.valueShort);

			// field valueInt
			if (object.valueInt != null)
				jacksonSerializer.writeNumberField("valueIntType", object.valueInt);

			// field valueLong
			if (object.valueLong != null)
				jacksonSerializer.writeNumberField("valueLongType", object.valueLong);

			// field valueFloat
			if (object.valueFloat != null)
				jacksonSerializer.writeNumberField("valueFloatType", object.valueFloat);

			// field valueDouble
			if (object.valueDouble != null)
				jacksonSerializer.writeNumberField("valueDoubleType", object.valueDouble);

			// field bean
			if (object.valueBean != null) {
				jacksonSerializer.writeFieldName("bean");
				context.mapperFor(Bean.class).serializeOnJackson(context, object.valueBean, wrapper);
			}

			// write end object
			jacksonSerializer.writeEndObject();

		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}

	@Override
	public void serializeOnJacksonAsString(JacksonContext context, Bean object, JacksonWrapperSerializer wrapper) {
		try {
			JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
			// write begin object
			jacksonSerializer.writeStartObject();

			// field id
			jacksonSerializer.writeFieldName("id");
			jacksonSerializer.writeString(String.valueOf(object.id));

			// field bean
			if (object.valueBean != null) {
				jacksonSerializer.writeFieldName("bean");
				context.mapperFor(Bean.class).serializeOnJacksonAsString(context, object.valueBean, wrapper);
			}

			// write end object
			jacksonSerializer.writeEndObject();

		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}

	@Override
	public void serializeOnXml(XmlBinderContext context, Bean object, XmlWrapperSerializer wrapper, int currentEventType) {
		try {
			XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;

			if (currentEventType == 0)
				xmlSerializer.writeStartElement("bean");

			// field id (attribute)
			xmlSerializer.writeAttribute("id", String.valueOf(object.id));

			// field name (attribute)
			xmlSerializer.writeAttribute("name", StringEscapeUtils.escapeXml10(String.valueOf(object.name)));

			// field valueByteType
			xmlSerializer.writeStartElement("valueByteType");
			xmlSerializer.writeLong(object.valueByteType);
			xmlSerializer.writeEndElement();

			// field valueByte
			if (object.valueByte != null) {
				xmlSerializer.writeStartElement("valueByte");
				xmlSerializer.writeInt(object.valueByte);
				xmlSerializer.writeEndElement();
			}

			// field bean
			if (object.valueBean != null) {
				xmlSerializer.writeStartElement("valueBean");
				context.mapperFor(Bean.class).serializeOnXml(context, object.valueBean, wrapper, 1);
				xmlSerializer.writeEndElement();
			}

			// field content
			if (object.content != null) {
				// xmlSerializer.writeStartElement("valueString");
				xmlSerializer.writeCData(StringEscapeUtils.escapeXml10(object.content));
				// xmlSerializer.writeCharacters(object.valueString);
				// xmlSerializer.writeEndElement();
			}

			if (currentEventType == 0)
				xmlSerializer.writeEndElement();

		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

	}

	@Override
	public Bean parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
		try {
			JsonParser jacksonParser = wrapper.jacksonParser;
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
				case "valueString":
					instance.valueString = jacksonParser.getText();
					break;

				case "valueByteType":
					instance.valueByteType = jacksonParser.getByteValue();
					break;
				case "valueCharType":
					instance.valueCharType = jacksonParser.getText().charAt(0);
					break;
				case "valueShortType":
					instance.valueShortType = jacksonParser.getShortValue();
					break;
				case "valueIntType":
					instance.valueIntType = jacksonParser.getIntValue();
					break;
				case "valueLongType":
					instance.valueLongType = jacksonParser.getLongValue();
					break;
				case "valueFloatType":
					instance.valueFloatType = jacksonParser.getFloatValue();
					break;
				case "valueDoubleType":
					instance.valueDoubleType = jacksonParser.getDoubleValue();
					break;

				case "valueByte":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueByte = jacksonParser.getByteValue();
					break;
				case "valueChar":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueChar = jacksonParser.getText().charAt(0);
					break;
				case "valueShort":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueShort = jacksonParser.getShortValue();
					break;
				case "valueInt":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueInt = jacksonParser.getIntValue();
					break;
				case "valueLong":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueLong = jacksonParser.getLongValue();
					break;
				case "valueFloat":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueFloat = jacksonParser.getFloatValue();
					break;
				case "valueDouble":
					if (jacksonParser.getCurrentToken() != JsonToken.VALUE_NULL)
						instance.valueDouble = jacksonParser.getDoubleValue();
					break;

				case "valueBean":
					instance.valueBean = context.mapperFor(Bean.class).parseOnJackson(context, wrapper);
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
	public Bean parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
		try {
			JsonParser jacksonParser = wrapper.jacksonParser;
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
					instance.valueString = jacksonParser.getText();
					break;
				case "valueByteType":
					instance.valueByteType = Byte.valueOf(jacksonParser.getByteValue());
					break;
				case "valueShortType":
					instance.valueShortType = Short.valueOf(jacksonParser.getShortValue());
					break;
				case "valueCharType":
					instance.valueCharType = Character.valueOf(jacksonParser.getText().charAt(0));
					break;
				case "valueBean":
					instance.valueBean = context.mapperFor(Bean.class).parseOnJacksonAsString(context, wrapper);
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
	public Bean parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
		try {			
			XMLStreamReader2 xmlParser = wrapper.xmlParser;
			Bean instance = createInstance();
			int eventType = currentEventType;
			String currentTag = null;

			if (currentEventType == 0) {
				eventType = xmlParser.next();
			}

			currentTag = xmlParser.getName().toString();
			
			Stack<String> elementNameStack = new Stack<>();
			elementNameStack.push(currentTag);
			
			// attributes
			String attributeName = null;
			String attributeValue = null;
			int attributes = xmlParser.getAttributeCount();
			for (int i = 0; i < attributes; i++) {
				System.out.println(String.format("tag %s attribute %s value '%s'", currentTag, xmlParser.getAttributeLocalName(i), StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(i))));
				attributeName = xmlParser.getAttributeLocalName(i);
				attributeValue = StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(i));

				// attributes
				switch (attributeName) {
				case "id":
					instance.id = Long.valueOf(attributeValue);
					break;
				case "name":
					instance.name = attributeValue;
					break;
				default:
					break;
				}
			}

			// sub-elements
			while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
				eventType = xmlParser.next();
				switch (eventType) {
				case XMLEvent.START_ELEMENT:
					// start and inner bean
					currentTag = xmlParser.getName().toString();

					elementNameStack.push(currentTag);

					switch (currentTag) {
					case "valueBean":
						// field valueBean (mapper manage end_element too)
						instance.valueBean = context.mapperFor(Bean.class).parseOnXml(context, wrapper, eventType);
						currentTag = elementNameStack.pop();
						break;
					default:
						break;
					}

					break;
				case XMLEvent.END_ELEMENT:
					currentTag = elementNameStack.pop();

					switch (currentTag) {
					case "id":
						if (!xmlParser.isEmptyElement()) {
							instance.id = Integer.valueOf(xmlParser.getElementText());
						}
						break;
					default:
						break;
					}

					break;
				case XMLEvent.CDATA:
				case XMLEvent.CHARACTERS:
					
					if (elementNameStack.size()==1)
					{
						// element values
						System.out.println(String.format("field %s value '%s'", "context", StringEscapeUtils.unescapeXml(xmlParser.getText())));

						// field content
						instance.content = StringEscapeUtils.unescapeXml(xmlParser.getText());
					}
					break;
				default:
					break;

				}
			}

			return instance;
		} catch (XMLStreamException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}

	}

	// @Override
	// public void visit(BinderContext context, Bean object, JacksonSerializer
	// jacksonSerializer, boolean writeStartAndEnd, boolean onlyText) {
	// if (writeStartAndEnd) {
	// jacksonSerializer.writeStartObject("bean");
	// }

	/*
	 * 
	 * // field id jacksonSerializer.writeAttribute("id", true, object.id);
	 * //longMapper.serialize(serializer, true, "id", object.id);
	 * 
	 * // field description if (object.description != null) {
	 * stringMapper.serialize(jacksonSerializer, true, "description",
	 * object.description); }
	 * 
	 * // field valueByteType byteMapper.serialize(jacksonSerializer, true,
	 * "valueByteType", object.valueByteType);
	 * 
	 * // field valueShortType shortMapper.serialize(jacksonSerializer, true,
	 * "valueShortType", object.valueShortType);
	 * 
	 * // field valueCharType characterMapper.serialize(serializer, true,
	 * "valueCharType", object.valueCharType);
	 * 
	 * // field bean if (object.valueBean != null) {
	 * serializer.writeFieldName("valueBean");
	 * context.mapperFor(Bean.class).serialize(context, object.valueBean,
	 * serializer, true); }
	 * 
	 * // field string list if (object.valueStringList != null) {
	 * //serializer.writeFieldName("valueStringList");
	 * serializer.writeStartArray("valueStringList");
	 * 
	 * for (int i = 0; i < object.valueStringList.size(); i++) {
	 * stringMapper.serialize(serializer, false, null,
	 * object.valueStringList.get(i)); } serializer.writeEndArray(); }
	 * 
	 * // field string array if (object.valueStringArray != null) {
	 * //serializer.writeFieldName("valueStringArray");
	 * serializer.writeStartArray("valueStringArray");
	 * 
	 * for (int i = 0; i < object.valueStringArray.length; i++) {
	 * stringMapper.serialize(serializer, false, null,
	 * object.valueStringArray[i]); } serializer.writeEndArray(); }
	 * 
	 * // field string map if (object.valueStringMap != null) { Map<String,
	 * String> map = object.valueStringMap;
	 * //serializer.writeFieldName("valueStringMap");
	 * serializer.writeStartArray("valueStringMap"); for (Map.Entry<String,
	 * String> entry : map.entrySet()) { serializer.writeStartObject();
	 * 
	 * serializer.writeFieldName("key"); stringMapper.serialize(serializer,
	 * false, null, entry.getKey()); if (entry.getValue() != null) {
	 * serializer.writeFieldName("value"); stringMapper.serialize(serializer,
	 * false, null, entry.getValue()); }
	 * 
	 * serializer.writeEndObject(); } serializer.writeEndArray(); }
	 * 
	 * if (writeStartAndEnd) { serializer.writeEndObject(); }
	 */

	/*
	 * if (writeStartAndEnd) { jsonGenerator.writeStartObject(); } if
	 * (object.date != null) {
	 * getjava_util_Date_type_converter().serialize(object.date, "date", true,
	 * jsonGenerator); } jsonGenerator.writeNumberField("intToIgnoreForParse",
	 * object.intToIgnoreForParse); final Object[]
	 * lslocalobject_array_with_dashes = object.objectArrayWithDashes; if
	 * (lslocalobject_array_with_dashes != null) {
	 * jsonGenerator.writeFieldName("object-array-with-dashes");
	 * jsonGenerator.writeStartArray(); for (Object element1 :
	 * lslocalobject_array_with_dashes) { if (element1 != null) {
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * serialize(element1, jsonGenerator, false); } }
	 * jsonGenerator.writeEndArray(); } final List<Object>
	 * lslocalobject_list_with_dashes = object.objectListWithDashes; if
	 * (lslocalobject_list_with_dashes != null) {
	 * jsonGenerator.writeFieldName("object-list-with-dashes");
	 * jsonGenerator.writeStartArray(); for (Object element1 :
	 * lslocalobject_list_with_dashes) { if (element1 != null) {
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * serialize(element1, jsonGenerator, false); } }
	 * jsonGenerator.writeEndArray(); } final Map<String, Object>
	 * lslocalobject_map = object.objectMap; if (lslocalobject_map != null) {
	 * jsonGenerator.writeFieldName("object_map");
	 * jsonGenerator.writeStartObject(); for (Map.Entry<String, Object> entry1 :
	 * lslocalobject_map.entrySet()) {
	 * jsonGenerator.writeFieldName(entry1.getKey().toString()); if
	 * (entry1.getValue() != null) {
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * serialize(entry1.getValue(), jsonGenerator, false); } }
	 * jsonGenerator.writeEndObject(); } final Map<String, Object>
	 * lslocalobject_map_with_dashes = object.objectMapWithDashes; if
	 * (lslocalobject_map_with_dashes != null) {
	 * jsonGenerator.writeFieldName("object-map-with-dashes");
	 * jsonGenerator.writeStartObject(); for (Map.Entry<String, Object> entry1 :
	 * lslocalobject_map_with_dashes.entrySet()) {
	 * jsonGenerator.writeFieldName(entry1.getKey().toString()); if
	 * (entry1.getValue() != null) {
	 * COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.
	 * serialize(entry1.getValue(), jsonGenerator, false); } }
	 * jsonGenerator.writeEndObject(); } if (object.string != null) {
	 * jsonGenerator.writeStringField("string", object.string); }
	 * jsonGenerator.writeNumberField("test_double", object.testDouble); if
	 * (object.testDoubleObj != null) {
	 * jsonGenerator.writeNumberField("test_double_obj", object.testDoubleObj);
	 * } jsonGenerator.writeNumberField("test_float", object.testFloat); if
	 * (object.testFloatObj != null) {
	 * jsonGenerator.writeNumberField("test_float_obj", object.testFloatObj); }
	 * jsonGenerator.writeNumberField("test_int", object.testInt); if
	 * (object.testIntObj != null) {
	 * jsonGenerator.writeNumberField("test_int_obj", object.testIntObj); }
	 * jsonGenerator.writeNumberField("test_long", object.testLong); if
	 * (object.testLongObj != null) {
	 * jsonGenerator.writeNumberField("test_long_obj", object.testLongObj); } if
	 * (object.testString != null) {
	 * jsonGenerator.writeStringField("test_string", object.testString); } if
	 * (writeStartAndEnd) { jsonGenerator.writeEndObject(); }
	 */
	// }

}