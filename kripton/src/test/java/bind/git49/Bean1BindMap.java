package bind.git49;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.EventType;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * This class is binder map for Bean1
 *
 * @see Bean1
 */
@BindMap(Bean1.class)
public class Bean1BindMap extends AbstractMapper<Bean1> {
  /**
   * Bean2BindMap */
  private Bean2BindMap bean2BindMap;

  @Override
  public int serializeOnJackson(Bean1 object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field adaptedBean3 (mapped with "adaptedBean3")
    if (object.adaptedBean3!=null)  {
      fieldCount++;
      // using type adapter bind.git49.Bean3Adapter
      jacksonSerializer.writeStringField("adaptedBean3", TypeAdapterUtils.toData(Bean3Adapter.class, object.adaptedBean3));
    }

    // field bean2 (mapped with "bean2")
    if (object.bean2!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("bean2");
      bean2BindMap.serializeOnJackson(object.bean2, jacksonSerializer);
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
  public int serializeOnJacksonAsString(Bean1 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field adaptedBean3 (mapped with "adaptedBean3")
    if (object.adaptedBean3!=null)  {
      fieldCount++;
      // using type adapter bind.git49.Bean3Adapter
      jacksonSerializer.writeStringField("adaptedBean3", TypeAdapterUtils.toData(Bean3Adapter.class, object.adaptedBean3));
    }

    // field bean2 (mapped with "bean2")
    if (object.bean2!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("bean2");
      if (bean2BindMap.serializeOnJacksonAsString(object.bean2, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("bean2");
      }
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
  public void serializeOnXml(Bean1 object, XMLSerializer xmlSerializer, EventType currentEventType)
      throws Exception {
    if (currentEventType == EventType.START_DOCUMENT) {
      xmlSerializer.writeStartElement("bean1");
    }

    // Persisted fields:

    // field adaptedBean3 (mapped with "adaptedBean3")
    // field trasformation java.lang.String bind.git49.Bean3Adapter 
    if (object.adaptedBean3!=null) {
      // using type adapter bind.git49.Bean3Adapter
      xmlSerializer.writeStartElement("adaptedBean3");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(Bean3Adapter.class, object.adaptedBean3)));
      xmlSerializer.writeEndElement();
    }

    // field bean2 (mapped with "bean2")
    if (object.bean2!=null)  {
      xmlSerializer.writeStartElement("bean2");
      bean2BindMap.serializeOnXml(object.bean2, xmlSerializer, EventType.START_TAG);
      xmlSerializer.writeEndElement();
    }

    // field name (mapped with "name")
    if (object.name!=null) {
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.name));
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
  public Bean1 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean1 instance = new Bean1();
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
          case "adaptedBean3":
            // field adaptedBean3 (mapped with "adaptedBean3")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.git49.Bean3Adapter
              instance.adaptedBean3=TypeAdapterUtils.toJava(Bean3Adapter.class, jacksonParser.getText());
            }
          break;
          case "bean2":
            // field bean2 (mapped with "bean2")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.bean2=bean2BindMap.parseOnJackson(jacksonParser);
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
  public Bean1 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean1 instance = new Bean1();
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
          case "adaptedBean3":
            // field adaptedBean3 (mapped with "adaptedBean3")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.git49.Bean3Adapter
              instance.adaptedBean3=TypeAdapterUtils.toJava(Bean3Adapter.class, jacksonParser.getText());
            }
          break;
          case "bean2":
            // field bean2 (mapped with "bean2")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.bean2=bean2BindMap.parseOnJacksonAsString(jacksonParser);
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
  public Bean1 parseOnXml(XMLParser xmlParser, EventType currentEventType) throws Exception {
    Bean1 instance = new Bean1();
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
                case "adaptedBean3":
                  // property adaptedBean3 (mapped on "adaptedBean3")
                  // using type adapter bind.git49.Bean3Adapter
                  instance.adaptedBean3=TypeAdapterUtils.toJava(Bean3Adapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "bean2":
                  // property bean2 (mapped on "bean2")
                  instance.bean2=bean2BindMap.parseOnXml(xmlParser, eventType);
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
  }
