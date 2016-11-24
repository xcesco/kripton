package kripton71;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.common.BigDecimalUtil;
import com.abubusoft.kripton.common.BigIntegerUtil;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for BeanElement71
 *
 * @see BeanElement71
 */
@BindMap
public class BeanElement71BindMap extends AbstractMapper<BeanElement71> {
  /**
   * create new object instance
   */
  @Override
  public BeanElement71 createInstance() {
    return new BeanElement71();
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJackson(JacksonContext context, BeanElement71 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueBigDecimalList
      if (object.valueBigDecimalList!=null)  {
        int n=object.valueBigDecimalList.size();
        BigDecimal item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigDecimalList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigDecimalUtil.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigIntegerList
      if (object.valueBigIntegerList!=null)  {
        int n=object.valueBigIntegerList.size();
        BigInteger item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigIntegerList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigIntegerList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigIntegerUtil.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null)  {
        jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
      }

      jacksonSerializer.writeEndObject();
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnJacksonAsString(JacksonContext context, BeanElement71 object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();

      // Serialized Field:

      // field valueBigDecimalList
      if (object.valueBigDecimalList!=null)  {
        int n=object.valueBigDecimalList.size();
        BigDecimal item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigDecimalList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigDecimalUtil.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field valueBigIntegerList
      if (object.valueBigIntegerList!=null)  {
        int n=object.valueBigIntegerList.size();
        BigInteger item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBigIntegerList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.valueBigIntegerList.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigIntegerUtil.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null)  {
        jacksonSerializer.writeStringField("zalueStringFinal", object.zalueStringFinal);
      }

      jacksonSerializer.writeEndObject();
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(XmlBinderContext context, BeanElement71 object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("beanElement71");
      }

      // Persisted fields:

      // field valueBigDecimalList
      if (object.valueBigDecimalList!=null)  {
        int n=object.valueBigDecimalList.size();
        BigDecimal item;
        for (int i=0; i<n; i++) {
          item=object.valueBigDecimalList.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("valueBigDecimalList");
          } else {
            xmlSerializer.writeStartElement("valueBigDecimalList");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigDecimalUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
      }

      // field valueBigIntegerList
      if (object.valueBigIntegerList!=null)  {
        int n=object.valueBigIntegerList.size();
        BigInteger item;
        // write wrapper tag
        xmlSerializer.writeStartElement("valueBigIntegerList");
        for (int i=0; i<n; i++) {
          item=object.valueBigIntegerList.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("item");
          } else {
            xmlSerializer.writeStartElement("item");
            xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(BigIntegerUtil.write(item)));
            xmlSerializer.writeEndElement();
          }
        }
        xmlSerializer.writeEndElement();
      }

      // field zalueStringFinal
      if (object.zalueStringFinal!=null) {
        xmlSerializer.writeStartElement("zalueStringFinal");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.zalueStringFinal));
        xmlSerializer.writeEndElement();
      }

      if (currentEventType == 0) {
        xmlSerializer.writeEndElement();
      }
    } catch(XMLStreamException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanElement71 parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement71 instance = createInstance();
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
            case "valueBigDecimalList":
              // field valueBigDecimalList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigDecimal> collection=new LinkedList<>();
                BigDecimal item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=BigDecimalUtil.read(jacksonParser.getText());
                  }
                  collection.add(item);
                }
                instance.valueBigDecimalList=collection;
              }
            break;
            case "valueBigIntegerList":
              // field valueBigIntegerList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigInteger> collection=new LinkedList<>();
                BigInteger item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=BigIntegerUtil.read(jacksonParser.getText());
                  }
                  collection.add(item);
                }
                instance.valueBigIntegerList=collection;
              }
            break;
            case "zalueStringFinal":
              // field zalueStringFinal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.zalueStringFinal=jacksonParser.getText();
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanElement71 parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      BeanElement71 instance = createInstance();
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
            case "valueBigDecimalList":
              // field valueBigDecimalList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigDecimal> collection=new LinkedList<>();
                BigDecimal item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=BigDecimalUtil.read(jacksonParser.getText());
                  }
                  collection.add(item);
                }
                instance.valueBigDecimalList=collection;
              }
            break;
            case "valueBigIntegerList":
              // field valueBigIntegerList
              if (jacksonParser.getCurrentToken()==JsonToken.START_ARRAY) {
                LinkedList<BigInteger> collection=new LinkedList<>();
                BigInteger item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.getCurrentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=BigIntegerUtil.read(jacksonParser.getText());
                  }
                  collection.add(item);
                }
                instance.valueBigIntegerList=collection;
              }
            break;
            case "zalueStringFinal":
              // field zalueStringFinal
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.zalueStringFinal=jacksonParser.getText();
              }
            break;
            default:
              jacksonParser.skipChildren();
            break;}
      }
      return instance;
    } catch (IOException e) {
      e.printStackTrace();
      throw new KriptonRuntimeException(e);
    }
  }

  /**
   * create new object instance
   */
  @Override
  public BeanElement71 parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      BeanElement71 instance = createInstance();
      int eventType = currentEventType;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      }
      String currentTag = xmlParser.getName().toString();
      Stack<String> elementNameStack = new Stack<>();
      elementNameStack.push(currentTag);
      // No attributes found

      //sub-elements
      while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
        eventType = xmlParser.next();
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "valueBigDecimalList":
                    // property valueBigDecimalList
                     {
                      LinkedList<BigDecimal> collection=new LinkedList<>();
                      BigDecimal item;
                      // add first element
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.skipElement();
                      } else {
                        item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                      }
                      collection.add(item);
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && xmlParser.getName().toString().equals("valueBigDecimalList")) {
                        if (xmlParser.getName().toString().equals("valueBigDecimalList")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                          } else {
                            item=BigDecimalUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueBigDecimalList=collection;
                    }
                  break;
                  case "valueBigIntegerList":
                    // property valueBigIntegerList
                     {
                      LinkedList<BigInteger> collection=new LinkedList<>();
                      BigInteger item;
                      while (xmlParser.nextTag() != XMLEvent.END_ELEMENT && !xmlParser.getName().toString().equals("valueBigIntegerList")) {
                        if (xmlParser.getName().toString().equals("item")) {
                          if (xmlParser.isEmptyElement()) {
                            item=null;
                            xmlParser.skipElement();
                          } else {
                            item=BigIntegerUtil.read(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                          }
                          collection.add(item);
                        }
                      }
                      instance.valueBigIntegerList=collection;
                    }
                  break;
                  case "zalueStringFinal":
                    // property zalueStringFinal
                    instance.zalueStringFinal=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  default:
                    xmlParser.skipElement();
                  break;
                }
              break;
              case XMLEvent.END_ELEMENT:
                currentTag = elementNameStack.pop();
              break;
              case XMLEvent.CDATA:
              case XMLEvent.CHARACTERS:
                // no property is binded to VALUE o CDATA break;
              default:
              break;
          }
        }
        return instance;
      } catch(XMLStreamException e) {
        e.printStackTrace();
        throw (new KriptonRuntimeException(e));
      }
    }
  }
