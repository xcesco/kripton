package sqlite.feature.kotlin.immutable2;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is binder map for RssFeed
 *
 * @see RssFeed
 */
@BindMap(RssFeed.class)
public class RssFeedBindMap extends AbstractMapper<RssFeed> {
  /**
   * ChannelBindMap */
  private ChannelBindMap channelBindMap = BinderUtils.mapperFor(Channel.class);

  @Override
  public int serializeOnJackson(RssFeed object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    // field version (mapped with "version")
    if (object.getVersion()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("version", object.getVersion());
    }

    // field channels (mapped with "channel")
    if (object.getChannels()!=null)  {
      fieldCount++;
      int n=object.getChannels().size();
      Channel item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("channel");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getChannels().get(i);
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
  public int serializeOnJacksonAsString(RssFeed object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field uid (mapped with "uid")
    if (object.getUid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.getUid());
    }

    // field version (mapped with "version")
    if (object.getVersion()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("version", object.getVersion());
    }

    // field channels (mapped with "channel")
    if (object.getChannels()!=null)  {
      fieldCount++;
      int n=object.getChannels().size();
      Channel item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("channel");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getChannels().get(i);
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
  public void serializeOnXml(RssFeed object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("rss");
    }

    // Persisted fields:

    // field version (mapped with "version")
    if (object.getVersion()!=null) {
      xmlSerializer.writeAttribute("version", StringEscapeUtils.escapeXml10(object.getVersion()));
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field uid (mapped with "uid")
    if (object.getUid()!=null) {
      xmlSerializer.writeStartElement("uid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getUid()));
      xmlSerializer.writeEndElement();
    }

    // field channels (mapped with "channel")
    if (object.getChannels()!=null)  {
      int n=object.getChannels().size();
      Channel item;
      for (int i=0; i<n; i++) {
        item=object.getChannels().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("channel");
        } else {
          xmlSerializer.writeStartElement("channel");
          channelBindMap.serializeOnXml(item, xmlSerializer, 2);
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

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public RssFeed parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __uid=null;
    String __version=null;
    List<Channel> __channels=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      RssFeed instance=new RssFeed(__id,__uid,__version,(__channels==null ? null : Collections.unmodifiableList(__channels)));
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
              __version=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            __id=jacksonParser.getLongValue();
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __uid=jacksonParser.getText();
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
              __channels=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    RssFeed instance=new RssFeed(__id,__uid,__version,(__channels==null ? null : Collections.unmodifiableList(__channels)));
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public RssFeed parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __uid=null;
    String __version=null;
    List<Channel> __channels=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      RssFeed instance=new RssFeed(__id,__uid,__version,(__channels==null ? null : Collections.unmodifiableList(__channels)));
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
              __version=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            __id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __uid=jacksonParser.getText();
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
              __channels=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Channel> collection=new ArrayList<>();
              __channels=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    RssFeed instance=new RssFeed(__id,__uid,__version,(__channels==null ? null : Collections.unmodifiableList(__channels)));
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public RssFeed parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __uid=null;
    String __version=null;
    List<Channel> __channels=null;
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;

    // attributes 
    String attributeName = null;
    int attributesCount = xmlParser.getAttributeCount();;
    for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
      attributeName = xmlParser.getAttributeName(attributeIndex);
      switch(attributeName) {
          case "version":
            // field version (mapped by "version")
            __version=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
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
          case XmlPullParser.START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "id":
                  // property id (mapped on "id")
                  __id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "uid":
                  // property uid (mapped on "uid")
                  __uid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "channel":
                  // property channels (mapped on "channel")
                   {
                    ArrayList<Channel> collection=new ArrayList<>();
                    Channel item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=channelBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("channel")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=channelBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    __channels=collection;
                    read=false;
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
      // immutable object: inizialize object
      RssFeed instance=new RssFeed(__id,__uid,__version,(__channels==null ? null : Collections.unmodifiableList(__channels)));
      return instance;
    }
  }
