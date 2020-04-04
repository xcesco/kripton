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
 * This class is binder map for FilmDetail
 *
 * @see FilmDetail
 */
@BindMap(FilmDetail.class)
public class FilmDetailBindMap extends AbstractMapper<FilmDetail> {
  /**
   * RatingBindMap */
  private RatingBindMap ratingBindMap = BinderUtils.mapperFor(Rating.class);

  @Override
  public int serializeOnJackson(FilmDetail object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field actors (mapped with "actors")
    if (object.getActors()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("actors", object.getActors());
    }

    // field awards (mapped with "awards")
    if (object.getAwards()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("awards", object.getAwards());
    }

    // field boxOffice (mapped with "boxOffice")
    if (object.getBoxOffice()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("boxOffice", object.getBoxOffice());
    }

    // field country (mapped with "country")
    if (object.getCountry()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("country", object.getCountry());
    }

    // field dVD (mapped with "dVD")
    if (object.getDVD()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dVD", object.getDVD());
    }

    // field director (mapped with "director")
    if (object.getDirector()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("director", object.getDirector());
    }

    // field genre (mapped with "genre")
    if (object.getGenre()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("genre", object.getGenre());
    }

    // field imdbID (mapped with "imdbID")
    if (object.getImdbID()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbID", object.getImdbID());
    }

    // field imdbRating (mapped with "imdbRating")
    if (object.getImdbRating()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbRating", object.getImdbRating());
    }

    // field imdbVotes (mapped with "imdbVotes")
    if (object.getImdbVotes()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbVotes", object.getImdbVotes());
    }

    // field language (mapped with "language")
    if (object.getLanguage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("language", object.getLanguage());
    }

    // field metascore (mapped with "metascore")
    if (object.getMetascore()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("metascore", object.getMetascore());
    }

    // field plot (mapped with "plot")
    if (object.getPlot()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("plot", object.getPlot());
    }

    // field poster (mapped with "poster")
    if (object.getPoster()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("poster", object.getPoster());
    }

    // field production (mapped with "production")
    if (object.getProduction()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("production", object.getProduction());
    }

    // field rated (mapped with "rated")
    if (object.getRated()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("rated", object.getRated());
    }

    // field ratings (mapped with "ratings")
    if (object.getRatings()!=null)  {
      fieldCount++;
      int n=object.getRatings().size();
      Rating item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("ratings");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.getRatings().get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          ratingBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field released (mapped with "released")
    if (object.getReleased()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("released", object.getReleased());
    }

    // field response (mapped with "response")
    if (object.getResponse()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("response", object.getResponse());
    }

    // field runtime (mapped with "runtime")
    if (object.getRuntime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("runtime", object.getRuntime());
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

    // field website (mapped with "website")
    if (object.getWebsite()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("website", object.getWebsite());
    }

    // field writer (mapped with "writer")
    if (object.getWriter()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("writer", object.getWriter());
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
  public int serializeOnJacksonAsString(FilmDetail object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field actors (mapped with "actors")
    if (object.getActors()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("actors", object.getActors());
    }

    // field awards (mapped with "awards")
    if (object.getAwards()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("awards", object.getAwards());
    }

    // field boxOffice (mapped with "boxOffice")
    if (object.getBoxOffice()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("boxOffice", object.getBoxOffice());
    }

    // field country (mapped with "country")
    if (object.getCountry()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("country", object.getCountry());
    }

    // field dVD (mapped with "dVD")
    if (object.getDVD()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dVD", object.getDVD());
    }

    // field director (mapped with "director")
    if (object.getDirector()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("director", object.getDirector());
    }

    // field genre (mapped with "genre")
    if (object.getGenre()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("genre", object.getGenre());
    }

    // field imdbID (mapped with "imdbID")
    if (object.getImdbID()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbID", object.getImdbID());
    }

    // field imdbRating (mapped with "imdbRating")
    if (object.getImdbRating()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbRating", object.getImdbRating());
    }

    // field imdbVotes (mapped with "imdbVotes")
    if (object.getImdbVotes()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("imdbVotes", object.getImdbVotes());
    }

    // field language (mapped with "language")
    if (object.getLanguage()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("language", object.getLanguage());
    }

    // field metascore (mapped with "metascore")
    if (object.getMetascore()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("metascore", object.getMetascore());
    }

    // field plot (mapped with "plot")
    if (object.getPlot()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("plot", object.getPlot());
    }

    // field poster (mapped with "poster")
    if (object.getPoster()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("poster", object.getPoster());
    }

    // field production (mapped with "production")
    if (object.getProduction()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("production", object.getProduction());
    }

    // field rated (mapped with "rated")
    if (object.getRated()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("rated", object.getRated());
    }

    // field ratings (mapped with "ratings")
    if (object.getRatings()!=null)  {
      fieldCount++;
      int n=object.getRatings().size();
      Rating item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("ratings");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getRatings().get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (ratingBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("ratings");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field released (mapped with "released")
    if (object.getReleased()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("released", object.getReleased());
    }

    // field response (mapped with "response")
    if (object.getResponse()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("response", object.getResponse());
    }

    // field runtime (mapped with "runtime")
    if (object.getRuntime()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("runtime", object.getRuntime());
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

    // field website (mapped with "website")
    if (object.getWebsite()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("website", object.getWebsite());
    }

    // field writer (mapped with "writer")
    if (object.getWriter()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("writer", object.getWriter());
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
  public void serializeOnXml(FilmDetail object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("filmDetail");
    }

    // Persisted fields:

    // field actors (mapped with "actors")
    if (object.getActors()!=null) {
      xmlSerializer.writeStartElement("actors");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getActors()));
      xmlSerializer.writeEndElement();
    }

    // field awards (mapped with "awards")
    if (object.getAwards()!=null) {
      xmlSerializer.writeStartElement("awards");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getAwards()));
      xmlSerializer.writeEndElement();
    }

    // field boxOffice (mapped with "boxOffice")
    if (object.getBoxOffice()!=null) {
      xmlSerializer.writeStartElement("boxOffice");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getBoxOffice()));
      xmlSerializer.writeEndElement();
    }

    // field country (mapped with "country")
    if (object.getCountry()!=null) {
      xmlSerializer.writeStartElement("country");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getCountry()));
      xmlSerializer.writeEndElement();
    }

    // field dVD (mapped with "dVD")
    if (object.getDVD()!=null) {
      xmlSerializer.writeStartElement("dVD");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDVD()));
      xmlSerializer.writeEndElement();
    }

    // field director (mapped with "director")
    if (object.getDirector()!=null) {
      xmlSerializer.writeStartElement("director");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDirector()));
      xmlSerializer.writeEndElement();
    }

    // field genre (mapped with "genre")
    if (object.getGenre()!=null) {
      xmlSerializer.writeStartElement("genre");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getGenre()));
      xmlSerializer.writeEndElement();
    }

    // field imdbID (mapped with "imdbID")
    if (object.getImdbID()!=null) {
      xmlSerializer.writeStartElement("imdbID");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getImdbID()));
      xmlSerializer.writeEndElement();
    }

    // field imdbRating (mapped with "imdbRating")
    if (object.getImdbRating()!=null) {
      xmlSerializer.writeStartElement("imdbRating");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getImdbRating()));
      xmlSerializer.writeEndElement();
    }

    // field imdbVotes (mapped with "imdbVotes")
    if (object.getImdbVotes()!=null) {
      xmlSerializer.writeStartElement("imdbVotes");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getImdbVotes()));
      xmlSerializer.writeEndElement();
    }

    // field language (mapped with "language")
    if (object.getLanguage()!=null) {
      xmlSerializer.writeStartElement("language");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getLanguage()));
      xmlSerializer.writeEndElement();
    }

    // field metascore (mapped with "metascore")
    if (object.getMetascore()!=null) {
      xmlSerializer.writeStartElement("metascore");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getMetascore()));
      xmlSerializer.writeEndElement();
    }

    // field plot (mapped with "plot")
    if (object.getPlot()!=null) {
      xmlSerializer.writeStartElement("plot");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getPlot()));
      xmlSerializer.writeEndElement();
    }

    // field poster (mapped with "poster")
    if (object.getPoster()!=null) {
      xmlSerializer.writeStartElement("poster");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getPoster()));
      xmlSerializer.writeEndElement();
    }

    // field production (mapped with "production")
    if (object.getProduction()!=null) {
      xmlSerializer.writeStartElement("production");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getProduction()));
      xmlSerializer.writeEndElement();
    }

    // field rated (mapped with "rated")
    if (object.getRated()!=null) {
      xmlSerializer.writeStartElement("rated");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getRated()));
      xmlSerializer.writeEndElement();
    }

    // field ratings (mapped with "ratings")
    if (object.getRatings()!=null)  {
      int n=object.getRatings().size();
      Rating item;
      for (int i=0; i<n; i++) {
        item=object.getRatings().get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("ratings");
        } else {
          xmlSerializer.writeStartElement("ratings");
          ratingBindMap.serializeOnXml(item, xmlSerializer, EventType.START_TAG);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("ratings");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field released (mapped with "released")
    if (object.getReleased()!=null) {
      xmlSerializer.writeStartElement("released");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getReleased()));
      xmlSerializer.writeEndElement();
    }

    // field response (mapped with "response")
    if (object.getResponse()!=null) {
      xmlSerializer.writeStartElement("response");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getResponse()));
      xmlSerializer.writeEndElement();
    }

    // field runtime (mapped with "runtime")
    if (object.getRuntime()!=null) {
      xmlSerializer.writeStartElement("runtime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getRuntime()));
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

    // field website (mapped with "website")
    if (object.getWebsite()!=null) {
      xmlSerializer.writeStartElement("website");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getWebsite()));
      xmlSerializer.writeEndElement();
    }

    // field writer (mapped with "writer")
    if (object.getWriter()!=null) {
      xmlSerializer.writeStartElement("writer");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getWriter()));
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
  public FilmDetail parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __title=null;
    String __year=null;
    String __rated=null;
    String __released=null;
    String __runtime=null;
    String __genre=null;
    String __director=null;
    String __writer=null;
    String __actors=null;
    String __plot=null;
    String __language=null;
    String __country=null;
    String __awards=null;
    String __poster=null;
    List<Rating> __ratings=null;
    String __metascore=null;
    String __imdbRating=null;
    String __imdbVotes=null;
    String __imdbID=null;
    String __type=null;
    String __dVD=null;
    String __boxOffice=null;
    String __production=null;
    String __website=null;
    String __response=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      FilmDetail instance=new FilmDetail(__title,__year,__rated,__released,__runtime,__genre,__director,__writer,__actors,__plot,__language,__country,__awards,__poster,(__ratings==null ? null : Collections.unmodifiableList(__ratings)),__metascore,__imdbRating,__imdbVotes,__imdbID,__type,__dVD,__boxOffice,__production,__website,__response);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "actors":
            // field actors (mapped with "actors")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __actors=jacksonParser.getText();
            }
          break;
          case "awards":
            // field awards (mapped with "awards")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __awards=jacksonParser.getText();
            }
          break;
          case "boxOffice":
            // field boxOffice (mapped with "boxOffice")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __boxOffice=jacksonParser.getText();
            }
          break;
          case "country":
            // field country (mapped with "country")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __country=jacksonParser.getText();
            }
          break;
          case "dVD":
            // field dVD (mapped with "dVD")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __dVD=jacksonParser.getText();
            }
          break;
          case "director":
            // field director (mapped with "director")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __director=jacksonParser.getText();
            }
          break;
          case "genre":
            // field genre (mapped with "genre")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __genre=jacksonParser.getText();
            }
          break;
          case "imdbID":
            // field imdbID (mapped with "imdbID")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbID=jacksonParser.getText();
            }
          break;
          case "imdbRating":
            // field imdbRating (mapped with "imdbRating")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbRating=jacksonParser.getText();
            }
          break;
          case "imdbVotes":
            // field imdbVotes (mapped with "imdbVotes")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbVotes=jacksonParser.getText();
            }
          break;
          case "language":
            // field language (mapped with "language")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __language=jacksonParser.getText();
            }
          break;
          case "metascore":
            // field metascore (mapped with "metascore")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __metascore=jacksonParser.getText();
            }
          break;
          case "plot":
            // field plot (mapped with "plot")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __plot=jacksonParser.getText();
            }
          break;
          case "poster":
            // field poster (mapped with "poster")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __poster=jacksonParser.getText();
            }
          break;
          case "production":
            // field production (mapped with "production")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __production=jacksonParser.getText();
            }
          break;
          case "rated":
            // field rated (mapped with "rated")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __rated=jacksonParser.getText();
            }
          break;
          case "ratings":
            // field ratings (mapped with "ratings")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Rating> collection=new ArrayList<>();
              Rating item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=ratingBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              __ratings=collection;
            }
          break;
          case "released":
            // field released (mapped with "released")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __released=jacksonParser.getText();
            }
          break;
          case "response":
            // field response (mapped with "response")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __response=jacksonParser.getText();
            }
          break;
          case "runtime":
            // field runtime (mapped with "runtime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __runtime=jacksonParser.getText();
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
          case "website":
            // field website (mapped with "website")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __website=jacksonParser.getText();
            }
          break;
          case "writer":
            // field writer (mapped with "writer")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __writer=jacksonParser.getText();
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
    FilmDetail instance=new FilmDetail(__title,__year,__rated,__released,__runtime,__genre,__director,__writer,__actors,__plot,__language,__country,__awards,__poster,(__ratings==null ? null : Collections.unmodifiableList(__ratings)),__metascore,__imdbRating,__imdbVotes,__imdbID,__type,__dVD,__boxOffice,__production,__website,__response);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public FilmDetail parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __title=null;
    String __year=null;
    String __rated=null;
    String __released=null;
    String __runtime=null;
    String __genre=null;
    String __director=null;
    String __writer=null;
    String __actors=null;
    String __plot=null;
    String __language=null;
    String __country=null;
    String __awards=null;
    String __poster=null;
    List<Rating> __ratings=null;
    String __metascore=null;
    String __imdbRating=null;
    String __imdbVotes=null;
    String __imdbID=null;
    String __type=null;
    String __dVD=null;
    String __boxOffice=null;
    String __production=null;
    String __website=null;
    String __response=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      FilmDetail instance=new FilmDetail(__title,__year,__rated,__released,__runtime,__genre,__director,__writer,__actors,__plot,__language,__country,__awards,__poster,(__ratings==null ? null : Collections.unmodifiableList(__ratings)),__metascore,__imdbRating,__imdbVotes,__imdbID,__type,__dVD,__boxOffice,__production,__website,__response);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "actors":
            // field actors (mapped with "actors")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __actors=jacksonParser.getText();
            }
          break;
          case "awards":
            // field awards (mapped with "awards")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __awards=jacksonParser.getText();
            }
          break;
          case "boxOffice":
            // field boxOffice (mapped with "boxOffice")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __boxOffice=jacksonParser.getText();
            }
          break;
          case "country":
            // field country (mapped with "country")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __country=jacksonParser.getText();
            }
          break;
          case "dVD":
            // field dVD (mapped with "dVD")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __dVD=jacksonParser.getText();
            }
          break;
          case "director":
            // field director (mapped with "director")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __director=jacksonParser.getText();
            }
          break;
          case "genre":
            // field genre (mapped with "genre")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __genre=jacksonParser.getText();
            }
          break;
          case "imdbID":
            // field imdbID (mapped with "imdbID")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbID=jacksonParser.getText();
            }
          break;
          case "imdbRating":
            // field imdbRating (mapped with "imdbRating")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbRating=jacksonParser.getText();
            }
          break;
          case "imdbVotes":
            // field imdbVotes (mapped with "imdbVotes")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __imdbVotes=jacksonParser.getText();
            }
          break;
          case "language":
            // field language (mapped with "language")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __language=jacksonParser.getText();
            }
          break;
          case "metascore":
            // field metascore (mapped with "metascore")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __metascore=jacksonParser.getText();
            }
          break;
          case "plot":
            // field plot (mapped with "plot")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __plot=jacksonParser.getText();
            }
          break;
          case "poster":
            // field poster (mapped with "poster")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __poster=jacksonParser.getText();
            }
          break;
          case "production":
            // field production (mapped with "production")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __production=jacksonParser.getText();
            }
          break;
          case "rated":
            // field rated (mapped with "rated")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __rated=jacksonParser.getText();
            }
          break;
          case "ratings":
            // field ratings (mapped with "ratings")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Rating> collection=new ArrayList<>();
              Rating item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=ratingBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              __ratings=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Rating> collection=new ArrayList<>();
              __ratings=collection;
            }
          break;
          case "released":
            // field released (mapped with "released")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __released=jacksonParser.getText();
            }
          break;
          case "response":
            // field response (mapped with "response")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __response=jacksonParser.getText();
            }
          break;
          case "runtime":
            // field runtime (mapped with "runtime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __runtime=jacksonParser.getText();
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
          case "website":
            // field website (mapped with "website")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __website=jacksonParser.getText();
            }
          break;
          case "writer":
            // field writer (mapped with "writer")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __writer=jacksonParser.getText();
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
    FilmDetail instance=new FilmDetail(__title,__year,__rated,__released,__runtime,__genre,__director,__writer,__actors,__plot,__language,__country,__awards,__poster,(__ratings==null ? null : Collections.unmodifiableList(__ratings)),__metascore,__imdbRating,__imdbVotes,__imdbID,__type,__dVD,__boxOffice,__production,__website,__response);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public FilmDetail parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __title=null;
    String __year=null;
    String __rated=null;
    String __released=null;
    String __runtime=null;
    String __genre=null;
    String __director=null;
    String __writer=null;
    String __actors=null;
    String __plot=null;
    String __language=null;
    String __country=null;
    String __awards=null;
    String __poster=null;
    List<Rating> __ratings=null;
    String __metascore=null;
    String __imdbRating=null;
    String __imdbVotes=null;
    String __imdbID=null;
    String __type=null;
    String __dVD=null;
    String __boxOffice=null;
    String __production=null;
    String __website=null;
    String __response=null;
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
                case "actors":
                  // property actors (mapped on "actors")
                  __actors=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "awards":
                  // property awards (mapped on "awards")
                  __awards=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "boxOffice":
                  // property boxOffice (mapped on "boxOffice")
                  __boxOffice=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "country":
                  // property country (mapped on "country")
                  __country=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "dVD":
                  // property dVD (mapped on "dVD")
                  __dVD=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "director":
                  // property director (mapped on "director")
                  __director=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "genre":
                  // property genre (mapped on "genre")
                  __genre=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "imdbID":
                  // property imdbID (mapped on "imdbID")
                  __imdbID=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "imdbRating":
                  // property imdbRating (mapped on "imdbRating")
                  __imdbRating=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "imdbVotes":
                  // property imdbVotes (mapped on "imdbVotes")
                  __imdbVotes=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "language":
                  // property language (mapped on "language")
                  __language=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "metascore":
                  // property metascore (mapped on "metascore")
                  __metascore=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "plot":
                  // property plot (mapped on "plot")
                  __plot=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "poster":
                  // property poster (mapped on "poster")
                  __poster=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "production":
                  // property production (mapped on "production")
                  __production=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "rated":
                  // property rated (mapped on "rated")
                  __rated=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "ratings":
                  // property ratings (mapped on "ratings")
                   {
                    ArrayList<Rating> collection=CollectionUtils.merge(new ArrayList<>(), __ratings);
                    Rating item;
                    // add first element
                    item=null;
                    if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=ratingBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != EventType.END_TAG && xmlParser.getName().toString().equals("ratings")) {
                      if (XmlAttributeUtils.isEmptyTag(xmlParser)) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=ratingBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    __ratings=collection;
                    read=false;
                  }
                break;
                case "released":
                  // property released (mapped on "released")
                  __released=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "response":
                  // property response (mapped on "response")
                  __response=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "runtime":
                  // property runtime (mapped on "runtime")
                  __runtime=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "title":
                  // property title (mapped on "title")
                  __title=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "type":
                  // property type (mapped on "type")
                  __type=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "website":
                  // property website (mapped on "website")
                  __website=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "writer":
                  // property writer (mapped on "writer")
                  __writer=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
      FilmDetail instance=new FilmDetail(__title,__year,__rated,__released,__runtime,__genre,__director,__writer,__actors,__plot,__language,__country,__awards,__poster,(__ratings==null ? null : Collections.unmodifiableList(__ratings)),__metascore,__imdbRating,__imdbVotes,__imdbID,__type,__dVD,__boxOffice,__production,__website,__response);
      return instance;
    }
  }
