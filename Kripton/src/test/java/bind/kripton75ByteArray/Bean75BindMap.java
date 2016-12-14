package bind.kripton75ByteArray;

import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.JacksonContext;
import com.abubusoft.kripton.binder.context.XmlBinderContext;
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
import java.lang.Byte;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;

/**
 * This class is the shared preference binder defined for Bean75
 *
 * @see Bean75
 */
@BindMap
public class Bean75BindMap extends AbstractMapper<Bean75> {
  /**
   * create new object instance
   */
  @Override
  public Bean75 createInstance() {
    return new Bean75();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, Bean75 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        fieldCount++;
        int n=object.valueByteArray.length;
        Byte item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueByteArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueByteArray[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueByteTypeArray
      if (object.valueByteTypeArray!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteTypeArray", object.valueByteTypeArray);
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
  public int serializeOnJacksonAsString(JacksonContext context, Bean75 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field name
      if (object.name!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.name);
      }

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        fieldCount++;
        int n=object.valueByteArray.length;
        Byte item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueByteArray");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.valueByteArray[i];
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              jacksonSerializer.writeString(PrimitiveUtils.writeByte(item));
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field valueByteTypeArray
      if (object.valueByteTypeArray!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteTypeArray", object.valueByteTypeArray);
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
  public void serializeOnXml(XmlBinderContext context, Bean75 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean75");
      }

      // Persisted fields:

      // field name
      if (object.name!=null) {
        xmlSerializer.writeStartElement("name");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
        xmlSerializer.writeEndElement();
      }

      // field valueByteArray
      if (object.valueByteArray!=null)  {
        int n=object.valueByteArray.length;
        Byte item;
        for (int i=0; i<n; i++) {
          item=object.valueByteArray[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueByteArray");
          } else {
            xmlSerializer.writeStartElement("valueByteArray");
            xmlSerializer.writeInt(item);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("valueByteArray");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field valueByteTypeArray
      if (object.valueByteTypeArray!=null) {
        xmlSerializer.writeStartElement("valueByteTypeArray");
        xmlSerializer.writeBinary(object.valueByteTypeArray, 0, object.valueByteTypeArray.length);
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
  public Bean75 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean75 instance = createInstance();
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
            case "name":
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=jacksonParser.getByteValue();
                  }
                  collection.add(item);
                }
                instance.valueByteArray=CollectionUtils.asByteArray(collection);
              }
            break;
            case "valueByteTypeArray":
              // field valueByteTypeArray
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByteTypeArray=jacksonParser.getBinaryValue();
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
  public Bean75 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean75 instance = createInstance();
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
            case "name":
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "valueByteArray":
              // field valueByteArray
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<Byte> collection=new ArrayList<>();
                Byte item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=PrimitiveUtils.readByte(jacksonParser.getText(), null);
                  }
                  collection.add(item);
                }
                instance.valueByteArray=CollectionUtils.asByteArray(collection);
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<Byte> collection=new ArrayList<>();
                instance.valueByteArray=CollectionUtils.asByteArray(collection);
              }
            break;
            case "valueByteTypeArray":
              // field valueByteTypeArray
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueByteTypeArray=Base64Utils.decode(jacksonParser.getValueAsString());
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
  public Bean75 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean75 instance = createInstance();
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
                  case "name":
                    // property name
                    instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "valueByteArray":
                    // property valueByteArray
                     {
                      ArrayList<Byte> collection=new ArrayList<>();
                      Byte item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XMLEventConstants.END_ELEMENT && xmlParser.getName().toString().equals("valueByteArray")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                        }
                        collection.add(item);
                      }
                      instance.valueByteArray=CollectionUtils.asByteArray(collection);
                      read=false;
                    }
                  break;
                  case "valueByteTypeArray":
                    // property valueByteTypeArray
                    instance.valueByteTypeArray=xmlParser.getElementAsBinary();
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
