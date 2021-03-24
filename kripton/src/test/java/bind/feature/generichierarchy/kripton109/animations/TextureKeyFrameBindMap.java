package bind.feature.generichierarchy.kripton109.animations;

import bind.feature.generichierarchy.kripton109.animations.texture.TextureRegion;
import bind.feature.generichierarchy.kripton109.animations.texture.TextureRegionBindMap;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for TextureKeyFrame
 *
 * @see TextureKeyFrame
 */
@BindMap(TextureKeyFrame.class)
public class TextureKeyFrameBindMap extends AbstractMapper<TextureKeyFrame> {
  /**
   * binder for type TextureRegion
   */
  private TextureRegionBindMap textureRegionBindMap;

  @Override
  public int serializeOnJackson(TextureKeyFrame object, JsonGenerator jacksonSerializer) throws
      Exception {
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

    // field textureRegion (mapped with "textureRegion")
    if (object.textureRegion!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("textureRegion");
      textureRegionBindMap.serializeOnJackson(object.textureRegion, jacksonSerializer);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(TextureKeyFrame object, JsonGenerator jacksonSerializer)
      throws Exception {
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

    // field textureRegion (mapped with "textureRegion")
    if (object.textureRegion!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("textureRegion");
      if (textureRegionBindMap.serializeOnJacksonAsString(object.textureRegion, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("textureRegion");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(TextureKeyFrame object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("textureKeyFrame");
    }

    // Persisted fields:

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeAttribute("name", StringEscapeUtils.escapeXml10(object.name));
    }

    // field duration (mapped with "duration")
    xmlSerializer.writeAttribute("duration", PrimitiveUtils.writeLong(object.duration));

    // field textureRegion (mapped with "textureRegion")
    if (object.textureRegion!=null)  {
      xmlSerializer.writeStartElement("textureRegion");
      textureRegionBindMap.serializeOnXml(object.textureRegion, xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public TextureKeyFrame parseOnJackson(JsonParser jacksonParser) throws Exception {
    TextureKeyFrame instance = new TextureKeyFrame();
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
          case "textureRegion":
            // field textureRegion (mapped with "textureRegion")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.textureRegion=textureRegionBindMap.parseOnJackson(jacksonParser);
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
  public TextureKeyFrame parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    TextureKeyFrame instance = new TextureKeyFrame();
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
          case "textureRegion":
            // field textureRegion (mapped with "textureRegion")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.textureRegion=textureRegionBindMap.parseOnJacksonAsString(jacksonParser);
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
  public TextureKeyFrame parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    TextureKeyFrame instance = new TextureKeyFrame();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
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
          case START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "textureRegion":
                  // property textureRegion (mapped on "textureRegion")
                  instance.textureRegion=textureRegionBindMap.parseOnXml(xmlParser, eventType);
                break;
                default:
                  xmlParser.skipChildren();
                break;
              }
            break;
            case END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case CDSECT:
            case TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }

    public void init() {
      // binding maps initialization 
      textureRegionBindMap=BinderUtils.mapperFor(TextureRegion.class);
    }
  }
