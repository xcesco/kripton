package sqlite.example02;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.AbstractContext;
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
 * This class is the shared preference binder defined for SecuritySharedPreferences
 *
 * @see SecuritySharedPreferences
 */
public class BindSecuritySharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindSecuritySharedPreferences instance;

  /**
   * working instance of bean
   */
  private final SecuritySharedPreferences defaultBean;

  /**
   * PersonBindMap */
  private PersonBindMap personBindMap = AbstractContext.mapperFor(Person.class);

  /**
   * constructor
   */
  private BindSecuritySharedPreferences() {
    // no name specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new SecuritySharedPreferences();
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
    SecuritySharedPreferences bean=new SecuritySharedPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public SecuritySharedPreferences read() {
    SecuritySharedPreferences bean=new SecuritySharedPreferences();
     {
      String temp=prefs.getString("person", null);
      bean.person=StringUtils.hasText(temp) ? parsePerson(temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(SecuritySharedPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    if (bean.person!=null)  {
      String temp=serializePerson(bean.person);
      editor.putString("person",temp);
    }  else  {
      editor.remove("person");
    }


    editor.commit();
  }

  /**
   * read property person
   *
   * @return property person value
   */
  public Person person() {
    String temp=prefs.getString("person", null);
    return StringUtils.hasText(temp) ? parsePerson(temp): null;

  }

  /**
   * write
   */
  protected String serializePerson(Person value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        personBindMap.serializeOnJackson(value, jacksonSerializer);
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
  protected Person parsePerson(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Person result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=personBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindSecuritySharedPreferences instance() {
    if (instance==null) {
      instance=new BindSecuritySharedPreferences();
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
     * modifier for property person
     */
    public BindEditor putPerson(Person value) {
      if (value!=null)  {
        String temp=serializePerson(value);
        editor.putString("person",temp);
      }  else  {
        editor.remove("person");
      }

      return this;
    }
  }
}
