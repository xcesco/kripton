package bind.feature.generichierarchy.kripton109.settings;

import bind.feature.generichierarchy.kripton109.settings.logger.ElioLoggerLevelType;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;

/**
 * This class is binder map for LoggerSettings
 *
 * @see LoggerSettings
 */
@BindMap(LoggerSettings.class)
public class LoggerSettingsBindMap extends AbstractMapper<LoggerSettings> {
  /**
   * LoggerAppenderSettingsBindMap */
  private LoggerAppenderSettingsBindMap loggerAppenderSettingsBindMap = BinderUtils.mapperFor(LoggerAppenderSettings.class);

  @Override
  public int serializeOnJackson(LoggerSettings object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field appenders (mapped with "appenders")
    if (object.appenders!=null)  {
      fieldCount++;
      int n=object.appenders.size();
      LoggerAppenderSettings item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("appenders");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.appenders.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          loggerAppenderSettingsBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field level (mapped with "level")
    if (object.level!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("level", object.level.toString());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(LoggerSettings object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field appenders (mapped with "appenders")
    if (object.appenders!=null)  {
      fieldCount++;
      int n=object.appenders.size();
      LoggerAppenderSettings item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("appenders");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.appenders.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (loggerAppenderSettingsBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("appenders");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field level (mapped with "level")
    if (object.level!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("level", object.level.toString());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(LoggerSettings object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("loggerSettings");
    }

    // Persisted fields:

    // field level (mapped with "level")
    if (object.level!=null)  {
      xmlSerializer.writeAttribute("level", StringEscapeUtils.escapeXml10(object.level.toString()));
    }

    // field appenders (mapped with "appenders")
    if (object.appenders!=null)  {
      int n=object.appenders.size();
      LoggerAppenderSettings item;
      for (int i=0; i<n; i++) {
        item=object.appenders.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("appenders");
        } else {
          xmlSerializer.writeStartElement("appenders");
          loggerAppenderSettingsBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("appenders");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public LoggerSettings parseOnJackson(JsonParser jacksonParser) throws Exception {
    LoggerSettings instance = new LoggerSettings();
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
          case "appenders":
            // field appenders (mapped with "appenders")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<LoggerAppenderSettings> collection=new ArrayList<>();
              LoggerAppenderSettings item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=loggerAppenderSettingsBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.appenders=collection;
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
  public LoggerSettings parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    LoggerSettings instance = new LoggerSettings();
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
          case "appenders":
            // field appenders (mapped with "appenders")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<LoggerAppenderSettings> collection=new ArrayList<>();
              LoggerAppenderSettings item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=loggerAppenderSettingsBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.appenders=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<LoggerAppenderSettings> collection=new ArrayList<>();
              instance.appenders=collection;
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
  public LoggerSettings parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    LoggerSettings instance = new LoggerSettings();
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
            switch(currentTag) {
                case "appenders":
                  // property appenders (mapped on "appenders")
                   {
                    ArrayList<LoggerAppenderSettings> collection=CollectionUtils.merge(new ArrayList<>(), instance.appenders);
                    LoggerAppenderSettings item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=loggerAppenderSettingsBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("appenders")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=loggerAppenderSettingsBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.appenders=collection;
                    read=false;
                  }
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
