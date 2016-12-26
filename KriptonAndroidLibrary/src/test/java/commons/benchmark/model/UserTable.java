package commons.benchmark.model;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Entity <code>User</code> is associated to table <code>user</code>
 * This class represents table associated to entity.
 * </p>
 *  @see User
 */
public class UserTable {
  /**
   * Costant represents name of table user
   */
  public static final String TABLE_NAME = "user";

  /**
   * <p>
   * DDL to create table user
   * </p>
   *
   * <pre>CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT, index INTEGER, guid TEXT, is_active INTEGER, balance TEXT, picture TEXT, age INTEGER, name BLOB, company TEXT, email TEXT, address TEXT, about TEXT, registered TEXT, latitude REAL, longitude REAL, tags BLOB, range BLOB, friends BLOB, images BLOB, greeting TEXT, favorite_fruit TEXT, eye_color TEXT, phone TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, uid TEXT, index INTEGER, guid TEXT, is_active INTEGER, balance TEXT, picture TEXT, age INTEGER, name BLOB, company TEXT, email TEXT, address TEXT, about TEXT, registered TEXT, latitude REAL, longitude REAL, tags BLOB, range BLOB, friends BLOB, images BLOB, greeting TEXT, favorite_fruit TEXT, eye_color TEXT, phone TEXT);";

  /**
   * <p>
   * DDL to drop table user
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS user;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS user;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see User#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column name.
   *
   *  @see User#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>index</code> is associated to table column <code>index</code>. This costant represents column name.
   *
   *  @see User#index
   */
  public static final String COLUMN_INDEX = "index";

  /**
   * Entity's property <code>guid</code> is associated to table column <code>guid</code>. This costant represents column name.
   *
   *  @see User#guid
   */
  public static final String COLUMN_GUID = "guid";

  /**
   * Entity's property <code>isActive</code> is associated to table column <code>is_active</code>. This costant represents column name.
   *
   *  @see User#isActive
   */
  public static final String COLUMN_IS_ACTIVE = "is_active";

  /**
   * Entity's property <code>balance</code> is associated to table column <code>balance</code>. This costant represents column name.
   *
   *  @see User#balance
   */
  public static final String COLUMN_BALANCE = "balance";

  /**
   * Entity's property <code>pictureUrl</code> is associated to table column <code>picture</code>. This costant represents column name.
   *
   *  @see User#pictureUrl
   */
  public static final String COLUMN_PICTURE_URL = "picture";

  /**
   * Entity's property <code>age</code> is associated to table column <code>age</code>. This costant represents column name.
   *
   *  @see User#age
   */
  public static final String COLUMN_AGE = "age";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see User#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>company</code> is associated to table column <code>company</code>. This costant represents column name.
   *
   *  @see User#company
   */
  public static final String COLUMN_COMPANY = "company";

  /**
   * Entity's property <code>email</code> is associated to table column <code>email</code>. This costant represents column name.
   *
   *  @see User#email
   */
  public static final String COLUMN_EMAIL = "email";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column name.
   *
   *  @see User#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>about</code> is associated to table column <code>about</code>. This costant represents column name.
   *
   *  @see User#about
   */
  public static final String COLUMN_ABOUT = "about";

  /**
   * Entity's property <code>registered</code> is associated to table column <code>registered</code>. This costant represents column name.
   *
   *  @see User#registered
   */
  public static final String COLUMN_REGISTERED = "registered";

  /**
   * Entity's property <code>latitude</code> is associated to table column <code>latitude</code>. This costant represents column name.
   *
   *  @see User#latitude
   */
  public static final String COLUMN_LATITUDE = "latitude";

  /**
   * Entity's property <code>longitude</code> is associated to table column <code>longitude</code>. This costant represents column name.
   *
   *  @see User#longitude
   */
  public static final String COLUMN_LONGITUDE = "longitude";

  /**
   * Entity's property <code>tags</code> is associated to table column <code>tags</code>. This costant represents column name.
   *
   *  @see User#tags
   */
  public static final String COLUMN_TAGS = "tags";

  /**
   * Entity's property <code>range</code> is associated to table column <code>range</code>. This costant represents column name.
   *
   *  @see User#range
   */
  public static final String COLUMN_RANGE = "range";

  /**
   * Entity's property <code>friends</code> is associated to table column <code>friends</code>. This costant represents column name.
   *
   *  @see User#friends
   */
  public static final String COLUMN_FRIENDS = "friends";

  /**
   * Entity's property <code>images</code> is associated to table column <code>images</code>. This costant represents column name.
   *
   *  @see User#images
   */
  public static final String COLUMN_IMAGES = "images";

  /**
   * Entity's property <code>greeting</code> is associated to table column <code>greeting</code>. This costant represents column name.
   *
   *  @see User#greeting
   */
  public static final String COLUMN_GREETING = "greeting";

  /**
   * Entity's property <code>favoriteFruit</code> is associated to table column <code>favorite_fruit</code>. This costant represents column name.
   *
   *  @see User#favoriteFruit
   */
  public static final String COLUMN_FAVORITE_FRUIT = "favorite_fruit";

  /**
   * Entity's property <code>eyeColor</code> is associated to table column <code>eye_color</code>. This costant represents column name.
   *
   *  @see User#eyeColor
   */
  public static final String COLUMN_EYE_COLOR = "eye_color";

  /**
   * Entity's property <code>phone</code> is associated to table column <code>phone</code>. This costant represents column name.
   *
   *  @see User#phone
   */
  public static final String COLUMN_PHONE = "phone";

  /**
   * write
   */
  public static byte[] serializeName(Name value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        context.mapperFor(Name.class).serializeOnJackson(context, value, wrapper);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static Name parseName(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Name result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=context.mapperFor(Name.class).parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeTags(List<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static List<String> parseTags(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<String> result=null;
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
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeRange(List<Integer> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Integer item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static List<Integer> parseRange(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Integer> result=null;
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
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeFriends(List<Friend> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Friend item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            context.mapperFor(Friend.class).serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static List<Friend> parseFriends(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Friend> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Friend> collection=new ArrayList<>();
        Friend item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=context.mapperFor(Friend.class).parseOnJackson(context, wrapper);
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeImages(List<Image> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Image item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            context.mapperFor(Image.class).serializeOnJackson(context, item, wrapper);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  public static List<Image> parseImages(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Image> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Image> collection=new ArrayList<>();
        Image item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=context.mapperFor(Image.class).parseOnJackson(context, wrapper);
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
