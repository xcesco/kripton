package bind.kripton81MoreCoverageTests;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean81P
 *
 * @see Bean81P
 */
@BindMap(Bean81P.class)
public class Bean81PBindMap extends AbstractMapper<Bean81P> {
  @Override
  public int serializeOnJackson(Bean81P object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueCurrency (mapped with "valueCurrency")
    if (object.valueCurrency!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.valueCurrency));
    }

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean81P object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueCurrency (mapped with "valueCurrency")
    if (object.valueCurrency!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueCurrency", CurrencyUtils.write(object.valueCurrency));
    }

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueLocale", LocaleUtils.write(object.valueLocale));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean81P object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean81P");
    }

    // Persisted fields:

    // field valueCurrency (mapped with "valueCurrency")
    if (object.valueCurrency!=null)  {
      xmlSerializer.writeAttribute("valueCurrency", CurrencyUtils.write(object.valueCurrency));
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field valueLocale (mapped with "valueLocale")
    if (object.valueLocale!=null)  {
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtils.write(object.valueLocale)));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean81P parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean81P instance = new Bean81P();
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
          case "valueCurrency":
            // field valueCurrency (mapped with "valueCurrency")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueCurrency=CurrencyUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "valueLocale":
            // field valueLocale (mapped with "valueLocale")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueLocale=LocaleUtils.read(jacksonParser.getText());
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
  public Bean81P parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean81P instance = new Bean81P();
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
          case "valueCurrency":
            // field valueCurrency (mapped with "valueCurrency")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueCurrency=CurrencyUtils.read(jacksonParser.getText());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "valueLocale":
            // field valueLocale (mapped with "valueLocale")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueLocale=LocaleUtils.read(jacksonParser.getText());
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
  public Bean81P parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean81P instance = new Bean81P();
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
          case "valueCurrency":
            // field valueCurrency (mapped by "valueCurrency")
            instance.valueCurrency=CurrencyUtils.read(xmlParser.getAttributeValue(attributeIndex));
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
                // property valueLocale
                instance.valueLocale=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getText()));
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
