/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.exception.NoSuchMapperException;
import com.abubusoft.kripton.map.BindMapHelper;
import com.abubusoft.kripton.persistence.ParserWrapper;
import com.abubusoft.kripton.persistence.SerializerWrapper;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.util.BufferRecycler;

// TODO: Auto-generated Javadoc
/**
 * Abstract context. Contains basic method to work with persistence on different
 * data formats.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public abstract class AbstractContext implements BinderContext {

	/** The Constant buffer. */
	static final ThreadLocal<BufferRecycler> buffer = new ThreadLocal<BufferRecycler>() {

	};

	/** The Constant OBJECT_MAPPERS. */
	@SuppressWarnings("rawtypes")
	static final Map<Class, BinderMapper> OBJECT_MAPPERS = new ConcurrentHashMap<>();

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#canPersist(java.lang.Class)
	 */
	public boolean canPersist(Class<?> cls) {
		Object mapper = OBJECT_MAPPERS.get(cls);
		if (mapper == null) {
			// The only way the mapper wouldn't already be loaded into
			// OBJECT_MAPPERS is if it was compiled separately, but let's handle
			// it anyway
			String beanClassName = cls.getName();
			String mapperClassName = cls.getName() + KriptonBinder.MAPPER_CLASS_SUFFIX;
			try {
				Class<?> mapperClass = (Class<?>) Class.forName(mapperClassName);
				mapper = mapperClass.newInstance();
				// mapper.

				return true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(String.format("Class '%s' does not exist. Does '%s' have @BindType annotation?", mapperClassName, beanClassName));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	/**
	 * Gets the mapper.
	 *
	 * @param <E> the element type
	 * @param <M> the generic type
	 * @param cls the cls
	 * @return the mapper
	 */
	@SuppressWarnings("unchecked")
	static <E, M extends BinderMapper<E>> M getMapper(Class<E> cls) {
		M mapper = (M) OBJECT_MAPPERS.get(cls);
		if (mapper == null) {
			// The only way the mapper wouldn't already be loaded into
			// OBJECT_MAPPERS is if it was compiled separately, but let's handle
			// it anyway
			String beanClassName = cls.getName();
			String mapperClassName = cls.getName() + KriptonBinder.MAPPER_CLASS_SUFFIX;
			try {
				Class<E> mapperClass = (Class<E>) Class.forName(mapperClassName);
				mapper = (M) mapperClass.newInstance();
				// mapper.
				OBJECT_MAPPERS.put(cls, mapper);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new KriptonRuntimeException(String.format("Class '%s' does not exist. Does '%s' have @BindType annotation?", mapperClassName, beanClassName));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return mapper;
	}

	/**
	 * <p>
	 * This method will be used by bean mapper to persists embedded objects.
	 * </p>
	 *
	 * @param <T> the generic type
	 * @param <M> the generic type
	 * @param cls            The class for which the JsonMapper should be fetched.
	 * @return the m
	 * @throws NoSuchMapperException the no such mapper exception
	 */
	static <T, M extends BinderMapper<T>> M mapperFor(Class<T> cls) throws NoSuchMapperException {
		M mapper = getMapper(cls);

		if (mapper == null) {
			throw new NoSuchMapperException(cls);
		} else {
			return mapper;
		}
	}

	/**
	 * Create a parser. It need to be public because it is used in generated
	 * classes.
	 * 
	 * @param data data
	 * @return
	 * 	parser
	 */
	public abstract ParserWrapper createParser(byte[] data);

	/**
	 * Create a parser. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param file the file
	 * @return 	parser
	 */
	public abstract ParserWrapper createParser(File file);

	/**
	 * Create a parser. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param in the in
	 * @return 	parser
	 */
	public abstract ParserWrapper createParser(InputStream in);

	/**
	 * Create a parser. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param reader the reader
	 * @return 	parser
	 */
	public abstract ParserWrapper createParser(Reader reader);

	/**
	 * Create a parser. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param content the content
	 * @return 	parser
	 */
	public abstract ParserWrapper createParser(String content);

	/**
	 * Create a serializer. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param file the file
	 * @return 	serializer
	 */
	public abstract SerializerWrapper createSerializer(File file);

	/**
	 * Create a serializer. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param file the file
	 * @param encoding the encoding
	 * @return 	serializer
	 */	
	public abstract SerializerWrapper createSerializer(File file, JsonEncoding encoding);

	/**
	 * Create a serializer. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param out the out
	 * @return 	serializer
	 */
	public abstract SerializerWrapper createSerializer(OutputStream out);

	/**
	 * Create a serializer. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param out the out
	 * @param encoding the encoding
	 * @return 	serializer
	 */
	public abstract SerializerWrapper createSerializer(OutputStream out, JsonEncoding encoding);

	/**
	 * Create a serializer. It need to be public because it is used in generated
	 * classes.
	 *
	 * @param writer the writer
	 * @return 	serializer
	 */
	public abstract SerializerWrapper createSerializer(Writer writer);

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parse(byte[], java.lang.Class)
	 */
	@Override
	public <E> E parse(byte[] source, Class<E> objectClazz) {
		try (ParserWrapper parserWrapper = createParser(source)) {
			E result = mapperFor(objectClazz).parse(this, parserWrapper);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parse(java.io.File, java.lang.Class)
	 */
	@Override
	public <E> E parse(File source, Class<E> objectClazz) {
		try (ParserWrapper parserWrapper = createParser(source)) {
			E result = mapperFor(objectClazz).parse(this, parserWrapper);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parse(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <E> E parse(InputStream source, Class<E> objectClazz) {
		try (ParserWrapper parserWrapper = createParser(source)) {
			E result = mapperFor(objectClazz).parse(this, parserWrapper);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parse(java.io.Reader, java.lang.Class)
	 */
	@Override
	public <E> E parse(Reader source, Class<E> objectClazz) {
		try (ParserWrapper parserWrapper = createParser(source)) {
			E result = mapperFor(objectClazz).parse(this, parserWrapper);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parse(java.lang.String, java.lang.Class)
	 */
	@Override
	public <E> E parse(String source, Class<E> objectClazz) {
		try (ParserWrapper parserWrapper = createParser(source)) {
			E result = mapperFor(objectClazz).parse(this, parserWrapper);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseCollection(byte[], java.util.Collection, java.lang.Class)
	 */
	@Override
	public <L extends Collection<E>, E> L parseCollection(byte[] source, L collection, Class<E> type) {
		if (collection == null || type == null)
			return null;

		try (ParserWrapper parserWrapper = createParser(source)) {
			L result = mapperFor(type).parseCollection(this, parserWrapper, collection);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseCollection(java.io.InputStream, java.util.Collection, java.lang.Class)
	 */
	@Override
	public <L extends Collection<E>, E> L parseCollection(InputStream source, L collection, Class<E> objectClazz) {
		if (collection == null || objectClazz == null)
			return null;

		try (ParserWrapper parserWrapper = createParser(source)) {
			L result = mapperFor(objectClazz).parseCollection(this, parserWrapper, collection);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseCollection(java.io.Reader, java.util.Collection, java.lang.Class)
	 */
	@Override
	public <L extends Collection<E>, E> L parseCollection(Reader source, L collection, Class<E> objectClazz) {
		if (collection == null || objectClazz == null)
			return null;

		try (ParserWrapper parserWrapper = createParser(source)) {
			L result = mapperFor(objectClazz).parseCollection(this, parserWrapper, collection);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseCollection(java.lang.String, java.util.Collection, java.lang.Class)
	 */
	@Override
	public <L extends Collection<E>, E> L parseCollection(String source, L collection, Class<E> type) {
		if (collection == null || type == null)
			return null;

		try (ParserWrapper parserWrapper = createParser(source)) {
			L result = mapperFor(type).parseCollection(this, parserWrapper, collection);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseList(byte[], java.lang.Class)
	 */
	@Override
	public <E> List<E> parseList(byte[] source, Class<E> objectClazz) {
		return parseCollection(source, new ArrayList<E>(), objectClazz);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseList(java.io.InputStream, java.lang.Class)
	 */
	@Override
	public <E> List<E> parseList(InputStream source, Class<E> objectClazz) {
		return parseCollection(source, new ArrayList<E>(), objectClazz);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseList(java.io.Reader, java.lang.Class)
	 */
	@Override
	public <E> List<E> parseList(Reader source, Class<E> objectClazz) {
		return parseCollection(source, new ArrayList<E>(), objectClazz);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseList(java.lang.String, java.lang.Class)
	 */
	@Override
	public <E> List<E> parseList(String source, Class<E> objectClazz) {
		return parseCollection(source, new ArrayList<E>(), objectClazz);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseMap(java.io.InputStream)
	 */
	@Override
	public Map<String, Object> parseMap(InputStream source) {
		Map<String, Object> map = new LinkedHashMap<>();

		try (ParserWrapper parserWrapper = createParser(source)) {
			Map<String, Object> result = BindMapHelper.parseMap(this, parserWrapper, map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseMap(java.io.Reader)
	 */
	@Override
	public Map<String, Object> parseMap(Reader source) {
		Map<String, Object> map = new LinkedHashMap<>();

		try (ParserWrapper parserWrapper = createParser(source)) {
			Map<String, Object> result = BindMapHelper.parseMap(this, parserWrapper, map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#parseMap(java.lang.String)
	 */
	@Override
	public Map<String, Object> parseMap(String source) {
		Map<String, Object> map = new LinkedHashMap<>();

		try (ParserWrapper parserWrapper = createParser(source)) {
			Map<String, Object> result = BindMapHelper.parseMap(this, parserWrapper, map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serialize(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> String serialize(E object) {
		if (object == null)
			return null;

		SegmentedStringWriter source = new SegmentedStringWriter(buffer.get());
		try (SerializerWrapper serializer = createSerializer(source)) {
			mapperFor((Class<E>) object.getClass()).serialize(this, serializer, object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}

		return source.getAndClear();

	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serialize(java.lang.Object, java.io.File)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, File output) {
		if (object == null)
			return;

		try (SerializerWrapper serializer = createSerializer(output)) {
			mapperFor((Class<E>) object.getClass()).serialize(this, serializer, object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serialize(java.lang.Object, java.io.OutputStream)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, OutputStream source) {
		if (object == null)
			return;

		try (SerializerWrapper serializer = createSerializer(source)) {
			mapperFor((Class<E>) object.getClass()).serialize(this, serializer, object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serialize(java.lang.Object, java.io.Writer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> void serialize(E object, Writer output) {
		if (object == null)
			return;

		try (SerializerWrapper serializer = createSerializer(output)) {
			mapperFor((Class<E>) object.getClass()).serialize(this, serializer, object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serializeCollection(java.util.Collection, java.lang.Class)
	 */
	@Override
	public <E> String serializeCollection(Collection<E> collection, Class<E> objectClazz) {
		if (collection == null)
			return null;

		SegmentedStringWriter source = new SegmentedStringWriter(buffer.get());
		try (SerializerWrapper serializer = createSerializer(source)) {
			mapperFor(objectClazz).serializeCollection(this, serializer, collection);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
		return source.getAndClear();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serializeCollection(java.util.Collection, java.lang.Class, java.io.File)
	 */
	@Override
	public <E> void serializeCollection(Collection<E> collection, Class<E> objectClazz, File output) {
		if (collection == null)
			return;

		try (SerializerWrapper serializer = createSerializer(output)) {
			mapperFor(objectClazz).serializeCollection(this, serializer, collection);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.BinderContext#serializeCollection(java.util.Collection, java.lang.Class, java.io.OutputStream)
	 */
	@Override
	public <E> void serializeCollection(Collection<E> collection, Class<E> objectClazz, OutputStream output) {
		if (collection == null)
			return;

		try (SerializerWrapper serializer = createSerializer(output)) {
			mapperFor(objectClazz).serializeCollection(this, serializer, collection);
		} catch (Exception e) {
			e.printStackTrace();
			throw new KriptonRuntimeException(e);
		}
	}

}
