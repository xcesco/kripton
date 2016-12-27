package sqlite.test05firt_aid;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Override;

/**
 * This class is the shared preference binder defined for FirstAid
 *
 * @see FirstAid
 */
@BindMap(FirstAid.class)
public class FirstAidBindMap extends AbstractMapper<FirstAid> {
  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(FirstAid object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address", object.address);
    }

    // field address2 (mapped with "address2")
    if (object.address2!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address2", object.address2);
    }

    // field city (mapped with "city")
    if (object.city!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("city", object.city);
    }

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field greenAverageWaitingTime (mapped with "greenAverageWaitingTime")
    if (object.greenAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("greenAverageWaitingTime", object.greenAverageWaitingTime);
    }

    // field greenVisitingPatients (mapped with "greenVisitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("greenVisitingPatients", object.greenVisitingPatients);

    // field greenWaitingPatients (mapped with "greenWaitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("greenWaitingPatients", object.greenWaitingPatients);

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field info (mapped with "info")
    if (object.info!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("info", object.info);
    }

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

    // field phone (mapped with "phone")
    if (object.phone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("phone", object.phone);
    }

    // field redAverageWaitingTime (mapped with "redAverageWaitingTime")
    if (object.redAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("redAverageWaitingTime", object.redAverageWaitingTime);
    }

    // field redWaitingPatients (mapped with "redWaitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("redWaitingPatients", object.redWaitingPatients);

    // field totalPatientCount (mapped with "totalPatientCount")
    fieldCount++;
    jacksonSerializer.writeNumberField("totalPatientCount", object.totalPatientCount);

    // field uid (mapped with "uid")
    if (object.uid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.uid);
    }

    // field whiteAverageWaitingTime (mapped with "whiteAverageWaitingTime")
    if (object.whiteAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("whiteAverageWaitingTime", object.whiteAverageWaitingTime);
    }

    // field whiteVisitingPatients (mapped with "whiteVisitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("whiteVisitingPatients", object.whiteVisitingPatients);

    // field whiteWaitingPatients (mapped with "whiteWaitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("whiteWaitingPatients", object.whiteWaitingPatients);

    // field yellowAverageWaitingTime (mapped with "yellowAverageWaitingTime")
    if (object.yellowAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("yellowAverageWaitingTime", object.yellowAverageWaitingTime);
    }

    // field yellowVisitingPatients (mapped with "yellowVisitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("yellowVisitingPatients", object.yellowVisitingPatients);

    // field yellowWaitingPatients (mapped with "yellowWaitingPatients")
    fieldCount++;
    jacksonSerializer.writeNumberField("yellowWaitingPatients", object.yellowWaitingPatients);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(FirstAid object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address", object.address);
    }

    // field address2 (mapped with "address2")
    if (object.address2!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address2", object.address2);
    }

    // field city (mapped with "city")
    if (object.city!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("city", object.city);
    }

    // field description (mapped with "description")
    if (object.description!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("description", object.description);
    }

    // field greenAverageWaitingTime (mapped with "greenAverageWaitingTime")
    if (object.greenAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("greenAverageWaitingTime", object.greenAverageWaitingTime);
    }

    // field greenVisitingPatients (mapped with "greenVisitingPatients")
    jacksonSerializer.writeStringField("greenVisitingPatients", PrimitiveUtils.writeInteger(object.greenVisitingPatients));

    // field greenWaitingPatients (mapped with "greenWaitingPatients")
    jacksonSerializer.writeStringField("greenWaitingPatients", PrimitiveUtils.writeInteger(object.greenWaitingPatients));

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field info (mapped with "info")
    if (object.info!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("info", object.info);
    }

    // field latitude (mapped with "latitude")
    if (object.latitude!=null)  {
      jacksonSerializer.writeStringField("latitude", PrimitiveUtils.writeFloat(object.latitude));
    }

    // field longitude (mapped with "longitude")
    if (object.longitude!=null)  {
      jacksonSerializer.writeStringField("longitude", PrimitiveUtils.writeFloat(object.longitude));
    }

    // field phone (mapped with "phone")
    if (object.phone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("phone", object.phone);
    }

    // field redAverageWaitingTime (mapped with "redAverageWaitingTime")
    if (object.redAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("redAverageWaitingTime", object.redAverageWaitingTime);
    }

    // field redWaitingPatients (mapped with "redWaitingPatients")
    jacksonSerializer.writeStringField("redWaitingPatients", PrimitiveUtils.writeInteger(object.redWaitingPatients));

    // field totalPatientCount (mapped with "totalPatientCount")
    jacksonSerializer.writeStringField("totalPatientCount", PrimitiveUtils.writeInteger(object.totalPatientCount));

    // field uid (mapped with "uid")
    if (object.uid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("uid", object.uid);
    }

    // field whiteAverageWaitingTime (mapped with "whiteAverageWaitingTime")
    if (object.whiteAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("whiteAverageWaitingTime", object.whiteAverageWaitingTime);
    }

    // field whiteVisitingPatients (mapped with "whiteVisitingPatients")
    jacksonSerializer.writeStringField("whiteVisitingPatients", PrimitiveUtils.writeInteger(object.whiteVisitingPatients));

    // field whiteWaitingPatients (mapped with "whiteWaitingPatients")
    jacksonSerializer.writeStringField("whiteWaitingPatients", PrimitiveUtils.writeInteger(object.whiteWaitingPatients));

    // field yellowAverageWaitingTime (mapped with "yellowAverageWaitingTime")
    if (object.yellowAverageWaitingTime!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("yellowAverageWaitingTime", object.yellowAverageWaitingTime);
    }

    // field yellowVisitingPatients (mapped with "yellowVisitingPatients")
    jacksonSerializer.writeStringField("yellowVisitingPatients", PrimitiveUtils.writeInteger(object.yellowVisitingPatients));

    // field yellowWaitingPatients (mapped with "yellowWaitingPatients")
    jacksonSerializer.writeStringField("yellowWaitingPatients", PrimitiveUtils.writeInteger(object.yellowWaitingPatients));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(FirstAid object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("firstAid");
    }

    // Persisted fields:

    // field address (mapped with "address")
    if (object.address!=null) {
      xmlSerializer.writeStartElement("address");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.address));
      xmlSerializer.writeEndElement();
    }

    // field address2 (mapped with "address2")
    if (object.address2!=null) {
      xmlSerializer.writeStartElement("address2");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.address2));
      xmlSerializer.writeEndElement();
    }

    // field city (mapped with "city")
    if (object.city!=null) {
      xmlSerializer.writeStartElement("city");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.city));
      xmlSerializer.writeEndElement();
    }

    // field description (mapped with "description")
    if (object.description!=null) {
      xmlSerializer.writeStartElement("description");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.description));
      xmlSerializer.writeEndElement();
    }

    // field greenAverageWaitingTime (mapped with "greenAverageWaitingTime")
    if (object.greenAverageWaitingTime!=null) {
      xmlSerializer.writeStartElement("greenAverageWaitingTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.greenAverageWaitingTime));
      xmlSerializer.writeEndElement();
    }

    // field greenVisitingPatients (mapped with "greenVisitingPatients")
    xmlSerializer.writeStartElement("greenVisitingPatients");
    xmlSerializer.writeInt(object.greenVisitingPatients);
    xmlSerializer.writeEndElement();

    // field greenWaitingPatients (mapped with "greenWaitingPatients")
    xmlSerializer.writeStartElement("greenWaitingPatients");
    xmlSerializer.writeInt(object.greenWaitingPatients);
    xmlSerializer.writeEndElement();

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field info (mapped with "info")
    if (object.info!=null) {
      xmlSerializer.writeStartElement("info");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.info));
      xmlSerializer.writeEndElement();
    }

    // field latitude (mapped with "latitude")
    if (object.latitude!=null)  {
      xmlSerializer.writeStartElement("latitude");
      xmlSerializer.writeFloat(object.latitude);
      xmlSerializer.writeEndElement();
    }

    // field longitude (mapped with "longitude")
    if (object.longitude!=null)  {
      xmlSerializer.writeStartElement("longitude");
      xmlSerializer.writeFloat(object.longitude);
      xmlSerializer.writeEndElement();
    }

    // field phone (mapped with "phone")
    if (object.phone!=null) {
      xmlSerializer.writeStartElement("phone");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.phone));
      xmlSerializer.writeEndElement();
    }

    // field redAverageWaitingTime (mapped with "redAverageWaitingTime")
    if (object.redAverageWaitingTime!=null) {
      xmlSerializer.writeStartElement("redAverageWaitingTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.redAverageWaitingTime));
      xmlSerializer.writeEndElement();
    }

    // field redWaitingPatients (mapped with "redWaitingPatients")
    xmlSerializer.writeStartElement("redWaitingPatients");
    xmlSerializer.writeInt(object.redWaitingPatients);
    xmlSerializer.writeEndElement();

    // field totalPatientCount (mapped with "totalPatientCount")
    xmlSerializer.writeStartElement("totalPatientCount");
    xmlSerializer.writeInt(object.totalPatientCount);
    xmlSerializer.writeEndElement();

    // field uid (mapped with "uid")
    if (object.uid!=null) {
      xmlSerializer.writeStartElement("uid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.uid));
      xmlSerializer.writeEndElement();
    }

    // field whiteAverageWaitingTime (mapped with "whiteAverageWaitingTime")
    if (object.whiteAverageWaitingTime!=null) {
      xmlSerializer.writeStartElement("whiteAverageWaitingTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.whiteAverageWaitingTime));
      xmlSerializer.writeEndElement();
    }

    // field whiteVisitingPatients (mapped with "whiteVisitingPatients")
    xmlSerializer.writeStartElement("whiteVisitingPatients");
    xmlSerializer.writeInt(object.whiteVisitingPatients);
    xmlSerializer.writeEndElement();

    // field whiteWaitingPatients (mapped with "whiteWaitingPatients")
    xmlSerializer.writeStartElement("whiteWaitingPatients");
    xmlSerializer.writeInt(object.whiteWaitingPatients);
    xmlSerializer.writeEndElement();

    // field yellowAverageWaitingTime (mapped with "yellowAverageWaitingTime")
    if (object.yellowAverageWaitingTime!=null) {
      xmlSerializer.writeStartElement("yellowAverageWaitingTime");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.yellowAverageWaitingTime));
      xmlSerializer.writeEndElement();
    }

    // field yellowVisitingPatients (mapped with "yellowVisitingPatients")
    xmlSerializer.writeStartElement("yellowVisitingPatients");
    xmlSerializer.writeInt(object.yellowVisitingPatients);
    xmlSerializer.writeEndElement();

    // field yellowWaitingPatients (mapped with "yellowWaitingPatients")
    xmlSerializer.writeStartElement("yellowWaitingPatients");
    xmlSerializer.writeInt(object.yellowWaitingPatients);
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * create new object instance
   */
  @Override
  public FirstAid parseOnJackson(JsonParser jacksonParser) throws Exception {
    FirstAid instance = new FirstAid();
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
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address=jacksonParser.getText();
            }
          break;
          case "address2":
            // field address2 (mapped with "address2")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address2=jacksonParser.getText();
            }
          break;
          case "city":
            // field city (mapped with "city")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.city=jacksonParser.getText();
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "greenAverageWaitingTime":
            // field greenAverageWaitingTime (mapped with "greenAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.greenAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "greenVisitingPatients":
            // field greenVisitingPatients (mapped with "greenVisitingPatients")
            instance.greenVisitingPatients=jacksonParser.getIntValue();
          break;
          case "greenWaitingPatients":
            // field greenWaitingPatients (mapped with "greenWaitingPatients")
            instance.greenWaitingPatients=jacksonParser.getIntValue();
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "info":
            // field info (mapped with "info")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.info=jacksonParser.getText();
            }
          break;
          case "latitude":
            // field latitude (mapped with "latitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.latitude=jacksonParser.getFloatValue();
            }
          break;
          case "longitude":
            // field longitude (mapped with "longitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.longitude=jacksonParser.getFloatValue();
            }
          break;
          case "phone":
            // field phone (mapped with "phone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.phone=jacksonParser.getText();
            }
          break;
          case "redAverageWaitingTime":
            // field redAverageWaitingTime (mapped with "redAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.redAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "redWaitingPatients":
            // field redWaitingPatients (mapped with "redWaitingPatients")
            instance.redWaitingPatients=jacksonParser.getIntValue();
          break;
          case "totalPatientCount":
            // field totalPatientCount (mapped with "totalPatientCount")
            instance.totalPatientCount=jacksonParser.getIntValue();
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.uid=jacksonParser.getText();
            }
          break;
          case "whiteAverageWaitingTime":
            // field whiteAverageWaitingTime (mapped with "whiteAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.whiteAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "whiteVisitingPatients":
            // field whiteVisitingPatients (mapped with "whiteVisitingPatients")
            instance.whiteVisitingPatients=jacksonParser.getIntValue();
          break;
          case "whiteWaitingPatients":
            // field whiteWaitingPatients (mapped with "whiteWaitingPatients")
            instance.whiteWaitingPatients=jacksonParser.getIntValue();
          break;
          case "yellowAverageWaitingTime":
            // field yellowAverageWaitingTime (mapped with "yellowAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.yellowAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "yellowVisitingPatients":
            // field yellowVisitingPatients (mapped with "yellowVisitingPatients")
            instance.yellowVisitingPatients=jacksonParser.getIntValue();
          break;
          case "yellowWaitingPatients":
            // field yellowWaitingPatients (mapped with "yellowWaitingPatients")
            instance.yellowWaitingPatients=jacksonParser.getIntValue();
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
   */
  @Override
  public FirstAid parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    FirstAid instance = new FirstAid();
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
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address=jacksonParser.getText();
            }
          break;
          case "address2":
            // field address2 (mapped with "address2")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address2=jacksonParser.getText();
            }
          break;
          case "city":
            // field city (mapped with "city")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.city=jacksonParser.getText();
            }
          break;
          case "description":
            // field description (mapped with "description")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.description=jacksonParser.getText();
            }
          break;
          case "greenAverageWaitingTime":
            // field greenAverageWaitingTime (mapped with "greenAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.greenAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "greenVisitingPatients":
            // field greenVisitingPatients (mapped with "greenVisitingPatients")
            instance.greenVisitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "greenWaitingPatients":
            // field greenWaitingPatients (mapped with "greenWaitingPatients")
            instance.greenWaitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "info":
            // field info (mapped with "info")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.info=jacksonParser.getText();
            }
          break;
          case "latitude":
            // field latitude (mapped with "latitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.latitude=PrimitiveUtils.readFloat(jacksonParser.getText(), null);
            }
          break;
          case "longitude":
            // field longitude (mapped with "longitude")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.longitude=PrimitiveUtils.readFloat(jacksonParser.getText(), null);
            }
          break;
          case "phone":
            // field phone (mapped with "phone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.phone=jacksonParser.getText();
            }
          break;
          case "redAverageWaitingTime":
            // field redAverageWaitingTime (mapped with "redAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.redAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "redWaitingPatients":
            // field redWaitingPatients (mapped with "redWaitingPatients")
            instance.redWaitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "totalPatientCount":
            // field totalPatientCount (mapped with "totalPatientCount")
            instance.totalPatientCount=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "uid":
            // field uid (mapped with "uid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.uid=jacksonParser.getText();
            }
          break;
          case "whiteAverageWaitingTime":
            // field whiteAverageWaitingTime (mapped with "whiteAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.whiteAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "whiteVisitingPatients":
            // field whiteVisitingPatients (mapped with "whiteVisitingPatients")
            instance.whiteVisitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "whiteWaitingPatients":
            // field whiteWaitingPatients (mapped with "whiteWaitingPatients")
            instance.whiteWaitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "yellowAverageWaitingTime":
            // field yellowAverageWaitingTime (mapped with "yellowAverageWaitingTime")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.yellowAverageWaitingTime=jacksonParser.getText();
            }
          break;
          case "yellowVisitingPatients":
            // field yellowVisitingPatients (mapped with "yellowVisitingPatients")
            instance.yellowVisitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "yellowWaitingPatients":
            // field yellowWaitingPatients (mapped with "yellowWaitingPatients")
            instance.yellowWaitingPatients=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * create new object instance
   */
  @Override
  public FirstAid parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    FirstAid instance = new FirstAid();
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
                case "address":
                  // property address (mapped on "address")
                  instance.address=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "address2":
                  // property address2 (mapped on "address2")
                  instance.address2=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "city":
                  // property city (mapped on "city")
                  instance.city=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "description":
                  // property description (mapped on "description")
                  instance.description=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "greenAverageWaitingTime":
                  // property greenAverageWaitingTime (mapped on "greenAverageWaitingTime")
                  instance.greenAverageWaitingTime=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "greenVisitingPatients":
                  // property greenVisitingPatients (mapped on "greenVisitingPatients")
                  instance.greenVisitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "greenWaitingPatients":
                  // property greenWaitingPatients (mapped on "greenWaitingPatients")
                  instance.greenWaitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "info":
                  // property info (mapped on "info")
                  instance.info=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "latitude":
                  // property latitude (mapped on "latitude")
                  instance.latitude=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                break;
                case "longitude":
                  // property longitude (mapped on "longitude")
                  instance.longitude=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), null);
                break;
                case "phone":
                  // property phone (mapped on "phone")
                  instance.phone=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "redAverageWaitingTime":
                  // property redAverageWaitingTime (mapped on "redAverageWaitingTime")
                  instance.redAverageWaitingTime=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "redWaitingPatients":
                  // property redWaitingPatients (mapped on "redWaitingPatients")
                  instance.redWaitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "totalPatientCount":
                  // property totalPatientCount (mapped on "totalPatientCount")
                  instance.totalPatientCount=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "uid":
                  // property uid (mapped on "uid")
                  instance.uid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "whiteAverageWaitingTime":
                  // property whiteAverageWaitingTime (mapped on "whiteAverageWaitingTime")
                  instance.whiteAverageWaitingTime=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "whiteVisitingPatients":
                  // property whiteVisitingPatients (mapped on "whiteVisitingPatients")
                  instance.whiteVisitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "whiteWaitingPatients":
                  // property whiteWaitingPatients (mapped on "whiteWaitingPatients")
                  instance.whiteWaitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "yellowAverageWaitingTime":
                  // property yellowAverageWaitingTime (mapped on "yellowAverageWaitingTime")
                  instance.yellowAverageWaitingTime=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "yellowVisitingPatients":
                  // property yellowVisitingPatients (mapped on "yellowVisitingPatients")
                  instance.yellowVisitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "yellowWaitingPatients":
                  // property yellowWaitingPatients (mapped on "yellowWaitingPatients")
                  instance.yellowWaitingPatients=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
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
    }
  }
