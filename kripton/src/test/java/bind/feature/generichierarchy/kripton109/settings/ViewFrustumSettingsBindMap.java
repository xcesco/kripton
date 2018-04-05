package bind.feature.generichierarchy.kripton109.settings;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for ViewFrustumSettings.
 *
 * @see ViewFrustumSettings
 */
@BindMap(ViewFrustumSettings.class)
public class ViewFrustumSettingsBindMap extends AbstractMapper<ViewFrustumSettings> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(ViewFrustumSettings object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field align (mapped with "viewFrustumAlign")
    if (object.align!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("viewFrustumAlign", object.align.toString());
    }

    // field fieldOfView (mapped with "viewFrustumFieldOfView")
    fieldCount++;
    jacksonSerializer.writeNumberField("viewFrustumFieldOfView", object.fieldOfView);

    // field projection (mapped with "viewFrustumProjection")
    if (object.projection!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("viewFrustumProjection", object.projection.toString());
    }

    // field size (mapped with "viewFrustumSize")
    fieldCount++;
    jacksonSerializer.writeNumberField("viewFrustumSize", object.size);

    // field zFar (mapped with "viewFrustumZFar")
    fieldCount++;
    jacksonSerializer.writeNumberField("viewFrustumZFar", object.zFar);

    // field zNear (mapped with "viewFrustumZNear")
    fieldCount++;
    jacksonSerializer.writeNumberField("viewFrustumZNear", object.zNear);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(ViewFrustumSettings object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field align (mapped with "viewFrustumAlign")
    if (object.align!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("viewFrustumAlign", object.align.toString());
    }

    // field fieldOfView (mapped with "viewFrustumFieldOfView")
    jacksonSerializer.writeStringField("viewFrustumFieldOfView", PrimitiveUtils.writeFloat(object.fieldOfView));

    // field projection (mapped with "viewFrustumProjection")
    if (object.projection!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("viewFrustumProjection", object.projection.toString());
    }

    // field size (mapped with "viewFrustumSize")
    jacksonSerializer.writeStringField("viewFrustumSize", PrimitiveUtils.writeFloat(object.size));

    // field zFar (mapped with "viewFrustumZFar")
    jacksonSerializer.writeStringField("viewFrustumZFar", PrimitiveUtils.writeFloat(object.zFar));

    // field zNear (mapped with "viewFrustumZNear")
    jacksonSerializer.writeStringField("viewFrustumZNear", PrimitiveUtils.writeFloat(object.zNear));

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization.
   *
   * @param object the object
   * @param xmlSerializer the xml serializer
   * @param currentEventType the current event type
   * @throws Exception the exception
   */
  @Override
  public void serializeOnXml(ViewFrustumSettings object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("viewFrustumSettings");
    }

    // Persisted fields:

    // field align (mapped with "viewFrustumAlign")
    if (object.align!=null)  {
      xmlSerializer.writeStartElement("viewFrustumAlign");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.align.toString()));
      xmlSerializer.writeEndElement();
    }

    // field fieldOfView (mapped with "viewFrustumFieldOfView")
    xmlSerializer.writeStartElement("viewFrustumFieldOfView");
    xmlSerializer.writeFloat(object.fieldOfView);
    xmlSerializer.writeEndElement();

    // field projection (mapped with "viewFrustumProjection")
    if (object.projection!=null)  {
      xmlSerializer.writeStartElement("viewFrustumProjection");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.projection.toString()));
      xmlSerializer.writeEndElement();
    }

    // field size (mapped with "viewFrustumSize")
    xmlSerializer.writeStartElement("viewFrustumSize");
    xmlSerializer.writeFloat(object.size);
    xmlSerializer.writeEndElement();

    // field zFar (mapped with "viewFrustumZFar")
    xmlSerializer.writeStartElement("viewFrustumZFar");
    xmlSerializer.writeFloat(object.zFar);
    xmlSerializer.writeEndElement();

    // field zNear (mapped with "viewFrustumZNear")
    xmlSerializer.writeStartElement("viewFrustumZNear");
    xmlSerializer.writeFloat(object.zNear);
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the view frustum settings
   * @throws Exception the exception
   */
  @Override
  public ViewFrustumSettings parseOnJackson(JsonParser jacksonParser) throws Exception {
    ViewFrustumSettings instance = new ViewFrustumSettings();
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
          case "viewFrustumAlign":
            // field align (mapped with "viewFrustumAlign")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.align=StringUtils.hasText(tempEnum)?ViewFrustumAlignType.valueOf(tempEnum):null;
            }
          break;
          case "viewFrustumFieldOfView":
            // field fieldOfView (mapped with "viewFrustumFieldOfView")
            instance.fieldOfView=jacksonParser.getFloatValue();
          break;
          case "viewFrustumProjection":
            // field projection (mapped with "viewFrustumProjection")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.projection=StringUtils.hasText(tempEnum)?ProjectionType.valueOf(tempEnum):null;
            }
          break;
          case "viewFrustumSize":
            // field size (mapped with "viewFrustumSize")
            instance.size=jacksonParser.getFloatValue();
          break;
          case "viewFrustumZFar":
            // field zFar (mapped with "viewFrustumZFar")
            instance.zFar=jacksonParser.getFloatValue();
          break;
          case "viewFrustumZNear":
            // field zNear (mapped with "viewFrustumZNear")
            instance.zNear=jacksonParser.getFloatValue();
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the view frustum settings
   * @throws Exception the exception
   */
  @Override
  public ViewFrustumSettings parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ViewFrustumSettings instance = new ViewFrustumSettings();
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
          case "viewFrustumAlign":
            // field align (mapped with "viewFrustumAlign")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.align=StringUtils.hasText(tempEnum)?ViewFrustumAlignType.valueOf(tempEnum):null;
            }
          break;
          case "viewFrustumFieldOfView":
            // field fieldOfView (mapped with "viewFrustumFieldOfView")
            instance.fieldOfView=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "viewFrustumProjection":
            // field projection (mapped with "viewFrustumProjection")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              String tempEnum=jacksonParser.getText();
              instance.projection=StringUtils.hasText(tempEnum)?ProjectionType.valueOf(tempEnum):null;
            }
          break;
          case "viewFrustumSize":
            // field size (mapped with "viewFrustumSize")
            instance.size=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "viewFrustumZFar":
            // field zFar (mapped with "viewFrustumZFar")
            instance.zFar=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          case "viewFrustumZNear":
            // field zNear (mapped with "viewFrustumZNear")
            instance.zNear=PrimitiveUtils.readFloat(jacksonParser.getText(), 0f);
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml.
   *
   * @param xmlParser the xml parser
   * @param currentEventType the current event type
   * @return the view frustum settings
   * @throws Exception the exception
   */
  @Override
  public ViewFrustumSettings parseOnXml(XMLParser xmlParser, int currentEventType) throws
      Exception {
    ViewFrustumSettings instance = new ViewFrustumSettings();
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
                case "viewFrustumAlign":
                  // property align (mapped on "viewFrustumAlign")
                  instance.align=ViewFrustumAlignType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "viewFrustumFieldOfView":
                  // property fieldOfView (mapped on "viewFrustumFieldOfView")
                  instance.fieldOfView=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f);
                break;
                case "viewFrustumProjection":
                  // property projection (mapped on "viewFrustumProjection")
                  instance.projection=ProjectionType.valueOf(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                case "viewFrustumSize":
                  // property size (mapped on "viewFrustumSize")
                  instance.size=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f);
                break;
                case "viewFrustumZFar":
                  // property zFar (mapped on "viewFrustumZFar")
                  instance.zFar=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f);
                break;
                case "viewFrustumZNear":
                  // property zNear (mapped on "viewFrustumZNear")
                  instance.zNear=PrimitiveUtils.readFloat(xmlParser.getElementAsFloat(), 0f);
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
