package kripton70.contexts;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonEncoding;

import kripton70.NoSuchMapperException;
import kripton70.core.BinderType;
import kripton70.core.JacksonMapper;
import kripton70.core.KriptonLibrary2;
import kripton70.core.ParameterizedType;
import util.SimpleArrayMap;

public abstract class AbstractBinderContext implements BinderContext {

	private static final Map<Class<?>, JacksonMapper<?>> OBJECT_MAPPERS = new ConcurrentHashMap<>();

	static {
		// OBJECT_MAPPERS.put(String.class, new String$JsonMapper());
		// OBJECT_MAPPERS.put(Integer.class, new IntegerMapper());
		// OBJECT_MAPPERS.put(Long.class, new
		// kripton70.internal.Long.JsonMapper());
		/*
		 * OBJECT_MAPPERS.put(Float.class, new FloatMapper());
		 * OBJECT_MAPPERS.put(Double.class, new DoubleMapper());
		 * OBJECT_MAPPERS.put(Boolean.class, new BooleanMapper());
		 * OBJECT_MAPPERS.put(Object.class, new ObjectMapper());
		 * OBJECT_MAPPERS.put(List.class, LIST_MAPPER);
		 * OBJECT_MAPPERS.put(ArrayList.class, LIST_MAPPER);
		 * OBJECT_MAPPERS.put(Map.class, MAP_MAPPER);
		 * OBJECT_MAPPERS.put(HashMap.class, MAP_MAPPER);
		 */
	}

	private static <E> JacksonMapper<E> getMapper(ParameterizedType<E> type, SimpleArrayMap<ParameterizedType, JacksonMapper> partialMappers) {
		/*
		 * if (type.typeParameters.size() == 0) { return getMapper((Class<E>)
		 * type.rawType); }
		 * 
		 * if (partialMappers == null) { partialMappers = new
		 * SimpleArrayMap<ParameterizedType, JsonMapper>(); }
		 * 
		 * if (partialMappers.containsKey(type)) { return
		 * partialMappers.get(type); } else if
		 * (PARAMETERIZED_OBJECT_MAPPERS.containsKey(type)) { return
		 * PARAMETERIZED_OBJECT_MAPPERS.get(type); } else { try { Class<?>
		 * mapperClass = Class.forName(type.rawType.getName() +
		 * Constants.MAPPER_CLASS_SUFFIX); Constructor constructor =
		 * mapperClass.getDeclaredConstructors()[0]; Object[] args = new
		 * Object[2 + type.typeParameters.size()]; args[0] = type;
		 * args[args.length - 1] = partialMappers; for (int i = 0; i <
		 * type.typeParameters.size(); i++) { args[i + 1] =
		 * type.typeParameters.get(i); } JsonMapper<E> mapper = (JsonMapper<E>)
		 * constructor.newInstance(args); PARAMETERIZED_OBJECT_MAPPERS.put(type,
		 * mapper); return mapper; } catch (Exception ignored) { return null; }
		 * }
		 */

		return null;
	}

	public BinderSerializer createSerializer(File file) {
		return createSerializer(file, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(OutputStream out) {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	@SuppressWarnings("unchecked")
	public <E, M extends JacksonMapper<E>> M getMapper(Class<E> cls) {
		M mapper = (M) OBJECT_MAPPERS.get(cls);
		if (mapper == null) {
			// The only way the mapper wouldn't already be loaded into
			// OBJECT_MAPPERS is if it was compiled separately, but let's handle
			// it anyway
			try {
				Class<?> mapperClass = Class.forName(cls.getName() + KriptonLibrary2.MAPPER_CLASS_SUFFIX);
				mapper = (M) mapperClass.newInstance();
				// mapper.
				OBJECT_MAPPERS.put(cls, mapper);
			} catch (Exception ignored) {
			}
		}
		return mapper;
	}

	public abstract BinderType getSupportedFormat();

	/**
	 * Returns a JsonMapper for a given class that has been annotated
	 * with @JsonObject.
	 *
	 * @param cls
	 *            The class for which the JsonMapper should be fetched.
	 */
	public <T, M extends JacksonMapper<T>> M mapperFor(Class<T> cls) throws NoSuchMapperException {
		M mapper = getMapper(cls);

		if (mapper == null) {
			throw new NoSuchMapperException(cls);
		} else {
			return mapper;
		}
	}

	/**
	 * Returns a JsonMapper for a given class that has been annotated
	 * with @JsonObject.
	 *
	 * @param type
	 *            The ParameterizedType for which the JsonMapper should be
	 *            fetched.
	 */
	@SuppressWarnings("unchecked")
	public <E, M extends JacksonMapper<E>> M mapperFor(ParameterizedType<E> type) throws NoSuchMapperException {
		return (M) mapperFor(type, null);
	}

	public <E> JacksonMapper<E> mapperFor(ParameterizedType<E> type, @SuppressWarnings("rawtypes") SimpleArrayMap<ParameterizedType, JacksonMapper> partialMappers) throws NoSuchMapperException {
		JacksonMapper<E> mapper = getMapper(type, partialMappers);
		if (mapper == null) {
			throw new NoSuchMapperException(type.rawType);
		} else {
			return mapper;
		}
	}

	// -------------
	/**
	 * Parse an object from an InputStream.
	 *
	 * @param is
	 *            The InputStream, most likely from your networking library.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	public <E> E parse(InputStream is, Class<E> jsonObjectClass) {
		return mapperFor(jsonObjectClass).parse(this, is);
	}

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
	public <E> E parse(InputStream is, ParameterizedType<E> jsonObjectType) {
		return mapperFor(jsonObjectType).parse(this, is);
	}

	/**
	 * Parse an object from a String. Note: parsing from an InputStream should
	 * be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	public <E> E parse(String jsonString, Class<E> jsonObjectClass) {
		return mapperFor(jsonObjectClass).parse(this, jsonString);
	}

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
	public <E> E parse(String jsonString, ParameterizedType<E> jsonObjectType) {
		return mapperFor(jsonObjectType).parse(this, jsonString);
	}

	/**
	 * Parse a list of objects from an InputStream.
	 *
	 * @param is
	 *            The inputStream, most likely from your networking library.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	public <E> List<E> parseList(InputStream is, Class<E> jsonObjectClass) {
		return mapperFor(jsonObjectClass).parseList(this, is);
	}

	/**
	 * Parse a list of objects from a String. Note: parsing from an InputStream
	 * should be preferred over parsing from a String if possible.
	 *
	 * @param jsonString
	 *            The JSON string being parsed.
	 * @param jsonObjectClass
	 *            The @JsonObject class to parse the InputStream into
	 */
	public <E> List<E> parseList(String jsonString, Class<E> jsonObjectClass) {
		return mapperFor(jsonObjectClass).parseList(this, jsonString);
	}

	/**
	 * Serialize an object to a JSON String.
	 *
	 * @param object
	 *            The object to serialize.
	 */
	@SuppressWarnings("unchecked")
	public <E> String serialize(E object) {
		return mapperFor((Class<E>) object.getClass()).serialize(this, object);
	}

	/**
	 * Serialize an object to an OutputStream.
	 *
	 * @param object
	 *            The object to serialize.
	 * @param os
	 *            The OutputStream being written to.
	 */
	@SuppressWarnings("unchecked")
	public <E> void serialize(E object, OutputStream os) {
		mapperFor((Class<E>) object.getClass()).serialize(this, object, os);
	}

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
	public <E> String serialize(E object, ParameterizedType<E> parameterizedType) {
		return mapperFor(parameterizedType).serialize(this, object);
	}

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
	public <E> void serialize(E object, ParameterizedType<E> parameterizedType, OutputStream os) {
		mapperFor(parameterizedType).serialize(this, object, os);
	}

	/**
	 * Serialize a list of objects to a JSON String.
	 *
	 * @param list
	 *            The list of objects to serialize.
	 * @param jsonObjectClass
	 *            The @JsonObject class of the list elements
	 */
	public <E> String serialize(List<E> list, Class<E> jsonObjectClass) {
		return mapperFor(jsonObjectClass).serialize(this, list);
	}

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
	public <E> void serialize(List<E> list, OutputStream os, Class<E> jsonObjectClass) {
		mapperFor(jsonObjectClass).serialize(this, list, os);
	}

}
