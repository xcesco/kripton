package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.persistence.XmlSerializer;
import com.abubusoft.kripton.persistence.XmlWrapperParser;
import com.abubusoft.kripton.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.persistence.xml.internal.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean87A_7
 *
 * @see Bean87A_7
 */
@BindMap(Bean87A_7.class)
public class Bean87A_7BindMap extends AbstractMapper<Bean87A_7> {
  /**
   * create new object instance
   */
  @Override
  public Bean87A_7 createInstance() {
    return new Bean87A_7();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean87A_7 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field attributeString (mapped with "attributeString")
      if (object.attributeString!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("attributeString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.attributeString));
      }

      // field dataString (mapped with "dataString")
      if (object.dataString!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("dataString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.dataString));
      }

      // field elementString (mapped with "elementString")
      if (object.elementString!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("elementString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.elementString));
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean87A_7 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field attributeString (mapped with "attributeString")
      if (object.attributeString!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("attributeString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.attributeString));
      }

      // field dataString (mapped with "dataString")
      if (object.dataString!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("dataString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.dataString));
      }

      // field elementString (mapped with "elementString")
      if (object.elementString!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("elementString", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.elementString));
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(KriptonXmlContext context, Bean87A_7 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean87A_7");
      }

      // Persisted fields:

      // field attributeString (mapped with "attributeString")
      // field trasformation java.lang.String bind.kripton87TypeAdapter.StringInverterTypeAdapter 
      if (object.attributeString!=null) {
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        xmlSerializer.writeAttribute("attributeString", StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.attributeString)));
      }

      // field elementString (mapped with "elementString")
      // field trasformation java.lang.String bind.kripton87TypeAdapter.StringInverterTypeAdapter 
      if (object.elementString!=null) {
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        xmlSerializer.writeStartElement("elementString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.elementString)));
        xmlSerializer.writeEndElement();
      }

      // field dataString (mapped with "dataString")
      // field trasformation java.lang.String bind.kripton87TypeAdapter.StringInverterTypeAdapter 
      if (object.dataString!=null) {
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        xmlSerializer.writeCData(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.dataString)));
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(Exception e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean87A_7 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_7 instance = createInstance();
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
            case "attributeString":
              // field attributeString (mapped with "attributeString")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.attributeString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
              }
            break;
            case "elementString":
              // field elementString (mapped with "elementString")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.elementString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
              }
            break;
            case "dataString":
              // field dataString (mapped with "dataString")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.dataString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean87A_7 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_7 instance = createInstance();
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
            case "attributeString":
              // field attributeString (mapped with "attributeString")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.attributeString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
              }
            break;
            case "elementString":
              // field elementString (mapped with "elementString")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.elementString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
              }
            break;
            case "dataString":
              // field dataString (mapped with "dataString")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.dataString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public Bean87A_7 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Bean87A_7 instance = createInstance();
      int eventType = currentEventType;
      boolean read=true;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      String currentTag = xmlParser.getName().toString();
      String elementName = currentTag;

      // attributes 
      String attributeName = null;
      int attributesCount = xmlParser.getAttributeCount();;
      for (int attributeIndex = 0; attributeIndex < attributesCount; attributeIndex++) {
        attributeName = xmlParser.getAttributeName(attributeIndex);
        switch(attributeName) {
            case "attributeString":
              // field attributeString (mapped by "attributeString")
              // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
              instance.attributeString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex)));
            break;
            default:
            break;
        }
      }

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
                  case "elementString":
                    // property elementString (mapped on "elementString")
                    // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                    instance.elementString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
                if (elementName!=null && xmlParser.hasText()) {
                  // property dataString
                  // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                  instance.dataString=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getText()));
                }
              break;
              default:
              break;
          }
        }
        return instance;
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
