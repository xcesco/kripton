package bind.feature.typeadapter.kripton87;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean87A_7
 *
 * @see Bean87A_7
 */
@BindMap(Bean87A_7.class)
public class Bean87A_7BindMap extends AbstractMapper<Bean87A_7> {
  @Override
  public int serializeOnJackson(Bean87A_7 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field attributeString (mapped with "attributeString")
    if (object.attributeString!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("attributeString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.attributeString));
    }

    // field dataString (mapped with "dataString")
    if (object.dataString!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("dataString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.dataString));
    }

    // field elementString (mapped with "elementString")
    if (object.elementString!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("elementString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.elementString));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean87A_7 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field attributeString (mapped with "attributeString")
    if (object.attributeString!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("attributeString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.attributeString));
    }

    // field dataString (mapped with "dataString")
    if (object.dataString!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("dataString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.dataString));
    }

    // field elementString (mapped with "elementString")
    if (object.elementString!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("elementString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.elementString));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean87A_7 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean87A_7");
    }

    // Persisted fields:

    // field attributeString (mapped with "attributeString")
    // field trasformation java.lang.String bind.feature.typeadapter.kripton87.StringInverterTypeAdapter 
    if (object.attributeString!=null) {
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      xmlSerializer.writeAttribute("attributeString", StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.attributeString)));
    }

    // field elementString (mapped with "elementString")
    // field trasformation java.lang.String bind.feature.typeadapter.kripton87.StringInverterTypeAdapter 
    if (object.elementString!=null) {
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      xmlSerializer.writeStartElement("elementString");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.elementString)));
      xmlSerializer.writeEndElement();
    }

    // field dataString (mapped with "dataString")
    // field trasformation java.lang.String bind.feature.typeadapter.kripton87.StringInverterTypeAdapter 
    if (object.dataString!=null) {
      // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
      xmlSerializer.writeCData(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.dataString)));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean87A_7 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean87A_7 instance = new Bean87A_7();
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
          case "attributeString":
            // field attributeString (mapped with "attributeString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
              instance.attributeString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
            }
          break;
          case "elementString":
            // field elementString (mapped with "elementString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
              instance.elementString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
            }
          break;
          case "dataString":
            // field dataString (mapped with "dataString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
              instance.dataString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
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
  public Bean87A_7 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean87A_7 instance = new Bean87A_7();
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
          case "attributeString":
            // field attributeString (mapped with "attributeString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
              instance.attributeString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
            }
          break;
          case "elementString":
            // field elementString (mapped with "elementString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
              instance.elementString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
            }
          break;
          case "dataString":
            // field dataString (mapped with "dataString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
              instance.dataString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
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
  public Bean87A_7 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean87A_7 instance = new Bean87A_7();
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
          case "attributeString":
            // field attributeString (mapped by "attributeString")
            // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
            instance.attributeString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex)));
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
                case "elementString":
                  // property elementString (mapped on "elementString")
                  // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
                  instance.elementString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
                // property dataString
                // using type adapter bind.feature.typeadapter.kripton87.StringInverterTypeAdapter
                instance.dataString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getText()));
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
