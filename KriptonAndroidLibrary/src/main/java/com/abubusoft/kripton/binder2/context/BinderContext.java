package com.abubusoft.kripton.binder2.context;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.core.ParameterizedType;
import com.abubusoft.kripton.binder2.persistence.ParserWrapper;
import com.abubusoft.kripton.binder2.persistence.SerializerWrapper;
import com.fasterxml.jackson.core.JsonEncoding;

public interface BinderContext<S extends SerializerWrapper, P extends ParserWrapper> {

	P createParser(byte[] data);

	P createParser(File file);

	P createParser(InputStream in);

	P createParser(Reader reader);

	P createParser(String content);

	S createSerializer(File file);

	S createSerializer(File file, JsonEncoding encoding);

	S createSerializer(OutputStream out);

	S createSerializer(OutputStream out, JsonEncoding encoding);

	S createSerializer(Writer writer);

	BinderType getSupportedFormat();

	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 * @param objectClazz
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> E parse(InputStream is, Class<E> objectClazz);

	/**
	 * Parse a parameterized object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 * @param objectType
	 *            The ParameterizedType describing the object. Ex:
	 *            LoganSquare.parse(is, new
	 *            ParameterizedType&lt;MyModel&lt;OtherModel&gt;&gt;() { });
	 */
	<E> E parse(InputStream is, ParameterizedType<E> objectType);

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should
	 * be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 * @param objectClazz
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> E parse(String buffer, Class<E> objectClazz);

	/**
	 * Parse a parameterized object from a String. Note: parsing from an
	 * InputStream should be preferred over parsing from a String if possible.
	 *
	 * @param buffer
	 *            The JSON string being parsed.
	 * @param objectType
	 *            The ParameterizedType describing the object. Ex:
	 *            LoganSquare.parse(is, new
	 *            ParameterizedType&lt;MyModel&lt;OtherModel&gt;&gt;() { });
	 */
	<E> E parse(String buffer, ParameterizedType<E> objectType);

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 * @param objectClazz
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> List<E> parseList(InputStream is, Class<E> objectClazz);

	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream
	 * should be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 * @param objectClazz
	 *            The @JsonObject class to parse the InputStream into
	 */
	<E> List<E> parseList(String jsonString, Class<E> objectClazz);

	/**
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	<E> String serialize(E object);

	/**
	 * Serialize an object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param os
	 *            The OutputStream being written to.
	 */
	<E> void serialize(E object, OutputStream os);

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
	<E> String serialize(E object, ParameterizedType<E> parameterizedType);

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
	<E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os);

	/**
	 * Serialize a list of objects to a JSON String.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param objectClazz
	 *            The @JsonObject class of the list elements
	 */
	<E> String serialize(List<E> list, Class<E> objectClazz);

	/**
	 * Serialize a list of objects to an OutputStream.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param os
	 *            The OutputStream to which the list should be serialized
	 * @param objectClazz
	 *            The @JsonObject class of the list elements
	 */
	<E> void serialize(List<E> list, OutputStream os, Class<E> objectClazz);
}
