package bind.kripton87TypeAdapter;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.Base64Utils;
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
 * This class is the shared preference binder defined for Bean87A_2
 *
 * @see Bean87A_2
 */
@BindMap(Bean87A_2.class)
public class Bean87A_2BindMap extends AbstractMapper<Bean87A_2> {
  /**
   * create new object instance
   */
  @Override
  public Bean87A_2 createInstance() {
    return new Bean87A_2();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Bean87A_2 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field attributeURL (mapped with "attributeURL")
      if (object.attributeURL!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("attributeURL", TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.attributeURL));
      }

      // field dataURL (mapped with "dataURL")
      if (object.dataURL!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("dataURL", TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.dataURL));
      }

      // field elementURL (mapped with "elementURL")
      if (object.elementURL!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("elementURL", TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.elementURL));
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Bean87A_2 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field attributeURL (mapped with "attributeURL")
      if (object.attributeURL!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("attributeURL", TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.attributeURL));
      }

      // field dataURL (mapped with "dataURL")
      if (object.dataURL!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("dataURL", TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.dataURL));
      }

      // field elementURL (mapped with "elementURL")
      if (object.elementURL!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("elementURL", TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.elementURL));
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
  public void serializeOnXml(KriptonXmlContext context, Bean87A_2 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("bean87A_2");
      }

      // Persisted fields:

      // field attributeURL (mapped with "attributeURL")
      // field trasformation byte[] bind.kripton87TypeAdapter.UrlByteArrayTypeAdapter 
      if (object.attributeURL!=null) {
        xmlSerializer.writeAttribute("attributeURL", Base64Utils.encode(TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.attributeURL)));
      }

      // field elementURL (mapped with "elementURL")
      // field trasformation byte[] bind.kripton87TypeAdapter.UrlByteArrayTypeAdapter 
      if (object.elementURL!=null) {
        xmlSerializer.writeStartElement("elementURL");
        xmlSerializer.writeBinary(TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.elementURL));
        xmlSerializer.writeEndElement();
      }

      // field dataURL (mapped with "dataURL")
      // field trasformation byte[] bind.kripton87TypeAdapter.UrlByteArrayTypeAdapter 
      if (object.dataURL!=null) {
        xmlSerializer.writeBinary(TypeAdapterUtils.toData(UrlByteArrayTypeAdapter.class, object.dataURL));
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
  public Bean87A_2 parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_2 instance = createInstance();
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
            case "attributeURL":
              // field attributeURL (mapped with "attributeURL")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.attributeURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, jacksonParser.getBinaryValue());
              }
            break;
            case "elementURL":
              // field elementURL (mapped with "elementURL")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.elementURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, jacksonParser.getBinaryValue());
              }
            break;
            case "dataURL":
              // field dataURL (mapped with "dataURL")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.dataURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, jacksonParser.getBinaryValue());
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
  public Bean87A_2 parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Bean87A_2 instance = createInstance();
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
            case "attributeURL":
              // field attributeURL (mapped with "attributeURL")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.attributeURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, Base64Utils.decode(jacksonParser.getValueAsString()));
              }
            break;
            case "elementURL":
              // field elementURL (mapped with "elementURL")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.elementURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, Base64Utils.decode(jacksonParser.getValueAsString()));
              }
            break;
            case "dataURL":
              // field dataURL (mapped with "dataURL")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.dataURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, Base64Utils.decode(jacksonParser.getValueAsString()));
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
  public Bean87A_2 parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Bean87A_2 instance = createInstance();
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
            case "attributeURL":
              // field attributeURL (mapped by "attributeURL")
              instance.attributeURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, Base64Utils.decode(xmlParser.getAttributeValue(attributeIndex)));
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
                  case "elementURL":
                    // property elementURL (mapped on "elementURL")
                    instance.elementURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, xmlParser.getElementAsBinary());
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
                  // property dataURL
                  instance.dataURL=TypeAdapterUtils.toJava(UrlByteArrayTypeAdapter.class, Base64Utils.decode(xmlParser.getText()));
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
