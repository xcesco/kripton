package bind.kripton81morecoveragetests;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is binder map for Bean81R
 *
 * @see Bean81R
 */
@BindMap(Bean81R.class)
public class Bean81RBindMap extends AbstractMapper<Bean81R> {
  @Override
  public int serializeOnJackson(Bean81R object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteArray", object.valueByteArray);
    }

    // field valueInteger (mapped with "valueInteger")
    if (object.valueInteger!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("valueInteger", object.valueInteger);
    }

    // field valueMapStringInteger (mapped with "valueMapStringInteger")
    if (object.valueMapStringInteger!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapStringInteger.size()>0) {
        jacksonSerializer.writeFieldName("valueMapStringInteger");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeNumberField("value", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueMapStringInteger");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean81R object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteArray", object.valueByteArray);
    }

    // field valueInteger (mapped with "valueInteger")
    if (object.valueInteger!=null)  {
      jacksonSerializer.writeStringField("valueInteger", PrimitiveUtils.writeInteger(object.valueInteger));
    }

    // field valueMapStringInteger (mapped with "valueMapStringInteger")
    if (object.valueMapStringInteger!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapStringInteger.size()>0) {
        jacksonSerializer.writeFieldName("valueMapStringInteger");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeStringField("value", PrimitiveUtils.writeInteger(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapStringInteger", "null");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean81R object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean81R");
    }

    // Persisted fields:

    // field valueByteArray (mapped with "valueByteArray")
    if (object.valueByteArray!=null) {
      xmlSerializer.writeAttribute("valueByteArray", Base64Utils.encode(object.valueByteArray));
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field valueMapStringInteger (mapped with "valueMapStringInteger")
    if (object.valueMapStringInteger!=null)  {
      for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
        xmlSerializer.writeStartElement("valueMapStringInteger");
          if (item.getKey()!=null) {
            xmlSerializer.writeAttribute("key", StringEscapeUtils.escapeXml10(item.getKey()));
          }
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeAttribute("value", PrimitiveUtils.writeInteger(item.getValue()));
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field valueInteger (mapped with "valueInteger")
    if (object.valueInteger!=null)  {
      xmlSerializer.writeInt(object.valueInteger);
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean81R parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean81R instance = new Bean81R();
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
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByteArray=jacksonParser.getBinaryValue();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "valueMapStringInteger":
            // field valueMapStringInteger (mapped with "valueMapStringInteger")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Integer> collection=new HashMap<>();
              String key=null;
              Integer value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getIntValue();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapStringInteger=collection;
            }
          break;
          case "valueInteger":
            // field valueInteger (mapped with "valueInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueInteger=jacksonParser.getIntValue();
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
  public Bean81R parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean81R instance = new Bean81R();
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
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByteArray=Base64Utils.decode(jacksonParser.getValueAsString());
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "valueMapStringInteger":
            // field valueMapStringInteger (mapped with "valueMapStringInteger")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Integer> collection=new HashMap<>();
              String key=null;
              Integer value=null;
              JsonToken current;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                current=jacksonParser.currentToken();
                for (int i=0; i<2 ;i++) {
                  while (current != JsonToken.FIELD_NAME) {
                    current=jacksonParser.nextToken();
                  }
                  jacksonParser.nextValue();
                  switch(jacksonParser.getCurrentName()) {
                  case "key":
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      key=jacksonParser.getText();
                    }
                  break;
                  case "value":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        value=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
                      }
                    }
                  break;
                  }
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapStringInteger=collection;
            }
          break;
          case "valueInteger":
            // field valueInteger (mapped with "valueInteger")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueInteger=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
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
  public Bean81R parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean81R instance = new Bean81R();
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
          case "valueByteArray":
            // field valueByteArray (mapped by "valueByteArray")
            instance.valueByteArray=Base64Utils.decode(xmlParser.getAttributeValue(attributeIndex));
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
                case "valueMapStringInteger":
                  // property valueMapStringInteger (mapped on "valueMapStringInteger")
                   {
                    HashMap<String, Integer> collection=new HashMap<>();
                    String key;
                    Integer value;
                    int attributeIndex;
                    // add first element
                    attributeIndex=xmlParser.getAttributeIndex(null, "key");
                    key=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
                    attributeIndex=xmlParser.getAttributeIndex(null, "value");
                    value=PrimitiveUtils.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueMapStringInteger")) {
                      attributeIndex=xmlParser.getAttributeIndex(null, "key");
                      key=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
                      attributeIndex=xmlParser.getAttributeIndex(null, "value");
                      value=PrimitiveUtils.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
                      collection.put(key, value);
                    }
                    instance.valueMapStringInteger=collection;
                    read=false;
                  }
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
                // property valueInteger
                instance.valueInteger=PrimitiveUtils.readInteger(xmlParser.getText(), null);
              }
            break;
            default:
            break;
        }
      }
      return instance;
    }
  }
