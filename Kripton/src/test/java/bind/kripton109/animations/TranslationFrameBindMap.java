package bind.kripton109.animations;

import bind.kripton109.animations.math.Vector3;
import bind.kripton109.animations.math.Vector3BindMap;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
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
 * This class is binder map for TranslationFrame
 *
 * @see TranslationFrame
 */
@BindMap(TranslationFrame.class)
public class TranslationFrameBindMap extends AbstractMapper<TranslationFrame> {
  /**
   * Vector3BindMap */
  private Vector3BindMap vector3BindMap = BinderUtils.mapperFor(Vector3.class);

  @Override
  public int serializeOnJackson(TranslationFrame object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field duration (mapped with "duration")
    fieldCount++;
    jacksonSerializer.writeNumberField("duration", object.duration);

    // field translation (mapped with "translation")
    if (object.translation!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("translation");
      vector3BindMap.serializeOnJackson(object.translation, jacksonSerializer);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(TranslationFrame object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field duration (mapped with "duration")
    jacksonSerializer.writeStringField("duration", PrimitiveUtils.writeLong(object.duration));

    // field translation (mapped with "translation")
    if (object.translation!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("translation");
      if (vector3BindMap.serializeOnJacksonAsString(object.translation, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("translation");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(TranslationFrame object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("translationFrame");
    }

    // Persisted fields:

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeAttribute("name", StringEscapeUtils.escapeXml10(object.name));
    }

    // field duration (mapped with "duration")
    xmlSerializer.writeAttribute("duration", PrimitiveUtils.writeLong(object.duration));

    // field translation (mapped with "translation")
    if (object.translation!=null)  {
      xmlSerializer.writeStartElement("translation");
      vector3BindMap.serializeOnXml(object.translation, xmlSerializer, 2);
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
  public TranslationFrame parseOnJackson(JsonParser jacksonParser) throws Exception {
    TranslationFrame instance = new TranslationFrame();
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
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "duration":
            // field duration (mapped with "duration")
            instance.duration=jacksonParser.getLongValue();
          break;
          case "translation":
            // field translation (mapped with "translation")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.translation=vector3BindMap.parseOnJackson(jacksonParser);
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
  public TranslationFrame parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    TranslationFrame instance = new TranslationFrame();
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
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "duration":
            // field duration (mapped with "duration")
            instance.duration=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "translation":
            // field translation (mapped with "translation")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.translation=vector3BindMap.parseOnJacksonAsString(jacksonParser);
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
  public TranslationFrame parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    TranslationFrame instance = new TranslationFrame();
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
          case "name":
            // field name (mapped by "name")
            instance.name=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
          break;
          case "duration":
            // field duration (mapped by "duration")
            instance.duration=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), 0L);
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
                case "translation":
                  // property translation (mapped on "translation")
                  instance.translation=vector3BindMap.parseOnXml(xmlParser, eventType);
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
