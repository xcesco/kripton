package bind.feature.typeadapter.kripton87;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean87A_5
 *
 * @see Bean87A_5
 */
@BindMap(Bean87A_5.class)
public class Bean87A_5BindMap extends AbstractMapper<Bean87A_5> {
  @Override
  public int serializeOnJackson(Bean87A_5 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field attributeBoolean (mapped with "attributeBoolean")
    if (object.attributeBoolean!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("attributeBoolean", BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.attributeBoolean)));
    }

    // field dataBoolean (mapped with "dataBoolean")
    if (object.dataBoolean!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dataBoolean", BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.dataBoolean)));
    }

    // field elementBoolean (mapped with "elementBoolean")
    if (object.elementBoolean!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("elementBoolean", BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.elementBoolean)));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean87A_5 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field attributeBoolean (mapped with "attributeBoolean")
    if (object.attributeBoolean!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("attributeBoolean", BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.attributeBoolean)));
    }

    // field dataBoolean (mapped with "dataBoolean")
    if (object.dataBoolean!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dataBoolean", BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.dataBoolean)));
    }

    // field elementBoolean (mapped with "elementBoolean")
    if (object.elementBoolean!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("elementBoolean", BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.elementBoolean)));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean87A_5 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean87A_5");
    }

    // Persisted fields:

    // field attributeBoolean (mapped with "attributeBoolean")
    // field trasformation java.math.BigDecimal bind.feature.typeadapter.kripton87.BooleanBigDecimalTypeAdapter 
    if (object.attributeBoolean!=null)  {
      xmlSerializer.writeDecimalAttribute(null, null,"attributeBoolean", TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.attributeBoolean));
    }

    // field elementBoolean (mapped with "elementBoolean")
    // field trasformation java.math.BigDecimal bind.feature.typeadapter.kripton87.BooleanBigDecimalTypeAdapter 
    if (object.elementBoolean!=null)  {
      xmlSerializer.writeStartElement("elementBoolean");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.elementBoolean))));
      xmlSerializer.writeEndElement();
    }

    // field dataBoolean (mapped with "dataBoolean")
    // field trasformation java.math.BigDecimal bind.feature.typeadapter.kripton87.BooleanBigDecimalTypeAdapter 
    if (object.dataBoolean!=null)  {
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(TypeAdapterUtils.toData(BooleanBigDecimalTypeAdapter.class, object.dataBoolean))));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean87A_5 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean87A_5 instance = new Bean87A_5();
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
          case "attributeBoolean":
            // field attributeBoolean (mapped with "attributeBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(jacksonParser.getText()));
            }
          break;
          case "elementBoolean":
            // field elementBoolean (mapped with "elementBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.elementBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(jacksonParser.getText()));
            }
          break;
          case "dataBoolean":
            // field dataBoolean (mapped with "dataBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.dataBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(jacksonParser.getText()));
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
  public Bean87A_5 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean87A_5 instance = new Bean87A_5();
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
          case "attributeBoolean":
            // field attributeBoolean (mapped with "attributeBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(jacksonParser.getText()));
            }
          break;
          case "elementBoolean":
            // field elementBoolean (mapped with "elementBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.elementBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(jacksonParser.getText()));
            }
          break;
          case "dataBoolean":
            // field dataBoolean (mapped with "dataBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.dataBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(jacksonParser.getText()));
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
  public Bean87A_5 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean87A_5 instance = new Bean87A_5();
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
          case "attributeBoolean":
            // field attributeBoolean (mapped by "attributeBoolean")
            instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, xmlParser.getAttributeAsDecimal(attributeIndex));
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
                case "elementBoolean":
                  // property elementBoolean (mapped on "elementBoolean")
                  instance.elementBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
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
                // property dataBoolean
                instance.dataBoolean=TypeAdapterUtils.toJava(BooleanBigDecimalTypeAdapter.class, BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getText())));
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
