package com.abubusoft.kripton.binder2.core;

import java.util.Collection;

import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;

public interface BinderMapper<E> {
	E createInstance();

	E parse(BinderContext context, ParserWrapper parser);
	
	<L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parser, L collection);
	
	E parseOnJackson(JacksonContext context, JacksonWrapperParser jacksonParser);

	E parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser jacksonParser);

	E parseOnXml(XmlBinderContext context, XmlWrapperParser xmlParser, int currentEventType);

	void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object);
	
	void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection);

	int serializeOnJackson(JacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer);
	
	int serializeOnJacksonAsString(JacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer);

	void serializeOnXml(XmlBinderContext context, E object, XmlWrapperSerializer xmlSerializer, int currentEventType);
	

}
