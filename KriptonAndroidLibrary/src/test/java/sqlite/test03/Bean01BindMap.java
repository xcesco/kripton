package sqlite.test03;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
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
 * This class is binder map for Bean01
 *
 * @see Bean01
 */
@BindMap(Bean01.class)
public class Bean01BindMap extends AbstractMapper<Bean01> {
  /**
   * Bean02BindMap */
  private Bean02BindMap bean02BindMap = BinderUtils.mapperFor(Bean02.class);

  @Override
  public int serializeOnJackson(Bean01 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field beanList (mapped with "beanList")
    if (object.getBeanList()!=null)  {
      fieldCount++;
      int n=object.getBeanList().size();
      Bean02 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("beanList");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getBeanList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean02BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field lista (mapped with "lista")
    if (object.getLista()!=null)  {
      fieldCount++;
      int n=object.getLista().size();
      Bean02 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("lista");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getLista().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean02BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field messageDate (mapped with "messageDate")
    fieldCount++;
    jacksonSerializer.writeNumberField("messageDate", object.getMessageDate());

    // field messageText (mapped with "messageText")
    if (object.getMessageText()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("messageText", object.getMessageText());
    }

    // field value (mapped with "value")
    fieldCount++;
    jacksonSerializer.writeNumberField("value", object.getValue());

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Bean01 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field beanList (mapped with "beanList")
    if (object.getBeanList()!=null)  {
      fieldCount++;
      int n=object.getBeanList().size();
      Bean02 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("beanList");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getBeanList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean02BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("beanList");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field lista (mapped with "lista")
    if (object.getLista()!=null)  {
      fieldCount++;
      int n=object.getLista().size();
      Bean02 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("lista");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getLista().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean02BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("lista");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field messageDate (mapped with "messageDate")
    jacksonSerializer.writeStringField("messageDate", PrimitiveUtils.writeLong(object.getMessageDate()));

    // field messageText (mapped with "messageText")
    if (object.getMessageText()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("messageText", object.getMessageText());
    }

    // field value (mapped with "value")
    jacksonSerializer.writeStringField("value", PrimitiveUtils.writeLong(object.getValue()));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Bean01 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean01");
    }

    // Persisted fields:

    // field beanList (mapped with "beanList")
    if (object.getBeanList()!=null)  {
      int n=object.getBeanList().size();
      Bean02 item;
      for (int i=0; i<n; i++) {
        item=object.getBeanList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("beanList");
        } else {
          xmlSerializer.writeStartElement("beanList");
          bean02BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("beanList");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field lista (mapped with "lista")
    if (object.getLista()!=null)  {
      int n=object.getLista().size();
      Bean02 item;
      for (int i=0; i<n; i++) {
        item=object.getLista().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("lista");
        } else {
          xmlSerializer.writeStartElement("lista");
          bean02BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("lista");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field messageDate (mapped with "messageDate")
    xmlSerializer.writeStartElement("messageDate");
    xmlSerializer.writeLong(object.getMessageDate());
    xmlSerializer.writeEndElement();

    // field messageText (mapped with "messageText")
    if (object.getMessageText()!=null) {
      xmlSerializer.writeStartElement("messageText");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getMessageText()));
      xmlSerializer.writeEndElement();
    }

    // field value (mapped with "value")
    xmlSerializer.writeStartElement("value");
    xmlSerializer.writeLong(object.getValue());
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Bean01 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean01 instance = new Bean01();
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
          case "beanList":
            // field beanList (mapped with "beanList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean02> collection=new ArrayList<>();
              Bean02 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean02BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setBeanList(collection);
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(jacksonParser.getLongValue());
          break;
          case "lista":
            // field lista (mapped with "lista")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean02> collection=new ArrayList<>();
              Bean02 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean02BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setLista(collection);
            }
          break;
          case "messageDate":
            // field messageDate (mapped with "messageDate")
            instance.setMessageDate(jacksonParser.getLongValue());
          break;
          case "messageText":
            // field messageText (mapped with "messageText")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setMessageText(jacksonParser.getText());
            }
          break;
          case "value":
            // field value (mapped with "value")
            instance.setValue(jacksonParser.getLongValue());
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
  public Bean01 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean01 instance = new Bean01();
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
          case "beanList":
            // field beanList (mapped with "beanList")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean02> collection=new ArrayList<>();
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
              instance.setBeanList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean02> collection=new ArrayList<>();
              instance.setBeanList(collection);
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "lista":
            // field lista (mapped with "lista")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean02> collection=new ArrayList<>();
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
              instance.setLista(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean02> collection=new ArrayList<>();
              instance.setLista(collection);
            }
          break;
          case "messageDate":
            // field messageDate (mapped with "messageDate")
            instance.setMessageDate(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
          break;
          case "messageText":
            // field messageText (mapped with "messageText")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setMessageText(jacksonParser.getText());
            }
          break;
          case "value":
            // field value (mapped with "value")
            instance.setValue(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
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
  public Bean01 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean01 instance = new Bean01();
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
                case "beanList":
                  // property beanList (mapped on "beanList")
                   {
                    ArrayList<Bean02> collection=new ArrayList<>();
                    Bean02 item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean02BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("beanList")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean02BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setBeanList(collection);
                    read=false;
                  }
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "lista":
                  // property lista (mapped on "lista")
                   {
                    ArrayList<Bean02> collection=new ArrayList<>();
                    Bean02 item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean02BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("lista")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean02BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setLista(collection);
                    read=false;
                  }
                break;
                case "messageDate":
                  // property messageDate (mapped on "messageDate")
                  instance.setMessageDate(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                break;
                case "messageText":
                  // property messageText (mapped on "messageText")
                  instance.setMessageText(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "value":
                  // property value (mapped on "value")
                  instance.setValue(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
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
