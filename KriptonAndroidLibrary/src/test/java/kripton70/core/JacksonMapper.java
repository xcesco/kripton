package kripton70.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import kripton70.contexts.BinderContext;
import kripton70.contexts.BinderSerializer;
import kripton70.persistence.JacksonParser;
import kripton70.persistence.JacksonSerializer;
import kripton70.persistence.Visitable;
import kripton70.persistence.Visitor;
import kripton70.typeconverters.ByteConverter;
import kripton70.typeconverters.CharacterConverter;
import kripton70.typeconverters.DoubleConverter;
import kripton70.typeconverters.FloatConverter;
import kripton70.typeconverters.IntegerConverter;
import kripton70.typeconverters.LongConverter;
import kripton70.typeconverters.ShortConverter;
import kripton70.typeconverters.StringConverter;

import com.fasterxml.jackson.core.JsonToken;

public abstract class JacksonMapper<E> implements BinderMapper<E>, Visitor<E> {
	
	protected static ByteConverter byteMapper=new ByteConverter();
	protected static CharacterConverter characterMapper=new CharacterConverter();
	protected static ShortConverter shortMapper=new ShortConverter();	
	protected static IntegerConverter integerMapper=new IntegerConverter();
	protected static FloatConverter floatMapper=new FloatConverter();
	protected static DoubleConverter doubleMapper=new DoubleConverter();	
	protected static LongConverter longMapper=new LongConverter();	
	protected static StringConverter stringMapper=new StringConverter();

	public abstract E createInstance();

	public E parse(BinderContext context, JacksonParser parser) {
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

	/**
	 * Parse an object from a byte array. Note: parsing from an InputStream
	 * should be preferred over parsing from a byte array if possible.
	 *
	 * @param byteArray
	 *            The byte array being parsed.
	 */
	public E parse(BinderContext context, byte[] byteArray) {
		JacksonParser parser = context.createParser(byteArray);
		parser.nextToken();
		return parse(context, parser);
	}

	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 */
	public E parse(BinderContext context, InputStream is) {
		JacksonParser parser = context.createParser(is);
		parser.nextToken();
		return parse(context, parser);
	}


	/**
	 * Parse an object from a String. Note: parsing from an InputStream should
	 * be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 */
	public E parse(BinderContext context, String jsonString) {
		JacksonParser parser = context.createParser(jsonString);
		parser.nextToken();
		return parse(context, parser);
	}

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
	public abstract void parseField(BinderContext context, E instance, String fieldName, JacksonParser parser);

	/**
	 * Parse a list of objects from a JsonParser.
	 *
	 * @param parser
	 *            The JsonParser, preconfigured to be at the START_ARRAY token.
	 */
	public List<E> parseList(BinderContext context, JacksonParser parser){
		List<E> list = new ArrayList<>();
		if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
			while (parser.nextToken() != JsonToken.END_ARRAY) {
				list.add(parse(context, parser));
			}
		}
		return list;
	}

	/**
	 * Parse a list of objects from a byte array. Note: parsing from an
	 * InputStream should be preferred over parsing from a byte array if
	 * possible.
	 *
	 * @param byteArray
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(BinderContext context, byte[] byteArray) {
		JacksonParser parser = context.createParser(byteArray);
		parser.nextToken();
		return parseList(context, parser);
	}

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(BinderContext context, InputStream is) {
		JacksonParser parser = context.createParser(is);
		parser.nextToken();
		return parseList(context, parser);
	}


	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream
	 * should be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 */
	public List<E> parseList(BinderContext context, String jsonString) {
		JacksonParser parser = context.createParser(jsonString);
		parser.nextToken();
		return parseList(context, parser);
	}

	/**
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	public String serialize(BinderContext context, E object) {
		StringWriter sw = new StringWriter();

		BinderSerializer serializer = context.createSerializer(sw);
		serialize(context, object, serializer, true);
		serializer.close();
		return sw.toString();
	}

	/**
	 * Serialize an object to a pre-configured JsonGenerator object.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param serializer
	 *            The pre-configured JsonGenerator being written to.
	 * @param writeStartAndEnd
	 *            True if writeStartObject() should be called before and
	 *            writeEndObject() should be called after serializing. False if
	 *            not.
	 */
	public abstract void serialize(BinderContext context, E object, BinderSerializer serializer, boolean writeStartAndEnd);

	/**
	 * Serialize an object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param os
	 *            The OutputStream being written to.
	 */
	public void serialize(BinderContext context, E object, OutputStream os){
		BinderSerializer serializer = context.createSerializer(os);
		serialize(context, object, serializer, true);
		serializer.close();
	}

	/**
	 * Serialize a list of objects to a JSON String.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 */
	public String serialize(BinderContext context, List<E> list) {
		StringWriter sw = new StringWriter();
		BinderSerializer serializer = context.createSerializer(sw);
		serialize(context, list, serializer);
		serializer.close();
		return sw.toString();
	}

	/**
	 * Serialize a list of objects to a JsonGenerator.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param serializer
	 *            The JsonGenerator to which the list should be serialized
	 */
	public void serialize(BinderContext context, List<E> list, JacksonSerializer serializer) {
		serializer.writeStartArray();
		E object;
		for (int i=0; i<list.size();i++) {
			object=list.get(0);
			if (object != null) {
				serialize(context, object, serializer, true);
			} else {
				serializer.writeNull();
			}
		}
		serializer.writeEndArray();
	}

	/**
	 * Serialize a list of objects to an OutputStream.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param os
	 *            The OutputStream to which the list should be serialized
	 */
	public void serialize(BinderContext context, List<E> list, OutputStream os) {
		JacksonSerializer serializer = context.createSerializer(os);
		serialize(context, list, serializer);
		serializer.close();
	}
}
