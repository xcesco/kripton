package bind.kripton110.model.stage2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for Native
 *
 * @see Native
 */
@BindMap(Native.class)
public class NativeBindMap extends AbstractMapper<Native> {
  /**
   * NldBindMap */
  private NldBindMap nldBindMap = BinderUtils.mapperFor(Nld.class);

  /**
   * PapBindMap */
  private PapBindMap papBindMap = BinderUtils.mapperFor(Pap.class);

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
  public void serializeOnXml(Native object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("native");
    }

    // Persisted fields:

    // field nld (mapped with "nld")
    if (object.nld!=null)  {
      xmlSerializer.writeStartElement("nld");
      nldBindMap.serializeOnXml(object.nld, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field pap (mapped with "pap")
    if (object.pap!=null)  {
      xmlSerializer.writeStartElement("pap");
      papBindMap.serializeOnXml(object.pap, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
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
  public Native parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Native instance = new Native();
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
                case "nld":
                  // property nld (mapped on "nld")
                  instance.nld=nldBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "pap":
                  // property pap (mapped on "pap")
                  instance.pap=papBindMap.parseOnXml(xmlParser, eventType);
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
