package bind.feature.generichierarchy.kripton109.settings;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for ArgonSettings
 *
 * @see ArgonSettings
 */
@BindMap(ArgonSettings.class)
public class ArgonSettingsBindMap extends AbstractMapper<ArgonSettings> {
  /**
   * ApplicationSettingsBindMap */
  private ApplicationSettingsBindMap applicationSettingsBindMap = BinderUtils.mapperFor(ApplicationSettings.class);

  /**
   * LoggerSettingsBindMap */
  private LoggerSettingsBindMap loggerSettingsBindMap = BinderUtils.mapperFor(LoggerSettings.class);

  /**
   * OpenGLSettingsBindMap */
  private OpenGLSettingsBindMap openGLSettingsBindMap = BinderUtils.mapperFor(OpenGLSettings.class);

  /**
   * ViewFrustumSettingsBindMap */
  private ViewFrustumSettingsBindMap viewFrustumSettingsBindMap = BinderUtils.mapperFor(ViewFrustumSettings.class);

  @Override
  public int serializeOnJackson(ArgonSettings object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field application (mapped with "application")
    if (object.application!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("application");
      applicationSettingsBindMap.serializeOnJackson(object.application, jacksonSerializer);
    }

    // field logger (mapped with "logger")
    if (object.logger!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("logger");
      loggerSettingsBindMap.serializeOnJackson(object.logger, jacksonSerializer);
    }

    // field openGL (mapped with "openGL")
    if (object.openGL!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("openGL");
      openGLSettingsBindMap.serializeOnJackson(object.openGL, jacksonSerializer);
    }

    // field version (mapped with "version")
    if (object.version!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("version", object.version);
    }

    // field viewFrustum (mapped with "viewFrustum")
    if (object.viewFrustum!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("viewFrustum");
      viewFrustumSettingsBindMap.serializeOnJackson(object.viewFrustum, jacksonSerializer);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(ArgonSettings object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field application (mapped with "application")
    if (object.application!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("application");
      if (applicationSettingsBindMap.serializeOnJacksonAsString(object.application, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("application");
      }
    }

    // field logger (mapped with "logger")
    if (object.logger!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("logger");
      if (loggerSettingsBindMap.serializeOnJacksonAsString(object.logger, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("logger");
      }
    }

    // field openGL (mapped with "openGL")
    if (object.openGL!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("openGL");
      if (openGLSettingsBindMap.serializeOnJacksonAsString(object.openGL, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("openGL");
      }
    }

    // field version (mapped with "version")
    if (object.version!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("version", object.version);
    }

    // field viewFrustum (mapped with "viewFrustum")
    if (object.viewFrustum!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("viewFrustum");
      if (viewFrustumSettingsBindMap.serializeOnJacksonAsString(object.viewFrustum, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("viewFrustum");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(ArgonSettings object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("settings");
    }

    // Persisted fields:

    // field version (mapped with "version")
    if (object.version!=null) {
      xmlSerializer.writeAttribute("version", StringEscapeUtils.escapeXml10(object.version));
    }

    // field application (mapped with "application")
    if (object.application!=null)  {
      xmlSerializer.writeStartElement("application");
      applicationSettingsBindMap.serializeOnXml(object.application, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field logger (mapped with "logger")
    if (object.logger!=null)  {
      xmlSerializer.writeStartElement("logger");
      loggerSettingsBindMap.serializeOnXml(object.logger, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field openGL (mapped with "openGL")
    if (object.openGL!=null)  {
      xmlSerializer.writeStartElement("openGL");
      openGLSettingsBindMap.serializeOnXml(object.openGL, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field viewFrustum (mapped with "viewFrustum")
    if (object.viewFrustum!=null)  {
      xmlSerializer.writeStartElement("viewFrustum");
      viewFrustumSettingsBindMap.serializeOnXml(object.viewFrustum, xmlSerializer, 2);
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
  public ArgonSettings parseOnJackson(JsonParser jacksonParser) throws Exception {
    ArgonSettings instance = new ArgonSettings();
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
          case "version":
            // field version (mapped with "version")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.version=jacksonParser.getText();
            }
          break;
          case "application":
            // field application (mapped with "application")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.application=applicationSettingsBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "logger":
            // field logger (mapped with "logger")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.logger=loggerSettingsBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "openGL":
            // field openGL (mapped with "openGL")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.openGL=openGLSettingsBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "viewFrustum":
            // field viewFrustum (mapped with "viewFrustum")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.viewFrustum=viewFrustumSettingsBindMap.parseOnJackson(jacksonParser);
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
  public ArgonSettings parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ArgonSettings instance = new ArgonSettings();
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
          case "version":
            // field version (mapped with "version")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.version=jacksonParser.getText();
            }
          break;
          case "application":
            // field application (mapped with "application")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.application=applicationSettingsBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "logger":
            // field logger (mapped with "logger")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.logger=loggerSettingsBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "openGL":
            // field openGL (mapped with "openGL")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.openGL=openGLSettingsBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "viewFrustum":
            // field viewFrustum (mapped with "viewFrustum")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.viewFrustum=viewFrustumSettingsBindMap.parseOnJacksonAsString(jacksonParser);
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
  public ArgonSettings parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    ArgonSettings instance = new ArgonSettings();
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
          case "version":
            // field version (mapped by "version")
            instance.version=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
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
                case "application":
                  // property application (mapped on "application")
                  instance.application=applicationSettingsBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "logger":
                  // property logger (mapped on "logger")
                  instance.logger=loggerSettingsBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "openGL":
                  // property openGL (mapped on "openGL")
                  instance.openGL=openGLSettingsBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "viewFrustum":
                  // property viewFrustum (mapped on "viewFrustum")
                  instance.viewFrustum=viewFrustumSettingsBindMap.parseOnXml(xmlParser, eventType);
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
