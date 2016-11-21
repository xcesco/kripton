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
  public void serializeOnJackson(JacksonContext context, BeanAttribute70 object, JacksonWrapperSerializer wrapper, boolean writeStartAndEnd) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      if (writeStartAndEnd) {
        jacksonSerializer.writeStartObject();
      }

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

      if (writeStartAndEnd) {
        jacksonSerializer.writeEndObject();
      }
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  public void serializeOnJacksonAsString(JacksonContext context, BeanAttribute70 object, JacksonWrapperSerializer wrapper, boolean writeStartAndEnd) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      if (writeStartAndEnd) {
        jacksonSerializer.writeStartObject();
      }

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
        jacksonSerializer.writeStringField("valueChar", String.valueOf(object.valueChar));
      }

      // field valueCharType
      jacksonSerializer.writeStringField("valueCharType", String.valueOf(object.valueCharType));

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

      if (writeStartAndEnd) {
        jacksonSerializer.writeEndObject();
      }
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  public void serializeOnXml(XmlBinderContext context, BeanAttribute70 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanAttribute70");
      }

      // Persisted fields:

      // field id
      xmlSerializer.writeAttribute("name", String.valueOf(object.getId()));

      // field valueBool
      if (object.valueBool!=null)  {
        xmlSerializer.writeAttribute("valueBool", String.valueOf(object.valueBool));
      }

      // field valueBoolType
      xmlSerializer.writeAttribute("valueBoolType", String.valueOf(object.valueBoolType));

      // field valueByte
      if (object.getValueByte()!=null)  {
        xmlSerializer.writeAttribute("valueByte", String.valueOf(object.getValueByte()));
      }

      // field valueByteType
      xmlSerializer.writeAttribute("valueByteType", String.valueOf(object.valueByteType));

      // field valueChar
      if (object.valueChar!=null)  {
        xmlSerializer.writeAttribute("valueChar", String.valueOf(object.valueChar));
      }

      // field valueCharType
      xmlSerializer.writeAttribute("valueCharType", String.valueOf(object.valueCharType));

      // field valueDouble
      if (object.valueDouble!=null)  {
        xmlSerializer.writeAttribute("valueDouble", String.valueOf(object.valueDouble));
      }

      // field valueDoubleType
      xmlSerializer.writeAttribute("valueDoubleType", String.valueOf(object.valueDoubleType));

      // field valueFloat
      if (object.valueFloat!=null)  {
        xmlSerializer.writeAttribute("valueFloat", String.valueOf(object.valueFloat));
      }

      // field valueFloatType
      xmlSerializer.writeAttribute("valueFloatType", String.valueOf(object.valueFloatType));

      // field valueInt
      if (object.valueInt!=null)  {
        xmlSerializer.writeAttribute("valueInt", String.valueOf(object.valueInt));
      }

      // field valueIntType
      xmlSerializer.writeAttribute("valueIntType", String.valueOf(object.valueIntType));

      // field valueLong
      if (object.valueLong!=null)  {
        xmlSerializer.writeAttribute("valueLong", String.valueOf(object.valueLong));
      }

      // field valueLongType
      xmlSerializer.writeAttribute("valueLongType", String.valueOf(object.valueLongType));

      // field valueShort
      if (object.valueShort!=null)  {
        xmlSerializer.writeAttribute("valueShort", String.valueOf(object.valueShort));
      }

      // field valueShortType
      xmlSerializer.writeAttribute("valueShortType", String.valueOf(object.valueShortType));

      // field valueString
      if (object.valueString!=null)  {
        xmlSerializer.writeAttribute("valueString", object.valueString);
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
  public BeanAttribute70 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper, boolean readStartAndEnd) {
    return new BeanAttribute70();
  }

  /**
   * create new object instance
   */
  @Override
  public BeanAttribute70 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper, boolean readStartAndEnd) {
    return new BeanAttribute70();
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

      // attributes 
      String attributeName = null;
      String attributeValue = null;
      int attributes = xmlParser.getAttributeCount();;
      for (int i = 0; i < attributes; i++) {
        attributeName = xmlParser.getAttributeLocalName(i);
        attributeValue = StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(i));
        switch(attributeName) {
            case "name":
              // field id
              instance.setId(Long.valueOf(attributeValue));
            break;
            case "valueBool":
              // field valueBool
              instance.valueBool=Boolean.valueOf(attributeValue);
            break;
            case "valueBoolType":
              // field valueBoolType
              instance.valueBoolType=Boolean.valueOf(attributeValue);
            break;
            case "valueByte":
              // field valueByte
              instance.setValueByte((byte)Byte.valueOf(attributeValue));
            break;
            case "valueByteType":
              // field valueByteType
              instance.valueByteType=(byte)Byte.valueOf(attributeValue);
            break;
            case "valueChar":
              // field valueChar
              instance.valueChar=attributeValue.length()>0 ? attributeValue.charAt(0) : '\0';
            break;
            case "valueCharType":
              // field valueCharType
              instance.valueCharType=attributeValue.length()>0 ? attributeValue.charAt(0) : '\0';
            break;
            case "valueDouble":
              // field valueDouble
              instance.valueDouble=Double.valueOf(attributeValue);
            break;
            case "valueDoubleType":
              // field valueDoubleType
              instance.valueDoubleType=Double.valueOf(attributeValue);
            break;
            case "valueFloat":
              // field valueFloat
              instance.valueFloat=Float.valueOf(attributeValue);
            break;
            case "valueFloatType":
              // field valueFloatType
              instance.valueFloatType=Float.valueOf(attributeValue);
            break;
            case "valueInt":
              // field valueInt
              instance.valueInt=Integer.valueOf(attributeValue);
            break;
            case "valueIntType":
              // field valueIntType
              instance.valueIntType=Integer.valueOf(attributeValue);
            break;
            case "valueLong":
              // field valueLong
              instance.valueLong=Long.valueOf(attributeValue);
            break;
            case "valueLongType":
              // field valueLongType
              instance.valueLongType=Long.valueOf(attributeValue);
            break;
            case "valueShort":
              // field valueShort
              instance.valueShort=(short)Short.valueOf(attributeValue);
            break;
            case "valueShortType":
              // field valueShortType
              instance.valueShortType=(short)Short.valueOf(attributeValue);
            break;
            case "valueString":
              // field valueString
              instance.valueString=attributeValue;
            break;
            default:
            break;
        }
      }

      //sub-elements
      while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
        eventType = xmlParser.next();
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              elementNameStack.push(currentTag);
              // No property to manage here
            break;
            case XMLEvent.END_ELEMENT:
            break;
            case XMLEvent.CDATA:
            case XMLEvent.CHARACTERS:
            break;
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
