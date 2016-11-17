package kripton70.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

import kripton70.contexts.BinderContext;
import kripton70.contexts.JacksonContext;
import kripton70.contexts.XmlBinderContext;
import kripton70.persistence.ParserWrapper;
import kripton70.persistence.SerializerWrapper;
import kripton70.persistence.JacksonWrapperParser;
import kripton70.persistence.JacksonWrapperSerializer;
import kripton70.persistence.XmlWrapperParser;
import kripton70.persistence.XmlWrapperSerializer;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonToken;

public abstract class AbstractMapper<E> implements BinderMapper<E> {

	/*
	 * protected static ByteConverter byteMapper=new ByteConverter(); protected
	 * static CharacterConverter characterMapper=new CharacterConverter();
	 * protected static ShortConverter shortMapper=new ShortConverter();
	 * protected static IntegerConverter integerMapper=new IntegerConverter();
	 * protected static FloatConverter floatMapper=new FloatConverter();
	 * protected static DoubleConverter doubleMapper=new DoubleConverter();
	 * protected static LongConverter longMapper=new LongConverter(); protected
	 * static StringConverter stringMapper=new StringConverter();
	 */

	public E parse(@SuppressWarnings("rawtypes") BinderContext context, ParserWrapper parserWrapper) {
		E instance = null;

		switch (context.getSupportedFormat()) {
		case XML:
			instance = parseOnXml((XmlBinderContext) context, (XmlWrapperParser) parserWrapper);
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				instance = parseOnJacksonAsString((JacksonContext) context, (JacksonWrapperParser) parserWrapper);
			else
				instance = parseOnJackson((JacksonContext) context, (JacksonWrapperParser) parserWrapper);

		}

		return instance;
	}

	public void serialize(@SuppressWarnings("rawtypes") BinderContext context, E object, SerializerWrapper serializerWrapper, boolean writeStartAndEnd) {

		switch (context.getSupportedFormat()) {
		case XML:
			try {
				XmlWrapperSerializer wrapper = ((XmlWrapperSerializer) serializerWrapper);
				wrapper.xmlSerializer.writeStartDocument();
				serializeOnXml((XmlBinderContext) context, object, wrapper, true);
				wrapper.xmlSerializer.writeEndDocument();
			} catch (XMLStreamException e) {
				e.printStackTrace();
				throw(new KriptonRuntimeException(e));
			}
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				serializeOnJacksonAsString((JacksonContext) context, object, (JacksonWrapperSerializer) serializerWrapper, true);
			else
				serializeOnJackson((JacksonContext) context, object, (JacksonWrapperSerializer) serializerWrapper, true);
		}
	}

	/*
	 * public E parse(BinderContext context, JacksonParser parser) { E instance
	 * = createInstance(); if (parser.getCurrentToken() == null) {
	 * parser.nextToken(); } if (parser.getCurrentToken() !=
	 * JsonToken.START_OBJECT) { parser.skipChildren(); return null; } while
	 * (parser.nextToken() != JsonToken.END_OBJECT) { String fieldName =
	 * parser.getCurrentName(); parser.nextToken(); parseField(context,
	 * instance, fieldName, parser); parser.skipChildren(); } return instance; }
	 */

	/**
	 * Parse an object from a byte array. Note: parsing from an InputStream
	 * should be preferred over parsing from a byte array if possible.
	 *
	 * @param byteArray
	 *            The byte array being parsed.
	 */
	public E parse(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray) {
		ParserWrapper parser = context.createParser(byteArray);
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
		ParserWrapper parser = context.createParser(is);
		return parse(context, parser);
	}

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should
	 * be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 */
	public E parse(@SuppressWarnings("rawtypes") BinderContext context, String buffer) {
		ParserWrapper parser = context.createParser(buffer);
		return parse(context, parser);
	}

	/**
	 * Parse a list of objects from a JsonParser.
	 *
	 * @param parser
	 *            The JsonParser, preconfigured to be at the START_ARRAY token.
	 * @throws IOException
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, ParserWrapper parser) {
		// try {
		// List<E> list = new ArrayList<>();
		// if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
		//
		// while (parser.nextToken() != JsonToken.END_ARRAY) {
		// list.add(parse(context, parser));
		// }
		// }
		// return list;
		// } catch (IOException e) {
		// e.printStackTrace();
		// throw(new KriptonRuntimeException(e));
		// }
		// TODO
		return null;
	}

	/**
	 * Parse a list of objects from a byte array. Note: parsing from an
	 * InputStream should be preferred over parsing from a byte array if
	 * possible.
	 *
	 * @param byteArray
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray) {
		ParserWrapper parser = context.createParser(byteArray);
		return parseList(context, parser);
	}

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, InputStream is) {
		ParserWrapper parser = context.createParser(is);
		return parseList(context, parser);
	}

	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream
	 * should be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 */
	public List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, String buffer) {
		ParserWrapper parser = context.createParser(buffer);
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

		SerializerWrapper serializer = context.createSerializer(sw);
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
	public void serialize(@SuppressWarnings("rawtypes") BinderContext context, E object, OutputStream os) {
		SerializerWrapper serializer = context.createSerializer(os);
		serialize(context, object, serializer, true);
		serializer.close();
	}

	/**
	 * Serialize a list of objects to a JSON String.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 */
	public String serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list) {
		StringWriter sw = new StringWriter();
		SerializerWrapper serializer = context.createSerializer(sw);
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
	public void serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list, JacksonWrapperSerializer serializer) {
		// TODO
		// serializer.writeStartArray();
		// E object;
		// for (int i = 0; i < list.size(); i++) {
		// object = list.get(0);
		// if (object != null) {
		// serialize(context, object, serializer, true);
		// } else {
		// serializer.writeNull();
		// }
		// }
		// serializer.writeEndArray();
	}

	/**
	 * Serialize a list of objects to an OutputStream.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param os
	 *            The OutputStream to which the list should be serialized
	 */
	public void serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list, OutputStream os) {
		SerializerWrapper serializer = context.createSerializer(os);
		// TODO Auto-generated method stub
		// serialize(context, list, serializer);
		serializer.close();
	}
}
