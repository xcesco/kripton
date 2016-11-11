package kripton70;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kripton70.typeconverters.ByteConverter;
import kripton70.typeconverters.CharacterConverter;
import kripton70.typeconverters.DoubleConverter;
import kripton70.typeconverters.FloatConverter;
import kripton70.typeconverters.IntegerConverter;
import kripton70.typeconverters.LongConverter;
import kripton70.typeconverters.ShortConverter;
import kripton70.typeconverters.StringConverter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public abstract class JsonMapper<E> {
	
	protected static ByteConverter byteMapper=new ByteConverter();
	protected static CharacterConverter characterMapper=new CharacterConverter();
	protected static ShortConverter shortMapper=new ShortConverter();	
	protected static IntegerConverter integerMapper=new IntegerConverter();
	protected static FloatConverter floatMapper=new FloatConverter();
	protected static DoubleConverter doubleMapper=new DoubleConverter();	
	protected static LongConverter longMapper=new LongConverter();	
	protected static StringConverter stringMapper=new StringConverter();

	 /**
     * Parse an object from a pre-configured JsonParser object.
     *
     * @param jsonParser The pre-configured JsonParser
     */
	public abstract E parse(JsonParser jsonParser) throws IOException;
	
	/**
     * Parse a single field from a pre-configured JsonParser object into a T instance.
     *
     * @param instance The instance of the object that the JsonParser should parse into
     * @param fieldName The name of the field that should be parsed
     * @param jsonParser The pre-configured JsonParser
     */
	public abstract void parseField(E instance, String fieldName, JsonParser jsonParser) throws IOException;
	
	/**
     * Serialize an object to a pre-configured JsonGenerator object.
     *
     * @param object The object to serialize.
     * @param generator The pre-configured JsonGenerator being written to.
     * @param writeStartAndEnd True if writeStartObject() should be called before and writeEndObject() should be called after serializing. False if not.
     */
	public abstract void serialize(E object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException;
	
	 /**
     * Parse an object from an InputStream.
     *
     * @param is The InputStream, most likely from your networking library.
     */
    public E parse(InputStream is) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(is);
        jsonParser.nextToken();
        return parse(jsonParser);
    }
    
    /**
     * Parse an object from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
     *
     * @param byteArray The byte array being parsed.
     */
    public E parse(byte[] byteArray) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(byteArray);
        jsonParser.nextToken();
        return parse(jsonParser);
    }

    /**
     * Parse an object from a char array. Note: parsing from an InputStream should be preferred over parsing from a char array if possible.
     *
     * @param charArray The char array being parsed.
     */
    public E parse(char[] charArray) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(charArray);
        jsonParser.nextToken();
        return parse(jsonParser);
    }

    /**
     * Parse an object from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
     *
     * @param jsonString The JSON string being parsed.
     */
    public E parse(String jsonString) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(jsonString);
        jsonParser.nextToken();
        return parse(jsonParser);
    }
    
    /**
     * Parse a list of objects from an InputStream.
     *
     * @param is The inputStream, most likely from your networking library.
     */
    public List<E> parseList(InputStream is) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(is);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
     *
     * @param byteArray The inputStream, most likely from your networking library.
     */
    public List<E> parseList(byte[] byteArray) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(byteArray);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a char array. Note: parsing from an InputStream should be preferred over parsing from a char array if possible.
     *
     * @param charArray The char array, most likely from your networking library.
     */
    public List<E> parseList(char[] charArray) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(charArray);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
     *
     * @param jsonString The JSON string being parsed.
     */
    public List<E> parseList(String jsonString) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(jsonString);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a JsonParser.
     *
     * @param jsonParser The JsonParser, preconfigured to be at the START_ARRAY token.
     */
    public List<E> parseList(JsonParser jsonParser) throws IOException {
        List<E> list = new ArrayList<>();
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                list.add(parse(jsonParser));
            }
        }
        return list;
    }

    /**
     * Parse a map of objects from an InputStream.
     *
     * @param is The inputStream, most likely from your networking library.
     */
    public Map<String, E> parseMap(InputStream is) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(is);
        jsonParser.nextToken();
        return parseMap(jsonParser);
    }

    /**
     * Parse a map of objects from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
     *
     * @param byteArray The byte array string being parsed.
     */
    public Map<String, E> parseMap(byte[] byteArray) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(byteArray);
        jsonParser.nextToken();
        return parseMap(jsonParser);
    }

    /**
     * Parse a map of objects from a char array. Note: parsing from an InputStream should be preferred over parsing from a char array if possible.
     *
     * @param charArray The char array being parsed.
     */
    public Map<String, E> parseMap(char[] charArray) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(charArray);
        jsonParser.nextToken();
        return parseMap(jsonParser);
    }

    /**
     * Parse a map of objects from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
     *
     * @param jsonString The JSON string being parsed.
     */
    public Map<String, E> parseMap(String jsonString) throws IOException {
        JsonParser jsonParser = KriptonLibrary2.JSON_FACTORY.createParser(jsonString);
        jsonParser.nextToken();
        return parseMap(jsonParser);
    }

    /**
     * Parse a map of objects from a JsonParser.
     *
     * @param jsonParser The JsonParser, preconfigured to be at the START_ARRAY token.
     */
    public Map<String, E> parseMap(JsonParser jsonParser) throws IOException {
        HashMap<String, E> map = new HashMap<String, E>();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String key = jsonParser.getText();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
                map.put(key, null);
            } else{
                map.put(key, parse(jsonParser));
            }
        }
        return map;
    }
    
    /**
     * Serialize an object to a JSON String.
     *
     * @param object The object to serialize.
     */
    public String serialize(E object) throws IOException {
        StringWriter sw = new StringWriter();
        
        JsonFactory factory = KriptonLibrary2.JSON_FACTORY;       
        JsonGenerator jsonGenerator = factory.createGenerator(sw);
        serialize(object, jsonGenerator, true);
        jsonGenerator.close();
        return sw.toString();
    }

    /**
     * Serialize an object to an OutputStream.
     *
     * @param object The object to serialize.
     * @param os The OutputStream being written to.
     */
    public void serialize(E object, OutputStream os) throws IOException {
        JsonGenerator jsonGenerator = KriptonLibrary2.JSON_FACTORY.createGenerator(os);
        serialize(object, jsonGenerator, true);
        jsonGenerator.close();
    }

    /**
     * Serialize a list of objects to a JSON String.
     *
     * @param list The list of objects to serialize.
     */
    public String serialize(List<E> list) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGenerator = KriptonLibrary2.JSON_FACTORY.createGenerator(sw);
        serialize(list, jsonGenerator);
        jsonGenerator.close();
        return sw.toString();
    }

    /**
     * Serialize a list of objects to an OutputStream.
     *
     * @param list The list of objects to serialize.
     * @param os The OutputStream to which the list should be serialized
     */
    public void serialize(List<E> list, OutputStream os) throws IOException {
        JsonGenerator jsonGenerator = KriptonLibrary2.JSON_FACTORY.createGenerator(os);
        serialize(list, jsonGenerator);
        jsonGenerator.close();
    }

    /**
     * Serialize a list of objects to a JsonGenerator.
     *
     * @param list The list of objects to serialize.
     * @param jsonGenerator The JsonGenerator to which the list should be serialized
     */
    public void serialize(List<E> list, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (E object : list) {
            if (object != null) {
                serialize(object, jsonGenerator, true);
            } else {
                jsonGenerator.writeNull();
            }
        }
        jsonGenerator.writeEndArray();
    }
}
