package bind.feature.generichierarchy.kripton109.animations;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
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

/**
 * This class is binder map for TiledMapAnimation
 *
 * @see TiledMapAnimation
 */
@BindMap(TiledMapAnimation.class)
public class TiledMapAnimationBindMap extends AbstractMapper<TiledMapAnimation> {
  /**
   * binder for type TranslationFrame
   */
  private TranslationFrameBindMap translationFrameBindMap;

  /**
   * binder for type TextureKeyFrame
   */
  private TextureKeyFrameBindMap textureKeyFrameBindMap;

  @Override
  public int serializeOnJackson(TiledMapAnimation object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field loop (mapped with "loop")
    fieldCount++;
    jacksonSerializer.writeBooleanField("loop", object.isLoop());

    // field rate (mapped with "rate")
    fieldCount++;
    jacksonSerializer.writeNumberField("rate", object.getRate());

    // field frames (mapped with "frames")
    if (object.frames!=null)  {
      fieldCount++;
      int n=object.frames.size();
      TranslationFrame item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("frames");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.frames.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          translationFrameBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field frames1 (mapped with "frame1")
    if (object.frames1!=null)  {
      fieldCount++;
      int n=object.frames1.size();
      TextureKeyFrame item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("frame1");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.frames1.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          textureKeyFrameBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(TiledMapAnimation object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field name (mapped with "name")
    if (object.getName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.getName());
    }

    // field loop (mapped with "loop")
    jacksonSerializer.writeStringField("loop", PrimitiveUtils.writeBoolean(object.isLoop()));

    // field rate (mapped with "rate")
    jacksonSerializer.writeStringField("rate", PrimitiveUtils.writeFloat(object.getRate()));

    // field frames (mapped with "frames")
    if (object.frames!=null)  {
      fieldCount++;
      int n=object.frames.size();
      TranslationFrame item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("frames");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.frames.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (translationFrameBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("frames");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field frames1 (mapped with "frame1")
    if (object.frames1!=null)  {
      fieldCount++;
      int n=object.frames1.size();
      TextureKeyFrame item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("frame1");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.frames1.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (textureKeyFrameBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("frame1");
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
  public void serializeOnXml(TiledMapAnimation object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("tiledMapAnimation");
    }

    // Persisted fields:

    // field name (mapped with "name")
    if (object.getName()!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
      xmlSerializer.writeEndElement();
    }

    // field loop (mapped with "loop")
    xmlSerializer.writeStartElement("loop");
    xmlSerializer.writeBoolean(object.isLoop());
    xmlSerializer.writeEndElement();

    // field rate (mapped with "rate")
    xmlSerializer.writeStartElement("rate");
    xmlSerializer.writeFloat(object.getRate());
    xmlSerializer.writeEndElement();

    // field frames (mapped with "frames")
    if (object.frames!=null)  {
      int n=object.frames.size();
      TranslationFrame item;
      // write wrapper tag
      xmlSerializer.writeStartElement("frames");
      for (int i=0; i<n; i++) {
        item=object.frames.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("frame");
        } else {
          xmlSerializer.writeStartElement("frame");
          translationFrameBindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      xmlSerializer.writeEndElement();
    }

    // field frames1 (mapped with "frame1")
    if (object.frames1!=null)  {
      int n=object.frames1.size();
      TextureKeyFrame item;
      for (int i=0; i<n; i++) {
        item=object.frames1.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("frame1");
        } else {
          xmlSerializer.writeStartElement("frame1");
          textureKeyFrameBindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("frame1");
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
  public TiledMapAnimation parseOnJackson(JsonParser jacksonParser) throws Exception {
    TiledMapAnimation instance = new TiledMapAnimation();
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
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
            }
          break;
          case "loop":
            // field loop (mapped with "loop")
            instance.setLoop(jacksonParser.getBooleanValue());
          break;
          case "rate":
            // field rate (mapped with "rate")
            instance.setRate(jacksonParser.getFloatValue());
          break;
          case "frames":
            // field frames (mapped with "frames")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<TranslationFrame> collection=new ArrayList<>();
              TranslationFrame item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=translationFrameBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.frames=collection;
            }
          break;
          case "frame1":
            // field frames1 (mapped with "frame1")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<TextureKeyFrame> collection=new ArrayList<>();
              TextureKeyFrame item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=textureKeyFrameBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.frames1=collection;
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
  public TiledMapAnimation parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    TiledMapAnimation instance = new TiledMapAnimation();
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
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setName(jacksonParser.getText());
            }
          break;
          case "loop":
            // field loop (mapped with "loop")
            instance.setLoop(PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false));
          break;
          case "rate":
            // field rate (mapped with "rate")
            instance.setRate(PrimitiveUtils.readFloat(jacksonParser.getText(), 0f));
          break;
          case "frames":
            // field frames (mapped with "frames")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<TranslationFrame> collection=new ArrayList<>();
              TranslationFrame item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=translationFrameBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.frames=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<TranslationFrame> collection=new ArrayList<>();
              instance.frames=collection;
            }
          break;
          case "frame1":
            // field frames1 (mapped with "frame1")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<TextureKeyFrame> collection=new ArrayList<>();
              TextureKeyFrame item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=textureKeyFrameBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.frames1=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<TextureKeyFrame> collection=new ArrayList<>();
              instance.frames1=collection;
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
  public TiledMapAnimation parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    TiledMapAnimation instance = new TiledMapAnimation();
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
                case "name":
                  // property name (mapped on "name")
                  instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "loop":
                  // property loop (mapped on "loop")
                  instance.setLoop(PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false));
                break;
                case "rate":
                  // property rate (mapped on "rate")
                  instance.setRate(PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f));
                break;
                case "frames":
                  // property frames (mapped on "frames")
                   {
                    ArrayList<TranslationFrame> collection=CollectionUtils.merge(new ArrayList<>(), instance.frames);
                    TranslationFrame item;
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("frame")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=translationFrameBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.frames=collection;
                  }
                break;
                case "frame1":
                  // property frames1 (mapped on "frame1")
                   {
                    ArrayList<TextureKeyFrame> collection=CollectionUtils.merge(new ArrayList<>(), instance.frames1);
                    TextureKeyFrame item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=textureKeyFrameBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("frame1")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=textureKeyFrameBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.frames1=collection;
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
        textureKeyFrameBindMap=BinderUtils.mapperFor(TextureKeyFrame.class);
        translationFrameBindMap=BinderUtils.mapperFor(TranslationFrame.class);
      }
    }
