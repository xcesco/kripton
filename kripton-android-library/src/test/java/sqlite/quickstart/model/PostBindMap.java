package sqlite.quickstart.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Post
 *
 * @see Post
 */
@BindMap(Post.class)
public class PostBindMap extends AbstractMapper<Post> {
  @Override
  public int serializeOnJackson(Post object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field body (mapped with "body")
    if (object.body!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("body", object.body);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    // field userId (mapped with "userId")
    fieldCount++;
    jacksonSerializer.writeNumberField("userId", object.userId);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Post object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field body (mapped with "body")
    if (object.body!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("body", object.body);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    // field userId (mapped with "userId")
    jacksonSerializer.writeStringField("userId", PrimitiveUtils.writeLong(object.userId));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Post object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("post");
    }

    // Persisted fields:

    // field body (mapped with "body")
    if (object.body!=null) {
      xmlSerializer.writeStartElement("body");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.body));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field title (mapped with "title")
    if (object.title!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.title));
      xmlSerializer.writeEndElement();
    }

    // field userId (mapped with "userId")
    xmlSerializer.writeStartElement("userId");
    xmlSerializer.writeLong(object.userId);
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Post parseOnJackson(JsonParser jacksonParser) throws Exception {
    Post instance = new Post();
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
          case "body":
            // field body (mapped with "body")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.body=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
            }
          break;
          case "userId":
            // field userId (mapped with "userId")
            instance.userId=jacksonParser.getLongValue();
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
  public Post parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Post instance = new Post();
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
          case "body":
            // field body (mapped with "body")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.body=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
            }
          break;
          case "userId":
            // field userId (mapped with "userId")
            instance.userId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public Post parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Post instance = new Post();
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
                case "body":
                  // property body (mapped on "body")
                  instance.body=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "title":
                  // property title (mapped on "title")
                  instance.title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "userId":
                  // property userId (mapped on "userId")
                  instance.userId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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

    @Override
    public void init() {
      // binding maps initialization 
    }
  }
