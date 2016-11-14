package kripton70.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonToken;

import kripton70.Bean;

public abstract class JacksonMapper<E> extends AbstractMapper<E> {

	/**
	 * Parse a single field from a pre-configured JsonParser object into a T
	 * instance.
	 *
	 * @param instance
	 *            The instance of the object that the JsonParser should parse
	 *            into
	 * @param fieldName
	 *            The name of the field that should be parsed
	 * @param parser
	 *            The pre-configured JsonParser
	 */
	public abstract void parseField(BinderContext context, E instance, String fieldName, BinderParser parser) throws IOException;

	/**
	 * Serialize an object to a pre-configured JsonGenerator object.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param generator
	 *            The pre-configured JsonGenerator being written to.
	 * @param writeStartAndEnd
	 *            True if writeStartObject() should be called before and
	 *            writeEndObject() should be called after serializing. False if
	 *            not.
	 */
	public abstract void serialize(BinderContext context, E object, BinderGenerator generator, boolean writeStartAndEnd) throws IOException;

	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 */
	public E parse(BinderContext context, InputStream is) throws IOException {
		BinderParser jsonParser = context.createParser(is);
		jsonParser.nextToken();
		return parse(context, jsonParser);
	}

	/**
	 * Parse an object from a byte array. Note: parsing from an InputStream
	 * should be preferred over parsing from a byte array if possible.
	 *
	 * @param byteArray
	 *            The byte array being parsed.
	 */
	public E parse(BinderContext context, byte[] byteArray) throws IOException {
		BinderParser jsonParser = context.createParser(byteArray);
		jsonParser.nextToken();
		return parse(context, jsonParser);
	}

	/**
	 * Parse an object from a char array. Note: parsing from an InputStream
	 * should be preferred over parsing from a char array if possible.
	 *
	 * @param charArray
	 *            The char array being parsed.
	 */
	public E parse(BinderContext context, char[] charArray) throws IOException {
		BinderParser jsonParser = context.createParser(charArray);
		jsonParser.nextToken();
		return parse(context, jsonParser);
	}

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should
	 * be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 */
	public E parse(BinderContext context, String jsonString) throws IOException {
		BinderParser jsonParser = context.createParser(jsonString);
		jsonParser.nextToken();
		return parse(context, jsonParser);
	}

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(BinderContext context, InputStream is) throws IOException {
		BinderParser jsonParser = context.createParser(is);
		jsonParser.nextToken();
		return parseList(context, jsonParser);
	}

	/**
	 * Parse a list of objects from a byte array. Note: parsing from an
	 * InputStream should be preferred over parsing from a byte array if
	 * possible.
	 *
	 * @param byteArray
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(BinderContext context, byte[] byteArray) throws IOException {
		BinderParser jsonParser = context.createParser(byteArray);
		jsonParser.nextToken();
		return parseList(context, jsonParser);
	}

	/**
	 * Parse a list of objects from a char array. Note: parsing from an
	 * InputStream should be preferred over parsing from a char array if
	 * possible.
	 *
	 * @param charArray
	 *            The char array, most likely from your networking library.
	 */
	public List<E> parseList(BinderContext context, char[] charArray) throws IOException {
		BinderParser jsonParser = context.createParser(charArray);
		jsonParser.nextToken();
		return parseList(context, jsonParser);
	}

	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream
	 * should be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 */
	public List<E> parseList(BinderContext context, String jsonString) throws IOException {
		BinderParser jsonParser = context.createParser(jsonString);
		jsonParser.nextToken();
		return parseList(context, jsonParser);
	}

	/**
	 * Parse a list of objects from a JsonParser.
	 *
	 * @param jsonParser
	 *            The JsonParser, preconfigured to be at the START_ARRAY token.
	 */
	public List<E> parseList(BinderContext context, BinderParser jsonParser) throws IOException {
		List<E> list = new ArrayList<>();
		if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
			while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
				list.add(parse(context, jsonParser));
			}
		}
		return list;
	}


	/**
	 * Serialize an object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param os
	 *            The OutputStream being written to.
	 */
	public void serialize(BinderContext context, E object, OutputStream os) throws IOException {
		BinderGenerator jsonGenerator = context.createGenerator(os);
		serialize(context, object, jsonGenerator, true);
		jsonGenerator.close();
	}

	/**
	 * Serialize a list of objects to a JSON String.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 */
	public String serialize(BinderContext context, List<E> list) throws IOException {
		StringWriter sw = new StringWriter();
		BinderGenerator jsonGenerator = context.createGenerator(sw);
		serialize(context, list, jsonGenerator);
		jsonGenerator.close();
		return sw.toString();
	}

	/**
	 * Serialize a list of objects to an OutputStream.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param os
	 *            The OutputStream to which the list should be serialized
	 */
	public void serialize(BinderContext context, List<E> list, OutputStream os) throws IOException {
		BinderGenerator jsonGenerator = context.createGenerator(os);
		serialize(context, list, jsonGenerator);
		jsonGenerator.close();
	}

	/**
	 * Serialize a list of objects to a JsonGenerator.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param jsonGenerator
	 *            The JsonGenerator to which the list should be serialized
	 */
	public void serialize(BinderContext context, List<E> list, BinderGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeStartArray();
		for (E object : list) {
			if (object != null) {
				serialize(context, object, jsonGenerator, true);
			} else {
				jsonGenerator.writeNull();
			}
		}
		jsonGenerator.writeEndArray();
	}

	/**
	 * Parse an object from a pre-configured JsonParser object.
	 *
	 * @param jsonParser
	 *            The pre-configured JsonParser
	 */
	public E parse(BinderContext context, BinderParser parser) throws IOException {
		E instance = createInstance(); 
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
			parseField(context, instance, fieldName, parser);
			parser.skipChildren();
		}
		return instance;
	}

	protected abstract E createInstance();

	/**
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	public String serialize(BinderContext context, E object) throws IOException {
		StringWriter sw = new StringWriter();

		BinderGenerator jsonGenerator = context.createGenerator(sw);
		serialize(context, object, jsonGenerator, true);
		jsonGenerator.close();
		return sw.toString();
	}
}
