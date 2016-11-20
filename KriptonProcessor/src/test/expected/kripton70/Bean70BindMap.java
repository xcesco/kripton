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
import javax.xml.stream.XMLStreamException;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for Bean70
 *
 * @see Bean70
 */
@BindMap
public class Bean70BindMap extends AbstractMapper<Bean70> {
  /**
   * create new object instance
   */
  @Override
  public Bean70 createInstance() {
    return new Bean70();
  }

  /**
   * reset shared preferences
   */
  public void serializeOnJackson(JacksonContext context, Bean70 object, JacksonWrapperSerializer wrapper, boolean writeStartAndEnd) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      if (writeStartAndEnd) {
        jacksonSerializer.writeStartObject();
      }

      // Serialized Field:

      // field id
      jacksonSerializer.writeNumberField("id", object.id);

      // field valueBool2
      if (object.valueBool2!=null)  {
        jacksonSerializer.writeBooleanField("valueBool2", object.valueBool2);
      }

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueBool1
      if (object.valueBool1!=null)  {
        jacksonSerializer.writeBooleanField("valueBool1", object.valueBool1);
      }

      // field valueBoolType
      jacksonSerializer.writeBooleanField("valueBoolType", object.valueBoolType);

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
  public void serializeOnJacksonAsString(JacksonContext context, Bean70 object, JacksonWrapperSerializer wrapper, boolean writeStartAndEnd) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      if (writeStartAndEnd) {
        jacksonSerializer.writeStartObject();
      }

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", String.valueOf(object.id));

      // field valueBool2
      if (object.valueBool2!=null)  {
        jacksonSerializer.writeStringField("valueBool2", String.valueOf(object.valueBool2));
      }

      // field valueString
      if (object.valueString!=null)  {
        jacksonSerializer.writeStringField("valueString", object.valueString);
      }

      // field valueBool1
      if (object.valueBool1!=null)  {
        jacksonSerializer.writeStringField("valueBool1", String.valueOf(object.valueBool1));
      }

      // field valueBoolType
      jacksonSerializer.writeStringField("valueBoolType", String.valueOf(object.valueBoolType));

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
  public void serializeOnXml(XmlBinderContext context, Bean70 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("root");
      }

      // Persisted fields:

      // field valueBool2
      if (object.valueBool2!=null)  {
        xmlSerializer.writeAttribute("valueBool2", String.valueOf(object.valueBool2));
      }

      // field valueBoolType
      xmlSerializer.writeAttribute("nameaa", String.valueOf(object.valueBoolType));

      // field id
      xmlSerializer.writeStartElement("name");
      xmlSerializer.writeLong(object.id);
      xmlSerializer.writeEndElement();

      // field valueString
      if (object.valueString!=null)  {
        xmlSerializer.writeStartElement("valueString");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.valueString));
        xmlSerializer.writeEndElement();
      }

      // field valueBool1
      if (object.valueBool1!=null)  {
        xmlSerializer.writeStartElement("valueBool1");
        xmlSerializer.writeBoolean(object.valueBool1);
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
  public Bean70 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper, boolean readStartAndEnd) {
    return new Bean70();
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper, boolean readStartAndEnd) {
    return new Bean70();
  }

  /**
   * create new object instance
   */
  @Override
  public Bean70 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    return new Bean70();
  }
}
