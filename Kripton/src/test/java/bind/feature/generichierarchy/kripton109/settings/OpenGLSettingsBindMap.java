package bind.feature.generichierarchy.kripton109.settings;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for OpenGLSettings
 *
 * @see OpenGLSettings
 */
@BindMap(OpenGLSettings.class)
public class OpenGLSettingsBindMap extends AbstractMapper<OpenGLSettings> {
  @Override
  public int serializeOnJackson(OpenGLSettings object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field asyncMode (mapped with "openGLAsyncMode")
    fieldCount++;
    jacksonSerializer.writeBooleanField("openGLAsyncMode", object.asyncMode);

    // field debug (mapped with "openGLDebug")
    fieldCount++;
    jacksonSerializer.writeBooleanField("openGLDebug", object.debug);

    // field maxFPS (mapped with "openGLMaxFPS")
    fieldCount++;
    jacksonSerializer.writeNumberField("openGLMaxFPS", object.maxFPS);

    // field safeMode (mapped with "openGLSafeMode")
    fieldCount++;
    jacksonSerializer.writeBooleanField("openGLSafeMode", object.safeMode);

    // field version (mapped with "openGLVersion")
    fieldCount++;
    jacksonSerializer.writeNumberField("openGLVersion", object.version);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(OpenGLSettings object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field asyncMode (mapped with "openGLAsyncMode")
    jacksonSerializer.writeStringField("openGLAsyncMode", PrimitiveUtils.writeBoolean(object.asyncMode));

    // field debug (mapped with "openGLDebug")
    jacksonSerializer.writeStringField("openGLDebug", PrimitiveUtils.writeBoolean(object.debug));

    // field maxFPS (mapped with "openGLMaxFPS")
    jacksonSerializer.writeStringField("openGLMaxFPS", PrimitiveUtils.writeInteger(object.maxFPS));

    // field safeMode (mapped with "openGLSafeMode")
    jacksonSerializer.writeStringField("openGLSafeMode", PrimitiveUtils.writeBoolean(object.safeMode));

    // field version (mapped with "openGLVersion")
    jacksonSerializer.writeStringField("openGLVersion", PrimitiveUtils.writeInteger(object.version));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(OpenGLSettings object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("openGLSettings");
    }

    // Persisted fields:

    // field asyncMode (mapped with "openGLAsyncMode")
    xmlSerializer.writeStartElement("openGLAsyncMode");
    xmlSerializer.writeBoolean(object.asyncMode);
    xmlSerializer.writeEndElement();

    // field debug (mapped with "openGLDebug")
    xmlSerializer.writeStartElement("openGLDebug");
    xmlSerializer.writeBoolean(object.debug);
    xmlSerializer.writeEndElement();

    // field maxFPS (mapped with "openGLMaxFPS")
    xmlSerializer.writeStartElement("openGLMaxFPS");
    xmlSerializer.writeInt(object.maxFPS);
    xmlSerializer.writeEndElement();

    // field safeMode (mapped with "openGLSafeMode")
    xmlSerializer.writeStartElement("openGLSafeMode");
    xmlSerializer.writeBoolean(object.safeMode);
    xmlSerializer.writeEndElement();

    // field version (mapped with "openGLVersion")
    xmlSerializer.writeStartElement("openGLVersion");
    xmlSerializer.writeInt(object.version);
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public OpenGLSettings parseOnJackson(JsonParser jacksonParser) throws Exception {
    OpenGLSettings instance = new OpenGLSettings();
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
          case "openGLAsyncMode":
            // field asyncMode (mapped with "openGLAsyncMode")
            instance.asyncMode=jacksonParser.getBooleanValue();
          break;
          case "openGLDebug":
            // field debug (mapped with "openGLDebug")
            instance.debug=jacksonParser.getBooleanValue();
          break;
          case "openGLMaxFPS":
            // field maxFPS (mapped with "openGLMaxFPS")
            instance.maxFPS=jacksonParser.getIntValue();
          break;
          case "openGLSafeMode":
            // field safeMode (mapped with "openGLSafeMode")
            instance.safeMode=jacksonParser.getBooleanValue();
          break;
          case "openGLVersion":
            // field version (mapped with "openGLVersion")
            instance.version=jacksonParser.getIntValue();
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
  public OpenGLSettings parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    OpenGLSettings instance = new OpenGLSettings();
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
          case "openGLAsyncMode":
            // field asyncMode (mapped with "openGLAsyncMode")
            instance.asyncMode=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "openGLDebug":
            // field debug (mapped with "openGLDebug")
            instance.debug=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "openGLMaxFPS":
            // field maxFPS (mapped with "openGLMaxFPS")
            instance.maxFPS=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "openGLSafeMode":
            // field safeMode (mapped with "openGLSafeMode")
            instance.safeMode=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "openGLVersion":
            // field version (mapped with "openGLVersion")
            instance.version=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
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
  public OpenGLSettings parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    OpenGLSettings instance = new OpenGLSettings();
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
                case "openGLAsyncMode":
                  // property asyncMode (mapped on "openGLAsyncMode")
                  instance.asyncMode=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "openGLDebug":
                  // property debug (mapped on "openGLDebug")
                  instance.debug=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "openGLMaxFPS":
                  // property maxFPS (mapped on "openGLMaxFPS")
                  instance.maxFPS=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "openGLSafeMode":
                  // property safeMode (mapped on "openGLSafeMode")
                  instance.safeMode=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "openGLVersion":
                  // property version (mapped on "openGLVersion")
                  instance.version=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
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
