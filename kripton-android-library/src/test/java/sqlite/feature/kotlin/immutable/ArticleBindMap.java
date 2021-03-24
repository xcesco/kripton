package sqlite.feature.kotlin.immutable;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
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
import java.net.URL;

/**
 * This class is binder map for Article
 *
 * @see Article
 */
@BindMap(Article.class)
public class ArticleBindMap extends AbstractMapper<Article> {
  /**
   * binder for type Thumbnail
   */
  private ThumbnailBindMap thumbnailBindMap;

  @Override
  public int serializeOnJackson(Article object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field author (mapped with "author")
    if (object.getAuthor()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("author", object.getAuthor());
    }

    // field channelId (mapped with "channelId")
    fieldCount++;
    jacksonSerializer.writeNumberField("channelId", object.getChannelId());

    // field comments (mapped with "comments")
    if (object.getComments()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("comments", UrlUtils.write(object.getComments()));
    }

    // field description (mapped with "description")
    if (object.getDescription()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.getDescription());
    }

    // field guid (mapped with "guid")
    if (object.getGuid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("guid", object.getGuid());
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field link (mapped with "link")
    if (object.getLink()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", UrlUtils.write(object.getLink()));
    }

    // field read (mapped with "read")
    fieldCount++;
    jacksonSerializer.writeBooleanField("read", object.isRead());

    // field title (mapped with "title")
    if (object.getTitle()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.getTitle());
    }

    // field thumbnail (mapped with "thumbnail")
    if (object.getThumbnail()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("thumbnail");
      thumbnailBindMap.serializeOnJackson(object.getThumbnail(), jacksonSerializer);
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
    if (object.getAuthor()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("author", object.getAuthor());
    }

    // field channelId (mapped with "channelId")
    jacksonSerializer.writeStringField("channelId", PrimitiveUtils.writeLong(object.getChannelId()));

    // field comments (mapped with "comments")
    if (object.getComments()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("comments", UrlUtils.write(object.getComments()));
    }

    // field description (mapped with "description")
    if (object.getDescription()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.getDescription());
    }

    // field guid (mapped with "guid")
    if (object.getGuid()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("guid", object.getGuid());
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field link (mapped with "link")
    if (object.getLink()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", UrlUtils.write(object.getLink()));
    }

    // field read (mapped with "read")
    jacksonSerializer.writeStringField("read", PrimitiveUtils.writeBoolean(object.isRead()));

    // field title (mapped with "title")
    if (object.getTitle()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.getTitle());
    }

    // field thumbnail (mapped with "thumbnail")
    if (object.getThumbnail()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("thumbnail");
      if (thumbnailBindMap.serializeOnJacksonAsString(object.getThumbnail(), jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("thumbnail");
      }
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
      xmlSerializer.writeAttribute("", "xmlns:dc", "http://purl.org/dc/elements/1.1/");
      xmlSerializer.writeAttribute("", "xmlns:content", "http://purl.org/dc/elements/1.1/");
    }

    // Persisted fields:

    // field author (mapped with "author")
    if (object.getAuthor()!=null) {
      xmlSerializer.writeStartElement("author");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getAuthor()));
      xmlSerializer.writeEndElement();
    }

    // field channelId (mapped with "channelId")
    xmlSerializer.writeStartElement("channelId");
    xmlSerializer.writeLong(object.getChannelId());
    xmlSerializer.writeEndElement();

    // field comments (mapped with "comments")
    if (object.getComments()!=null)  {
      xmlSerializer.writeStartElement("comments");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.getComments())));
      xmlSerializer.writeEndElement();
    }

    // field description (mapped with "description")
    if (object.getDescription()!=null) {
      xmlSerializer.writeStartElement("description");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDescription()));
      xmlSerializer.writeEndElement();
    }

    // field guid (mapped with "guid")
    if (object.getGuid()!=null) {
      xmlSerializer.writeStartElement("guid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getGuid()));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field link (mapped with "link")
    if (object.getLink()!=null)  {
      xmlSerializer.writeStartElement("link");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtils.write(object.getLink())));
      xmlSerializer.writeEndElement();
    }

    // field read (mapped with "read")
    xmlSerializer.writeStartElement("read");
    xmlSerializer.writeBoolean(object.isRead());
    xmlSerializer.writeEndElement();

    // field title (mapped with "title")
    if (object.getTitle()!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getTitle()));
      xmlSerializer.writeEndElement();
    }

    // field thumbnail (mapped with "media:thumbnail")
    if (object.getThumbnail()!=null)  {
      xmlSerializer.writeStartElement("media:thumbnail");
      thumbnailBindMap.serializeOnXml(object.getThumbnail(), xmlSerializer, EventType.START_TAG);
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
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __title=null;
    String __description=null;
    URL __link=null;
    String __author=null;
    String __guid=null;
    URL __comments=null;
    long __channelId=0;
    Thumbnail __thumbnail=null;
    boolean __read=false;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Article instance=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
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
              __author=jacksonParser.getText();
            }
          break;
          case "channelId":
            // field channelId (mapped with "channelId")
            __channelId=jacksonParser.getLongValue();
          break;
          case "comments":
            // field comments (mapped with "comments")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __comments=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __description=jacksonParser.getText();
            }
          break;
          case "guid":
            // field guid (mapped with "guid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __guid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            __id=jacksonParser.getLongValue();
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __link=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "read":
            // field read (mapped with "read")
            __read=jacksonParser.getBooleanValue();
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __title=jacksonParser.getText();
            }
          break;
          case "thumbnail":
            // field thumbnail (mapped with "thumbnail")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              __thumbnail=thumbnailBindMap.parseOnJackson(jacksonParser);
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Article instance=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Article parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __title=null;
    String __description=null;
    URL __link=null;
    String __author=null;
    String __guid=null;
    URL __comments=null;
    long __channelId=0;
    Thumbnail __thumbnail=null;
    boolean __read=false;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Article instance=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
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
              __author=jacksonParser.getText();
            }
          break;
          case "channelId":
            // field channelId (mapped with "channelId")
            __channelId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "comments":
            // field comments (mapped with "comments")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __comments=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __description=jacksonParser.getText();
            }
          break;
          case "guid":
            // field guid (mapped with "guid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __guid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            __id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __link=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "read":
            // field read (mapped with "read")
            __read=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __title=jacksonParser.getText();
            }
          break;
          case "thumbnail":
            // field thumbnail (mapped with "thumbnail")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              __thumbnail=thumbnailBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Article instance=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Article parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __title=null;
    String __description=null;
    URL __link=null;
    String __author=null;
    String __guid=null;
    URL __comments=null;
    long __channelId=0;
    Thumbnail __thumbnail=null;
    boolean __read=false;
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
                case "author":
                  // property author (mapped on "author")
                  __author=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "channelId":
                  // property channelId (mapped on "channelId")
                  __channelId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "comments":
                  // property comments (mapped on "comments")
                  __comments=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "description":
                  // property description (mapped on "description")
                  __description=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "guid":
                  // property guid (mapped on "guid")
                  __guid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  __id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "link":
                  // property link (mapped on "link")
                  __link=UrlUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "read":
                  // property read (mapped on "read")
                  __read=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "title":
                  // property title (mapped on "title")
                  __title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "media:thumbnail":
                  // property thumbnail (mapped on "media:thumbnail")
                  __thumbnail=thumbnailBindMap.parseOnXml(xmlParser, eventType);
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
      // immutable object: inizialize object
      Article instance=new Article(__id,__title,__description,__link,__author,__guid,__comments,__channelId,__thumbnail,__read);
      return instance;
    }

    public void init() {
      // binding maps initialization 
      thumbnailBindMap=BinderUtils.mapperFor(Thumbnail.class);
    }
  }
