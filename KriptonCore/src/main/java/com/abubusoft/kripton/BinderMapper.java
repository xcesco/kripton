package com.abubusoft.kripton;

import java.util.Collection;

import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.ParserWrapper;
import com.abubusoft.kripton.persistence.SerializerWrapper;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;

public interface BinderMapper<E> {
	
	E createInstance();

	E parse(BinderContext context, ParserWrapper parser);

	<L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parser, L collection);

	E parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser jacksonParser);

	E parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser jacksonParser);

	E parseOnXml(KriptonXmlContext context, XmlWrapperParser xmlParser, int currentEventType);

	void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object);

	void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection);

	int serializeOnJackson(AbstractJacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer);

	int serializeOnJacksonAsString(AbstractJacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer);

	void serializeOnXml(KriptonXmlContext context, E object, XmlWrapperSerializer xmlSerializer, int currentEventType);

}
