package bind.kripton75bytearray;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;

/**
 * This class is binder map for Bean75
 *
 * @see Bean75
 */
@BindMap(Bean75.class)
public class Bean75BindMap extends AbstractMapper<Bean75> {
  @Override
  public int serializeOnJackson(Bean75 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field valueByteArray (mapped with "valueByteArray")
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

    // field valueByteTypeArray (mapped with "valueByteTypeArray")
    if (object.valueByteTypeArray!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteTypeArray", object.valueByteTypeArray);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean75 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field valueByteArray (mapped with "valueByteArray")
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

    // field valueByteTypeArray (mapped with "valueByteTypeArray")
    if (object.valueByteTypeArray!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("valueByteTypeArray", object.valueByteTypeArray);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean75 object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("bean75");
    }

    // Persisted fields:

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
      xmlSerializer.writeEndElement();
    }

    // field valueByteArray (mapped with "valueByteArray")
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

    // field valueByteTypeArray (mapped with "valueByteTypeArray")
    if (object.valueByteTypeArray!=null) {
      xmlSerializer.writeStartElement("valueByteTypeArray");
      xmlSerializer.writeBinary(object.valueByteTypeArray);
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean75 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean75 instance = new Bean75();
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
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
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
            // field valueByteTypeArray (mapped with "valueByteTypeArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByteTypeArray=jacksonParser.getBinaryValue();
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
  public Bean75 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean75 instance = new Bean75();
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
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "valueByteArray":
            // field valueByteArray (mapped with "valueByteArray")
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
            // field valueByteTypeArray (mapped with "valueByteTypeArray")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.valueByteTypeArray=Base64Utils.decode(jacksonParser.getValueAsString());
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
  public Bean75 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Bean75 instance = new Bean75();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
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
          case START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "valueByteArray":
                  // property valueByteArray (mapped on "valueByteArray")
                   {
                    ArrayList<Byte> collection=CollectionUtils.merge(new ArrayList<>(), instance.valueByteArray);
                    Byte item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(byte)PrimitiveUtils.readByte(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("valueByteArray")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
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
                  // property valueByteTypeArray (mapped on "valueByteTypeArray")
                  instance.valueByteTypeArray=xmlParser.getElementAsBinary();
                break;
                default:
                  xmlParser.skipChildren();
                break;
              }
            break;
            case END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case CDSECT:
            case TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }

    public void init() {
      // binding maps initialization 
    }
  }
