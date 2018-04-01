package sqlite.kripton186.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is binder map for Country
 *
 * @see Country
 */
@BindMap(Country.class)
public class CountryBindMap extends AbstractMapper<Country> {
  @Override
  public int serializeOnJackson(Country object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field area (mapped with "area")
    fieldCount++;
    jacksonSerializer.writeNumberField("area", object.area);

    // field callingCode (mapped with "callingCode")
    if (object.callingCode!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("callingCode", object.callingCode);
    }

    // field code (mapped with "code")
    if (object.code!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("code", object.code);
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field region (mapped with "region")
    if (object.region!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("region", object.region);
    }

    // field translatedName (mapped with "translatedName")
    if (object.translatedName!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.translatedName.size()>0) {
        jacksonSerializer.writeFieldName("translatedName");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Translation, String> item: object.translatedName.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeNullField("value");
          } else {
            jacksonSerializer.writeStringField("value", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeNullField("translatedName");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Country object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field area (mapped with "area")
    jacksonSerializer.writeStringField("area", PrimitiveUtils.writeLong(object.area));

    // field callingCode (mapped with "callingCode")
    if (object.callingCode!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("callingCode", object.callingCode);
    }

    // field code (mapped with "code")
    if (object.code!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("code", object.code);
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("name", object.name);
    }

    // field region (mapped with "region")
    if (object.region!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("region", object.region);
    }

    // field translatedName (mapped with "translatedName")
    if (object.translatedName!=null)  {
      fieldCount++;
      // write wrapper tag
      if (object.translatedName.size()>0) {
        jacksonSerializer.writeFieldName("translatedName");
        jacksonSerializer.writeStartArray();
        for (Map.Entry<Translation, String> item: object.translatedName.entrySet()) {
          jacksonSerializer.writeStartObject();
          jacksonSerializer.writeStringField("key", item.getKey().toString());
          if (item.getValue()==null) {
            jacksonSerializer.writeStringField("value", "null");
          } else {
            jacksonSerializer.writeStringField("value", item.getValue());
          }
          jacksonSerializer.writeEndObject();
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeStringField("translatedName", "null");
      }
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Country object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("country");
    }

    // Persisted fields:

    // field area (mapped with "area")
    xmlSerializer.writeStartElement("area");
    xmlSerializer.writeLong(object.area);
    xmlSerializer.writeEndElement();

    // field callingCode (mapped with "callingCode")
    if (object.callingCode!=null) {
      xmlSerializer.writeStartElement("callingCode");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.callingCode));
      xmlSerializer.writeEndElement();
    }

    // field code (mapped with "code")
    if (object.code!=null) {
      xmlSerializer.writeStartElement("code");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.code));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
      xmlSerializer.writeEndElement();
    }

    // field region (mapped with "region")
    if (object.region!=null) {
      xmlSerializer.writeStartElement("region");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.region));
      xmlSerializer.writeEndElement();
    }

    // field translatedName (mapped with "translatedName")
    if (object.translatedName!=null)  {
      for (Map.Entry<Translation, String> item: object.translatedName.entrySet()) {
        xmlSerializer.writeStartElement("translatedName");
          xmlSerializer.writeStartElement("key");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getKey().toString()));
          xmlSerializer.writeEndElement();
          if (item.getValue()==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            if (item.getValue()!=null) {
              xmlSerializer.writeStartElement("value");
              xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item.getValue()));
              xmlSerializer.writeEndElement();
            }
          }
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Country parseOnJackson(JsonParser jacksonParser) throws Exception {
    Country instance = new Country();
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
          case "area":
            // field area (mapped with "area")
            instance.area=jacksonParser.getLongValue();
          break;
          case "callingCode":
            // field callingCode (mapped with "callingCode")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.callingCode=jacksonParser.getText();
            }
          break;
          case "code":
            // field code (mapped with "code")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.code=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "region":
            // field region (mapped with "region")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.region=jacksonParser.getText();
            }
          break;
          case "translatedName":
            // field translatedName (mapped with "translatedName")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Translation, String> collection=new HashMap<>();
              Translation key=null;
              String value=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                jacksonParser.nextValue();
                 {
                  String tempEnum=jacksonParser.getText();
                  key=StringUtils.hasText(tempEnum)?Translation.valueOf(tempEnum):null;
                }
                jacksonParser.nextValue();
                if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                  value=jacksonParser.getText();
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.translatedName=collection;
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
  public Country parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Country instance = new Country();
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
          case "area":
            // field area (mapped with "area")
            instance.area=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "callingCode":
            // field callingCode (mapped with "callingCode")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.callingCode=jacksonParser.getText();
            }
          break;
          case "code":
            // field code (mapped with "code")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.code=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.name=jacksonParser.getText();
            }
          break;
          case "region":
            // field region (mapped with "region")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.region=jacksonParser.getText();
            }
          break;
          case "translatedName":
            // field translatedName (mapped with "translatedName")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashMap<Translation, String> collection=new HashMap<>();
              Translation key=null;
              String value=null;
              JsonToken current;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                current=jacksonParser.currentToken();
                for (int i=0; i<2 ;i++) {
                  while (current != JsonToken.FIELD_NAME) {
                    current=jacksonParser.nextToken();
                  }
                  jacksonParser.nextValue();
                  switch(jacksonParser.getCurrentName()) {
                  case "key":
                     {
                      String tempEnum=jacksonParser.getText();
                      key=StringUtils.hasText(tempEnum)?Translation.valueOf(tempEnum):null;
                    }
                  break;
                  case "value":
                    tempValue=jacksonParser.getValueAsString();
                    if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                      value=null;
                    } else {
                      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                        value=jacksonParser.getText();
                      }
                    }
                  break;
                  }
                }
                collection.put(key, value);
                key=null;
                value=null;
                jacksonParser.nextToken();
              }
              instance.translatedName=collection;
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
  public Country parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Country instance = new Country();
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
                case "area":
                  // property area (mapped on "area")
                  instance.area=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "callingCode":
                  // property callingCode (mapped on "callingCode")
                  instance.callingCode=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "code":
                  // property code (mapped on "code")
                  instance.code=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "region":
                  // property region (mapped on "region")
                  instance.region=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "translatedName":
                  // property translatedName (mapped on "translatedName")
                   {
                    HashMap<Translation, String> collection=new HashMap<>();
                    Translation key;
                    String value;
                    // add first element
                    xmlParser.nextTag();
                    key=Translation.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    xmlParser.nextTag();
                    if (xmlParser.isEmptyElement()) {
                      value=null;
                      xmlParser.nextTag();
                    } else {
                      value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                    }
                    xmlParser.nextTag();
                    collection.put(key, value);
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("translatedName")) {
                      xmlParser.nextTag();
                      key=Translation.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      xmlParser.nextTag();
                      if (xmlParser.isEmptyElement()) {
                        value=null;
                        xmlParser.nextTag();
                      } else {
                        value=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      xmlParser.nextTag();
                      collection.put(key, value);
                    }
                    instance.translatedName=collection;
                    read=false;
                  }
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
