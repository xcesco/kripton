package bind.kripton81MoreCoverageTests;

import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.AbstractJacksonContext;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;
import com.abubusoft.kripton.binder.core.AbstractMapper;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the shared preference binder defined for Bean81S
 *
 * @see Bean81S
 */
@BindMap
public class Bean81SBindMap extends AbstractMapper<Bean81S> {
  /**
   * create new object instance
   */
  @Override
  public Bean81S createInstance() {
    return new Bean81S();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean81S object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.id);

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteArray", object.valueByteArray);
      }

      // field valueInteger
      if (object.valueInteger!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("valueInteger", object.valueInteger);
      }

      // field valueMapStringInteger
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
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean81S object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteArray", object.valueByteArray);
      }

      // field valueInteger
      if (object.valueInteger!=null)  {
        jacksonSerializer.writeStringField("valueInteger", PrimitiveUtils.writeInteger(object.valueInteger));
      }

      // field valueMapStringInteger
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
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(KriptonXmlContext context, Bean81S object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean81S");
      }

      // Persisted fields:

      // field id
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.id);
      xmlSerializer.writeEndElement();

      // field valueByteArray
      if (object.valueByteArray!=null) {
        xmlSerializer.writeStartElement("valueByteArray");
        xmlSerializer.writeBinary(object.valueByteArray, 0, object.valueByteArray.length);
        xmlSerializer.writeEndElement();
      }

      // field valueMapStringInteger
      if (object.valueMapStringInteger!=null)  {
        // write wrapper tag
        xmlSerializer.writeStartElement("valueMapStringInteger");
        for (Map.Entry<String, Integer> item: object.valueMapStringInteger.entrySet()) {
          xmlSerializer.writeStartElement("map");
            if (item.getKey()!=null) {
              xmlSerializer.writeAttribute("key", item.getKey());
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
        xmlSerializer.writeEndElement();
      }

      // field valueInteger
      if (object.valueInteger!=null)  {
        xmlSerializer.writeCData(PrimitiveUtils.writeInteger(object.valueInteger));
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean81S parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean81S instance = createInstance();
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
              // field id
              instance.id=jacksonParser.getLongValue();
            break;
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByteArray=jacksonParser.getBinaryValue();
              }
            break;
            case "valueMapStringInteger":
              // field valueMapStringInteger
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
              // field valueInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInteger=jacksonParser.getIntValue();
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean81S parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean81S instance = createInstance();
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
              // field id
              instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
            break;
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByteArray=Base64Utils.decode(jacksonParser.getValueAsString());
              }
            break;
            case "valueMapStringInteger":
              // field valueMapStringInteger
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
              // field valueInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInteger=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean81S parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean81S instance = createInstance();
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
            case XMLEventConstants.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "id":
                    // property id
                    instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                  break;
                  case "valueByteArray":
                    // property valueByteArray
                    instance.valueByteArray=xmlParser.getElementAsBinary();
                  break;
                  case "valueMapStringInteger":
                    // property valueMapStringInteger
                     {
                      HashMap<String, Integer> collection=new HashMap<>();
                      String key;
                      Integer value;
                      int attributeIndex;
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("map")) {
                        attributeIndex=xmlParser.getAttributeIndex(null, "key");
                        key=xmlParser.getAttributeValue(attributeIndex);
                        attributeIndex=xmlParser.getAttributeIndex(null, "value");
                        value=PrimitiveUtils.readInteger(xmlParser.getAttributeValue(attributeIndex), null);
                        collection.put(key, value);
                      }
                      instance.valueMapStringInteger=collection;
                    }
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEventConstants.END_ELEMENT:
                if (elementName.equals(xmlParser.getName().getLocalPart())) {
                  currentTag = elementName;
                  elementName = null;
                }
              break;
              case XMLEventConstants.CDATA:
              case XMLEventConstants.CHARACTERS:
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
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
