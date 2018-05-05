package bind.rss;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
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
    if (object.copyright!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("copyright", object.copyright);
    }

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field image (mapped with "image")
    if (object.image!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("image");
      imageBindMap.serializeOnJackson(object.image, jacksonSerializer);
    }

    // field language (mapped with "language")
    if (object.language!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("language", object.language);
    }

    // field lastBuildDate (mapped with "lastBuildDate")
    if (object.lastBuildDate!=null)  {
      fieldCount++;
      // using type adapter bind.rss.DateAdapter
      jacksonSerializer.writeStringField("lastBuildDate", TypeAdapterUtils.toData(DateAdapter.class, object.lastBuildDate));
    }

    // field link (mapped with "link")
    if (object.link!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", object.link);
    }

    // field pubDate (mapped with "pubDate")
    if (object.pubDate!=null)  {
      fieldCount++;
      // using type adapter bind.rss.DateAdapter
      jacksonSerializer.writeStringField("pubDate", TypeAdapterUtils.toData(DateAdapter.class, object.pubDate));
    }

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    // field articles (mapped with "item")
    if (object.articles!=null)  {
      fieldCount++;
      int n=object.articles.size();
      Article item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.articles.get(i);
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
    if (object.copyright!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("copyright", object.copyright);
    }

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field image (mapped with "image")
    if (object.image!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("image");
      if (imageBindMap.serializeOnJacksonAsString(object.image, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("image");
      }
    }

    // field language (mapped with "language")
    if (object.language!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("language", object.language);
    }

    // field lastBuildDate (mapped with "lastBuildDate")
    if (object.lastBuildDate!=null)  {
      fieldCount++;
      // using type adapter bind.rss.DateAdapter
      jacksonSerializer.writeStringField("lastBuildDate", TypeAdapterUtils.toData(DateAdapter.class, object.lastBuildDate));
    }

    // field link (mapped with "link")
    if (object.link!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("link", object.link);
    }

    // field pubDate (mapped with "pubDate")
    if (object.pubDate!=null)  {
      fieldCount++;
      // using type adapter bind.rss.DateAdapter
      jacksonSerializer.writeStringField("pubDate", TypeAdapterUtils.toData(DateAdapter.class, object.pubDate));
    }

    // field title (mapped with "title")
    if (object.title!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.title);
    }

    // field articles (mapped with "item")
    if (object.articles!=null)  {
      fieldCount++;
      int n=object.articles.size();
      Article item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("item");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.articles.get(i);
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
    if (object.copyright!=null) {
      xmlSerializer.writeStartElement("copyright");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.copyright));
      xmlSerializer.writeEndElement();
    }

    // field description (mapped with "description")
    if (object.description!=null) {
      xmlSerializer.writeStartElement("description");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.description));
      xmlSerializer.writeEndElement();
    }

    // field image (mapped with "image")
    if (object.image!=null)  {
      xmlSerializer.writeStartElement("image");
      imageBindMap.serializeOnXml(object.image, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field language (mapped with "language")
    if (object.language!=null) {
      xmlSerializer.writeStartElement("language");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.language));
      xmlSerializer.writeEndElement();
    }

    // field lastBuildDate (mapped with "lastBuildDate")
    // field trasformation java.lang.String bind.rss.DateAdapter 
    if (object.lastBuildDate!=null) {
      // using type adapter bind.rss.DateAdapter
      xmlSerializer.writeStartElement("lastBuildDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(DateAdapter.class, object.lastBuildDate)));
      xmlSerializer.writeEndElement();
    }

    // field link (mapped with "link")
    if (object.link!=null) {
      xmlSerializer.writeStartElement("link");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.link));
      xmlSerializer.writeEndElement();
    }

    // field pubDate (mapped with "pubDate")
    // field trasformation java.lang.String bind.rss.DateAdapter 
    if (object.pubDate!=null) {
      // using type adapter bind.rss.DateAdapter
      xmlSerializer.writeStartElement("pubDate");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(DateAdapter.class, object.pubDate)));
      xmlSerializer.writeEndElement();
    }

    // field title (mapped with "title")
    if (object.title!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.title));
      xmlSerializer.writeEndElement();
    }

    // field articles (mapped with "item")
    if (object.articles!=null)  {
      int n=object.articles.size();
      Article item;
      for (int i=0; i<n; i++) {
        item=object.articles.get(i);
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
    Channel instance = new Channel();
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
          case "copyright":
            // field copyright (mapped with "copyright")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.copyright=jacksonParser.getText();
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.image=imageBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "language":
            // field language (mapped with "language")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.language=jacksonParser.getText();
            }
          break;
          case "lastBuildDate":
            // field lastBuildDate (mapped with "lastBuildDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.rss.DateAdapter
              instance.lastBuildDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.link=jacksonParser.getText();
            }
          break;
          case "pubDate":
            // field pubDate (mapped with "pubDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.rss.DateAdapter
              instance.pubDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
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
              instance.articles=collection;
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
  public Channel parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Channel instance = new Channel();
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
          case "copyright":
            // field copyright (mapped with "copyright")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.copyright=jacksonParser.getText();
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "image":
            // field image (mapped with "image")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.image=imageBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "language":
            // field language (mapped with "language")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.language=jacksonParser.getText();
            }
          break;
          case "lastBuildDate":
            // field lastBuildDate (mapped with "lastBuildDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.rss.DateAdapter
              instance.lastBuildDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "link":
            // field link (mapped with "link")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.link=jacksonParser.getText();
            }
          break;
          case "pubDate":
            // field pubDate (mapped with "pubDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.rss.DateAdapter
              instance.pubDate=TypeAdapterUtils.toJava(DateAdapter.class, jacksonParser.getText());
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.title=jacksonParser.getText();
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
              instance.articles=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Article> collection=new ArrayList<>();
              instance.articles=collection;
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
  public Channel parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Channel instance = new Channel();
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
                  instance.copyright=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "description":
                  // property description (mapped on "description")
                  instance.description=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "image":
                  // property image (mapped on "image")
                  instance.image=imageBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "language":
                  // property language (mapped on "language")
                  instance.language=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "lastBuildDate":
                  // property lastBuildDate (mapped on "lastBuildDate")
                  // using type adapter bind.rss.DateAdapter
                  instance.lastBuildDate=TypeAdapterUtils.toJava(DateAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "link":
                  // property link (mapped on "link")
                  instance.link=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "pubDate":
                  // property pubDate (mapped on "pubDate")
                  // using type adapter bind.rss.DateAdapter
                  instance.pubDate=TypeAdapterUtils.toJava(DateAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "title":
                  // property title (mapped on "title")
                  instance.title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "item":
                  // property articles (mapped on "item")
                   {
                    ArrayList<Article> collection=new ArrayList<>();
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
                    instance.articles=collection;
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
      return instance;
    }
  }
