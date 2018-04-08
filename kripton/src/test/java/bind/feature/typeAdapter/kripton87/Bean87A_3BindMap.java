package bind.feature.typeAdapter.kripton87;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean87A_3
 *
 * @see Bean87A_3
 */
@BindMap(Bean87A_3.class)
public class Bean87A_3BindMap extends AbstractMapper<Bean87A_3> {
  @Override
  public int serializeOnJackson(Bean87A_3 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field attributeBoolean (mapped with "attributeBoolean")
    if (object.attributeBoolean!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      jacksonSerializer.writeNumberField("attributeBoolean", TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.attributeBoolean));
    }

    // field dataBoolean (mapped with "dataBoolean")
    if (object.dataBoolean!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      jacksonSerializer.writeNumberField("dataBoolean", TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.dataBoolean));
    }

    // field elementBoolean (mapped with "elementBoolean")
    if (object.elementBoolean!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      jacksonSerializer.writeNumberField("elementBoolean", TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.elementBoolean));
    }

    // field elementEnum (mapped with "elementEnum")
    if (object.elementEnum!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter
      jacksonSerializer.writeNumberField("elementEnum", TypeAdapterUtils.toData(Enum87IntegerTypeAdapter.class, object.elementEnum));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean87A_3 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field attributeBoolean (mapped with "attributeBoolean")
    if (object.attributeBoolean!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      jacksonSerializer.writeStringField("attributeBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.attributeBoolean)));
    }

    // field dataBoolean (mapped with "dataBoolean")
    if (object.dataBoolean!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      jacksonSerializer.writeStringField("dataBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.dataBoolean)));
    }

    // field elementBoolean (mapped with "elementBoolean")
    if (object.elementBoolean!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      jacksonSerializer.writeStringField("elementBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.elementBoolean)));
    }

    // field elementEnum (mapped with "elementEnum")
    if (object.elementEnum!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter
      jacksonSerializer.writeStringField("elementEnum", PrimitiveUtils.writeInteger(TypeAdapterUtils.toData(Enum87IntegerTypeAdapter.class, object.elementEnum)));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean87A_3 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean87A_3");
    }

    // Persisted fields:

    // field attributeBoolean (mapped with "attributeBoolean")
    // field trasformation java.lang.Long bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter 
    if (object.attributeBoolean!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      xmlSerializer.writeAttribute("attributeBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.attributeBoolean)));
    }

    // field elementBoolean (mapped with "elementBoolean")
    // field trasformation java.lang.Long bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter 
    if (object.elementBoolean!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      xmlSerializer.writeStartElement("elementBoolean");
      xmlSerializer.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.elementBoolean));
      xmlSerializer.writeEndElement();
    }

    // field elementEnum (mapped with "elementEnum")
    // field trasformation java.lang.Integer bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter 
    if (object.elementEnum!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter
      xmlSerializer.writeStartElement("elementEnum");
      xmlSerializer.writeInt(TypeAdapterUtils.toData(Enum87IntegerTypeAdapter.class, object.elementEnum));
      xmlSerializer.writeEndElement();
    }

    // field dataBoolean (mapped with "dataBoolean")
    // field trasformation java.lang.Long bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter 
    if (object.dataBoolean!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
      xmlSerializer.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.dataBoolean));
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean87A_3 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean87A_3 instance = new Bean87A_3();
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
              // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
              instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, jacksonParser.getLongValue());
            }
          break;
          case "elementBoolean":
            // field elementBoolean (mapped with "elementBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
              instance.elementBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, jacksonParser.getLongValue());
            }
          break;
          case "elementEnum":
            // field elementEnum (mapped with "elementEnum")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter
              instance.elementEnum=TypeAdapterUtils.toJava(Enum87IntegerTypeAdapter.class, jacksonParser.getIntValue());
            }
          break;
          case "dataBoolean":
            // field dataBoolean (mapped with "dataBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
              instance.dataBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, jacksonParser.getLongValue());
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
  public Bean87A_3 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean87A_3 instance = new Bean87A_3();
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
              // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
              instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
            }
          break;
          case "elementBoolean":
            // field elementBoolean (mapped with "elementBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
              instance.elementBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
            }
          break;
          case "elementEnum":
            // field elementEnum (mapped with "elementEnum")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter
              instance.elementEnum=TypeAdapterUtils.toJava(Enum87IntegerTypeAdapter.class, PrimitiveUtils.readInteger(jacksonParser.getText(), null));
            }
          break;
          case "dataBoolean":
            // field dataBoolean (mapped with "dataBoolean")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
              instance.dataBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
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
  public Bean87A_3 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean87A_3 instance = new Bean87A_3();
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
            // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
            instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), null));
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
                  // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
                  instance.elementBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null));
                break;
                case "elementEnum":
                  // property elementEnum (mapped on "elementEnum")
                  // using type adapter bind.feature.typeAdapter.kripton87.Enum87IntegerTypeAdapter
                  instance.elementEnum=TypeAdapterUtils.toJava(Enum87IntegerTypeAdapter.class, PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null));
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
                // using type adapter bind.feature.typeAdapter.kripton87.BooleanByteArrayTypeAdapter
                instance.dataBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getText(), null));
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
