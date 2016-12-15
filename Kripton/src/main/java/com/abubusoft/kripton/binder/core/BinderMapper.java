package com.abubusoft.kripton.binder.core;

import java.util.Collection;

import com.abubusoft.kripton.binder.context.BinderContext;
import com.abubusoft.kripton.binder.context.AbstractJacksonContext;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder.persistence.ParserWrapper;
import com.abubusoft.kripton.binder.persistence.SerializerWrapper;
import com.abubusoft.kripton.binder.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder.persistence.XmlWrapperSerializer;

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
