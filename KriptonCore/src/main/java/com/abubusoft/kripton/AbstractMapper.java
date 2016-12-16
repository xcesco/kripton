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
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public abstract class AbstractMapper<E> implements BinderMapper<E> {

	@Override
	public E parse(BinderContext context, ParserWrapper parserWrapper) {
		E instance = null;

		switch (context.getSupportedFormat()) {
		case XML:
			instance = parseOnXml((KriptonXmlContext) context, (XmlWrapperParser) parserWrapper, 0);
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				instance = parseOnJacksonAsString((AbstractJacksonContext) context, (JacksonWrapperParser) parserWrapper);
			else
				instance = parseOnJackson((AbstractJacksonContext) context, (JacksonWrapperParser) parserWrapper);

			break;
		}

		return instance;
	}

	@Override
	public <L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parserWrapper, L collection) {
		switch (context.getSupportedFormat()) {
		case XML: {
			throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
		}
		default: {
			AbstractJacksonContext jacksonContext = (AbstractJacksonContext) context;
			JacksonWrapperParser wrapperParser = (JacksonWrapperParser) parserWrapper;
			JsonParser parser = wrapperParser.jacksonParser;

			try {
				collection.clear();

				if (parser.nextToken() != JsonToken.START_ARRAY) {
					throw (new KriptonRuntimeException("Invalid input format"));
				}
				if (context.getSupportedFormat().onlyText) {
					while (parser.nextToken() != JsonToken.END_ARRAY) {
						collection.add(parseOnJacksonAsString(jacksonContext, wrapperParser));
					}
				} else {
					while (parser.nextToken() != JsonToken.END_ARRAY) {
						collection.add(parseOnJackson(jacksonContext, wrapperParser));
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

	public void serialize(BinderContext context, E object, SerializerWrapper serializerWrapper, boolean writeStartAndEnd) {

		switch (context.getSupportedFormat()) {
		case XML:
			try {
				XmlWrapperSerializer wrapper = ((XmlWrapperSerializer) serializerWrapper);

				if (writeStartAndEnd) {
					wrapper.xmlSerializer.writeStartDocument();
				}
				serializeOnXml((KriptonXmlContext) context, object, wrapper, 0);

				if (writeStartAndEnd) {
					wrapper.xmlSerializer.writeEndDocument();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw (new KriptonRuntimeException(e));
			}
			break;
		default:
			if (context.getSupportedFormat().onlyText)
				serializeOnJacksonAsString((AbstractJacksonContext) context, object, (JacksonWrapperSerializer) serializerWrapper);
			else
				serializeOnJackson((AbstractJacksonContext) context, object, (JacksonWrapperSerializer) serializerWrapper);
			break;
		}
	}

	@Override
	public void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object) {
		serialize(context, object, serializerWrapper, true);
	}

	@Override
	public void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection) {
		switch (context.getSupportedFormat()) {
		case XML: {
			throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
		}
		default: {
			AbstractJacksonContext jacksonContext = (AbstractJacksonContext) context;
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

}
