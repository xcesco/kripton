package com.abubusoft.kripton.binder2.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.core.BinderMapper;
//import com.abubusoft.kripton.binder2.core.ParameterizedType;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.exception.NoSuchMapperException;

public abstract class AbstractContext {

	@SuppressWarnings("rawtypes")
	private static final Map<Class, BinderMapper> OBJECT_MAPPERS = new ConcurrentHashMap<>();

	static {
		// OBJECT_MAPPERS.put(String.class, new String$JsonMapper());
		// OBJECT_MAPPERS.put(Integer.class, new IntegerMapper());
		// OBJECT_MAPPERS.put(Long.class, new
		// kripton70.internal.Long.JsonMapper());
		/*
		 * OBJECT_MAPPERS.put(Float.class, new FloatMapper()); OBJECT_MAPPERS.put(Double.class, new DoubleMapper()); OBJECT_MAPPERS.put(Boolean.class, new BooleanMapper()); OBJECT_MAPPERS.put(Object.class, new ObjectMapper());
		 * OBJECT_MAPPERS.put(List.class, LIST_MAPPER); OBJECT_MAPPERS.put(ArrayList.class, LIST_MAPPER); OBJECT_MAPPERS.put(Map.class, MAP_MAPPER); OBJECT_MAPPERS.put(HashMap.class, MAP_MAPPER);
		 */
	}

	/**
	 * Returns a JsonMapper for a given class that has been annotated with @JsonObject.
	 *
	 * @param cls
	 *            The class for which the JsonMapper should be fetched.
	 */
	public <T, M extends BinderMapper<T>> M mapperFor(Class<T> cls) throws NoSuchMapperException {
		M mapper = getMapper(cls);

		if (mapper == null) {
			throw new NoSuchMapperException(cls, getSupportedFormat());
		} else {
			return mapper;
		}
	}

/*
	@SuppressWarnings("unchecked")
	public <E, M extends BinderMapper<E>> M mapperFor(ParameterizedType<E> type) throws NoSuchMapperException {
		return (M) mapperFor(type, null);
	}
	
	public <E, M extends BinderMapper<E>> M mapperFor(ParameterizedType<E> type, @SuppressWarnings("rawtypes") SimpleArrayMap<ParameterizedType, BinderMapper> partialMappers) throws NoSuchMapperException {
		M mapper = getMapper(type, partialMappers);
		if (mapper == null) {
			throw new NoSuchMapperException(type.rawType, getSupportedFormat());
		} else {
			return mapper;
		}
	}
*/
	/*
	public static <E, M extends BinderMapper<E>> M getMapper(ParameterizedType<E> type, SimpleArrayMap<ParameterizedType, BinderMapper> partialMappers) {
		//
		// if (type.typeParameters.size() == 0) {
		// return getMapper((Class<E>) type.rawType);
		// }
		//
		// if (partialMappers == null) {
		// partialMappers = new SimpleArrayMap<ParameterizedType, JsonMapper>();
		// }
		//
		// if (partialMappers.containsKey(type)) {
		// return partialMappers.get(type);
		// } else if (PARAMETERIZED_OBJECT_MAPPERS.containsKey(type)) {
		// return PARAMETERIZED_OBJECT_MAPPERS.get(type);
		// } else {
		// try {
		// Class<?> mapperClass = Class.forName(type.rawType.getName() + Constants.MAPPER_CLASS_SUFFIX);
		// Constructor constructor = mapperClass.getDeclaredConstructors()[0];
		// Object[] args = new Object[2 + type.typeParameters.size()];
		// args[0] = type;
		// args[args.length - 1] = partialMappers;
		// for (int i = 0; i < type.typeParameters.size(); i++) {
		// args[i + 1] = type.typeParameters.get(i);
		// }
		// JsonMapper<E> mapper = (JsonMapper<E>) constructor.newInstance(args);
		// PARAMETERIZED_OBJECT_MAPPERS.put(type, mapper);
		// return mapper;
		// } catch (Exception ignored) {
		// return null;
		// }
		// }
		//
		return null;
	}*/

	@SuppressWarnings("unchecked")
	public <E, M extends BinderMapper<E>> M getMapper(Class<E> cls) {
		M mapper = (M) OBJECT_MAPPERS.get(cls);
		if (mapper == null) {
			// The only way the mapper wouldn't already be loaded into
			// OBJECT_MAPPERS is if it was compiled separately, but let's handle
			// it anyway
			try {
				Class<E> mapperClass = (Class<E>) Class.forName(cls.getName() + KriptonBinder2.MAPPER_CLASS_SUFFIX);
				mapper = (M) mapperClass.newInstance();
				// mapper.
				OBJECT_MAPPERS.put(cls, mapper);
			} catch (Exception e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(e);
			}
		}
		return mapper;
	}

	public abstract BinderType getSupportedFormat();

}
