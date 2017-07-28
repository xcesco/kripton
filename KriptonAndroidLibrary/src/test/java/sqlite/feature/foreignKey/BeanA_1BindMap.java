package sqlite.feature.foreignKey;

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
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for BeanA_1
 *
 * @see BeanA_1
 */
@BindMap(BeanA_1.class)
public class BeanA_1BindMap extends AbstractMapper<BeanA_1> {
  @Override
  public int serializeOnJackson(BeanA_1 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field beanA2Id (mapped with "beanA2Id")
    fieldCount++;
    jacksonSerializer.writeNumberField("beanA2Id", object.beanA2Id);

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(BeanA_1 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field beanA2Id (mapped with "beanA2Id")
    jacksonSerializer.writeStringField("beanA2Id", PrimitiveUtils.writeLong(object.beanA2Id));

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(BeanA_1 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("beanA_1");
    }

    // Persisted fields:

    // field beanA2Id (mapped with "beanA2Id")
    xmlSerializer.writeStartElement("beanA2Id");
    xmlSerializer.writeLong(object.beanA2Id);
    xmlSerializer.writeEndElement();

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field valueString (mapped with "valueString")
    if (object.valueString!=null) {
      xmlSerializer.writeStartElement("valueString");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
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
  public BeanA_1 parseOnJackson(JsonParser jacksonParser) throws Exception {
    BeanA_1 instance = new BeanA_1();
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
          case "beanA2Id":
            // field beanA2Id (mapped with "beanA2Id")
            instance.beanA2Id=jacksonParser.getLongValue();
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
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
  public BeanA_1 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    BeanA_1 instance = new BeanA_1();
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
          case "beanA2Id":
            // field beanA2Id (mapped with "beanA2Id")
            instance.beanA2Id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
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
  public BeanA_1 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    BeanA_1 instance = new BeanA_1();
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
                case "beanA2Id":
                  // property beanA2Id (mapped on "beanA2Id")
                  instance.beanA2Id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "valueString":
                  // property valueString (mapped on "valueString")
                  instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
