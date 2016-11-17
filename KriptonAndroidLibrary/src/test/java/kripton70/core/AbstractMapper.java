package kripton70.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import kripton70.contexts.BinderContext;
import kripton70.contexts.JacksonContext;
import kripton70.contexts.XmlBinderContext;
import kripton70.persistence.BinderParser;
import kripton70.persistence.BinderSerializer;
import kripton70.persistence.JacksonParser;
import kripton70.persistence.JacksonSerializer;
import kripton70.persistence.XmlParser;
import kripton70.persistence.XmlSerializer;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonToken;

public abstract class AbstractMapper<E> implements BinderMapper<E> {

	/*
	 * protected static ByteConverter byteMapper=new ByteConverter(); protected static CharacterConverter characterMapper=new CharacterConverter(); protected static ShortConverter shortMapper=new ShortConverter(); protected static
	 * IntegerConverter integerMapper=new IntegerConverter(); protected static FloatConverter floatMapper=new FloatConverter(); protected static DoubleConverter doubleMapper=new DoubleConverter(); protected static LongConverter
	 * longMapper=new LongConverter(); protected static StringConverter stringMapper=new StringConverter();
	 */

	public E parse(@SuppressWarnings("rawtypes") BinderContext context, BinderParser parser) {
		E instance = null;

		switch (context.getSupportedFormat()) {
		case XML:
			instance=parse((XmlBinderContext) context, (XmlParser) parser, true);
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				instance=parseOnlyText((JacksonContext) context, (JacksonParser) parser, true);
			else
				instance=parse((JacksonContext) context, (JacksonParser) parser, true);

		}

		return instance;
	}

	public void serialize(@SuppressWarnings("rawtypes") BinderContext context, E object, BinderSerializer serializer, boolean writeStartAndEnd) {
		switch (context.getSupportedFormat()) {
		case XML: {
			try {
				XmlSerializer xmlSerializer = (XmlSerializer) serializer;
				xmlSerializer.writeStartDocument();
				serialize((XmlBinderContext) context, object, xmlSerializer, true);
				xmlSerializer.writeEndDocument();
			} catch (XMLStreamException e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}

		}
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				serializeOnlyText((JacksonContext) context, object, (JacksonSerializer) serializer, true);
			else
				serialize((JacksonContext) context, object, (JacksonSerializer) serializer, true);
		}
	}

	@Override
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, BinderParser parser) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public E parse(BinderContext context, JacksonParser parser) { E instance = createInstance(); if (parser.getCurrentToken() == null) { parser.nextToken(); } if (parser.getCurrentToken() != JsonToken.START_OBJECT) {
	 * parser.skipChildren(); return null; } while (parser.nextToken() != JsonToken.END_OBJECT) { String fieldName = parser.getCurrentName(); parser.nextToken(); parseField(context, instance, fieldName, parser); parser.skipChildren(); }
	 * return instance; }
	 */

	/**
	 * Parse an object from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
	 *
	 * @param byteArray
	 *            The byte array being parsed.
	 */
	public E parse(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray) {
		BinderParser parser = context.createParser(byteArray);
		// parser.nextToken();
		return parse(context, parser);
		// return null;
	}

	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 */
	public E parse(@SuppressWarnings("rawtypes") BinderContext context, InputStream is) {
		BinderParser parser = context.createParser(is);
		return parse(context, parser);
	}

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 */
	public E parse(@SuppressWarnings("rawtypes") BinderContext context, String buffer) {
		BinderParser parser = context.createParser(buffer);
		return parse(context, parser);
	}

	/**
	 * Parse a list of objects from a JsonParser.
	 *
	 * @param parser
	 *            The JsonParser, preconfigured to be at the START_ARRAY token.
	 * @throws IOException
	 */
	public List<E> parseList(BinderContext context, JacksonParser parser) {
		try {
			List<E> list = new ArrayList<>();
			if (parser.getCurrentToken() == JsonToken.START_ARRAY) {

				while (parser.nextToken() != JsonToken.END_ARRAY) {
					list.add(parse(context, parser));
				}
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			throw(new KriptonRuntimeException(e));
		}
	}

	/**
	 * Parse a list of objects from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
	 *
	 * @param byteArray
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray) {
		BinderParser parser = context.createParser(byteArray);
		return parseList(context, parser);
	}

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, InputStream is) {
		BinderParser parser = context.createParser(is);
		return parseList(context, parser);
	}

	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, String buffer) {
		BinderParser parser = context.createParser(buffer);
		return parseList(context, parser);
	}

	/**
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	public String serialize(@SuppressWarnings("rawtypes") BinderContext context, E object) {
		StringWriter sw = new StringWriter();

		BinderSerializer serializer = context.createSerializer(sw);
		serialize(context, object, serializer, true);
		serializer.close();
		return sw.toString();
	}

	/**
	 * Serialize an object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param os
	 *            The OutputStream being written to.
	 */
	public void serialize(BinderContext context, E object, OutputStream os) {
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
		// serialize(context, list, serializer);
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
		for (int i = 0; i < list.size(); i++) {
			object = list.get(0);
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
		BinderSerializer serializer = context.createSerializer(os);
		// serialize(context, list, serializer);
		serializer.close();
	}
}
