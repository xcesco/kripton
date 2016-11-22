package kripton70;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.CalendarUtil;
import com.abubusoft.kripton.common.CurrencyUtil;
import com.abubusoft.kripton.common.DateUtil;
import com.abubusoft.kripton.common.LocaleUtil;
import com.abubusoft.kripton.common.TimeUtil;
import com.abubusoft.kripton.common.TimeZoneUtil;
import com.abubusoft.kripton.common.UrlUtil;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement70
 *
 * @see BeanElement70
 */
@BindMap
public class BeanElement70BindMap extends AbstractMapper<BeanElement70> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement70 createInstance() {
    return new BeanElement70();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanElement70 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        jacksonSerializer.writeStringField("valueCalendar", CalendarUtil.write(object.valueCalendar));
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        jacksonSerializer.writeStringField("valueCurrency", CurrencyUtil.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        jacksonSerializer.writeStringField("valueDate", DateUtil.write(object.valueDate));
      }

      // field valueLocale
      if (object.valueLocale!=null)  {
        jacksonSerializer.writeStringField("valueLocale", LocaleUtil.write(object.valueLocale));
      }

      // field valueTime
      if (object.valueTime!=null)  {
        jacksonSerializer.writeStringField("valueTime", TimeUtil.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtil.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        jacksonSerializer.writeStringField("valueUrl", UrlUtil.write(object.valueUrl));
      }

      jacksonSerializer.writeEndObject();
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement70 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        jacksonSerializer.writeStringField("valueCalendar", CalendarUtil.write(object.valueCalendar));
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        jacksonSerializer.writeStringField("valueCurrency", CurrencyUtil.write(object.valueCurrency));
      }

      // field valueDate
      if (object.valueDate!=null)  {
        jacksonSerializer.writeStringField("valueDate", DateUtil.write(object.valueDate));
      }

      // field valueLocale
      if (object.valueLocale!=null)  {
        jacksonSerializer.writeStringField("valueLocale", LocaleUtil.write(object.valueLocale));
      }

      // field valueTime
      if (object.valueTime!=null)  {
        jacksonSerializer.writeStringField("valueTime", TimeUtil.write(object.valueTime));
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        jacksonSerializer.writeStringField("valueTimeZone", TimeZoneUtil.write(object.valueTimeZone));
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        jacksonSerializer.writeStringField("valueUrl", UrlUtil.write(object.valueUrl));
      }

      jacksonSerializer.writeEndObject();
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(XmlBinderContext context, BeanElement70 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueCalendar
      if (object.valueCalendar!=null)  {
        xmlSerializer.writeStartElement("valueCalendar");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CalendarUtil.write(object.valueCalendar)));
        xmlSerializer.writeEndElement();
      }

      // field valueCurrency
      if (object.valueCurrency!=null)  {
        xmlSerializer.writeStartElement("valueCurrency");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(CurrencyUtil.write(object.valueCurrency)));
        xmlSerializer.writeEndElement();
      }

      // field valueDate
      if (object.valueDate!=null)  {
        xmlSerializer.writeStartElement("valueDate");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtil.write(object.valueDate)));
        xmlSerializer.writeEndElement();
      }

      // field valueLocale
      if (object.valueLocale!=null)  {
        xmlSerializer.writeStartElement("valueLocale");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(LocaleUtil.write(object.valueLocale)));
        xmlSerializer.writeEndElement();
      }

      // field valueTime
      if (object.valueTime!=null)  {
        xmlSerializer.writeStartElement("valueTime");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TimeUtil.write(object.valueTime)));
        xmlSerializer.writeEndElement();
      }

      // field valueTimeZone
      if (object.valueTimeZone!=null)  {
        xmlSerializer.writeStartElement("valueTimeZone");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TimeZoneUtil.write(object.valueTimeZone)));
        xmlSerializer.writeEndElement();
      }

      // field valueUrl
      if (object.valueUrl!=null)  {
        xmlSerializer.writeStartElement("valueUrl");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(UrlUtil.write(object.valueUrl)));
        xmlSerializer.writeEndElement();
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(XMLStreamException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanElement70 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement70 instance = createInstance();
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
            case "valueCalendar":
              // field valueCalendar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCalendar= CalendarUtil.read(jacksonParser.getText());
              }
            break;
            case "valueCurrency":
              // field valueCurrency
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCurrency= CurrencyUtil.read(jacksonParser.getText());
              }
            break;
            case "valueDate":
              // field valueDate
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDate= DateUtil.read(jacksonParser.getText());
              }
            break;
            case "valueLocale":
              // field valueLocale
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLocale= LocaleUtil.read(jacksonParser.getText());
              }
            break;
            case "valueTime":
              // field valueTime
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTime= TimeUtil.read(jacksonParser.getText());
              }
            break;
            case "valueTimeZone":
              // field valueTimeZone
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTimeZone= TimeZoneUtil.read(jacksonParser.getText());
              }
            break;
            case "valueUrl":
              // field valueUrl
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueUrl= UrlUtil.read(jacksonParser.getText());
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanElement70 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement70 instance = createInstance();
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
            case "valueCalendar":
              // field valueCalendar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCalendar=CalendarUtil.read(jacksonParser.getText());
              }
            break;
            case "valueCurrency":
              // field valueCurrency
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueCurrency=CurrencyUtil.read(jacksonParser.getText());
              }
            break;
            case "valueDate":
              // field valueDate
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDate=DateUtil.read(jacksonParser.getText());
              }
            break;
            case "valueLocale":
              // field valueLocale
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLocale=LocaleUtil.read(jacksonParser.getText());
              }
            break;
            case "valueTime":
              // field valueTime
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTime=TimeUtil.read(jacksonParser.getText());
              }
            break;
            case "valueTimeZone":
              // field valueTimeZone
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueTimeZone=TimeZoneUtil.read(jacksonParser.getText());
              }
            break;
            case "valueUrl":
              // field valueUrl
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueUrl=UrlUtil.read(jacksonParser.getText());
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanElement70 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement70 instance = createInstance();
      int eventType = currentEventType;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      }
      String currentTag = xmlParser.getName().toString();
      Stack<String> elementNameStack = new Stack<>();
      elementNameStack.push(currentTag);
      // No attributes found

      //sub-elements
      while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
        eventType = xmlParser.next();
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "valueCalendar":
                    // property valueCalendar
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueCalendar=CalendarUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  case "valueCurrency":
                    // property valueCurrency
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueCurrency=CurrencyUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  case "valueDate":
                    // property valueDate
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueDate=DateUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  case "valueLocale":
                    // property valueLocale
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueLocale=LocaleUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  case "valueTime":
                    // property valueTime
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueTime=TimeUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  case "valueTimeZone":
                    // property valueTimeZone
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueTimeZone=TimeZoneUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  case "valueUrl":
                    // property valueUrl
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueUrl=UrlUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                    }
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEvent.END_ELEMENT:
                currentTag = elementNameStack.pop();
              break;
              case XMLEvent.CDATA:
              case XMLEvent.CHARACTERS:
                // no property is binded to VALUE o CDATA break;
              default:
              break;
          }
        }
        return instance;
      } catch(XMLStreamException e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
