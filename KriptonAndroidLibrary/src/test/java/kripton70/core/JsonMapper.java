package kripton70.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import kripton70.Bean;

import com.fasterxml.jackson.core.JsonToken;

public abstract class JsonMapper<T> extends AbstractMapper {

	 /**
     * Parse an object from a pre-configured JsonParser object.
     *
     * @param jsonParser The pre-configured JsonParser
     */
	public T parse(BinderParser parser) throws IOException {
		T instance = createInstance();
        if (parser.getCurrentToken() == null) {
        	parser.nextToken();
        }
        if (parser.getCurrentToken() != JsonToken.START_OBJECT) {
        	parser.skipChildren();
            return null;
        }
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = parser.getCurrentName();
            parser.nextToken();
            parseField(parser, instance, fieldName);
            parser.skipChildren();
        }
        return instance;
	}

	protected abstract T createInstance();
	
	/**
     * Parse a single field from a pre-configured JsonParser object into a T instance.
     *
	 * @param parser The pre-configured JsonParser
	 * @param instance The instance of the object that the JsonParser should parse into
	 * @param fieldName The name of the field that should be parsed
	 * @throws IOException
	 */
	public abstract void parseField(BinderParser parser, T instance, String fieldName) throws IOException;

	public abstract void serialize(T object, BinderGenerator generator, boolean writeStartAndEnd) throws IOException;

	 /**
     * Parse an object from an InputStream.
     *
     * @param is The InputStream, most likely from your networking library.
     */
    public T parse(InputStream is) throws IOException {
    	return binder.parse(is);
    }

    /**
     * Parse an object from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
     *
     * @param byteArray The byte array being parsed.
     */
    public T parse(byte[] byteArray) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(byteArray);
        jsonParser.nextToken();
        return parse(jsonParser);
    }

    /**
     * Parse an object from a char array. Note: parsing from an InputStream should be preferred over parsing from a char array if possible.
     *
     * @param charArray The char array being parsed.
     */
    public T parse(char[] charArray) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(charArray);
        jsonParser.nextToken();
        return parse(jsonParser);
    }

    /**
     * Parse an object from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
     *
     * @param jsonString The JSON string being parsed.
     */
    public T parse(String jsonString) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(jsonString);
        jsonParser.nextToken();
        return parse(jsonParser);
    }

    /**
     * Parse a list of objects from an InputStream.
     *
     * @param is The inputStream, most likely from your networking library.
     */
    public List<T> parseList(InputStream is) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(is);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
     *
     * @param byteArray The inputStream, most likely from your networking library.
     */
    public List<T> parseList(byte[] byteArray) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(byteArray);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a char array. Note: parsing from an InputStream should be preferred over parsing from a char array if possible.
     *
     * @param charArray The char array, most likely from your networking library.
     */
    public List<T> parseList(char[] charArray) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(charArray);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
     *
     * @param jsonString The JSON string being parsed.
     */
    public List<T> parseList(String jsonString) throws IOException {
        JsonParser jsonParser = LoganSquare.JSON_FACTORY.createParser(jsonString);
        jsonParser.nextToken();
        return parseList(jsonParser);
    }

    /**
     * Parse a list of objects from a JsonParser.
     *
     * @param jsonParser The JsonParser, preconfigured to be at the START_ARRAY token.
     */
    public List<T> parseList(JsonParser jsonParser) throws IOException {
        List<T> list = new ArrayList<>();
        if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                list.add(parse(jsonParser));
            }
        }
        return list;
    }

    /**
     * Serialize an object to a JSON String.
     *
     * @param object The object to serialize.
     */
    public String serialize(T object) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGenerator = LoganSquare.JSON_FACTORY.createGenerator(sw);
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
    public void serialize(T object, OutputStream os) throws IOException {
        JsonGenerator jsonGenerator = LoganSquare.JSON_FACTORY.createGenerator(os);
        serialize(object, jsonGenerator, true);
        jsonGenerator.close();
    }

    /**
     * Serialize a list of objects to a JSON String.
     *
     * @param list The list of objects to serialize.
     */
    public String serialize(List<T> list) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGenerator = LoganSquare.JSON_FACTORY.createGenerator(sw);
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
    public void serialize(List<T> list, OutputStream os) throws IOException {
        JsonGenerator jsonGenerator = LoganSquare.JSON_FACTORY.createGenerator(os);
        serialize(list, jsonGenerator);
        jsonGenerator.close();
    }

    /**
     * Serialize a list of objects to a JsonGenerator.
     *
     * @param list The list of objects to serialize.
     * @param jsonGenerator The JsonGenerator to which the list should be serialized
     */
    public void serialize(List<T> list, BinderGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStartArray();
        for (T object : list) {
            if (object != null) {
                serialize(object, jsonGenerator, true);
            } else {
                jsonGenerator.writeNull();
            }
        }
        jsonGenerator.writeEndArray();
    }
	
}
