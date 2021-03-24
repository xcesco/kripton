package bind.feature.immutable.case1;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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

    // field bufferString (mapped with "bufferString")
    if (object.getBufferString()!=null)  {
      fieldCount++;
      int n=object.getBufferString().length;
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("bufferString");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getBufferString()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
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

    // field map (mapped with "map")
    if (object.getMap()!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.getMap().size()>0) {
        jacksonSerializer.writeFieldName("map");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Long> item: object.getMap().entrySet()) {
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
        jacksonSerializer.writeNullField("map");
      }
    }

    // field mapSorted (mapped with "mapSorted")
    if (object.getMapSorted()!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.getMapSorted().size()>0) {
        jacksonSerializer.writeFieldName("mapSorted");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, String> item: object.getMapSorted().entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeStringField("value", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("mapSorted");
      }
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

    // field sortableSet (mapped with "sortableSet")
    if (object.getSortableSet()!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("sortableSet");
      jacksonSerializer.writeStartArray();
      for (String item: object.getSortableSet()) {
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

    // field bufferString (mapped with "bufferString")
    if (object.getBufferString()!=null)  {
      fieldCount++;
      int n=object.getBufferString().length;
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("bufferString");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getBufferString()[i];
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

    // field map (mapped with "map")
    if (object.getMap()!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.getMap().size()>0) {
        jacksonSerializer.writeFieldName("map");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Long> item: object.getMap().entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeStringField("value", PrimitiveUtils.writeLong(item.getValue()));
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("map", "null");
      }
    }

    // field mapSorted (mapped with "mapSorted")
    if (object.getMapSorted()!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.getMapSorted().size()>0) {
        jacksonSerializer.writeFieldName("mapSorted");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, String> item: object.getMapSorted().entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeStringField("value", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("mapSorted", "null");
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

    // field sortableSet (mapped with "sortableSet")
    if (object.getSortableSet()!=null)  {
      fieldCount++;
      int n=object.getSortableSet().size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("sortableSet");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (String item: object.getSortableSet()) {
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
  public void serializeOnXml(Bean object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
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

    // field bufferString (mapped with "bufferString")
    if (object.getBufferString()!=null)  {
      int n=object.getBufferString().length;
      String item;
      for (int i=0; i<n; i++) {
        item=object.getBufferString()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("bufferString");
        } else {
          xmlSerializer.writeStartElement("bufferString");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("bufferString");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
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

    // field map (mapped with "map")
    if (object.getMap()!=null)  {
      for (Map.Entry<String, Long> item: object.getMap().entrySet()) {
        xmlSerializer.writeStartElement("map");
          if (item.getKey()!=null) {
            xmlSerializer.writeStartElement("key");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
          }
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null)  {
              xmlSerializer.writeStartElement("value");
              xmlSerializer.writeLong(item.getValue());
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field mapSorted (mapped with "mapSorted")
    if (object.getMapSorted()!=null)  {
      for (Map.Entry<String, String> item: object.getMapSorted().entrySet()) {
        xmlSerializer.writeStartElement("mapSorted");
          if (item.getKey()!=null) {
            xmlSerializer.writeStartElement("key");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey()));
            xmlSerializer.writeEndElement();
          }
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

    // field sortableSet (mapped with "sortableSet")
    if (object.getSortableSet()!=null)  {
      int n=object.getSortableSet().size();
      for (String item: object.getSortableSet()) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("sortableSet");
        } else {
          xmlSerializer.writeStartElement("sortableSet");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("sortableSet");
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
  public Bean parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    SortedSet<String> __sortableSet=null;
    Map<String, Long> __map=null;
    SortedMap<String, String> __mapSorted=null;
    String __name=null;
    Date __birthDate=null;
    long __age=0;
    Long __numberOfCars=null;
    List<Long> __items=null;
    ArrayList<String> __itemsString=null;
    byte[] __buffer=null;
    String[] __bufferString=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Bean instance=new Bean((__sortableSet==null ? null : Collections.unmodifiableSortedSet(__sortableSet)),(__map==null ? null : Collections.unmodifiableMap(__map)),(__mapSorted==null ? null : Collections.unmodifiableSortedMap(__mapSorted)),__name,__birthDate,__age,__numberOfCars,(__items==null ? null : Collections.unmodifiableList(__items)),__itemsString,__buffer,__bufferString);
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
          case "bufferString":
            // field bufferString (mapped with "bufferString")
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
              __bufferString=CollectionUtils.asArray(collection, new String[collection.size()]);
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
          case "map":
            // field map (mapped with "map")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Long> collection=new HashMap<>();
              String key=null;
              Long value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getLongValue();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              __map=collection;
            }
          break;
          case "mapSorted":
            // field mapSorted (mapped with "mapSorted")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              TreeMap<String, String> collection=new TreeMap<>();
              String key=null;
              String value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getText();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              __mapSorted=collection;
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
          case "sortableSet":
            // field sortableSet (mapped with "sortableSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              TreeSet<String> collection=new TreeSet<>();
              String item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getText();
                }
                collection.add(item);
              }
              __sortableSet=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Bean instance=new Bean((__sortableSet==null ? null : Collections.unmodifiableSortedSet(__sortableSet)),(__map==null ? null : Collections.unmodifiableMap(__map)),(__mapSorted==null ? null : Collections.unmodifiableSortedMap(__mapSorted)),__name,__birthDate,__age,__numberOfCars,(__items==null ? null : Collections.unmodifiableList(__items)),__itemsString,__buffer,__bufferString);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    SortedSet<String> __sortableSet=null;
    Map<String, Long> __map=null;
    SortedMap<String, String> __mapSorted=null;
    String __name=null;
    Date __birthDate=null;
    long __age=0;
    Long __numberOfCars=null;
    List<Long> __items=null;
    ArrayList<String> __itemsString=null;
    byte[] __buffer=null;
    String[] __bufferString=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Bean instance=new Bean((__sortableSet==null ? null : Collections.unmodifiableSortedSet(__sortableSet)),(__map==null ? null : Collections.unmodifiableMap(__map)),(__mapSorted==null ? null : Collections.unmodifiableSortedMap(__mapSorted)),__name,__birthDate,__age,__numberOfCars,(__items==null ? null : Collections.unmodifiableList(__items)),__itemsString,__buffer,__bufferString);
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
          case "bufferString":
            // field bufferString (mapped with "bufferString")
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
              __bufferString=CollectionUtils.asArray(collection, new String[collection.size()]);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              __bufferString=CollectionUtils.asArray(collection, new String[collection.size()]);
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
          case "map":
            // field map (mapped with "map")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Long> collection=new HashMap<>();
              String key=null;
              Long value=null;
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
                        value=PrimitiveUtils.readLong(jacksonParser.getText(), null);
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
              __map=collection;
            }
          break;
          case "mapSorted":
            // field mapSorted (mapped with "mapSorted")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              TreeMap<String, String> collection=new TreeMap<>();
              String key=null;
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
              __mapSorted=collection;
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
          case "sortableSet":
            // field sortableSet (mapped with "sortableSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              TreeSet<String> collection=new TreeSet<>();
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
              __sortableSet=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              TreeSet<String> collection=new TreeSet<>();
              __sortableSet=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Bean instance=new Bean((__sortableSet==null ? null : Collections.unmodifiableSortedSet(__sortableSet)),(__map==null ? null : Collections.unmodifiableMap(__map)),(__mapSorted==null ? null : Collections.unmodifiableSortedMap(__mapSorted)),__name,__birthDate,__age,__numberOfCars,(__items==null ? null : Collections.unmodifiableList(__items)),__itemsString,__buffer,__bufferString);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Bean parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    SortedSet<String> __sortableSet=null;
    Map<String, Long> __map=null;
    SortedMap<String, String> __mapSorted=null;
    String __name=null;
    Date __birthDate=null;
    long __age=0;
    Long __numberOfCars=null;
    List<Long> __items=null;
    ArrayList<String> __itemsString=null;
    byte[] __buffer=null;
    String[] __bufferString=null;
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
                case "bufferString":
                  // property bufferString (mapped on "bufferString")
                   {
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), __bufferString);
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
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("bufferString")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    __bufferString=CollectionUtils.asArray(collection, new String[collection.size()]);
                    read=false;
                  }
                break;
                case "items":
                  // property items (mapped on "items")
                   {
                    ArrayList<Long> collection=CollectionUtils.merge(new ArrayList<>(), __items);
                    Long item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("items")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
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
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), __itemsString);
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
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("itemsString")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
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
                case "map":
                  // property map (mapped on "map")
                   {
                    HashMap<String, Long> collection=new HashMap<>();
                    String key;
                    Long value;
                    // add first element
                    xmlParser.nextTag();
                    key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("map")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    __map=collection;
                    read=false;
                  }
                break;
                case "mapSorted":
                  // property mapSorted (mapped on "mapSorted")
                   {
                    TreeMap<String, String> collection=new TreeMap<>();
                    String key;
                    String value;
                    // add first element
                    xmlParser.nextTag();
                    key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("mapSorted")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
                    __mapSorted=collection;
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
                case "sortableSet":
                  // property sortableSet (mapped on "sortableSet")
                   {
                    TreeSet<String> collection=CollectionUtils.merge(new TreeSet<>(), __sortableSet);
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
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("sortableSet")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    __sortableSet=collection;
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
      // immutable object: inizialize object
      Bean instance=new Bean((__sortableSet==null ? null : Collections.unmodifiableSortedSet(__sortableSet)),(__map==null ? null : Collections.unmodifiableMap(__map)),(__mapSorted==null ? null : Collections.unmodifiableSortedMap(__mapSorted)),__name,__birthDate,__age,__numberOfCars,(__items==null ? null : Collections.unmodifiableList(__items)),__itemsString,__buffer,__bufferString);
      return instance;
    }

    public void init() {
      // binding maps initialization 
    }
  }
