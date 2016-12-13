package sqlite.kripton84;

import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.binder.context.JacksonContext;
import com.abubusoft.kripton.binder.context.XmlBinderContext;
import com.abubusoft.kripton.binder.core.AbstractMapper;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.binder.xml.XMLEventConstants;
import com.abubusoft.kripton.binder.xml.XmlParser;
import com.abubusoft.kripton.binder.xml.XmlSerializer;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean84B
 *
 * @see Bean84B
 */
@BindMap
public class Bean84BBindMap extends AbstractMapper<Bean84B> {
  /**
   * create new object instance
   */
  @Override
  public Bean84B createInstance() {
    return new Bean84B();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, Bean84B object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field columnBean
      if (object.columnBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("columnBean");
        context.mapperFor(Bean84B2.class).serializeOnJackson(context, object.columnBean, wrapper);
      }

      // field id
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.id);

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(JacksonContext context, Bean84B object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field columnBean
      if (object.columnBean!=null)  {
        fieldCount++;
        jacksonSerializer.writeFieldName("columnBean");
        if (context.mapperFor(Bean84B2.class).serializeOnJacksonAsString(context, object.columnBean, wrapper)==0) {
          jacksonSerializer.writeNullField("columnBean");
        }
      }

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(XmlBinderContext context, Bean84B object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean84B");
      }

      // Persisted fields:

      // field columnBean
      if (object.columnBean!=null)  {
        xmlSerializer.writeStartElement("columnBean");
        context.mapperFor(Bean84B2.class).serializeOnXml(context, object.columnBean, wrapper, 1);
        xmlSerializer.writeEndElement();
      }

      // field id
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.id);
      xmlSerializer.writeEndElement();

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
  public Bean84B parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean84B instance = createInstance();
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
            case "columnBean":
              // field columnBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.columnBean=context.mapperFor(Bean84B2.class).parseOnJackson(context, wrapper);
              }
            break;
            case "id":
              // field id
              instance.id=jacksonParser.getLongValue();
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
  public Bean84B parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean84B instance = createInstance();
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
            case "columnBean":
              // field columnBean
              if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
                instance.columnBean=context.mapperFor(Bean84B2.class).parseOnJacksonAsString(context, wrapper);
              }
            break;
            case "id":
              // field id
              instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
  public Bean84B parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean84B instance = createInstance();
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
            case XMLEventConstants.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "columnBean":
                    // property columnBean
                    instance.columnBean=context.mapperFor(Bean84B2.class).parseOnXml(context, wrapper, eventType);
                  break;
                  case "id":
                    // property id
                    instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEventConstants.END_ELEMENT:
                currentTag = elementName;
                elementName = null;
              break;
              case XMLEventConstants.CDATA:
              case XMLEventConstants.CHARACTERS:
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
