package bind.feature.generichierarchy.kripton109.animations.math;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Point3
 *
 * @see Point3
 */
@BindMap(Point3.class)
public class Point3BindMap extends AbstractMapper<Point3> {
  @Override
  public int serializeOnJackson(Point3 object, JsonGenerator jacksonSerializer) throws Exception {
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
  public int serializeOnJacksonAsString(Point3 object, JsonGenerator jacksonSerializer) throws
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
  public void serializeOnXml(Point3 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("point3");
    }

    // Persisted fields:

    // field x (mapped with "x")
    xmlSerializer.writeAttribute("x", PrimitiveUtils.writeFloat(object.x));

    // field y (mapped with "y")
    xmlSerializer.writeAttribute("y", PrimitiveUtils.writeFloat(object.y));

    // field z (mapped with "z")
    xmlSerializer.writeAttribute("z", PrimitiveUtils.writeFloat(object.z));

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Point3 parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: inizialize temporary variables for properties
    float __x=0.0f;
    float __y=0.0f;
    float __z=0.0f;

    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Point3 instance=new Point3(__x,__y,__z);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "x":
            // field x (mapped with "x")
            __x=jacksonParser.getFloatValue();
          break;
          case "y":
            // field y (mapped with "y")
            __y=jacksonParser.getFloatValue();
          break;
          case "z":
            // field z (mapped with "z")
            __z=jacksonParser.getFloatValue();
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Point3 instance=new Point3(__x,__y,__z);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Point3 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: inizialize temporary variables for properties
    float __x=0.0f;
    float __y=0.0f;
    float __z=0.0f;

    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Point3 instance=new Point3(__x,__y,__z);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "x":
            // field x (mapped with "x")
            __x=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "y":
            // field y (mapped with "y")
            __y=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "z":
            // field z (mapped with "z")
            __z=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Point3 instance=new Point3(__x,__y,__z);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Point3 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    // immutable object: inizialize temporary variables for properties
    float __x=0.0f;
    float __y=0.0f;
    float __z=0.0f;

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
          case "x":
            // field x (mapped by "x")
            __x=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "y":
            // field y (mapped by "y")
            __y=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
          break;
          case "z":
            // field z (mapped by "z")
            __z=PrimitiveUtils.readFloat(xmlParser.getAttributeValue(attributeIndex), 0f);
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
    // immutable object: inizialize object
    Point3 instance=new Point3(__x,__y,__z);
    return instance;
  }
}
