package shared.kripton202;

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
import java.util.HashSet;
import java.util.Set;

/**
 * This class is the shared preference binder defined for DataSharedPreferences
 *
 * @see DataSharedPreferences
 */
public class BindDataSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindDataSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final DataSharedPreferences defaultBean;

  /**
   * constructor
   */
  private BindDataSharedPreferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new DataSharedPreferences();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * force to refresh values
   */
  public BindDataSharedPreferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    DataSharedPreferences bean=new DataSharedPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public DataSharedPreferences read() {
    DataSharedPreferences bean=new DataSharedPreferences();
     {
      String temp=prefs.getString("example_multi_list", null);
      bean.exampleMultiList=StringUtils.hasText(temp) ? parseExampleMultiList(temp): null;
    }

    bean.notificationNewMessageRingtone=prefs.getString("notification_new_message_ringtone", bean.notificationNewMessageRingtone);
    bean.notificationsNewMessageVibrate=(boolean)prefs.getBoolean("notifications_new_message_vibrate", (boolean)bean.notificationsNewMessageVibrate);
    bean.syncFrequency=prefs.getString("sync_frequency", bean.syncFrequency);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(DataSharedPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    if (bean.exampleMultiList!=null)  {
      String temp=serializeExampleMultiList(bean.exampleMultiList);
      editor.putString("example_multi_list",temp);
    }  else  {
      editor.remove("example_multi_list");
    }

    editor.putString("notification_new_message_ringtone",bean.notificationNewMessageRingtone);

    editor.putBoolean("notifications_new_message_vibrate",(boolean)bean.notificationsNewMessageVibrate);

    editor.putString("sync_frequency",bean.syncFrequency);


    editor.commit();
  }

  /**
   * read property exampleMultiList
   *
   * @return property exampleMultiList value
   */
  public Set<String> exampleMultiList() {
    String temp=prefs.getString("example_multi_list", null);
    return StringUtils.hasText(temp) ? parseExampleMultiList(temp): null;

  }

  /**
   * read property notificationNewMessageRingtone
   *
   * @return property notificationNewMessageRingtone value
   */
  public String notificationNewMessageRingtone() {
    return prefs.getString("notification_new_message_ringtone", defaultBean.notificationNewMessageRingtone);
  }

  /**
   * read property notificationsNewMessageVibrate
   *
   * @return property notificationsNewMessageVibrate value
   */
  public boolean notificationsNewMessageVibrate() {
    return (boolean)prefs.getBoolean("notifications_new_message_vibrate", (boolean)defaultBean.notificationsNewMessageVibrate);
  }

  /**
   * read property syncFrequency
   *
   * @return property syncFrequency value
   */
  public String syncFrequency() {
    return prefs.getString("sync_frequency", defaultBean.syncFrequency);
  }

  /**
   * for attribute exampleMultiList serialization
   */
  protected String serializeExampleMultiList(Set<String> value) {
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
        // write wrapper tag
        jacksonSerializer.writeFieldName("exampleMultiList");
        jacksonSerializer.writeStartArray();
        for (String item: value) {
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
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute exampleMultiList parsing
   */
  protected Set<String> parseExampleMultiList(String input) {
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
      Set<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<String> collection=new HashSet<>();
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
   * get instance of shared preferences
   */
  public static synchronized BindDataSharedPreferences instance() {
    if (instance==null) {
      instance=new BindDataSharedPreferences();
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
     * modifier for property exampleMultiList
     */
    public BindEditor putExampleMultiList(Set<String> value) {
      if (value!=null)  {
        String temp=serializeExampleMultiList(value);
        editor.putString("example_multi_list",temp);
      }  else  {
        editor.remove("example_multi_list");
      }

      return this;
    }

    /**
     * modifier for property notificationNewMessageRingtone
     */
    public BindEditor putNotificationNewMessageRingtone(String value) {
      editor.putString("notification_new_message_ringtone",value);

      return this;
    }

    /**
     * modifier for property notificationsNewMessageVibrate
     */
    public BindEditor putNotificationsNewMessageVibrate(boolean value) {
      editor.putBoolean("notifications_new_message_vibrate",(boolean)value);

      return this;
    }

    /**
     * modifier for property syncFrequency
     */
    public BindEditor putSyncFrequency(String value) {
      editor.putString("sync_frequency",value);

      return this;
    }
  }
}
