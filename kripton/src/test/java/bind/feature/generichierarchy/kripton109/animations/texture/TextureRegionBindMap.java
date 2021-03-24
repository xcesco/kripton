package bind.feature.generichierarchy.kripton109.animations.texture;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for TextureRegion
 *
 * @see TextureRegion
 */
@BindMap(TextureRegion.class)
public class TextureRegionBindMap extends AbstractMapper<TextureRegion> {
  @Override
  public int serializeOnJackson(TextureRegion object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field highX (mapped with "highX")
    fieldCount++;
    jacksonSerializer.writeNumberField("highX", object.highX);

    // field highY (mapped with "highY")
    fieldCount++;
    jacksonSerializer.writeNumberField("highY", object.highY);

    // field lowX (mapped with "lowX")
    fieldCount++;
    jacksonSerializer.writeNumberField("lowX", object.lowX);

    // field lowY (mapped with "lowY")
    fieldCount++;
    jacksonSerializer.writeNumberField("lowY", object.lowY);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(TextureRegion object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field highX (mapped with "highX")
    jacksonSerializer.writeStringField("highX", PrimitiveUtils.writeFloat(object.highX));

    // field highY (mapped with "highY")
    jacksonSerializer.writeStringField("highY", PrimitiveUtils.writeFloat(object.highY));

    // field lowX (mapped with "lowX")
    jacksonSerializer.writeStringField("lowX", PrimitiveUtils.writeFloat(object.lowX));

    // field lowY (mapped with "lowY")
    jacksonSerializer.writeStringField("lowY", PrimitiveUtils.writeFloat(object.lowY));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(TextureRegion object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("textureRegion");
    }

    // Persisted fields:

    // field highX (mapped with "highX")
    xmlSerializer.writeAttribute("highX", PrimitiveUtils.writeFloat(object.highX));

    // field highY (mapped with "highY")
    xmlSerializer.writeAttribute("highY", PrimitiveUtils.writeFloat(object.highY));

    // field lowX (mapped with "lowX")
    xmlSerializer.writeAttribute("lowX", PrimitiveUtils.writeFloat(object.lowX));

    // field lowY (mapped with "lowY")
    xmlSerializer.writeAttribute("lowY", PrimitiveUtils.writeFloat(object.lowY));

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public TextureRegion parseOnJackson(JsonParser jacksonParser) throws Exception {
    TextureRegion instance = new TextureRegion();
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
          case "highX":
            // field highX (mapped with "highX")
            instance.highX=jacksonParser.getFloatValue();
          break;
          case "highY":
            // field highY (mapped with "highY")
            instance.highY=jacksonParser.getFloatValue();
          break;
          case "lowX":
            // field lowX (mapped with "lowX")
            instance.lowX=jacksonParser.getFloatValue();
          break;
          case "lowY":
            // field lowY (mapped with "lowY")
            instance.lowY=jacksonParser.getFloatValue();
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
  public TextureRegion parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    TextureRegion instance = new TextureRegion();
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
          case "highX":
            // field highX (mapped with "highX")
            instance.highX=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "highY":
            // field highY (mapped with "highY")
            instance.highY=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "lowX":
            // field lowX (mapped with "lowX")
            instance.lowX=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "lowY":
            // field lowY (mapped with "lowY")
            instance.lowY=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
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
  public TextureRegion parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    TextureRegion instance = new TextureRegion();
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
          case "highX":
            // field highX (mapped by "highX")
            instance.highX=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "highY":
            // field highY (mapped by "highY")
            instance.highY=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "lowX":
            // field lowX (mapped by "lowX")
            instance.lowX=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "lowY":
            // field lowY (mapped by "lowY")
            instance.lowY=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
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
            // No property to manage here
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
  }
}
