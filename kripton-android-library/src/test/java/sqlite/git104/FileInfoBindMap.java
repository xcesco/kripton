package sqlite.git104;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for FileInfo
 *
 * @see FileInfo
 */
@BindMap(FileInfo.class)
public class FileInfoBindMap extends AbstractMapper<FileInfo> {
  /**
   * binder for type DocumentInfo
   */
  private DocumentInfoBindMap documentInfoBindMap;

  @Override
  public int serializeOnJackson(FileInfo object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field fileName (mapped with "fileName")
    if (object.getFileName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("fileName", object.getFileName());
    }

    // field info (mapped with "info")
    if (object.getInfo()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("info");
      documentInfoBindMap.serializeOnJackson(object.getInfo(), jacksonSerializer);
    }

    // field secret (mapped with "secret")
    if (object.getSecret()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("secret", object.getSecret());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(FileInfo object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field fileName (mapped with "fileName")
    if (object.getFileName()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("fileName", object.getFileName());
    }

    // field info (mapped with "info")
    if (object.getInfo()!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("info");
      if (documentInfoBindMap.serializeOnJacksonAsString(object.getInfo(), jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("info");
      }
    }

    // field secret (mapped with "secret")
    if (object.getSecret()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("secret", object.getSecret());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(FileInfo object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("fileInfo");
    }

    // Persisted fields:

    // field fileName (mapped with "fileName")
    if (object.getFileName()!=null) {
      xmlSerializer.writeStartElement("fileName");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getFileName()));
      xmlSerializer.writeEndElement();
    }

    // field info (mapped with "info")
    if (object.getInfo()!=null)  {
      xmlSerializer.writeStartElement("info");
      documentInfoBindMap.serializeOnXml(object.getInfo(), xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    // field secret (mapped with "secret")
    if (object.getSecret()!=null) {
      xmlSerializer.writeStartElement("secret");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getSecret()));
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
  public FileInfo parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __fileName=null;
    String __secret=null;
    DocumentInfo __info=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      FileInfo instance=new FileInfo(__fileName,__secret,__info);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "fileName":
            // field fileName (mapped with "fileName")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __fileName=jacksonParser.getText();
            }
          break;
          case "info":
            // field info (mapped with "info")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              __info=documentInfoBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "secret":
            // field secret (mapped with "secret")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __secret=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    FileInfo instance=new FileInfo(__fileName,__secret,__info);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public FileInfo parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __fileName=null;
    String __secret=null;
    DocumentInfo __info=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      FileInfo instance=new FileInfo(__fileName,__secret,__info);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "fileName":
            // field fileName (mapped with "fileName")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __fileName=jacksonParser.getText();
            }
          break;
          case "info":
            // field info (mapped with "info")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              __info=documentInfoBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "secret":
            // field secret (mapped with "secret")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __secret=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    FileInfo instance=new FileInfo(__fileName,__secret,__info);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public FileInfo parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __fileName=null;
    String __secret=null;
    DocumentInfo __info=null;
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
                case "fileName":
                  // property fileName (mapped on "fileName")
                  __fileName=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "info":
                  // property info (mapped on "info")
                  __info=documentInfoBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "secret":
                  // property secret (mapped on "secret")
                  __secret=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
          FileInfo instance=new FileInfo(__fileName,__secret,__info);
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
        documentInfoBindMap=BinderUtils.mapperFor(DocumentInfo.class);
      }
    }
