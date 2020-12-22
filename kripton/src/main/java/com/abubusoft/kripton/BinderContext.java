/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Binder context is interface used to define a persistence context. Every
 * persistence context allow to convert Java objects in specific data format and
 * viceversa.
 * </p>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface BinderContext {

	/**
	 * return true if class can be persisted.
	 *
	 * @param cls
	 *            the cls
	 * @return true or false
	 */
	boolean canPersist(Class<?> cls);

	/**
	 * <p>
	 * Specifies which data format is supported by this binding context.
	 * </p>
	 * 
	 * @return supported data format
	 */
	BinderType getSupportedFormat();

	/**
	 * <p>
	 * Parse a byte array to create an instance of <code>objectClazz</code>
	 * class.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            byte array as input
	 * @param objectClazz
	 *            class of object to create
	 * @return instance of object of type <code>objectClazz</code>
	 */
	<E> E parse(byte[] source, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a file to create an instance of <code>objectClazz</code> class.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return instance of object of type <code>objectClazz</code>
	 */
	<E> E parse(File source, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse an input stream to create an instance of <code>objectClazz</code>
	 * class.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return instance of object of type <code>objectClazz</code>
	 */
	<E> E parse(InputStream source, Class<E> objectClazz);

	/**
	 * <p>
	 * Use a reader to create an instance of <code>objectClazz</code> class.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return instance of object of type <code>objectClazz</code>
	 */
	<E> E parse(Reader source, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a string to create an instance of <code>objectClazz</code> class.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param buffer
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return instance of object of type <code>objectClazz</code>
	 */
	<E> E parse(String buffer, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a string to fill a collection of <code>objectClazz</code> instance.
	 * </p>
	 *
	 * @param <L>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param collection
	 *            instance of collection to fill during parsing
	 * @param objectClazz
	 *            class of object to create
	 * @return collection of objects of type <code>objectClazz</code>. It's
	 *         equals to collection param.
	 */
	<L extends Collection<E>, E> L parseCollection(String source, L collection, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a byte array to fill a collection of <code>objectClazz</code>
	 * instance.
	 * </p>
	 *
	 * @param <L>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param collection
	 *            instance of collection to fill during parsing
	 * @param objectClazz
	 *            class of object to create
	 * @return collection of objects of type <code>objectClazz</code>. It's
	 *         equals to collection param.
	 */
	<L extends Collection<E>, E> L parseCollection(byte[] source, L collection, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse an inputstream to fill a collection of <code>objectClazz</code>
	 * instance.
	 * </p>
	 *
	 * @param <L>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param collection
	 *            instance of collection to fill during parsing
	 * @param objectClazz
	 *            class of object to create
	 * @return collection of objects of type <code>objectClazz</code>. It's
	 *         equals to collection param.
	 */
	<L extends Collection<E>, E> L parseCollection(InputStream source, L collection, Class<E> objectClazz);

	/**
	 * <p>
	 * Use a reader to fill a collection of <code>objectClazz</code> instance.
	 * </p>
	 *
	 * @param <L>
	 *            the generic type
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param collection
	 *            instance of collection to fill during parsing
	 * @param objectClazz
	 *            class of object to create
	 * @return collection of objects of type <code>objectClazz</code>. It's
	 *         equals to collection param.
	 */
	<L extends Collection<E>, E> L parseCollection(Reader source, L collection, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a byte array to create and populate a list of
	 * <code>objectClazz</code> instance.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(byte[] source, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a string to create and populate a list of <code>objectClazz</code>
	 * instance.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(String source, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a string to create and populate a map of key/value. The key is the
	 * name of field, value is its content.
	 * </p>
	 * 
	 * @param source
	 *            input
	 * @return map of key/value.
	 */
	Map<String, Object> parseMap(String source);

	/**
	 * <p>
	 * Parse an inputstream to create and populate a map of key/value. The key
	 * is the name of field, value is its content.
	 * </p>
	 * 
	 * @param source
	 *            input
	 * @return map of key/value.
	 */
	Map<String, Object> parseMap(InputStream source);

	/**
	 * <p>
	 * Use a reader to create and populate a map of key/value. The key is the
	 * name of field, value is its content.
	 * </p>
	 * 
	 * @param source
	 *            input
	 * @return map of key/value.
	 */
	Map<String, Object> parseMap(Reader source);

	/**
	 * <p>
	 * Parse a inputstream to create and populate a list of
	 * <code>objectClazz</code> instance.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(InputStream source, Class<E> objectClazz);

	/**
	 * <p>
	 * Use a reader to create and populate a list of <code>objectClazz</code>
	 * instance.
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(Reader source, Class<E> objectClazz);

	/**
	 * <p>
	 * Serialize on a string the object passed as parameter. The used data
	 * format is the one supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param object
	 *            object to serialize
	 * @return output
	 */
	<E> String serialize(E object);

	/**
	 * <p>
	 * Serialize on a file the object passed as parameter. The used data format
	 * is the one supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param object
	 *            object to serialize
	 * @param output
	 *            output
	 */
	<E> void serialize(E object, File output);

	/**
	 * <p>
	 * Serialize on an outputstream the object passed as parameter. The used
	 * data format is the one supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param object
	 *            object to serialize
	 * @param output
	 *            output
	 */
	<E> void serialize(E object, OutputStream output);

	/**
	 * <p>
	 * Serialize on an writer the object passed as parameter. The used data
	 * format is the one supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param object
	 *            object to serialize
	 * @param output
	 *            output
	 */
	<E> void serialize(E object, Writer output);

	/**
	 * <p>
	 * Serialize a collection on a string. The used data format is the one
	 * supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param list
	 *            list to serialize
	 * @param objectClazz
	 *            type of object to persist
	 * @return output
	 */
	<E> String serializeCollection(Collection<E> list, Class<E> objectClazz);

	/**
	 * <p>
	 * Serialize a collection on an output stream. The used data format is the
	 * one supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param list
	 *            list to serialize
	 * @param objectClazz
	 *            type of object to persist
	 * @param output
	 *            output
	 */
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, OutputStream output);

	/**
	 * <p>
	 * Serialize a collection on an output stream. The used data format is the
	 * one supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param list
	 *            list to serialize
	 * @param objectClazz
	 *            type of object to persist
	 * @param writer
	 *            output writer
	 */
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, Writer writer);


	/**
	 * <p>
	 * Serialize a collection on an file. The used data format is the one
	 * supported by the binding context
	 * </p>
	 *
	 * @param <E>
	 *            the element type
	 * @param list
	 *            list to serialize
	 * @param objectClazz
	 *            type of object to persist
	 * @param output
	 *            output
	 */
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, File output);

}
