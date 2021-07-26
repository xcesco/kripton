package sqlite.git104;

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
 * This class is binder map for DocumentInfo
 *
 * @see DocumentInfo
 */
@BindMap(DocumentInfo.class)
public class DocumentInfoBindMap extends AbstractMapper<DocumentInfo> {
  @Override
  public int serializeOnJackson(DocumentInfo object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field bucket1 (mapped with "bucket1")
    if (object.getBucket1()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("bucket1", object.getBucket1());
    }

    // field bucket2 (mapped with "bucket2")
    if (object.getBucket2()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("bucket2", object.getBucket2());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(DocumentInfo object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field bucket1 (mapped with "bucket1")
    if (object.getBucket1()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("bucket1", object.getBucket1());
    }

    // field bucket2 (mapped with "bucket2")
    if (object.getBucket2()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("bucket2", object.getBucket2());
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(DocumentInfo object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("documentInfo");
    }

    // Persisted fields:

    // field bucket1 (mapped with "bucket1")
    if (object.getBucket1()!=null) {
      xmlSerializer.writeStartElement("bucket1");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getBucket1()));
      xmlSerializer.writeEndElement();
    }

    // field bucket2 (mapped with "bucket2")
    if (object.getBucket2()!=null) {
      xmlSerializer.writeStartElement("bucket2");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getBucket2()));
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
  public DocumentInfo parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __bucket1=null;
    String __bucket2=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      DocumentInfo instance=new DocumentInfo(__bucket1,__bucket2);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "bucket1":
            // field bucket1 (mapped with "bucket1")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __bucket1=jacksonParser.getText();
            }
          break;
          case "bucket2":
            // field bucket2 (mapped with "bucket2")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __bucket2=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    DocumentInfo instance=new DocumentInfo(__bucket1,__bucket2);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public DocumentInfo parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __bucket1=null;
    String __bucket2=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      DocumentInfo instance=new DocumentInfo(__bucket1,__bucket2);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "bucket1":
            // field bucket1 (mapped with "bucket1")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __bucket1=jacksonParser.getText();
            }
          break;
          case "bucket2":
            // field bucket2 (mapped with "bucket2")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __bucket2=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    DocumentInfo instance=new DocumentInfo(__bucket1,__bucket2);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public DocumentInfo parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    String __bucket1=null;
    String __bucket2=null;
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
                case "bucket1":
                  // property bucket1 (mapped on "bucket1")
                  __bucket1=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "bucket2":
                  // property bucket2 (mapped on "bucket2")
                  __bucket2=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
      DocumentInfo instance=new DocumentInfo(__bucket1,__bucket2);
      return instance;
    }

    @Override
    public void init() {
      // binding maps initialization 
    }
  }
