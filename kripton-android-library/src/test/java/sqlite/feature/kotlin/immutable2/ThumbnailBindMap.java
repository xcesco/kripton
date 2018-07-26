package sqlite.feature.kotlin.immutable2;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.net.URL;

/**
 * This class is binder map for Thumbnail
 *
 * @see Thumbnail
 */
@BindMap(Thumbnail.class)
public class ThumbnailBindMap extends AbstractMapper<Thumbnail> {
  @Override
  public int serializeOnJackson(Thumbnail object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field height (mapped with "height")
    fieldCount++;
    jacksonSerializer.writeNumberField("height", object.getHeight());

    // field url (mapped with "url")
    if (object.getUrl()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", UrlUtils.write(object.getUrl()));
    }

    // field width (mapped with "width")
    fieldCount++;
    jacksonSerializer.writeNumberField("width", object.getWidth());

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(Thumbnail object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field height (mapped with "height")
    jacksonSerializer.writeStringField("height", PrimitiveUtils.writeLong(object.getHeight()));

    // field url (mapped with "url")
    if (object.getUrl()!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("url", UrlUtils.write(object.getUrl()));
    }

    // field width (mapped with "width")
    jacksonSerializer.writeStringField("width", PrimitiveUtils.writeLong(object.getWidth()));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(Thumbnail object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("thumbnail");
    }

    // Persisted fields:

    // field height (mapped with "height")
    xmlSerializer.writeAttribute("height", PrimitiveUtils.writeLong(object.getHeight()));

    // field url (mapped with "url")
    if (object.getUrl()!=null)  {
      xmlSerializer.writeAttribute("url", UrlUtils.write(object.getUrl()));
    }

    // field width (mapped with "width")
    xmlSerializer.writeAttribute("width", PrimitiveUtils.writeLong(object.getWidth()));

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public Thumbnail parseOnJackson(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __width=0;
    long __height=0;
    URL __url=null;
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Thumbnail instance=new Thumbnail(__width,__height,__url);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "height":
            // field height (mapped with "height")
            __height=jacksonParser.getLongValue();
          break;
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __url=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "width":
            // field width (mapped with "width")
            __width=jacksonParser.getLongValue();
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Thumbnail instance=new Thumbnail(__width,__height,__url);
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public Thumbnail parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __width=0;
    long __height=0;
    URL __url=null;
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      // immutable object: inizialize object
      Thumbnail instance=new Thumbnail(__width,__height,__url);
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "height":
            // field height (mapped with "height")
            __height=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "url":
            // field url (mapped with "url")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              __url=UrlUtils.read(jacksonParser.getText());
            }
          break;
          case "width":
            // field width (mapped with "width")
            __width=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    // immutable object: inizialize object
    Thumbnail instance=new Thumbnail(__width,__height,__url);
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public Thumbnail parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    // immutable object: initialize temporary variables for properties
    long __width=0;
    long __height=0;
    URL __url=null;
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
          case "height":
            // field height (mapped by "height")
            __height=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), 0L);
          break;
          case "url":
            // field url (mapped by "url")
            __url=UrlUtils.read(xmlParser.getAttributeValue(attributeIndex));
          break;
          case "width":
            // field width (mapped by "width")
            __width=PrimitiveUtils.readLong(xmlParser.getAttributeValue(attributeIndex), 0L);
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
            // no property is binded to VALUE o CDATA break;
          default:
          break;
      }
    }
    // immutable object: inizialize object
    Thumbnail instance=new Thumbnail(__width,__height,__url);
    return instance;
  }
}
