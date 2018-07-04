package commons.kripton86.test5;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean5
 *
 * @see Bean5
 */
@BindMap(Bean5.class)
public class Bean5BindMap extends AbstractMapper<Bean5> {
  @Override
  public int serializeOnJackson(Bean5 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    if (object.id!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("id", object.id);
    }

    // field test (mapped with "test")
    if (object.test!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("test", object.test);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean5 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    if (object.id!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("id", object.id);
    }

    // field test (mapped with "test")
    if (object.test!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("test", object.test);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean5 object, XMLSerializer xmlSerializer, int currentEventType) throws
      Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean5");
    }

    // Persisted fields:

    // field id (mapped with "id")
    if (object.id!=null) {
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.id));
      xmlSerializer.writeEndElement();
    }

    // field test (mapped with "test")
    if (object.test!=null) {
      xmlSerializer.writeStartElement("test");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.test));
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
  public Bean5 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean5 instance = new Bean5();
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
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.id=jacksonParser.getText();
            }
          break;
          case "test":
            // field test (mapped with "test")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.test=jacksonParser.getText();
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
  public Bean5 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean5 instance = new Bean5();
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
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.id=jacksonParser.getText();
            }
          break;
          case "test":
            // field test (mapped with "test")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.test=jacksonParser.getText();
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
  public Bean5 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean5 instance = new Bean5();
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
                  instance.id=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "test":
                  // property test (mapped on "test")
                  instance.test=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
