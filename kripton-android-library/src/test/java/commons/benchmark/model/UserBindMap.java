package commons.benchmark.model;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlAttributeUtils;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;

/**
 * This class is binder map for User
 *
 * @see User
 */
@BindMap(User.class)
public class UserBindMap extends AbstractMapper<User> {
  /**
   * FriendBindMap */
  private FriendBindMap friendBindMap = BinderUtils.mapperFor(Friend.class);

  /**
   * ImageBindMap */
  private ImageBindMap imageBindMap = BinderUtils.mapperFor(Image.class);

  /**
   * NameBindMap */
  private NameBindMap nameBindMap = BinderUtils.mapperFor(Name.class);

  @Override
  public int serializeOnJackson(User object, JsonGenerator jacksonSerializer) throws Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field about (mapped with "about")
    if (object.about!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("about", object.about);
    }

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address", object.address);
    }

    // field age (mapped with "age")
    fieldCount++;
    jacksonSerializer.writeNumberField("age", object.age);

    // field balance (mapped with "balance")
    if (object.balance!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("balance", object.balance);
    }

    // field company (mapped with "company")
    if (object.company!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("company", object.company);
    }

    // field email (mapped with "email")
    if (object.email!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.email);
    }

    // field eyeColor (mapped with "eyeColor")
    if (object.eyeColor!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("eyeColor", object.eyeColor);
    }

    // field favoriteFruit (mapped with "favoriteFruit")
    if (object.favoriteFruit!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("favoriteFruit", object.favoriteFruit);
    }

    // field friends (mapped with "friends")
    if (object.friends!=null)  {
      fieldCount++;
      int n=object.friends.size();
      Friend item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("friends");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.friends.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          friendBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field greeting (mapped with "greeting")
    if (object.greeting!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("greeting", object.greeting);
    }

    // field guid (mapped with "guid")
    if (object.guid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("guid", object.guid);
    }

    // field images (mapped with "images")
    if (object.images!=null)  {
      fieldCount++;
      int n=object.images.size();
      Image item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("images");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.images.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          imageBindMap.serializeOnJackson(item, jacksonSerializer);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field index (mapped with "index")
    fieldCount++;
    jacksonSerializer.writeNumberField("index", object.index);

    // field isActive (mapped with "isActive")
    fieldCount++;
    jacksonSerializer.writeBooleanField("isActive", object.isActive);

    // field latitude (mapped with "latitude")
    fieldCount++;
    jacksonSerializer.writeNumberField("latitude", object.latitude);

    // field longitude (mapped with "longitude")
    fieldCount++;
    jacksonSerializer.writeNumberField("longitude", object.longitude);

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("name");
      nameBindMap.serializeOnJackson(object.name, jacksonSerializer);
    }

    // field phone (mapped with "phone")
    if (object.phone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("phone", object.phone);
    }

    // field range (mapped with "range")
    if (object.range!=null)  {
      fieldCount++;
      int n=object.range.size();
      Integer item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("range");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.range.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeNumber(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field registered (mapped with "registered")
    if (object.registered!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("registered", object.registered);
    }

    // field tags (mapped with "tags")
    if (object.tags!=null)  {
      fieldCount++;
      int n=object.tags.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("tags");
      jacksonSerializer.writeStartArray();
      for (int i=0; i<n; i++) {
        item=object.tags.get(i);
        if (item==null) {
          jacksonSerializer.writeNull();
        } else {
          jacksonSerializer.writeString(item);
        }
      }
      jacksonSerializer.writeEndArray();
    }

    // field id (mapped with "id")
    fieldCount++;
    jacksonSerializer.writeNumberField("id", object.id);

    // field pictureUrl (mapped with "picture")
    if (object.pictureUrl!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("picture", object.pictureUrl);
    }

    // field uid (mapped with "_id")
    if (object.uid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("_id", object.uid);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  @Override
  public int serializeOnJacksonAsString(User object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field about (mapped with "about")
    if (object.about!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("about", object.about);
    }

    // field address (mapped with "address")
    if (object.address!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("address", object.address);
    }

    // field age (mapped with "age")
    jacksonSerializer.writeStringField("age", PrimitiveUtils.writeInteger(object.age));

    // field balance (mapped with "balance")
    if (object.balance!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("balance", object.balance);
    }

    // field company (mapped with "company")
    if (object.company!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("company", object.company);
    }

    // field email (mapped with "email")
    if (object.email!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("email", object.email);
    }

    // field eyeColor (mapped with "eyeColor")
    if (object.eyeColor!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("eyeColor", object.eyeColor);
    }

    // field favoriteFruit (mapped with "favoriteFruit")
    if (object.favoriteFruit!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("favoriteFruit", object.favoriteFruit);
    }

    // field friends (mapped with "friends")
    if (object.friends!=null)  {
      fieldCount++;
      int n=object.friends.size();
      Friend item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("friends");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.friends.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (friendBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("friends");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field greeting (mapped with "greeting")
    if (object.greeting!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("greeting", object.greeting);
    }

    // field guid (mapped with "guid")
    if (object.guid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("guid", object.guid);
    }

    // field images (mapped with "images")
    if (object.images!=null)  {
      fieldCount++;
      int n=object.images.size();
      Image item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("images");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.images.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            if (imageBindMap.serializeOnJacksonAsString(item, jacksonSerializer)==0) {
              jacksonSerializer.writeNullField("images");
            }
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field index (mapped with "index")
    jacksonSerializer.writeStringField("index", PrimitiveUtils.writeInteger(object.index));

    // field isActive (mapped with "isActive")
    jacksonSerializer.writeStringField("isActive", PrimitiveUtils.writeBoolean(object.isActive));

    // field latitude (mapped with "latitude")
    jacksonSerializer.writeStringField("latitude", PrimitiveUtils.writeDouble(object.latitude));

    // field longitude (mapped with "longitude")
    jacksonSerializer.writeStringField("longitude", PrimitiveUtils.writeDouble(object.longitude));

    // field name (mapped with "name")
    if (object.name!=null)  {
      fieldCount++;
      jacksonSerializer.writeFieldName("name");
      if (nameBindMap.serializeOnJacksonAsString(object.name, jacksonSerializer)==0) {
        jacksonSerializer.writeNullField("name");
      }
    }

    // field phone (mapped with "phone")
    if (object.phone!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("phone", object.phone);
    }

    // field range (mapped with "range")
    if (object.range!=null)  {
      fieldCount++;
      int n=object.range.size();
      Integer item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("range");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.range.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(PrimitiveUtils.writeInteger(item));
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field registered (mapped with "registered")
    if (object.registered!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("registered", object.registered);
    }

    // field tags (mapped with "tags")
    if (object.tags!=null)  {
      fieldCount++;
      int n=object.tags.size();
      String item;
      // write wrapper tag
      jacksonSerializer.writeFieldName("tags");
      if (n>0) {
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=object.tags.get(i);
          if (item==null) {
            jacksonSerializer.writeString("null");
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      } else {
        jacksonSerializer.writeString("");
      }
    }

    // field id (mapped with "id")
    jacksonSerializer.writeStringField("id", PrimitiveUtils.writeLong(object.id));

    // field pictureUrl (mapped with "picture")
    if (object.pictureUrl!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("picture", object.pictureUrl);
    }

    // field uid (mapped with "_id")
    if (object.uid!=null)  {
      fieldCount++;
      jacksonSerializer.writeStringField("_id", object.uid);
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization
   */
  @Override
  public void serializeOnXml(User object, XMLSerializer xmlSerializer, int currentEventType) throws
      Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("user");
    }

    // Persisted fields:

    // field about (mapped with "about")
    if (object.about!=null) {
      xmlSerializer.writeStartElement("about");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.about));
      xmlSerializer.writeEndElement();
    }

    // field address (mapped with "address")
    if (object.address!=null) {
      xmlSerializer.writeStartElement("address");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.address));
      xmlSerializer.writeEndElement();
    }

    // field age (mapped with "age")
    xmlSerializer.writeStartElement("age");
    xmlSerializer.writeInt(object.age);
    xmlSerializer.writeEndElement();

    // field balance (mapped with "balance")
    if (object.balance!=null) {
      xmlSerializer.writeStartElement("balance");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.balance));
      xmlSerializer.writeEndElement();
    }

    // field company (mapped with "company")
    if (object.company!=null) {
      xmlSerializer.writeStartElement("company");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.company));
      xmlSerializer.writeEndElement();
    }

    // field email (mapped with "email")
    if (object.email!=null) {
      xmlSerializer.writeStartElement("email");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.email));
      xmlSerializer.writeEndElement();
    }

    // field eyeColor (mapped with "eyeColor")
    if (object.eyeColor!=null) {
      xmlSerializer.writeStartElement("eyeColor");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.eyeColor));
      xmlSerializer.writeEndElement();
    }

    // field favoriteFruit (mapped with "favoriteFruit")
    if (object.favoriteFruit!=null) {
      xmlSerializer.writeStartElement("favoriteFruit");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.favoriteFruit));
      xmlSerializer.writeEndElement();
    }

    // field friends (mapped with "friends")
    if (object.friends!=null)  {
      int n=object.friends.size();
      Friend item;
      for (int i=0; i<n; i++) {
        item=object.friends.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("friends");
        } else {
          xmlSerializer.writeStartElement("friends");
          friendBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("friends");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field greeting (mapped with "greeting")
    if (object.greeting!=null) {
      xmlSerializer.writeStartElement("greeting");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.greeting));
      xmlSerializer.writeEndElement();
    }

    // field guid (mapped with "guid")
    if (object.guid!=null) {
      xmlSerializer.writeStartElement("guid");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.guid));
      xmlSerializer.writeEndElement();
    }

    // field images (mapped with "images")
    if (object.images!=null)  {
      int n=object.images.size();
      Image item;
      for (int i=0; i<n; i++) {
        item=object.images.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("images");
        } else {
          xmlSerializer.writeStartElement("images");
          imageBindMap.serializeOnXml(item, xmlSerializer, 2);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("images");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field index (mapped with "index")
    xmlSerializer.writeStartElement("index");
    xmlSerializer.writeInt(object.index);
    xmlSerializer.writeEndElement();

    // field isActive (mapped with "isActive")
    xmlSerializer.writeStartElement("isActive");
    xmlSerializer.writeBoolean(object.isActive);
    xmlSerializer.writeEndElement();

    // field latitude (mapped with "latitude")
    xmlSerializer.writeStartElement("latitude");
    xmlSerializer.writeDouble(object.latitude);
    xmlSerializer.writeEndElement();

    // field longitude (mapped with "longitude")
    xmlSerializer.writeStartElement("longitude");
    xmlSerializer.writeDouble(object.longitude);
    xmlSerializer.writeEndElement();

    // field name (mapped with "name")
    if (object.name!=null)  {
      xmlSerializer.writeStartElement("name");
      nameBindMap.serializeOnXml(object.name, xmlSerializer, 2);
      xmlSerializer.writeEndElement();
    }

    // field phone (mapped with "phone")
    if (object.phone!=null) {
      xmlSerializer.writeStartElement("phone");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.phone));
      xmlSerializer.writeEndElement();
    }

    // field range (mapped with "range")
    if (object.range!=null)  {
      int n=object.range.size();
      Integer item;
      for (int i=0; i<n; i++) {
        item=object.range.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("range");
        } else {
          xmlSerializer.writeStartElement("range");
          xmlSerializer.writeInt(item);
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("range");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field registered (mapped with "registered")
    if (object.registered!=null) {
      xmlSerializer.writeStartElement("registered");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.registered));
      xmlSerializer.writeEndElement();
    }

    // field tags (mapped with "tags")
    if (object.tags!=null)  {
      int n=object.tags.size();
      String item;
      for (int i=0; i<n; i++) {
        item=object.tags.get(i);
        if (item==null) {
          xmlSerializer.writeEmptyElement("tags");
        } else {
          xmlSerializer.writeStartElement("tags");
          xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(item));
          xmlSerializer.writeEndElement();
        }
      }
      // to distinguish between first empty element and empty collection, we write an attribute emptyCollection
      if (n==0) {
        xmlSerializer.writeStartElement("tags");
        xmlSerializer.writeAttribute("emptyCollection", "true");
        xmlSerializer.writeEndElement();
      }
    }

    // field uid (mapped with "_id")
    if (object.uid!=null) {
      xmlSerializer.writeStartElement("_id");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.uid));
      xmlSerializer.writeEndElement();
    }

    // field id (mapped with "id")
    xmlSerializer.writeStartElement("id");
    xmlSerializer.writeLong(object.id);
    xmlSerializer.writeEndElement();

    // field pictureUrl (mapped with "picture")
    if (object.pictureUrl!=null) {
      xmlSerializer.writeStartElement("picture");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(object.pictureUrl));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson
   */
  @Override
  public User parseOnJackson(JsonParser jacksonParser) throws Exception {
    User instance = new User();
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
          case "about":
            // field about (mapped with "about")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.about=jacksonParser.getText();
            }
          break;
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address=jacksonParser.getText();
            }
          break;
          case "age":
            // field age (mapped with "age")
            instance.age=jacksonParser.getIntValue();
          break;
          case "balance":
            // field balance (mapped with "balance")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.balance=jacksonParser.getText();
            }
          break;
          case "company":
            // field company (mapped with "company")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.company=jacksonParser.getText();
            }
          break;
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.email=jacksonParser.getText();
            }
          break;
          case "eyeColor":
            // field eyeColor (mapped with "eyeColor")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.eyeColor=jacksonParser.getText();
            }
          break;
          case "favoriteFruit":
            // field favoriteFruit (mapped with "favoriteFruit")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.favoriteFruit=jacksonParser.getText();
            }
          break;
          case "friends":
            // field friends (mapped with "friends")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Friend> collection=new ArrayList<>();
              Friend item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=friendBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.friends=collection;
            }
          break;
          case "greeting":
            // field greeting (mapped with "greeting")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.greeting=jacksonParser.getText();
            }
          break;
          case "guid":
            // field guid (mapped with "guid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.guid=jacksonParser.getText();
            }
          break;
          case "images":
            // field images (mapped with "images")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Image> collection=new ArrayList<>();
              Image item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=imageBindMap.parseOnJackson(jacksonParser);
                }
                collection.add(item);
              }
              instance.images=collection;
            }
          break;
          case "index":
            // field index (mapped with "index")
            instance.index=jacksonParser.getIntValue();
          break;
          case "isActive":
            // field isActive (mapped with "isActive")
            instance.isActive=jacksonParser.getBooleanValue();
          break;
          case "latitude":
            // field latitude (mapped with "latitude")
            instance.latitude=jacksonParser.getDoubleValue();
          break;
          case "longitude":
            // field longitude (mapped with "longitude")
            instance.longitude=jacksonParser.getDoubleValue();
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
              instance.name=nameBindMap.parseOnJackson(jacksonParser);
            }
          break;
          case "phone":
            // field phone (mapped with "phone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.phone=jacksonParser.getText();
            }
          break;
          case "range":
            // field range (mapped with "range")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Integer> collection=new ArrayList<>();
              Integer item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getIntValue();
                }
                collection.add(item);
              }
              instance.range=collection;
            }
          break;
          case "registered":
            // field registered (mapped with "registered")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.registered=jacksonParser.getText();
            }
          break;
          case "tags":
            // field tags (mapped with "tags")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<String> collection=new ArrayList<>();
              String item=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
                  item=null;
                } else {
                  item=jacksonParser.getText();
                }
                collection.add(item);
              }
              instance.tags=collection;
            }
          break;
          case "_id":
            // field uid (mapped with "_id")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.uid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=jacksonParser.getLongValue();
          break;
          case "picture":
            // field pictureUrl (mapped with "picture")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.pictureUrl=jacksonParser.getText();
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
  public User parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    User instance = new User();
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
          case "about":
            // field about (mapped with "about")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.about=jacksonParser.getText();
            }
          break;
          case "address":
            // field address (mapped with "address")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.address=jacksonParser.getText();
            }
          break;
          case "age":
            // field age (mapped with "age")
            instance.age=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "balance":
            // field balance (mapped with "balance")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.balance=jacksonParser.getText();
            }
          break;
          case "company":
            // field company (mapped with "company")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.company=jacksonParser.getText();
            }
          break;
          case "email":
            // field email (mapped with "email")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.email=jacksonParser.getText();
            }
          break;
          case "eyeColor":
            // field eyeColor (mapped with "eyeColor")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.eyeColor=jacksonParser.getText();
            }
          break;
          case "favoriteFruit":
            // field favoriteFruit (mapped with "favoriteFruit")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.favoriteFruit=jacksonParser.getText();
            }
          break;
          case "friends":
            // field friends (mapped with "friends")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Friend> collection=new ArrayList<>();
              Friend item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=friendBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.friends=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Friend> collection=new ArrayList<>();
              instance.friends=collection;
            }
          break;
          case "greeting":
            // field greeting (mapped with "greeting")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.greeting=jacksonParser.getText();
            }
          break;
          case "guid":
            // field guid (mapped with "guid")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.guid=jacksonParser.getText();
            }
          break;
          case "images":
            // field images (mapped with "images")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Image> collection=new ArrayList<>();
              Image item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=imageBindMap.parseOnJacksonAsString(jacksonParser);
                }
                collection.add(item);
              }
              instance.images=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Image> collection=new ArrayList<>();
              instance.images=collection;
            }
          break;
          case "index":
            // field index (mapped with "index")
            instance.index=PrimitiveUtils.readInteger(jacksonParser.getText(), 0);
          break;
          case "isActive":
            // field isActive (mapped with "isActive")
            instance.isActive=PrimitiveUtils.readBoolean(jacksonParser.getText(), (boolean)false);
          break;
          case "latitude":
            // field latitude (mapped with "latitude")
            instance.latitude=PrimitiveUtils.readDouble(jacksonParser.getText(), 0.0);
          break;
          case "longitude":
            // field longitude (mapped with "longitude")
            instance.longitude=PrimitiveUtils.readDouble(jacksonParser.getText(), 0.0);
          break;
          case "name":
            // field name (mapped with "name")
            if (jacksonParser.currentToken()==JsonToken.START_OBJECT || jacksonParser.currentToken()==JsonToken.VALUE_STRING) {
              instance.name=nameBindMap.parseOnJacksonAsString(jacksonParser);
            }
          break;
          case "phone":
            // field phone (mapped with "phone")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.phone=jacksonParser.getText();
            }
          break;
          case "range":
            // field range (mapped with "range")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<Integer> collection=new ArrayList<>();
              Integer item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  item=PrimitiveUtils.readInteger(jacksonParser.getText(), null);
                }
                collection.add(item);
              }
              instance.range=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<Integer> collection=new ArrayList<>();
              instance.range=collection;
            }
          break;
          case "registered":
            // field registered (mapped with "registered")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.registered=jacksonParser.getText();
            }
          break;
          case "tags":
            // field tags (mapped with "tags")
            if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
              ArrayList<String> collection=new ArrayList<>();
              String item=null;
              String tempValue=null;
              while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
                tempValue=jacksonParser.getValueAsString();
                if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && "null".equals(tempValue)) {
                  item=null;
                } else {
                  if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
                    item=jacksonParser.getText();
                  }
                }
                collection.add(item);
              }
              instance.tags=collection;
            } else if (jacksonParser.currentToken()==JsonToken.VALUE_STRING && !StringUtils.hasText(jacksonParser.getValueAsString())) {
              ArrayList<String> collection=new ArrayList<>();
              instance.tags=collection;
            }
          break;
          case "_id":
            // field uid (mapped with "_id")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.uid=jacksonParser.getText();
            }
          break;
          case "id":
            // field id (mapped with "id")
            instance.id=PrimitiveUtils.readLong(jacksonParser.getText(), 0L);
          break;
          case "picture":
            // field pictureUrl (mapped with "picture")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              instance.pictureUrl=jacksonParser.getText();
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
  public User parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    User instance = new User();
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
                case "about":
                  // property about (mapped on "about")
                  instance.about=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "address":
                  // property address (mapped on "address")
                  instance.address=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "age":
                  // property age (mapped on "age")
                  instance.age=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "balance":
                  // property balance (mapped on "balance")
                  instance.balance=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "company":
                  // property company (mapped on "company")
                  instance.company=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "email":
                  // property email (mapped on "email")
                  instance.email=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "eyeColor":
                  // property eyeColor (mapped on "eyeColor")
                  instance.eyeColor=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "favoriteFruit":
                  // property favoriteFruit (mapped on "favoriteFruit")
                  instance.favoriteFruit=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "friends":
                  // property friends (mapped on "friends")
                   {
                    ArrayList<Friend> collection=CollectionUtils.merge(new ArrayList<>(), instance.friends);
                    Friend item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=friendBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("friends")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=friendBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.friends=collection;
                    read=false;
                  }
                break;
                case "greeting":
                  // property greeting (mapped on "greeting")
                  instance.greeting=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "guid":
                  // property guid (mapped on "guid")
                  instance.guid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "images":
                  // property images (mapped on "images")
                   {
                    ArrayList<Image> collection=CollectionUtils.merge(new ArrayList<>(), instance.images);
                    Image item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=imageBindMap.parseOnXml(xmlParser, eventType);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("images")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=imageBindMap.parseOnXml(xmlParser, eventType);
                      }
                      collection.add(item);
                    }
                    instance.images=collection;
                    read=false;
                  }
                break;
                case "index":
                  // property index (mapped on "index")
                  instance.index=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), 0);
                break;
                case "isActive":
                  // property isActive (mapped on "isActive")
                  instance.isActive=PrimitiveUtils.readBoolean(xmlParser.getElementAsBoolean(), (boolean)false);
                break;
                case "latitude":
                  // property latitude (mapped on "latitude")
                  instance.latitude=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), 0.0);
                break;
                case "longitude":
                  // property longitude (mapped on "longitude")
                  instance.longitude=PrimitiveUtils.readDouble(xmlParser.getElementAsDouble(), 0.0);
                break;
                case "name":
                  // property name (mapped on "name")
                  instance.name=nameBindMap.parseOnXml(xmlParser, eventType);
                break;
                case "phone":
                  // property phone (mapped on "phone")
                  instance.phone=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "range":
                  // property range (mapped on "range")
                   {
                    ArrayList<Integer> collection=CollectionUtils.merge(new ArrayList<>(), instance.range);
                    Integer item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("range")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=PrimitiveUtils.readInteger(xmlParser.getElementAsInt(), null);
                      }
                      collection.add(item);
                    }
                    instance.range=collection;
                    read=false;
                  }
                break;
                case "registered":
                  // property registered (mapped on "registered")
                  instance.registered=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "tags":
                  // property tags (mapped on "tags")
                   {
                    ArrayList<String> collection=CollectionUtils.merge(new ArrayList<>(), instance.tags);
                    String item;
                    // add first element
                    item=null;
                    if (xmlParser.isEmptyElement()) {
                      // if there's a an empty collection it marked with attribute emptyCollection
                      if (XmlAttributeUtils.getAttributeAsBoolean(xmlParser, "emptyCollection", false)==false) {
                        collection.add(item);
                      }
                      xmlParser.nextTag();
                    } else {
                      item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      collection.add(item);
                    }
                    while (xmlParser.nextTag() != XmlPullParser.END_TAG && xmlParser.getName().toString().equals("tags")) {
                      if (xmlParser.isEmptyElement()) {
                        item=null;
                        xmlParser.nextTag();
                      } else {
                        item=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                      }
                      collection.add(item);
                    }
                    instance.tags=collection;
                    read=false;
                  }
                break;
                case "_id":
                  // property uid (mapped on "_id")
                  instance.uid=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
                break;
                case "id":
                  // property id (mapped on "id")
                  instance.id=PrimitiveUtils.readLong(xmlParser.getElementAsLong(), 0L);
                break;
                case "picture":
                  // property pictureUrl (mapped on "picture")
                  instance.pictureUrl=StringEscapeUtils.unescapeXml(xmlParser.getElementText());
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
