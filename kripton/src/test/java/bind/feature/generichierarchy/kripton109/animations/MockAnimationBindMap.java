package bind.feature.generichierarchy.kripton109.animations;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
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

/**
 * This class is binder map for MockAnimation
 *
 * @see MockAnimation
 */
@BindMap(MockAnimation.class)
public class MockAnimationBindMap extends AbstractMapper<MockAnimation> {
  /**
   * MockKeyFrameBindMap */
  private MockKeyFrameBindMap mockKeyFrameBindMap = BinderUtils.mapperFor(MockKeyFrame.class);

  @Override
  public int serializeOnJackson(MockAnimation object, JsonGenerator jacksonSerializer) throws
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
      MockKeyFrame item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("frames");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.frames.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          mockKeyFrameBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(MockAnimation object, JsonGenerator jacksonSerializer)
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
      MockKeyFrame item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("frames");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.frames.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (mockKeyFrameBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("frames");
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
  public void serializeOnXml(MockAnimation object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("mockAnimation");
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
      MockKeyFrame item;
      // write wrapper tag
      xmlSerializer.writeStartElement("frames");
      for (int i=0; i<n; i++) {
        item=object.frames.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("frame");
        } else {
          xmlSerializer.writeStartElement("frame");
          mockKeyFrameBindMap.serializeOnXml(item, xmlSerializer, 2);
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
  public MockAnimation parseOnJackson(JsonParser jacksonParser) throws Exception {
    MockAnimation instance = new MockAnimation();
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
              ArrayList<MockKeyFrame> collection=new ArrayList<>();
              MockKeyFrame item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=mockKeyFrameBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.frames=collection;
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
  public MockAnimation parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    MockAnimation instance = new MockAnimation();
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
              ArrayList<MockKeyFrame> collection=new ArrayList<>();
              MockKeyFrame item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=mockKeyFrameBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.frames=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<MockKeyFrame> collection=new ArrayList<>();
              instance.frames=collection;
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
  public MockAnimation parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    MockAnimation instance = new MockAnimation();
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
                    ArrayList<MockKeyFrame> collection=CollectionUtils.merge(new ArrayList<>(), instance.frames);
                    MockKeyFrame item;
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("frame")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=mockKeyFrameBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.frames=collection;
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
