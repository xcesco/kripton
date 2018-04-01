package com.abubusoft.kripton;

import java.io.IOException;
import java.util.Collection;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.ParserWrapper;
import com.abubusoft.kripton.persistence.SerializerWrapper;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public abstract class AbstractMapper<E> implements BinderMapper<E> {

	@Override
	public E parse(BinderContext context, ParserWrapper parserWrapper) throws Exception {
		E instance = null;

		switch (context.getSupportedFormat()) {
		case XML:
			instance = parseOnXml(((XmlWrapperParser) parserWrapper).xmlParser, 0);
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				instance = parseOnJacksonAsString(((JacksonWrapperParser) parserWrapper).jacksonParser);
			else
				instance = parseOnJackson(((JacksonWrapperParser) parserWrapper).jacksonParser);

			break;
		}

		return instance;
	}

	@Override
	public <L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parserWrapper, L collection)  throws Exception {
		switch (context.getSupportedFormat()) {
		case XML: {
			throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
		}
		default: {
			JacksonWrapperParser wrapperParser = (JacksonWrapperParser) parserWrapper;
			JsonParser parser = wrapperParser.jacksonParser;

			try {
				collection.clear();

				if (parser.nextToken() != JsonToken.START_ARRAY) {
					throw (new KriptonRuntimeException("Invalid input format"));
				}
				if (context.getSupportedFormat().onlyText) {
					while (parser.nextToken() != JsonToken.END_ARRAY) {
						collection.add(parseOnJacksonAsString(parser));
					}
				} else {
					while (parser.nextToken() != JsonToken.END_ARRAY) {
						collection.add(parseOnJackson(parser));
					}
				}
				return collection;
			} catch (IOException e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}
		}
		}
	}

	public void serialize(BinderContext context, E object, SerializerWrapper serializerWrapper, boolean writeStartAndEnd) throws Exception {

		switch (context.getSupportedFormat()) {
		case XML:
			try {
				XmlWrapperSerializer wrapper = ((XmlWrapperSerializer) serializerWrapper);
				XMLSerializer xmlSerializer=wrapper.xmlSerializer;

				if (writeStartAndEnd) {
					xmlSerializer.writeStartDocument();
				}
				serializeOnXml(object, xmlSerializer, 0);

				if (writeStartAndEnd) {
					xmlSerializer.writeEndDocument();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				serializeOnJacksonAsString(object, ((JacksonWrapperSerializer) serializerWrapper).jacksonGenerator);
			else
				serializeOnJackson(object, ((JacksonWrapperSerializer) serializerWrapper).jacksonGenerator);
			break;
		}
	}

	@Override
	public void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object) throws Exception {
		serialize(context, object, serializerWrapper, true);
	}

	@Override
	public void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection) throws Exception {
		switch (context.getSupportedFormat()) {
		case XML: {
			throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
		}
		default: {
			JacksonWrapperSerializer wrapper = (JacksonWrapperSerializer) serializerWrapper;
			JsonGenerator jacksonGenerator=wrapper.jacksonGenerator;

			try {
				jacksonGenerator.writeStartArray();
				if (context.getSupportedFormat().onlyText)
					for (E object : collection) {
						serializeOnJacksonAsString(object, jacksonGenerator);
					}
				else
					for (E object : collection) {
						serializeOnJackson(object, jacksonGenerator);
					}
				jacksonGenerator.writeEndArray();
			} catch (IOException e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}
		}
			break;
		}
	}

}
