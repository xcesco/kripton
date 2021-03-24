package sqlite.feature.schema.version2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Seminar2Student
 *
 * @see Seminar2Student
 */
@BindMap(Seminar2Student.class)
public class Seminar2StudentBindMap extends AbstractMapper<Seminar2Student> {
  @Override
  public int serializeOnJackson(Seminar2Student object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field seminarId (mapped with "seminarId")
    fieldCount++;
    jacksonSerializer.writeNumberField("seminarId", object.seminarId);

    // field studentId (mapped with "studentId")
    fieldCount++;
    jacksonSerializer.writeNumberField("studentId", object.studentId);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Seminar2Student object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field seminarId (mapped with "seminarId")
    jacksonSerializer.writeStringField("seminarId", PrimitiveUtils.writeLong(object.seminarId));

    // field studentId (mapped with "studentId")
    jacksonSerializer.writeStringField("studentId", PrimitiveUtils.writeLong(object.studentId));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Seminar2Student object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("seminar2Student");
    }

    // Persisted fields:

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field seminarId (mapped with "seminarId")
    xmlSerializer.writeStartElement("seminarId");
    xmlSerializer.writeLong(object.seminarId);
    xmlSerializer.writeEndElement();

    // field studentId (mapped with "studentId")
    xmlSerializer.writeStartElement("studentId");
    xmlSerializer.writeLong(object.studentId);
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * Initialize field mappers.
   */
  @Override
  public void init() {

  }

  /**
   * parse with jackson
   */
  @Override
  public Seminar2Student parseOnJackson(JsonParser jacksonParser) throws Exception {
    Seminar2Student instance = new Seminar2Student();
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
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "seminarId":
            // field seminarId (mapped with "seminarId")
            instance.seminarId=jacksonParser.getLongValue();
          break;
          case "studentId":
            // field studentId (mapped with "studentId")
            instance.studentId=jacksonParser.getLongValue();
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
  public Seminar2Student parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Seminar2Student instance = new Seminar2Student();
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
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "seminarId":
            // field seminarId (mapped with "seminarId")
            instance.seminarId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "studentId":
            // field studentId (mapped with "studentId")
            instance.studentId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public Seminar2Student parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    Seminar2Student instance = new Seminar2Student();
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
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "seminarId":
                  // property seminarId (mapped on "seminarId")
                  instance.seminarId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "studentId":
                  // property studentId (mapped on "studentId")
                  instance.studentId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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
      return instance;
    }
  }
