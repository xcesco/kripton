package sqlite.feature.kotlin.immutable;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
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
import java.util.Date;
import java.util.List;

/**
 * This class is binder map for Channel
 *
 * @see Channel
 */
@BindMap(Channel.class)
public class ChannelBindMap extends AbstractMapper<Channel> {
  /**
   * ImageBindMap */
  private ImageBindMap imageBindMap = BinderUtils.mapperFor(Image.class);

  /**
   * ArticleBindMap */
  private ArticleBindMap articleBindMap = BinderUtils.mapperFor(Article.class);

  @Override
  public int serializeOnJackson(Channel object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field copyright (mapped with "copyright")
    if (object.getCopyright()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("copyright", object.getCopyright());
    }

    // field description (mapped with "description")
    if (object.getDescription()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.getDescription());
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.getId());

    // field image (mapped with "image")
    if (object.getImage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("image");
      imageBindMap.serializeOnJackson(object.getImage(), jacksonSerializer);
    }

    // field language (mapped with "language")
    if (object.getLanguage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("language", object.getLanguage());
    }

    // field lastBuildDate (mapped with "lastBuildDate")
    if (object.getLastBuildDate()!=null)  {
      fieldCount++;
      // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
      jacksonSerializer.writeStringField("lastBuildDate", TypeAdapterUtils.toData(DateAdapter.class, object.getLastBuildDate()));
    }

    // field link (mapped with "link")
    if (object.getLink()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", object.getLink());
    }

    // field pubDate (mapped with "pubDate")
    if (object.getPubDate()!=null)  {
      fieldCount++;
      // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
      jacksonSerializer.writeStringField("pubDate", TypeAdapterUtils.toData(DateAdapter.class, object.getPubDate()));
    }

    // field rssFeedId (mapped with "rssFeedId")
    fieldCount++;
    jacksonSerializer.writeNumberField("rssFeedId", object.getRssFeedId());

    // field title (mapped with "title")
    if (object.getTitle()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.getTitle());
    }

    // field articles (mapped with "item")
    if (object.getArticles()!=null)  {
      fieldCount++;
      int n=object.getArticles().size();
      Article item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getArticles().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          articleBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Channel object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field copyright (mapped with "copyright")
    if (object.getCopyright()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("copyright", object.getCopyright());
    }

    // field description (mapped with "description")
    if (object.getDescription()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.getDescription());
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

    // field image (mapped with "image")
    if (object.getImage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("image");
      if (imageBindMap.serializeOnJacksonAsString(object.getImage(), jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("image");
      }
    }

    // field language (mapped with "language")
    if (object.getLanguage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("language", object.getLanguage());
    }

    // field lastBuildDate (mapped with "lastBuildDate")
    if (object.getLastBuildDate()!=null)  {
      fieldCount++;
      // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
      jacksonSerializer.writeStringField("lastBuildDate", TypeAdapterUtils.toData(DateAdapter.class, object.getLastBuildDate()));
    }

    // field link (mapped with "link")
    if (object.getLink()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", object.getLink());
    }

    // field pubDate (mapped with "pubDate")
    if (object.getPubDate()!=null)  {
      fieldCount++;
      // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
      jacksonSerializer.writeStringField("pubDate", TypeAdapterUtils.toData(DateAdapter.class, object.getPubDate()));
    }

    // field rssFeedId (mapped with "rssFeedId")
    jacksonSerializer.writeStringField("rssFeedId", PrimitiveUtils.writeLong(object.getRssFeedId()));

    // field title (mapped with "title")
    if (object.getTitle()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.getTitle());
    }

    // field articles (mapped with "item")
    if (object.getArticles()!=null)  {
      fieldCount++;
      int n=object.getArticles().size();
      Article item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getArticles().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (articleBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("item");
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
  public void serializeOnXml(Channel object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("channel");
    }

    // Persisted fields:

    // field copyright (mapped with "copyright")
    if (object.getCopyright()!=null) {
      xmlSerializer.writeStartElement("copyright");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getCopyright()));
      xmlSerializer.writeEndElement();
    }

    // field description (mapped with "description")
    if (object.getDescription()!=null) {
      xmlSerializer.writeStartElement("description");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDescription()));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.getId());
    xmlSerializer.writeEndElement();

    // field image (mapped with "image")
    if (object.getImage()!=null)  {
      xmlSerializer.writeStartElement("image");
      imageBindMap.serializeOnXml(object.getImage(), xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field language (mapped with "language")
    if (object.getLanguage()!=null) {
      xmlSerializer.writeStartElement("language");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getLanguage()));
      xmlSerializer.writeEndElement();
    }

    // field lastBuildDate (mapped with "lastBuildDate")
    // field trasformation java.lang.String sqlite.feature.kotlin.immutable.DateAdapter 
    if (object.getLastBuildDate()!=null) {
      // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
      xmlSerializer.writeStartElement("lastBuildDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(DateAdapter.class, object.getLastBuildDate())));
      xmlSerializer.writeEndElement();
    }

    // field link (mapped with "link")
    if (object.getLink()!=null) {
      xmlSerializer.writeStartElement("link");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getLink()));
      xmlSerializer.writeEndElement();
    }

    // field pubDate (mapped with "pubDate")
    // field trasformation java.lang.String sqlite.feature.kotlin.immutable.DateAdapter 
    if (object.getPubDate()!=null) {
      // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
      xmlSerializer.writeStartElement("pubDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(DateAdapter.class, object.getPubDate())));
      xmlSerializer.writeEndElement();
    }

    // field rssFeedId (mapped with "rssFeedId")
    xmlSerializer.writeStartElement("rssFeedId");
    xmlSerializer.writeLong(object.getRssFeedId());
    xmlSerializer.writeEndElement();

    // field title (mapped with "title")
    if (object.getTitle()!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getTitle()));
      xmlSerializer.writeEndElement();
    }

    // field articles (mapped with "item")
    if (object.getArticles()!=null)  {
      int n=object.getArticles().size();
      Article item;
      for (int i=0; i<n; i++) {
        item=object.getArticles().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("item");
        } else {
          xmlSerializer.writeStartElement("item");
          articleBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("item");
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
  public Channel parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __title=null;
    String __link=null;
    String __description=null;
    String __language=null;
    String __copyright=null;
    Date __pubDate=null;
    Date __lastBuildDate=null;
    Image __image=null;
    long __rssFeedId=0;
    List<Article> __articles=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Channel instance=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "copyright":
            // field copyright (mapped with "copyright")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __copyright=jacksonParser.getText();
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __description=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            __id=jacksonParser.getLongValue();
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              __image=imageBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "language":
            // field language (mapped with "language")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __language=jacksonParser.getText();
            }
          break;
          case "lastBuildDate":
            // field lastBuildDate (mapped with "lastBuildDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
              __lastBuildDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __link=jacksonParser.getText();
            }
          break;
          case "pubDate":
            // field pubDate (mapped with "pubDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
              __pubDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "rssFeedId":
            // field rssFeedId (mapped with "rssFeedId")
            __rssFeedId=jacksonParser.getLongValue();
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __title=jacksonParser.getText();
            }
          break;
          case "item":
            // field articles (mapped with "item")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Article> collection=new ArrayList<>();
              Article item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=articleBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              __articles=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Channel instance=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Channel parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __title=null;
    String __link=null;
    String __description=null;
    String __language=null;
    String __copyright=null;
    Date __pubDate=null;
    Date __lastBuildDate=null;
    Image __image=null;
    long __rssFeedId=0;
    List<Article> __articles=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Channel instance=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "copyright":
            // field copyright (mapped with "copyright")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __copyright=jacksonParser.getText();
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __description=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            __id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              __image=imageBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "language":
            // field language (mapped with "language")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __language=jacksonParser.getText();
            }
          break;
          case "lastBuildDate":
            // field lastBuildDate (mapped with "lastBuildDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
              __lastBuildDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __link=jacksonParser.getText();
            }
          break;
          case "pubDate":
            // field pubDate (mapped with "pubDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
              __pubDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "rssFeedId":
            // field rssFeedId (mapped with "rssFeedId")
            __rssFeedId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __title=jacksonParser.getText();
            }
          break;
          case "item":
            // field articles (mapped with "item")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Article> collection=new ArrayList<>();
              Article item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=articleBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              __articles=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Article> collection=new ArrayList<>();
              __articles=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Channel instance=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Channel parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __id=0;
    String __title=null;
    String __link=null;
    String __description=null;
    String __language=null;
    String __copyright=null;
    Date __pubDate=null;
    Date __lastBuildDate=null;
    Image __image=null;
    long __rssFeedId=0;
    List<Article> __articles=null;
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
                case "copyright":
                  // property copyright (mapped on "copyright")
                  __copyright=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "description":
                  // property description (mapped on "description")
                  __description=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  __id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "image":
                  // property image (mapped on "image")
                  __image=imageBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "language":
                  // property language (mapped on "language")
                  __language=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "lastBuildDate":
                  // property lastBuildDate (mapped on "lastBuildDate")
                  // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
                  __lastBuildDate=TypeAdapterUtils.toJava(DateAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "link":
                  // property link (mapped on "link")
                  __link=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "pubDate":
                  // property pubDate (mapped on "pubDate")
                  // using type adapter sqlite.feature.kotlin.immutable.DateAdapter
                  __pubDate=TypeAdapterUtils.toJava(DateAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "rssFeedId":
                  // property rssFeedId (mapped on "rssFeedId")
                  __rssFeedId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "title":
                  // property title (mapped on "title")
                  __title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "item":
                  // property articles (mapped on "item")
                   {
                    ArrayList<Article> collection=CollectionUtils.merge(new ArrayList<>(), __articles);
                    Article item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=articleBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("item")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=articleBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    __articles=collection;
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
      Channel instance=new Channel(__id,__title,__link,__description,__language,__copyright,__pubDate,__lastBuildDate,__image,__rssFeedId,(__articles==null ? null : Collections.unmodifiableList(__articles)));
      return instance;
    }
  }
