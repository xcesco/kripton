package bind.feature.generichierarchy.kripton109.test3;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.DateUtils;
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
 * This class is binder map for Class3
 *
 * @see Class3
 */
@BindMap(Class3.class)
public class Class3BindMap extends AbstractMapper<Class3> {
  @Override
  public int serializeOnJackson(Class3 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueA (mapped with "valueA")
    if (object.valueA!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueA", object.valueA);
    }

    // field valueB (mapped with "valueB")
    if (object.valueB!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueB", DateUtils.write(object.valueB));
    }

    // field valueC (mapped with "valueC")
    if (object.valueC!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueC", object.valueC);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Class3 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueA (mapped with "valueA")
    if (object.valueA!=null)  {
      jacksonSerializer.writeStringField("valueA", PrimitiveUtils.writeInteger(object.valueA));
    }

    // field valueB (mapped with "valueB")
    if (object.valueB!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueB", DateUtils.write(object.valueB));
    }

    // field valueC (mapped with "valueC")
    if (object.valueC!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueC", object.valueC);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Class3 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("class3");
    }

    // Persisted fields:

    // field valueA (mapped with "valueA")
    if (object.valueA!=null)  {
      xmlSerializer.writeStartElement("valueA");
      xmlSerializer.writeInt(object.valueA);
      xmlSerializer.writeEndElement();
    }

    // field valueB (mapped with "valueB")
    if (object.valueB!=null)  {
      xmlSerializer.writeStartElement("valueB");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.valueB)));
      xmlSerializer.writeEndElement();
    }

    // field valueC (mapped with "valueC")
    if (object.valueC!=null) {
      xmlSerializer.writeStartElement("valueC");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueC));
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
  public Class3 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Class3 instance = new Class3();
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
          case "valueA":
            // field valueA (mapped with "valueA")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueA=jacksonParser.getIntValue();
            }
          break;
          case "valueB":
            // field valueB (mapped with "valueB")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueB=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "valueC":
            // field valueC (mapped with "valueC")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueC=jacksonParser.getText();
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
  public Class3 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Class3 instance = new Class3();
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
          case "valueA":
            // field valueA (mapped with "valueA")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueA=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
            }
          break;
          case "valueB":
            // field valueB (mapped with "valueB")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueB=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "valueC":
            // field valueC (mapped with "valueC")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueC=jacksonParser.getText();
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
  public Class3 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Class3 instance = new Class3();
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
                case "valueA":
                  // property valueA (mapped on "valueA")
                  instance.valueA=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                break;
                case "valueB":
                  // property valueB (mapped on "valueB")
                  instance.valueB=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "valueC":
                  // property valueC (mapped on "valueC")
                  instance.valueC=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
