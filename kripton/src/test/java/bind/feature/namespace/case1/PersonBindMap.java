package bind.feature.namespace.case1;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
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
 * This class is binder map for Person
 *
 * @see Person
 */
@BindMap(Person.class)
public class PersonBindMap extends AbstractMapper<Person> {
  /**
   * PersonBindMap */
  private PersonBindMap personBindMap = this;

  @Override
  public int serializeOnJackson(Person object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthday (mapped with "birthday")
    if (object.birthday!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthday", DateUtils.write(object.birthday));
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field parent (mapped with "parent")
    if (object.parent!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("parent");
      personBindMap.serializeOnJackson(object.parent, jacksonSerializer);
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    // field tags (mapped with "tags")
    if (object.tags!=null)  {
      fieldCount++;
      int n=object.tags.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("tags");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.tags.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Person object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field birthday (mapped with "birthday")
    if (object.birthday!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthday", DateUtils.write(object.birthday));
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field parent (mapped with "parent")
    if (object.parent!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("parent");
      if (personBindMap.serializeOnJacksonAsString(object.parent, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("parent");
      }
    }

    // field surname (mapped with "surname")
    if (object.surname!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("surname", object.surname);
    }

    // field tags (mapped with "tags")
    if (object.tags!=null)  {
      fieldCount++;
      int n=object.tags.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("tags");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.tags.get(i);
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

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Person object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("person");
      xmlSerializer.writeAttribute("", "xmlns:tool", "http://www.dummy.com");
      xmlSerializer.writeAttribute("", "xmlns", "http://www.dummy.com");
    }

    // Persisted fields:

    // field birthday (mapped with "birthday")
    if (object.birthday!=null)  {
      xmlSerializer.writeStartElement("birthday");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.birthday)));
      xmlSerializer.writeEndElement();
    }

    // field name (mapped with "tool:name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("tool:name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
      xmlSerializer.writeEndElement();
    }

    // field parent (mapped with "parent")
    if (object.parent!=null)  {
      xmlSerializer.writeStartElement("parent");
      personBindMap.serializeOnXml(object.parent, xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    // field surname (mapped with "surname")
    if (object.surname!=null) {
      xmlSerializer.writeStartElement("surname");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.surname));
      xmlSerializer.writeEndElement();
    }

    // field tags (mapped with "tags")
    if (object.tags!=null)  {
      int n=object.tags.size();
      String item;
      for (int i=0; i<n; i++) {
        item=object.tags.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("tags");
        } else {
          xmlSerializer.writeStartElement("tags");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("tags");
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
  public Person parseOnJackson(JsonParser jacksonParser) throws Exception {
    Person instance = new Person();
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
          case "birthday":
            // field birthday (mapped with "birthday")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthday=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "parent":
            // field parent (mapped with "parent")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.parent=personBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.surname=jacksonParser.getText();
            }
          break;
          case "tags":
            // field tags (mapped with "tags")
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
              instance.tags=collection;
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
  public Person parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Person instance = new Person();
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
          case "birthday":
            // field birthday (mapped with "birthday")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.birthday=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "parent":
            // field parent (mapped with "parent")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.parent=personBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "surname":
            // field surname (mapped with "surname")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.surname=jacksonParser.getText();
            }
          break;
          case "tags":
            // field tags (mapped with "tags")
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
              instance.tags=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              instance.tags=collection;
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
  public Person parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Person instance = new Person();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName();
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
            currentTag = xmlParser.getName();
            switch(currentTag) {
                case "birthday":
                  // property birthday (mapped on "birthday")
                  instance.birthday=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "tool:name":
                  // property name (mapped on "tool:name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "parent":
                  // property parent (mapped on "parent")
                  instance.parent=personBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "surname":
                  // property surname (mapped on "surname")
                  instance.surname=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "tags":
                  // property tags (mapped on "tags")
                   {
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), instance.tags);
                    String item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("tags")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.tags=collection;
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
      // if document is empty, the element is null
      if (currentEventType == EventType.START_DOCUMENT && eventType == EventType.END_DOCUMENT) {
          return null;
        } else {
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
      }
    }
