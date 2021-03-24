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

import java.util.Collection;

import com.abubusoft.kripton.persistence.ParserWrapper;
import com.abubusoft.kripton.persistence.SerializerWrapper;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;


/**
 * <p>
 * Defines interface of generated bind map classes by Kripton. There are two
 * ways to persist object: as a single object or as a collection.
 * </p>
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <E> the element type
 */
public interface BinderMapper<E> {

	/**
	 * Parse an object.
	 *
	 * @param context the context
	 * @param parser the parser
	 * @return parsed object
	 * @throws Exception the exception
	 */
	E parse(BinderContext context, ParserWrapper parser) throws Exception;

	/**
	 * Parse collection of object.
	 *
	 * @param <L> the generic type
	 * @param context the context
	 * @param parser the parser
	 * @param collection the collection
	 * @return parsed collection
	 * @throws Exception the exception
	 */
	<L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parser, L collection) throws Exception;

	/**
	 * Read an object using jackson engine.
	 *
	 * @param jacksonParser the jackson parser
	 * @return parsed object
	 * @throws Exception the exception
	 */
	E parseOnJackson(JsonParser jacksonParser) throws Exception;

	/**
	 * Read an object from a string.
	 *
	 * @param jacksonParser the jackson parser
	 * @return parsed object
	 * @throws Exception the exception
	 */
	E parseOnJacksonAsString(JsonParser jacksonParser) throws Exception;

	/**
	 * Read an object from xml.
	 *
	 * @param xmlParser the xml parser
	 * @param currentEventType the current event type
	 * @return parsed object
	 * @throws Exception the exception
	 */
	E parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception;

	/**
	 * Serialize an object.
	 *
	 * @param context the context
	 * @param serializerWrapper the serializer wrapper
	 * @param object the object
	 * @throws Exception the exception
	 */
	void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object) throws Exception;

	/**
	 * Serialises a collection of object.
	 *
	 * @param context the context
	 * @param serializerWrapper the serializer wrapper
	 * @param collection the collection
	 * @throws Exception the exception
	 */
	void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection) throws Exception;

	/**
	 * Serialises an object.
	 *
	 * @param object the object
	 * @param jacksonSerializer the jackson serializer
	 * @return number of written object's fields
	 * @throws Exception the exception
	 */
	int serializeOnJackson(E object, JsonGenerator jacksonSerializer) throws Exception;

	/**
	 * Serialises an object on a string.
	 *
	 * @param object the object
	 * @param jacksonSerializer the jackson serializer
	 * @return number of written object's fields
	 * @throws Exception the exception
	 */
	int serializeOnJacksonAsString(E object, JsonGenerator jacksonSerializer) throws Exception;

	/**
	 * Serialises an object on xml.
	 *
	 * @param object the object
	 * @param xmlSerializer the xml serializer
	 * @param currentEventType the current event type
	 * @throws Exception the exception
	 */
	void serializeOnXml(E object, XMLSerializer xmlSerializer, EventType currentEventType) throws Exception;

	/**
	 * Initialize field mappers.
	 */
  void init();
}
