package bind.kripton74map;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.LocaleUtils;
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
import java.util.Locale;
import java.util.Map;

/**
 * This class is binder map for Bean74
 *
 * @see Bean74
 */
@BindMap(Bean74.class)
public class Bean74BindMap extends AbstractMapper<Bean74> {
  /**
   * Bean74BindMap */
  private Bean74BindMap bean74BindMap = this;

  @Override
  public int serializeOnJackson(Bean74 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    // field valueMapBeanLocale (mapped with "valueMapBeanLocale")
    if (object.valueMapBeanLocale!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapBeanLocale.size()>0) {
        jacksonSerializer.writeFieldName("valueMapBeanLocale");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Bean74, Locale> item: object.valueMapBeanLocale.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeFieldName("k");
          bean74BindMap.serializeOnJackson(item.getKey(), jacksonSerializer);
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            jacksonSerializer.writeStringField("v", LocaleUtils.write(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueMapBeanLocale");
      }
    }

    // field valueMapEnumBean (mapped with "valueMapEnumBean")
    if (object.valueMapEnumBean!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapEnumBean.size()>0) {
        jacksonSerializer.writeFieldName("valueMapEnumBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Enum74, Bean74> item: object.valueMapEnumBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            jacksonSerializer.writeFieldName("v");
            bean74BindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueMapEnumBean");
      }
    }

    // field valueMapIntByteArray (mapped with "valueMapIntByteArray")
    if (object.valueMapIntByteArray!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapIntByteArray.size()>0) {
        jacksonSerializer.writeFieldName("valueMapIntByteArray");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeNumberField("k", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            jacksonSerializer.writeBinaryField("v", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("valueMapIntByteArray");
      }
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
          jacksonSerializer.writeStringField("k", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("v");
          } else {
            jacksonSerializer.writeNumberField("v", item.getValue());
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
  public int serializeOnJacksonAsString(Bean74 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueString (mapped with "valueString")
    if (object.valueString!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("valueString", object.valueString);
    }

    // field valueMapBeanLocale (mapped with "valueMapBeanLocale")
    if (object.valueMapBeanLocale!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapBeanLocale.size()>0) {
        jacksonSerializer.writeFieldName("valueMapBeanLocale");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Bean74, Locale> item: object.valueMapBeanLocale.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeFieldName("k");
          if (bean74BindMap.serializeOnJacksonAsString(item.getKey(), jacksonSerializer)==0) {
            jacksonSerializer.writeNullField("k");
          }
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("v", "null");
          } else {
            jacksonSerializer.writeStringField("v", LocaleUtils.write(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapBeanLocale", "null");
      }
    }

    // field valueMapEnumBean (mapped with "valueMapEnumBean")
    if (object.valueMapEnumBean!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapEnumBean.size()>0) {
        jacksonSerializer.writeFieldName("valueMapEnumBean");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Enum74, Bean74> item: object.valueMapEnumBean.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("v", "null");
          } else {
            jacksonSerializer.writeFieldName("v");
            if (bean74BindMap.serializeOnJacksonAsString(item.getValue(), jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("v");
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapEnumBean", "null");
      }
    }

    // field valueMapIntByteArray (mapped with "valueMapIntByteArray")
    if (object.valueMapIntByteArray!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.valueMapIntByteArray.size()>0) {
        jacksonSerializer.writeFieldName("valueMapIntByteArray");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("k", PrimitiveUtils.writeInteger(item.getKey()));
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("v", "null");
          } else {
            jacksonSerializer.writeBinaryField("v", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("valueMapIntByteArray", "null");
      }
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
          jacksonSerializer.writeStringField("k", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("v", "null");
          } else {
            jacksonSerializer.writeStringField("v", PrimitiveUtils.writeInteger(item.getValue()));
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
  public void serializeOnXml(Bean74 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean74");
    }

    // Persisted fields:

    // field valueString (mapped with "valueString")
    if (object.valueString!=null) {
      xmlSerializer.writeStartElement("valueString");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
      xmlSerializer.writeEndElement();
    }

    // field valueMapBeanLocale (mapped with "valueMapBeanLocale")
    if (object.valueMapBeanLocale!=null)  {
      for (Map.Entry<Bean74, Locale> item: object.valueMapBeanLocale.entrySet()) {
        xmlSerializer.writeStartElement("valueMapBeanLocale");
          xmlSerializer.writeStartElement("k");
          bean74BindMap.serializeOnXml(item.getKey(), xmlSerializer, 2);
          xmlSerializer.writeEndElement();
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("v");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("v");
              xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtils.write(item.getValue())));
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field valueMapEnumBean (mapped with "valueMapEnumBean")
    if (object.valueMapEnumBean!=null)  {
      // write wrapper tag
      xmlSerializer.writeStartElement("valueMapEnumBean");
      for (Map.Entry<Enum74, Bean74> item: object.valueMapEnumBean.entrySet()) {
        xmlSerializer.writeStartElement("item");
          xmlSerializer.writeStartElement("k");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey().toString()));
          xmlSerializer.writeEndElement();
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("v");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("v");
              bean74BindMap.serializeOnXml(item.getValue(), xmlSerializer, 2);
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
      xmlSerializer.writeEndElement();
    }

    // field valueMapIntByteArray (mapped with "valueMapIntByteArray")
    if (object.valueMapIntByteArray!=null)  {
      for (Map.Entry<Integer, byte[]> item: object.valueMapIntByteArray.entrySet()) {
        xmlSerializer.writeStartElement("valueMapIntByteArray");
          xmlSerializer.writeStartElement("k");
          xmlSerializer.writeInt(item.getKey());
          xmlSerializer.writeEndElement();
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("v");
          } else {
            if (item.getValue()!=null) {
              xmlSerializer.writeStartElement("v");
              xmlSerializer.writeBinary(item.getValue());
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field valueMapStringInteger (mapped with "valueMapStringInteger")
    if (object.valueMapStringInteger!=null)  {
      // write wrapper tag
      xmlSerializer.writeStartElement("valueMapStringInteger");
      for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
        xmlSerializer.writeStartElement("rutto");
          if (item.getKey()!=null) {
            xmlSerializer.writeStartElement("k");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
          }
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("v");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("v");
              xmlSerializer.writeInt(item.getValue());
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
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
  public Bean74 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean74 instance = new Bean74();
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
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
            }
          break;
          case "valueMapBeanLocale":
            // field valueMapBeanLocale (mapped with "valueMapBeanLocale")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Bean74, Locale> collection=new HashMap<>();
              Bean74 key=null;
              Locale value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=bean74BindMap.parseOnJackson(jacksonParser);
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=LocaleUtils.read(jacksonParser.getText());
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapBeanLocale=collection;
            }
          break;
          case "valueMapEnumBean":
            // field valueMapEnumBean (mapped with "valueMapEnumBean")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Enum74, Bean74> collection=new HashMap<>();
              Enum74 key=null;
              Bean74 value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                 {
                  String tempEnum=jacksonParser.getText();
                  key=StringUtils.hasText(tempEnum)?Enum74.valueOf(tempEnum):null;
                }
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                  value=bean74BindMap.parseOnJackson(jacksonParser);
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapEnumBean=collection;
            }
          break;
          case "valueMapIntByteArray":
            // field valueMapIntByteArray (mapped with "valueMapIntByteArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Integer, byte[]> collection=new HashMap<>();
              Integer key=null;
              byte[] value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getIntValue();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getBinaryValue();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.valueMapIntByteArray=collection;
            }
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
  public Bean74 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean74 instance = new Bean74();
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
          case "valueString":
            // field valueString (mapped with "valueString")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueString=jacksonParser.getText();
            }
          break;
          case "valueMapBeanLocale":
            // field valueMapBeanLocale (mapped with "valueMapBeanLocale")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Bean74, Locale> collection=new HashMap<>();
              Bean74 key=null;
              Locale value=null;
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
                  case "k":
                    key=bean74BindMap.parseOnJacksonAsString(jacksonParser);
                  break;
                  case "v":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        value=LocaleUtils.read(jacksonParser.getText());
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
              instance.valueMapBeanLocale=collection;
            }
          break;
          case "valueMapEnumBean":
            // field valueMapEnumBean (mapped with "valueMapEnumBean")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Enum74, Bean74> collection=new HashMap<>();
              Enum74 key=null;
              Bean74 value=null;
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
                  case "k":
                     {
                      String tempEnum=jacksonParser.getText();
                      key=StringUtils.hasText(tempEnum)?Enum74.valueOf(tempEnum):null;
                    }
                  break;
                  case "v":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                        value=bean74BindMap.parseOnJacksonAsString(jacksonParser);
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
              instance.valueMapEnumBean=collection;
            }
          break;
          case "valueMapIntByteArray":
            // field valueMapIntByteArray (mapped with "valueMapIntByteArray")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Integer, byte[]> collection=new HashMap<>();
              Integer key=null;
              byte[] value=null;
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
                  case "k":
                    key=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
                  break;
                  case "v":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        value=Base64Utils.decode(jacksonParser.getValueAsString());
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
              instance.valueMapIntByteArray=collection;
            }
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
                  case "k":
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      key=jacksonParser.getText();
                    }
                  break;
                  case "v":
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
  public Bean74 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean74 instance = new Bean74();
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
                case "valueString":
                  // property valueString (mapped on "valueString")
                  instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "valueMapBeanLocale":
                  // property valueMapBeanLocale (mapped on "valueMapBeanLocale")
                   {
                    HashMap<Bean74, Locale> collection=new HashMap<>();
                    Bean74 key;
                    Locale value;
                    // add first element
                    xmlParser.nextTag();
                    key=bean74BindMap.parseOnXml(xmlParser, eventType);
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueMapBeanLocale")) {
                      xmlParser.nextTag();
                      key=bean74BindMap.parseOnXml(xmlParser, eventType);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=LocaleUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapBeanLocale=collection;
                    read=false;
                  }
                break;
                case "valueMapEnumBean":
                  // property valueMapEnumBean (mapped on "valueMapEnumBean")
                   {
                    HashMap<Enum74, Bean74> collection=new HashMap<>();
                    Enum74 key;
                    Bean74 value;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      xmlParser.nextTag();
                      key=Enum74.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=bean74BindMap.parseOnXml(xmlParser, eventType);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapEnumBean=collection;
                  }
                break;
                case "valueMapIntByteArray":
                  // property valueMapIntByteArray (mapped on "valueMapIntByteArray")
                   {
                    HashMap<Integer, byte[]> collection=new HashMap<>();
                    Integer key;
                    byte[] value;
                    // add first element
                    xmlParser.nextTag();
                    key=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=xmlParser.getElementAsBinary();
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueMapIntByteArray")) {
                      xmlParser.nextTag();
                      key=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=xmlParser.getElementAsBinary();
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapIntByteArray=collection;
                    read=false;
                  }
                break;
                case "valueMapStringInteger":
                  // property valueMapStringInteger (mapped on "valueMapStringInteger")
                   {
                    HashMap<String, Integer> collection=new HashMap<>();
                    String key;
                    Integer value;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("rutto")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.valueMapStringInteger=collection;
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
