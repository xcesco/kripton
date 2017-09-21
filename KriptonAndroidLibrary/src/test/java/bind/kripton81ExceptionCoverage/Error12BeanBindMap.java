package bind.kripton81ExceptionCoverage;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for Error12Bean
 *
 * @see Error12Bean
 */
@BindMap(Error12Bean.class)
public class Error12BeanBindMap extends AbstractMapper<Error12Bean> {
  @Override
  public int serializeOnJackson(Error12Bean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Error12Bean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Error12Bean object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("error12Bean");
    }

    // Persisted fields:

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Error12Bean parseOnJackson(JsonParser jacksonParser) throws Exception {
    Error12Bean instance = new Error12Bean();
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

      // No field to parse
    }
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Error12Bean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Error12Bean instance = new Error12Bean();
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

      // No field to parse
    }
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Error12Bean parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Error12Bean instance = new Error12Bean();
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
            // No property to manage here
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
