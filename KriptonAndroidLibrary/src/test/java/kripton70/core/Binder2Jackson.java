package kripton70.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import com.fasterxml.jackson.core.JsonEncoding;

import kripton70.NoSuchMapperException;

public interface Binder2Jackson extends Binder2 {

	BinderSerializer createSerializer(DataOutput out) throws IOException;
	
	BinderSerializer createSerializer(OutputStream out) throws IOException;
	
	BinderSerializer createSerializer(Writer writer) throws IOException;

	BinderSerializer createSerializer(DataOutput out, JsonEncoding encoding) throws IOException;

	BinderSerializer createSerializer(File file, JsonEncoding encoding) throws IOException;

	BinderSerializer createSerializer(File file) throws IOException;

	BinderSerializer createSerializer(OutputStream out, JsonEncoding encoding) throws IOException;

	BinderParser createParser(byte[] data) throws IOException;

	BinderParser createParser(char[] content) throws IOException;

	BinderParser createParser(DataInput in) throws IOException;

	BinderParser createParser(File file) throws IOException;

	BinderParser createParser(InputStream in) throws IOException;

	BinderParser createParser(Reader reader) throws IOException;

	BinderParser createParser(String content) throws IOException;

	BinderParser createParser(URL url) throws IOException;
	
	/**
	 * Returns a JsonMapper for a given class that has been annotated with @JsonObject.
	 *
	 * @param type
	 *            The ParameterizedType for which the JsonMapper should be fetched.
	 */
	<E, M extends JacksonMapper<E>> M mapperFor(ParameterizedType<E> type) throws NoSuchMapperException;

	/**
	 * Returns a JsonMapper for a given class that has been annotated with @JsonObject.
	 *
	 * @param cls
	 *            The class for which the JsonMapper should be fetched.
	 */
	<E, M extends JacksonMapper<E>> M mapperFor(Class<E> cls) throws NoSuchMapperException;

	<T> T parse(InputStream is);
}
