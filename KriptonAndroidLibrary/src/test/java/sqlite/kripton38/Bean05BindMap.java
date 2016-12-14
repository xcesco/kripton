package sqlite.kripton38;

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
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean05
 *
 * @see Bean05
 */
@BindMap
public class Bean05BindMap extends AbstractMapper<Bean05> {
  /**
   * create new object instance
   */
  @Override
  public Bean05 createInstance() {
    return new Bean05();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, Bean05 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field beanType
      if (object.getBeanType()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("beanType", object.getBeanType().toString());
      }

      // field content
      if (object.getContent()!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("content", object.getContent());
      }

      // field creationTime
      if (object.getCreationTime()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("creationTime", DateUtils.write(object.getCreationTime()));
      }

      // field number
      fieldCount++;
      jacksonSerializer.writeNumberField("number", object.getNumber());

      // field pk
      fieldCount++;
      jacksonSerializer.writeNumberField("pk", object.getPk());

      // field text
      if (object.getText()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("text", object.getText());
      }

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
  public int serializeOnJacksonAsString(JacksonContext context, Bean05 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field beanType
      if (object.getBeanType()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("beanType", object.getBeanType().toString());
      }

      // field content
      if (object.getContent()!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("content", object.getContent());
      }

      // field creationTime
      if (object.getCreationTime()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("creationTime", DateUtils.write(object.getCreationTime()));
      }

      // field number
      jacksonSerializer.writeStringField("number", PrimitiveUtils.writeLong(object.getNumber()));

      // field pk
      jacksonSerializer.writeStringField("pk", PrimitiveUtils.writeLong(object.getPk()));

      // field text
      if (object.getText()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("text", object.getText());
      }

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
  public void serializeOnXml(XmlBinderContext context, Bean05 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean05");
      }

      // Persisted fields:

      // field beanType
      if (object.getBeanType()!=null)  {
        xmlSerializer.writeStartElement("beanType");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getBeanType().toString()));
        xmlSerializer.writeEndElement();
      }

      // field content
      if (object.getContent()!=null) {
        xmlSerializer.writeStartElement("content");
        xmlSerializer.writeBinary(object.getContent(), 0, object.getContent().length);
        xmlSerializer.writeEndElement();
      }

      // field creationTime
      if (object.getCreationTime()!=null)  {
        xmlSerializer.writeStartElement("creationTime");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.getCreationTime())));
        xmlSerializer.writeEndElement();
      }

      // field number
      xmlSerializer.writeStartElement("number");
      xmlSerializer.writeLong(object.getNumber());
      xmlSerializer.writeEndElement();

      // field pk
      xmlSerializer.writeStartElement("pk");
      xmlSerializer.writeLong(object.getPk());
      xmlSerializer.writeEndElement();

      // field text
      if (object.getText()!=null) {
        xmlSerializer.writeStartElement("text");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getText()));
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
  public Bean05 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean05 instance = createInstance();
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
            case "beanType":
              // field beanType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.setBeanType(StringUtils.hasText(tempEnum)?BeanType.valueOf(tempEnum):null);
              }
            break;
            case "content":
              // field content
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setContent(jacksonParser.getBinaryValue());
              }
            break;
            case "creationTime":
              // field creationTime
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setCreationTime( DateUtils.read(jacksonParser.getText()));
              }
            break;
            case "number":
              // field number
              instance.setNumber(jacksonParser.getLongValue());
            break;
            case "pk":
              // field pk
              instance.setPk(jacksonParser.getLongValue());
            break;
            case "text":
              // field text
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setText(jacksonParser.getText());
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
  public Bean05 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean05 instance = createInstance();
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
            case "beanType":
              // field beanType
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.setBeanType(StringUtils.hasText(tempEnum)?BeanType.valueOf(tempEnum):null);
              }
            break;
            case "content":
              // field content
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setContent(Base64Utils.decode(jacksonParser.getValueAsString()));
              }
            break;
            case "creationTime":
              // field creationTime
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setCreationTime(DateUtils.read(jacksonParser.getText()));
              }
            break;
            case "number":
              // field number
              instance.setNumber(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "pk":
              // field pk
              instance.setPk(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "text":
              // field text
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setText(jacksonParser.getText());
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
  public Bean05 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean05 instance = createInstance();
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
                  case "beanType":
                    // property beanType
                    instance.setBeanType(BeanType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                  break;
                  case "content":
                    // property content
                    instance.setContent(xmlParser.getElementAsBinary());
                  break;
                  case "creationTime":
                    // property creationTime
                    instance.setCreationTime(DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                  break;
                  case "number":
                    // property number
                    instance.setNumber(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  case "pk":
                    // property pk
                    instance.setPk(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  case "text":
                    // property text
                    instance.setText(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEventConstants.END_ELEMENT:
                if (elementName.equals(xmlParser.getName())) {
                  currentTag = elementName;
                  elementName = null;
                }
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
