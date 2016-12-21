package bind.kripton42faster;

import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
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
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for Restaurant
 *
 * @see Restaurant
 */
@BindMap(Restaurant.class)
public class RestaurantBindMap extends AbstractMapper<Restaurant> {
  /**
   * create new object instance
   */
  @Override
  public Restaurant createInstance() {
    return new Restaurant();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Restaurant object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field address (mapped with "address")
      if (object.address!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("address", object.address);
      }

      // field id (mapped with "id")
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.id);

      // field latitude (mapped with "latitude")
      if (object.latitude!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("latitude", object.latitude);
      }

      // field longitude (mapped with "longitude")
      if (object.longitude!=null)  {
        fieldCount++;
        jacksonSerializer.writeNumberField("longitude", object.longitude);
      }

      // field name (mapped with "name")
      if (object.name!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.name);
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Restaurant object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field address (mapped with "address")
      if (object.address!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("address", object.address);
      }

      // field id (mapped with "id")
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

      // field latitude (mapped with "latitude")
      if (object.latitude!=null)  {
        jacksonSerializer.writeStringField("latitude", PrimitiveUtils.writeDouble(object.latitude));
      }

      // field longitude (mapped with "longitude")
      if (object.longitude!=null)  {
        jacksonSerializer.writeStringField("longitude", PrimitiveUtils.writeDouble(object.longitude));
      }

      // field name (mapped with "name")
      if (object.name!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.name);
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
  public void serializeOnXml(KriptonXmlContext context, Restaurant object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("restaurant");
      }

      // Persisted fields:

      // field id (mapped with "id")
      xmlSerializer.writeAttribute("id", PrimitiveUtils.writeLong(object.id));

      // field latitude (mapped with "latitude")
      if (object.latitude!=null)  {
        xmlSerializer.writeAttribute("latitude", PrimitiveUtils.writeDouble(object.latitude));
      }

      // field longitude (mapped with "longitude")
      if (object.longitude!=null)  {
        xmlSerializer.writeAttribute("longitude", PrimitiveUtils.writeDouble(object.longitude));
      }

      // field name (mapped with "name")
      if (object.name!=null) {
        xmlSerializer.writeAttribute("name", StringEscapeUtils.escapeXml10(object.name));
      }

      // field address (mapped with "address")
      if (object.address!=null) {
        xmlSerializer.writeCData(StringEscapeUtils.escapeXml10(object.address));
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
  public Restaurant parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Restaurant instance = createInstance();
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
            case "id":
              // field id (mapped with "id")
              instance.id=jacksonParser.getLongValue();
            break;
            case "latitude":
              // field latitude (mapped with "latitude")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.latitude=jacksonParser.getDoubleValue();
              }
            break;
            case "longitude":
              // field longitude (mapped with "longitude")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.longitude=jacksonParser.getDoubleValue();
              }
            break;
            case "name":
              // field name (mapped with "name")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "address":
              // field address (mapped with "address")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.address=jacksonParser.getText();
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
  public Restaurant parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Restaurant instance = createInstance();
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
              // field id (mapped with "id")
              instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
            break;
            case "latitude":
              // field latitude (mapped with "latitude")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.latitude=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
              }
            break;
            case "longitude":
              // field longitude (mapped with "longitude")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.longitude=PrimitiveUtils.readDouble(jacksonParser.getText(), null);
              }
            break;
            case "name":
              // field name (mapped with "name")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.name=jacksonParser.getText();
              }
            break;
            case "address":
              // field address (mapped with "address")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.address=jacksonParser.getText();
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
  public Restaurant parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Restaurant instance = createInstance();
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
            case "id":
              // field id (mapped by "id")
              instance.id=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), 0L);
            break;
            case "latitude":
              // field latitude (mapped by "latitude")
              instance.latitude=PrimitiveUtils.readDouble(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "longitude":
              // field longitude (mapped by "longitude")
              instance.longitude=PrimitiveUtils.readDouble(xmlParser.getAttributeValue(attributeIndex), null);
            break;
            case "name":
              // field name (mapped by "name")
              instance.name=StringEscapeUtils.unescapeXml(xmlParser.getAttributeValue(attributeIndex));
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
              // No property to manage here
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
                // property address
                instance.address=StringEscapeUtils.unescapeXml(xmlParser.getText());
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
