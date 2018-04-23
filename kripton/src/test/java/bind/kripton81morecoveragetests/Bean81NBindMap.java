package bind.kripton81morecoveragetests;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean81N
 *
 * @see Bean81N
 */
@BindMap(Bean81N.class)
public class Bean81NBindMap extends AbstractMapper<Bean81N> {
  @Override
  public int serializeOnJackson(Bean81N object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueString1 (mapped with "valueString1")
    if (object.valueString1!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString1", object.valueString1);
    }

    // field valueString2 (mapped with "valueString2")
    if (object.valueString2!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString2", object.valueString2);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean81N object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueString1 (mapped with "valueString1")
    if (object.valueString1!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString1", object.valueString1);
    }

    // field valueString2 (mapped with "valueString2")
    if (object.valueString2!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString2", object.valueString2);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean81N object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean81N");
    }

    // Persisted fields:

    // field valueString1 (mapped with "valueString1")
    if (object.valueString1!=null) {
      xmlSerializer.writeAttribute("valueString1", StringEscapeUtils.escapeXml10(object.valueString1));
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field valueString2 (mapped with "valueString2")
    if (object.valueString2!=null) {
      xmlSerializer.writeCData(StringEscapeUtils.escapeXml10(object.valueString2));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean81N parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean81N instance = new Bean81N();
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
          case "valueString1":
            // field valueString1 (mapped with "valueString1")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString1=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "valueString2":
            // field valueString2 (mapped with "valueString2")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString2=jacksonParser.getText();
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
  public Bean81N parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean81N instance = new Bean81N();
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
          case "valueString1":
            // field valueString1 (mapped with "valueString1")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString1=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "valueString2":
            // field valueString2 (mapped with "valueString2")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString2=jacksonParser.getText();
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
  public Bean81N parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean81N instance = new Bean81N();
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
          case "valueString1":
            // field valueString1 (mapped by "valueString1")
            instance.valueString1=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
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
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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
              if (elementName!=null && xmlParser.hasText()) {
                // property valueString2
                instance.valueString2=StringEscapeUtils.unescapeXml(xmlParser.getText());
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
