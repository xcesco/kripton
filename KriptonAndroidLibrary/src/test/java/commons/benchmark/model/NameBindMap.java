package commons.benchmark.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
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
 * This class is the shared preference binder defined for Name
 *
 * @see Name
 */
@BindMap(Name.class)
public class NameBindMap extends AbstractMapper<Name> {
  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(Name object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field first (mapped with "first")
    if (object.first!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("first", object.first);
    }

    // field last (mapped with "last")
    if (object.last!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("last", object.last);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(Name object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field first (mapped with "first")
    if (object.first!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("first", object.first);
    }

    // field last (mapped with "last")
    if (object.last!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("last", object.last);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(Name object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("name");
    }

    // Persisted fields:

    // field first (mapped with "first")
    if (object.first!=null) {
      xmlSerializer.writeStartElement("first");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.first));
      xmlSerializer.writeEndElement();
    }

    // field last (mapped with "last")
    if (object.last!=null) {
      xmlSerializer.writeStartElement("last");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.last));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * create new object instance
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
          case "first":
            // field first (mapped with "first")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.first=jacksonParser.getText();
            }
          break;
          case "last":
            // field last (mapped with "last")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.last=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
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
          case "first":
            // field first (mapped with "first")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.first=jacksonParser.getText();
            }
          break;
          case "last":
            // field last (mapped with "last")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.last=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
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
                case "first":
                  // property first (mapped on "first")
                  instance.first=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "last":
                  // property last (mapped on "last")
                  instance.last=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
