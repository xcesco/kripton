package shared.kripton47;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.XmlSerializer;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.persistence.xml.internal.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for UserIdentity
 *
 * @see UserIdentity
 */
@BindMap(UserIdentity.class)
public class UserIdentityBindMap extends AbstractMapper<UserIdentity> {
  /**
   * create new object instance
   */
  @Override
  public UserIdentity createInstance() {
    return new UserIdentity();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, UserIdentity object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field email (mapped with "email")
      if (object.getEmail()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("email", object.getEmail());
      }

      // field name (mapped with "name")
      if (object.getName()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.getName());
      }

      // field username (mapped with "username")
      if (object.getUsername()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("username", object.getUsername());
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(AbstractJacksonContext context, UserIdentity object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field email (mapped with "email")
      if (object.getEmail()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("email", object.getEmail());
      }

      // field name (mapped with "name")
      if (object.getName()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.getName());
      }

      // field username (mapped with "username")
      if (object.getUsername()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("username", object.getUsername());
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(KriptonXmlContext context, UserIdentity object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("userIdentity");
      }

      // Persisted fields:

      // field email (mapped with "email")
      if (object.getEmail()!=null) {
        xmlSerializer.writeStartElement("email");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getEmail()));
        xmlSerializer.writeEndElement();
      }

      // field name (mapped with "name")
      if (object.getName()!=null) {
        xmlSerializer.writeStartElement("name");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
        xmlSerializer.writeEndElement();
      }

      // field username (mapped with "username")
      if (object.getUsername()!=null) {
        xmlSerializer.writeStartElement("username");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getUsername()));
        xmlSerializer.writeEndElement();
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public UserIdentity parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      UserIdentity instance = createInstance();
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
            case "email":
              // field email (mapped with "email")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setEmail(jacksonParser.getText());
              }
            break;
            case "name":
              // field name (mapped with "name")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setName(jacksonParser.getText());
              }
            break;
            case "username":
              // field username (mapped with "username")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setUsername(jacksonParser.getText());
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public UserIdentity parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      UserIdentity instance = createInstance();
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
            case "email":
              // field email (mapped with "email")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setEmail(jacksonParser.getText());
              }
            break;
            case "name":
              // field name (mapped with "name")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setName(jacksonParser.getText());
              }
            break;
            case "username":
              // field username (mapped with "username")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setUsername(jacksonParser.getText());
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public UserIdentity parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      UserIdentity instance = createInstance();
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
                  case "email":
                    // property email (mapped on "email")
                    instance.setEmail(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "name":
                    // property name (mapped on "name")
                    instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "username":
                    // property username (mapped on "username")
                    instance.setUsername(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
