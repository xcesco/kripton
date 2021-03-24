package bind.feature.generichierarchy.kripton109.animations.math;

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
 * This class is binder map for Vector3
 *
 * @see Vector3
 */
@BindMap(Vector3.class)
public class Vector3BindMap extends AbstractMapper<Vector3> {
  @Override
  public int serializeOnJackson(Vector3 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field x (mapped with "x")
    fieldCount++;
    jacksonSerializer.writeNumberField("x", object.x);

    // field y (mapped with "y")
    fieldCount++;
    jacksonSerializer.writeNumberField("y", object.y);

    // field z (mapped with "z")
    fieldCount++;
    jacksonSerializer.writeNumberField("z", object.z);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Vector3 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field x (mapped with "x")
    jacksonSerializer.writeStringField("x", PrimitiveUtils.writeFloat(object.x));

    // field y (mapped with "y")
    jacksonSerializer.writeStringField("y", PrimitiveUtils.writeFloat(object.y));

    // field z (mapped with "z")
    jacksonSerializer.writeStringField("z", PrimitiveUtils.writeFloat(object.z));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Vector3 object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("vector3");
    }

    // Persisted fields:

    // field x (mapped with "x")
    xmlSerializer.writeAttribute("x", PrimitiveUtils.writeFloat(object.x));

    // field y (mapped with "y")
    xmlSerializer.writeAttribute("y", PrimitiveUtils.writeFloat(object.y));

    // field z (mapped with "z")
    xmlSerializer.writeAttribute("z", PrimitiveUtils.writeFloat(object.z));

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Vector3 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Vector3 instance = new Vector3();
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
          case "x":
            // field x (mapped with "x")
            instance.x=jacksonParser.getFloatValue();
          break;
          case "y":
            // field y (mapped with "y")
            instance.y=jacksonParser.getFloatValue();
          break;
          case "z":
            // field z (mapped with "z")
            instance.z=jacksonParser.getFloatValue();
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
  public Vector3 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Vector3 instance = new Vector3();
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
          case "x":
            // field x (mapped with "x")
            instance.x=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "y":
            // field y (mapped with "y")
            instance.y=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "z":
            // field z (mapped with "z")
            instance.z=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
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
  public Vector3 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Vector3 instance = new Vector3();
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
          case "x":
            // field x (mapped by "x")
            instance.x=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "y":
            // field y (mapped by "y")
            instance.y=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "z":
            // field z (mapped by "z")
            instance.z=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
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

  @Override
  public void init() {
    // binding maps initialization 
  }
}
