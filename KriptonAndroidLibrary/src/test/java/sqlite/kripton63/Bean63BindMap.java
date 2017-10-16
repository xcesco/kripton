package sqlite.kripton63;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
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
 * This class is binder map for Bean63
 *
 * @see Bean63
 */
@BindMap(Bean63.class)
public class Bean63BindMap extends AbstractMapper<Bean63> {
  @Override
  public int serializeOnJackson(Bean63 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field value (mapped with "value")
    if (object.value!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("value", object.value);
    }

    // field valueMapEnumByte (mapped with "valueMapEnumByte")
    if (object.valueMapEnumByte!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapEnumByte.size()>0) {
        jacksonSerializer.writeFieldName("valueMapEnumByte");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<EnumType, Byte> item: object.valueMapEnumByte.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeNumberField("value", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueMapEnumByte");
      }
    }

    // field valueMapStringByte (mapped with "valueMapStringByte")
    if (object.valueMapStringByte!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapStringByte.size()>0) {
        jacksonSerializer.writeFieldName("valueMapStringByte");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Byte> item: object.valueMapStringByte.entrySet()) {
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
        jacksonSerializer.writeNullField("valueMapStringByte");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean63 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field value (mapped with "value")
    if (object.value!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("value", object.value);
    }

    // field valueMapEnumByte (mapped with "valueMapEnumByte")
    if (object.valueMapEnumByte!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapEnumByte.size()>0) {
        jacksonSerializer.writeFieldName("valueMapEnumByte");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<EnumType, Byte> item: object.valueMapEnumByte.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeStringField("value", PrimitiveUtils.writeByte(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapEnumByte", "null");
      }
    }

    // field valueMapStringByte (mapped with "valueMapStringByte")
    if (object.valueMapStringByte!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapStringByte.size()>0) {
        jacksonSerializer.writeFieldName("valueMapStringByte");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Byte> item: object.valueMapStringByte.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeStringField("value", PrimitiveUtils.writeByte(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapStringByte", "null");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean63 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean63");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field value (mapped with "value")
    if (object.value!=null) {
      xmlSerializer.writeStartElement("value");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.value));
      xmlSerializer.writeEndElement();
    }

    // field valueMapEnumByte (mapped with "valueMapEnumByte")
    if (object.valueMapEnumByte!=null)  {
      for (Map.Entry<EnumType, Byte> item: object.valueMapEnumByte.entrySet()) {
        xmlSerializer.writeStartElement("valueMapEnumByte");
          xmlSerializer.writeStartElement("key");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey().toString()));
          xmlSerializer.writeEndElement();
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("value");
              xmlSerializer.writeInt(item.getValue());
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field valueMapStringByte (mapped with "valueMapStringByte")
    if (object.valueMapStringByte!=null)  {
      for (Map.Entry<String, Byte> item: object.valueMapStringByte.entrySet()) {
        xmlSerializer.writeStartElement("valueMapStringByte");
          if (item.getKey()!=null) {
            xmlSerializer.writeStartElement("key");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
          }
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("value");
              xmlSerializer.writeInt(item.getValue());
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean63 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean63 instance = new Bean63();
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
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "value":
            // field value (mapped with "value")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.value=jacksonParser.getText();
            }
          break;
          case "valueMapEnumByte":
            // field valueMapEnumByte (mapped with "valueMapEnumByte")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<EnumType, Byte> collection=new HashMap<>();
              EnumType key=null;
              Byte value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                 {
                  String tempEnum=jacksonParser.getText();
                  key=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
                }
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getByteValue();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapEnumByte=collection;
            }
          break;
          case "valueMapStringByte":
            // field valueMapStringByte (mapped with "valueMapStringByte")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Byte> collection=new HashMap<>();
              String key=null;
              Byte value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getByteValue();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapStringByte=collection;
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
  public Bean63 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean63 instance = new Bean63();
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
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "value":
            // field value (mapped with "value")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.value=jacksonParser.getText();
            }
          break;
          case "valueMapEnumByte":
            // field valueMapEnumByte (mapped with "valueMapEnumByte")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<EnumType, Byte> collection=new HashMap<>();
              EnumType key=null;
              Byte value=null;
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
                     {
                      String tempEnum=jacksonParser.getText();
                      key=StringUtils.hasText(tempEnum)?EnumType.valueOf(tempEnum):null;
                    }
                  break;
                  case "value":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        value=PrimitiveUtils.readByte(jacksonParser.getText(), null);
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
              instance.valueMapEnumByte=collection;
            }
          break;
          case "valueMapStringByte":
            // field valueMapStringByte (mapped with "valueMapStringByte")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Byte> collection=new HashMap<>();
              String key=null;
              Byte value=null;
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
                        value=PrimitiveUtils.readByte(jacksonParser.getText(), null);
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
              instance.valueMapStringByte=collection;
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
  public Bean63 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean63 instance = new Bean63();
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
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "value":
                  // property value (mapped on "value")
                  instance.value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "valueMapEnumByte":
                  // property valueMapEnumByte (mapped on "valueMapEnumByte")
                   {
                    HashMap<EnumType, Byte> collection=new HashMap<>();
                    EnumType key;
                    Byte value;
                    // add first element
                    xmlParser.nextTag();
                    key=EnumType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueMapEnumByte")) {
                      xmlParser.nextTag();
                      key=EnumType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapEnumByte=collection;
                    read=false;
                  }
                break;
                case "valueMapStringByte":
                  // property valueMapStringByte (mapped on "valueMapStringByte")
                   {
                    HashMap<String, Byte> collection=new HashMap<>();
                    String key;
                    Byte value;
                    // add first element
                    xmlParser.nextTag();
                    key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueMapStringByte")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapStringByte=collection;
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
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }
  }
