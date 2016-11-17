package kripton70.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import kripton70.contexts.BinderContext;
import kripton70.contexts.JacksonContext;
import kripton70.contexts.XmlBinderContext;
import kripton70.persistence.ParserWrapper;
import kripton70.persistence.SerializerWrapper;
import kripton70.persistence.JacksonWrapperParser;
import kripton70.persistence.JacksonWrapperSerializer;
import kripton70.persistence.XmlWrapperParser;
import kripton70.persistence.XmlWrapperSerializer;

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
	
	void serializeOnJackson(JacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer, boolean writeStartAndEnd);
	
	void serializeOnJacksonAsString(JacksonContext context, E object, JacksonWrapperSerializer jacksonSerializer, boolean writeStartAndEnd);

	void serializeOnXml(XmlBinderContext context, E object, XmlWrapperSerializer xmlSerializer, boolean writeStartAndEnd);
	
	E parseOnJackson(JacksonContext context, JacksonWrapperParser jacksonParser);
	
	E parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser jacksonParser);

	E parseOnXml(XmlBinderContext context, XmlWrapperParser xmlParser);

}
