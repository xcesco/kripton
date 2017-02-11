package bind.generichierarchy.case1.transfer;

import bind.generichierarchy.case1.model.Message;
import bind.generichierarchy.case1.model.MessageBindMap;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.core.AbstractContext;
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
 * This class is binder map for MessageListResponse
 *
 * @see MessageListResponse
 */
@BindMap(MessageListResponse.class)
public class MessageListResponseBindMap extends AbstractMapper<MessageListResponse> {
  /**
   * MessageBindMap */
  private MessageBindMap messageBindMap = AbstractContext.mapperFor(Message.class);

  @Override
  public int serializeOnJackson(MessageListResponse object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field detailMessage (mapped with "detailMessage")
    if (object.getDetailMessage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("detailMessage", object.getDetailMessage());
    }

    // field list (mapped with "list")
    if (object.getList()!=null)  {
      fieldCount++;
      int n=object.getList().size();
      Message item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("list");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getList().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          messageBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
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
  public int serializeOnJacksonAsString(MessageListResponse object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field detailMessage (mapped with "detailMessage")
    if (object.getDetailMessage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("detailMessage", object.getDetailMessage());
    }

    // field list (mapped with "list")
    if (object.getList()!=null)  {
      fieldCount++;
      int n=object.getList().size();
      Message item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("list");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getList().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (messageBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("list");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
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
  public void serializeOnXml(MessageListResponse object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("messageListResponse");
    }

    // Persisted fields:

    // field detailMessage (mapped with "detailMessage")
    if (object.getDetailMessage()!=null) {
      xmlSerializer.writeStartElement("detailMessage");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDetailMessage()));
      xmlSerializer.writeEndElement();
    }

    // field list (mapped with "list")
    if (object.getList()!=null)  {
      int n=object.getList().size();
      Message item;
      for (int i=0; i<n; i++) {
        item=object.getList().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("list");
        } else {
          xmlSerializer.writeStartElement("list");
          messageBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("list");
        xmlSerializer.writeAttribute("emptyCollection", "true");
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
  public MessageListResponse parseOnJackson(JsonParser jacksonParser) throws Exception {
    MessageListResponse instance = new MessageListResponse();
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
          case "detailMessage":
            // field detailMessage (mapped with "detailMessage")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDetailMessage(jacksonParser.getText());
            }
          break;
          case "list":
            // field list (mapped with "list")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Message> collection=new ArrayList<>();
              Message item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=messageBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.setList(collection);
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
  public MessageListResponse parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    MessageListResponse instance = new MessageListResponse();
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
          case "detailMessage":
            // field detailMessage (mapped with "detailMessage")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.setDetailMessage(jacksonParser.getText());
            }
          break;
          case "list":
            // field list (mapped with "list")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Message> collection=new ArrayList<>();
              Message item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=messageBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.setList(collection);
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Message> collection=new ArrayList<>();
              instance.setList(collection);
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
  public MessageListResponse parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    MessageListResponse instance = new MessageListResponse();
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
                case "detailMessage":
                  // property detailMessage (mapped on "detailMessage")
                  instance.setDetailMessage(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "list":
                  // property list (mapped on "list")
                   {
                    ArrayList<Message> collection=new ArrayList<>();
                    Message item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=messageBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("list")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=messageBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.setList(collection);
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
