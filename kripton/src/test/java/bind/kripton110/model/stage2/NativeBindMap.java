package bind.kripton110.model.stage2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Native
 *
 * @see Native
 */
@BindMap(Native.class)
public class NativeBindMap extends AbstractMapper<Native> {
  /**
   * binder for type Nld
   */
  private NldBindMap nldBindMap;

  /**
   * binder for type Pap
   */
  private PapBindMap papBindMap;

  @Override
  public int serializeOnJackson(Native object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field nld (mapped with "nld")
    if (object.nld!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("nld");
      nldBindMap.serializeOnJackson(object.nld, jacksonSerializer);
    }

    // field pap (mapped with "pap")
    if (object.pap!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("pap");
      papBindMap.serializeOnJackson(object.pap, jacksonSerializer);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Native object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field nld (mapped with "nld")
    if (object.nld!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("nld");
      if (nldBindMap.serializeOnJacksonAsString(object.nld, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("nld");
      }
    }

    // field pap (mapped with "pap")
    if (object.pap!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("pap");
      if (papBindMap.serializeOnJacksonAsString(object.pap, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("pap");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Native object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("native");
    }

    // Persisted fields:

    // field nld (mapped with "nld")
    if (object.nld!=null)  {
      xmlSerializer.writeStartElement("nld");
      nldBindMap.serializeOnXml(object.nld, xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    // field pap (mapped with "pap")
    if (object.pap!=null)  {
      xmlSerializer.writeStartElement("pap");
      papBindMap.serializeOnXml(object.pap, xmlSerializer, EventType.START_TAG);
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
  public Native parseOnJackson(JsonParser jacksonParser) throws Exception {
    Native instance = new Native();
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
          case "nld":
            // field nld (mapped with "nld")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.nld=nldBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "pap":
            // field pap (mapped with "pap")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.pap=papBindMap.parseOnJackson(jacksonParser);
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
  public Native parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Native instance = new Native();
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
          case "nld":
            // field nld (mapped with "nld")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.nld=nldBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "pap":
            // field pap (mapped with "pap")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.pap=papBindMap.parseOnJacksonAsString(jacksonParser);
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
  public Native parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Native instance = new Native();
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
                case "nld":
                  // property nld (mapped on "nld")
                  instance.nld=nldBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "pap":
                  // property pap (mapped on "pap")
                  instance.pap=papBindMap.parseOnXml(xmlParser, eventType);
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
          return instance;
        }
      }

      @Override
      public void init() {
        // binding maps initialization 
        nldBindMap=BinderUtils.mapperFor(Nld.class);
        papBindMap=BinderUtils.mapperFor(Pap.class);
      }
    }
