package shared.kripton47;

import com.abubusoft.kripton.android.annotation.BindMap;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.context.XmlBinderContext;
import com.abubusoft.kripton.binder2.core.AbstractMapper;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperParser;
import com.abubusoft.kripton.binder2.persistence.XmlWrapperSerializer;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.util.Stack;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.XMLStreamWriter2;

/**
 * This class is the shared preference binder defined for UserIdentity
 *
 * @see UserIdentity
 */
@BindMap
public class UserIdentityBindMap extends AbstractMapper<UserIdentity> {
  /**
   * create new object instance
   */
  @Override
  public UserIdentity createInstance() {
    return new UserIdentity();
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJackson(JacksonContext context, UserIdentity object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field email
      if (object.getEmail()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("email", object.getEmail());
      }

      // field name
      if (object.getName()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.getName());
      }

      // field username
      if (object.getUsername()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("username", object.getUsername());
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public int serializeOnJacksonAsString(JacksonContext context, UserIdentity object, JacksonWrapperSerializer wrapper) {
    try {
      JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;

      // Serialized Field:

      // field email
      if (object.getEmail()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("email", object.getEmail());
      }

      // field name
      if (object.getName()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("name", object.getName());
      }

      // field username
      if (object.getUsername()!=null)  {
        fieldCount++;
        jacksonSerializer.writeStringField("username", object.getUsername());
      }

      jacksonSerializer.writeEndObject();
      return fieldCount;
    } catch(IOException e) {
      e.printStackTrace();
      throw (new KriptonRuntimeException(e));
    }
  }

  /**
   * reset shared preferences
   */
  @Override
  public void serializeOnXml(XmlBinderContext context, UserIdentity object, XmlWrapperSerializer wrapper, int currentEventType) {
    try {
      XMLStreamWriter2 xmlSerializer = wrapper.xmlSerializer;
      if (currentEventType == 0) {
        xmlSerializer.writeStartElement("userIdentity");
      }

      // Persisted fields:

      // field email
      if (object.getEmail()!=null) {
        xmlSerializer.writeStartElement("email");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getEmail()));
        xmlSerializer.writeEndElement();
      }

      // field name
      if (object.getName()!=null) {
        xmlSerializer.writeStartElement("name");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getName()));
        xmlSerializer.writeEndElement();
      }

      // field username
      if (object.getUsername()!=null) {
        xmlSerializer.writeStartElement("username");
        xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.getUsername()));
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
  public UserIdentity parseOnJackson(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      UserIdentity instance = createInstance();
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
            case "email":
              // field email
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setEmail(jacksonParser.getText());
              }
            break;
            case "name":
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setName(jacksonParser.getText());
              }
            break;
            case "username":
              // field username
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setUsername(jacksonParser.getText());
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
  public UserIdentity parseOnJacksonAsString(JacksonContext context, JacksonWrapperParser wrapper) {
    try {
      JsonParser jacksonParser = wrapper.jacksonParser;
      UserIdentity instance = createInstance();
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
            case "email":
              // field email
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setEmail(jacksonParser.getText());
              }
            break;
            case "name":
              // field name
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setName(jacksonParser.getText());
              }
            break;
            case "username":
              // field username
              if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                instance.setUsername(jacksonParser.getText());
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
  public UserIdentity parseOnXml(XmlBinderContext context, XmlWrapperParser wrapper, int currentEventType) {
    try {
      XMLStreamReader2 xmlParser = wrapper.xmlParser;
      UserIdentity instance = createInstance();
      int eventType = currentEventType;
      boolean read=true;

      if (currentEventType == 0) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      String currentTag = xmlParser.getName().toString();
      Stack<String> elementNameStack = new Stack<>();
      elementNameStack.push(currentTag);
      // No attributes found

      //sub-elements
      while (xmlParser.hasNext() && !elementNameStack.isEmpty()) {
        if (read) {
          eventType = xmlParser.next();
        } else {
          eventType = xmlParser.getEventType();
        }
        read=true;
        switch(eventType) {
            case XMLEvent.START_ELEMENT:
              currentTag = xmlParser.getName().toString();
              switch(currentTag) {
                  case "email":
                    // property email
                    instance.setEmail(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "name":
                    // property name
                    instance.setName(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                  break;
                  case "username":
                    // property username
                    instance.setUsername(StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
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
