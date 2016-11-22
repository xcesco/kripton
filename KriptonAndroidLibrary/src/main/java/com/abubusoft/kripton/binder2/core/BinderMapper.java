package com.abubusoft.kripton.binder2.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;

public interface BinderMapper<E> {
	E createInstance();

	E parse(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray);
	
	E parse(@SuppressWarnings("rawtypes") BinderContext context, ParserWrapper parser);

	E parse(@SuppressWarnings("rawtypes") BinderContext context, InputStream is);

	E parse(@SuppressWarnings("rawtypes") BinderContext context, String jsonString);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, ParserWrapper parser);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, InputStream is);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, String jsonString);

	String serialize(@SuppressWarnings("rawtypes") BinderContext context, E object);

	void serialize(@SuppressWarnings("rawtypes") BinderContext context, E object, OutputStream os);

	String serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list);

	void serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list, OutputStream os);
	
	void serializeOnJackson(JacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer);
	
	void serializeOnJacksonAsString(JacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer);

	void serializeOnXml(XmlBinderContext context, E object, XmlWrapperSerializer xmlSerializer, int currentEventType);
	
	E parseOnJackson(JacksonContext context, JacksonWrapperParser jacksonParser);
	
	E parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser jacksonParser);

	E parseOnXml(XmlBinderContext context, XmlWrapperParser xmlParser, int currentEventType);

}
