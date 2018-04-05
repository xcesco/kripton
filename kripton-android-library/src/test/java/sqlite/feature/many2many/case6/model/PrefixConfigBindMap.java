/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.many2many.case6.model;

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

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for PrefixConfig.
 *
 * @see PrefixConfig
 */
@BindMap(PrefixConfig.class)
public class PrefixConfigBindMap extends AbstractMapper<PrefixConfig> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(PrefixConfig object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field defaultCountry (mapped with "defaultCountry")
    if (object.defaultCountry!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("defaultCountry", object.defaultCountry);
    }

    // field dialogTimeout (mapped with "dialogTimeout")
    fieldCount++;
    jacksonSerializer.writeNumberField("dialogTimeout", object.dialogTimeout);

    // field dualBillingPrefix (mapped with "dualBillingPrefix")
    if (object.dualBillingPrefix!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dualBillingPrefix", object.dualBillingPrefix);
    }

    // field enabled (mapped with "enabled")
    fieldCount++;
    jacksonSerializer.writeBooleanField("enabled", object.enabled);

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(PrefixConfig object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field defaultCountry (mapped with "defaultCountry")
    if (object.defaultCountry!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("defaultCountry", object.defaultCountry);
    }

    // field dialogTimeout (mapped with "dialogTimeout")
    jacksonSerializer.writeStringField("dialogTimeout", PrimitiveUtils.writeLong(object.dialogTimeout));

    // field dualBillingPrefix (mapped with "dualBillingPrefix")
    if (object.dualBillingPrefix!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("dualBillingPrefix", object.dualBillingPrefix);
    }

    // field enabled (mapped with "enabled")
    jacksonSerializer.writeStringField("enabled", PrimitiveUtils.writeBoolean(object.enabled));

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

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
  public void serializeOnXml(PrefixConfig object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("prefixConfig");
    }

    // Persisted fields:

    // field defaultCountry (mapped with "defaultCountry")
    if (object.defaultCountry!=null) {
      xmlSerializer.writeStartElement("defaultCountry");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.defaultCountry));
      xmlSerializer.writeEndElement();
    }

    // field dialogTimeout (mapped with "dialogTimeout")
    xmlSerializer.writeStartElement("dialogTimeout");
    xmlSerializer.writeLong(object.dialogTimeout);
    xmlSerializer.writeEndElement();

    // field dualBillingPrefix (mapped with "dualBillingPrefix")
    if (object.dualBillingPrefix!=null) {
      xmlSerializer.writeStartElement("dualBillingPrefix");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.dualBillingPrefix));
      xmlSerializer.writeEndElement();
    }

    // field enabled (mapped with "enabled")
    xmlSerializer.writeStartElement("enabled");
    xmlSerializer.writeBoolean(object.enabled);
    xmlSerializer.writeEndElement();

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the prefix config
   * @throws Exception the exception
   */
  @Override
  public PrefixConfig parseOnJackson(JsonParser jacksonParser) throws Exception {
    PrefixConfig instance = new PrefixConfig();
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
          case "defaultCountry":
            // field defaultCountry (mapped with "defaultCountry")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.defaultCountry=jacksonParser.getText();
            }
          break;
          case "dialogTimeout":
            // field dialogTimeout (mapped with "dialogTimeout")
            instance.dialogTimeout=jacksonParser.getLongValue();
          break;
          case "dualBillingPrefix":
            // field dualBillingPrefix (mapped with "dualBillingPrefix")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.dualBillingPrefix=jacksonParser.getText();
            }
          break;
          case "enabled":
            // field enabled (mapped with "enabled")
            instance.enabled=jacksonParser.getBooleanValue();
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
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
   * @return the prefix config
   * @throws Exception the exception
   */
  @Override
  public PrefixConfig parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    PrefixConfig instance = new PrefixConfig();
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
          case "defaultCountry":
            // field defaultCountry (mapped with "defaultCountry")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.defaultCountry=jacksonParser.getText();
            }
          break;
          case "dialogTimeout":
            // field dialogTimeout (mapped with "dialogTimeout")
            instance.dialogTimeout=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "dualBillingPrefix":
            // field dualBillingPrefix (mapped with "dualBillingPrefix")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.dualBillingPrefix=jacksonParser.getText();
            }
          break;
          case "enabled":
            // field enabled (mapped with "enabled")
            instance.enabled=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
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
   * @return the prefix config
   * @throws Exception the exception
   */
  @Override
  public PrefixConfig parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    PrefixConfig instance = new PrefixConfig();
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
                case "defaultCountry":
                  // property defaultCountry (mapped on "defaultCountry")
                  instance.defaultCountry=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "dialogTimeout":
                  // property dialogTimeout (mapped on "dialogTimeout")
                  instance.dialogTimeout=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "dualBillingPrefix":
                  // property dualBillingPrefix (mapped on "dualBillingPrefix")
                  instance.dualBillingPrefix=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "enabled":
                  // property enabled (mapped on "enabled")
                  instance.enabled=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
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
