package shared.kripton47;

import android.content.Context;
import android.content.SharedPreferences;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.binder2.context.JacksonContext;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder2.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.StringUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.StringWriter;
import java.lang.Exception;
import java.lang.String;

/**
 * This class is the shared preference binder defined for App
 *
 * @see App
 */
public class BindAppSharedPreferences extends AbstractSharedPreference {
  /**
   * shared preferences name for App
   */
  public static final String SHARED_PREFERENCE_NAME = "app";

  /**
   * instance of shared preferences
   */
  private static BindAppSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final App defaultBean;

  /**
   * constructor
   */
  private BindAppSharedPreferences() {
    // using name attribute of annotation @BindSharedPreferences as name
    prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    defaultBean=new App();
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
    App bean=new App();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App read() {
    App bean=new App();
    bean.name=prefs.getString("name", bean.name);
     {
      String temp=prefs.getString("userAccessToken", null);
      bean.userAccessToken=StringUtils.hasText(temp) ? parseUserAccessToken(temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(App bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name",bean.name);

    if (bean.userAccessToken!=null)  {
      String temp=serializeUserAccessToken(bean.userAccessToken);
      editor.putString("userAccessToken",temp);
    }  else  {
      editor.remove("userAccessToken");
    }


    editor.commit();
  }

  /**
   * read property name
   *
   * @return property name value
   */
  public String name() {
    return prefs.getString("name", defaultBean.name);
  }

  /**
   * read property userAccessToken
   *
   * @return property userAccessToken value
   */
  public UserAccessToken userAccessToken() {
    String temp=prefs.getString("userAccessToken", null);
    return StringUtils.hasText(temp) ? parseUserAccessToken(temp): null;

  }

  /**
   * write
   */
  protected String serializeUserAccessToken(UserAccessToken value) {
    if (value==null) {
      return null;
    }
    try {
      StringWriter writer=new StringWriter();
      JacksonContext context=(JacksonContext)KriptonBinder2.getBinder(BinderType.JSON);
      JacksonWrapperSerializer wrapper=context.createSerializer(writer);
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        context.mapperFor(UserAccessToken.class).serializeOnJackson(context, value, wrapper);
      }
      wrapper.close();
      return writer.toString();
    } catch(Exception e) {
      return null;
    }
  }

  /**
   * parse
   */
  protected UserAccessToken parseUserAccessToken(String input) {
    if (input==null) {
      return null;
    }
    try {
      JacksonContext context=(JacksonContext)KriptonBinder2.getBinder(BinderType.JSON);
      JacksonWrapperParser wrapper=context.createParser(input);
      JsonParser jacksonParser=wrapper.jacksonParser;
      jacksonParser.nextToken();
      UserAccessToken result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=context.mapperFor(UserAccessToken.class).parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      return null;
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindAppSharedPreferences instance() {
    if (instance==null) {
      instance=new BindAppSharedPreferences();
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
     * modifier for property name
     */
    public BindEditor putName(String value) {
      editor.putString("name",value);

      return this;
    }

    /**
     * modifier for property userAccessToken
     */
    public BindEditor putUserAccessToken(UserAccessToken value) {
      if (value!=null)  {
        String temp=serializeUserAccessToken(value);
        editor.putString("userAccessToken",temp);
      }  else  {
        editor.remove("userAccessToken");
      }

      return this;
    }
  }
}
