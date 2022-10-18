package sqlite.feature.relations.case4.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
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
 * This class is binder map for RSSFeed
 *
 * @see RSSFeed
 */
@BindMap(RSSFeed.class)
public class RSSFeedBindMap extends AbstractMapper<RSSFeed> {
  /**
   * binder for type Channel
   */
  private ChannelBindMap channelBindMap;

  @Override
  public int serializeOnJackson(RSSFeed object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field version (mapped with "version")
    if (object.version!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("version", object.version);
    }

    // field channels (mapped with "channel")
    if (object.channels!=null)  {
      fieldCount++;
      int n=object.channels.size();
      Channel item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("channel");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.channels.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          channelBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(RSSFeed object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field version (mapped with "version")
    if (object.version!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("version", object.version);
    }

    // field channels (mapped with "channel")
    if (object.channels!=null)  {
      fieldCount++;
      int n=object.channels.size();
      Channel item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("channel");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.channels.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (channelBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("channel");
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
  public void serializeOnXml(RSSFeed object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("rss");
    }

    // Persisted fields:

    // field version (mapped with "version")
    if (object.version!=null) {
      xmlSerializer.writeAttribute("version", StringEscapeUtils.escapeXml10(object.version));
    }

    // field channels (mapped with "channel")
    if (object.channels!=null)  {
      int n=object.channels.size();
      Channel item;
      for (int i=0; i<n; i++) {
        item=object.channels.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("channel");
        } else {
          xmlSerializer.writeStartElement("channel");
          channelBindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("channel");
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
  public RSSFeed parseOnJackson(JsonParser jacksonParser) throws Exception {
    RSSFeed instance = new RSSFeed();
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
          case "version":
            // field version (mapped with "version")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.version=jacksonParser.getText();
            }
          break;
          case "channel":
            // field channels (mapped with "channel")
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
              instance.channels=collection;
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
  public RSSFeed parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    RSSFeed instance = new RSSFeed();
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
          case "version":
            // field version (mapped with "version")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.version=jacksonParser.getText();
            }
          break;
          case "channel":
            // field channels (mapped with "channel")
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
              instance.channels=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Channel> collection=new ArrayList<>();
              instance.channels=collection;
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
  public RSSFeed parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    RSSFeed instance = new RSSFeed();
    EventType eventType = currentEventType;
    boolean read=true;

    if (currentEventType == EventType.START_DOCUMENT) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName();
    String elementName = currentTag;

    // attributes 
    String attributeName = null;
    int attributesCount = xmlParser.getAttributeCount();;
    for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
      attributeName = xmlParser.getAttributeName(attributeIndex);
      switch(attributeName) {
          case "version":
            // field version (mapped by "version")
            instance.version=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
          break;
          default:
          break;
      }
    }

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
                case "channel":
                  // property channels (mapped on "channel")
                   {
                    ArrayList<Channel> collection=CollectionUtils.merge(new ArrayList<>(), instance.channels);
                    Channel item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=channelBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("channel")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=channelBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.channels=collection;
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
        channelBindMap=BinderUtils.mapperFor(Channel.class);
      }
    }
