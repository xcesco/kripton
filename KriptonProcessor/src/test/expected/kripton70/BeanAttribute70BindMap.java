package kripton70;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
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
 * This class is the shared preference binder defined for BeanAttribute70
 *
 * @see BeanAttribute70
 */
@BindMap
public class BeanAttribute70BindMap extends AbstractMapper<BeanAttribute70> {
  /**
   * create new object instance
   */
  @Override
  public BeanAttribute70 createInstance() {
    return new BeanAttribute70();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanAttribute70 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field id
      jacksonSerializer.writeNumberField("id", object.getId());

      // field valueBool
      if (object.valueBool!=null)  {
        jacksonSerializer.writeBooleanField("valueBool", object.valueBool);
      }

      // field valueBoolType
      jacksonSerializer.writeBooleanField("valueBoolType", object.valueBoolType);

      // field valueByte
      if (object.getValueByte()!=null)  {
        jacksonSerializer.writeNumberField("valueByte", object.getValueByte());
      }

      // field valueByteType
      jacksonSerializer.writeNumberField("valueByteType", object.valueByteType);

      // field valueChar
      if (object.valueChar!=null)  {
        jacksonSerializer.writeNumberField("valueChar", object.valueChar);
      }

      // field valueCharType
      jacksonSerializer.writeNumberField("valueCharType", object.valueCharType);

      // field valueDouble
      if (object.valueDouble!=null)  {
        jacksonSerializer.writeNumberField("valueDouble", object.valueDouble);
      }

      // field valueDoubleType
      jacksonSerializer.writeNumberField("valueDoubleType", object.valueDoubleType);

      // field valueFloat
      if (object.valueFloat!=null)  {
        jacksonSerializer.writeNumberField("valueFloat", object.valueFloat);
      }

      // field valueFloatType
      jacksonSerializer.writeNumberField("valueFloatType", object.valueFloatType);

      // field valueInt
      if (object.valueInt!=null)  {
        jacksonSerializer.writeNumberField("valueInt", object.valueInt);
      }

      // field valueIntType
      jacksonSerializer.writeNumberField("valueIntType", object.valueIntType);

      // field valueLong
      if (object.valueLong!=null)  {
        jacksonSerializer.writeNumberField("valueLong", object.valueLong);
      }

      // field valueLongType
      jacksonSerializer.writeNumberField("valueLongType", object.valueLongType);

      // field valueShort
      if (object.valueShort!=null)  {
        jacksonSerializer.writeNumberField("valueShort", object.valueShort);
      }

      // field valueShortType
      jacksonSerializer.writeNumberField("valueShortType", object.valueShortType);

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
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
  public void serializeOnJacksonAsString(JacksonContext context, BeanAttribute70 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", String.valueOf(object.getId()));

      // field valueBool
      if (object.valueBool!=null)  {
        jacksonSerializer.writeStringField("valueBool", String.valueOf(object.valueBool));
      }

      // field valueBoolType
      jacksonSerializer.writeStringField("valueBoolType", String.valueOf(object.valueBoolType));

      // field valueByte
      if (object.getValueByte()!=null)  {
        jacksonSerializer.writeStringField("valueByte", String.valueOf(object.getValueByte()));
      }

      // field valueByteType
      jacksonSerializer.writeStringField("valueByteType", String.valueOf(object.valueByteType));

      // field valueChar
      if (object.valueChar!=null)  {
        jacksonSerializer.writeStringField("valueChar", String.valueOf((int)object.valueChar));
      }

      // field valueCharType
      jacksonSerializer.writeStringField("valueCharType", String.valueOf((int)object.valueCharType));

      // field valueDouble
      if (object.valueDouble!=null)  {
        jacksonSerializer.writeStringField("valueDouble", String.valueOf(object.valueDouble));
      }

      // field valueDoubleType
      jacksonSerializer.writeStringField("valueDoubleType", String.valueOf(object.valueDoubleType));

      // field valueFloat
      if (object.valueFloat!=null)  {
        jacksonSerializer.writeStringField("valueFloat", String.valueOf(object.valueFloat));
      }

      // field valueFloatType
      jacksonSerializer.writeStringField("valueFloatType", String.valueOf(object.valueFloatType));

      // field valueInt
      if (object.valueInt!=null)  {
        jacksonSerializer.writeStringField("valueInt", String.valueOf(object.valueInt));
      }

      // field valueIntType
      jacksonSerializer.writeStringField("valueIntType", String.valueOf(object.valueIntType));

      // field valueLong
      if (object.valueLong!=null)  {
        jacksonSerializer.writeStringField("valueLong", String.valueOf(object.valueLong));
      }

      // field valueLongType
      jacksonSerializer.writeStringField("valueLongType", String.valueOf(object.valueLongType));

      // field valueShort
      if (object.valueShort!=null)  {
        jacksonSerializer.writeStringField("valueShort", String.valueOf(object.valueShort));
      }

      // field valueShortType
      jacksonSerializer.writeStringField("valueShortType", String.valueOf(object.valueShortType));

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
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
  public void serializeOnXml(XmlBinderContext context, BeanAttribute70 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field id
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeLong(object.getId());
      xmlSerializer.writeEndElement();

      // field valueBool
      if (object.valueBool!=null)  {
        xmlSerializer.writeStartElement("valueBool");
        xmlSerializer.writeBoolean(object.valueBool);
        xmlSerializer.writeEndElement();
      }

      // field valueBoolType
      xmlSerializer.writeStartElement("valueBoolType");
      xmlSerializer.writeBoolean(object.valueBoolType);
      xmlSerializer.writeEndElement();

      // field valueByte
      if (object.getValueByte()!=null)  {
        xmlSerializer.writeStartElement("valueByte");
        xmlSerializer.writeInt(object.getValueByte());
        xmlSerializer.writeEndElement();
      }

      // field valueByteType
      xmlSerializer.writeStartElement("valueByteType");
      xmlSerializer.writeInt(object.valueByteType);
      xmlSerializer.writeEndElement();

      // field valueChar
      if (object.valueChar!=null)  {
        xmlSerializer.writeStartElement("valueChar");
        xmlSerializer.writeInt(object.valueChar);
        xmlSerializer.writeEndElement();
      }

      // field valueCharType
      xmlSerializer.writeStartElement("valueCharType");
      xmlSerializer.writeInt(object.valueCharType);
      xmlSerializer.writeEndElement();

      // field valueDouble
      if (object.valueDouble!=null)  {
        xmlSerializer.writeStartElement("valueDouble");
        xmlSerializer.writeDouble(object.valueDouble);
        xmlSerializer.writeEndElement();
      }

      // field valueDoubleType
      xmlSerializer.writeStartElement("valueDoubleType");
      xmlSerializer.writeDouble(object.valueDoubleType);
      xmlSerializer.writeEndElement();

      // field valueFloat
      if (object.valueFloat!=null)  {
        xmlSerializer.writeStartElement("valueFloat");
        xmlSerializer.writeFloat(object.valueFloat);
        xmlSerializer.writeEndElement();
      }

      // field valueFloatType
      xmlSerializer.writeStartElement("valueFloatType");
      xmlSerializer.writeFloat(object.valueFloatType);
      xmlSerializer.writeEndElement();

      // field valueInt
      if (object.valueInt!=null)  {
        xmlSerializer.writeStartElement("valueInt");
        xmlSerializer.writeInt(object.valueInt);
        xmlSerializer.writeEndElement();
      }

      // field valueIntType
      xmlSerializer.writeStartElement("valueIntType");
      xmlSerializer.writeInt(object.valueIntType);
      xmlSerializer.writeEndElement();

      // field valueLong
      if (object.valueLong!=null)  {
        xmlSerializer.writeStartElement("valueLong");
        xmlSerializer.writeLong(object.valueLong);
        xmlSerializer.writeEndElement();
      }

      // field valueLongType
      xmlSerializer.writeStartElement("valueLongType");
      xmlSerializer.writeLong(object.valueLongType);
      xmlSerializer.writeEndElement();

      // field valueShort
      if (object.valueShort!=null)  {
        xmlSerializer.writeStartElement("valueShort");
        xmlSerializer.writeInt(object.valueShort);
        xmlSerializer.writeEndElement();
      }

      // field valueShortType
      xmlSerializer.writeStartElement("valueShortType");
      xmlSerializer.writeInt(object.valueShortType);
      xmlSerializer.writeEndElement();

      // field valueString
      if (object.valueString!=null)  {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
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
  public BeanAttribute70 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanAttribute70 instance = createInstance();
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
            case "id":
              // field id
              instance.setId(jacksonParser.getLongValue());
            break;
            case "valueBool":
              // field valueBool
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBool=jacksonParser.getBooleanValue();
              }
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=jacksonParser.getBooleanValue();
            break;
            case "valueByte":
              // field valueByte
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setValueByte(jacksonParser.getByteValue());
              }
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=jacksonParser.getByteValue();
            break;
            case "valueChar":
              // field valueChar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueChar=Character.valueOf((char)jacksonParser.getIntValue());
              }
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=Character.valueOf((char)jacksonParser.getIntValue());
            break;
            case "valueDouble":
              // field valueDouble
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDouble=jacksonParser.getDoubleValue();
              }
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=jacksonParser.getDoubleValue();
            break;
            case "valueFloat":
              // field valueFloat
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueFloat=jacksonParser.getFloatValue();
              }
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=jacksonParser.getFloatValue();
            break;
            case "valueInt":
              // field valueInt
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInt=jacksonParser.getIntValue();
              }
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=jacksonParser.getIntValue();
            break;
            case "valueLong":
              // field valueLong
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLong=jacksonParser.getLongValue();
              }
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=jacksonParser.getLongValue();
            break;
            case "valueShort":
              // field valueShort
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueShort=jacksonParser.getShortValue();
              }
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=jacksonParser.getShortValue();
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
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
  public BeanAttribute70 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanAttribute70 instance = createInstance();
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
            case "id":
              // field id
              instance.setId(Long.valueOf(jacksonParser.getText()));
            break;
            case "valueBool":
              // field valueBool
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBool=Boolean.valueOf(jacksonParser.getText());
              }
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=Boolean.valueOf(jacksonParser.getText());
            break;
            case "valueByte":
              // field valueByte
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setValueByte(Byte.valueOf(jacksonParser.getText()));
              }
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=Byte.valueOf(jacksonParser.getText());
            break;
            case "valueChar":
              // field valueChar
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueChar=Character.valueOf((char)(int)Integer.valueOf(jacksonParser.getText()));
              }
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=Character.valueOf((char)(int)Integer.valueOf(jacksonParser.getText()));
            break;
            case "valueDouble":
              // field valueDouble
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueDouble=Double.valueOf(jacksonParser.getText());
              }
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=Double.valueOf(jacksonParser.getText());
            break;
            case "valueFloat":
              // field valueFloat
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueFloat=Float.valueOf(jacksonParser.getText());
              }
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=Float.valueOf(jacksonParser.getText());
            break;
            case "valueInt":
              // field valueInt
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueInt=Integer.valueOf(jacksonParser.getText());
              }
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=Integer.valueOf(jacksonParser.getText());
            break;
            case "valueLong":
              // field valueLong
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueLong=Long.valueOf(jacksonParser.getText());
              }
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=Long.valueOf(jacksonParser.getText());
            break;
            case "valueShort":
              // field valueShort
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueShort=Short.valueOf(jacksonParser.getText());
              }
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=Short.valueOf(jacksonParser.getText());
            break;
            case "valueString":
              // field valueString
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueString=jacksonParser.getText();
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
  public BeanAttribute70 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanAttribute70 instance = createInstance();
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
                  case "name":
                    // property id
                    if (!xmlParser.isEmptyElement()) {
                      instance.setId(xmlParser.getElementAsLong());
                    }
                  break;
                  case "valueBool":
                    // property valueBool
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueBool=xmlParser.getElementAsBoolean();
                    }
                  break;
                  case "valueBoolType":
                    // property valueBoolType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueBoolType=xmlParser.getElementAsBoolean();
                    }
                  break;
                  case "valueByte":
                    // property valueByte
                    if (!xmlParser.isEmptyElement()) {
                      instance.setValueByte((byte)xmlParser.getElementAsInt());
                    }
                  break;
                  case "valueByteType":
                    // property valueByteType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueByteType=(byte)xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueChar":
                    // property valueChar
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueChar=(char)xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueCharType":
                    // property valueCharType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueCharType=(char)xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueDouble":
                    // property valueDouble
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueDouble=xmlParser.getElementAsDouble();
                    }
                  break;
                  case "valueDoubleType":
                    // property valueDoubleType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueDoubleType=xmlParser.getElementAsDouble();
                    }
                  break;
                  case "valueFloat":
                    // property valueFloat
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueFloat=xmlParser.getElementAsFloat();
                    }
                  break;
                  case "valueFloatType":
                    // property valueFloatType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueFloatType=xmlParser.getElementAsFloat();
                    }
                  break;
                  case "valueInt":
                    // property valueInt
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueInt=xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueIntType":
                    // property valueIntType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueIntType=xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueLong":
                    // property valueLong
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueLong=xmlParser.getElementAsLong();
                    }
                  break;
                  case "valueLongType":
                    // property valueLongType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueLongType=xmlParser.getElementAsLong();
                    }
                  break;
                  case "valueShort":
                    // property valueShort
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueShort=(short)xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueShortType":
                    // property valueShortType
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueShortType=(short)xmlParser.getElementAsInt();
                    }
                  break;
                  case "valueString":
                    // property valueString
                    if (!xmlParser.isEmptyElement()) {
                      instance.valueString=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
