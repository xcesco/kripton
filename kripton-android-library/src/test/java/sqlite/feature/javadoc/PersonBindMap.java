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
package sqlite.feature.javadoc;

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

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field personName (mapped with "personName")
    if (object.getPersonName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("personName", object.getPersonName());
    }

    // field personSurname (mapped with "personSurname")
    if (object.getPersonSurname()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("personSurname", object.getPersonSurname());
    }

    // field student (mapped with "student")
    fieldCount++;
    jacksonSerializer.writeBooleanField("student", object.isStudent());

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

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field personName (mapped with "personName")
    if (object.getPersonName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("personName", object.getPersonName());
    }

    // field personSurname (mapped with "personSurname")
    if (object.getPersonSurname()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("personSurname", object.getPersonSurname());
    }

    // field student (mapped with "student")
    jacksonSerializer.writeStringField("student", PrimitiveUtils.writeBoolean(object.isStudent()));

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

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field personName (mapped with "personName")
    if (object.getPersonName()!=null) {
      xmlSerializer.writeStartElement("personName");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getPersonName()));
      xmlSerializer.writeEndElement();
    }

    // field personSurname (mapped with "personSurname")
    if (object.getPersonSurname()!=null) {
      xmlSerializer.writeStartElement("personSurname");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getPersonSurname()));
      xmlSerializer.writeEndElement();
    }

    // field student (mapped with "student")
    xmlSerializer.writeStartElement("student");
    xmlSerializer.writeBoolean(object.isStudent());
    xmlSerializer.writeEndElement();

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
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "personName":
            // field personName (mapped with "personName")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPersonName(jacksonParser.getText());
            }
          break;
          case "personSurname":
            // field personSurname (mapped with "personSurname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPersonSurname(jacksonParser.getText());
            }
          break;
          case "student":
            // field student (mapped with "student")
            instance.setStudent(jacksonParser.getBooleanValue());
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
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "personName":
            // field personName (mapped with "personName")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPersonName(jacksonParser.getText());
            }
          break;
          case "personSurname":
            // field personSurname (mapped with "personSurname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPersonSurname(jacksonParser.getText());
            }
          break;
          case "student":
            // field student (mapped with "student")
            instance.setStudent(PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false));
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
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "personName":
                  // property personName (mapped on "personName")
                  instance.setPersonName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "personSurname":
                  // property personSurname (mapped on "personSurname")
                  instance.setPersonSurname(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "student":
                  // property student (mapped on "student")
                  instance.setStudent(PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false));
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
