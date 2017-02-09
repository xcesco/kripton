package com.abubusoft.kripton;

import java.util.Collection;

import com.abubusoft.kripton.persistence.ParserWrapper;
import com.abubusoft.kripton.persistence.SerializerWrapper;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

/**
 * This annotation is used to define interface of generated bind map classes by Kripton.
 *  
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 * @param <E>
 */
public interface BinderMapper<E> {
	
	E parse(BinderContext context, ParserWrapper parser) throws Exception;

	<L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parser, L collection)  throws Exception;

	E parseOnJackson(JsonParser jacksonParser) throws Exception;

	E parseOnJacksonAsString(JsonParser jacksonParser) throws Exception;

	E parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception;

	void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object) throws Exception;

	void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection) throws Exception;

	int serializeOnJackson(E object, JsonGenerator jacksonSerializer) throws Exception;

	int serializeOnJacksonAsString(E object, JsonGenerator jacksonSerializer) throws Exception;

	void serializeOnXml(E object, XMLSerializer xmlSerializer, int currentEventType) throws Exception;

}
