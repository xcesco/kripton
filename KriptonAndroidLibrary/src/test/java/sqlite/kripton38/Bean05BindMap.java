package sqlite.kripton38;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
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
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean05
 *
 * @see Bean05
 */
@BindMap(Bean05.class)
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
  public int serializeOnJackson(AbstractJacksonContext context, Bean05 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field beanType (mapped with "beanType")
      if (object.getBeanType()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("beanType", object.getBeanType().toString());
      }

      // field content (mapped with "content")
      if (object.getContent()!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("content", object.getContent());
      }

      // field creationTime (mapped with "creationTime")
      if (object.getCreationTime()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("creationTime", DateUtils.write(object.getCreationTime()));
      }

      // field number (mapped with "number")
      fieldCount++;
      jacksonSerializer.writeNumberField("number", object.getNumber());

      // field pk (mapped with "pk")
      fieldCount++;
      jacksonSerializer.writeNumberField("pk", object.getPk());

      // field text (mapped with "text")
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean05 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field beanType (mapped with "beanType")
      if (object.getBeanType()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("beanType", object.getBeanType().toString());
      }

      // field content (mapped with "content")
      if (object.getContent()!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("content", object.getContent());
      }

      // field creationTime (mapped with "creationTime")
      if (object.getCreationTime()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("creationTime", DateUtils.write(object.getCreationTime()));
      }

      // field number (mapped with "number")
      jacksonSerializer.writeStringField("number", PrimitiveUtils.writeLong(object.getNumber()));

      // field pk (mapped with "pk")
      jacksonSerializer.writeStringField("pk", PrimitiveUtils.writeLong(object.getPk()));

      // field text (mapped with "text")
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
  public void serializeOnXml(KriptonXmlContext context, Bean05 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean05");
      }

      // Persisted fields:

      // field beanType (mapped with "beanType")
      if (object.getBeanType()!=null)  {
        xmlSerializer.writeStartElement("beanType");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getBeanType().toString()));
        xmlSerializer.writeEndElement();
      }

      // field content (mapped with "content")
      if (object.getContent()!=null) {
        xmlSerializer.writeStartElement("content");
        xmlSerializer.writeBinary(object.getContent(), 0, object.getContent().length);
        xmlSerializer.writeEndElement();
      }

      // field creationTime (mapped with "creationTime")
      if (object.getCreationTime()!=null)  {
        xmlSerializer.writeStartElement("creationTime");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(DateUtils.write(object.getCreationTime())));
        xmlSerializer.writeEndElement();
      }

      // field number (mapped with "number")
      xmlSerializer.writeStartElement("number");
      xmlSerializer.writeLong(object.getNumber());
      xmlSerializer.writeEndElement();

      // field pk (mapped with "pk")
      xmlSerializer.writeStartElement("pk");
      xmlSerializer.writeLong(object.getPk());
      xmlSerializer.writeEndElement();

      // field text (mapped with "text")
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
  public Bean05 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
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
              // field beanType (mapped with "beanType")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.setBeanType(StringUtils.hasText(tempEnum)?BeanType.valueOf(tempEnum):null);
              }
            break;
            case "content":
              // field content (mapped with "content")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setContent(jacksonParser.getBinaryValue());
              }
            break;
            case "creationTime":
              // field creationTime (mapped with "creationTime")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setCreationTime( DateUtils.read(jacksonParser.getText()));
              }
            break;
            case "number":
              // field number (mapped with "number")
              instance.setNumber(jacksonParser.getLongValue());
            break;
            case "pk":
              // field pk (mapped with "pk")
              instance.setPk(jacksonParser.getLongValue());
            break;
            case "text":
              // field text (mapped with "text")
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
  public Bean05 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
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
              // field beanType (mapped with "beanType")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                String tempEnum=jacksonParser.getText();
                instance.setBeanType(StringUtils.hasText(tempEnum)?BeanType.valueOf(tempEnum):null);
              }
            break;
            case "content":
              // field content (mapped with "content")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setContent(Base64Utils.decode(jacksonParser.getValueAsString()));
              }
            break;
            case "creationTime":
              // field creationTime (mapped with "creationTime")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setCreationTime(DateUtils.read(jacksonParser.getText()));
              }
            break;
            case "number":
              // field number (mapped with "number")
              instance.setNumber(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "pk":
              // field pk (mapped with "pk")
              instance.setPk(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "text":
              // field text (mapped with "text")
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
  public Bean05 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
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
            case XmlPullParser.START_TAG:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "beanType":
                    // property beanType (mapped on "beanType")
                    instance.setBeanType(BeanType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                  break;
                  case "content":
                    // property content (mapped on "content")
                    instance.setContent(xmlParser.getElementAsBinary());
                  break;
                  case "creationTime":
                    // property creationTime (mapped on "creationTime")
                    instance.setCreationTime(DateUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText())));
                  break;
                  case "number":
                    // property number (mapped on "number")
                    instance.setNumber(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  case "pk":
                    // property pk (mapped on "pk")
                    instance.setPk(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  case "text":
                    // property text (mapped on "text")
                    instance.setText(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
