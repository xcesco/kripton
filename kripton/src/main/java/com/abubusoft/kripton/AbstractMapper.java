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

import java.io.IOException;
import java.util.Collection;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.ParserWrapper;
import com.abubusoft.kripton.persistence.SerializerWrapper;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


/**
 * The Class AbstractMapper.
 *
 * @param <E> the element type
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class AbstractMapper<E> implements BinderMapper<E> {

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.BinderMapper#parse(com.abubusoft.kripton.BinderContext, com.abubusoft.kripton.persistence.ParserWrapper)
     */
    @Override
    public E parse(BinderContext context, ParserWrapper parserWrapper) throws Exception {
        E instance;

        if (BinderType.XML == context.getSupportedFormat()) {
            instance = parseOnXml(((XmlWrapperParser) parserWrapper).xmlParser, EventType.START_DOCUMENT);
        } else {
            if (context.getSupportedFormat().onlyText)
                instance = parseOnJacksonAsString(((JacksonWrapperParser) parserWrapper).jacksonParser);
            else
                instance = parseOnJackson(((JacksonWrapperParser) parserWrapper).jacksonParser);
        }

        return instance;
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.BinderMapper#parseCollection(com.abubusoft.kripton.BinderContext, com.abubusoft.kripton.persistence.ParserWrapper, java.util.Collection)
     */
    @Override
    public <L extends Collection<E>> L parseCollection(BinderContext context, ParserWrapper parserWrapper, L collection) throws Exception {
        switch (context.getSupportedFormat()) {
            case XML: {
                throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
            }
            default: {
                JacksonWrapperParser wrapperParser = (JacksonWrapperParser) parserWrapper;
                JsonParser parser = wrapperParser.jacksonParser;

                try {
                    collection.clear();

                    if (parser.nextToken() != JsonToken.START_ARRAY) {
                        throw (new KriptonRuntimeException("Invalid input format"));
                    }
                    if (context.getSupportedFormat().onlyText) {
                        while (parser.nextToken() != JsonToken.END_ARRAY) {
                            collection.add(parseOnJacksonAsString(parser));
                        }
                    } else {
                        while (parser.nextToken() != JsonToken.END_ARRAY) {
                            collection.add(parseOnJackson(parser));
                        }
                    }
                    return collection;
                } catch (IOException e) {
                    e.printStackTrace();
                    throw (new KriptonRuntimeException(e));
                }
            }
        }
    }

    /**
     * Serialize an object using the contxt and the serializerWrapper.
     *
     * @param context           the context
     * @param object            the object
     * @param serializerWrapper the serializer wrapper
     * @param writeStartAndEnd  the write start and end
     * @throws Exception the exception
     */
    protected void serialize(BinderContext context, E object, SerializerWrapper serializerWrapper, boolean writeStartAndEnd) throws Exception {

        switch (context.getSupportedFormat()) {
            case XML:
                try {
                    XmlWrapperSerializer wrapper = ((XmlWrapperSerializer) serializerWrapper);
                    XMLSerializer xmlSerializer = wrapper.xmlSerializer;

                    if (writeStartAndEnd) {
                        xmlSerializer.writeStartDocument();
                    }
                    serializeOnXml(object, xmlSerializer, EventType.START_DOCUMENT);

                    if (writeStartAndEnd) {
                        xmlSerializer.writeEndDocument();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw (new KriptonRuntimeException(e));
                }
                break;
            default:
                if (context.getSupportedFormat().onlyText)
                    serializeOnJacksonAsString(object, ((JacksonWrapperSerializer) serializerWrapper).jacksonGenerator);
                else
                    serializeOnJackson(object, ((JacksonWrapperSerializer) serializerWrapper).jacksonGenerator);
                break;
        }
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.BinderMapper#serialize(com.abubusoft.kripton.BinderContext, com.abubusoft.kripton.persistence.SerializerWrapper, java.lang.Object)
     */
    @Override
    public void serialize(BinderContext context, SerializerWrapper serializerWrapper, E object) throws Exception {
        serialize(context, object, serializerWrapper, true);
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.BinderMapper#serializeCollection(com.abubusoft.kripton.BinderContext, com.abubusoft.kripton.persistence.SerializerWrapper, java.util.Collection)
     */
    @Override
    public void serializeCollection(BinderContext context, SerializerWrapper serializerWrapper, Collection<E> collection) throws Exception {
        if (BinderType.XML == context.getSupportedFormat()) {
            throw (new KriptonRuntimeException(context.getSupportedFormat() + " context does not support direct collection persistance"));
        }

        JacksonWrapperSerializer wrapper = (JacksonWrapperSerializer) serializerWrapper;
        JsonGenerator jacksonGenerator = wrapper.jacksonGenerator;

        try {
            jacksonGenerator.writeStartArray();
            if (context.getSupportedFormat().onlyText)
                for (E object : collection) {
                    serializeOnJacksonAsString(object, jacksonGenerator);
                }
            else
                for (E object : collection) {
                    serializeOnJackson(object, jacksonGenerator);
                }
            jacksonGenerator.writeEndArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw (new KriptonRuntimeException(e));
        }
    }

}
