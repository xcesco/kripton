package shared.kripton47;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.abubusoft.kripton.BinderUtils;
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

/**
 * This class is the shared preference binder defined for Security47
 *
 * @see Security47
 */
public class BindSecurity47SharedPreferences extends AbstractSharedPreference {
  /**
   * binder for type DeviceAccessToken
   */
  private static DeviceAccessTokenBindMap deviceAccessTokenBindMap = BinderUtils.mapperFor(DeviceAccessToken.class);

  /**
   * binder for type UserIdentity
   */
  private static UserIdentityBindMap userIdentityBindMap = BinderUtils.mapperFor(UserIdentity.class);

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
    createPrefs();
    defaultBean=new Security47();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * create prefs
   */
  private void createPrefs() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
  }

  /**
   * force to refresh values
   */
  public BindSecurity47SharedPreferences refresh() {
    createPrefs();
    return this;
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
     {
      String temp=prefs.getString("authorization_token", null);
      bean.authorizationToken=StringUtils.hasText(temp) ? parseAuthorizationToken(temp): defaultBean.authorizationToken;
    }

    bean.fcmId=prefs.getString("fcm_id", defaultBean.fcmId);
    bean.deviceUid=prefs.getString("device_uid", defaultBean.deviceUid);
     {
      String temp=prefs.getString("user_identity", null);
      bean.userIdentity=StringUtils.hasText(temp) ? parseUserIdentity(temp): defaultBean.userIdentity;
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
    if (bean.authorizationToken!=null)  {
      String temp=serializeAuthorizationToken(bean.authorizationToken);
      editor.putString("authorization_token",temp);
    }  else  {
      editor.remove("authorization_token");
    }

    editor.putString("fcm_id",bean.fcmId);

    editor.putString("device_uid",bean.deviceUid);

    if (bean.userIdentity!=null)  {
      String temp=serializeUserIdentity(bean.userIdentity);
      editor.putString("user_identity",temp);
    }  else  {
      editor.remove("user_identity");
    }


    editor.commit();
  }

  /**
   * reads property <code>authorizationToken</code> from shared pref with key <code>authorization_token</code>
   *
   * @return property authorizationToken value
   */
  public DeviceAccessToken getAuthorizationToken() {
    String temp=prefs.getString("authorization_token", null);
    return StringUtils.hasText(temp) ? parseAuthorizationToken(temp): defaultBean.authorizationToken;
  }

  /**
   * reads property <code>fcmId</code> from shared pref with key <code>fcm_id</code>
   *
   * @return property fcmId value
   */
  public String getFcmId() {
    return prefs.getString("fcm_id", defaultBean.fcmId);}

  /**
   * reads property <code>deviceUid</code> from shared pref with key <code>device_uid</code>
   *
   * @return property deviceUid value
   */
  public String getDeviceUid() {
    return prefs.getString("device_uid", defaultBean.deviceUid);}

  /**
   * reads property <code>userIdentity</code> from shared pref with key <code>user_identity</code>
   *
   * @return property userIdentity value
   */
  public UserIdentity getUserIdentity() {
    String temp=prefs.getString("user_identity", null);
    return StringUtils.hasText(temp) ? parseUserIdentity(temp): defaultBean.userIdentity;
  }

  /**
   * for attribute authorizationToken serialization
   */
  protected String serializeAuthorizationToken(DeviceAccessToken value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        deviceAccessTokenBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute authorizationToken parsing
   */
  protected DeviceAccessToken parseAuthorizationToken(String input) {
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
        result=deviceAccessTokenBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute userIdentity serialization
   */
  protected String serializeUserIdentity(UserIdentity value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        userIdentityBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute userIdentity parsing
   */
  protected UserIdentity parseUserIdentity(String input) {
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
        result=userIdentityBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindSecurity47SharedPreferences getInstance() {
    if (instance==null) {
      instance=new BindSecurity47SharedPreferences();
      // read and write instance to sync with default values
      instance.write(instance.read());
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
     * modifier for property authorizationToken
     */
    public BindEditor putAuthorizationToken(DeviceAccessToken value) {
      if (value!=null)  {
        String temp=serializeAuthorizationToken(value);
        editor.putString("authorization_token",temp);
      }  else  {
        editor.remove("authorization_token");
      }

      return this;
    }

    /**
     * remove property authorizationToken
     */
    public BindEditor removeAuthorizationToken() {
      editor.remove("authorization_token");
      return this;
    }

    /**
     * modifier for property fcmId
     */
    public BindEditor putFcmId(String value) {
      editor.putString("fcm_id",value);

      return this;
    }

    /**
     * remove property fcmId
     */
    public BindEditor removeFcmId() {
      editor.remove("fcm_id");
      return this;
    }

    /**
     * modifier for property deviceUid
     */
    public BindEditor putDeviceUid(String value) {
      editor.putString("device_uid",value);

      return this;
    }

    /**
     * remove property deviceUid
     */
    public BindEditor removeDeviceUid() {
      editor.remove("device_uid");
      return this;
    }

    /**
     * modifier for property userIdentity
     */
    public BindEditor putUserIdentity(UserIdentity value) {
      if (value!=null)  {
        String temp=serializeUserIdentity(value);
        editor.putString("user_identity",temp);
      }  else  {
        editor.remove("user_identity");
      }

      return this;
    }

    /**
     * remove property userIdentity
     */
    public BindEditor removeUserIdentity() {
      editor.remove("user_identity");
      return this;
    }

    /**
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}
