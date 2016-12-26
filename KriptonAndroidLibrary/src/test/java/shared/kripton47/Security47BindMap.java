package shared.kripton47;

import com.abubusoft.kripton.AbstractContext;
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
 * This class is the shared preference binder defined for Security47
 *
 * @see Security47
 */
@BindMap(Security47.class)
public class Security47BindMap extends AbstractMapper<Security47> {
  /**
   * DeviceAccessTokenBindMap */
  private DeviceAccessTokenBindMap deviceAccessTokenBindMap;

  /**
   * UserIdentityBindMap */
  private UserIdentityBindMap userIdentityBindMap;

  /**
   * create new object instance
   */
  @Override
  public Security47 createInstance() {
    return new Security47();
  }

  private DeviceAccessTokenBindMap deviceAccessTokenBindMap() {
    if (deviceAccessTokenBindMap==null) {
      deviceAccessTokenBindMap=AbstractContext.mapperFor(DeviceAccessToken.class);
    }
    return deviceAccessTokenBindMap;
  }

  private UserIdentityBindMap userIdentityBindMap() {
    if (userIdentityBindMap==null) {
      userIdentityBindMap=AbstractContext.mapperFor(UserIdentity.class);
    }
    return userIdentityBindMap;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Security47 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field authorizationToken (mapped with "authorizationToken")
      if (object.authorizationToken!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("authorizationToken");
        deviceAccessTokenBindMap().serializeOnJackson(context, object.authorizationToken, wrapper);
      }

      // field deviceUid (mapped with "deviceUid")
      if (object.deviceUid!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("deviceUid", object.deviceUid);
      }

      // field fcmId (mapped with "fcmId")
      if (object.fcmId!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("fcmId", object.fcmId);
      }

      // field userIdentity (mapped with "userIdentity")
      if (object.userIdentity!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("userIdentity");
        userIdentityBindMap().serializeOnJackson(context, object.userIdentity, wrapper);
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Security47 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field authorizationToken (mapped with "authorizationToken")
      if (object.authorizationToken!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("authorizationToken");
        if (deviceAccessTokenBindMap().serializeOnJacksonAsString(context, object.authorizationToken, wrapper)==0) {
          jacksonSerializer.writeNullField("authorizationToken");
        }
      }

      // field deviceUid (mapped with "deviceUid")
      if (object.deviceUid!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("deviceUid", object.deviceUid);
      }

      // field fcmId (mapped with "fcmId")
      if (object.fcmId!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("fcmId", object.fcmId);
      }

      // field userIdentity (mapped with "userIdentity")
      if (object.userIdentity!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("userIdentity");
        if (userIdentityBindMap().serializeOnJacksonAsString(context, object.userIdentity, wrapper)==0) {
          jacksonSerializer.writeNullField("userIdentity");
        }
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
  public void serializeOnXml(KriptonXmlContext context, Security47 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("security47");
      }

      // Persisted fields:

      // field authorizationToken (mapped with "authorizationToken")
      if (object.authorizationToken!=null)  {
        xmlSerializer.writeStartElement("authorizationToken");
        deviceAccessTokenBindMap().serializeOnXml(context, object.authorizationToken, wrapper, 2);
        xmlSerializer.writeEndElement();
      }

      // field deviceUid (mapped with "deviceUid")
      if (object.deviceUid!=null) {
        xmlSerializer.writeStartElement("deviceUid");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.deviceUid));
        xmlSerializer.writeEndElement();
      }

      // field fcmId (mapped with "fcmId")
      if (object.fcmId!=null) {
        xmlSerializer.writeStartElement("fcmId");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.fcmId));
        xmlSerializer.writeEndElement();
      }

      // field userIdentity (mapped with "userIdentity")
      if (object.userIdentity!=null)  {
        xmlSerializer.writeStartElement("userIdentity");
        userIdentityBindMap().serializeOnXml(context, object.userIdentity, wrapper, 2);
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
  public Security47 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Security47 instance = createInstance();
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
            case "authorizationToken":
              // field authorizationToken (mapped with "authorizationToken")
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.authorizationToken=deviceAccessTokenBindMap().parseOnJackson(context, wrapper);
              }
            break;
            case "deviceUid":
              // field deviceUid (mapped with "deviceUid")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.deviceUid=jacksonParser.getText();
              }
            break;
            case "fcmId":
              // field fcmId (mapped with "fcmId")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.fcmId=jacksonParser.getText();
              }
            break;
            case "userIdentity":
              // field userIdentity (mapped with "userIdentity")
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.userIdentity=userIdentityBindMap().parseOnJackson(context, wrapper);
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
  public Security47 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Security47 instance = createInstance();
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
            case "authorizationToken":
              // field authorizationToken (mapped with "authorizationToken")
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                instance.authorizationToken=deviceAccessTokenBindMap().parseOnJacksonAsString(context, wrapper);
              }
            break;
            case "deviceUid":
              // field deviceUid (mapped with "deviceUid")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.deviceUid=jacksonParser.getText();
              }
            break;
            case "fcmId":
              // field fcmId (mapped with "fcmId")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.fcmId=jacksonParser.getText();
              }
            break;
            case "userIdentity":
              // field userIdentity (mapped with "userIdentity")
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                instance.userIdentity=userIdentityBindMap().parseOnJacksonAsString(context, wrapper);
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
  public Security47 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Security47 instance = createInstance();
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
                  case "authorizationToken":
                    // property authorizationToken (mapped on "authorizationToken")
                    instance.authorizationToken=deviceAccessTokenBindMap().parseOnXml(context, wrapper, eventType);
                  break;
                  case "deviceUid":
                    // property deviceUid (mapped on "deviceUid")
                    instance.deviceUid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "fcmId":
                    // property fcmId (mapped on "fcmId")
                    instance.fcmId=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "userIdentity":
                    // property userIdentity (mapped on "userIdentity")
                    instance.userIdentity=userIdentityBindMap().parseOnXml(context, wrapper, eventType);
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
