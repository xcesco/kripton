package kripton70.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import kripton70.contexts.BinderContext;

public interface BinderMapper<E> {
	E createInstance();

	E parse(BinderContext context, BinderParser parser);
	
	E parse(BinderContext context, byte[] byteArray);

	E parse(BinderContext context, InputStream is);

	E parse(BinderContext context, String jsonString);

	void parseField(BinderContext context, E instance, String fieldName, BinderParser parser);

	List<E> parseList(BinderContext context, BinderParser parser);

	List<E> parseList(BinderContext context, byte[] byteArray);

	List<E> parseList(BinderContext context, InputStream is);

	List<E> parseList(BinderContext context, String jsonString);

	String serialize(BinderContext context, E object);

	void serialize(BinderContext context, E object, BinderSerializer serializer, boolean writeStartAndEnd);

	void serialize(BinderContext context, E object, OutputStream os);

	String serialize(BinderContext context, List<E> list);

	void serialize(BinderContext context, List<E> list, BinderSerializer serializer);

	void serialize(BinderContext context, List<E> list, OutputStream os);
}
