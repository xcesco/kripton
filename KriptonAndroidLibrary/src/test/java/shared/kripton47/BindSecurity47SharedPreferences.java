package shared.kripton47;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.String;

/**
 * This class is the shared preference binder defined for Security47
 *
 * @see Security47
 */
public class BindSecurity47SharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindSecurity47SharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Security47 defaultBean;

  /**
   * constructor
   */
  private BindSecurity47SharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Security47();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    Security47 bean=new Security47();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Security47 read() {
    Security47 bean=new Security47();
    bean.fcmId=prefs.getString("fcmId", bean.fcmId);
     {
      String temp=prefs.getString("authorizationToken", null);
      bean.authorizationToken=StringUtils.hasText(temp) ? parseAuthorizationToken(temp): null;
    }

    bean.deviceUid=prefs.getString("deviceUid", bean.deviceUid);
     {
      String temp=prefs.getString("userIdentity", null);
      bean.userIdentity=StringUtils.hasText(temp) ? parseUserIdentity(temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Security47 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("fcmId",bean.fcmId);

    if (bean.authorizationToken!=null)  {
      String temp=serializeAuthorizationToken(bean.authorizationToken);
      editor.putString("authorizationToken",temp);
    }  else  {
      editor.remove("authorizationToken");
    }

    editor.putString("deviceUid",bean.deviceUid);

    if (bean.userIdentity!=null)  {
      String temp=serializeUserIdentity(bean.userIdentity);
      editor.putString("userIdentity",temp);
    }  else  {
      editor.remove("userIdentity");
    }


    editor.commit();
  }

  /**
   * read property fcmId
   *
   * @return property fcmId value
   */
  public String fcmId() {
    return prefs.getString("fcmId", defaultBean.fcmId);
  }

  /**
   * read property authorizationToken
   *
   * @return property authorizationToken value
   */
  public DeviceAccessToken authorizationToken() {
    String temp=prefs.getString("authorizationToken", null);
    return StringUtils.hasText(temp) ? parseAuthorizationToken(temp): null;

  }

  /**
   * read property deviceUid
   *
   * @return property deviceUid value
   */
  public String deviceUid() {
    return prefs.getString("deviceUid", defaultBean.deviceUid);
  }

  /**
   * read property userIdentity
   *
   * @return property userIdentity value
   */
  public UserIdentity userIdentity() {
    String temp=prefs.getString("userIdentity", null);
    return StringUtils.hasText(temp) ? parseUserIdentity(temp): null;

  }

  /**
   * write
   */
  protected static String serializeAuthorizationToken(DeviceAccessToken value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        context.mapperFor(DeviceAccessToken.class).serializeOnJackson(context, value, wrapper);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected static DeviceAccessToken parseAuthorizationToken(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      DeviceAccessToken result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=context.mapperFor(DeviceAccessToken.class).parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * write
   */
  protected static String serializeUserIdentity(UserIdentity value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        context.mapperFor(UserIdentity.class).serializeOnJackson(context, value, wrapper);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  protected static UserIdentity parseUserIdentity(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      UserIdentity result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=context.mapperFor(UserIdentity.class).parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindSecurity47SharedPreferences instance() {
    if (instance==null) {
      instance=new BindSecurity47SharedPreferences();
    }
    return instance;
  }

  /**
   * editor class for shared preferences
   */
  public class BindEditor extends AbstractEditor {
    private BindEditor() {
    }

    /**
     * modifier for property fcmId
     */
    public BindEditor putFcmId(String value) {
      editor.putString("fcmId",value);

      return this;
    }

    /**
     * modifier for property authorizationToken
     */
    public BindEditor putAuthorizationToken(DeviceAccessToken value) {
      if (value!=null)  {
        String temp=serializeAuthorizationToken(value);
        editor.putString("authorizationToken",temp);
      }  else  {
        editor.remove("authorizationToken");
      }

      return this;
    }

    /**
     * modifier for property deviceUid
     */
    public BindEditor putDeviceUid(String value) {
      editor.putString("deviceUid",value);

      return this;
    }

    /**
     * modifier for property userIdentity
     */
    public BindEditor putUserIdentity(UserIdentity value) {
      if (value!=null)  {
        String temp=serializeUserIdentity(value);
        editor.putString("userIdentity",temp);
      }  else  {
        editor.remove("userIdentity");
      }

      return this;
    }
  }
}
