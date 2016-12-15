package sqlite.kripton84;

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
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Character;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the shared preference binder defined for Bean84A
 *
 * @see Bean84A
 */
@BindMap
public class Bean84ABindMap extends AbstractMapper<Bean84A> {
  /**
   * create new object instance
   */
  @Override
  public Bean84A createInstance() {
    return new Bean84A();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean84A object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field columnArrayByteType
      if (object.columnArrayByteType!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("columnArrayByteType", object.columnArrayByteType);
      }

      // field columnArrayChar
      if (object.columnArrayChar!=null)  {
        fieldCount++;
        int n=object.columnArrayChar.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("columnArrayChar");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.columnArrayChar[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field columnArrayCharType
      if (object.columnArrayCharType!=null)  {
        fieldCount++;
        int n=object.columnArrayCharType.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("columnArrayCharType");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.columnArrayCharType[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }

      // field columnBean
      if (object.columnBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("columnBean");
        context.mapperFor(Bean84A.class).serializeOnJackson(context, object.columnBean, wrapper);
      }

      // field columnListString
      if (object.columnListString!=null)  {
        fieldCount++;
        int n=object.columnListString.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("columnListString");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.columnListString.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field columnMapIntegerString
      if (object.columnMapIntegerString!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.columnMapIntegerString.size()>0) {
          jacksonSerializer.writeFieldName("columnMapIntegerString");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Integer, String> item: object.columnMapIntegerString.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeNumberField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeStringField("value", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("columnMapIntegerString");
        }
      }

      // field id
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.id);

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean84A object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field columnArrayByteType
      if (object.columnArrayByteType!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("columnArrayByteType", object.columnArrayByteType);
      }

      // field columnArrayChar
      if (object.columnArrayChar!=null)  {
        fieldCount++;
        int n=object.columnArrayChar.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("columnArrayChar");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.columnArrayChar[i];
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field columnArrayCharType
      if (object.columnArrayCharType!=null)  {
        fieldCount++;
        int n=object.columnArrayCharType.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("columnArrayCharType");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.columnArrayCharType[i];
            jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field columnBean
      if (object.columnBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("columnBean");
        if (context.mapperFor(Bean84A.class).serializeOnJacksonAsString(context, object.columnBean, wrapper)==0) {
          jacksonSerializer.writeNullField("columnBean");
        }
      }

      // field columnListString
      if (object.columnListString!=null)  {
        fieldCount++;
        int n=object.columnListString.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("columnListString");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.columnListString.get(i);
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              jacksonSerializer.writeString(item);
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field columnMapIntegerString
      if (object.columnMapIntegerString!=null)  {
        fieldCount++;
        // write wrapper tag
        if (object.columnMapIntegerString.size()>0) {
          jacksonSerializer.writeFieldName("columnMapIntegerString");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<Integer, String> item: object.columnMapIntegerString.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", PrimitiveUtils.writeInteger(item.getKey()));
            if (item.getValue()==null) {
              jacksonSerializer.writeStringField("value", "null");
            } else {
              jacksonSerializer.writeStringField("value", item.getValue());
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeStringField("columnMapIntegerString", "null");
        }
      }

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

      // field valueString
      if (object.valueString!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueString", object.valueString);
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
  public void serializeOnXml(KriptonXmlContext context, Bean84A object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean84A");
      }

      // Persisted fields:

      // field columnArrayByteType
      if (object.columnArrayByteType!=null) {
        xmlSerializer.writeStartElement("columnArrayByteType");
        xmlSerializer.writeBinary(object.columnArrayByteType, 0, object.columnArrayByteType.length);
        xmlSerializer.writeEndElement();
      }

      // field columnArrayChar
      if (object.columnArrayChar!=null)  {
        int n=object.columnArrayChar.length;
        Character item;
        for (int i=0; i<n; i++) {
          item=object.columnArrayChar[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("columnArrayChar");
          } else {
            xmlSerializer.writeStartElement("columnArrayChar");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("columnArrayChar");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field columnArrayCharType
      if (object.columnArrayCharType!=null)  {
        int n=object.columnArrayCharType.length;
        char item;
        for (int i=0; i<n; i++) {
          item=object.columnArrayCharType[i];
          xmlSerializer.writeStartElement("columnArrayCharType");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("columnArrayCharType");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field columnBean
      if (object.columnBean!=null)  {
        xmlSerializer.writeStartElement("columnBean");
        context.mapperFor(Bean84A.class).serializeOnXml(context, object.columnBean, wrapper, 1);
        xmlSerializer.writeEndElement();
      }

      // field columnListString
      if (object.columnListString!=null)  {
        int n=object.columnListString.size();
        String item;
        for (int i=0; i<n; i++) {
          item=object.columnListString.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("columnListString");
          } else {
            xmlSerializer.writeStartElement("columnListString");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("columnListString");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field columnMapIntegerString
      if (object.columnMapIntegerString!=null)  {
        for (Map.Entry<Integer, String> item: object.columnMapIntegerString.entrySet()) {
          xmlSerializer.writeStartElement("columnMapIntegerString");
            xmlSerializer.writeStartElement("key");
            xmlSerializer.writeInt(item.getKey());
            xmlSerializer.writeEndElement();
            if (item.getValue()==null) {
              xmlSerializer.writeEmptyElement("value");
            } else {
              if (item.getValue()!=null) {
                xmlSerializer.writeStartElement("value");
                xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getValue()));
                xmlSerializer.writeEndElement();
              }
            }
          xmlSerializer.writeEndElement();
        }
      }

      // field id
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.id);
      xmlSerializer.writeEndElement();

      // field valueString
      if (object.valueString!=null) {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
        xmlSerializer.writeEndElement();
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
  public Bean84A parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean84A instance = createInstance();
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
            case "columnArrayByteType":
              // field columnArrayByteType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.columnArrayByteType=jacksonParser.getBinaryValue();
              }
            break;
            case "columnArrayChar":
              // field columnArrayChar
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=Character.valueOf((char)jacksonParser.getIntValue());
                  }
                  collection.add(item);
                }
                instance.columnArrayChar=CollectionUtils.asCharacterArray(collection);
              }
            break;
            case "columnArrayCharType":
              // field columnArrayCharType
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=Character.valueOf((char)jacksonParser.getIntValue());
                  }
                  collection.add(item);
                }
                instance.columnArrayCharType=CollectionUtils.asCharacterTypeArray(collection);
              }
            break;
            case "columnBean":
              // field columnBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.columnBean=context.mapperFor(Bean84A.class).parseOnJackson(context, wrapper);
              }
            break;
            case "columnListString":
              // field columnListString
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=jacksonParser.getText();
                  }
                  collection.add(item);
                }
                instance.columnListString=collection;
              }
            break;
            case "columnMapIntegerString":
              // field columnMapIntegerString
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Integer, String> collection=new HashMap<>();
                Integer key=null;
                String value=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  jacksonParser.nextValue();
                  key=jacksonParser.getIntValue();
                  jacksonParser.nextValue();
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    value=jacksonParser.getText();
                  }
                  collection.put(key, value);
                  key=null;
                  value=null;
                  jacksonParser.nextToken();
                }
                instance.columnMapIntegerString=collection;
              }
            break;
            case "id":
              // field id
              instance.id=jacksonParser.getLongValue();
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
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
  public Bean84A parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean84A instance = createInstance();
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
            case "columnArrayByteType":
              // field columnArrayByteType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.columnArrayByteType=Base64Utils.decode(jacksonParser.getValueAsString());
              }
            break;
            case "columnArrayChar":
              // field columnArrayChar
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=PrimitiveUtils.readCharacter(jacksonParser.getText(), null);
                  }
                  collection.add(item);
                }
                instance.columnArrayChar=CollectionUtils.asCharacterArray(collection);
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<Character> collection=new ArrayList<>();
                instance.columnArrayChar=CollectionUtils.asCharacterArray(collection);
              }
            break;
            case "columnArrayCharType":
              // field columnArrayCharType
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Character> collection=new ArrayList<>();
                Character item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=PrimitiveUtils.readCharacter(jacksonParser.getText(), ' ');
                  }
                  collection.add(item);
                }
                instance.columnArrayCharType=CollectionUtils.asCharacterTypeArray(collection);
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<Character> collection=new ArrayList<>();
                instance.columnArrayCharType=CollectionUtils.asCharacterTypeArray(collection);
              }
            break;
            case "columnBean":
              // field columnBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                instance.columnBean=context.mapperFor(Bean84A.class).parseOnJacksonAsString(context, wrapper);
              }
            break;
            case "columnListString":
              // field columnListString
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<String> collection=new ArrayList<>();
                String item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                      item=jacksonParser.getText();
                    }
                  }
                  collection.add(item);
                }
                instance.columnListString=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<String> collection=new ArrayList<>();
                instance.columnListString=collection;
              }
            break;
            case "columnMapIntegerString":
              // field columnMapIntegerString
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                HashMap<Integer, String> collection=new HashMap<>();
                Integer key=null;
                String value=null;
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
                      key=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
                    break;
                    case "value":
                      tempValue=jacksonParser.getValueAsString();
                      if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                        value=null;
                      } else {
                        if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                          value=jacksonParser.getText();
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
                instance.columnMapIntegerString=collection;
              }
            break;
            case "id":
              // field id
              instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
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
  public Bean84A parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean84A instance = createInstance();
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
                  case "columnArrayByteType":
                    // property columnArrayByteType
                    instance.columnArrayByteType=xmlParser.getElementAsBinary();
                  break;
                  case "columnArrayChar":
                    // property columnArrayChar
                     {
                      ArrayList<Character> collection=new ArrayList<>();
                      Character item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("columnArrayChar")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.columnArrayChar=CollectionUtils.asCharacterArray(collection);
                      read=false;
                    }
                  break;
                  case "columnArrayCharType":
                    // property columnArrayCharType
                     {
                      ArrayList<Character> collection=new ArrayList<>();
                      Character item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("columnArrayCharType")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                        }
                        collection.add(item);
                      }
                      instance.columnArrayCharType=CollectionUtils.asCharacterTypeArray(collection);
                      read=false;
                    }
                  break;
                  case "columnBean":
                    // property columnBean
                    instance.columnBean=context.mapperFor(Bean84A.class).parseOnXml(context, wrapper, eventType);
                  break;
                  case "columnListString":
                    // property columnListString
                     {
                      ArrayList<String> collection=new ArrayList<>();
                      String item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("columnListString")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        }
                        collection.add(item);
                      }
                      instance.columnListString=collection;
                      read=false;
                    }
                  break;
                  case "columnMapIntegerString":
                    // property columnMapIntegerString
                     {
                      HashMap<Integer, String> collection=new HashMap<>();
                      Integer key;
                      String value;
                      // add first element
                      xmlParser.nextTag();
                      key=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("columnMapIntegerString")) {
                        xmlParser.nextTag();
                        key=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                        xmlParser.nextTag();
                        if (xmlParser.isEmptyElement()) {
                          value=null;
                          xmlParser.nextTag();
                        } else {
                          value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                        }
                        xmlParser.nextTag();
                        collection.put(key, value);
                      }
                      instance.columnMapIntegerString=collection;
                      read=false;
                    }
                  break;
                  case "id":
                    // property id
                    instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                  break;
                  case "valueString":
                    // property valueString
                    instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
