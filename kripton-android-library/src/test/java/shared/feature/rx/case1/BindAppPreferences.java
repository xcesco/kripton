package shared.feature.rx.case1;

import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.livedata.KriptonComputableLiveData;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedata.persistence0.BindApp0DaoFactory;
import sqlite.feature.livedata.persistence0.BindApp0DataSource;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is the shared preference binder defined for AppPreferences
 *
 * @see AppPreferences
 */
public class BindAppPreferences extends AbstractSharedPreference {
	/**
	 * instance of shared preferences
	 */
	private static BindAppPreferences instance;

	/**
	 * working instance of bean
	 */
	private final AppPreferences defaultBean;

	/**
	 * subject for field name - shared pref name
	 */
	private Subject<String> nameSubject = BehaviorSubject.create();

	/**
	 * subject for field description - shared pref description
	 */
	private Subject<String> descriptionSubject = BehaviorSubject.create();

	/**
	 * subject for field valueFloat - shared pref value_float
	 */
	private Subject<Float> valueFloatSubject = BehaviorSubject.create();

	/**
	 * subject for field valueBoolean - shared pref value_boolean
	 */
	private Subject<Boolean> valueBooleanSubject = BehaviorSubject.create();

	/**
	 * subject for field stringArray - shared pref string_array
	 */
	private Subject<String[]> stringArraySubject = BehaviorSubject.create();

	/**
	 * subject for field stringList - shared pref string_list
	 */
	private Subject<List<String>> stringListSubject = BehaviorSubject.create();

	/**
	 * subject for field valueInt - shared pref value_int
	 */
	private Subject<Integer> valueIntSubject = BehaviorSubject.create();

	/**
	 * subject for field valueLong - shared pref value_long
	 */
	private Subject<Long> valueLongSubject = BehaviorSubject.create();

	/**
	 * Listener used to propagate shared prefs changes through RX
	 */
	private SharedPreferences.OnSharedPreferenceChangeListener rxListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			switch (key) {
			// name - name
			case "name": {
				String _value = sharedPreferences.getString("name", defaultBean.name);
				if (_value != null) {
					nameSubject.onNext(_value);
				}
				return;
			}
			// description - description
			case "description": {
				String _value = sharedPreferences.getString("description", defaultBean.getDescription());
				if (_value != null) {
					descriptionSubject.onNext(_value);
				}
				return;
			}
			// value_float - valueFloat
			case "value_float": {
				float _value = sharedPreferences.getFloat("value_float", defaultBean.valueFloat);
				valueFloatSubject.onNext(_value);
				return;
			}
			// value_boolean - valueBoolean
			case "value_boolean": {
				boolean _value = (boolean) sharedPreferences.getBoolean("value_boolean",
						(boolean) defaultBean.valueBoolean);
				valueBooleanSubject.onNext(_value);
				return;
			}
			// string_array - stringArray
			case "string_array": {
				String temp = sharedPreferences.getString("string_array", null);
				String[] _value = StringUtils.hasText(temp) ? parseStringArray(temp) : defaultBean.getStringArray();

				if (_value != null) {
					stringArraySubject.onNext(_value);
				}
				return;
			}
			// string_list - stringList
			case "string_list": {
				String temp = sharedPreferences.getString("string_list", null);
				List<String> _value = StringUtils.hasText(temp) ? parseStringList(temp) : defaultBean.stringList;

				if (_value != null) {
					stringListSubject.onNext(_value);
				}
				return;
			}
			// value_int - valueInt
			case "value_int": {
				int _value = (int) sharedPreferences.getInt("value_int", (int) defaultBean.valueInt);
				valueIntSubject.onNext(_value);
				return;
			}
			// value_long - valueLong
			case "value_long": {
				Long _value = sharedPreferences.getLong("value_long",
						(defaultBean.valueLong == null ? 0L : defaultBean.valueLong));
				if (_value != null) {
					valueLongSubject.onNext(_value);
				}
				return;
			}
			default:
				return;
			}
		}
	};

	@SuppressWarnings("rawtypes")
	private List<Pair<String, WeakReference<KriptonComputableLiveData>>> liveDatas = Collections
			.synchronizedList(new ArrayList<Pair<String, WeakReference<KriptonComputableLiveData>>>());

	/**
	 * Listener used to propagate shared prefs changes through RX
	 */
	private SharedPreferences.OnSharedPreferenceChangeListener liveDataListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
		@Override
		public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {
			KriptonLibrary.getExecutorService().execute(new Runnable() {
				@Override
				public void run() {
					switch (key) {
					// name - name
					case "name": {
						String _value = sharedPreferences.getString("name", defaultBean.name);
						updateLiveData("name", _value);
						return;
					}
					// description - description
					case "description": {
						String _value = sharedPreferences.getString("description", defaultBean.getDescription());
						updateLiveData("description", _value);
						return;
					}
					// value_float - valueFloat
					case "value_float": {
						float _value = sharedPreferences.getFloat("value_float", defaultBean.valueFloat);
						updateLiveData("value_float", _value);
						return;
					}
					// value_boolean - valueBoolean
					case "value_boolean": {
						boolean _value = (boolean) sharedPreferences.getBoolean("value_boolean",
								(boolean) defaultBean.valueBoolean);
						updateLiveData("value_boolean", _value);
						return;
					}
					// string_array - stringArray
					case "string_array": {
						String temp = sharedPreferences.getString("string_array", null);
						String[] _value = StringUtils.hasText(temp) ? parseStringArray(temp)
								: defaultBean.getStringArray();

						updateLiveData("string_array", _value);
						return;
					}
					// string_list - stringList
					case "string_list": {
						String temp = sharedPreferences.getString("string_list", null);
						List<String> _value = StringUtils.hasText(temp) ? parseStringList(temp)
								: defaultBean.stringList;

						updateLiveData("string_list", _value);
						return;
					}
					// value_int - valueInt
					case "value_int": {
						int _value = (int) sharedPreferences.getInt("value_int", (int) defaultBean.valueInt);
						updateLiveData("value_int", _value);
						return;
					}
					// value_long - valueLong
					case "value_long": {
						Long _value = sharedPreferences.getLong("value_long",
								(defaultBean.valueLong == null ? 0L : defaultBean.valueLong));
						updateLiveData("value_long", _value);
						return;
					}
					default:
						return;
					}
				}
			});
		}
	};

	/**
	 * constructor
	 */
	private BindAppPreferences() {
		createPrefs();
		defaultBean = new AppPreferences();
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
		prefs = PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
		prefs.registerOnSharedPreferenceChangeListener(rxListener);
		prefs.registerOnSharedPreferenceChangeListener(liveDataListener);
	}

	/**
	 * force to refresh values
	 */
	public BindAppPreferences refresh() {
		createPrefs();
		return this;
	}

	/**
	 * Obtains an observable to <code>name</code> property
	 *
	 * @return an observable to <code>name</code> property
	 */
	public Subject<String> getNameAsObservable() {
		return nameSubject;
	}

	/**
	 * Obtains an observable to <code>description</code> property
	 *
	 * @return an observable to <code>description</code> property
	 */
	public Subject<String> getDescriptionAsObservable() {
		return descriptionSubject;
	}

	/**
	 * Obtains an observable to <code>valueFloat</code> property
	 *
	 * @return an observable to <code>valueFloat</code> property
	 */
	public Subject<Float> getValueFloatAsObservable() {
		return valueFloatSubject;
	}

	/**
	 * Obtains an observable to <code>valueBoolean</code> property
	 *
	 * @return an observable to <code>valueBoolean</code> property
	 */
	public Subject<Boolean> getValueBooleanAsObservable() {
		return valueBooleanSubject;
	}

	/**
	 * Obtains an observable to <code>stringArray</code> property
	 *
	 * @return an observable to <code>stringArray</code> property
	 */
	public Subject<String[]> getStringArrayAsObservable() {
		return stringArraySubject;
	}

	/**
	 * Obtains an observable to <code>stringList</code> property
	 *
	 * @return an observable to <code>stringList</code> property
	 */
	public Subject<List<String>> getStringListAsObservable() {
		return stringListSubject;
	}

	/**
	 * Obtains an observable to <code>valueInt</code> property
	 *
	 * @return an observable to <code>valueInt</code> property
	 */
	public Subject<Integer> getValueIntAsObservable() {
		return valueIntSubject;
	}

	/**
	 * Obtains an observable to <code>valueLong</code> property
	 *
	 * @return an observable to <code>valueLong</code> property
	 */
	public Subject<Long> getValueLongAsObservable() {
		return valueLongSubject;
	}

	@SuppressWarnings("rawtypes")
	protected void registryLiveData(String key, KriptonComputableLiveData<?> value) {
		liveDatas.add(new Pair<String, WeakReference<KriptonComputableLiveData>>(key, new WeakReference<KriptonComputableLiveData>(value)));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void updateLiveData(String key, Object value) {
		for (Pair<String, WeakReference<KriptonComputableLiveData>> item : liveDatas) {
			if (item.value0.equals(key) && item.value1.get() != null) {
				item.value1.get().invalidate();
			}
		}
	}

	/**
	 * Obtains an LiveData to <code>name</code> property
	 *
	 * @return an LiveData to <code>name</code> property
	 */
	public MutableLiveData<String> getNameAsLiveData() {
		MutableLiveData<String> liveData = new MutableLiveData<String>();
		registryLiveData("name", liveData);
		return liveData;
	}

	/**
	 * Obtains an LiveData to <code>description</code> property
	 *
	 * @return an LiveData to <code>description</code> property
	 */
	public MutableLiveData<String> getDescriptionAsLiveData() {
		final KriptonComputableLiveData<String> builder = new KriptonComputableLiveData<String>() {
			@Override
			protected String compute() {
				BindAppPreferences.this.refresh();
				return BindAppPreferences.this.getDescription();
			}
		};
		registryLiveData("description", builder);
		return builder.getLiveData();
	}

	/**
	 * Obtains an LiveData to <code>valueFloat</code> property
	 *
	 * @return an LiveData to <code>valueFloat</code> property
	 */
	public MutableLiveData<Float> getValueFloatAsLiveData() {
		MutableLiveData<Float> liveData = new MutableLiveData<Float>();
		registryLiveData("value_float", liveData);
		return liveData;
	}

	/**
	 * Obtains an LiveData to <code>valueBoolean</code> property
	 *
	 * @return an LiveData to <code>valueBoolean</code> property
	 */
	public MutableLiveData<Boolean> getValueBooleanAsLiveData() {
		MutableLiveData<Boolean> liveData = new MutableLiveData<Boolean>();
		registryLiveData("value_boolean", liveData);
		return liveData;
	}

	/**
	 * Obtains an LiveData to <code>stringArray</code> property
	 *
	 * @return an LiveData to <code>stringArray</code> property
	 */
	public MutableLiveData<String[]> getStringArrayAsLiveData() {
		MutableLiveData<String[]> liveData = new MutableLiveData<String[]>();
		registryLiveData("string_array", liveData);
		return liveData;
	}

	/**
	 * Obtains an LiveData to <code>stringList</code> property
	 *
	 * @return an LiveData to <code>stringList</code> property
	 */
	public MutableLiveData<List<String>> getStringListAsLiveData() {
		MutableLiveData<List<String>> liveData = new MutableLiveData<List<String>>();
		registryLiveData("string_list", liveData);
		return liveData;
	}

	/**
	 * Obtains an LiveData to <code>valueInt</code> property
	 *
	 * @return an LiveData to <code>valueInt</code> property
	 */
	public MutableLiveData<Integer> getValueIntAsLiveData() {
		MutableLiveData<Integer> liveData = new MutableLiveData<Integer>();
		registryLiveData("value_int", liveData);
		return liveData;
	}

	/**
	 * Obtains an LiveData to <code>valueLong</code> property
	 *
	 * @return an LiveData to <code>valueLong</code> property
	 */
	public MutableLiveData<Long> getValueLongAsLiveData() {
		MutableLiveData<Long> liveData = new MutableLiveData<Long>();
		registryLiveData("value_long", liveData);
		return liveData;
	}

	/**
	 * reset shared preferences
	 */
	public void reset() {
		AppPreferences bean = new AppPreferences();
		write(bean);
	}

	/**
	 * read bean entirely
	 *
	 * @return read bean
	 */
	public AppPreferences read() {
		AppPreferences bean = new AppPreferences();
		bean.name = prefs.getString("name", bean.name);
		bean.setDescription(prefs.getString("description", bean.getDescription()));
		bean.valueFloat = prefs.getFloat("value_float", bean.valueFloat);
		bean.valueBoolean = (boolean) prefs.getBoolean("value_boolean", (boolean) bean.valueBoolean);
		{
			String temp = prefs.getString("string_array", null);
			bean.setStringArray(StringUtils.hasText(temp) ? parseStringArray(temp) : defaultBean.getStringArray());
		}

		{
			String temp = prefs.getString("string_list", null);
			bean.stringList = StringUtils.hasText(temp) ? parseStringList(temp) : defaultBean.stringList;
		}

		bean.valueInt = (int) prefs.getInt("value_int", (int) bean.valueInt);
		bean.valueLong = prefs.getLong("value_long", (bean.valueLong == null ? 0L : bean.valueLong));

		return bean;
	}

	/**
	 * write bean entirely
	 *
	 * @param bean
	 *            bean to entirely write
	 */
	public void write(AppPreferences bean) {
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("name", bean.name);

		editor.putString("description", bean.getDescription());

		editor.putFloat("value_float", bean.valueFloat);

		editor.putBoolean("value_boolean", (boolean) bean.valueBoolean);

		if (bean.getStringArray() != null) {
			String temp = serializeStringArray(bean.getStringArray());
			editor.putString("string_array", temp);
		} else {
			editor.remove("string_array");
		}

		if (bean.stringList != null) {
			String temp = serializeStringList(bean.stringList);
			editor.putString("string_list", temp);
		} else {
			editor.remove("string_list");
		}

		editor.putInt("value_int", (int) bean.valueInt);

		if (bean.valueLong != null) {
			editor.putLong("value_long", bean.valueLong);
		}

		editor.commit();
	}

	/**
	 * read property name
	 *
	 * @return property name value
	 */
	public String getName() {
		return prefs.getString("name", defaultBean.name);
	}

	/**
	 * read property description
	 *
	 * @return property description value
	 */
	public String getDescription() {
		return prefs.getString("description", defaultBean.getDescription());
	}

	/**
	 * read property valueFloat
	 *
	 * @return property valueFloat value
	 */
	public float getValueFloat() {
		return prefs.getFloat("value_float", defaultBean.valueFloat);
	}

	/**
	 * read property valueBoolean
	 *
	 * @return property valueBoolean value
	 */
	public boolean getValueBoolean() {
		return (boolean) prefs.getBoolean("value_boolean", (boolean) defaultBean.valueBoolean);
	}

	/**
	 * read property stringArray
	 *
	 * @return property stringArray value
	 */
	public String[] getStringArray() {
		String temp = prefs.getString("string_array", null);
		return StringUtils.hasText(temp) ? parseStringArray(temp) : defaultBean.getStringArray();
	}

	/**
	 * read property stringList
	 *
	 * @return property stringList value
	 */
	public List<String> getStringList() {
		String temp = prefs.getString("string_list", null);
		return StringUtils.hasText(temp) ? parseStringList(temp) : defaultBean.stringList;
	}

	/**
	 * read property valueInt
	 *
	 * @return property valueInt value
	 */
	public int getValueInt() {
		return (int) prefs.getInt("value_int", (int) defaultBean.valueInt);
	}

	/**
	 * read property valueLong
	 *
	 * @return property valueLong value
	 */
	public Long getValueLong() {
		return prefs.getLong("value_long", (defaultBean.valueLong == null ? 0L : defaultBean.valueLong));
	}

	/**
	 * for attribute stringArray serialization
	 */
	protected String serializeStringArray(String[] value) {
		if (value == null) {
			return null;
		}
		KriptonJsonContext context = KriptonBinder.jsonBind();
		try (KriptonByteArrayOutputStream stream = new KriptonByteArrayOutputStream();
				JacksonWrapperSerializer wrapper = context.createSerializer(stream)) {
			JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
			jacksonSerializer.writeStartObject();
			int fieldCount = 0;
			if (value != null) {
				fieldCount++;
				int n = value.length;
				String item;
				// write wrapper tag
				jacksonSerializer.writeFieldName("stringArray");
				jacksonSerializer.writeStartArray();
				for (int i = 0; i < n; i++) {
					item = value[i];
					if (item == null) {
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
		} catch (Exception e) {
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * for attribute stringArray parsing
	 */
	protected String[] parseStringArray(String input) {
		if (input == null) {
			return null;
		}
		KriptonJsonContext context = KriptonBinder.jsonBind();
		try (JacksonWrapperParser wrapper = context.createParser(input)) {
			JsonParser jacksonParser = wrapper.jacksonParser;
			// START_OBJECT
			jacksonParser.nextToken();
			// value of "element"
			jacksonParser.nextValue();
			String[] result = null;
			if (jacksonParser.currentToken() == JsonToken.START_ARRAY) {
				ArrayList<String> collection = new ArrayList<>();
				String item = null;
				while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
					if (jacksonParser.currentToken() == JsonToken.VALUE_NULL) {
						item = null;
					} else {
						item = jacksonParser.getText();
					}
					collection.add(item);
				}
				result = CollectionUtils.asArray(collection, new String[collection.size()]);
			}
			return result;
		} catch (Exception e) {
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * for attribute stringList serialization
	 */
	protected String serializeStringList(List<String> value) {
		if (value == null) {
			return null;
		}
		KriptonJsonContext context = KriptonBinder.jsonBind();
		try (KriptonByteArrayOutputStream stream = new KriptonByteArrayOutputStream();
				JacksonWrapperSerializer wrapper = context.createSerializer(stream)) {
			JsonGenerator jacksonSerializer = wrapper.jacksonGenerator;
			jacksonSerializer.writeStartObject();
			int fieldCount = 0;
			if (value != null) {
				fieldCount++;
				int n = value.size();
				String item;
				// write wrapper tag
				jacksonSerializer.writeFieldName("stringList");
				jacksonSerializer.writeStartArray();
				for (int i = 0; i < n; i++) {
					item = value.get(i);
					if (item == null) {
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
		} catch (Exception e) {
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * for attribute stringList parsing
	 */
	protected List<String> parseStringList(String input) {
		if (input == null) {
			return null;
		}
		KriptonJsonContext context = KriptonBinder.jsonBind();
		try (JacksonWrapperParser wrapper = context.createParser(input)) {
			JsonParser jacksonParser = wrapper.jacksonParser;
			// START_OBJECT
			jacksonParser.nextToken();
			// value of "element"
			jacksonParser.nextValue();
			List<String> result = null;
			if (jacksonParser.currentToken() == JsonToken.START_ARRAY) {
				ArrayList<String> collection = new ArrayList<>();
				String item = null;
				while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
					if (jacksonParser.currentToken() == JsonToken.VALUE_NULL) {
						item = null;
					} else {
						item = jacksonParser.getText();
					}
					collection.add(item);
				}
				result = collection;
			}
			return result;
		} catch (Exception e) {
			throw (new KriptonRuntimeException(e.getMessage()));
		}
	}

	/**
	 * get instance of shared preferences
	 */
	public static synchronized BindAppPreferences instance() {
		if (instance == null) {
			instance = new BindAppPreferences();
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
			editor.putString("name", value);

			return this;
		}

		/**
		 * modifier for property description
		 */
		public BindEditor putDescription(String value) {
			editor.putString("description", value);

			return this;
		}

		/**
		 * modifier for property valueFloat
		 */
		public BindEditor putValueFloat(float value) {
			editor.putFloat("value_float", value);

			return this;
		}

		/**
		 * modifier for property valueBoolean
		 */
		public BindEditor putValueBoolean(boolean value) {
			editor.putBoolean("value_boolean", (boolean) value);

			return this;
		}

		/**
		 * modifier for property stringArray
		 */
		public BindEditor putStringArray(String[] value) {
			if (value != null) {
				String temp = serializeStringArray(value);
				editor.putString("string_array", temp);
			} else {
				editor.remove("string_array");
			}

			return this;
		}

		/**
		 * modifier for property stringList
		 */
		public BindEditor putStringList(List<String> value) {
			if (value != null) {
				String temp = serializeStringList(value);
				editor.putString("string_list", temp);
			} else {
				editor.remove("string_list");
			}

			return this;
		}

		/**
		 * modifier for property valueInt
		 */
		public BindEditor putValueInt(int value) {
			editor.putInt("value_int", (int) value);

			return this;
		}

		/**
		 * modifier for property valueLong
		 */
		public BindEditor putValueLong(Long value) {
			if (value != null) {
				editor.putLong("value_long", value);
			}

			return this;
		}
	}
}
