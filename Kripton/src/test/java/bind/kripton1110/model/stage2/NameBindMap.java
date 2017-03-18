package bind.kripton1110.model.stage2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
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
 * This class is binder map for Name
 *
 * @see Name
 */
@BindMap(Name.class)
public class NameBindMap extends AbstractMapper<Name> {
  /**
   * NativeBindMap */
  private NativeBindMap nativeBindMap = AbstractContext.mapperFor(Native.class);

  @Override
  public int serializeOnJackson(Name object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field common (mapped with "common")
    if (object.common!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("common", object.common);
    }

    // field official (mapped with "official")
    if (object.official!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("official", object.official);
    }

    // field _native (mapped with "native")
    if (object._native!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("native");
      nativeBindMap.serializeOnJackson(object._native, jacksonSerializer);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Name object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field common (mapped with "common")
    if (object.common!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("common", object.common);
    }

    // field official (mapped with "official")
    if (object.official!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("official", object.official);
    }

    // field _native (mapped with "native")
    if (object._native!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("native");
      if (nativeBindMap.serializeOnJacksonAsString(object._native, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("native");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Name object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("name");
    }

    // Persisted fields:

    // field common (mapped with "common")
    if (object.common!=null) {
      xmlSerializer.writeStartElement("common");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.common));
      xmlSerializer.writeEndElement();
    }

    // field official (mapped with "official")
    if (object.official!=null) {
      xmlSerializer.writeStartElement("official");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.official));
      xmlSerializer.writeEndElement();
    }

    // field _native (mapped with "native")
    if (object._native!=null)  {
      xmlSerializer.writeStartElement("native");
      nativeBindMap.serializeOnXml(object._native, xmlSerializer, 2);
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
  public Name parseOnJackson(JsonParser jacksonParser) throws Exception {
    Name instance = new Name();
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
          case "common":
            // field common (mapped with "common")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.common=jacksonParser.getText();
            }
          break;
          case "official":
            // field official (mapped with "official")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.official=jacksonParser.getText();
            }
          break;
          case "native":
            // field _native (mapped with "native")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance._native=nativeBindMap.parseOnJackson(jacksonParser);
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
  public Name parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Name instance = new Name();
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
          case "common":
            // field common (mapped with "common")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.common=jacksonParser.getText();
            }
          break;
          case "official":
            // field official (mapped with "official")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.official=jacksonParser.getText();
            }
          break;
          case "native":
            // field _native (mapped with "native")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance._native=nativeBindMap.parseOnJacksonAsString(jacksonParser);
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
  public Name parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Name instance = new Name();
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
                case "common":
                  // property common (mapped on "common")
                  instance.common=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "official":
                  // property official (mapped on "official")
                  instance.official=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "native":
                  // property _native (mapped on "native")
                  instance._native=nativeBindMap.parseOnXml(xmlParser, eventType);
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
