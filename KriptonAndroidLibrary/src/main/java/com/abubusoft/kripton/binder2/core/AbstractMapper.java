package com.abubusoft.kripton.binder2.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.stream.XMLStreamException;

import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public abstract class AbstractMapper<E> implements BinderMapper<E> {

	public E parse(BinderContext context, ParserWrapper parserWrapper) {
		E instance = null;

		switch (context.getSupportedFormat()) {
		case XML:
			instance = parseOnXml((XmlBinderContext) context, (XmlWrapperParser) parserWrapper, 0);
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				instance = parseOnJacksonAsString((JacksonContext) context, (JacksonWrapperParser) parserWrapper);
			else
				instance = parseOnJackson((JacksonContext) context, (JacksonWrapperParser) parserWrapper);

			break;
		}

		return instance;
	}
	
	public void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object) {
		serialize(context, object, serializerWrapper, true);
	}

	public void serialize(BinderContext context, E object, SerializerWrapper serializerWrapper, boolean writeStartAndEnd) {

		switch (context.getSupportedFormat()) {
		case XML:
			try {
				XmlWrapperSerializer wrapper = ((XmlWrapperSerializer) serializerWrapper);

				if (writeStartAndEnd) {
					wrapper.xmlSerializer.writeStartDocument();
				}
				serializeOnXml((XmlBinderContext) context, object, wrapper, 0);

				if (writeStartAndEnd) {
					wrapper.xmlSerializer.writeEndDocument();
				}
			} catch (XMLStreamException e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				serializeOnJacksonAsString((JacksonContext) context, object, (JacksonWrapperSerializer) serializerWrapper);
			else
				serializeOnJackson((JacksonContext) context, object, (JacksonWrapperSerializer) serializerWrapper);
			break;
		}
	}

	public void serializeCollection(BinderContext context, Collection<E> collection, OutputStream os) {
		SerializerWrapper serializer = context.createSerializer(os);
		serializeCollection(context, serializer, collection);
		serializer.close();
	}

	public String serializeCollection(BinderContext context, Collection<E> collection) {
		StringWriter sw = new StringWriter();

		SerializerWrapper serializer = context.createSerializer(sw);
		serializeCollection(context, serializer, collection);
		serializer.close();
		return sw.toString();
	}

	public void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection) {
		switch (context.getSupportedFormat()) {
		case XML: {
			throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
		}
		default: {
			JacksonContext jacksonContext = (JacksonContext) context;
			JacksonWrapperSerializer wrapper = (JacksonWrapperSerializer) serializerWrapper;

			try {
				wrapper.jacksonGenerator.writeStartArray();
				if (context.getSupportedFormat().onlyText)
					for (E object : collection) {
						serializeOnJacksonAsString(jacksonContext, object, wrapper);
					}
				else
					for (E object : collection) {
						serializeOnJackson(jacksonContext, object, wrapper);
					}
				wrapper.jacksonGenerator.writeEndArray();
			} catch (IOException e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}
		}
			break;
		}
	}

	/**
	 * Parse an object from a byte array. Note: parsing from an InputStream should be preferred over parsing from a byte array if possible.
	 *
	 * @param byteArray
	 *            The byte array being parsed.
	 */
	public E parse(BinderContext context, byte[] byteArray) {
		ParserWrapper parser = context.createParser(byteArray);
		return parse(context, parser);
	}

	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 */
	public E parse(BinderContext context, InputStream is) {
		ParserWrapper parser = context.createParser(is);
		return parse(context, parser);
	}

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 */
	public E parse(BinderContext context, String buffer) {
		ParserWrapper parser = context.createParser(buffer);
		return parse(context, parser);
	}

	public <L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parser, L collection) {
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
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	public String serialize(BinderContext context, E object) {
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
	public void serialize(BinderContext context, E object, OutputStream os) {
		SerializerWrapper serializer = context.createSerializer(os);
		serialize(context, object, serializer, true);
		serializer.close();
	}

}
