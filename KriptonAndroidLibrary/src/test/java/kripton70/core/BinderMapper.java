package kripton70.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import kripton70.contexts.BinderContext;
import kripton70.contexts.JacksonContext;
import kripton70.contexts.XmlBinderContext;
import kripton70.persistence.BinderParser;
import kripton70.persistence.BinderSerializer;
import kripton70.persistence.JacksonParser;
import kripton70.persistence.JacksonSerializer;
import kripton70.persistence.XmlParser;
import kripton70.persistence.XmlSerializer;

public interface BinderMapper<E> {
	E createInstance();

	E parse(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray);
	
	E parse(@SuppressWarnings("rawtypes") BinderContext context, BinderParser parser);

	E parse(@SuppressWarnings("rawtypes") BinderContext context, InputStream is);

	E parse(@SuppressWarnings("rawtypes") BinderContext context, String jsonString);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, BinderParser parser);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, byte[] byteArray);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, InputStream is);

	List<E> parseList(@SuppressWarnings("rawtypes") BinderContext context, String jsonString);

	String serialize(@SuppressWarnings("rawtypes") BinderContext context, E object);

	void serialize(@SuppressWarnings("rawtypes") BinderContext context, E object, OutputStream os);

	String serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list);

	void serialize(@SuppressWarnings("rawtypes") BinderContext context, List<E> list, OutputStream os);
	
	void serialize(JacksonContext context, E object, JacksonSerializer jacksonSerializer, boolean writeStartAndEnd);
	
	void serializeOnlyText(JacksonContext context, E object, JacksonSerializer jacksonSerializer, boolean writeStartAndEnd);

	void serialize(XmlBinderContext context, E object, XmlSerializer xmlSerializer, boolean writeStartAndEnd);
	
	void parse(JacksonContext context, E object, JacksonParser jacksonParser, boolean writeStartAndEnd);
	
	void parseOnlyText(JacksonContext context, E object, JacksonParser jacksonParser, boolean writeStartAndEnd);

	void parse(XmlBinderContext context, E object, XmlParser xmlParser, boolean writeStartAndEnd);

}
