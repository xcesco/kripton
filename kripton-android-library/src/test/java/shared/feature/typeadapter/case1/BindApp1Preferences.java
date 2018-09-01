package shared.feature.typeadapter.case1;

import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.PrefsTypeAdapterUtils;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import shared.feature.typeadapter.case2.IntTypeAdapter;

/**
 * This class is the shared preference binder defined for App1Preferences
 *
 * @see App1Preferences
 */
public class BindApp1Preferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindApp1Preferences instance;

  /**
   * working instance of bean
   */
  private final App1Preferences defaultBean;

  @SuppressWarnings("rawtypes")
  private List<Pair<String, WeakReference<LiveDataHandler>>> liveDatas = new CopyOnWriteArrayList<Pair<String, WeakReference<LiveDataHandler>>>();

  /**
   * Listener used to propagate shared prefs changes through RX
   */
  private SharedPreferences.OnSharedPreferenceChangeListener liveDataListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences,
        final String key) {
      KriptonLibrary.getExecutorService().execute(new Runnable() {
        @Override
        public void run() {
          switch (key) {
            // value_set - valueSet
            case "value_set": {
            Set<String> temp=sharedPreferences.getStringSet("value_set", defaultBean.valueSet);
            HashSet<String> _value=new HashSet<String>(temp);

            updateLiveData("value_set", _value);
            return;
            }
            // right - right
            case "right": {
            int _value=PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(sharedPreferences.getString("right", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(defaultBean.right)));
            updateLiveData("right", _value);
            return;
            }
            default: return;
          }
        }
      });
    }
  };

  /**
   * constructor
   */
  private BindApp1Preferences() {
    createPrefs();
    defaultBean=new App1Preferences();
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
    prefs.registerOnSharedPreferenceChangeListener(liveDataListener);
  }

  /**
   * force to refresh values
   */
  public BindApp1Preferences refresh() {
    createPrefs();
    return this;
  }

  @SuppressWarnings("rawtypes")
  protected void registryLiveData(String key, LiveDataHandler value) {
    liveDatas.add(new Pair<String , WeakReference<LiveDataHandler>>(key, new WeakReference<LiveDataHandler>(value)));
  }

  @SuppressWarnings("rawtypes")
  protected void updateLiveData(String key, Object value) {
    for (Pair<String, WeakReference<LiveDataHandler>> item : liveDatas) {
      if (item.value0.equals(key) && item.value1.get() != null) {
        item.value1.get().invalidate();
      }
    }
  }

  /**
   * Obtains an LiveData to <code>valueSet</code> property
   *
   * @return
   * an LiveData to <code>valueSet</code> property
   */
  public MutableLiveData<HashSet<String>> getValueSetAsLiveData() {
    KriptonLiveDataHandlerImpl<HashSet<String>> liveData=new KriptonLiveDataHandlerImpl<HashSet<String>>() {
      @Override
      protected HashSet<String> compute() {
        BindApp1Preferences.this.refresh();
        return BindApp1Preferences.this.getValueSet();
      }
    };
    registryLiveData("value_set", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>right</code> property
   *
   * @return
   * an LiveData to <code>right</code> property
   */
  public MutableLiveData<Integer> getRightAsLiveData() {
    KriptonLiveDataHandlerImpl<Integer> liveData=new KriptonLiveDataHandlerImpl<Integer>() {
      @Override
      protected Integer compute() {
        BindApp1Preferences.this.refresh();
        return BindApp1Preferences.this.getRight();
      }
    };
    registryLiveData("right", liveData);
    return liveData.getLiveData();
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    App1Preferences bean=new App1Preferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public App1Preferences read() {
    App1Preferences bean=new App1Preferences();
     {
      Set<String> temp=prefs.getStringSet("value_set", defaultBean.valueSet);
      bean.valueSet=new HashSet<String>(temp);
    }

    bean.right=PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("right", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(defaultBean.right)));

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(App1Preferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putStringSet("value_set",bean.valueSet);

    editor.putString("right",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(bean.right));


    editor.commit();
  }

  /**
   * reads property <code>valueSet</code> from shared pref with key <code>value_set</code>
   *
   * @return property valueSet value
   */
  public HashSet<String> getValueSet() {
    Set<String> temp=prefs.getStringSet("value_set", defaultBean.valueSet);
    return new HashSet<String>(temp);
  }

  /**
   * reads property <code>right</code> from shared pref with key <code>right</code>
   *
   * @return property right value
   */
  public int getRight() {
    return PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toJava(prefs.getString("right", PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(defaultBean.right)));}

  /**
   * get instance of shared preferences
   */
  public static synchronized BindApp1Preferences getInstance() {
    if (instance==null) {
      instance=new BindApp1Preferences();
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
     * modifier for property valueSet
     */
    public BindEditor putValueSet(HashSet<String> value) {
      editor.putStringSet("value_set",value);

      return this;
    }

    /**
     * remove property valueSet
     */
    public BindEditor removeValueSet() {
      editor.remove("value_set");
      return this;
    }

    /**
     * modifier for property right
     */
    public BindEditor putRight(int value) {
      editor.putString("right",PrefsTypeAdapterUtils.getAdapter(IntTypeAdapter.class).toData(value));

      return this;
    }

    /**
     * remove property right
     */
    public BindEditor removeRight() {
      editor.remove("right");
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
