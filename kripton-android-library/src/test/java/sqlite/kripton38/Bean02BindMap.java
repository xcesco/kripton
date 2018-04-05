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
package sqlite.kripton38;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for Bean02.
 *
 * @see Bean02
 */
@BindMap(Bean02.class)
public class Bean02BindMap extends AbstractMapper<Bean02> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(Bean02 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field text (mapped with "text")
    if (object.getText()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("text", object.getText());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(Bean02 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field text (mapped with "text")
    if (object.getText()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("text", object.getText());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization.
   *
   * @param object the object
   * @param xmlSerializer the xml serializer
   * @param currentEventType the current event type
   * @throws Exception the exception
   */
  @Override
  public void serializeOnXml(Bean02 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean02");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field text (mapped with "text")
    if (object.getText()!=null) {
      xmlSerializer.writeStartElement("text");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getText()));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the bean 02
   * @throws Exception the exception
   */
  @Override
  public Bean02 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean02 instance = new Bean02();
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "id":
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "text":
            // field text (mapped with "text")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setText(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the bean 02
   * @throws Exception the exception
   */
  @Override
  public Bean02 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean02 instance = new Bean02();
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "id":
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "text":
            // field text (mapped with "text")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setText(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml.
   *
   * @param xmlParser the xml parser
   * @param currentEventType the current event type
   * @return the bean 02
   * @throws Exception the exception
   */
  @Override
  public Bean02 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean02 instance = new Bean02();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;
    // No attributes found

    //sub-elements
    while (xmlParser.hasNext() && elementName!=null) {
      if (read) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      read=true;
      switch(eventType) {
          case XmlPullParser.START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "text":
                  // property text (mapped on "text")
                  instance.setText(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                default:
                break;
              }
            break;
            case XmlPullParser.END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case XmlPullParser.CDSECT:
            case XmlPullParser.TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }
  }
