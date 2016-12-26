package sqlite.kripton33;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
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
 * This class is the shared preference binder defined for Channel
 *
 * @see Channel
 */
@BindMap(Channel.class)
public class ChannelBindMap extends AbstractMapper<Channel> {
  /**
   * create new object instance
   */
  @Override
  public Channel createInstance() {
    return new Channel();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Channel object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id (mapped with "id")
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.getId());

      // field name (mapped with "name")
      if (object.getName()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.getName());
      }

      // field ownerUid (mapped with "ownerUid")
      if (object.getOwnerUid()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("ownerUid", object.getOwnerUid());
      }

      // field updateTime (mapped with "updateTime")
      fieldCount++;
      jacksonSerializer.writeNumberField("updateTime", object.getUpdateTime());

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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Channel object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id (mapped with "id")
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

      // field name (mapped with "name")
      if (object.getName()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.getName());
      }

      // field ownerUid (mapped with "ownerUid")
      if (object.getOwnerUid()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("ownerUid", object.getOwnerUid());
      }

      // field updateTime (mapped with "updateTime")
      jacksonSerializer.writeStringField("updateTime", PrimitiveUtils.writeLong(object.getUpdateTime()));

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
  public void serializeOnXml(KriptonXmlContext context, Channel object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("channel");
      }

      // Persisted fields:

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

      // field ownerUid (mapped with "ownerUid")
      if (object.getOwnerUid()!=null) {
        xmlSerializer.writeStartElement("ownerUid");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getOwnerUid()));
        xmlSerializer.writeEndElement();
      }

      // field updateTime (mapped with "updateTime")
      xmlSerializer.writeStartElement("updateTime");
      xmlSerializer.writeLong(object.getUpdateTime());
      xmlSerializer.writeEndElement();

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
  public Channel parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Channel instance = createInstance();
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
            case "name":
              // field name (mapped with "name")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setName(jacksonParser.getText());
              }
            break;
            case "ownerUid":
              // field ownerUid (mapped with "ownerUid")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setOwnerUid(jacksonParser.getText());
              }
            break;
            case "updateTime":
              // field updateTime (mapped with "updateTime")
              instance.setUpdateTime(jacksonParser.getLongValue());
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
  public Channel parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Channel instance = createInstance();
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
            case "name":
              // field name (mapped with "name")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setName(jacksonParser.getText());
              }
            break;
            case "ownerUid":
              // field ownerUid (mapped with "ownerUid")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setOwnerUid(jacksonParser.getText());
              }
            break;
            case "updateTime":
              // field updateTime (mapped with "updateTime")
              instance.setUpdateTime(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
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
  public Channel parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Channel instance = createInstance();
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
                  case "name":
                    // property name (mapped on "name")
                    instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "ownerUid":
                    // property ownerUid (mapped on "ownerUid")
                    instance.setOwnerUid(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "updateTime":
                    // property updateTime (mapped on "updateTime")
                    instance.setUpdateTime(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
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
