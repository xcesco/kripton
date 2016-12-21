package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
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
 * This class is the shared preference binder defined for Bean87A_1
 *
 * @see Bean87A_1
 */
@BindMap(Bean87A_1.class)
public class Bean87A_1BindMap extends AbstractMapper<Bean87A_1> {
  /**
   * create new object instance
   */
  @Override
  public Bean87A_1 createInstance() {
    return new Bean87A_1();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean87A_1 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueDate (mapped with "valueDate")
      if (object.valueDate!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.DateLongTypeAdapter
        jacksonSerializer.writeNumberField("valueDate", TypeAdapterUtils.toData(DateLongTypeAdapter.class, object.valueDate));
      }

      // field valueDescription (mapped with "valueDescription")
      if (object.valueDescription!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("valueDescription", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.valueDescription));
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean87A_1 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field valueDate (mapped with "valueDate")
      if (object.valueDate!=null)  {
        // using type adapter bind.kripton87TypeAdapter.DateLongTypeAdapter
        jacksonSerializer.writeStringField("valueDate", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(DateLongTypeAdapter.class, object.valueDate)));
      }

      // field valueDescription (mapped with "valueDescription")
      if (object.valueDescription!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        jacksonSerializer.writeStringField("valueDescription", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.valueDescription));
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
  public void serializeOnXml(KriptonXmlContext context, Bean87A_1 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean87A_1");
      }

      // Persisted fields:

      // field valueDate (mapped with "valueDate")
      // field trasformation java.lang.Long bind.kripton87TypeAdapter.DateLongTypeAdapter 
      if (object.valueDate!=null)  {
        // using type adapter bind.kripton87TypeAdapter.DateLongTypeAdapter
        xmlSerializer.writeStartElement("valueDate");
        xmlSerializer.writeLong(TypeAdapterUtils.toData(DateLongTypeAdapter.class, object.valueDate));
        xmlSerializer.writeEndElement();
      }

      // field valueDescription (mapped with "valueDescription")
      // field trasformation java.lang.String bind.kripton87TypeAdapter.StringInverterTypeAdapter 
      if (object.valueDescription!=null) {
        // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
        xmlSerializer.writeStartElement("valueDescription");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.valueDescription)));
        xmlSerializer.writeEndElement();
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
  public Bean87A_1 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_1 instance = createInstance();
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
            case "valueDate":
              // field valueDate (mapped with "valueDate")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.DateLongTypeAdapter
                instance.valueDate=TypeAdapterUtils.toJava(DateLongTypeAdapter.class, jacksonParser.getLongValue());
              }
            break;
            case "valueDescription":
              // field valueDescription (mapped with "valueDescription")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.valueDescription=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
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
  public Bean87A_1 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_1 instance = createInstance();
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
            case "valueDate":
              // field valueDate (mapped with "valueDate")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.DateLongTypeAdapter
                instance.valueDate=TypeAdapterUtils.toJava(DateLongTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
              }
            break;
            case "valueDescription":
              // field valueDescription (mapped with "valueDescription")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                instance.valueDescription=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
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
  public Bean87A_1 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Bean87A_1 instance = createInstance();
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
                  case "valueDate":
                    // property valueDate (mapped on "valueDate")
                    // using type adapter bind.kripton87TypeAdapter.DateLongTypeAdapter
                    instance.valueDate=TypeAdapterUtils.toJava(DateLongTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null));
                  break;
                  case "valueDescription":
                    // property valueDescription (mapped on "valueDescription")
                    // using type adapter bind.kripton87TypeAdapter.StringInverterTypeAdapter
                    instance.valueDescription=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
      } catch(Exception e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
