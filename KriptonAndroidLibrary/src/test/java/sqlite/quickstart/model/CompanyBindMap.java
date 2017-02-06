package sqlite.quickstart.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is binder map for Company
 *
 * @see Company
 */
@BindMap(Company.class)
public class CompanyBindMap extends AbstractMapper<Company> {
  @Override
  public int serializeOnJackson(Company object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field bs (mapped with "bs")
    if (object.bs!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("bs", object.bs);
    }

    // field catchPhrase (mapped with "catchPhrase")
    if (object.catchPhrase!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("catchPhrase", object.catchPhrase);
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Company object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field bs (mapped with "bs")
    if (object.bs!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("bs", object.bs);
    }

    // field catchPhrase (mapped with "catchPhrase")
    if (object.catchPhrase!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("catchPhrase", object.catchPhrase);
    }

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Company object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("company");
    }

    // Persisted fields:

    // field bs (mapped with "bs")
    if (object.bs!=null) {
      xmlSerializer.writeStartElement("bs");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.bs));
      xmlSerializer.writeEndElement();
    }

    // field catchPhrase (mapped with "catchPhrase")
    if (object.catchPhrase!=null) {
      xmlSerializer.writeStartElement("catchPhrase");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.catchPhrase));
      xmlSerializer.writeEndElement();
    }

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
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
  public Company parseOnJackson(JsonParser jacksonParser) throws Exception {
    Company instance = new Company();
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
          case "bs":
            // field bs (mapped with "bs")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.bs=jacksonParser.getText();
            }
          break;
          case "catchPhrase":
            // field catchPhrase (mapped with "catchPhrase")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.catchPhrase=jacksonParser.getText();
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
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
  public Company parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Company instance = new Company();
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
          case "bs":
            // field bs (mapped with "bs")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.bs=jacksonParser.getText();
            }
          break;
          case "catchPhrase":
            // field catchPhrase (mapped with "catchPhrase")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.catchPhrase=jacksonParser.getText();
            }
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
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
  public Company parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Company instance = new Company();
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
                case "bs":
                  // property bs (mapped on "bs")
                  instance.bs=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "catchPhrase":
                  // property catchPhrase (mapped on "catchPhrase")
                  instance.catchPhrase=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
