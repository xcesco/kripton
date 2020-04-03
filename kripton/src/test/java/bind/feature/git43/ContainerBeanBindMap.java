package bind.feature.git43;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;

/**
 * This class is binder map for ContainerBean
 *
 * @see ContainerBean
 */
@BindMap(ContainerBean.class)
public class ContainerBeanBindMap extends AbstractMapper<ContainerBean> {
  /**
   * Bean01BindMap */
  private Bean01BindMap bean01BindMap = BinderUtils.mapperFor(Bean01.class);

  @Override
  public int serializeOnJackson(ContainerBean object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field elements (mapped with "elements")
    if (object.elements!=null)  {
      fieldCount++;
      int n=object.elements.size();
      Bean01 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("elements");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.elements.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          bean01BindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(ContainerBean object, JsonGenerator jacksonSerializer)
      throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field elements (mapped with "elements")
    if (object.elements!=null)  {
      fieldCount++;
      int n=object.elements.size();
      Bean01 item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("elements");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.elements.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (bean01BindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("elements");
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
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(ContainerBean object, XMLSerializer xmlSerializer,
      int currentEventType) throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("containerBean");
    }

    // Persisted fields:

    // field elements (mapped with "elements")
    if (object.elements!=null)  {
      int n=object.elements.size();
      Bean01 item;
      for (int i=0; i<n; i++) {
        item=object.elements.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("elements");
        } else {
          xmlSerializer.writeStartElement("elements");
          bean01BindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("elements");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public ContainerBean parseOnJackson(JsonParser jacksonParser) throws Exception {
    ContainerBean instance = new ContainerBean();
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
          case "elements":
            // field elements (mapped with "elements")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean01> collection=new ArrayList<>();
              Bean01 item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=bean01BindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.elements=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson
   */
  @Override
  public ContainerBean parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    ContainerBean instance = new ContainerBean();
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
          case "elements":
            // field elements (mapped with "elements")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Bean01> collection=new ArrayList<>();
              Bean01 item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=bean01BindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.elements=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Bean01> collection=new ArrayList<>();
              instance.elements=collection;
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml
   */
  @Override
  public ContainerBean parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    ContainerBean instance = new ContainerBean();
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
                case "elements":
                  // property elements (mapped on "elements")
                   {
                    ArrayList<Bean01> collection=new ArrayList<>();
                    Bean01 item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=bean01BindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("elements")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=bean01BindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.elements=collection;
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
    }
  }
