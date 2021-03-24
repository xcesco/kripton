package bind.git80;

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
 * This class is binder map for Errori
 *
 * @see Errori
 */
@BindMap(Errori.class)
public class ErroriBindMap extends AbstractMapper<Errori> {
  @Override
  public int serializeOnJackson(Errori object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field codice (mapped with "codice")
    if (object.getCodice()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("codice", object.getCodice());
    }

    // field descrizione (mapped with "descrizione")
    if (object.getDescrizione()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("descrizione", object.getDescrizione());
    }

    // field xmlns (mapped with "xmlns")
    if (object.getXmlns()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("xmlns", object.getXmlns());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Errori object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field codice (mapped with "codice")
    if (object.getCodice()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("codice", object.getCodice());
    }

    // field descrizione (mapped with "descrizione")
    if (object.getDescrizione()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("descrizione", object.getDescrizione());
    }

    // field xmlns (mapped with "xmlns")
    if (object.getXmlns()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("xmlns", object.getXmlns());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Errori object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("errori");
    }

    // Persisted fields:

    // field codice (mapped with "codice")
    if (object.getCodice()!=null) {
      xmlSerializer.writeStartElement("codice");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getCodice()));
      xmlSerializer.writeEndElement();
    }

    // field descrizione (mapped with "descrizione")
    if (object.getDescrizione()!=null) {
      xmlSerializer.writeStartElement("descrizione");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getDescrizione()));
      xmlSerializer.writeEndElement();
    }

    // field xmlns (mapped with "xmlns")
    if (object.getXmlns()!=null) {
      xmlSerializer.writeStartElement("xmlns");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getXmlns()));
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
  public Errori parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __xmlns=null;
    String __descrizione=null;
    String __codice=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Errori instance=new Errori(__xmlns,__descrizione,__codice);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "codice":
            // field codice (mapped with "codice")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __codice=jacksonParser.getText();
            }
          break;
          case "descrizione":
            // field descrizione (mapped with "descrizione")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __descrizione=jacksonParser.getText();
            }
          break;
          case "xmlns":
            // field xmlns (mapped with "xmlns")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __xmlns=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Errori instance=new Errori(__xmlns,__descrizione,__codice);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Errori parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __xmlns=null;
    String __descrizione=null;
    String __codice=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Errori instance=new Errori(__xmlns,__descrizione,__codice);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "codice":
            // field codice (mapped with "codice")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __codice=jacksonParser.getText();
            }
          break;
          case "descrizione":
            // field descrizione (mapped with "descrizione")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __descrizione=jacksonParser.getText();
            }
          break;
          case "xmlns":
            // field xmlns (mapped with "xmlns")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __xmlns=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Errori instance=new Errori(__xmlns,__descrizione,__codice);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Errori parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __xmlns=null;
    String __descrizione=null;
    String __codice=null;
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
                case "codice":
                  // property codice (mapped on "codice")
                  __codice=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "descrizione":
                  // property descrizione (mapped on "descrizione")
                  __descrizione=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "xmlns":
                  // property xmlns (mapped on "xmlns")
                  __xmlns=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
      Errori instance=new Errori(__xmlns,__descrizione,__codice);
      return instance;
    }

    @Override
    public void init() {
      // binding maps initialization 
    }
  }
