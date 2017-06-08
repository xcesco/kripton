package sqlite.quickstart.model;

import com.abubusoft.kripton.BinderUtils;
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
import java.lang.String;

/**
 * <p>
 * Entity <code>User</code> is associated to table <code>user</code>
 * This class represents table associated to entity.
 * </p>
 *  @see User
 */
public class UserTable {
  /**
   * Costant represents typeName of table user
   */
  public static final String TABLE_NAME = "user";

  /**
   * <p>
   * DDL to create table user
   * </p>
   *
   * <pre>CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, username TEXT, email TEXT, address BLOB, phone TEXT, website TEXT, company BLOB);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, username TEXT, email TEXT, address BLOB, phone TEXT, website TEXT, company BLOB);";

  /**
   * <p>
   * DDL to drop table user
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS user;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS user;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column typeName.
   *
   *  @see User#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column typeName.
   *
   *  @see User#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>username</code> is associated to table column <code>username</code>. This costant represents column typeName.
   *
   *  @see User#username
   */
  public static final String COLUMN_USERNAME = "username";

  /**
   * Entity's property <code>email</code> is associated to table column <code>email</code>. This costant represents column typeName.
   *
   *  @see User#email
   */
  public static final String COLUMN_EMAIL = "email";

  /**
   * Entity's property <code>address</code> is associated to table column <code>address</code>. This costant represents column typeName.
   *
   *  @see User#address
   */
  public static final String COLUMN_ADDRESS = "address";

  /**
   * Entity's property <code>phone</code> is associated to table column <code>phone</code>. This costant represents column typeName.
   *
   *  @see User#phone
   */
  public static final String COLUMN_PHONE = "phone";

  /**
   * Entity's property <code>website</code> is associated to table column <code>website</code>. This costant represents column typeName.
   *
   *  @see User#website
   */
  public static final String COLUMN_WEBSITE = "website";

  /**
   * Entity's property <code>company</code> is associated to table column <code>company</code>. This costant represents column typeName.
   *
   *  @see User#company
   */
  public static final String COLUMN_COMPANY = "company";

  /**
   * AddressBindMap */
  private static AddressBindMap addressBindMap = BinderUtils.mapperFor(Address.class);

  /**
   * CompanyBindMap */
  private static CompanyBindMap companyBindMap = BinderUtils.mapperFor(Company.class);

  /**
   * write
   */
  public static byte[] serializeAddress(Address value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        addressBindMap.serializeOnJackson(value, jacksonSerializer);
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
  public static Address parseAddress(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Address result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=addressBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  public static byte[] serializeCompany(Company value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        companyBindMap.serializeOnJackson(value, jacksonSerializer);
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
  public static Company parseCompany(byte[] input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Company result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=companyBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
