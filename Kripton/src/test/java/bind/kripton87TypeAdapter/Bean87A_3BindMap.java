package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
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
 * This class is the shared preference binder defined for Bean87A_3
 *
 * @see Bean87A_3
 */
@BindMap(Bean87A_3.class)
public class Bean87A_3BindMap extends AbstractMapper<Bean87A_3> {
  /**
   * create new object instance
   */
  @Override
  public Bean87A_3 createInstance() {
    return new Bean87A_3();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean87A_3 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field attributeBoolean (mapped with "attributeBoolean")
      if (object.attributeBoolean!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        jacksonSerializer.writeNumberField("attributeBoolean", TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.attributeBoolean));
      }

      // field dataBoolean (mapped with "dataBoolean")
      if (object.dataBoolean!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        jacksonSerializer.writeNumberField("dataBoolean", TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.dataBoolean));
      }

      // field elementBoolean (mapped with "elementBoolean")
      if (object.elementBoolean!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        jacksonSerializer.writeNumberField("elementBoolean", TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.elementBoolean));
      }

      // field elementEnum (mapped with "elementEnum")
      if (object.elementEnum!=null)  {
        fieldCount++;
        // using type adapter bind.kripton87TypeAdapter.EnumIntegerTypeAdapter
        jacksonSerializer.writeNumberField("elementEnum", TypeAdapterUtils.toData(EnumIntegerTypeAdapter.class, object.elementEnum));
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean87A_3 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field attributeBoolean (mapped with "attributeBoolean")
      if (object.attributeBoolean!=null)  {
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        jacksonSerializer.writeStringField("attributeBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.attributeBoolean)));
      }

      // field dataBoolean (mapped with "dataBoolean")
      if (object.dataBoolean!=null)  {
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        jacksonSerializer.writeStringField("dataBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.dataBoolean)));
      }

      // field elementBoolean (mapped with "elementBoolean")
      if (object.elementBoolean!=null)  {
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        jacksonSerializer.writeStringField("elementBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.elementBoolean)));
      }

      // field elementEnum (mapped with "elementEnum")
      if (object.elementEnum!=null)  {
        // using type adapter bind.kripton87TypeAdapter.EnumIntegerTypeAdapter
        jacksonSerializer.writeStringField("elementEnum", PrimitiveUtils.writeInteger(TypeAdapterUtils.toData(EnumIntegerTypeAdapter.class, object.elementEnum)));
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
  public void serializeOnXml(KriptonXmlContext context, Bean87A_3 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean87A_3");
      }

      // Persisted fields:

      // field attributeBoolean (mapped with "attributeBoolean")
      // field trasformation java.lang.Long bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter 
      if (object.attributeBoolean!=null)  {
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        xmlSerializer.writeAttribute("attributeBoolean", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.attributeBoolean)));
      }

      // field elementBoolean (mapped with "elementBoolean")
      // field trasformation java.lang.Long bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter 
      if (object.elementBoolean!=null)  {
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        xmlSerializer.writeStartElement("elementBoolean");
        xmlSerializer.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.elementBoolean));
        xmlSerializer.writeEndElement();
      }

      // field elementEnum (mapped with "elementEnum")
      // field trasformation java.lang.Integer bind.kripton87TypeAdapter.EnumIntegerTypeAdapter 
      if (object.elementEnum!=null)  {
        // using type adapter bind.kripton87TypeAdapter.EnumIntegerTypeAdapter
        xmlSerializer.writeStartElement("elementEnum");
        xmlSerializer.writeInt(TypeAdapterUtils.toData(EnumIntegerTypeAdapter.class, object.elementEnum));
        xmlSerializer.writeEndElement();
      }

      // field dataBoolean (mapped with "dataBoolean")
      // field trasformation java.lang.Long bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter 
      if (object.dataBoolean!=null)  {
        // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
        xmlSerializer.writeLong(TypeAdapterUtils.toData(BooleanByteArrayTypeAdapter.class, object.dataBoolean));
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
  public Bean87A_3 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_3 instance = createInstance();
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
            case "attributeBoolean":
              // field attributeBoolean (mapped with "attributeBoolean")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, jacksonParser.getLongValue());
              }
            break;
            case "elementBoolean":
              // field elementBoolean (mapped with "elementBoolean")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                instance.elementBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, jacksonParser.getLongValue());
              }
            break;
            case "elementEnum":
              // field elementEnum (mapped with "elementEnum")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.EnumIntegerTypeAdapter
                instance.elementEnum=TypeAdapterUtils.toJava(EnumIntegerTypeAdapter.class, jacksonParser.getIntValue());
              }
            break;
            case "dataBoolean":
              // field dataBoolean (mapped with "dataBoolean")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                instance.dataBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, jacksonParser.getLongValue());
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
  public Bean87A_3 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_3 instance = createInstance();
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
            case "attributeBoolean":
              // field attributeBoolean (mapped with "attributeBoolean")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
              }
            break;
            case "elementBoolean":
              // field elementBoolean (mapped with "elementBoolean")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                instance.elementBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
              }
            break;
            case "elementEnum":
              // field elementEnum (mapped with "elementEnum")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.EnumIntegerTypeAdapter
                instance.elementEnum=TypeAdapterUtils.toJava(EnumIntegerTypeAdapter.class, PrimitiveUtils.readInteger(jacksonParser.getText(), null));
              }
            break;
            case "dataBoolean":
              // field dataBoolean (mapped with "dataBoolean")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                instance.dataBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
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
  public Bean87A_3 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Bean87A_3 instance = createInstance();
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
            case "attributeBoolean":
              // field attributeBoolean (mapped by "attributeBoolean")
              // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
              instance.attributeBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), null));
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
                  case "elementBoolean":
                    // property elementBoolean (mapped on "elementBoolean")
                    // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                    instance.elementBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null));
                  break;
                  case "elementEnum":
                    // property elementEnum (mapped on "elementEnum")
                    // using type adapter bind.kripton87TypeAdapter.EnumIntegerTypeAdapter
                    instance.elementEnum=TypeAdapterUtils.toJava(EnumIntegerTypeAdapter.class, PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null));
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
                  // property dataBoolean
                  // using type adapter bind.kripton87TypeAdapter.BooleanByteArrayTypeAdapter
                  instance.dataBoolean=TypeAdapterUtils.toJava(BooleanByteArrayTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getText(), null));
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
