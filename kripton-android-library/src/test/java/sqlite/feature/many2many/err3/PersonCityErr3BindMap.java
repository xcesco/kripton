package sqlite.feature.many2many.err3;

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
 * This class is binder map for PersonCityErr3
 *
 * @see PersonCityErr3
 */
@BindMap(PersonCityErr3.class)
public class PersonCityErr3BindMap extends AbstractMapper<PersonCityErr3> {
  @Override
  public int serializeOnJackson(PersonCityErr3 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field cityId (mapped with "cityId")
    fieldCount++;
    jacksonSerializer.writeNumberField("cityId", object.cityId);

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field personId (mapped with "personId")
    fieldCount++;
    jacksonSerializer.writeNumberField("personId", object.personId);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(PersonCityErr3 object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field cityId (mapped with "cityId")
    jacksonSerializer.writeStringField("cityId", PrimitiveUtils.writeLong(object.cityId));

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field personId (mapped with "personId")
    jacksonSerializer.writeStringField("personId", PrimitiveUtils.writeLong(object.personId));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(PersonCityErr3 object, XMLSerializer xmlSerializer,
      EventType currentEventType) throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("personCityErr3");
    }

    // Persisted fields:

    // field cityId (mapped with "cityId")
    xmlSerializer.writeStartElement("cityId");
    xmlSerializer.writeLong(object.cityId);
    xmlSerializer.writeEndElement();

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field personId (mapped with "personId")
    xmlSerializer.writeStartElement("personId");
    xmlSerializer.writeLong(object.personId);
    xmlSerializer.writeEndElement();

    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public PersonCityErr3 parseOnJackson(JsonParser jacksonParser) throws Exception {
    PersonCityErr3 instance = new PersonCityErr3();
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
          case "cityId":
            // field cityId (mapped with "cityId")
            instance.cityId=jacksonParser.getLongValue();
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "personId":
            // field personId (mapped with "personId")
            instance.personId=jacksonParser.getLongValue();
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
  public PersonCityErr3 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    PersonCityErr3 instance = new PersonCityErr3();
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
          case "cityId":
            // field cityId (mapped with "cityId")
            instance.cityId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "personId":
            // field personId (mapped with "personId")
            instance.personId=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public PersonCityErr3 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws
      Exception {
    PersonCityErr3 instance = new PersonCityErr3();
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
                case "cityId":
                  // property cityId (mapped on "cityId")
                  instance.cityId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "personId":
                  // property personId (mapped on "personId")
                  instance.personId=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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
      return instance;
    }

    public void init() {
      // binding maps initialization 
    }
  }
