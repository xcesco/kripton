package shared.kripton47;

import android.content.Context;
import android.content.SharedPreferences;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.binder.KriptonBinder;
import com.abubusoft.kripton.binder.context.JacksonContext;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.binder.persistence.JacksonWrapperSerializer;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.Exception;
import java.lang.String;

/**
 * This class is the shared preference binder defined for App47
 *
 * @see App47
 */
public class BindApp47SharedPreferences extends AbstractSharedPreference {
  /**
   * shared preferences name for App47
   */
  public static final String SHARED_PREFERENCE_NAME = "app47";

  /**
   * instance of shared preferences
   */
  private static BindApp47SharedPreferences instance;

  /**
   * working instance of bean
   */
  private final App47 defaultBean;

  /**
   * constructor
   */
  private BindApp47SharedPreferences() {
    // using name attribute of annotation @BindSharedPreferences as name
    prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    defaultBean=new App47();
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
    App47 bean=new App47();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App47 read() {
    App47 bean=new App47();
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
  public void write(App47 bean) {
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
  protected static String serializeUserAccessToken(UserAccessToken value) {
    if (value==null) {
      return null;
    }
    JacksonContext context=KriptonBinder.getJsonBinderContext();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        context.mapperFor(UserAccessToken.class).serializeOnJackson(context, value, wrapper);
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
  protected static UserAccessToken parseUserAccessToken(String input) {
    if (input==null) {
      return null;
    }
    JacksonContext context=KriptonBinder.getJsonBinderContext();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      UserAccessToken result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=context.mapperFor(UserAccessToken.class).parseOnJackson(context, wrapper);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindApp47SharedPreferences instance() {
    if (instance==null) {
      instance=new BindApp47SharedPreferences();
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
