package bind.feature.immutable.case1;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.DateUtils;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * This class is binder map for Bean
 *
 * @see Bean
 */
@BindMap(Bean.class)
public class BeanBindMap extends AbstractMapper<Bean> {
  @Override
  public int serializeOnJackson(Bean object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field age (mapped with "age")
    fieldCount++;
    jacksonSerializer.writeNumberField("age", object.getAge());

    // field birthDate (mapped with "birthDate")
    if (object.getBirthDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthDate", DateUtils.write(object.getBirthDate()));
    }

    // field buffer (mapped with "buffer")
    if (object.getBuffer()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("buffer", object.getBuffer());
    }

    // field items (mapped with "items")
    if (object.getItems()!=null)  {
      fieldCount++;
      int n=object.getItems().size();
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("items");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getItems().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field itemsString (mapped with "itemsString")
    if (object.getItemsString()!=null)  {
      fieldCount++;
      int n=object.getItemsString().size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("itemsString");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getItemsString().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field numberOfCars (mapped with "numberOfCars")
    if (object.getNumberOfCars()!=null)  {
      fieldCount++;
      jacksonSerializer.writeNumberField("numberOfCars", object.getNumberOfCars());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field age (mapped with "age")
    jacksonSerializer.writeStringField("age", PrimitiveUtils.writeLong(object.getAge()));

    // field birthDate (mapped with "birthDate")
    if (object.getBirthDate()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("birthDate", DateUtils.write(object.getBirthDate()));
    }

    // field buffer (mapped with "buffer")
    if (object.getBuffer()!=null)  {
      fieldCount++;
      jacksonSerializer.writeBinaryField("buffer", object.getBuffer());
    }

    // field items (mapped with "items")
    if (object.getItems()!=null)  {
      fieldCount++;
      int n=object.getItems().size();
      Long item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("items");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getItems().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeLong(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field itemsString (mapped with "itemsString")
    if (object.getItemsString()!=null)  {
      fieldCount++;
      int n=object.getItemsString().size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("itemsString");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getItemsString().get(i);
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

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field numberOfCars (mapped with "numberOfCars")
    if (object.getNumberOfCars()!=null)  {
      jacksonSerializer.writeStringField("numberOfCars", PrimitiveUtils.writeLong(object.getNumberOfCars()));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean object, XMLSerializer xmlSerializer, int currentEventType) throws
      Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean");
    }

    // Persisted fields:

    // field age (mapped with "age")
    xmlSerializer.writeStartElement("age");
    xmlSerializer.writeLong(object.getAge());
    xmlSerializer.writeEndElement();

    // field birthDate (mapped with "birthDate")
    if (object.getBirthDate()!=null)  {
      xmlSerializer.writeStartElement("birthDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.getBirthDate())));
      xmlSerializer.writeEndElement();
    }

    // field buffer (mapped with "buffer")
    if (object.getBuffer()!=null) {
      xmlSerializer.writeStartElement("buffer");
      xmlSerializer.writeBinary(object.getBuffer());
      xmlSerializer.writeEndElement();
    }

    // field items (mapped with "items")
    if (object.getItems()!=null)  {
      int n=object.getItems().size();
      Long item;
      for (int i=0; i<n; i++) {
        item=object.getItems().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("items");
        } else {
          xmlSerializer.writeStartElement("items");
          xmlSerializer.writeLong(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("items");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field itemsString (mapped with "itemsString")
    if (object.getItemsString()!=null)  {
      int n=object.getItemsString().size();
      String item;
      for (int i=0; i<n; i++) {
        item=object.getItemsString().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("itemsString");
        } else {
          xmlSerializer.writeStartElement("itemsString");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("itemsString");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field name (mapped with "name")
    if (object.getName()!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
      xmlSerializer.writeEndElement();
    }

    // field numberOfCars (mapped with "numberOfCars")
    if (object.getNumberOfCars()!=null)  {
      xmlSerializer.writeStartElement("numberOfCars");
      xmlSerializer.writeLong(object.getNumberOfCars());
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
  public Bean parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: inizialize temporary variables for properties
    String __name=null;
    Date __birthDate=null;
    long __age=0;
    Long __numberOfCars=null;
    List<Long> __items=null;
    ArrayList<String> __itemsString=null;
    byte[] __buffer=null;

    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Bean instance=new Bean(__name,__birthDate,__age,__numberOfCars,Collections.unmodifiableList(__items),__itemsString,__buffer);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "age":
            // field age (mapped with "age")
            __age=jacksonParser.getLongValue();
          break;
          case "birthDate":
            // field birthDate (mapped with "birthDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __birthDate=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "buffer":
            // field buffer (mapped with "buffer")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __buffer=jacksonParser.getBinaryValue();
            }
          break;
          case "items":
            // field items (mapped with "items")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Long> collection=new ArrayList<>();
              Long item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getLongValue();
                }
                collection.add(item);
              }
              __items=collection;
            }
          break;
          case "itemsString":
            // field itemsString (mapped with "itemsString")
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
              __itemsString=collection;
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __name=jacksonParser.getText();
            }
          break;
          case "numberOfCars":
            // field numberOfCars (mapped with "numberOfCars")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __numberOfCars=jacksonParser.getLongValue();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Bean instance=new Bean(__name,__birthDate,__age,__numberOfCars,Collections.unmodifiableList(__items),__itemsString,__buffer);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: inizialize temporary variables for properties
    String __name=null;
    Date __birthDate=null;
    long __age=0;
    Long __numberOfCars=null;
    List<Long> __items=null;
    ArrayList<String> __itemsString=null;
    byte[] __buffer=null;

    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Bean instance=new Bean(__name,__birthDate,__age,__numberOfCars,Collections.unmodifiableList(__items),__itemsString,__buffer);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "age":
            // field age (mapped with "age")
            __age=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "birthDate":
            // field birthDate (mapped with "birthDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __birthDate=DateUtils.read(jacksonParser.getText());
            }
          break;
          case "buffer":
            // field buffer (mapped with "buffer")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __buffer=Base64Utils.decode(jacksonParser.getValueAsString());
            }
          break;
          case "items":
            // field items (mapped with "items")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Long> collection=new ArrayList<>();
              Long item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readLong(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              __items=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Long> collection=new ArrayList<>();
              __items=collection;
            }
          break;
          case "itemsString":
            // field itemsString (mapped with "itemsString")
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
              __itemsString=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              __itemsString=collection;
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __name=jacksonParser.getText();
            }
          break;
          case "numberOfCars":
            // field numberOfCars (mapped with "numberOfCars")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __numberOfCars=PrimitiveUtils.readLong(jacksonParser.getText(), null);
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Bean instance=new Bean(__name,__birthDate,__age,__numberOfCars,Collections.unmodifiableList(__items),__itemsString,__buffer);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Bean parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    // immutable object: inizialize temporary variables for properties
    String __name=null;
    Date __birthDate=null;
    long __age=0;
    Long __numberOfCars=null;
    List<Long> __items=null;
    ArrayList<String> __itemsString=null;
    byte[] __buffer=null;

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
                case "age":
                  // property age (mapped on "age")
                  __age=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "birthDate":
                  // property birthDate (mapped on "birthDate")
                  __birthDate=DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "buffer":
                  // property buffer (mapped on "buffer")
                  __buffer=xmlParser.getElementAsBinary();
                break;
                case "items":
                  // property items (mapped on "items")
                   {
                    ArrayList<Long> collection=new ArrayList<>();
                    Long item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("items")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      collection.add(item);
                    }
                    __items=collection;
                    read=false;
                  }
                break;
                case "itemsString":
                  // property itemsString (mapped on "itemsString")
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
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("itemsString")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    __itemsString=collection;
                    read=false;
                  }
                break;
                case "name":
                  // property name (mapped on "name")
                  __name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "numberOfCars":
                  // property numberOfCars (mapped on "numberOfCars")
                  __numberOfCars=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
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
      // immutable object: inizialize object
      Bean instance=new Bean(__name,__birthDate,__age,__numberOfCars,Collections.unmodifiableList(__items),__itemsString,__buffer);
      return instance;
    }
  }
