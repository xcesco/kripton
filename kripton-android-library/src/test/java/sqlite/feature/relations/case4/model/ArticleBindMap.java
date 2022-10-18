package sqlite.feature.relations.case4.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Article
 *
 * @see Article
 */
@BindMap(Article.class)
public class ArticleBindMap extends AbstractMapper<Article> {
  @Override
  public int serializeOnJackson(Article object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field author (mapped with "author")
    if (object.author!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("author", object.author);
    }

    // field channelId (mapped with "channelId")
    fieldCount++;
    jacksonSerializer.writeNumberField("channelId", object.channelId);

    // field comments (mapped with "comments")
    if (object.comments!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("comments", UrlUtils.write(object.comments));
    }

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field guid (mapped with "guid")
    if (object.guid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("guid", object.guid);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field link (mapped with "link")
    if (object.link!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", UrlUtils.write(object.link));
    }

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Article object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field author (mapped with "author")
    if (object.author!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("author", object.author);
    }

    // field channelId (mapped with "channelId")
    jacksonSerializer.writeStringField("channelId", PrimitiveUtils.writeLong(object.channelId));

    // field comments (mapped with "comments")
    if (object.comments!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("comments", UrlUtils.write(object.comments));
    }

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field guid (mapped with "guid")
    if (object.guid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("guid", object.guid);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field link (mapped with "link")
    if (object.link!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", UrlUtils.write(object.link));
    }

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Article object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("item");
    }

    // Persisted fields:

    // field author (mapped with "author")
    if (object.author!=null) {
      xmlSerializer.writeStartElement("author");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.author));
      xmlSerializer.writeEndElement();
    }

    // field channelId (mapped with "channelId")
    xmlSerializer.writeStartElement("channelId");
    xmlSerializer.writeLong(object.channelId);
    xmlSerializer.writeEndElement();

    // field comments (mapped with "comments")
    if (object.comments!=null)  {
      xmlSerializer.writeStartElement("comments");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.comments)));
      xmlSerializer.writeEndElement();
    }

    // field description (mapped with "description")
    if (object.description!=null) {
      xmlSerializer.writeStartElement("description");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.description));
      xmlSerializer.writeEndElement();
    }

    // field guid (mapped with "guid")
    if (object.guid!=null) {
      xmlSerializer.writeStartElement("guid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.guid));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field link (mapped with "link")
    if (object.link!=null)  {
      xmlSerializer.writeStartElement("link");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.link)));
      xmlSerializer.writeEndElement();
    }

    // field title (mapped with "title")
    if (object.title!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.title));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Article parseOnJackson(JsonParser jacksonParser) throws Exception {
    Article instance = new Article();
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
          case "author":
            // field author (mapped with "author")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.author=jacksonParser.getText();
            }
          break;
          case "channelId":
            // field channelId (mapped with "channelId")
            instance.channelId=jacksonParser.getLongValue();
          break;
          case "comments":
            // field comments (mapped with "comments")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.comments=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "guid":
            // field guid (mapped with "guid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.guid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.link=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
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
  public Article parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Article instance = new Article();
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
          case "author":
            // field author (mapped with "author")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.author=jacksonParser.getText();
            }
          break;
          case "channelId":
            // field channelId (mapped with "channelId")
            instance.channelId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "comments":
            // field comments (mapped with "comments")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.comments=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "guid":
            // field guid (mapped with "guid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.guid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.link=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
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
  public Article parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Article instance = new Article();
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
                case "author":
                  // property author (mapped on "author")
                  instance.author=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "channelId":
                  // property channelId (mapped on "channelId")
                  instance.channelId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "comments":
                  // property comments (mapped on "comments")
                  instance.comments=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "description":
                  // property description (mapped on "description")
                  instance.description=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "guid":
                  // property guid (mapped on "guid")
                  instance.guid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "link":
                  // property link (mapped on "link")
                  instance.link=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "title":
                  // property title (mapped on "title")
                  instance.title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
      }
    }
