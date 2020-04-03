package bind.feature.git45.bug01;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Property
 *
 * @see Property
 */
@BindMap(Property.class)
public class PropertyBindMap extends AbstractMapper<Property> {
  @Override
  public int serializeOnJackson(Property object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field content (mapped with "content")
    if (object.content!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("content", object.content);
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field value (mapped with "value")
    if (object.value!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("value", object.value);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Property object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field content (mapped with "content")
    if (object.content!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("content", object.content);
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field value (mapped with "value")
    if (object.value!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("value", object.value);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Property object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("property");
    }

    // Persisted fields:

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeAttribute("name", StringEscapeUtils.escapeXml10(object.name));
    }

    // field value (mapped with "value")
    if (object.value!=null) {
      xmlSerializer.writeAttribute("value", StringEscapeUtils.escapeXml10(object.value));
    }

    // field content (mapped with "content")
    if (object.content!=null) {
      xmlSerializer.writeStartElement("content");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.content));
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
  public Property parseOnJackson(JsonParser jacksonParser) throws Exception {
    Property instance = new Property();
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
          case "value":
            // field value (mapped with "value")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.value=jacksonParser.getText();
            }
          break;
          case "content":
            // field content (mapped with "content")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.content=jacksonParser.getText();
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
  public Property parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Property instance = new Property();
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
          case "value":
            // field value (mapped with "value")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.value=jacksonParser.getText();
            }
          break;
          case "content":
            // field content (mapped with "content")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.content=jacksonParser.getText();
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
  public Property parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Property instance = new Property();
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
          case "value":
            // field value (mapped by "value")
            instance.value=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
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
                case "content":
                  // property content (mapped on "content")
                  instance.content=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
