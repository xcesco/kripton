package bind.kripton81MoreCoverageTests;

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
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.BigIntegerUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Bean81G
 *
 * @see Bean81G
 */
@BindMap
public class Bean81GBindMap extends AbstractMapper<Bean81G> {
  /**
   * create new object instance
   */
  @Override
  public Bean81G createInstance() {
    return new Bean81G();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, Bean81G object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.id);

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.valueBigDecimal));
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.valueBigInteger));
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
  public int serializeOnJacksonAsString(JacksonContext context, Bean81G object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigDecimal", BigDecimalUtils.write(object.valueBigDecimal));
      }

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("valueBigInteger", BigIntegerUtils.write(object.valueBigInteger));
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
  public void serializeOnXml(XmlBinderContext context, Bean81G object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean81G");
      }

      // Persisted fields:

      // field valueBigInteger
      if (object.valueBigInteger!=null)  {
        xmlSerializer.writeIntegerAttribute(null, null,"valueBigInteger", object.valueBigInteger);
      }

      // field id
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.id);
      xmlSerializer.writeEndElement();

      // field valueBigDecimal
      if (object.valueBigDecimal!=null)  {
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtils.write(object.valueBigDecimal)));
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
  public Bean81G parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean81G instance = createInstance();
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
            case "valueBigInteger":
              // field valueBigInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigInteger=BigIntegerUtils.read(jacksonParser.getText());
              }
            break;
            case "id":
              // field id
              instance.id=jacksonParser.getLongValue();
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigDecimal=BigDecimalUtils.read(jacksonParser.getText());
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
  public Bean81G parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean81G instance = createInstance();
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
            case "valueBigInteger":
              // field valueBigInteger
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigInteger=BigIntegerUtils.read(jacksonParser.getText());
              }
            break;
            case "id":
              // field id
              instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
            break;
            case "valueBigDecimal":
              // field valueBigDecimal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.valueBigDecimal=BigDecimalUtils.read(jacksonParser.getText());
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
  public Bean81G parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlParser xmlParser = wrapper.xmlParser;
      Bean81G instance = createInstance();
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
        attributeName = xmlParser.getAttributeLocalName(attributeIndex);
        switch(attributeName) {
            case "valueBigInteger":
              // field valueBigInteger
              instance.valueBigInteger=xmlParser.getAttributeAsInteger(attributeIndex);
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
            case XMLEventConstants.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
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
                if (elementName!=null && xmlParser.hasText()) {
                  // property valueBigDecimal
                  instance.valueBigDecimal=BigDecimalUtils.read(StringEscapeUtils.unescapeXml(xmlParser.getText()));
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
