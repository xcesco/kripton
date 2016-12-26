package commons.benchmark.model;

import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.AbstractJacksonContext;
import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.KriptonXmlContext;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.XmlAttributeUtils;
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
import java.util.ArrayList;

/**
 * This class is the shared preference binder defined for Response
 *
 * @see Response
 */
@BindMap(Response.class)
public class ResponseBindMap extends AbstractMapper<Response> {
  /**
   * UserBindMap */
  private UserBindMap userBindMap;

  /**
   * create new object instance
   */
  @Override
  public Response createInstance() {
    return new Response();
  }

  private UserBindMap userBindMap() {
    if (userBindMap==null) {
      userBindMap=AbstractContext.mapperFor(User.class);
    }
    return userBindMap;
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(AbstractJacksonContext context, Response object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field status (mapped with "status")
      if (object.status!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("status", object.status);
      }

      // field users (mapped with "users")
      if (object.users!=null)  {
        fieldCount++;
        int n=object.users.size();
        User item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("users");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.users.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            userBindMap().serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
      }

      // field isRealJson (mapped with "is_real_json")
      fieldCount++;
      jacksonSerializer.writeBooleanField("is_real_json", object.isRealJson);

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
  public int serializeOnJacksonAsString(AbstractJacksonContext context, Response object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field status (mapped with "status")
      if (object.status!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("status", object.status);
      }

      // field users (mapped with "users")
      if (object.users!=null)  {
        fieldCount++;
        int n=object.users.size();
        User item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("users");
        if (n>0) {
          jacksonSerializer.writeStartArray();
          for (int i=0; i<n; i++) {
            item=object.users.get(i);
            if (item==null) {
              jacksonSerializer.writeString("null");
            } else {
              if (userBindMap().serializeOnJacksonAsString(context, item, wrapper)==0) {
                jacksonSerializer.writeNullField("users");
              }
            }
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeString("");
        }
      }

      // field isRealJson (mapped with "is_real_json")
      jacksonSerializer.writeStringField("is_real_json", PrimitiveUtils.writeBoolean(object.isRealJson));

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
  public void serializeOnXml(KriptonXmlContext context, Response object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XmlSerializer xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("response");
      }

      // Persisted fields:

      // field status (mapped with "status")
      if (object.status!=null) {
        xmlSerializer.writeStartElement("status");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.status));
        xmlSerializer.writeEndElement();
      }

      // field users (mapped with "users")
      if (object.users!=null)  {
        int n=object.users.size();
        User item;
        for (int i=0; i<n; i++) {
          item=object.users.get(i);
          if (item==null) {
            xmlSerializer.writeEmptyElement("users");
          } else {
            xmlSerializer.writeStartElement("users");
            userBindMap().serializeOnXml(context, item, wrapper, 2);
            xmlSerializer.writeEndElement();
          }
        }
        // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
        if (n==0) {
          xmlSerializer.writeStartElement("users");
          xmlSerializer.writeAttribute("emptyCollection", "true");
          xmlSerializer.writeEndElement();
        }
      }

      // field isRealJson (mapped with "is_real_json")
      xmlSerializer.writeStartElement("is_real_json");
      xmlSerializer.writeBoolean(object.isRealJson);
      xmlSerializer.writeEndElement();

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
  public Response parseOnJackson(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Response instance = createInstance();
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
            case "status":
              // field status (mapped with "status")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.status=jacksonParser.getText();
              }
            break;
            case "users":
              // field users (mapped with "users")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<User> collection=new ArrayList<>();
                User item=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                    item=null;
                  } else {
                    item=userBindMap().parseOnJackson(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.users=collection;
              }
            break;
            case "is_real_json":
              // field isRealJson (mapped with "is_real_json")
              instance.isRealJson=jacksonParser.getBooleanValue();
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
  public Response parseOnJacksonAsString(AbstractJacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      Response instance = createInstance();
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
            case "status":
              // field status (mapped with "status")
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.status=jacksonParser.getText();
              }
            break;
            case "users":
              // field users (mapped with "users")
              if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
                ArrayList<User> collection=new ArrayList<>();
                User item=null;
                String tempValue=null;
                while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                  tempValue=jacksonParser.getValueAsString();
                  if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                    item=null;
                  } else {
                    item=userBindMap().parseOnJacksonAsString(context, wrapper);
                  }
                  collection.add(item);
                }
                instance.users=collection;
              } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
                ArrayList<User> collection=new ArrayList<>();
                instance.users=collection;
              }
            break;
            case "is_real_json":
              // field isRealJson (mapped with "is_real_json")
              instance.isRealJson=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
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
  public Response parseOnXml(KriptonXmlContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XmlPullParser xmlParser = wrapper.xmlParser;
      Response instance = createInstance();
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
                  case "status":
                    // property status (mapped on "status")
                    instance.status=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                  break;
                  case "users":
                    // property users (mapped on "users")
                     {
                      ArrayList<User> collection=new ArrayList<>();
                      User item;
                      // add first element
                      item=null;
                      if (xmlParser.isEmptyElement()) {
                        // if there's a an empty collection it marked with attribute emptyCollection
                        if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                          collection.add(item);
                        }
                        xmlParser.nextTag();
                      } else {
                        item=userBindMap().parseOnXml(context, wrapper, eventType);
                        collection.add(item);
                      }
                      while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("users")) {
                        if (xmlParser.isEmptyElement()) {
                          item=null;
                          xmlParser.nextTag();
                        } else {
                          item=userBindMap().parseOnXml(context, wrapper, eventType);
                        }
                        collection.add(item);
                      }
                      instance.users=collection;
                      read=false;
                    }
                  break;
                  case "is_real_json":
                    // property isRealJson (mapped on "is_real_json")
                    instance.isRealJson=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
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
