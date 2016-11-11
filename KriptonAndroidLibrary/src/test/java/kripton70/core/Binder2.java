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
import java.util.List;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public interface Binder2 {

	BinderType getSupportedFormat();

	JsonFactory createInnerFactory();

	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> E parse(InputStream is, Class<E> jsonObjectClass) throws IOException;

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should
	 * be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> E parse(String jsonString, Class<E> jsonObjectClass) throws IOException;

	/**
	 * Parse a parameterized object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 * @param jsonObjectType
	 *            The ParameterizedType describing the object. Ex:
	 *            LoganSquare.parse(is, new
	 *            ParameterizedType&lt;MyModel&lt;OtherModel&gt;&gt;() { });
	 */
	<E> E parse(InputStream is, ParameterizedType<E> jsonObjectType) throws IOException;

	/**
	 * Parse a parameterized object from a String. Note: parsing from an
	 * InputStream should be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 * @param jsonObjectType
	 *            The ParameterizedType describing the object. Ex:
	 *            LoganSquare.parse(is, new
	 *            ParameterizedType&lt;MyModel&lt;OtherModel&gt;&gt;() { });
	 */
	<E> E parse(String jsonString, ParameterizedType<E> jsonObjectType) throws IOException;

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> List<E> parseList(InputStream is, Class<E> jsonObjectClass) throws IOException;

	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream
	 * should be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> List<E> parseList(String jsonString, Class<E> jsonObjectClass) throws IOException;

	/**
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	<E> String serialize(E object) throws IOException;

	/**
	 * Serialize an object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param os
	 *            The OutputStream being written to.
	 */
	<E> void serialize(E object, OutputStream os) throws IOException;

	/**
	 * Serialize a parameterized object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param parameterizedType
	 *            The ParameterizedType describing the object. Ex:
	 *            LoganSquare.serialize(object, new
	 *            ParameterizedType&lt;MyModel&lt;OtherModel&gt;&gt;() { });
	 */
	<E> String serialize(E object, ParameterizedType<E> parameterizedType) throws IOException;

	/**
	 * Serialize a parameterized object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param parameterizedType
	 *            The ParameterizedType describing the object. Ex:
	 *            LoganSquare.serialize(object, new
	 *            ParameterizedType&lt;MyModel&lt;OtherModel&gt;&gt;() { }, os);
	 * @param os
	 *            The OutputStream being written to.
	 */
	<E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os) throws IOException;

	/**
	 * Serialize a list of objects to a JSON String.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param jsonObjectClass
	 *            The @JsonObject class of the list elements
	 */
	<E> String serialize(List<E> list, Class<E> jsonObjectClass) throws IOException;

	/**
	 * Serialize a list of objects to an OutputStream.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param os
	 *            The OutputStream to which the list should be serialized
	 * @param jsonObjectClass
	 *            The @JsonObject class of the list elements
	 */
	<E> void serialize(List<E> list, OutputStream os, Class<E> jsonObjectClass) throws IOException;
	
	BinderGenerator createGenerator(DataOutput out) throws IOException;
	
	BinderGenerator createGenerator(OutputStream out) throws IOException;
	
	BinderGenerator createGenerator(Writer writer) throws IOException;

	BinderGenerator createGenerator(DataOutput out, JsonEncoding encoding) throws IOException;

	BinderGenerator createGenerator(File file, JsonEncoding encoding) throws IOException;

	BinderGenerator createGenerator(File file) throws IOException;

	BinderGenerator createGenerator(OutputStream out, JsonEncoding encoding) throws IOException;

	BinderParser createParser(byte[] data) throws IOException;

	BinderParser createParser(char[] content) throws IOException;

	BinderParser createParser(DataInput in) throws IOException;

	BinderParser createParser(File file) throws IOException;

	BinderParser createParser(InputStream in) throws IOException;

	BinderGenerator createParser(Reader reader) throws IOException;

	BinderGenerator createParser(String content) throws IOException;

	BinderGenerator createParser(URL url) throws IOException;

}
