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
package sqlite.quickstart.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for Geo.
 *
 * @see Geo
 */
@BindMap(Geo.class)
public class GeoBindMap extends AbstractMapper<Geo> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(Geo object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field lat (mapped with "lat")
    if (object.lat!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("lat", object.lat);
    }

    // field lng (mapped with "lng")
    if (object.lng!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("lng", object.lng);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(Geo object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field lat (mapped with "lat")
    if (object.lat!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("lat", object.lat);
    }

    // field lng (mapped with "lng")
    if (object.lng!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("lng", object.lng);
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
  public void serializeOnXml(Geo object, XMLSerializer xmlSerializer, int currentEventType) throws
      Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("geo");
    }

    // Persisted fields:

    // field lat (mapped with "lat")
    if (object.lat!=null) {
      xmlSerializer.writeStartElement("lat");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.lat));
      xmlSerializer.writeEndElement();
    }

    // field lng (mapped with "lng")
    if (object.lng!=null) {
      xmlSerializer.writeStartElement("lng");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.lng));
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
   * @return the geo
   * @throws Exception the exception
   */
  @Override
  public Geo parseOnJackson(JsonParser jacksonParser) throws Exception {
    Geo instance = new Geo();
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
          case "lat":
            // field lat (mapped with "lat")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.lat=jacksonParser.getText();
            }
          break;
          case "lng":
            // field lng (mapped with "lng")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.lng=jacksonParser.getText();
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
   * @return the geo
   * @throws Exception the exception
   */
  @Override
  public Geo parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Geo instance = new Geo();
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
          case "lat":
            // field lat (mapped with "lat")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.lat=jacksonParser.getText();
            }
          break;
          case "lng":
            // field lng (mapped with "lng")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.lng=jacksonParser.getText();
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
   * @return the geo
   * @throws Exception the exception
   */
  @Override
  public Geo parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Geo instance = new Geo();
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
                case "lat":
                  // property lat (mapped on "lat")
                  instance.lat=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "lng":
                  // property lng (mapped on "lng")
                  instance.lng=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
