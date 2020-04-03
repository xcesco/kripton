package bind.feature.generichierarchy;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is binder map for ChannelListResponse
 *
 * @see ChannelListResponse
 */
@BindMap(ChannelListResponse.class)
public class ChannelListResponseBindMap extends AbstractMapper<ChannelListResponse> {
  /**
   * ChannelBindMap */
  private ChannelBindMap channelBindMap = BinderUtils.mapperFor(Channel.class);

  @Override
  public int serializeOnJackson(ChannelListResponse object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field bean (mapped with "bean")
    if (object.bean!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("bean");
      channelBindMap.serializeOnJackson(object.bean, jacksonSerializer);
    }

    // field detailMessage (mapped with "detailMessage")
    if (object.getDetailMessage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("detailMessage", object.getDetailMessage());
    }

    // field list (mapped with "list")
    if (object.getList()!=null)  {
      fieldCount++;
      int n=object.getList().size();
      Channel item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("list");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          channelBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field map (mapped with "map")
    if (object.map!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.map.size()>0) {
        jacksonSerializer.writeFieldName("map");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Channel> item: object.map.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeFieldName("value");
            channelBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("map");
      }
    }

    // field status (mapped with "status")
    if (object.getStatus()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("status", object.getStatus().toString());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(ChannelListResponse object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field bean (mapped with "bean")
    if (object.bean!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("bean");
      if (channelBindMap.serializeOnJacksonAsString(object.bean, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("bean");
      }
    }

    // field detailMessage (mapped with "detailMessage")
    if (object.getDetailMessage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("detailMessage", object.getDetailMessage());
    }

    // field list (mapped with "list")
    if (object.getList()!=null)  {
      fieldCount++;
      int n=object.getList().size();
      Channel item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("list");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (channelBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("list");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field map (mapped with "map")
    if (object.map!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.map.size()>0) {
        jacksonSerializer.writeFieldName("map");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<String, Channel> item: object.map.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeFieldName("value");
            if (channelBindMap.serializeOnJacksonAsString(item.getValue(), jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("value");
            }
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("map", "null");
      }
    }

    // field status (mapped with "status")
    if (object.getStatus()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("status", object.getStatus().toString());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(ChannelListResponse object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("channelListResponse");
    }

    // Persisted fields:

    // field bean (mapped with "bean")
    if (object.bean!=null)  {
      xmlSerializer.writeStartElement("bean");
      channelBindMap.serializeOnXml(object.bean, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field detailMessage (mapped with "detailMessage")
    if (object.getDetailMessage()!=null) {
      xmlSerializer.writeStartElement("detailMessage");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDetailMessage()));
      xmlSerializer.writeEndElement();
    }

    // field list (mapped with "list")
    if (object.getList()!=null)  {
      int n=object.getList().size();
      Channel item;
      // write wrapper tag
      xmlSerializer.writeStartElement("list");
      for (int i=0; i<n; i++) {
        item=object.getList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          channelBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field map (mapped with "map")
    if (object.map!=null)  {
      for (Map.Entry<String, Channel> item: object.map.entrySet()) {
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
              channelBindMap.serializeOnXml(item.getValue(), xmlSerializer, 2);
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    // field status (mapped with "status")
    if (object.getStatus()!=null)  {
      xmlSerializer.writeStartElement("status");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getStatus().toString()));
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
  public ChannelListResponse parseOnJackson(JsonParser jacksonParser) throws Exception {
    ChannelListResponse instance = new ChannelListResponse();
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
          case "bean":
            // field bean (mapped with "bean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.bean=channelBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "detailMessage":
            // field detailMessage (mapped with "detailMessage")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDetailMessage(jacksonParser.getText());
            }
          break;
          case "list":
            // field list (mapped with "list")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Channel> collection=new ArrayList<>();
              Channel item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=channelBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setList(collection);
            }
          break;
          case "map":
            // field map (mapped with "map")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Channel> collection=new HashMap<>();
              String key=null;
              Channel value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                key=jacksonParser.getText();
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                  value=channelBindMap.parseOnJackson(jacksonParser);
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.map=collection;
            }
          break;
          case "status":
            // field status (mapped with "status")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setStatus(StringUtils.hasText(tempEnum)?ServiceStatusType.valueOf(tempEnum):null);
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
  public ChannelListResponse parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ChannelListResponse instance = new ChannelListResponse();
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
          case "bean":
            // field bean (mapped with "bean")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.bean=channelBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "detailMessage":
            // field detailMessage (mapped with "detailMessage")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDetailMessage(jacksonParser.getText());
            }
          break;
          case "list":
            // field list (mapped with "list")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Channel> collection=new ArrayList<>();
              Channel item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=channelBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.setList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Channel> collection=new ArrayList<>();
              instance.setList(collection);
            }
          break;
          case "map":
            // field map (mapped with "map")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<String, Channel> collection=new HashMap<>();
              String key=null;
              Channel value=null;
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
                      if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
                        value=channelBindMap.parseOnJacksonAsString(jacksonParser);
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
              instance.map=collection;
            }
          break;
          case "status":
            // field status (mapped with "status")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.setStatus(StringUtils.hasText(tempEnum)?ServiceStatusType.valueOf(tempEnum):null);
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
  public ChannelListResponse parseOnXml(XMLParser xmlParser, int currentEventType) throws
      Exception {
    ChannelListResponse instance = new ChannelListResponse();
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
                case "bean":
                  // property bean (mapped on "bean")
                  instance.bean=channelBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "detailMessage":
                  // property detailMessage (mapped on "detailMessage")
                  instance.setDetailMessage(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "list":
                  // property list (mapped on "list")
                   {
                    ArrayList<Channel> collection=CollectionUtils.merge(new ArrayList<>(), instance.getList());
                    Channel item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=channelBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setList(collection);
                  }
                break;
                case "map":
                  // property map (mapped on "map")
                   {
                    HashMap<String, Channel> collection=new HashMap<>();
                    String key;
                    Channel value;
                    // add first element
                    xmlParser.nextTag();
                    key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=channelBindMap.parseOnXml(xmlParser, eventType);
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("map")) {
                      xmlParser.nextTag();
                      key=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=channelBindMap.parseOnXml(xmlParser, eventType);
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.map=collection;
                    read=false;
                  }
                break;
                case "status":
                  // property status (mapped on "status")
                  instance.setStatus(ServiceStatusType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
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
