package sqlite.kripton58.array2;

import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
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
import java.util.ArrayList;
import sqlite.kripton58.BeanInner;
import sqlite.kripton58.BeanInnerBindMap;

/**
 * This class is the shared preference binder defined for BeanBean
 *
 * @see BeanBean
 */
@BindMap(BeanBean.class)
public class BeanBeanBindMap extends AbstractMapper<BeanBean> {
  /**
   * BeanInnerBindMap */
  private BeanInnerBindMap beanInnerBindMap;

  /**
   * create new object instance
   */
  @Override
  public BeanBean createInstance() {
    return new BeanBean();
  }

  private BeanInnerBindMap beanInnerBindMap() {
    if (beanInnerBindMap==null) {
      beanInnerBindMap=AbstractContext.mapperFor(BeanInner.class);
    }
    return beanInnerBindMap;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, BeanBean object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id (mapped with "id")
      fieldCount++;
      jacksonSerializer.writeNumberField("id", object.getId());

      // field value (mapped with "value")
      if (object.getValue()!=null)  {
        fieldCount++;
        int n=object.getValue().length;
        BeanInner item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("value");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValue()[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            beanInnerBindMap().serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field value2 (mapped with "value2")
      if (object.getValue2()!=null)  {
        fieldCount++;
        int n=object.getValue2().length;
        BeanInner item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("value2");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.getValue2()[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            beanInnerBindMap().serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, BeanBean object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field id (mapped with "id")
      jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.getId()));

      // field value (mapped with "value")
      if (object.getValue()!=null)  {
        fieldCount++;
        int n=object.getValue().length;
        BeanInner item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("value");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.getValue()[i];
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              if (beanInnerBindMap().serializeOnJacksonAsString(context, item, wrapper)==0) {
                jacksonSerializer.writeNullField("value");
              }
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field value2 (mapped with "value2")
      if (object.getValue2()!=null)  {
        fieldCount++;
        int n=object.getValue2().length;
        BeanInner item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("value2");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.getValue2()[i];
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              if (beanInnerBindMap().serializeOnJacksonAsString(context, item, wrapper)==0) {
                jacksonSerializer.writeNullField("value2");
              }
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
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
  public void serializeOnXml(KriptonXmlContext context, BeanBean object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanBean");
      }

      // Persisted fields:

      // field id (mapped with "id")
      xmlSerializer.writeStartElement("id");
      xmlSerializer.writeLong(object.getId());
      xmlSerializer.writeEndElement();

      // field value (mapped with "value")
      if (object.getValue()!=null)  {
        int n=object.getValue().length;
        BeanInner item;
        for (int i=0; i<n; i++) {
          item=object.getValue()[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("value");
          } else {
            xmlSerializer.writeStartElement("value");
            beanInnerBindMap().serializeOnXml(context, item, wrapper, 2);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("value");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field value2 (mapped with "value2")
      if (object.getValue2()!=null)  {
        int n=object.getValue2().length;
        BeanInner item;
        for (int i=0; i<n; i++) {
          item=object.getValue2()[i];
          if (item==null) {
            xmlSerializer.writeEmptyElement("value2");
          } else {
            xmlSerializer.writeStartElement("value2");
            beanInnerBindMap().serializeOnXml(context, item, wrapper, 2);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("value2");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
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
  public BeanBean parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanBean instance = createInstance();
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
              instance.setId(jacksonParser.getLongValue());
            break;
            case "value":
              // field value (mapped with "value")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanInner> collection=new ArrayList<>();
                BeanInner item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=beanInnerBindMap().parseOnJackson(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.setValue(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
              }
            break;
            case "value2":
              // field value2 (mapped with "value2")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanInner> collection=new ArrayList<>();
                BeanInner item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=beanInnerBindMap().parseOnJackson(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.setValue2(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
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
  public BeanBean parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanBean instance = createInstance();
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
              instance.setId(PrimitiveUtils.readLong(jacksonParser.getText(), 0L));
            break;
            case "value":
              // field value (mapped with "value")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanInner> collection=new ArrayList<>();
                BeanInner item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=beanInnerBindMap().parseOnJacksonAsString(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.setValue(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<BeanInner> collection=new ArrayList<>();
                instance.setValue(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
              }
            break;
            case "value2":
              // field value2 (mapped with "value2")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<BeanInner> collection=new ArrayList<>();
                BeanInner item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=beanInnerBindMap().parseOnJacksonAsString(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.setValue2(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<BeanInner> collection=new ArrayList<>();
                instance.setValue2(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
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
  public BeanBean parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      BeanBean instance = createInstance();
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
                  case "id":
                    // property id (mapped on "id")
                    instance.setId(PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L));
                  break;
                  case "value":
                    // property value (mapped on "value")
                     {
                      ArrayList<BeanInner> collection=new ArrayList<>();
                      BeanInner item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=beanInnerBindMap().parseOnXml(context, wrapper, eventType);
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("value")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=beanInnerBindMap().parseOnXml(context, wrapper, eventType);
                        }
                        collection.add(item);
                      }
                      instance.setValue(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
                      read=false;
                    }
                  break;
                  case "value2":
                    // property value2 (mapped on "value2")
                     {
                      ArrayList<BeanInner> collection=new ArrayList<>();
                      BeanInner item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=beanInnerBindMap().parseOnXml(context, wrapper, eventType);
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("value2")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=beanInnerBindMap().parseOnXml(context, wrapper, eventType);
                        }
                        collection.add(item);
                      }
                      instance.setValue2(CollectionUtils.asArray(collection, new BeanInner[collection.size()]));
                      read=false;
                    }
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
