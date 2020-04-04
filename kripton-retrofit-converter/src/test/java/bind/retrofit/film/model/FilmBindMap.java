package bind.retrofit.film.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Film
 *
 * @see Film
 */
@BindMap(Film.class)
public class FilmBindMap extends AbstractMapper<Film> {
  @Override
  public int serializeOnJackson(Film object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field imdbID (mapped with "imdbID")
    if (object.getImdbID()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbID", object.getImdbID());
    }

    // field poster (mapped with "poster")
    if (object.getPoster()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("poster", object.getPoster());
    }

    // field title (mapped with "title")
    if (object.getTitle()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.getTitle());
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.getType());
    }

    // field year (mapped with "year")
    if (object.getYear()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("year", object.getYear());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Film object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field imdbID (mapped with "imdbID")
    if (object.getImdbID()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbID", object.getImdbID());
    }

    // field poster (mapped with "poster")
    if (object.getPoster()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("poster", object.getPoster());
    }

    // field title (mapped with "title")
    if (object.getTitle()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("title", object.getTitle());
    }

    // field type (mapped with "type")
    if (object.getType()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("type", object.getType());
    }

    // field year (mapped with "year")
    if (object.getYear()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("year", object.getYear());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Film object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("film");
    }

    // Persisted fields:

    // field imdbID (mapped with "imdbID")
    if (object.getImdbID()!=null) {
      xmlSerializer.writeStartElement("imdbID");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getImdbID()));
      xmlSerializer.writeEndElement();
    }

    // field poster (mapped with "poster")
    if (object.getPoster()!=null) {
      xmlSerializer.writeStartElement("poster");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getPoster()));
      xmlSerializer.writeEndElement();
    }

    // field title (mapped with "title")
    if (object.getTitle()!=null) {
      xmlSerializer.writeStartElement("title");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getTitle()));
      xmlSerializer.writeEndElement();
    }

    // field type (mapped with "type")
    if (object.getType()!=null) {
      xmlSerializer.writeStartElement("type");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getType()));
      xmlSerializer.writeEndElement();
    }

    // field year (mapped with "year")
    if (object.getYear()!=null) {
      xmlSerializer.writeStartElement("year");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getYear()));
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
  public Film parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __title=null;
    String __year=null;
    String __imdbID=null;
    String __type=null;
    String __poster=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Film instance=new Film(__title,__year,__imdbID,__type,__poster);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "imdbID":
            // field imdbID (mapped with "imdbID")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbID=jacksonParser.getText();
            }
          break;
          case "poster":
            // field poster (mapped with "poster")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __poster=jacksonParser.getText();
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __title=jacksonParser.getText();
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __type=jacksonParser.getText();
            }
          break;
          case "year":
            // field year (mapped with "year")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __year=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Film instance=new Film(__title,__year,__imdbID,__type,__poster);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Film parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __title=null;
    String __year=null;
    String __imdbID=null;
    String __type=null;
    String __poster=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Film instance=new Film(__title,__year,__imdbID,__type,__poster);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "imdbID":
            // field imdbID (mapped with "imdbID")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbID=jacksonParser.getText();
            }
          break;
          case "poster":
            // field poster (mapped with "poster")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __poster=jacksonParser.getText();
            }
          break;
          case "title":
            // field title (mapped with "title")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __title=jacksonParser.getText();
            }
          break;
          case "type":
            // field type (mapped with "type")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __type=jacksonParser.getText();
            }
          break;
          case "year":
            // field year (mapped with "year")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __year=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Film instance=new Film(__title,__year,__imdbID,__type,__poster);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Film parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __title=null;
    String __year=null;
    String __imdbID=null;
    String __type=null;
    String __poster=null;
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
                case "imdbID":
                  // property imdbID (mapped on "imdbID")
                  __imdbID=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "poster":
                  // property poster (mapped on "poster")
                  __poster=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "title":
                  // property title (mapped on "title")
                  __title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "type":
                  // property type (mapped on "type")
                  __type=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "year":
                  // property year (mapped on "year")
                  __year=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                default:
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
      Film instance=new Film(__title,__year,__imdbID,__type,__poster);
      return instance;
    }
  }
