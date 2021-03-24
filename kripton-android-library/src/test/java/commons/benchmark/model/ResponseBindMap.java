package commons.benchmark.model;

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
 * This class is binder map for Response
 *
 * @see Response
 */
@BindMap(Response.class)
public class ResponseBindMap extends AbstractMapper<Response> {
  /**
   * binder for type User
   */
  private UserBindMap userBindMap;

  @Override
  public int serializeOnJackson(Response object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field status (mapped with "status")
    if (object.status!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("status", object.status);
    }

    // field users (mapped with "users")
    if (object.users!=null)  {
      fieldCount++;
      int n=object.users.size();
      User item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("users");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.users.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          userBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field isRealJson (mapped with "is_real_json")
    fieldCount++;
    jacksonSerializer.writeBooleanField("is_real_json", object.isRealJson);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Response object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field status (mapped with "status")
    if (object.status!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("status", object.status);
    }

    // field users (mapped with "users")
    if (object.users!=null)  {
      fieldCount++;
      int n=object.users.size();
      User item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("users");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.users.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (userBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("users");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field isRealJson (mapped with "is_real_json")
    jacksonSerializer.writeStringField("is_real_json", PrimitiveUtils.writeBoolean(object.isRealJson));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Response object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("response");
    }

    // Persisted fields:

    // field status (mapped with "status")
    if (object.status!=null) {
      xmlSerializer.writeStartElement("status");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.status));
      xmlSerializer.writeEndElement();
    }

    // field users (mapped with "users")
    if (object.users!=null)  {
      int n=object.users.size();
      User item;
      for (int i=0; i<n; i++) {
        item=object.users.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("users");
        } else {
          xmlSerializer.writeStartElement("users");
          userBindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("users");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field isRealJson (mapped with "is_real_json")
    xmlSerializer.writeStartElement("is_real_json");
    xmlSerializer.writeBoolean(object.isRealJson);
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Response parseOnJackson(JsonParser jacksonParser) throws Exception {
    Response instance = new Response();
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
          case "status":
            // field status (mapped with "status")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.status=jacksonParser.getText();
            }
          break;
          case "users":
            // field users (mapped with "users")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<User> collection=new ArrayList<>();
              User item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=userBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.users=collection;
            }
          break;
          case "is_real_json":
            // field isRealJson (mapped with "is_real_json")
            instance.isRealJson=jacksonParser.getBooleanValue();
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
  public Response parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Response instance = new Response();
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
          case "status":
            // field status (mapped with "status")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.status=jacksonParser.getText();
            }
          break;
          case "users":
            // field users (mapped with "users")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<User> collection=new ArrayList<>();
              User item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=userBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.users=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<User> collection=new ArrayList<>();
              instance.users=collection;
            }
          break;
          case "is_real_json":
            // field isRealJson (mapped with "is_real_json")
            instance.isRealJson=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
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
  public Response parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Response instance = new Response();
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
                case "status":
                  // property status (mapped on "status")
                  instance.status=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "users":
                  // property users (mapped on "users")
                   {
                    ArrayList<User> collection=CollectionUtils.merge(new ArrayList<>(), instance.users);
                    User item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=userBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("users")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=userBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.users=collection;
                    read=false;
                  }
                break;
                case "is_real_json":
                  // property isRealJson (mapped on "is_real_json")
                  instance.isRealJson=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
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
      return instance;
    }

    public void init() {
      // binding maps initialization 
      userBindMap=BinderUtils.mapperFor(User.class);
    }
  }
