package bind.feature.generichierarchy.kripton109.settings;

import bind.feature.generichierarchy.kripton109.settings.logger.ElioLoggerLevelType;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
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
 * This class is binder map for LoggerAppenderSettings
 *
 * @see LoggerAppenderSettings
 */
@BindMap(LoggerAppenderSettings.class)
public class LoggerAppenderSettingsBindMap extends AbstractMapper<LoggerAppenderSettings> {
  @Override
  public int serializeOnJackson(LoggerAppenderSettings object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field level (mapped with "level")
    if (object.level!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("level", object.level.toString());
    }

    // field tag (mapped with "tag")
    if (object.tag!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("tag", object.tag);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(LoggerAppenderSettings object,
      JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field level (mapped with "level")
    if (object.level!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("level", object.level.toString());
    }

    // field tag (mapped with "tag")
    if (object.tag!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("tag", object.tag);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(LoggerAppenderSettings object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("loggerAppenderSettings");
    }

    // Persisted fields:

    // field level (mapped with "level")
    if (object.level!=null)  {
      xmlSerializer.writeAttribute("level", StringEscapeUtils.escapeXml10(object.level.toString()));
    }

    // field tag (mapped with "tag")
    if (object.tag!=null) {
      xmlSerializer.writeAttribute("tag", StringEscapeUtils.escapeXml10(object.tag));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public LoggerAppenderSettings parseOnJackson(JsonParser jacksonParser) throws Exception {
    LoggerAppenderSettings instance = new LoggerAppenderSettings();
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
          case "level":
            // field level (mapped with "level")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.level=StringUtils.hasText(tempEnum)?ElioLoggerLevelType.valueOf(tempEnum):null;
            }
          break;
          case "tag":
            // field tag (mapped with "tag")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.tag=jacksonParser.getText();
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
  public LoggerAppenderSettings parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    LoggerAppenderSettings instance = new LoggerAppenderSettings();
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
          case "level":
            // field level (mapped with "level")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.level=StringUtils.hasText(tempEnum)?ElioLoggerLevelType.valueOf(tempEnum):null;
            }
          break;
          case "tag":
            // field tag (mapped with "tag")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.tag=jacksonParser.getText();
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
  public LoggerAppenderSettings parseOnXml(XMLParser xmlParser, int currentEventType) throws
      Exception {
    LoggerAppenderSettings instance = new LoggerAppenderSettings();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;

    // attributes 
    String attributeName = null;
    int attributesCount = xmlParser.getAttributeCount();;
    for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
      attributeName = xmlParser.getAttributeName(attributeIndex);
      switch(attributeName) {
          case "level":
            // field level (mapped by "level")
            instance.level=ElioLoggerLevelType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex)));
          break;
          case "tag":
            // field tag (mapped by "tag")
            instance.tag=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
          break;
          default:
          break;
      }
    }

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
