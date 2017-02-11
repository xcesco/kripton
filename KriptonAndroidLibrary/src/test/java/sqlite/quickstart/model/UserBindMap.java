package sqlite.quickstart.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.core.AbstractContext;
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
 * This class is binder map for User
 *
 * @see User
 */
@BindMap(User.class)
public class UserBindMap extends AbstractMapper<User> {
  /**
   * AddressBindMap */
  private AddressBindMap addressBindMap = AbstractContext.mapperFor(Address.class);

  /**
   * CompanyBindMap */
  private CompanyBindMap companyBindMap = AbstractContext.mapperFor(Company.class);

  @Override
  public int serializeOnJackson(User object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("address");
      addressBindMap.serializeOnJackson(object.address, jacksonSerializer);
    }

    // field company (mapped with "company")
    if (object.company!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("company");
      companyBindMap.serializeOnJackson(object.company, jacksonSerializer);
    }

    // field email (mapped with "email")
    if (object.email!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.email);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field phone (mapped with "phone")
    if (object.phone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("phone", object.phone);
    }

    // field username (mapped with "username")
    if (object.username!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("username", object.username);
    }

    // field website (mapped with "website")
    if (object.website!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("website", object.website);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(User object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("address");
      if (addressBindMap.serializeOnJacksonAsString(object.address, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("address");
      }
    }

    // field company (mapped with "company")
    if (object.company!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("company");
      if (companyBindMap.serializeOnJacksonAsString(object.company, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("company");
      }
    }

    // field email (mapped with "email")
    if (object.email!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.email);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field phone (mapped with "phone")
    if (object.phone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("phone", object.phone);
    }

    // field username (mapped with "username")
    if (object.username!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("username", object.username);
    }

    // field website (mapped with "website")
    if (object.website!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("website", object.website);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(User object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("user");
    }

    // Persisted fields:

    // field address (mapped with "address")
    if (object.address!=null)  {
      xmlSerializer.writeStartElement("address");
      addressBindMap.serializeOnXml(object.address, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field company (mapped with "company")
    if (object.company!=null)  {
      xmlSerializer.writeStartElement("company");
      companyBindMap.serializeOnXml(object.company, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field email (mapped with "email")
    if (object.email!=null) {
      xmlSerializer.writeStartElement("email");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.email));
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

    // field phone (mapped with "phone")
    if (object.phone!=null) {
      xmlSerializer.writeStartElement("phone");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.phone));
      xmlSerializer.writeEndElement();
    }

    // field username (mapped with "username")
    if (object.username!=null) {
      xmlSerializer.writeStartElement("username");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.username));
      xmlSerializer.writeEndElement();
    }

    // field website (mapped with "website")
    if (object.website!=null) {
      xmlSerializer.writeStartElement("website");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.website));
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
  public User parseOnJackson(JsonParser jacksonParser) throws Exception {
    User instance = new User();
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
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.address=addressBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "company":
            // field company (mapped with "company")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.company=companyBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.email=jacksonParser.getText();
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
          case "phone":
            // field phone (mapped with "phone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.phone=jacksonParser.getText();
            }
          break;
          case "username":
            // field username (mapped with "username")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.username=jacksonParser.getText();
            }
          break;
          case "website":
            // field website (mapped with "website")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.website=jacksonParser.getText();
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
  public User parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    User instance = new User();
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
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.address=addressBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "company":
            // field company (mapped with "company")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.company=companyBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.email=jacksonParser.getText();
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
          case "phone":
            // field phone (mapped with "phone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.phone=jacksonParser.getText();
            }
          break;
          case "username":
            // field username (mapped with "username")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.username=jacksonParser.getText();
            }
          break;
          case "website":
            // field website (mapped with "website")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.website=jacksonParser.getText();
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
  public User parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    User instance = new User();
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
                case "address":
                  // property address (mapped on "address")
                  instance.address=addressBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "company":
                  // property company (mapped on "company")
                  instance.company=companyBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "email":
                  // property email (mapped on "email")
                  instance.email=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "phone":
                  // property phone (mapped on "phone")
                  instance.phone=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "username":
                  // property username (mapped on "username")
                  instance.username=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "website":
                  // property website (mapped on "website")
                  instance.website=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
