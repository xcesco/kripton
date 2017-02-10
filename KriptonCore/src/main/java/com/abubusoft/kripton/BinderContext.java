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
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public interface BinderContext {

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
	 * @param source
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
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(byte[] source, Class<E> objectClazz);

	/**
	 * <p>
	 * Parse a string to create and populate a list of
	 * <code>objectClazz</code> instance.
	 * </p>
	 * 
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(String source, Class<E> objectClazz);
	
	/**
	 * <p>
	 * Parse a string to create and populate a map of key/value. The key is the name of field, value is its content.
	 * </p>
	 * 
	 * @param source
	 *            input
	 * @return map of key/value.</code>
	 */
	Map<String, Object> parseMap(String source);

	/**
	 * <p>
	 * Parse a inputstream to create and populate a list of
	 * <code>objectClazz</code> instance.
	 * </p>
	 * 
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
	 * @param source
	 *            input
	 * @param objectClazz
	 *            class of object to create
	 * @return list of objects of type <code>objectClazz</code>
	 */
	<E> List<E> parseList(Reader source, Class<E> objectClazz);

	/**
	 * <p>
	 * Serialize on a string the object passed as parameter. The used data format
	 * is the one supported by the binding context
	 * </p>
	 * 
	 * @param object
	 * 		object to serialize
	 * @return
	 * 		output
	 */
	<E> String serialize(E object);

	/**
	 * <p>
	 * Serialize on a file the object passed as parameter. The used data format
	 * is the one supported by the binding context
	 * </p>
	 * 
	 * @param object
	 * 		object to serialize
	 * @param output
	 * 		output
	 */
	<E> void serialize(E object, File output) throws Exception;

	/**
	 * <p>
	 * Serialize on an outputstream the object passed as parameter. The used data format
	 * is the one supported by the binding context
	 * </p>
	 * 
	 * @param object
	 * 		object to serialize
	 * @param output
	 * 		output
	 */
	<E> void serialize(E object, OutputStream output) throws Exception;
	
	/**
	 * <p>
	 * Serialize on an writer the object passed as parameter. The used data format
	 * is the one supported by the binding context
	 * </p>
	 * 
	 * @param object
	 * 		object to serialize
	 * @param output
	 * 		output
	 */
	<E> void serialize(E object, Writer output) throws Exception;
	
	/**
	 * <p>
	 * Serialize on an writer the object passed as parameter. The used data format
	 * is the one supported by the binding context
	 * </p>
	 * 
	 * @param object
	 * 		object to serialize
	 * @param objectClazz
	 * 		
	 * @return
	 * 		output
	 */
	<E> String serializeCollection(Collection<E> list, Class<E> objectClazz);

	
	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, OutputStream output);

	<E> void serializeCollection(Collection<E> list, Class<E> objectClazz, File output);

}
