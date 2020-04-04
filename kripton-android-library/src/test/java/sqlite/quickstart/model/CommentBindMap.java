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
 * This class is binder map for Comment
 *
 * @see Comment
 */
@BindMap(Comment.class)
public class CommentBindMap extends AbstractMapper<Comment> {
  @Override
  public int serializeOnJackson(Comment object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field body (mapped with "body")
    if (object.body!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("body", object.body);
    }

    // field email (mapped with "email")
    if (object.email!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.email);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field postId (mapped with "postId")
    fieldCount++;
    jacksonSerializer.writeNumberField("postId", object.postId);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Comment object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field body (mapped with "body")
    if (object.body!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("body", object.body);
    }

    // field email (mapped with "email")
    if (object.email!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.email);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field postId (mapped with "postId")
    jacksonSerializer.writeStringField("postId", PrimitiveUtils.writeLong(object.postId));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Comment object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("comment");
    }

    // Persisted fields:

    // field body (mapped with "body")
    if (object.body!=null) {
      xmlSerializer.writeStartElement("body");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.body));
      xmlSerializer.writeEndElement();
    }

    // field email (mapped with "email")
    if (object.email!=null) {
      xmlSerializer.writeStartElement("email");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.email));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
      xmlSerializer.writeEndElement();
    }

    // field postId (mapped with "postId")
    xmlSerializer.writeStartElement("postId");
    xmlSerializer.writeLong(object.postId);
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Comment parseOnJackson(JsonParser jacksonParser) throws Exception {
    Comment instance = new Comment();
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
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.email=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "postId":
            // field postId (mapped with "postId")
            instance.postId=jacksonParser.getLongValue();
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
  public Comment parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Comment instance = new Comment();
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
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.email=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "postId":
            // field postId (mapped with "postId")
            instance.postId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public Comment parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Comment instance = new Comment();
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
                case "email":
                  // property email (mapped on "email")
                  instance.email=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "postId":
                  // property postId (mapped on "postId")
                  instance.postId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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
  }
