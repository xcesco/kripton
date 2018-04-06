package bind.kripton110.model.stage2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for Nld.
 *
 * @see Nld
 */
@BindMap(Nld.class)
public class NldBindMap extends AbstractMapper<Nld> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(Nld object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field common (mapped with "common")
    if (object.common!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("common", object.common);
    }

    // field official (mapped with "official")
    if (object.official!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("official", object.official);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(Nld object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field common (mapped with "common")
    if (object.common!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("common", object.common);
    }

    // field official (mapped with "official")
    if (object.official!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("official", object.official);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization.
   *
   * @param object the object
   * @param xmlSerializer the xml serializer
   * @param currentEventType the current event type
   * @throws Exception the exception
   */
  @Override
  public void serializeOnXml(Nld object, XMLSerializer xmlSerializer, int currentEventType) throws
      Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("nld");
    }

    // Persisted fields:

    // field common (mapped with "common")
    if (object.common!=null) {
      xmlSerializer.writeStartElement("common");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.common));
      xmlSerializer.writeEndElement();
    }

    // field official (mapped with "official")
    if (object.official!=null) {
      xmlSerializer.writeStartElement("official");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.official));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the nld
   * @throws Exception the exception
   */
  @Override
  public Nld parseOnJackson(JsonParser jacksonParser) throws Exception {
    Nld instance = new Nld();
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
          case "common":
            // field common (mapped with "common")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.common=jacksonParser.getText();
            }
          break;
          case "official":
            // field official (mapped with "official")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.official=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the nld
   * @throws Exception the exception
   */
  @Override
  public Nld parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Nld instance = new Nld();
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
          case "common":
            // field common (mapped with "common")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.common=jacksonParser.getText();
            }
          break;
          case "official":
            // field official (mapped with "official")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.official=jacksonParser.getText();
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml.
   *
   * @param xmlParser the xml parser
   * @param currentEventType the current event type
   * @return the nld
   * @throws Exception the exception
   */
  @Override
  public Nld parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Nld instance = new Nld();
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
                case "common":
                  // property common (mapped on "common")
                  instance.common=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "official":
                  // property official (mapped on "official")
                  instance.official=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
