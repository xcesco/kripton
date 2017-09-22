package sqlite.feature.typeadapter;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for Contact
 *
 * @see Contact
 */
@BindMap(Contact.class)
public class ContactBindMap extends AbstractMapper<Contact> {
  @Override
  public int serializeOnJackson(Contact object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthDay (mapped with "birthDay")
    if (object.birthDay!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthDay", DateUtils.write(object.birthDay));
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field password (mapped with "password")
    if (object.getPassword()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("password", object.getPassword());
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.type.toString());
    }

    // field updateDate (mapped with "updateDate")
    if (object.updateDate!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("updateDate", SQLDateUtils.write(object.updateDate));
    }

    // field updateTime (mapped with "updateTime")
    if (object.updateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("updateTime", SQLTimeUtils.write(object.updateTime));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Contact object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthDay (mapped with "birthDay")
    if (object.birthDay!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthDay", DateUtils.write(object.birthDay));
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field password (mapped with "password")
    if (object.getPassword()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("password", object.getPassword());
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.type.toString());
    }

    // field updateDate (mapped with "updateDate")
    if (object.updateDate!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("updateDate", SQLDateUtils.write(object.updateDate));
    }

    // field updateTime (mapped with "updateTime")
    if (object.updateTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("updateTime", SQLTimeUtils.write(object.updateTime));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Contact object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("contact");
    }

    // Persisted fields:

    // field birthDay (mapped with "birthDay")
    if (object.birthDay!=null)  {
      xmlSerializer.writeStartElement("birthDay");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.birthDay)));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field password (mapped with "password")
    if (object.getPassword()!=null) {
      xmlSerializer.writeStartElement("password");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getPassword()));
      xmlSerializer.writeEndElement();
    }

    // field surname (mapped with "surname")
    if (object.surname!=null) {
      xmlSerializer.writeStartElement("surname");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.surname));
      xmlSerializer.writeEndElement();
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.type.toString()));
      xmlSerializer.writeEndElement();
    }

    // field updateDate (mapped with "updateDate")
    if (object.updateDate!=null)  {
      xmlSerializer.writeStartElement("updateDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(SQLDateUtils.write(object.updateDate)));
      xmlSerializer.writeEndElement();
    }

    // field updateTime (mapped with "updateTime")
    if (object.updateTime!=null)  {
      xmlSerializer.writeStartElement("updateTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(SQLTimeUtils.write(object.updateTime)));
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
  public Contact parseOnJackson(JsonParser jacksonParser) throws Exception {
    Contact instance = new Contact();
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
          case "birthDay":
            // field birthDay (mapped with "birthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthDay=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "password":
            // field password (mapped with "password")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPassword(jacksonParser.getText());
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.surname=jacksonParser.getText();
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.type=StringUtils.hasText(tempEnum)?ContactType.valueOf(tempEnum):null;
            }
          break;
          case "updateDate":
            // field updateDate (mapped with "updateDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.updateDate=SQLDateUtils.read(jacksonParser.getText());
            }
          break;
          case "updateTime":
            // field updateTime (mapped with "updateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.updateTime=SQLTimeUtils.read(jacksonParser.getText());
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
  public Contact parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Contact instance = new Contact();
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
          case "birthDay":
            // field birthDay (mapped with "birthDay")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthDay=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "password":
            // field password (mapped with "password")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPassword(jacksonParser.getText());
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.surname=jacksonParser.getText();
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.type=StringUtils.hasText(tempEnum)?ContactType.valueOf(tempEnum):null;
            }
          break;
          case "updateDate":
            // field updateDate (mapped with "updateDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.updateDate=SQLDateUtils.read(jacksonParser.getText());
            }
          break;
          case "updateTime":
            // field updateTime (mapped with "updateTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.updateTime=SQLTimeUtils.read(jacksonParser.getText());
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
  public Contact parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Contact instance = new Contact();
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
                case "birthDay":
                  // property birthDay (mapped on "birthDay")
                  instance.birthDay=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "password":
                  // property password (mapped on "password")
                  instance.setPassword(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "surname":
                  // property surname (mapped on "surname")
                  instance.surname=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "type":
                  // property type (mapped on "type")
                  instance.type=ContactType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "updateDate":
                  // property updateDate (mapped on "updateDate")
                  instance.updateDate=SQLDateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "updateTime":
                  // property updateTime (mapped on "updateTime")
                  instance.updateTime=SQLTimeUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
