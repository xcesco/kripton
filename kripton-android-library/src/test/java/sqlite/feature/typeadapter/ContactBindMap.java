package sqlite.feature.typeadapter;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

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
      // using type adapter sqlite.feature.typeadapter.DateAdapterType
      jacksonSerializer.writeNumberField("birthDay", TypeAdapterUtils.toData(DateAdapterType.class, object.birthDay));
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field password (mapped with "password")
    if (object.getPassword()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("password", TypeAdapterUtils.toData(PasswordAdapterType.class, object.getPassword()));
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      fieldCount++;
      // using type adapter sqlite.feature.typeadapter.EnumAdapterType
      jacksonSerializer.writeNumberField("type", TypeAdapterUtils.toData(EnumAdapterType.class, object.type));
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
      // using type adapter sqlite.feature.typeadapter.DateAdapterType
      jacksonSerializer.writeStringField("birthDay", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(DateAdapterType.class, object.birthDay)));
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field password (mapped with "password")
    if (object.getPassword()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("password", TypeAdapterUtils.toData(PasswordAdapterType.class, object.getPassword()));
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    // field type (mapped with "type")
    if (object.type!=null)  {
      // using type adapter sqlite.feature.typeadapter.EnumAdapterType
      jacksonSerializer.writeStringField("type", PrimitiveUtils.writeInteger(TypeAdapterUtils.toData(EnumAdapterType.class, object.type)));
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
  public void serializeOnXml(Contact object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("contact");
    }

    // Persisted fields:

    // field birthDay (mapped with "birthDay")
    // field trasformation java.lang.Long sqlite.feature.typeadapter.DateAdapterType 
    if (object.birthDay!=null)  {
      // using type adapter sqlite.feature.typeadapter.DateAdapterType
      xmlSerializer.writeStartElement("birthDay");
      xmlSerializer.writeLong(TypeAdapterUtils.toData(DateAdapterType.class, object.birthDay));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field password (mapped with "password")
    // field trasformation byte[] sqlite.feature.typeadapter.PasswordAdapterType 
    if (object.getPassword()!=null) {
      xmlSerializer.writeStartElement("password");
      xmlSerializer.writeBinary(TypeAdapterUtils.toData(PasswordAdapterType.class, object.getPassword()));
      xmlSerializer.writeEndElement();
    }

    // field surname (mapped with "surname")
    if (object.surname!=null) {
      xmlSerializer.writeStartElement("surname");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.surname));
      xmlSerializer.writeEndElement();
    }

    // field type (mapped with "type")
    // field trasformation java.lang.Integer sqlite.feature.typeadapter.EnumAdapterType 
    if (object.type!=null)  {
      // using type adapter sqlite.feature.typeadapter.EnumAdapterType
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeInt(TypeAdapterUtils.toData(EnumAdapterType.class, object.type));
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

    if (currentEventType == EventType.START_DOCUMENT) {
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
              // using type adapter sqlite.feature.typeadapter.DateAdapterType
              instance.birthDay=TypeAdapterUtils.toJava(DateAdapterType.class, jacksonParser.getLongValue());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "password":
            // field password (mapped with "password")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPassword(TypeAdapterUtils.toJava(PasswordAdapterType.class, jacksonParser.getBinaryValue()));
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
              // using type adapter sqlite.feature.typeadapter.EnumAdapterType
              instance.type=TypeAdapterUtils.toJava(EnumAdapterType.class, jacksonParser.getIntValue());
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
              // using type adapter sqlite.feature.typeadapter.DateAdapterType
              instance.birthDay=TypeAdapterUtils.toJava(DateAdapterType.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "password":
            // field password (mapped with "password")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setPassword(TypeAdapterUtils.toJava(PasswordAdapterType.class, Base64Utils.decode(jacksonParser.getValueAsString())));
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
              // using type adapter sqlite.feature.typeadapter.EnumAdapterType
              instance.type=TypeAdapterUtils.toJava(EnumAdapterType.class, PrimitiveUtils.readInteger(jacksonParser.getText(), null));
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
  public Contact parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Contact instance = new Contact();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
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
          case START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "birthDay":
                  // property birthDay (mapped on "birthDay")
                  // using type adapter sqlite.feature.typeadapter.DateAdapterType
                  instance.birthDay=TypeAdapterUtils.toJava(DateAdapterType.class, PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null));
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "password":
                  // property password (mapped on "password")
                  instance.setPassword(TypeAdapterUtils.toJava(PasswordAdapterType.class, xmlParser.getElementAsBinary()));
                break;
                case "surname":
                  // property surname (mapped on "surname")
                  instance.surname=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "type":
                  // property type (mapped on "type")
                  // using type adapter sqlite.feature.typeadapter.EnumAdapterType
                  instance.type=TypeAdapterUtils.toJava(EnumAdapterType.class, PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null));
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
                  xmlParser.skipChildren();
                break;
              }
            break;
            case END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case CDSECT:
            case TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }

    public void init() {
      // binding maps initialization 
    }
  }
