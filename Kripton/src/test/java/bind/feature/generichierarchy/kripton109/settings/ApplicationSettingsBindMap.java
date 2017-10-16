package bind.feature.generichierarchy.kripton109.settings;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for ApplicationSettings
 *
 * @see ApplicationSettings
 */
@BindMap(ApplicationSettings.class)
public class ApplicationSettingsBindMap extends AbstractMapper<ApplicationSettings> {
  @Override
  public int serializeOnJackson(ApplicationSettings object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field activityClazz (mapped with "applicationActivityClazz")
    if (object.activityClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationActivityClazz", object.activityClazz);
    }

    // field clazz (mapped with "applicationClazz")
    if (object.clazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationClazz", object.clazz);
    }

    // field configClazz (mapped with "applicationConfigClazz")
    if (object.configClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationConfigClazz", object.configClazz);
    }

    // field gestureListenerClazz (mapped with "applicationGestureListenerClazz")
    if (object.gestureListenerClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationGestureListenerClazz", object.gestureListenerClazz);
    }

    // field mode (mapped with "applicationMode")
    if (object.mode!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationMode", object.mode.toString());
    }

    // field resetConfig (mapped with "applicationResetConfig")
    if (object.resetConfig!=null)  {
      fieldCount++;
      jacksonSerializer.writeBooleanField("applicationResetConfig", object.resetConfig);
    }

    // field splashScreenTimeout (mapped with "applicationSplashScreenTimeout")
    fieldCount++;
    jacksonSerializer.writeNumberField("applicationSplashScreenTimeout", object.splashScreenTimeout);

    // field startupTaskClazz (mapped with "applicationStartupTaskClazz")
    if (object.startupTaskClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationStartupTaskClazz", object.startupTaskClazz);
    }

    // field upgradePolicyClazz (mapped with "applicationUpgradePolicyClazz")
    if (object.upgradePolicyClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationUpgradePolicyClazz", object.upgradePolicyClazz);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(ApplicationSettings object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field activityClazz (mapped with "applicationActivityClazz")
    if (object.activityClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationActivityClazz", object.activityClazz);
    }

    // field clazz (mapped with "applicationClazz")
    if (object.clazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationClazz", object.clazz);
    }

    // field configClazz (mapped with "applicationConfigClazz")
    if (object.configClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationConfigClazz", object.configClazz);
    }

    // field gestureListenerClazz (mapped with "applicationGestureListenerClazz")
    if (object.gestureListenerClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationGestureListenerClazz", object.gestureListenerClazz);
    }

    // field mode (mapped with "applicationMode")
    if (object.mode!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationMode", object.mode.toString());
    }

    // field resetConfig (mapped with "applicationResetConfig")
    if (object.resetConfig!=null)  {
      jacksonSerializer.writeStringField("applicationResetConfig", PrimitiveUtils.writeBoolean(object.resetConfig));
    }

    // field splashScreenTimeout (mapped with "applicationSplashScreenTimeout")
    jacksonSerializer.writeStringField("applicationSplashScreenTimeout", PrimitiveUtils.writeInteger(object.splashScreenTimeout));

    // field startupTaskClazz (mapped with "applicationStartupTaskClazz")
    if (object.startupTaskClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationStartupTaskClazz", object.startupTaskClazz);
    }

    // field upgradePolicyClazz (mapped with "applicationUpgradePolicyClazz")
    if (object.upgradePolicyClazz!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("applicationUpgradePolicyClazz", object.upgradePolicyClazz);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(ApplicationSettings object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("applicationSettings");
    }

    // Persisted fields:

    // field activityClazz (mapped with "applicationActivityClazz")
    if (object.activityClazz!=null) {
      xmlSerializer.writeStartElement("applicationActivityClazz");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.activityClazz));
      xmlSerializer.writeEndElement();
    }

    // field clazz (mapped with "applicationClazz")
    if (object.clazz!=null) {
      xmlSerializer.writeStartElement("applicationClazz");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.clazz));
      xmlSerializer.writeEndElement();
    }

    // field configClazz (mapped with "applicationConfigClazz")
    if (object.configClazz!=null) {
      xmlSerializer.writeStartElement("applicationConfigClazz");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.configClazz));
      xmlSerializer.writeEndElement();
    }

    // field gestureListenerClazz (mapped with "applicationGestureListenerClazz")
    if (object.gestureListenerClazz!=null) {
      xmlSerializer.writeStartElement("applicationGestureListenerClazz");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.gestureListenerClazz));
      xmlSerializer.writeEndElement();
    }

    // field mode (mapped with "applicationMode")
    if (object.mode!=null)  {
      xmlSerializer.writeStartElement("applicationMode");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.mode.toString()));
      xmlSerializer.writeEndElement();
    }

    // field resetConfig (mapped with "applicationResetConfig")
    if (object.resetConfig!=null)  {
      xmlSerializer.writeStartElement("applicationResetConfig");
      xmlSerializer.writeBoolean(object.resetConfig);
      xmlSerializer.writeEndElement();
    }

    // field splashScreenTimeout (mapped with "applicationSplashScreenTimeout")
    xmlSerializer.writeStartElement("applicationSplashScreenTimeout");
    xmlSerializer.writeInt(object.splashScreenTimeout);
    xmlSerializer.writeEndElement();

    // field startupTaskClazz (mapped with "applicationStartupTaskClazz")
    if (object.startupTaskClazz!=null) {
      xmlSerializer.writeStartElement("applicationStartupTaskClazz");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.startupTaskClazz));
      xmlSerializer.writeEndElement();
    }

    // field upgradePolicyClazz (mapped with "applicationUpgradePolicyClazz")
    if (object.upgradePolicyClazz!=null) {
      xmlSerializer.writeStartElement("applicationUpgradePolicyClazz");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.upgradePolicyClazz));
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
  public ApplicationSettings parseOnJackson(JsonParser jacksonParser) throws Exception {
    ApplicationSettings instance = new ApplicationSettings();
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
          case "applicationActivityClazz":
            // field activityClazz (mapped with "applicationActivityClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.activityClazz=jacksonParser.getText();
            }
          break;
          case "applicationClazz":
            // field clazz (mapped with "applicationClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.clazz=jacksonParser.getText();
            }
          break;
          case "applicationConfigClazz":
            // field configClazz (mapped with "applicationConfigClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.configClazz=jacksonParser.getText();
            }
          break;
          case "applicationGestureListenerClazz":
            // field gestureListenerClazz (mapped with "applicationGestureListenerClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.gestureListenerClazz=jacksonParser.getText();
            }
          break;
          case "applicationMode":
            // field mode (mapped with "applicationMode")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.mode=StringUtils.hasText(tempEnum)?ModeType.valueOf(tempEnum):null;
            }
          break;
          case "applicationResetConfig":
            // field resetConfig (mapped with "applicationResetConfig")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.resetConfig=jacksonParser.getBooleanValue();
            }
          break;
          case "applicationSplashScreenTimeout":
            // field splashScreenTimeout (mapped with "applicationSplashScreenTimeout")
            instance.splashScreenTimeout=jacksonParser.getIntValue();
          break;
          case "applicationStartupTaskClazz":
            // field startupTaskClazz (mapped with "applicationStartupTaskClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.startupTaskClazz=jacksonParser.getText();
            }
          break;
          case "applicationUpgradePolicyClazz":
            // field upgradePolicyClazz (mapped with "applicationUpgradePolicyClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.upgradePolicyClazz=jacksonParser.getText();
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
  public ApplicationSettings parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ApplicationSettings instance = new ApplicationSettings();
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
          case "applicationActivityClazz":
            // field activityClazz (mapped with "applicationActivityClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.activityClazz=jacksonParser.getText();
            }
          break;
          case "applicationClazz":
            // field clazz (mapped with "applicationClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.clazz=jacksonParser.getText();
            }
          break;
          case "applicationConfigClazz":
            // field configClazz (mapped with "applicationConfigClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.configClazz=jacksonParser.getText();
            }
          break;
          case "applicationGestureListenerClazz":
            // field gestureListenerClazz (mapped with "applicationGestureListenerClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.gestureListenerClazz=jacksonParser.getText();
            }
          break;
          case "applicationMode":
            // field mode (mapped with "applicationMode")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.mode=StringUtils.hasText(tempEnum)?ModeType.valueOf(tempEnum):null;
            }
          break;
          case "applicationResetConfig":
            // field resetConfig (mapped with "applicationResetConfig")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.resetConfig=PrimitiveUtils.readBoolean(jacksonParser.getText(), null);
            }
          break;
          case "applicationSplashScreenTimeout":
            // field splashScreenTimeout (mapped with "applicationSplashScreenTimeout")
            instance.splashScreenTimeout=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "applicationStartupTaskClazz":
            // field startupTaskClazz (mapped with "applicationStartupTaskClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.startupTaskClazz=jacksonParser.getText();
            }
          break;
          case "applicationUpgradePolicyClazz":
            // field upgradePolicyClazz (mapped with "applicationUpgradePolicyClazz")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.upgradePolicyClazz=jacksonParser.getText();
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
  public ApplicationSettings parseOnXml(XMLParser xmlParser, int currentEventType) throws
      Exception {
    ApplicationSettings instance = new ApplicationSettings();
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
                case "applicationActivityClazz":
                  // property activityClazz (mapped on "applicationActivityClazz")
                  instance.activityClazz=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "applicationClazz":
                  // property clazz (mapped on "applicationClazz")
                  instance.clazz=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "applicationConfigClazz":
                  // property configClazz (mapped on "applicationConfigClazz")
                  instance.configClazz=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "applicationGestureListenerClazz":
                  // property gestureListenerClazz (mapped on "applicationGestureListenerClazz")
                  instance.gestureListenerClazz=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "applicationMode":
                  // property mode (mapped on "applicationMode")
                  instance.mode=ModeType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "applicationResetConfig":
                  // property resetConfig (mapped on "applicationResetConfig")
                  instance.resetConfig=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), null);
                break;
                case "applicationSplashScreenTimeout":
                  // property splashScreenTimeout (mapped on "applicationSplashScreenTimeout")
                  instance.splashScreenTimeout=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "applicationStartupTaskClazz":
                  // property startupTaskClazz (mapped on "applicationStartupTaskClazz")
                  instance.startupTaskClazz=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "applicationUpgradePolicyClazz":
                  // property upgradePolicyClazz (mapped on "applicationUpgradePolicyClazz")
                  instance.upgradePolicyClazz=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
