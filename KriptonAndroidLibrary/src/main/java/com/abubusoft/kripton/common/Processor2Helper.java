package com.abubusoft.kripton.common;

import java.util.Collection;
import java.util.Map;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;

/**
 * @author Francesco Benincasa
 *
 */
public abstract class Processor2Helper {

	protected static BinderType format = BinderType.JSON;

	public static <E> String asString(Collection<E> value, Class<E> clazz) {
		return KriptonBinder2.getBinder(format).serializeCollection(value, clazz);
	}

	public static <V, K> String asString(Map<K, V> map, Class<K> keyClazz, Class<V> valueClazz) {
		return KriptonBinder2.getBinder(format).serializeMap(map, keyClazz, valueClazz);
	}

	// /**
	// * Convert a list in an UTF-8 byte array
	// *
	// * @param value
	// * @return
	// * UTF-8 byte array
	// */
	// public static byte[] asByteArray(Collection<?> value)
	// {
	// JsonBinderContext context = getBinderContext();
	//
	// String result;
	// try {
	// context.createSerializer().
	// result = writer.writeCollection(value);
	// return result.getBytes(StandardCharsets.UTF_8);
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (WriterException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// public static byte[] asByteArray(Collection<?> value, Charset charset)
	// {
	// BinderContext<?, ?> context = getBinderContext();
	//
	// String result;
	// try {
	// result = writer.writeCollection(value);
	// return result.getBytes(charset);
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (WriterException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// /**
	// * Convert a map in an UTF-8 byte array
	// *
	// * @param value
	// * @return
	// * UTF-8 byte array
	// */
	// public static byte[] asByteArray(Map<?, ?> value)
	// {
	// BinderContext<?, ?> context = getBinderContext();
	//
	// String result;
	// try {
	// result = writer.writeMap(value);
	// return result.getBytes(StandardCharsets.UTF_8);
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (WriterException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// public static byte[] asByteArray(Map<?, ?> value, Charset charset)
	// {
	// BinderContext<?, ?> context = getBinderContext();
	//
	// String result;
	// try {
	// result = writer.writeMap(value);
	// return result.getBytes(charset);
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (WriterException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// /**
	// * convert blog (byte[]) in a list of element of type clazz
	// * @param clazz
	// * item class
	// * @param input
	// * blob
	// * @return
	// * list of element extracted from blob
	// */
	// public static <E> List<E> asList(Class<E> clazz, byte[] input)
	// {
	// return asList(clazz, new String(input));
	// }
	//
	// /**
	// * convert blog (byte[]) in a list of element of type clazz
	// * @param clazz
	// * item class
	// * @param input
	// * blob
	// * @return
	// * list of element extracted from blob
	// */
	// public static <E> List<E> asList(Class<E> clazz, String input)
	// {
	// BinderContext<?, ?> context = getBinderContext();
	//
	// try {
	// List<E> result = reader.readCollection(new ArrayList<E>(),clazz, input);
	// return result;
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (ReaderException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// /**
	// * convert blog (byte[]) in a collection of element of type clazz
	// *
	// * @param collection
	// * @param clazz
	// * item class
	// * @param input
	// * blob
	// * @return
	// * list of element extracted from blob
	// */
	// public static <E, L extends Collection<E>> L asCollection(L collection, Class<E> clazz, byte[] input)
	// {
	// return asCollection(collection, clazz, new String(input));
	// }
	//
	// /**
	// * convert blog (byte[]) in a collection of element of type clazz
	// *
	// * @param collection
	// * @param clazz
	// * item class
	// * @param input
	// * blob
	// * @return
	// * list of element extracted from blob
	// */
	// public static <E, L extends Collection<E>> L asCollection(L collection, Class<E> clazz, String input)
	// {
	// BinderContext<?, ?> context = getBinderContext();
	//
	// try {
	// L result = (L) reader.readCollection(collection, clazz, input);
	// return result;
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (ReaderException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// public static <K, V, M extends Map<K, V>> M asMap(M map, Class<K> keyClazz, Class<V> valueClazz, byte[] input)
	// {
	// return asMap(map, keyClazz, valueClazz, new String(input));
	// }
	//
	// public static <K, V, M extends Map<K, V>> M asMap(M map, Class<K> keyClazz, Class<V> valueClazz, String input)
	// {
	// BinderContext<?, ?> context = getBinderContext();
	//
	// try {
	// M result = reader.readMap(map, keyClazz, valueClazz, input);
	// return result;
	// } catch (MappingException e) {
	// e.printStackTrace();
	// } catch (ReaderException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	//
	// public synchronized static JsonBinderContext getBinderContext() {
	// if (binderContext==null)
	// {
	// binderContext=new ThreadLocal<BinderContext<?,?>>() {
	//
	// @Override
	// protected BinderContext<?,?> initialValue() {
	// return (JsonBinderContext)KriptonBinder2.getBinder(BinderType.JSON);
	// }
	//
	// };
	// }
	//
	// return binderContext.get();
	// }
}
