package bind.kripton78;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.KriptonXmlContext;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the shared preference binder defined for BeanElement78
 *
 * @see BeanElement78
 */
@BindMap
public class BeanElement78BindMap extends AbstractMapper<BeanElement78> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement78 createInstance() {
    return new BeanElement78();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, BeanElement78 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueListByteArray
      if (object.valueListByteArray!=null)  {
        fieldCount++;
        int n=object.valueListByteArray.size();
        byte[] item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueListByteArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueListByteArray.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeBinary(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueMapIntByteArray
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, BeanElement78 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueListByteArray
      if (object.valueListByteArray!=null)  {
        fieldCount++;
        int n=object.valueListByteArray.size();
        byte[] item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueListByteArray");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.valueListByteArray.get(i);
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              jacksonSerializer.writeBinary(item);
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field valueMapIntByteArray
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
  public void serializeOnXml(KriptonXmlContext context, BeanElement78 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement78");
      }

      // Persisted fields:

      // field valueListByteArray
      if (object.valueListByteArray!=null)  {
        int n=object.valueListByteArray.size();
        byte[] item;
        for (int i=0; i<n; i++) {
          item=object.valueListByteArray.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueListByteArray");
          } else {
            xmlSerializer.writeStartElement("valueListByteArray");
            xmlSerializer.writeBinary(item, 0, item.length);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("valueListByteArray");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field valueMapIntByteArray
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
                xmlSerializer.writeBinary(item.getValue(), 0, item.getValue().length);
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
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
  public BeanElement78 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement78 instance = createInstance();
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
            case "valueListByteArray":
              // field valueListByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<byte[]> collection=new ArrayList<>();
                byte[] item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=jacksonParser.getBinaryValue();
                  }
                  collection.add(item);
                }
                instance.valueListByteArray=collection;
              }
            break;
            case "valueMapIntByteArray":
              // field valueMapIntByteArray
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
  public BeanElement78 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement78 instance = createInstance();
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
            case "valueListByteArray":
              // field valueListByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<byte[]> collection=new ArrayList<>();
                byte[] item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=Base64Utils.decode(jacksonParser.getValueAsString());
                  }
                  collection.add(item);
                }
                instance.valueListByteArray=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<byte[]> collection=new ArrayList<>();
                instance.valueListByteArray=collection;
              }
            break;
            case "valueMapIntByteArray":
              // field valueMapIntByteArray
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
  public BeanElement78 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      BeanElement78 instance = createInstance();
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
                  case "valueListByteArray":
                    // property valueListByteArray
                     {
                      ArrayList<byte[]> collection=new ArrayList<>();
                      byte[] item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=xmlParser.getElementAsBinary();
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueListByteArray")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=xmlParser.getElementAsBinary();
                        }
                        collection.add(item);
                      }
                      instance.valueListByteArray=collection;
                      read=false;
                    }
                  break;
                  case "valueMapIntByteArray":
                    // property valueMapIntByteArray
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
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueMapIntByteArray")) {
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
                // no property is binded to VALUE o CDATA break;
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
