package kripton70;

import java.io.IOException;

import kripton70.core.BinderGenerator;
import kripton70.core.BinderParser;
import kripton70.core.JsonMapper;

public final class BeanJsonMapper extends JsonMapper<Bean> {
    //private static final JsonMapper<Object> COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER = LoganSquare.mapperFor(Object.class);

    //private static TypeConverter<Date> java_util_Date_type_converter;	

	@Override
	public void parseField(BinderParser parser, Bean instance, String fieldName) throws IOException {
		switch(fieldName)
    	{
    	case "id":
    		instance.id=longMapper.parse(parser);
    		break;
    	case "description":
    		instance.description=stringMapper.parse(parser);
    		break;
    	case "valueByteType":
    		instance.valueByteType=byteMapper.parse(parser);
    		break;
    	case "valueShortType":
    		instance.valueShortType=shortMapper.parse(parser);
    		break;
    	case "valueCharType":
    		instance.valueCharType=characterMapper.parse(parser);
    		break;
    	case "valueBean":    		
    		instance.valueBean=binder.mapperFor(Bean.class).parse(parser);    		
    		break;
    	default:
    		break;
    	}
       /* if ("date".equals(fieldName)) {
            instance.date = getjava_util_Date_type_converter().parse(jsonParser);
        } else if ("intToIgnoreForSerialization".equals(fieldName)){
            instance.intToIgnoreForSerialization = jsonParser.getValueAsInt();
        } else if ("object-array-with-dashes".equals(fieldName)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                List<Object> collection1 = new ArrayList<Object>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    Object value1;
                    value1 = COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.parse(jsonParser);
                    collection1.add(value1);
                }
                Object[] array = collection1.toArray(new Object[collection1.size()]);
                instance.objectArrayWithDashes = array;
            } else {
                instance.objectArrayWithDashes = null;
            }
        } else if ("object-list-with-dashes".equals(fieldName)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                ArrayList<Object> collection1 = new ArrayList<Object>();
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    Object value1;
                    value1 = COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.parse(jsonParser);
                    collection1.add(value1);
                }
                instance.objectListWithDashes = collection1;
            } else {
                instance.objectListWithDashes = null;
            }
        } else if ("object_map".equals(fieldName)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                HashMap<String, Object> map1 = new HashMap<String, Object>();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String key1 = jsonParser.getText();
                    jsonParser.nextToken();
                    if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                        map1.put(key1, null);
                    } else {
                        map1.put(key1, COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.parse(jsonParser));
                    }
                }
                instance.objectMap = map1;
            } else {
                instance.objectMap = null;
            }
        } else if ("object-map-with-dashes".equals(fieldName)) {
            if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                HashMap<String, Object> map1 = new HashMap<String, Object>();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String key1 = jsonParser.getText();
                    jsonParser.nextToken();
                    if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                        map1.put(key1, null);
                    } else {
                        map1.put(key1, COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.parse(jsonParser));
                    }
                }
                instance.objectMapWithDashes = map1;
            } else {
                instance.objectMapWithDashes = null;
            }
        } else if ("string".equals(fieldName)){
            instance.string = jsonParser.getValueAsString(null);
        } else if ("test_double".equals(fieldName)){
            instance.testDouble = jsonParser.getValueAsDouble();
        } else if ("test_double_obj".equals(fieldName)){
            instance.testDoubleObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Double.valueOf(jsonParser.getValueAsDouble());
        } else if ("test_float".equals(fieldName)){
            instance.testFloat = (float)jsonParser.getValueAsDouble();
        } else if ("test_float_obj".equals(fieldName)){
            instance.testFloatObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : new Float(jsonParser.getValueAsDouble());
        } else if ("test_int".equals(fieldName)){
            instance.testInt = jsonParser.getValueAsInt();
        } else if ("test_int_obj".equals(fieldName)){
            instance.testIntObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Integer.valueOf(jsonParser.getValueAsInt());
        } else if ("test_long".equals(fieldName)){
            instance.testLong = jsonParser.getValueAsLong();
        } else if ("test_long_obj".equals(fieldName)){
            instance.testLongObj = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : Long.valueOf(jsonParser.getValueAsLong());
        } else if ("test_string".equals(fieldName)){
            instance.testString = jsonParser.getValueAsString(null);
        }*/
		
	}

	@Override
	public void serialize(Bean object, BinderGenerator generator, boolean writeStartAndEnd) throws IOException {
		if (writeStartAndEnd) {
			generator.writeStartObject();
    	}
    	
    	// field id
    	longMapper.serialize(object.id, "id", true, generator);
    	
    	// field description
    	if (object.description != null) {
            stringMapper.serialize(object.description, "description", true, generator);
        }
    	
    	// field valueByteType
		byteMapper.serialize(object.valueByteType,"valueByteType", true, generator);
		
		// field valueShortType
		shortMapper.serialize(object.valueShortType,"valueShortType", true, generator);
		
		// field valueCharType
		characterMapper.serialize(object.valueCharType,"valueCharType", true, generator);
		
		// field bean
		if (object.valueBean!=null)
		{						
			generator.writeFieldName("valueBean");
			binder.mapperFor(Bean.class).serialize(object.valueBean, generator, true);
		}		
    	
    	
    	if (writeStartAndEnd) {
    		generator.writeEndObject();
        }
    	
    	/*
        if (writeStartAndEnd) {
            jsonGenerator.writeStartObject();
        }
        if (object.date != null) {
            getjava_util_Date_type_converter().serialize(object.date, "date", true, jsonGenerator);
        }
        jsonGenerator.writeNumberField("intToIgnoreForParse", object.intToIgnoreForParse);
        final Object[] lslocalobject_array_with_dashes = object.objectArrayWithDashes;
        if (lslocalobject_array_with_dashes != null) {
            jsonGenerator.writeFieldName("object-array-with-dashes");
            jsonGenerator.writeStartArray();
            for (Object element1 : lslocalobject_array_with_dashes) {
                if (element1 != null) {
                    COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.serialize(element1, jsonGenerator, false);
                }
            }
            jsonGenerator.writeEndArray();
        }
        final List<Object> lslocalobject_list_with_dashes = object.objectListWithDashes;
        if (lslocalobject_list_with_dashes != null) {
            jsonGenerator.writeFieldName("object-list-with-dashes");
            jsonGenerator.writeStartArray();
            for (Object element1 : lslocalobject_list_with_dashes) {
                if (element1 != null) {
                    COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.serialize(element1, jsonGenerator, false);
                }
            }
            jsonGenerator.writeEndArray();
        }
        final Map<String, Object> lslocalobject_map = object.objectMap;
        if (lslocalobject_map != null) {
            jsonGenerator.writeFieldName("object_map");
            jsonGenerator.writeStartObject();
            for (Map.Entry<String, Object> entry1 : lslocalobject_map.entrySet()) {
                jsonGenerator.writeFieldName(entry1.getKey().toString());
                if (entry1.getValue() != null) {
                    COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.serialize(entry1.getValue(), jsonGenerator, false);
                }
            }
            jsonGenerator.writeEndObject();
        }
        final Map<String, Object> lslocalobject_map_with_dashes = object.objectMapWithDashes;
        if (lslocalobject_map_with_dashes != null) {
            jsonGenerator.writeFieldName("object-map-with-dashes");
            jsonGenerator.writeStartObject();
            for (Map.Entry<String, Object> entry1 : lslocalobject_map_with_dashes.entrySet()) {
                jsonGenerator.writeFieldName(entry1.getKey().toString());
                if (entry1.getValue() != null) {
                    COM_BLUELINELABS_LOGANSQUARE_INTERNAL_OBJECTMAPPERS_OBJECTMAPPER.serialize(entry1.getValue(), jsonGenerator, false);
                }
            }
            jsonGenerator.writeEndObject();
        }
        if (object.string != null) {
            jsonGenerator.writeStringField("string", object.string);
        }
        jsonGenerator.writeNumberField("test_double", object.testDouble);
        if (object.testDoubleObj != null) {
            jsonGenerator.writeNumberField("test_double_obj", object.testDoubleObj);
        }
        jsonGenerator.writeNumberField("test_float", object.testFloat);
        if (object.testFloatObj != null) {
            jsonGenerator.writeNumberField("test_float_obj", object.testFloatObj);
        }
        jsonGenerator.writeNumberField("test_int", object.testInt);
        if (object.testIntObj != null) {
            jsonGenerator.writeNumberField("test_int_obj", object.testIntObj);
        }
        jsonGenerator.writeNumberField("test_long", object.testLong);
        if (object.testLongObj != null) {
            jsonGenerator.writeNumberField("test_long_obj", object.testLongObj);
        }
        if (object.testString != null) {
            jsonGenerator.writeStringField("test_string", object.testString);
        }
        if (writeStartAndEnd) {
            jsonGenerator.writeEndObject();
        }*/
		
	}

	@Override
	protected Bean createInstance() {
		return new Bean();
	}

    /*
    private static final TypeConverter<Date> getjava_util_Date_type_converter() {
        if (java_util_Date_type_converter == null) {
            java_util_Date_type_converter = LoganSquare.typeConverterFor(Date.class);
        }
        return java_util_Date_type_converter;
    }*/
}