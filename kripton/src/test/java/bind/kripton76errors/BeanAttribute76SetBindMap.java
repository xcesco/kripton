/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.kripton76errors;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Byte;
import java.lang.Exception;
import java.lang.Override;
import java.util.HashSet;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for BeanAttribute76Set.
 *
 * @see BeanAttribute76Set
 */
@BindMap(BeanAttribute76Set.class)
public class BeanAttribute76SetBindMap extends AbstractMapper<BeanAttribute76Set> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(BeanAttribute76Set object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueByteSet (mapped with "valueByteSet")
    if (object.valueByteSet!=null)  {
      fieldCount++;
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueByteSet");
      jacksonSerializer.writeStartArray();
      for (Byte item: object.valueByteSet) {
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(BeanAttribute76Set object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueByteSet (mapped with "valueByteSet")
    if (object.valueByteSet!=null)  {
      fieldCount++;
      int n=object.valueByteSet.size();
      // write wrapper tag
      jacksonSerializer.writeFieldName("valueByteSet");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (Byte item: object.valueByteSet) {
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeByte(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

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
  public void serializeOnXml(BeanAttribute76Set object, XMLSerializer xmlSerializer, int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("root");
    }

    // Persisted fields:

    // field valueByteSet (mapped with "valueByteSet")
    if (object.valueByteSet!=null)  {
      int n=object.valueByteSet.size();
      for (Byte item: object.valueByteSet) {
        if (item==null) {
          xmlSerializer.writeEmptyElement("valueByteSet");
        } else {
          xmlSerializer.writeInt(item);
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("valueByteSet");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the bean attribute 76 set
   * @throws Exception the exception
   */
  @Override
  public BeanAttribute76Set parseOnJackson(JsonParser jacksonParser) throws Exception {
    BeanAttribute76Set instance = new BeanAttribute76Set();
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
          case "valueByteSet":
            // field valueByteSet (mapped with "valueByteSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<Byte> collection=new HashSet<>();
              Byte item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getByteValue();
                }
                collection.add(item);
              }
              instance.valueByteSet=collection;
            }
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
   * @return the bean attribute 76 set
   * @throws Exception the exception
   */
  @Override
  public BeanAttribute76Set parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    BeanAttribute76Set instance = new BeanAttribute76Set();
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
          case "valueByteSet":
            // field valueByteSet (mapped with "valueByteSet")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              HashSet<Byte> collection=new HashSet<>();
              Byte item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readByte(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.valueByteSet=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              HashSet<Byte> collection=new HashSet<>();
              instance.valueByteSet=collection;
            }
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
   * @return the bean attribute 76 set
   * @throws Exception the exception
   */
  @Override
  public BeanAttribute76Set parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    BeanAttribute76Set instance = new BeanAttribute76Set();
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
              // property valueByteSet
               {
                HashSet<Byte> collection=new HashSet<>();
                Byte item;
                // add first element
                item=null;
                if (xmlParser.isEmptyElement()) {
                  // if there's a an empty collection it marked with attribute emptyCollection
                  if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                    collection.add(item);
                  }
                  xmlParser.nextTag();
                } else {
                  item=(byte)PrimitiveUtils.readByte(xmlParser.getText(), null);
                  collection.add(item);
                }
                while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("valueByteSet")) {
                  if (xmlParser.isEmptyElement()) {
                    item=null;
                    xmlParser.nextTag();
                  } else {
                    item=(byte)PrimitiveUtils.readByte(xmlParser.getText(), null);
                  }
                  collection.add(item);
                }
                instance.valueByteSet=collection;
                read=false;
              }
            }
          break;
          default:
          break;
      }
    }
    return instance;
  }
}
