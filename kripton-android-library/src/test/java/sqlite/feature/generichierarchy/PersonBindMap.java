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
package sqlite.feature.generichierarchy;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.DateUtils;
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
 * This class is binder map for Person.
 *
 * @see Person
 */
@BindMap(Person.class)
public class PersonBindMap extends AbstractMapper<Person> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(Person object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthCity (mapped with "birthCity")
    if (object.birthCity!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthCity", object.birthCity);
    }

    // field birthDay (mapped with "birthDay")
    if (object.birthDay!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthDay", DateUtils.write(object.birthDay));
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(Person object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthCity (mapped with "birthCity")
    if (object.birthCity!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthCity", object.birthCity);
    }

    // field birthDay (mapped with "birthDay")
    if (object.birthDay!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthDay", DateUtils.write(object.birthDay));
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
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
  public void serializeOnXml(Person object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("person");
    }

    // Persisted fields:

    // field birthCity (mapped with "birthCity")
    if (object.birthCity!=null) {
      xmlSerializer.writeStartElement("birthCity");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.birthCity));
      xmlSerializer.writeEndElement();
    }

    // field birthDay (mapped with "birthDay")
    if (object.birthDay!=null)  {
      xmlSerializer.writeStartElement("birthDay");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.birthDay)));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
      xmlSerializer.writeEndElement();
    }

    // field surname (mapped with "surname")
    if (object.surname!=null) {
      xmlSerializer.writeStartElement("surname");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.surname));
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
   * @return the person
   * @throws Exception the exception
   */
  @Override
  public Person parseOnJackson(JsonParser jacksonParser) throws Exception {
    Person instance = new Person();
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
          case "birthCity":
            // field birthCity (mapped with "birthCity")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthCity=jacksonParser.getText();
            }
          break;
          case "birthDay":
            // field birthDay (mapped with "birthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthDay=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.surname=jacksonParser.getText();
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
   * @return the person
   * @throws Exception the exception
   */
  @Override
  public Person parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Person instance = new Person();
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
          case "birthCity":
            // field birthCity (mapped with "birthCity")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthCity=jacksonParser.getText();
            }
          break;
          case "birthDay":
            // field birthDay (mapped with "birthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthDay=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.surname=jacksonParser.getText();
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
   * @return the person
   * @throws Exception the exception
   */
  @Override
  public Person parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Person instance = new Person();
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
                case "birthCity":
                  // property birthCity (mapped on "birthCity")
                  instance.birthCity=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "birthDay":
                  // property birthDay (mapped on "birthDay")
                  instance.birthDay=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "surname":
                  // property surname (mapped on "surname")
                  instance.surname=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
