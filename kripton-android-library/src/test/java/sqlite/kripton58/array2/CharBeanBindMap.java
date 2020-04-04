package sqlite.kripton58.array2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;

/**
 * This class is binder map for CharBean
 *
 * @see CharBean
 */
@BindMap(CharBean.class)
public class CharBeanBindMap extends AbstractMapper<CharBean> {
  @Override
  public int serializeOnJackson(CharBean object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field value (mapped with "value")
    if (object.getValue()!=null)  {
      fieldCount++;
      int n=object.getValue().length;
      char item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("value");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValue()[i];
        jacksonSerializer.writeNumber(item);
      }
      jacksonSerializer.writeEndArray();
    }

    // field value2 (mapped with "value2")
    if (object.getValue2()!=null)  {
      fieldCount++;
      int n=object.getValue2().length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("value2");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getValue2()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(CharBean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field value (mapped with "value")
    if (object.getValue()!=null)  {
      fieldCount++;
      int n=object.getValue().length;
      char item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("value");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValue()[i];
          jacksonSerializer.writeString(PrimitiveUtils.writeCharacter(item));
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field value2 (mapped with "value2")
    if (object.getValue2()!=null)  {
      fieldCount++;
      int n=object.getValue2().length;
      Character item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("value2");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValue2()[i];
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

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(CharBean object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("charBean");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field value (mapped with "value")
    if (object.getValue()!=null)  {
      int n=object.getValue().length;
      char item;
      for (int i=0; i<n; i++) {
        item=object.getValue()[i];
        xmlSerializer.writeStartElement("value");
        xmlSerializer.writeInt(item);
        xmlSerializer.writeEndElement();
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("value");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field value2 (mapped with "value2")
    if (object.getValue2()!=null)  {
      int n=object.getValue2().length;
      Character item;
      for (int i=0; i<n; i++) {
        item=object.getValue2()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("value2");
        } else {
          xmlSerializer.writeStartElement("value2");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("value2");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public CharBean parseOnJackson(JsonParser jacksonParser) throws Exception {
    CharBean instance = new CharBean();
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
            instance.setId(jacksonParser.getLongValue());
          break;
          case "value":
            // field value (mapped with "value")
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
              instance.setValue(CollectionUtils.asCharacterTypeArray(collection));
            }
          break;
          case "value2":
            // field value2 (mapped with "value2")
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
              instance.setValue2(CollectionUtils.asCharacterArray(collection));
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
  public CharBean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    CharBean instance = new CharBean();
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
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "value":
            // field value (mapped with "value")
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
              instance.setValue(CollectionUtils.asCharacterTypeArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.setValue(CollectionUtils.asCharacterTypeArray(collection));
            }
          break;
          case "value2":
            // field value2 (mapped with "value2")
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
              instance.setValue2(CollectionUtils.asCharacterArray(collection));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Character> collection=new ArrayList<>();
              instance.setValue2(CollectionUtils.asCharacterArray(collection));
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
  public CharBean parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    CharBean instance = new CharBean();
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
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "value":
                  // property value (mapped on "value")
                   {
                    ArrayList<Character> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValue());
                    Character item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("value")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), ' ');
                      }
                      collection.add(item);
                    }
                    instance.setValue(CollectionUtils.asCharacterTypeArray(collection));
                    read=false;
                  }
                break;
                case "value2":
                  // property value2 (mapped on "value2")
                   {
                    ArrayList<Character> collection=CollectionUtils.merge(new ArrayList<>(), instance.getValue2());
                    Character item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("value2")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=(char)PrimitiveUtils.readCharacter(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.setValue2(CollectionUtils.asCharacterArray(collection));
                    read=false;
                  }
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
  }
