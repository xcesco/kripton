package sqlite.kripton60;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * This class is the shared preference binder defined for Bean
 *
 * @see Bean
 */
public class BindBeanSharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindBeanSharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Bean defaultBean;

  /**
   * subject for field valueBoolType - shared pref value_bool_type
   */
  private Subject<Boolean> valueBoolTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueBool - shared pref value_bool
   */
  private Subject<Boolean> valueBoolSubject = BehaviorSubject.create();

  /**
   * subject for field valueByteType - shared pref value_byte_type
   */
  private Subject<Byte> valueByteTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueByte - shared pref value_byte
   */
  private Subject<Byte> valueByteSubject = BehaviorSubject.create();

  /**
   * subject for field valueShortType - shared pref value_short_type
   */
  private Subject<Short> valueShortTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueShort - shared pref value_short
   */
  private Subject<Short> valueShortSubject = BehaviorSubject.create();

  /**
   * subject for field valueIntType - shared pref value_int_type
   */
  private Subject<Integer> valueIntTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueInt - shared pref value_int
   */
  private Subject<Integer> valueIntSubject = BehaviorSubject.create();

  /**
   * subject for field valueString - shared pref value_string
   */
  private Subject<String> valueStringSubject = BehaviorSubject.create();

  /**
   * subject for field valueCharType - shared pref value_char_type
   */
  private Subject<Character> valueCharTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueChar - shared pref value_char
   */
  private Subject<Character> valueCharSubject = BehaviorSubject.create();

  /**
   * subject for field valueFloatType - shared pref value_float_type
   */
  private Subject<Float> valueFloatTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueFloat - shared pref value_float
   */
  private Subject<Float> valueFloatSubject = BehaviorSubject.create();

  /**
   * subject for field valueBigInteger - shared pref value_big_integer
   */
  private Subject<BigInteger> valueBigIntegerSubject = BehaviorSubject.create();

  /**
   * subject for field valueBigDecimal - shared pref value_big_decimal
   */
  private Subject<BigDecimal> valueBigDecimalSubject = BehaviorSubject.create();

  /**
   * subject for field valueEnumType - shared pref value_enum_type
   */
  private Subject<EnumType> valueEnumTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueLongType - shared pref value_long_type
   */
  private Subject<Long> valueLongTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueLong - shared pref value_long
   */
  private Subject<Long> valueLongSubject = BehaviorSubject.create();

  /**
   * subject for field valueDoubleType - shared pref value_double_type
   */
  private Subject<Double> valueDoubleTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueDouble - shared pref value_double
   */
  private Subject<Double> valueDoubleSubject = BehaviorSubject.create();

  /**
   * subject for field valueLocale - shared pref value_locale
   */
  private Subject<Locale> valueLocaleSubject = BehaviorSubject.create();

  /**
   * subject for field valueCalendar - shared pref value_calendar
   */
  private Subject<Calendar> valueCalendarSubject = BehaviorSubject.create();

  /**
   * subject for field valueDate - shared pref value_date
   */
  private Subject<Date> valueDateSubject = BehaviorSubject.create();

  /**
   * subject for field valueUrl - shared pref value_url
   */
  private Subject<URL> valueUrlSubject = BehaviorSubject.create();

  /**
   * subject for field valueTime - shared pref value_time
   */
  private Subject<Time> valueTimeSubject = BehaviorSubject.create();

  /**
   * subject for field valueCurrency - shared pref value_currency
   */
  private Subject<Currency> valueCurrencySubject = BehaviorSubject.create();

  /**
   * subject for field valueTimeZone - shared pref value_time_zone
   */
  private Subject<TimeZone> valueTimeZoneSubject = BehaviorSubject.create();

  /**
   * subject for field valueTimeList - shared pref value_time_list
   */
  private Subject<List<Time>> valueTimeListSubject = BehaviorSubject.create();

  /**
   * subject for field valueStrinList - shared pref value_strin_list
   */
  private Subject<LinkedList<String>> valueStrinListSubject = BehaviorSubject.create();

  /**
   * subject for field valueLongList - shared pref value_long_list
   */
  private Subject<LinkedList<Long>> valueLongListSubject = BehaviorSubject.create();

  /**
   * subject for field valueByteArray - shared pref value_byte_array
   */
  private Subject<byte[]> valueByteArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueBean - shared pref value_bean
   */
  private Subject<Bean> valueBeanSubject = BehaviorSubject.create();

  /**
   * subject for field valueLongTypeArray - shared pref value_long_type_array
   */
  private Subject<long[]> valueLongTypeArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueLongArray - shared pref value_long_array
   */
  private Subject<Long[]> valueLongArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueBeanArray - shared pref value_bean_array
   */
  private Subject<Bean[]> valueBeanArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueStringArray - shared pref value_string_array
   */
  private Subject<String[]> valueStringArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueCharList - shared pref value_char_list
   */
  private Subject<LinkedList<Character>> valueCharListSubject = BehaviorSubject.create();

  /**
   * subject for field valueCharTypeArray - shared pref value_char_type_array
   */
  private Subject<char[]> valueCharTypeArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueCharArray - shared pref value_char_array
   */
  private Subject<Character[]> valueCharArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueMapStringBean - shared pref value_map_string_bean
   */
  private Subject<Map<String, Bean>> valueMapStringBeanSubject = BehaviorSubject.create();

  /**
   * subject for field valueLinkedMapStringBean - shared pref value_linked_map_string_bean
   */
  private Subject<LinkedHashMap<String, Bean>> valueLinkedMapStringBeanSubject = BehaviorSubject.create();

  /**
   * subject for field valueSetString - shared pref value_set_string
   */
  private Subject<Set<String>> valueSetStringSubject = BehaviorSubject.create();

  /**
   * List of tables compose datasource
   */
  private SharedPreferences.OnSharedPreferenceChangeListener prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
      switch (key) {
        // value_bool_type - valueBoolType
        case "value_bool_type": {
        boolean _value=(boolean)sharedPreferences.getBoolean("value_bool_type", (boolean)defaultBean.valueBoolType);
        valueBoolTypeSubject.onNext(_value); return;
        }
        // value_bool - valueBool
        case "value_bool": {
        Boolean _value=(boolean)sharedPreferences.getBoolean("value_bool", (boolean)(defaultBean.valueBool==null?false:defaultBean.valueBool));
        valueBoolSubject.onNext(_value); return;
        }
        // value_byte_type - valueByteType
        case "value_byte_type": {
        byte _value=(byte)sharedPreferences.getInt("value_byte_type", (byte)defaultBean.valueByteType);
        valueByteTypeSubject.onNext(_value); return;
        }
        // value_byte - valueByte
        case "value_byte": {
        Byte _value=(byte)sharedPreferences.getInt("value_byte", (byte)(defaultBean.valueByte==null?(byte)0:defaultBean.valueByte));
        valueByteSubject.onNext(_value); return;
        }
        // value_short_type - valueShortType
        case "value_short_type": {
        short _value=(short)sharedPreferences.getInt("value_short_type", (short)defaultBean.valueShortType);
        valueShortTypeSubject.onNext(_value); return;
        }
        // value_short - valueShort
        case "value_short": {
        Short _value=(short)sharedPreferences.getInt("value_short", (short)(defaultBean.valueShort==null?(short)0:defaultBean.valueShort));
        valueShortSubject.onNext(_value); return;
        }
        // value_int_type - valueIntType
        case "value_int_type": {
        int _value=(int)sharedPreferences.getInt("value_int_type", (int)defaultBean.valueIntType);
        valueIntTypeSubject.onNext(_value); return;
        }
        // value_int - valueInt
        case "value_int": {
        Integer _value=(int)sharedPreferences.getInt("value_int", (int)(defaultBean.valueInt==null?0:defaultBean.valueInt));
        valueIntSubject.onNext(_value); return;
        }
        // value_string - valueString
        case "value_string": {
        String _value=sharedPreferences.getString("value_string", defaultBean.valueString);
        valueStringSubject.onNext(_value); return;
        }
        // value_char_type - valueCharType
        case "value_char_type": {
        char _value=(char)sharedPreferences.getInt("value_char_type", (char)defaultBean.valueCharType);
        valueCharTypeSubject.onNext(_value); return;
        }
        // value_char - valueChar
        case "value_char": {
        Character _value=(char)sharedPreferences.getInt("value_char", (char)(defaultBean.valueChar==null?(char)0:defaultBean.valueChar));
        valueCharSubject.onNext(_value); return;
        }
        // value_float_type - valueFloatType
        case "value_float_type": {
        float _value=sharedPreferences.getFloat("value_float_type", defaultBean.valueFloatType);
        valueFloatTypeSubject.onNext(_value); return;
        }
        // value_float - valueFloat
        case "value_float": {
        Float _value=sharedPreferences.getFloat("value_float", (defaultBean.valueFloat==null?0F:defaultBean.valueFloat));
        valueFloatSubject.onNext(_value); return;
        }
        // value_big_integer - valueBigInteger
        case "value_big_integer": {
        String temp=sharedPreferences.getString("value_big_integer", "0");
        BigInteger _value=(StringUtils.hasText(temp)) ? new BigInteger(temp): null;

        valueBigIntegerSubject.onNext(_value); return;
        }
        // value_big_decimal - valueBigDecimal
        case "value_big_decimal": {
        String temp=sharedPreferences.getString("value_big_decimal", "0");
        BigDecimal _value=(StringUtils.hasText(temp)) ? new BigDecimal(temp): null;

        valueBigDecimalSubject.onNext(_value); return;
        }
        // value_enum_type - valueEnumType
        case "value_enum_type": {
        String temp=sharedPreferences.getString("value_enum_type", null);
        EnumType _value=(StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;

        valueEnumTypeSubject.onNext(_value); return;
        }
        // value_long_type - valueLongType
        case "value_long_type": {
        long _value=sharedPreferences.getLong("value_long_type", defaultBean.valueLongType);
        valueLongTypeSubject.onNext(_value); return;
        }
        // value_long - valueLong
        case "value_long": {
        Long _value=sharedPreferences.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));
        valueLongSubject.onNext(_value); return;
        }
        // value_double_type - valueDoubleType
        case "value_double_type": {
        String temp=sharedPreferences.getString("value_double_type", null);
        double _value=(StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;

        valueDoubleTypeSubject.onNext(_value); return;
        }
        // value_double - valueDouble
        case "value_double": {
        String temp=sharedPreferences.getString("value_double", null);
        Double _value=(StringUtils.hasText(temp)) ? Double.valueOf(temp): null;

        valueDoubleSubject.onNext(_value); return;
        }
        // value_locale - valueLocale
        case "value_locale": {
        String temp=sharedPreferences.getString("value_locale", null);
        Locale _value=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;
        valueLocaleSubject.onNext(_value); return;
        }
        // value_calendar - valueCalendar
        case "value_calendar": {
        String temp=sharedPreferences.getString("value_calendar", null);
        Calendar _value=(StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;
        valueCalendarSubject.onNext(_value); return;
        }
        // value_date - valueDate
        case "value_date": {
        String temp=sharedPreferences.getString("value_date", null);
        Date _value=(StringUtils.hasText(temp)) ? DateUtils.read(temp): null;
        valueDateSubject.onNext(_value); return;
        }
        // value_url - valueUrl
        case "value_url": {
        String temp=sharedPreferences.getString("value_url", null);
        URL _value=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;
        valueUrlSubject.onNext(_value); return;
        }
        // value_time - valueTime
        case "value_time": {
        String temp=sharedPreferences.getString("value_time", null);
        Time _value=(StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;
        valueTimeSubject.onNext(_value); return;
        }
        // value_currency - valueCurrency
        case "value_currency": {
        String temp=sharedPreferences.getString("value_currency", null);
        Currency _value=(StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;
        valueCurrencySubject.onNext(_value); return;
        }
        // value_time_zone - valueTimeZone
        case "value_time_zone": {
        String temp=sharedPreferences.getString("value_time_zone", null);
        TimeZone _value=(StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;
        valueTimeZoneSubject.onNext(_value); return;
        }
        // value_time_list - valueTimeList
        case "value_time_list": {
        String temp=sharedPreferences.getString("value_time_list", null);
        List<Time> _value=StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.valueTimeList;

        valueTimeListSubject.onNext(_value); return;
        }
        // value_strin_list - valueStrinList
        case "value_strin_list": {
        String temp=sharedPreferences.getString("value_strin_list", null);
        LinkedList<String> _value=StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.valueStrinList;

        valueStrinListSubject.onNext(_value); return;
        }
        // value_long_list - valueLongList
        case "value_long_list": {
        String temp=sharedPreferences.getString("value_long_list", null);
        LinkedList<Long> _value=StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.valueLongList;

        valueLongListSubject.onNext(_value); return;
        }
        // value_byte_array - valueByteArray
        case "value_byte_array": {
        String temp=sharedPreferences.getString("value_byte_array", null);
        byte[] _value=StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.valueByteArray;

        valueByteArraySubject.onNext(_value); return;
        }
        // value_bean - valueBean
        case "value_bean": {
        String temp=sharedPreferences.getString("value_bean", null);
        Bean _value=StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.valueBean;

        valueBeanSubject.onNext(_value); return;
        }
        // value_long_type_array - valueLongTypeArray
        case "value_long_type_array": {
        String temp=sharedPreferences.getString("value_long_type_array", null);
        long[] _value=StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.valueLongTypeArray;

        valueLongTypeArraySubject.onNext(_value); return;
        }
        // value_long_array - valueLongArray
        case "value_long_array": {
        String temp=sharedPreferences.getString("value_long_array", null);
        Long[] _value=StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.valueLongArray;

        valueLongArraySubject.onNext(_value); return;
        }
        // value_bean_array - valueBeanArray
        case "value_bean_array": {
        String temp=sharedPreferences.getString("value_bean_array", null);
        Bean[] _value=StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.valueBeanArray;

        valueBeanArraySubject.onNext(_value); return;
        }
        // value_string_array - valueStringArray
        case "value_string_array": {
        String temp=sharedPreferences.getString("value_string_array", null);
        String[] _value=StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.valueStringArray;

        valueStringArraySubject.onNext(_value); return;
        }
        // value_char_list - valueCharList
        case "value_char_list": {
        String temp=sharedPreferences.getString("value_char_list", null);
        LinkedList<Character> _value=StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.valueCharList;

        valueCharListSubject.onNext(_value); return;
        }
        // value_char_type_array - valueCharTypeArray
        case "value_char_type_array": {
        String temp=sharedPreferences.getString("value_char_type_array", null);
        char[] _value=StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.valueCharTypeArray;

        valueCharTypeArraySubject.onNext(_value); return;
        }
        // value_char_array - valueCharArray
        case "value_char_array": {
        String temp=sharedPreferences.getString("value_char_array", null);
        Character[] _value=StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.valueCharArray;

        valueCharArraySubject.onNext(_value); return;
        }
        // value_map_string_bean - valueMapStringBean
        case "value_map_string_bean": {
        String temp=sharedPreferences.getString("value_map_string_bean", null);
        Map<String, Bean> _value=StringUtils.hasText(temp) ? parseValueMapStringBean(temp): defaultBean.valueMapStringBean;

        valueMapStringBeanSubject.onNext(_value); return;
        }
        // value_linked_map_string_bean - valueLinkedMapStringBean
        case "value_linked_map_string_bean": {
        String temp=sharedPreferences.getString("value_linked_map_string_bean", null);
        LinkedHashMap<String, Bean> _value=StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): defaultBean.valueLinkedMapStringBean;

        valueLinkedMapStringBeanSubject.onNext(_value); return;
        }
        // value_set_string - valueSetString
        case "value_set_string": {
        Set<String> temp=sharedPreferences.getStringSet("value_set_string", defaultBean.valueSetString);
        Set<String> _value=temp;

        valueSetStringSubject.onNext(_value); return;
        }
        default: return;
      }
    }
  };

  /**
   * BeanBindMap */
  private BeanBindMap beanBindMap = BinderUtils.mapperFor(Bean.class);

  /**
   * constructor
   */
  private BindBeanSharedPreferences() {
    createPrefs();
    defaultBean=new Bean();
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
    prefs.registerOnSharedPreferenceChangeListener(prefsListener);
  }

  /**
   * force to refresh values
   */
  public BindBeanSharedPreferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * Obtains an observable to <code>valueBoolType</code> property
   *
   * @return
   * an observable to <code>valueBoolType</code> property
   */
  public Subject<Boolean> getValueBoolTypeAsObservable() {
    return valueBoolTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueBool</code> property
   *
   * @return
   * an observable to <code>valueBool</code> property
   */
  public Subject<Boolean> getValueBoolAsObservable() {
    return valueBoolSubject;
  }

  /**
   * Obtains an observable to <code>valueByteType</code> property
   *
   * @return
   * an observable to <code>valueByteType</code> property
   */
  public Subject<Byte> getValueByteTypeAsObservable() {
    return valueByteTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueByte</code> property
   *
   * @return
   * an observable to <code>valueByte</code> property
   */
  public Subject<Byte> getValueByteAsObservable() {
    return valueByteSubject;
  }

  /**
   * Obtains an observable to <code>valueShortType</code> property
   *
   * @return
   * an observable to <code>valueShortType</code> property
   */
  public Subject<Short> getValueShortTypeAsObservable() {
    return valueShortTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueShort</code> property
   *
   * @return
   * an observable to <code>valueShort</code> property
   */
  public Subject<Short> getValueShortAsObservable() {
    return valueShortSubject;
  }

  /**
   * Obtains an observable to <code>valueIntType</code> property
   *
   * @return
   * an observable to <code>valueIntType</code> property
   */
  public Subject<Integer> getValueIntTypeAsObservable() {
    return valueIntTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueInt</code> property
   *
   * @return
   * an observable to <code>valueInt</code> property
   */
  public Subject<Integer> getValueIntAsObservable() {
    return valueIntSubject;
  }

  /**
   * Obtains an observable to <code>valueString</code> property
   *
   * @return
   * an observable to <code>valueString</code> property
   */
  public Subject<String> getValueStringAsObservable() {
    return valueStringSubject;
  }

  /**
   * Obtains an observable to <code>valueCharType</code> property
   *
   * @return
   * an observable to <code>valueCharType</code> property
   */
  public Subject<Character> getValueCharTypeAsObservable() {
    return valueCharTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueChar</code> property
   *
   * @return
   * an observable to <code>valueChar</code> property
   */
  public Subject<Character> getValueCharAsObservable() {
    return valueCharSubject;
  }

  /**
   * Obtains an observable to <code>valueFloatType</code> property
   *
   * @return
   * an observable to <code>valueFloatType</code> property
   */
  public Subject<Float> getValueFloatTypeAsObservable() {
    return valueFloatTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueFloat</code> property
   *
   * @return
   * an observable to <code>valueFloat</code> property
   */
  public Subject<Float> getValueFloatAsObservable() {
    return valueFloatSubject;
  }

  /**
   * Obtains an observable to <code>valueBigInteger</code> property
   *
   * @return
   * an observable to <code>valueBigInteger</code> property
   */
  public Subject<BigInteger> getValueBigIntegerAsObservable() {
    return valueBigIntegerSubject;
  }

  /**
   * Obtains an observable to <code>valueBigDecimal</code> property
   *
   * @return
   * an observable to <code>valueBigDecimal</code> property
   */
  public Subject<BigDecimal> getValueBigDecimalAsObservable() {
    return valueBigDecimalSubject;
  }

  /**
   * Obtains an observable to <code>valueEnumType</code> property
   *
   * @return
   * an observable to <code>valueEnumType</code> property
   */
  public Subject<EnumType> getValueEnumTypeAsObservable() {
    return valueEnumTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueLongType</code> property
   *
   * @return
   * an observable to <code>valueLongType</code> property
   */
  public Subject<Long> getValueLongTypeAsObservable() {
    return valueLongTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueLong</code> property
   *
   * @return
   * an observable to <code>valueLong</code> property
   */
  public Subject<Long> getValueLongAsObservable() {
    return valueLongSubject;
  }

  /**
   * Obtains an observable to <code>valueDoubleType</code> property
   *
   * @return
   * an observable to <code>valueDoubleType</code> property
   */
  public Subject<Double> getValueDoubleTypeAsObservable() {
    return valueDoubleTypeSubject;
  }

  /**
   * Obtains an observable to <code>valueDouble</code> property
   *
   * @return
   * an observable to <code>valueDouble</code> property
   */
  public Subject<Double> getValueDoubleAsObservable() {
    return valueDoubleSubject;
  }

  /**
   * Obtains an observable to <code>valueLocale</code> property
   *
   * @return
   * an observable to <code>valueLocale</code> property
   */
  public Subject<Locale> getValueLocaleAsObservable() {
    return valueLocaleSubject;
  }

  /**
   * Obtains an observable to <code>valueCalendar</code> property
   *
   * @return
   * an observable to <code>valueCalendar</code> property
   */
  public Subject<Calendar> getValueCalendarAsObservable() {
    return valueCalendarSubject;
  }

  /**
   * Obtains an observable to <code>valueDate</code> property
   *
   * @return
   * an observable to <code>valueDate</code> property
   */
  public Subject<Date> getValueDateAsObservable() {
    return valueDateSubject;
  }

  /**
   * Obtains an observable to <code>valueUrl</code> property
   *
   * @return
   * an observable to <code>valueUrl</code> property
   */
  public Subject<URL> getValueUrlAsObservable() {
    return valueUrlSubject;
  }

  /**
   * Obtains an observable to <code>valueTime</code> property
   *
   * @return
   * an observable to <code>valueTime</code> property
   */
  public Subject<Time> getValueTimeAsObservable() {
    return valueTimeSubject;
  }

  /**
   * Obtains an observable to <code>valueCurrency</code> property
   *
   * @return
   * an observable to <code>valueCurrency</code> property
   */
  public Subject<Currency> getValueCurrencyAsObservable() {
    return valueCurrencySubject;
  }

  /**
   * Obtains an observable to <code>valueTimeZone</code> property
   *
   * @return
   * an observable to <code>valueTimeZone</code> property
   */
  public Subject<TimeZone> getValueTimeZoneAsObservable() {
    return valueTimeZoneSubject;
  }

  /**
   * Obtains an observable to <code>valueTimeList</code> property
   *
   * @return
   * an observable to <code>valueTimeList</code> property
   */
  public Subject<List<Time>> getValueTimeListAsObservable() {
    return valueTimeListSubject;
  }

  /**
   * Obtains an observable to <code>valueStrinList</code> property
   *
   * @return
   * an observable to <code>valueStrinList</code> property
   */
  public Subject<LinkedList<String>> getValueStrinListAsObservable() {
    return valueStrinListSubject;
  }

  /**
   * Obtains an observable to <code>valueLongList</code> property
   *
   * @return
   * an observable to <code>valueLongList</code> property
   */
  public Subject<LinkedList<Long>> getValueLongListAsObservable() {
    return valueLongListSubject;
  }

  /**
   * Obtains an observable to <code>valueByteArray</code> property
   *
   * @return
   * an observable to <code>valueByteArray</code> property
   */
  public Subject<byte[]> getValueByteArrayAsObservable() {
    return valueByteArraySubject;
  }

  /**
   * Obtains an observable to <code>valueBean</code> property
   *
   * @return
   * an observable to <code>valueBean</code> property
   */
  public Subject<Bean> getValueBeanAsObservable() {
    return valueBeanSubject;
  }

  /**
   * Obtains an observable to <code>valueLongTypeArray</code> property
   *
   * @return
   * an observable to <code>valueLongTypeArray</code> property
   */
  public Subject<long[]> getValueLongTypeArrayAsObservable() {
    return valueLongTypeArraySubject;
  }

  /**
   * Obtains an observable to <code>valueLongArray</code> property
   *
   * @return
   * an observable to <code>valueLongArray</code> property
   */
  public Subject<Long[]> getValueLongArrayAsObservable() {
    return valueLongArraySubject;
  }

  /**
   * Obtains an observable to <code>valueBeanArray</code> property
   *
   * @return
   * an observable to <code>valueBeanArray</code> property
   */
  public Subject<Bean[]> getValueBeanArrayAsObservable() {
    return valueBeanArraySubject;
  }

  /**
   * Obtains an observable to <code>valueStringArray</code> property
   *
   * @return
   * an observable to <code>valueStringArray</code> property
   */
  public Subject<String[]> getValueStringArrayAsObservable() {
    return valueStringArraySubject;
  }

  /**
   * Obtains an observable to <code>valueCharList</code> property
   *
   * @return
   * an observable to <code>valueCharList</code> property
   */
  public Subject<LinkedList<Character>> getValueCharListAsObservable() {
    return valueCharListSubject;
  }

  /**
   * Obtains an observable to <code>valueCharTypeArray</code> property
   *
   * @return
   * an observable to <code>valueCharTypeArray</code> property
   */
  public Subject<char[]> getValueCharTypeArrayAsObservable() {
    return valueCharTypeArraySubject;
  }

  /**
   * Obtains an observable to <code>valueCharArray</code> property
   *
   * @return
   * an observable to <code>valueCharArray</code> property
   */
  public Subject<Character[]> getValueCharArrayAsObservable() {
    return valueCharArraySubject;
  }

  /**
   * Obtains an observable to <code>valueMapStringBean</code> property
   *
   * @return
   * an observable to <code>valueMapStringBean</code> property
   */
  public Subject<Map<String, Bean>> getValueMapStringBeanAsObservable() {
    return valueMapStringBeanSubject;
  }

  /**
   * Obtains an observable to <code>valueLinkedMapStringBean</code> property
   *
   * @return
   * an observable to <code>valueLinkedMapStringBean</code> property
   */
  public Subject<LinkedHashMap<String, Bean>> getValueLinkedMapStringBeanAsObservable() {
    return valueLinkedMapStringBeanSubject;
  }

  /**
   * Obtains an observable to <code>valueSetString</code> property
   *
   * @return
   * an observable to <code>valueSetString</code> property
   */
  public Subject<Set<String>> getValueSetStringAsObservable() {
    return valueSetStringSubject;
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    Bean bean=new Bean();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Bean read() {
    Bean bean=new Bean();
    bean.valueBoolType=(boolean)prefs.getBoolean("value_bool_type", (boolean)bean.valueBoolType);
    bean.valueBool=(boolean)prefs.getBoolean("value_bool", (boolean)(bean.valueBool==null?false:bean.valueBool));
    bean.valueByteType=(byte)prefs.getInt("value_byte_type", (byte)bean.valueByteType);
    bean.valueByte=(byte)prefs.getInt("value_byte", (byte)(bean.valueByte==null?(byte)0:bean.valueByte));
    bean.valueShortType=(short)prefs.getInt("value_short_type", (short)bean.valueShortType);
    bean.valueShort=(short)prefs.getInt("value_short", (short)(bean.valueShort==null?(short)0:bean.valueShort));
    bean.valueIntType=(int)prefs.getInt("value_int_type", (int)bean.valueIntType);
    bean.valueInt=(int)prefs.getInt("value_int", (int)(bean.valueInt==null?0:bean.valueInt));
    bean.valueString=prefs.getString("value_string", bean.valueString);
    bean.valueCharType=(char)prefs.getInt("value_char_type", (char)bean.valueCharType);
    bean.valueChar=(char)prefs.getInt("value_char", (char)(bean.valueChar==null?(char)0:bean.valueChar));
    bean.valueFloatType=prefs.getFloat("value_float_type", bean.valueFloatType);
    bean.valueFloat=prefs.getFloat("value_float", (bean.valueFloat==null?0F:bean.valueFloat));
     {
      String temp=prefs.getString("value_big_integer", "0");
      bean.valueBigInteger=(StringUtils.hasText(temp)) ? new BigInteger(temp): null;
    }

     {
      String temp=prefs.getString("value_big_decimal", "0");
      bean.valueBigDecimal=(StringUtils.hasText(temp)) ? new BigDecimal(temp): null;
    }

     {
      String temp=prefs.getString("value_enum_type", null);
      bean.valueEnumType=(StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;
    }

    bean.valueLongType=prefs.getLong("value_long_type", bean.valueLongType);
    bean.valueLong=prefs.getLong("value_long", (bean.valueLong==null?0L:bean.valueLong));
     {
      String temp=prefs.getString("value_double_type", null);
      bean.valueDoubleType=(StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;
    }

     {
      String temp=prefs.getString("value_double", null);
      bean.valueDouble=(StringUtils.hasText(temp)) ? Double.valueOf(temp): null;
    }

     {
      String temp=prefs.getString("value_locale", null);
      bean.valueLocale=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_calendar", null);
      bean.valueCalendar=(StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_date", null);
      bean.valueDate=(StringUtils.hasText(temp)) ? DateUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_url", null);
      bean.valueUrl=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_time", null);
      bean.valueTime=(StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_currency", null);
      bean.valueCurrency=(StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_time_zone", null);
      bean.valueTimeZone=(StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_time_list", null);
      bean.valueTimeList=StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.valueTimeList;
    }

     {
      String temp=prefs.getString("value_strin_list", null);
      bean.valueStrinList=StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.valueStrinList;
    }

     {
      String temp=prefs.getString("value_long_list", null);
      bean.valueLongList=StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.valueLongList;
    }

     {
      String temp=prefs.getString("value_byte_array", null);
      bean.valueByteArray=StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.valueByteArray;
    }

     {
      String temp=prefs.getString("value_bean", null);
      bean.valueBean=StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.valueBean;
    }

     {
      String temp=prefs.getString("value_long_type_array", null);
      bean.valueLongTypeArray=StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.valueLongTypeArray;
    }

     {
      String temp=prefs.getString("value_long_array", null);
      bean.valueLongArray=StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.valueLongArray;
    }

     {
      String temp=prefs.getString("value_bean_array", null);
      bean.valueBeanArray=StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.valueBeanArray;
    }

     {
      String temp=prefs.getString("value_string_array", null);
      bean.valueStringArray=StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.valueStringArray;
    }

     {
      String temp=prefs.getString("value_char_list", null);
      bean.valueCharList=StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.valueCharList;
    }

     {
      String temp=prefs.getString("value_char_type_array", null);
      bean.valueCharTypeArray=StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.valueCharTypeArray;
    }

     {
      String temp=prefs.getString("value_char_array", null);
      bean.valueCharArray=StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.valueCharArray;
    }

     {
      String temp=prefs.getString("value_map_string_bean", null);
      bean.valueMapStringBean=StringUtils.hasText(temp) ? parseValueMapStringBean(temp): defaultBean.valueMapStringBean;
    }

     {
      String temp=prefs.getString("value_linked_map_string_bean", null);
      bean.valueLinkedMapStringBean=StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): defaultBean.valueLinkedMapStringBean;
    }

     {
      Set<String> temp=prefs.getStringSet("value_set_string", defaultBean.valueSetString);
      bean.valueSetString=temp;
    }


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("value_bool_type",(boolean)bean.valueBoolType);

    if (bean.valueBool!=null)  {
      editor.putBoolean("value_bool",(boolean)bean.valueBool);
    }

    editor.putInt("value_byte_type",(int)bean.valueByteType);

    if (bean.valueByte!=null)  {
      editor.putInt("value_byte",(int)bean.valueByte);
    }

    editor.putInt("value_short_type",(int)bean.valueShortType);

    if (bean.valueShort!=null)  {
      editor.putInt("value_short",(int)bean.valueShort);
    }

    editor.putInt("value_int_type",(int)bean.valueIntType);

    if (bean.valueInt!=null)  {
      editor.putInt("value_int",(int)bean.valueInt);
    }

    editor.putString("value_string",bean.valueString);

    editor.putInt("value_char_type",(char)bean.valueCharType);

    if (bean.valueChar!=null)  {
      editor.putInt("value_char",(char)bean.valueChar);
    }

    editor.putFloat("value_float_type",bean.valueFloatType);

    if (bean.valueFloat!=null)  {
      editor.putFloat("value_float",bean.valueFloat);
    }

    if (bean.valueBigInteger!=null) editor.putString("value_big_integer",bean.valueBigInteger.toString() ); else editor.putString("value_big_integer", null);
    if (bean.valueBigDecimal!=null) editor.putString("value_big_decimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("value_big_decimal", null);
    if (bean.valueEnumType!=null)  {
      editor.putString("value_enum_type",bean.valueEnumType.toString() );
    } else {
      editor.remove("valueEnumType");
    }

    editor.putLong("value_long_type",bean.valueLongType);

    if (bean.valueLong!=null)  {
      editor.putLong("value_long",bean.valueLong);
    }

    editor.putString("value_double_type",String.valueOf(bean.valueDoubleType));

    if (bean.valueDouble!=null)  {
      editor.putString("value_double",String.valueOf(bean.valueDouble));
    } else {
      editor.remove("valueDouble");
    }

    if (bean.valueLocale!=null)  {
      editor.putString("value_locale",LocaleUtils.write(bean.valueLocale));
    } else {
      editor.remove("valueLocale");
    }

    if (bean.valueCalendar!=null)  {
      editor.putString("value_calendar",CalendarUtils.write(bean.valueCalendar));
    } else {
      editor.remove("valueCalendar");
    }

    if (bean.valueDate!=null)  {
      editor.putString("value_date",DateUtils.write(bean.valueDate));
    } else {
      editor.remove("valueDate");
    }

    if (bean.valueUrl!=null)  {
      editor.putString("value_url",UrlUtils.write(bean.valueUrl));
    } else {
      editor.remove("valueUrl");
    }

    if (bean.valueTime!=null)  {
      editor.putString("value_time",SQLTimeUtils.write(bean.valueTime));
    } else {
      editor.remove("valueTime");
    }

    if (bean.valueCurrency!=null)  {
      editor.putString("value_currency",CurrencyUtils.write(bean.valueCurrency));
    } else {
      editor.remove("valueCurrency");
    }

    if (bean.valueTimeZone!=null)  {
      editor.putString("value_time_zone",TimeZoneUtils.write(bean.valueTimeZone));
    } else {
      editor.remove("valueTimeZone");
    }

    if (bean.valueTimeList!=null)  {
      String temp=serializeValueTimeList(bean.valueTimeList);
      editor.putString("value_time_list",temp);
    }  else  {
      editor.remove("value_time_list");
    }

    if (bean.valueStrinList!=null)  {
      String temp=serializeValueStrinList(bean.valueStrinList);
      editor.putString("value_strin_list",temp);
    }  else  {
      editor.remove("value_strin_list");
    }

    if (bean.valueLongList!=null)  {
      String temp=serializeValueLongList(bean.valueLongList);
      editor.putString("value_long_list",temp);
    }  else  {
      editor.remove("value_long_list");
    }

    if (bean.valueByteArray!=null)  {
      String temp=serializeValueByteArray(bean.valueByteArray);
      editor.putString("value_byte_array",temp);
    }  else  {
      editor.remove("value_byte_array");
    }

    if (bean.valueBean!=null)  {
      String temp=serializeValueBean(bean.valueBean);
      editor.putString("value_bean",temp);
    }  else  {
      editor.remove("value_bean");
    }

    if (bean.valueLongTypeArray!=null)  {
      String temp=serializeValueLongTypeArray(bean.valueLongTypeArray);
      editor.putString("value_long_type_array",temp);
    }  else  {
      editor.remove("value_long_type_array");
    }

    if (bean.valueLongArray!=null)  {
      String temp=serializeValueLongArray(bean.valueLongArray);
      editor.putString("value_long_array",temp);
    }  else  {
      editor.remove("value_long_array");
    }

    if (bean.valueBeanArray!=null)  {
      String temp=serializeValueBeanArray(bean.valueBeanArray);
      editor.putString("value_bean_array",temp);
    }  else  {
      editor.remove("value_bean_array");
    }

    if (bean.valueStringArray!=null)  {
      String temp=serializeValueStringArray(bean.valueStringArray);
      editor.putString("value_string_array",temp);
    }  else  {
      editor.remove("value_string_array");
    }

    if (bean.valueCharList!=null)  {
      String temp=serializeValueCharList(bean.valueCharList);
      editor.putString("value_char_list",temp);
    }  else  {
      editor.remove("value_char_list");
    }

    if (bean.valueCharTypeArray!=null)  {
      String temp=serializeValueCharTypeArray(bean.valueCharTypeArray);
      editor.putString("value_char_type_array",temp);
    }  else  {
      editor.remove("value_char_type_array");
    }

    if (bean.valueCharArray!=null)  {
      String temp=serializeValueCharArray(bean.valueCharArray);
      editor.putString("value_char_array",temp);
    }  else  {
      editor.remove("value_char_array");
    }

    if (bean.valueMapStringBean!=null)  {
      String temp=serializeValueMapStringBean(bean.valueMapStringBean);
      editor.putString("value_map_string_bean",temp);
    }  else  {
      editor.remove("value_map_string_bean");
    }

    if (bean.valueLinkedMapStringBean!=null)  {
      String temp=serializeValueLinkedMapStringBean(bean.valueLinkedMapStringBean);
      editor.putString("value_linked_map_string_bean",temp);
    }  else  {
      editor.remove("value_linked_map_string_bean");
    }

    editor.putStringSet("value_set_string",bean.valueSetString);


    editor.commit();
  }

  /**
   * read property valueBoolType
   *
   * @return property valueBoolType value
   */
  public boolean getValueBoolType() {
    return (boolean)prefs.getBoolean("value_bool_type", (boolean)defaultBean.valueBoolType);}

  /**
   * read property valueBool
   *
   * @return property valueBool value
   */
  public Boolean getValueBool() {
    return (boolean)prefs.getBoolean("value_bool", (boolean)(defaultBean.valueBool==null?false:defaultBean.valueBool));}

  /**
   * read property valueByteType
   *
   * @return property valueByteType value
   */
  public byte getValueByteType() {
    return (byte)prefs.getInt("value_byte_type", (byte)defaultBean.valueByteType);}

  /**
   * read property valueByte
   *
   * @return property valueByte value
   */
  public Byte getValueByte() {
    return (byte)prefs.getInt("value_byte", (byte)(defaultBean.valueByte==null?(byte)0:defaultBean.valueByte));}

  /**
   * read property valueShortType
   *
   * @return property valueShortType value
   */
  public short getValueShortType() {
    return (short)prefs.getInt("value_short_type", (short)defaultBean.valueShortType);}

  /**
   * read property valueShort
   *
   * @return property valueShort value
   */
  public Short getValueShort() {
    return (short)prefs.getInt("value_short", (short)(defaultBean.valueShort==null?(short)0:defaultBean.valueShort));}

  /**
   * read property valueIntType
   *
   * @return property valueIntType value
   */
  public int getValueIntType() {
    return (int)prefs.getInt("value_int_type", (int)defaultBean.valueIntType);}

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public Integer getValueInt() {
    return (int)prefs.getInt("value_int", (int)(defaultBean.valueInt==null?0:defaultBean.valueInt));}

  /**
   * read property valueString
   *
   * @return property valueString value
   */
  public String getValueString() {
    return prefs.getString("value_string", defaultBean.valueString);}

  /**
   * read property valueCharType
   *
   * @return property valueCharType value
   */
  public char getValueCharType() {
    return (char)prefs.getInt("value_char_type", (char)defaultBean.valueCharType);}

  /**
   * read property valueChar
   *
   * @return property valueChar value
   */
  public Character getValueChar() {
    return (char)prefs.getInt("value_char", (char)(defaultBean.valueChar==null?(char)0:defaultBean.valueChar));}

  /**
   * read property valueFloatType
   *
   * @return property valueFloatType value
   */
  public float getValueFloatType() {
    return prefs.getFloat("value_float_type", defaultBean.valueFloatType);}

  /**
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public Float getValueFloat() {
    return prefs.getFloat("value_float", (defaultBean.valueFloat==null?0F:defaultBean.valueFloat));}

  /**
   * read property valueBigInteger
   *
   * @return property valueBigInteger value
   */
  public BigInteger getValueBigInteger() {
    String temp=prefs.getString("value_big_integer", "0");
    return (StringUtils.hasText(temp)) ? new BigInteger(temp): null;
  }

  /**
   * read property valueBigDecimal
   *
   * @return property valueBigDecimal value
   */
  public BigDecimal getValueBigDecimal() {
    String temp=prefs.getString("value_big_decimal", "0");
    return (StringUtils.hasText(temp)) ? new BigDecimal(temp): null;
  }

  /**
   * read property valueEnumType
   *
   * @return property valueEnumType value
   */
  public EnumType getValueEnumType() {
    String temp=prefs.getString("value_enum_type", null);
    return (StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;
  }

  /**
   * read property valueLongType
   *
   * @return property valueLongType value
   */
  public long getValueLongType() {
    return prefs.getLong("value_long_type", defaultBean.valueLongType);}

  /**
   * read property valueLong
   *
   * @return property valueLong value
   */
  public Long getValueLong() {
    return prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));}

  /**
   * read property valueDoubleType
   *
   * @return property valueDoubleType value
   */
  public double getValueDoubleType() {
    String temp=prefs.getString("value_double_type", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;
  }

  /**
   * read property valueDouble
   *
   * @return property valueDouble value
   */
  public Double getValueDouble() {
    String temp=prefs.getString("value_double", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): null;
  }

  /**
   * read property valueLocale
   *
   * @return property valueLocale value
   */
  public Locale getValueLocale() {
    String temp=prefs.getString("value_locale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;}

  /**
   * read property valueCalendar
   *
   * @return property valueCalendar value
   */
  public Calendar getValueCalendar() {
    String temp=prefs.getString("value_calendar", null);
    return (StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;}

  /**
   * read property valueDate
   *
   * @return property valueDate value
   */
  public Date getValueDate() {
    String temp=prefs.getString("value_date", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): null;}

  /**
   * read property valueUrl
   *
   * @return property valueUrl value
   */
  public URL getValueUrl() {
    String temp=prefs.getString("value_url", null);
    return (StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;}

  /**
   * read property valueTime
   *
   * @return property valueTime value
   */
  public Time getValueTime() {
    String temp=prefs.getString("value_time", null);
    return (StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;}

  /**
   * read property valueCurrency
   *
   * @return property valueCurrency value
   */
  public Currency getValueCurrency() {
    String temp=prefs.getString("value_currency", null);
    return (StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;}

  /**
   * read property valueTimeZone
   *
   * @return property valueTimeZone value
   */
  public TimeZone getValueTimeZone() {
    String temp=prefs.getString("value_time_zone", null);
    return (StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;}

  /**
   * read property valueTimeList
   *
   * @return property valueTimeList value
   */
  public List<Time> getValueTimeList() {
    String temp=prefs.getString("value_time_list", null);
    return StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.valueTimeList;
  }

  /**
   * read property valueStrinList
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> getValueStrinList() {
    String temp=prefs.getString("value_strin_list", null);
    return StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.valueStrinList;
  }

  /**
   * read property valueLongList
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> getValueLongList() {
    String temp=prefs.getString("value_long_list", null);
    return StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.valueLongList;
  }

  /**
   * read property valueByteArray
   *
   * @return property valueByteArray value
   */
  public byte[] getValueByteArray() {
    String temp=prefs.getString("value_byte_array", null);
    return StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.valueByteArray;
  }

  /**
   * read property valueBean
   *
   * @return property valueBean value
   */
  public Bean getValueBean() {
    String temp=prefs.getString("value_bean", null);
    return StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.valueBean;
  }

  /**
   * read property valueLongTypeArray
   *
   * @return property valueLongTypeArray value
   */
  public long[] getValueLongTypeArray() {
    String temp=prefs.getString("value_long_type_array", null);
    return StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.valueLongTypeArray;
  }

  /**
   * read property valueLongArray
   *
   * @return property valueLongArray value
   */
  public Long[] getValueLongArray() {
    String temp=prefs.getString("value_long_array", null);
    return StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.valueLongArray;
  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean[] getValueBeanArray() {
    String temp=prefs.getString("value_bean_array", null);
    return StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.valueBeanArray;
  }

  /**
   * read property valueStringArray
   *
   * @return property valueStringArray value
   */
  public String[] getValueStringArray() {
    String temp=prefs.getString("value_string_array", null);
    return StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.valueStringArray;
  }

  /**
   * read property valueCharList
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> getValueCharList() {
    String temp=prefs.getString("value_char_list", null);
    return StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.valueCharList;
  }

  /**
   * read property valueCharTypeArray
   *
   * @return property valueCharTypeArray value
   */
  public char[] getValueCharTypeArray() {
    String temp=prefs.getString("value_char_type_array", null);
    return StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.valueCharTypeArray;
  }

  /**
   * read property valueCharArray
   *
   * @return property valueCharArray value
   */
  public Character[] getValueCharArray() {
    String temp=prefs.getString("value_char_array", null);
    return StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.valueCharArray;
  }

  /**
   * read property valueMapStringBean
   *
   * @return property valueMapStringBean value
   */
  public Map<String, Bean> getValueMapStringBean() {
    String temp=prefs.getString("value_map_string_bean", null);
    return StringUtils.hasText(temp) ? parseValueMapStringBean(temp): defaultBean.valueMapStringBean;
  }

  /**
   * read property valueLinkedMapStringBean
   *
   * @return property valueLinkedMapStringBean value
   */
  public LinkedHashMap<String, Bean> getValueLinkedMapStringBean() {
    String temp=prefs.getString("value_linked_map_string_bean", null);
    return StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): defaultBean.valueLinkedMapStringBean;
  }

  /**
   * read property valueSetString
   *
   * @return property valueSetString value
   */
  public Set<String> getValueSetString() {
    Set<String> temp=prefs.getStringSet("value_set_string", defaultBean.valueSetString);
    return temp;
  }

  /**
   * for attribute valueTimeList serialization
   */
  protected String serializeValueTimeList(List<Time> value) {
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
        Time item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueTimeList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(SQLTimeUtils.write(item));
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
   * for attribute valueTimeList parsing
   */
  protected List<Time> parseValueTimeList(String input) {
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
      List<Time> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Time> collection=new ArrayList<>();
        Time item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=SQLTimeUtils.read(jacksonParser.getText());
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
   * for attribute valueStrinList serialization
   */
  protected String serializeValueStrinList(LinkedList<String> value) {
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
        jacksonSerializer.writeFieldName("valueStrinList");
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
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStrinList parsing
   */
  protected LinkedList<String> parseValueStrinList(String input) {
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
      LinkedList<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<String> collection=new LinkedList<>();
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
   * for attribute valueLongList serialization
   */
  protected String serializeValueLongList(LinkedList<Long> value) {
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
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongList");
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
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongList parsing
   */
  protected LinkedList<Long> parseValueLongList(String input) {
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
      LinkedList<Long> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Long> collection=new LinkedList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
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
   * for attribute valueByteArray serialization
   */
  protected String serializeValueByteArray(byte[] value) {
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
        jacksonSerializer.writeBinaryField("valueByteArray", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueByteArray parsing
   */
  protected byte[] parseValueByteArray(String input) {
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
      byte[] result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getBinaryValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean serialization
   */
  protected String serializeValueBean(Bean value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        beanBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean parsing
   */
  protected Bean parseValueBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=beanBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongTypeArray serialization
   */
  protected String serializeValueLongTypeArray(long[] value) {
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
        int n=value.length;
        long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongTypeArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
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
   * for attribute valueLongTypeArray parsing
   */
  protected long[] parseValueLongTypeArray(String input) {
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
      long[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asLongTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongArray serialization
   */
  protected String serializeValueLongArray(Long[] value) {
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
        int n=value.length;
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
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
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongArray parsing
   */
  protected Long[] parseValueLongArray(String input) {
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
      Long[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asLongArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray serialization
   */
  protected String serializeValueBeanArray(Bean[] value) {
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
        int n=value.length;
        Bean item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            beanBindMap.serializeOnJackson(item, jacksonSerializer);
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
   * for attribute valueBeanArray parsing
   */
  protected Bean[] parseValueBeanArray(String input) {
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
      Bean[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Bean> collection=new ArrayList<>();
        Bean item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new Bean[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStringArray serialization
   */
  protected String serializeValueStringArray(String[] value) {
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
        int n=value.length;
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
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
   * for attribute valueStringArray parsing
   */
  protected String[] parseValueStringArray(String input) {
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
      String[] result=null;
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
        result=CollectionUtils.asArray(collection, new String[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharList serialization
   */
  protected String serializeValueCharList(LinkedList<Character> value) {
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
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharList");
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
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharList parsing
   */
  protected LinkedList<Character> parseValueCharList(String input) {
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
      LinkedList<Character> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Character> collection=new LinkedList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
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
   * for attribute valueCharTypeArray serialization
   */
  protected String serializeValueCharTypeArray(char[] value) {
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
        int n=value.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharTypeArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
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
   * for attribute valueCharTypeArray parsing
   */
  protected char[] parseValueCharTypeArray(String input) {
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
      char[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharArray serialization
   */
  protected String serializeValueCharArray(Character[] value) {
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
        int n=value.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
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
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharArray parsing
   */
  protected Character[] parseValueCharArray(String input) {
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
      Character[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueMapStringBean serialization
   */
  protected String serializeValueMapStringBean(Map<String, Bean> value) {
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
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              beanBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapStringBean");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueMapStringBean parsing
   */
  protected Map<String, Bean> parseValueMapStringBean(String input) {
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
      Map<String, Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<String, Bean> collection=new HashMap<>();
        String key=null;
        Bean value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
            value=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLinkedMapStringBean serialization
   */
  protected String serializeValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
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
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("valueLinkedMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              beanBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueLinkedMapStringBean");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLinkedMapStringBean parsing
   */
  protected LinkedHashMap<String, Bean> parseValueLinkedMapStringBean(String input) {
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
      LinkedHashMap<String, Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedHashMap<String, Bean> collection=new LinkedHashMap<>();
        String key=null;
        Bean value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
            value=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
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
  public static synchronized BindBeanSharedPreferences instance() {
    if (instance==null) {
      instance=new BindBeanSharedPreferences();
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
     * modifier for property valueBoolType
     */
    public BindEditor putValueBoolType(boolean value) {
      editor.putBoolean("value_bool_type",(boolean)value);

      return this;
    }

    /**
     * modifier for property valueBool
     */
    public BindEditor putValueBool(Boolean value) {
      if (value!=null)  {
        editor.putBoolean("value_bool",(boolean)value);
      }

      return this;
    }

    /**
     * modifier for property valueByteType
     */
    public BindEditor putValueByteType(byte value) {
      editor.putInt("value_byte_type",(int)value);

      return this;
    }

    /**
     * modifier for property valueByte
     */
    public BindEditor putValueByte(Byte value) {
      if (value!=null)  {
        editor.putInt("value_byte",(int)value);
      }

      return this;
    }

    /**
     * modifier for property valueShortType
     */
    public BindEditor putValueShortType(short value) {
      editor.putInt("value_short_type",(int)value);

      return this;
    }

    /**
     * modifier for property valueShort
     */
    public BindEditor putValueShort(Short value) {
      if (value!=null)  {
        editor.putInt("value_short",(int)value);
      }

      return this;
    }

    /**
     * modifier for property valueIntType
     */
    public BindEditor putValueIntType(int value) {
      editor.putInt("value_int_type",(int)value);

      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(Integer value) {
      if (value!=null)  {
        editor.putInt("value_int",(int)value);
      }

      return this;
    }

    /**
     * modifier for property valueString
     */
    public BindEditor putValueString(String value) {
      editor.putString("value_string",value);

      return this;
    }

    /**
     * modifier for property valueCharType
     */
    public BindEditor putValueCharType(char value) {
      editor.putInt("value_char_type",(char)value);

      return this;
    }

    /**
     * modifier for property valueChar
     */
    public BindEditor putValueChar(Character value) {
      if (value!=null)  {
        editor.putInt("value_char",(char)value);
      }

      return this;
    }

    /**
     * modifier for property valueFloatType
     */
    public BindEditor putValueFloatType(float value) {
      editor.putFloat("value_float_type",value);

      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(Float value) {
      if (value!=null)  {
        editor.putFloat("value_float",value);
      }

      return this;
    }

    /**
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("value_big_integer",value.toString()); else editor.remove("value_big_integer");
      return this;
    }

    /**
     * modifier for property valueBigDecimal
     */
    public BindEditor putValueBigDecimal(BigDecimal value) {
      if (value!=null) editor.putString("value_big_decimal",value.toPlainString()); else editor.remove("value_big_decimal");
      return this;
    }

    /**
     * modifier for property valueEnumType
     */
    public BindEditor putValueEnumType(EnumType value) {
      if (value!=null)  {
        editor.putString("value_enum_type",value.toString() );
      } else {
        editor.remove("valueEnumType");
      }

      return this;
    }

    /**
     * modifier for property valueLongType
     */
    public BindEditor putValueLongType(long value) {
      editor.putLong("value_long_type",value);

      return this;
    }

    /**
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("value_long",value);
      }

      return this;
    }

    /**
     * modifier for property valueDoubleType
     */
    public BindEditor putValueDoubleType(double value) {
      editor.putString("value_double_type",String.valueOf(value));

      return this;
    }

    /**
     * modifier for property valueDouble
     */
    public BindEditor putValueDouble(Double value) {
      if (value!=null)  {
        editor.putString("value_double",String.valueOf(value));
      } else {
        editor.remove("valueDouble");
      }

      return this;
    }

    /**
     * modifier for property valueLocale
     */
    public BindEditor putValueLocale(Locale value) {
      if (value!=null)  {
        editor.putString("value_locale",LocaleUtils.write(value));
      } else {
        editor.remove("valueLocale");
      }

      return this;
    }

    /**
     * modifier for property valueCalendar
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null)  {
        editor.putString("value_calendar",CalendarUtils.write(value));
      } else {
        editor.remove("valueCalendar");
      }

      return this;
    }

    /**
     * modifier for property valueDate
     */
    public BindEditor putValueDate(Date value) {
      if (value!=null)  {
        editor.putString("value_date",DateUtils.write(value));
      } else {
        editor.remove("valueDate");
      }

      return this;
    }

    /**
     * modifier for property valueUrl
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null)  {
        editor.putString("value_url",UrlUtils.write(value));
      } else {
        editor.remove("valueUrl");
      }

      return this;
    }

    /**
     * modifier for property valueTime
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null)  {
        editor.putString("value_time",SQLTimeUtils.write(value));
      } else {
        editor.remove("valueTime");
      }

      return this;
    }

    /**
     * modifier for property valueCurrency
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null)  {
        editor.putString("value_currency",CurrencyUtils.write(value));
      } else {
        editor.remove("valueCurrency");
      }

      return this;
    }

    /**
     * modifier for property valueTimeZone
     */
    public BindEditor putValueTimeZone(TimeZone value) {
      if (value!=null)  {
        editor.putString("value_time_zone",TimeZoneUtils.write(value));
      } else {
        editor.remove("valueTimeZone");
      }

      return this;
    }

    /**
     * modifier for property valueTimeList
     */
    public BindEditor putValueTimeList(List<Time> value) {
      if (value!=null)  {
        String temp=serializeValueTimeList(value);
        editor.putString("value_time_list",temp);
      }  else  {
        editor.remove("value_time_list");
      }

      return this;
    }

    /**
     * modifier for property valueStrinList
     */
    public BindEditor putValueStrinList(LinkedList<String> value) {
      if (value!=null)  {
        String temp=serializeValueStrinList(value);
        editor.putString("value_strin_list",temp);
      }  else  {
        editor.remove("value_strin_list");
      }

      return this;
    }

    /**
     * modifier for property valueLongList
     */
    public BindEditor putValueLongList(LinkedList<Long> value) {
      if (value!=null)  {
        String temp=serializeValueLongList(value);
        editor.putString("value_long_list",temp);
      }  else  {
        editor.remove("value_long_list");
      }

      return this;
    }

    /**
     * modifier for property valueByteArray
     */
    public BindEditor putValueByteArray(byte[] value) {
      if (value!=null)  {
        String temp=serializeValueByteArray(value);
        editor.putString("value_byte_array",temp);
      }  else  {
        editor.remove("value_byte_array");
      }

      return this;
    }

    /**
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean value) {
      if (value!=null)  {
        String temp=serializeValueBean(value);
        editor.putString("value_bean",temp);
      }  else  {
        editor.remove("value_bean");
      }

      return this;
    }

    /**
     * modifier for property valueLongTypeArray
     */
    public BindEditor putValueLongTypeArray(long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongTypeArray(value);
        editor.putString("value_long_type_array",temp);
      }  else  {
        editor.remove("value_long_type_array");
      }

      return this;
    }

    /**
     * modifier for property valueLongArray
     */
    public BindEditor putValueLongArray(Long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongArray(value);
        editor.putString("value_long_array",temp);
      }  else  {
        editor.remove("value_long_array");
      }

      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean[] value) {
      if (value!=null)  {
        String temp=serializeValueBeanArray(value);
        editor.putString("value_bean_array",temp);
      }  else  {
        editor.remove("value_bean_array");
      }

      return this;
    }

    /**
     * modifier for property valueStringArray
     */
    public BindEditor putValueStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeValueStringArray(value);
        editor.putString("value_string_array",temp);
      }  else  {
        editor.remove("value_string_array");
      }

      return this;
    }

    /**
     * modifier for property valueCharList
     */
    public BindEditor putValueCharList(LinkedList<Character> value) {
      if (value!=null)  {
        String temp=serializeValueCharList(value);
        editor.putString("value_char_list",temp);
      }  else  {
        editor.remove("value_char_list");
      }

      return this;
    }

    /**
     * modifier for property valueCharTypeArray
     */
    public BindEditor putValueCharTypeArray(char[] value) {
      if (value!=null)  {
        String temp=serializeValueCharTypeArray(value);
        editor.putString("value_char_type_array",temp);
      }  else  {
        editor.remove("value_char_type_array");
      }

      return this;
    }

    /**
     * modifier for property valueCharArray
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null)  {
        String temp=serializeValueCharArray(value);
        editor.putString("value_char_array",temp);
      }  else  {
        editor.remove("value_char_array");
      }

      return this;
    }

    /**
     * modifier for property valueMapStringBean
     */
    public BindEditor putValueMapStringBean(Map<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueMapStringBean(value);
        editor.putString("value_map_string_bean",temp);
      }  else  {
        editor.remove("value_map_string_bean");
      }

      return this;
    }

    /**
     * modifier for property valueLinkedMapStringBean
     */
    public BindEditor putValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueLinkedMapStringBean(value);
        editor.putString("value_linked_map_string_bean",temp);
      }  else  {
        editor.remove("value_linked_map_string_bean");
      }

      return this;
    }

    /**
     * modifier for property valueSetString
     */
    public BindEditor putValueSetString(Set<String> value) {
      editor.putStringSet("value_set_string",value);

      return this;
    }
  }
}
