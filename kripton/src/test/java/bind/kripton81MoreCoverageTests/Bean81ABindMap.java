package bind.kripton81MoreCoverageTests;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean81A
 *
 * @see Bean81A
 */
@BindMap(Bean81A.class)
public class Bean81ABindMap extends AbstractMapper<Bean81A> {
  @Override
  public int serializeOnJackson(Bean81A object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueBidDecimal (mapped with "valueBidDecimal")
    if (object.valueBidDecimal!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBidDecimal", BigDecimalUtils.write(object.valueBidDecimal));
    }

    // field valueBidInteger (mapped with "valueBidInteger")
    if (object.valueBidInteger!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBidInteger", BigIntegerUtils.write(object.valueBidInteger));
    }

    // field valueEnum (mapped with "valueEnum")
    if (object.valueEnum!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean81A object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueBidDecimal (mapped with "valueBidDecimal")
    if (object.valueBidDecimal!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBidDecimal", BigDecimalUtils.write(object.valueBidDecimal));
    }

    // field valueBidInteger (mapped with "valueBidInteger")
    if (object.valueBidInteger!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueBidInteger", BigIntegerUtils.write(object.valueBidInteger));
    }

    // field valueEnum (mapped with "valueEnum")
    if (object.valueEnum!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueEnum", object.valueEnum.toString());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean81A object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean81A");
    }

    // Persisted fields:

    // field valueBidDecimal (mapped with "valueBidDecimal")
    if (object.valueBidDecimal!=null)  {
      xmlSerializer.writeDecimalAttribute(null, null,"valueBidDecimal", object.valueBidDecimal);
    }

    // field valueBidInteger (mapped with "valueBidInteger")
    if (object.valueBidInteger!=null)  {
      xmlSerializer.writeIntegerAttribute(null, null,"valueBidInteger", object.valueBidInteger);
    }

    // field valueEnum (mapped with "valueEnum")
    if (object.valueEnum!=null)  {
      xmlSerializer.writeAttribute("valueEnum", StringEscapeUtils.escapeXml10(object.valueEnum.toString()));
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean81A parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean81A instance = new Bean81A();
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
          case "valueBidDecimal":
            // field valueBidDecimal (mapped with "valueBidDecimal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBidDecimal=BigDecimalUtils.read(jacksonParser.getText());
            }
          break;
          case "valueBidInteger":
            // field valueBidInteger (mapped with "valueBidInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBidInteger=BigIntegerUtils.read(jacksonParser.getText());
            }
          break;
          case "valueEnum":
            // field valueEnum (mapped with "valueEnum")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.valueEnum=StringUtils.hasText(tempEnum)?Bean81Enum.valueOf(tempEnum):null;
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
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
  public Bean81A parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean81A instance = new Bean81A();
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
          case "valueBidDecimal":
            // field valueBidDecimal (mapped with "valueBidDecimal")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBidDecimal=BigDecimalUtils.read(jacksonParser.getText());
            }
          break;
          case "valueBidInteger":
            // field valueBidInteger (mapped with "valueBidInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueBidInteger=BigIntegerUtils.read(jacksonParser.getText());
            }
          break;
          case "valueEnum":
            // field valueEnum (mapped with "valueEnum")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.valueEnum=StringUtils.hasText(tempEnum)?Bean81Enum.valueOf(tempEnum):null;
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public Bean81A parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean81A instance = new Bean81A();
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
          case "valueBidDecimal":
            // field valueBidDecimal (mapped by "valueBidDecimal")
            instance.valueBidDecimal=xmlParser.getAttributeAsDecimal(attributeIndex);
          break;
          case "valueBidInteger":
            // field valueBidInteger (mapped by "valueBidInteger")
            instance.valueBidInteger=xmlParser.getAttributeAsInteger(attributeIndex);
          break;
          case "valueEnum":
            // field valueEnum (mapped by "valueEnum")
            instance.valueEnum=Bean81Enum.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex)));
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
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }
  }
