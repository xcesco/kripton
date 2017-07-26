package sqlite.feature.jql.entities;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;

/**
 * This class is binder map for Person
 *
 * @see Person
 */
@BindMap(Person.class)
public class PersonBindMap extends AbstractMapper<Person> {
  /**
   * ChildBindMap */
  private ChildBindMap childBindMap = BinderUtils.mapperFor(Child.class);

  @Override
  public int serializeOnJackson(Person object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field image (mapped with "image")
    if (object.image!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("image", object.image);
    }

    // field listChild (mapped with "listChild")
    if (object.listChild!=null)  {
      fieldCount++;
      int n=object.listChild.size();
      Child item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("listChild");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.listChild.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          childBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
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

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field image (mapped with "image")
    if (object.image!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("image", object.image);
    }

    // field listChild (mapped with "listChild")
    if (object.listChild!=null)  {
      fieldCount++;
      int n=object.listChild.size();
      Child item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("listChild");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.listChild.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (childBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("listChild");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Person object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("person");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field image (mapped with "image")
    if (object.image!=null) {
      xmlSerializer.writeStartElement("image");
      xmlSerializer.writeBinary(object.image);
      xmlSerializer.writeEndElement();
    }

    // field listChild (mapped with "listChild")
    if (object.listChild!=null)  {
      int n=object.listChild.size();
      Child item;
      for (int i=0; i<n; i++) {
        item=object.listChild.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("listChild");
        } else {
          xmlSerializer.writeStartElement("listChild");
          childBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("listChild");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
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
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.image=jacksonParser.getBinaryValue();
            }
          break;
          case "listChild":
            // field listChild (mapped with "listChild")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Child> collection=new ArrayList<>();
              Child item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=childBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.listChild=collection;
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
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
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.image=Base64Utils.decode(jacksonParser.getValueAsString());
            }
          break;
          case "listChild":
            // field listChild (mapped with "listChild")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Child> collection=new ArrayList<>();
              Child item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=childBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.listChild=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Child> collection=new ArrayList<>();
              instance.listChild=collection;
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
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
  public Person parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Person instance = new Person();
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
                case "image":
                  // property image (mapped on "image")
                  instance.image=xmlParser.getElementAsBinary();
                break;
                case "listChild":
                  // property listChild (mapped on "listChild")
                   {
                    ArrayList<Child> collection=new ArrayList<>();
                    Child item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=childBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("listChild")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=childBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.listChild=collection;
                    read=false;
                  }
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
