package bind.feature.git45;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class is binder map for ContainerBean
 *
 * @see ContainerBean
 */
@BindMap(ContainerBean.class)
public class ContainerBeanBindMap extends AbstractMapper<ContainerBean> {
  /**
   * Bean01BindMap */
  private Bean01BindMap bean01BindMap = BinderUtils.mapperFor(Bean01.class);

  /**
   * Bean02BindMap */
  private Bean02BindMap bean02BindMap = BinderUtils.mapperFor(Bean02.class);

  /**
   * Bean03BindMap */
  private Bean03BindMap bean03BindMap = BinderUtils.mapperFor(Bean03.class);

  @Override
  public int serializeOnJackson(ContainerBean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field elements (mapped with "item1")
    if (object.elements!=null)  {
      fieldCount++;
      int n=object.elements.size();
      Bean01 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item1");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.elements.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean01BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field elements2 (mapped with "list2")
    if (object.getElements2()!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("list2");
      jacksonSerializer.writeStartArray();
      for (Bean02 item: object.getElements2()) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean02BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field elements3 (mapped with "item3")
    if (object.getElements3()!=null)  {
      fieldCount++;
      int n=object.getElements3().length;
      Bean03 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item3");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getElements3()[i];
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean03BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(ContainerBean object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field elements (mapped with "item1")
    if (object.elements!=null)  {
      fieldCount++;
      int n=object.elements.size();
      Bean01 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item1");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.elements.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean01BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("item1");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field elements2 (mapped with "list2")
    if (object.getElements2()!=null)  {
      fieldCount++;
      int n=object.getElements2().size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("list2");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (Bean02 item: object.getElements2()) {
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean02BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("list2");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field elements3 (mapped with "item3")
    if (object.getElements3()!=null)  {
      fieldCount++;
      int n=object.getElements3().length;
      Bean03 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item3");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getElements3()[i];
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean03BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("item3");
            }
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
  public void serializeOnXml(ContainerBean object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("containerBean");
    }

    // Persisted fields:

    // field elements (mapped with "item1")
    if (object.elements!=null)  {
      int n=object.elements.size();
      Bean01 item;
      for (int i=0; i<n; i++) {
        item=object.elements.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item1");
        } else {
          xmlSerializer.writeStartElement("item1");
          bean01BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("item1");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field elements3 (mapped with "item3")
    if (object.getElements3()!=null)  {
      int n=object.getElements3().length;
      Bean03 item;
      for (int i=0; i<n; i++) {
        item=object.getElements3()[i];
        if (item==null) {
          xmlSerializer.writeEmptyElement("item3");
        } else {
          xmlSerializer.writeStartElement("item3");
          bean03BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("item3");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field elements2 (mapped with "list2")
    if (object.getElements2()!=null)  {
      int n=object.getElements2().size();
      // write wrapper tag
      xmlSerializer.writeStartElement("list2");
      for (Bean02 item: object.getElements2()) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("item2");
        } else {
          xmlSerializer.writeStartElement("item2");
          bean02BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
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
  public ContainerBean parseOnJackson(JsonParser jacksonParser) throws Exception {
    ContainerBean instance = new ContainerBean();
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
          case "item1":
            // field elements (mapped with "item1")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean01> collection=new ArrayList<>();
              Bean01 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean01BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.elements=collection;
            }
          break;
          case "item3":
            // field elements3 (mapped with "item3")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean03> collection=new ArrayList<>();
              Bean03 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean03BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setElements3(CollectionUtils.asArray(collection, new Bean03[collection.size()]));
            }
          break;
          case "list2":
            // field elements2 (mapped with "list2")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<Bean02> collection=new HashSet<>();
              Bean02 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean02BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setElements2(collection);
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
  public ContainerBean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ContainerBean instance = new ContainerBean();
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
          case "item1":
            // field elements (mapped with "item1")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean01> collection=new ArrayList<>();
              Bean01 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean01BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.elements=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean01> collection=new ArrayList<>();
              instance.elements=collection;
            }
          break;
          case "item3":
            // field elements3 (mapped with "item3")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean03> collection=new ArrayList<>();
              Bean03 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean03BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.setElements3(CollectionUtils.asArray(collection, new Bean03[collection.size()]));
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean03> collection=new ArrayList<>();
              instance.setElements3(CollectionUtils.asArray(collection, new Bean03[collection.size()]));
            }
          break;
          case "list2":
            // field elements2 (mapped with "list2")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<Bean02> collection=new HashSet<>();
              Bean02 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean02BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.setElements2(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              HashSet<Bean02> collection=new HashSet<>();
              instance.setElements2(collection);
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
  public ContainerBean parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    ContainerBean instance = new ContainerBean();
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
                case "item1":
                  // property elements (mapped on "item1")
                   {
                    ArrayList<Bean01> collection=CollectionUtils.merge(new ArrayList<>(), instance.elements);
                    Bean01 item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean01BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item1")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean01BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.elements=collection;
                    read=false;
                  }
                break;
                case "item3":
                  // property elements3 (mapped on "item3")
                   {
                    ArrayList<Bean03> collection=CollectionUtils.merge(new ArrayList<>(), instance.getElements3());
                    Bean03 item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean03BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item3")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean03BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setElements3(CollectionUtils.asArray(collection, new Bean03[collection.size()]));
                    read=false;
                  }
                break;
                case "list2":
                  // property elements2 (mapped on "list2")
                   {
                    HashSet<Bean02> collection=CollectionUtils.merge(new HashSet<>(), instance.getElements2());
                    Bean02 item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item2")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean02BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setElements2(collection);
                  }
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
