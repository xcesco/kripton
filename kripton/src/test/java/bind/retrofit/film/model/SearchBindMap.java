package bind.retrofit.film.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
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
import java.util.Collections;
import java.util.List;

/**
 * This class is binder map for Search
 *
 * @see Search
 */
@BindMap(Search.class)
public class SearchBindMap extends AbstractMapper<Search> {
  /**
   * binder for type Film
   */
  private FilmBindMap filmBindMap;

  @Override
  public int serializeOnJackson(Search object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field response (mapped with "response")
    if (object.getResponse()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("response", object.getResponse());
    }

    // field search (mapped with "search")
    if (object.getSearch()!=null)  {
      fieldCount++;
      int n=object.getSearch().size();
      Film item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("search");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getSearch().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          filmBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field totalResults (mapped with "totalResults")
    if (object.getTotalResults()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("totalResults", object.getTotalResults());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Search object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field response (mapped with "response")
    if (object.getResponse()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("response", object.getResponse());
    }

    // field search (mapped with "search")
    if (object.getSearch()!=null)  {
      fieldCount++;
      int n=object.getSearch().size();
      Film item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("search");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getSearch().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (filmBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("search");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field totalResults (mapped with "totalResults")
    if (object.getTotalResults()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("totalResults", object.getTotalResults());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Search object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("search");
    }

    // Persisted fields:

    // field response (mapped with "response")
    if (object.getResponse()!=null) {
      xmlSerializer.writeStartElement("response");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getResponse()));
      xmlSerializer.writeEndElement();
    }

    // field search (mapped with "search")
    if (object.getSearch()!=null)  {
      int n=object.getSearch().size();
      Film item;
      for (int i=0; i<n; i++) {
        item=object.getSearch().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("search");
        } else {
          xmlSerializer.writeStartElement("search");
          filmBindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("search");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field totalResults (mapped with "totalResults")
    if (object.getTotalResults()!=null) {
      xmlSerializer.writeStartElement("totalResults");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getTotalResults()));
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
  public Search parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __response=null;
    List<Film> __search=null;
    String __totalResults=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Search instance=new Search(__response,(__search==null ? null : Collections.unmodifiableList(__search)),__totalResults);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "response":
            // field response (mapped with "response")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __response=jacksonParser.getText();
            }
          break;
          case "search":
            // field search (mapped with "search")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Film> collection=new ArrayList<>();
              Film item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=filmBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              __search=collection;
            }
          break;
          case "totalResults":
            // field totalResults (mapped with "totalResults")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __totalResults=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Search instance=new Search(__response,(__search==null ? null : Collections.unmodifiableList(__search)),__totalResults);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Search parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __response=null;
    List<Film> __search=null;
    String __totalResults=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Search instance=new Search(__response,(__search==null ? null : Collections.unmodifiableList(__search)),__totalResults);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "response":
            // field response (mapped with "response")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __response=jacksonParser.getText();
            }
          break;
          case "search":
            // field search (mapped with "search")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Film> collection=new ArrayList<>();
              Film item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=filmBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              __search=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Film> collection=new ArrayList<>();
              __search=collection;
            }
          break;
          case "totalResults":
            // field totalResults (mapped with "totalResults")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __totalResults=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Search instance=new Search(__response,(__search==null ? null : Collections.unmodifiableList(__search)),__totalResults);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Search parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __response=null;
    List<Film> __search=null;
    String __totalResults=null;
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
                case "response":
                  // property response (mapped on "response")
                  __response=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "search":
                  // property search (mapped on "search")
                   {
                    ArrayList<Film> collection=CollectionUtils.merge(new ArrayList<>(), __search);
                    Film item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=filmBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("search")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=filmBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    __search=collection;
                    read=false;
                  }
                break;
                case "totalResults":
                  // property totalResults (mapped on "totalResults")
                  __totalResults=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
          // immutable object: inizialize object
          Search instance=new Search(__response,(__search==null ? null : Collections.unmodifiableList(__search)),__totalResults);
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
        filmBindMap=BinderUtils.mapperFor(Film.class);
      }
    }
