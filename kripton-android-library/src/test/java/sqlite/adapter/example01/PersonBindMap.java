package sqlite.adapter.example01;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Person
 *
 * @see Person
 */
@BindMap(Person.class)
public class PersonBindMap extends AbstractMapper<Person> {
  @Override
  public int serializeOnJackson(Person object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthDate (mapped with "birthDate")
    if (object.getBirthDate()!=null)  {
      fieldCount++;
      // using type adapter sqlite.adapter.example01.DateAdapter
      jacksonSerializer.writeNumberField("birthDate", TypeAdapterUtils.toData(DateAdapter.class, object.getBirthDate()));
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field surname (mapped with "surname")
    if (object.getSurname()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.getSurname());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Person object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthDate (mapped with "birthDate")
    if (object.getBirthDate()!=null)  {
      // using type adapter sqlite.adapter.example01.DateAdapter
      jacksonSerializer.writeStringField("birthDate", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(DateAdapter.class, object.getBirthDate())));
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field surname (mapped with "surname")
    if (object.getSurname()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.getSurname());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Person object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("person");
    }

    // Persisted fields:

    // field birthDate (mapped with "birthDate")
    // field trasformation java.lang.Long sqlite.adapter.example01.DateAdapter 
    if (object.getBirthDate()!=null)  {
      // using type adapter sqlite.adapter.example01.DateAdapter
      xmlSerializer.writeStartElement("birthDate");
      xmlSerializer.writeLong(TypeAdapterUtils.toData(DateAdapter.class, object.getBirthDate()));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field name (mapped with "name")
    if (object.getName()!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
      xmlSerializer.writeEndElement();
    }

    // field surname (mapped with "surname")
    if (object.getSurname()!=null) {
      xmlSerializer.writeStartElement("surname");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getSurname()));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
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
          case "birthDate":
            // field birthDate (mapped with "birthDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter sqlite.adapter.example01.DateAdapter
              instance.setBirthDate(TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getLongValue()));
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setSurname(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson
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
          case "birthDate":
            // field birthDate (mapped with "birthDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter sqlite.adapter.example01.DateAdapter
              instance.setBirthDate(TypeAdapterUtils.toJava(DateAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null)));
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setSurname(jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml
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
                case "birthDate":
                  // property birthDate (mapped on "birthDate")
                  // using type adapter sqlite.adapter.example01.DateAdapter
                  instance.setBirthDate(TypeAdapterUtils.toJava(DateAdapter.class, PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null)));
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "surname":
                  // property surname (mapped on "surname")
                  instance.setSurname(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
