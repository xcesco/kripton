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
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This class is the shared preference binder defined for Bean2
 *
 * @see Bean2
 */
public class BindBean2SharedPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindBean2SharedPreferences instance;

  /**
   * working instance of bean
   */
  private final Bean2 defaultBean;

  /**
   * subject for field id - shared pref id
   */
  private Subject<Long> idSubject = BehaviorSubject.create();

  /**
   * subject for field valueBean - shared pref value_bean
   */
  private Subject<Bean2> valueBeanSubject = BehaviorSubject.create();

  /**
   * subject for field valueBeanArray - shared pref value_bean_array
   */
  private Subject<Bean2[]> valueBeanArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueBigDecimal - shared pref value_big_decimal
   */
  private Subject<BigDecimal> valueBigDecimalSubject = BehaviorSubject.create();

  /**
   * subject for field valueBigInteger - shared pref value_big_integer
   */
  private Subject<BigInteger> valueBigIntegerSubject = BehaviorSubject.create();

  /**
   * subject for field valueBool - shared pref value_bool
   */
  private Subject<Boolean> valueBoolSubject = BehaviorSubject.create();

  /**
   * subject for field valueBoolType - shared pref value_bool_type
   */
  private Subject<Boolean> valueBoolTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueByte - shared pref value_byte
   */
  private Subject<Byte> valueByteSubject = BehaviorSubject.create();

  /**
   * subject for field valueByteArray - shared pref value_byte_array
   */
  private Subject<byte[]> valueByteArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueByteType - shared pref value_byte_type
   */
  private Subject<Byte> valueByteTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueCalendar - shared pref value_calendar
   */
  private Subject<Calendar> valueCalendarSubject = BehaviorSubject.create();

  /**
   * subject for field valueChar - shared pref value_char
   */
  private Subject<Character> valueCharSubject = BehaviorSubject.create();

  /**
   * subject for field valueCharArray - shared pref value_char_array
   */
  private Subject<Character[]> valueCharArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueCharList - shared pref value_char_list
   */
  private Subject<LinkedList<Character>> valueCharListSubject = BehaviorSubject.create();

  /**
   * subject for field valueCharType - shared pref value_char_type
   */
  private Subject<Character> valueCharTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueCharTypeArray - shared pref value_char_type_array
   */
  private Subject<char[]> valueCharTypeArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueCurrency - shared pref value_currency
   */
  private Subject<Currency> valueCurrencySubject = BehaviorSubject.create();

  /**
   * subject for field valueDate - shared pref value_date
   */
  private Subject<Date> valueDateSubject = BehaviorSubject.create();

  /**
   * subject for field valueDouble - shared pref value_double
   */
  private Subject<Double> valueDoubleSubject = BehaviorSubject.create();

  /**
   * subject for field valueDoubleType - shared pref value_double_type
   */
  private Subject<Double> valueDoubleTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueEnumType - shared pref value_enum_type
   */
  private Subject<EnumType> valueEnumTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueFloat - shared pref value_float
   */
  private Subject<Float> valueFloatSubject = BehaviorSubject.create();

  /**
   * subject for field valueFloatType - shared pref value_float_type
   */
  private Subject<Float> valueFloatTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueInt - shared pref value_int
   */
  private Subject<Integer> valueIntSubject = BehaviorSubject.create();

  /**
   * subject for field valueIntType - shared pref value_int_type
   */
  private Subject<Integer> valueIntTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueLocale - shared pref value_locale
   */
  private Subject<Locale> valueLocaleSubject = BehaviorSubject.create();

  /**
   * subject for field valueLong - shared pref value_long
   */
  private Subject<Long> valueLongSubject = BehaviorSubject.create();

  /**
   * subject for field valueLongArray - shared pref value_long_array
   */
  private Subject<Long[]> valueLongArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueLongList - shared pref value_long_list
   */
  private Subject<LinkedList<Long>> valueLongListSubject = BehaviorSubject.create();

  /**
   * subject for field valueLongType - shared pref value_long_type
   */
  private Subject<Long> valueLongTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueLongTypeArray - shared pref value_long_type_array
   */
  private Subject<long[]> valueLongTypeArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueShort - shared pref value_short
   */
  private Subject<Short> valueShortSubject = BehaviorSubject.create();

  /**
   * subject for field valueShortType - shared pref value_short_type
   */
  private Subject<Short> valueShortTypeSubject = BehaviorSubject.create();

  /**
   * subject for field valueString - shared pref value_string
   */
  private Subject<String> valueStringSubject = BehaviorSubject.create();

  /**
   * subject for field valueStringArray - shared pref value_string_array
   */
  private Subject<String[]> valueStringArraySubject = BehaviorSubject.create();

  /**
   * subject for field valueStrinList - shared pref value_strin_list
   */
  private Subject<LinkedList<String>> valueStrinListSubject = BehaviorSubject.create();

  /**
   * subject for field valueTime - shared pref value_time
   */
  private Subject<Time> valueTimeSubject = BehaviorSubject.create();

  /**
   * subject for field valueTimeList - shared pref value_time_list
   */
  private Subject<List<Time>> valueTimeListSubject = BehaviorSubject.create();

  /**
   * subject for field valueTimeZone - shared pref value_time_zone
   */
  private Subject<TimeZone> valueTimeZoneSubject = BehaviorSubject.create();

  /**
   * subject for field valueUrl - shared pref value_url
   */
  private Subject<URL> valueUrlSubject = BehaviorSubject.create();

  /**
   * List of tables compose datasource
   */
  private SharedPreferences.OnSharedPreferenceChangeListener prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
      switch (key) {
        // id - id
        case "id": {
        long _value=sharedPreferences.getLong("id", defaultBean.getId());
        idSubject.onNext(_value); return;
        }
        // value_bean - valueBean
        case "value_bean": {
        String temp=sharedPreferences.getString("value_bean", null);
        Bean2 _value=StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.getValueBean();

        valueBeanSubject.onNext(_value); return;
        }
        // value_bean_array - valueBeanArray
        case "value_bean_array": {
        String temp=sharedPreferences.getString("value_bean_array", null);
        Bean2[] _value=StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.getValueBeanArray();

        valueBeanArraySubject.onNext(_value); return;
        }
        // value_big_decimal - valueBigDecimal
        case "value_big_decimal": {
        String temp=sharedPreferences.getString("value_big_decimal", "0");
        BigDecimal _value=(StringUtils.hasText(temp)) ? new BigDecimal(temp): null;

        valueBigDecimalSubject.onNext(_value); return;
        }
        // value_big_integer - valueBigInteger
        case "value_big_integer": {
        String temp=sharedPreferences.getString("value_big_integer", "0");
        BigInteger _value=(StringUtils.hasText(temp)) ? new BigInteger(temp): null;

        valueBigIntegerSubject.onNext(_value); return;
        }
        // value_bool - valueBool
        case "value_bool": {
        Boolean _value=(boolean)sharedPreferences.getBoolean("value_bool", (boolean)(defaultBean.getValueBool()==null?false:defaultBean.getValueBool()));
        valueBoolSubject.onNext(_value); return;
        }
        // value_bool_type - valueBoolType
        case "value_bool_type": {
        boolean _value=(boolean)sharedPreferences.getBoolean("value_bool_type", (boolean)defaultBean.isValueBoolType());
        valueBoolTypeSubject.onNext(_value); return;
        }
        // value_byte - valueByte
        case "value_byte": {
        Byte _value=(byte)sharedPreferences.getInt("value_byte", (byte)(defaultBean.getValueByte()==null?(byte)0:defaultBean.getValueByte()));
        valueByteSubject.onNext(_value); return;
        }
        // value_byte_array - valueByteArray
        case "value_byte_array": {
        String temp=sharedPreferences.getString("value_byte_array", null);
        byte[] _value=StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.getValueByteArray();

        valueByteArraySubject.onNext(_value); return;
        }
        // value_byte_type - valueByteType
        case "value_byte_type": {
        byte _value=(byte)sharedPreferences.getInt("value_byte_type", (byte)defaultBean.getValueByteType());
        valueByteTypeSubject.onNext(_value); return;
        }
        // value_calendar - valueCalendar
        case "value_calendar": {
        String temp=sharedPreferences.getString("value_calendar", null);
        Calendar _value=(StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;
        valueCalendarSubject.onNext(_value); return;
        }
        // value_char - valueChar
        case "value_char": {
        Character _value=(char)sharedPreferences.getInt("value_char", (char)(defaultBean.getValueChar()==null?(char)0:defaultBean.getValueChar()));
        valueCharSubject.onNext(_value); return;
        }
        // value_char_array - valueCharArray
        case "value_char_array": {
        String temp=sharedPreferences.getString("value_char_array", null);
        Character[] _value=StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.getValueCharArray();

        valueCharArraySubject.onNext(_value); return;
        }
        // value_char_list - valueCharList
        case "value_char_list": {
        String temp=sharedPreferences.getString("value_char_list", null);
        LinkedList<Character> _value=StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.getValueCharList();

        valueCharListSubject.onNext(_value); return;
        }
        // value_char_type - valueCharType
        case "value_char_type": {
        char _value=(char)sharedPreferences.getInt("value_char_type", (char)defaultBean.getValueCharType());
        valueCharTypeSubject.onNext(_value); return;
        }
        // value_char_type_array - valueCharTypeArray
        case "value_char_type_array": {
        String temp=sharedPreferences.getString("value_char_type_array", null);
        char[] _value=StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.getValueCharTypeArray();

        valueCharTypeArraySubject.onNext(_value); return;
        }
        // value_currency - valueCurrency
        case "value_currency": {
        String temp=sharedPreferences.getString("value_currency", null);
        Currency _value=(StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;
        valueCurrencySubject.onNext(_value); return;
        }
        // value_date - valueDate
        case "value_date": {
        String temp=sharedPreferences.getString("value_date", null);
        Date _value=(StringUtils.hasText(temp)) ? DateUtils.read(temp): null;
        valueDateSubject.onNext(_value); return;
        }
        // value_double - valueDouble
        case "value_double": {
        String temp=sharedPreferences.getString("value_double", null);
        Double _value=(StringUtils.hasText(temp)) ? Double.valueOf(temp): null;

        valueDoubleSubject.onNext(_value); return;
        }
        // value_double_type - valueDoubleType
        case "value_double_type": {
        String temp=sharedPreferences.getString("value_double_type", null);
        double _value=(StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;

        valueDoubleTypeSubject.onNext(_value); return;
        }
        // value_enum_type - valueEnumType
        case "value_enum_type": {
        String temp=sharedPreferences.getString("value_enum_type", null);
        EnumType _value=(StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;

        valueEnumTypeSubject.onNext(_value); return;
        }
        // value_float - valueFloat
        case "value_float": {
        Float _value=sharedPreferences.getFloat("value_float", (defaultBean.getValueFloat()==null?0F:defaultBean.getValueFloat()));
        valueFloatSubject.onNext(_value); return;
        }
        // value_float_type - valueFloatType
        case "value_float_type": {
        float _value=sharedPreferences.getFloat("value_float_type", defaultBean.getValueFloatType());
        valueFloatTypeSubject.onNext(_value); return;
        }
        // value_int - valueInt
        case "value_int": {
        Integer _value=(int)sharedPreferences.getInt("value_int", (int)(defaultBean.getValueInt()==null?0:defaultBean.getValueInt()));
        valueIntSubject.onNext(_value); return;
        }
        // value_int_type - valueIntType
        case "value_int_type": {
        int _value=(int)sharedPreferences.getInt("value_int_type", (int)defaultBean.getValueIntType());
        valueIntTypeSubject.onNext(_value); return;
        }
        // value_locale - valueLocale
        case "value_locale": {
        String temp=sharedPreferences.getString("value_locale", null);
        Locale _value=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;
        valueLocaleSubject.onNext(_value); return;
        }
        // value_long - valueLong
        case "value_long": {
        Long _value=sharedPreferences.getLong("value_long", (defaultBean.getValueLong()==null?0L:defaultBean.getValueLong()));
        valueLongSubject.onNext(_value); return;
        }
        // value_long_array - valueLongArray
        case "value_long_array": {
        String temp=sharedPreferences.getString("value_long_array", null);
        Long[] _value=StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.getValueLongArray();

        valueLongArraySubject.onNext(_value); return;
        }
        // value_long_list - valueLongList
        case "value_long_list": {
        String temp=sharedPreferences.getString("value_long_list", null);
        LinkedList<Long> _value=StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.getValueLongList();

        valueLongListSubject.onNext(_value); return;
        }
        // value_long_type - valueLongType
        case "value_long_type": {
        long _value=sharedPreferences.getLong("value_long_type", defaultBean.getValueLongType());
        valueLongTypeSubject.onNext(_value); return;
        }
        // value_long_type_array - valueLongTypeArray
        case "value_long_type_array": {
        String temp=sharedPreferences.getString("value_long_type_array", null);
        long[] _value=StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.getValueLongTypeArray();

        valueLongTypeArraySubject.onNext(_value); return;
        }
        // value_short - valueShort
        case "value_short": {
        Short _value=(short)sharedPreferences.getInt("value_short", (short)(defaultBean.getValueShort()==null?(short)0:defaultBean.getValueShort()));
        valueShortSubject.onNext(_value); return;
        }
        // value_short_type - valueShortType
        case "value_short_type": {
        short _value=(short)sharedPreferences.getInt("value_short_type", (short)defaultBean.getValueShortType());
        valueShortTypeSubject.onNext(_value); return;
        }
        // value_string - valueString
        case "value_string": {
        String _value=sharedPreferences.getString("value_string", defaultBean.getValueString());
        valueStringSubject.onNext(_value); return;
        }
        // value_string_array - valueStringArray
        case "value_string_array": {
        String temp=sharedPreferences.getString("value_string_array", null);
        String[] _value=StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.getValueStringArray();

        valueStringArraySubject.onNext(_value); return;
        }
        // value_strin_list - valueStrinList
        case "value_strin_list": {
        String temp=sharedPreferences.getString("value_strin_list", null);
        LinkedList<String> _value=StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.getValueStrinList();

        valueStrinListSubject.onNext(_value); return;
        }
        // value_time - valueTime
        case "value_time": {
        String temp=sharedPreferences.getString("value_time", null);
        Time _value=(StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;
        valueTimeSubject.onNext(_value); return;
        }
        // value_time_list - valueTimeList
        case "value_time_list": {
        String temp=sharedPreferences.getString("value_time_list", null);
        List<Time> _value=StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.getValueTimeList();

        valueTimeListSubject.onNext(_value); return;
        }
        // value_time_zone - valueTimeZone
        case "value_time_zone": {
        String temp=sharedPreferences.getString("value_time_zone", null);
        TimeZone _value=(StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;
        valueTimeZoneSubject.onNext(_value); return;
        }
        // value_url - valueUrl
        case "value_url": {
        String temp=sharedPreferences.getString("value_url", null);
        URL _value=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;
        valueUrlSubject.onNext(_value); return;
        }
        default: return;
      }
    }
  };

  /**
   * Bean2BindMap */
  private Bean2BindMap bean2BindMap = BinderUtils.mapperFor(Bean2.class);

  /**
   * constructor
   */
  private BindBean2SharedPreferences() {
    createPrefs();
    defaultBean=new Bean2();
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
  public BindBean2SharedPreferences refresh() {
    createPrefs();
    return this;
  }

  /**
   * Obtains an observable to <code>id</code> property
   *
   * @return
   * an observable to <code>id</code> property
   */
  public Subject<Long> getIdAsObservable() {
    return idSubject;
  }

  /**
   * Obtains an observable to <code>valueBean</code> property
   *
   * @return
   * an observable to <code>valueBean</code> property
   */
  public Subject<Bean2> getValueBeanAsObservable() {
    return valueBeanSubject;
  }

  /**
   * Obtains an observable to <code>valueBeanArray</code> property
   *
   * @return
   * an observable to <code>valueBeanArray</code> property
   */
  public Subject<Bean2[]> getValueBeanArrayAsObservable() {
    return valueBeanArraySubject;
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
   * Obtains an observable to <code>valueBigInteger</code> property
   *
   * @return
   * an observable to <code>valueBigInteger</code> property
   */
  public Subject<BigInteger> getValueBigIntegerAsObservable() {
    return valueBigIntegerSubject;
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
   * Obtains an observable to <code>valueBoolType</code> property
   *
   * @return
   * an observable to <code>valueBoolType</code> property
   */
  public Subject<Boolean> getValueBoolTypeAsObservable() {
    return valueBoolTypeSubject;
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
   * Obtains an observable to <code>valueByteArray</code> property
   *
   * @return
   * an observable to <code>valueByteArray</code> property
   */
  public Subject<byte[]> getValueByteArrayAsObservable() {
    return valueByteArraySubject;
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
   * Obtains an observable to <code>valueCalendar</code> property
   *
   * @return
   * an observable to <code>valueCalendar</code> property
   */
  public Subject<Calendar> getValueCalendarAsObservable() {
    return valueCalendarSubject;
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
   * Obtains an observable to <code>valueCharArray</code> property
   *
   * @return
   * an observable to <code>valueCharArray</code> property
   */
  public Subject<Character[]> getValueCharArrayAsObservable() {
    return valueCharArraySubject;
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
   * Obtains an observable to <code>valueCharType</code> property
   *
   * @return
   * an observable to <code>valueCharType</code> property
   */
  public Subject<Character> getValueCharTypeAsObservable() {
    return valueCharTypeSubject;
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
   * Obtains an observable to <code>valueCurrency</code> property
   *
   * @return
   * an observable to <code>valueCurrency</code> property
   */
  public Subject<Currency> getValueCurrencyAsObservable() {
    return valueCurrencySubject;
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
   * Obtains an observable to <code>valueDouble</code> property
   *
   * @return
   * an observable to <code>valueDouble</code> property
   */
  public Subject<Double> getValueDoubleAsObservable() {
    return valueDoubleSubject;
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
   * Obtains an observable to <code>valueEnumType</code> property
   *
   * @return
   * an observable to <code>valueEnumType</code> property
   */
  public Subject<EnumType> getValueEnumTypeAsObservable() {
    return valueEnumTypeSubject;
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
   * Obtains an observable to <code>valueFloatType</code> property
   *
   * @return
   * an observable to <code>valueFloatType</code> property
   */
  public Subject<Float> getValueFloatTypeAsObservable() {
    return valueFloatTypeSubject;
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
   * Obtains an observable to <code>valueIntType</code> property
   *
   * @return
   * an observable to <code>valueIntType</code> property
   */
  public Subject<Integer> getValueIntTypeAsObservable() {
    return valueIntTypeSubject;
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
   * Obtains an observable to <code>valueLong</code> property
   *
   * @return
   * an observable to <code>valueLong</code> property
   */
  public Subject<Long> getValueLongAsObservable() {
    return valueLongSubject;
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
   * Obtains an observable to <code>valueLongList</code> property
   *
   * @return
   * an observable to <code>valueLongList</code> property
   */
  public Subject<LinkedList<Long>> getValueLongListAsObservable() {
    return valueLongListSubject;
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
   * Obtains an observable to <code>valueLongTypeArray</code> property
   *
   * @return
   * an observable to <code>valueLongTypeArray</code> property
   */
  public Subject<long[]> getValueLongTypeArrayAsObservable() {
    return valueLongTypeArraySubject;
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
   * Obtains an observable to <code>valueShortType</code> property
   *
   * @return
   * an observable to <code>valueShortType</code> property
   */
  public Subject<Short> getValueShortTypeAsObservable() {
    return valueShortTypeSubject;
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
   * Obtains an observable to <code>valueStringArray</code> property
   *
   * @return
   * an observable to <code>valueStringArray</code> property
   */
  public Subject<String[]> getValueStringArrayAsObservable() {
    return valueStringArraySubject;
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
   * Obtains an observable to <code>valueTime</code> property
   *
   * @return
   * an observable to <code>valueTime</code> property
   */
  public Subject<Time> getValueTimeAsObservable() {
    return valueTimeSubject;
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
   * Obtains an observable to <code>valueTimeZone</code> property
   *
   * @return
   * an observable to <code>valueTimeZone</code> property
   */
  public Subject<TimeZone> getValueTimeZoneAsObservable() {
    return valueTimeZoneSubject;
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
   * reset shared preferences
   */
  public void reset() {
    Bean2 bean=new Bean2();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public Bean2 read() {
    Bean2 bean=new Bean2();
    bean.setId(prefs.getLong("id", bean.getId()));
     {
      String temp=prefs.getString("value_bean", null);
      bean.setValueBean(StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.getValueBean());
    }

     {
      String temp=prefs.getString("value_bean_array", null);
      bean.setValueBeanArray(StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.getValueBeanArray());
    }

     {
      String temp=prefs.getString("value_big_decimal", "0");
      bean.setValueBigDecimal((StringUtils.hasText(temp)) ? new BigDecimal(temp): null);
    }

     {
      String temp=prefs.getString("value_big_integer", "0");
      bean.setValueBigInteger((StringUtils.hasText(temp)) ? new BigInteger(temp): null);
    }

    bean.setValueBool((boolean)prefs.getBoolean("value_bool", (boolean)(bean.getValueBool()==null?false:bean.getValueBool())));
    bean.setValueBoolType((boolean)prefs.getBoolean("value_bool_type", (boolean)bean.isValueBoolType()));
    bean.setValueByte((byte)prefs.getInt("value_byte", (byte)(bean.getValueByte()==null?(byte)0:bean.getValueByte())));
     {
      String temp=prefs.getString("value_byte_array", null);
      bean.setValueByteArray(StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.getValueByteArray());
    }

    bean.setValueByteType((byte)prefs.getInt("value_byte_type", (byte)bean.getValueByteType()));
     {
      String temp=prefs.getString("value_calendar", null);
      bean.setValueCalendar((StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null);}

    bean.setValueChar((char)prefs.getInt("value_char", (char)(bean.getValueChar()==null?(char)0:bean.getValueChar())));
     {
      String temp=prefs.getString("value_char_array", null);
      bean.setValueCharArray(StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.getValueCharArray());
    }

     {
      String temp=prefs.getString("value_char_list", null);
      bean.setValueCharList(StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.getValueCharList());
    }

    bean.setValueCharType((char)prefs.getInt("value_char_type", (char)bean.getValueCharType()));
     {
      String temp=prefs.getString("value_char_type_array", null);
      bean.setValueCharTypeArray(StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.getValueCharTypeArray());
    }

     {
      String temp=prefs.getString("value_currency", null);
      bean.setValueCurrency((StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null);}

     {
      String temp=prefs.getString("value_date", null);
      bean.setValueDate((StringUtils.hasText(temp)) ? DateUtils.read(temp): null);}

     {
      String temp=prefs.getString("value_double", null);
      bean.setValueDouble((StringUtils.hasText(temp)) ? Double.valueOf(temp): null);
    }

     {
      String temp=prefs.getString("value_double_type", null);
      bean.setValueDoubleType((StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0);
    }

     {
      String temp=prefs.getString("value_enum_type", null);
      bean.setValueEnumType((StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null);
    }

    bean.setValueFloat(prefs.getFloat("value_float", (bean.getValueFloat()==null?0F:bean.getValueFloat())));
    bean.setValueFloatType(prefs.getFloat("value_float_type", bean.getValueFloatType()));
    bean.setValueInt((int)prefs.getInt("value_int", (int)(bean.getValueInt()==null?0:bean.getValueInt())));
    bean.setValueIntType((int)prefs.getInt("value_int_type", (int)bean.getValueIntType()));
     {
      String temp=prefs.getString("value_locale", null);
      bean.setValueLocale((StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null);}

    bean.setValueLong(prefs.getLong("value_long", (bean.getValueLong()==null?0L:bean.getValueLong())));
     {
      String temp=prefs.getString("value_long_array", null);
      bean.setValueLongArray(StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.getValueLongArray());
    }

     {
      String temp=prefs.getString("value_long_list", null);
      bean.setValueLongList(StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.getValueLongList());
    }

    bean.setValueLongType(prefs.getLong("value_long_type", bean.getValueLongType()));
     {
      String temp=prefs.getString("value_long_type_array", null);
      bean.setValueLongTypeArray(StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.getValueLongTypeArray());
    }

    bean.setValueShort((short)prefs.getInt("value_short", (short)(bean.getValueShort()==null?(short)0:bean.getValueShort())));
    bean.setValueShortType((short)prefs.getInt("value_short_type", (short)bean.getValueShortType()));
    bean.setValueString(prefs.getString("value_string", bean.getValueString()));
     {
      String temp=prefs.getString("value_string_array", null);
      bean.setValueStringArray(StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.getValueStringArray());
    }

     {
      String temp=prefs.getString("value_strin_list", null);
      bean.setValueStrinList(StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.getValueStrinList());
    }

     {
      String temp=prefs.getString("value_time", null);
      bean.setValueTime((StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null);}

     {
      String temp=prefs.getString("value_time_list", null);
      bean.setValueTimeList(StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.getValueTimeList());
    }

     {
      String temp=prefs.getString("value_time_zone", null);
      bean.setValueTimeZone((StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null);}

     {
      String temp=prefs.getString("value_url", null);
      bean.setValueUrl((StringUtils.hasText(temp)) ? UrlUtils.read(temp): null);}


    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(Bean2 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putLong("id",bean.getId());

    if (bean.getValueBean()!=null)  {
      String temp=serializeValueBean(bean.getValueBean());
      editor.putString("value_bean",temp);
    }  else  {
      editor.remove("value_bean");
    }

    if (bean.getValueBeanArray()!=null)  {
      String temp=serializeValueBeanArray(bean.getValueBeanArray());
      editor.putString("value_bean_array",temp);
    }  else  {
      editor.remove("value_bean_array");
    }

    if (bean.getValueBigDecimal()!=null) editor.putString("value_big_decimal",bean.getValueBigDecimal().toPlainString() ); else editor.putString("value_big_decimal", null);
    if (bean.getValueBigInteger()!=null) editor.putString("value_big_integer",bean.getValueBigInteger().toString() ); else editor.putString("value_big_integer", null);
    if (bean.getValueBool()!=null)  {
      editor.putBoolean("value_bool",(boolean)bean.getValueBool());
    }

    editor.putBoolean("value_bool_type",(boolean)bean.isValueBoolType());

    if (bean.getValueByte()!=null)  {
      editor.putInt("value_byte",(int)bean.getValueByte());
    }

    if (bean.getValueByteArray()!=null)  {
      String temp=serializeValueByteArray(bean.getValueByteArray());
      editor.putString("value_byte_array",temp);
    }  else  {
      editor.remove("value_byte_array");
    }

    editor.putInt("value_byte_type",(int)bean.getValueByteType());

    if (bean.getValueCalendar()!=null)  {
      editor.putString("value_calendar",CalendarUtils.write(bean.getValueCalendar()));
    } else {
      editor.remove("valueCalendar");
    }

    if (bean.getValueChar()!=null)  {
      editor.putInt("value_char",(char)bean.getValueChar());
    }

    if (bean.getValueCharArray()!=null)  {
      String temp=serializeValueCharArray(bean.getValueCharArray());
      editor.putString("value_char_array",temp);
    }  else  {
      editor.remove("value_char_array");
    }

    if (bean.getValueCharList()!=null)  {
      String temp=serializeValueCharList(bean.getValueCharList());
      editor.putString("value_char_list",temp);
    }  else  {
      editor.remove("value_char_list");
    }

    editor.putInt("value_char_type",(char)bean.getValueCharType());

    if (bean.getValueCharTypeArray()!=null)  {
      String temp=serializeValueCharTypeArray(bean.getValueCharTypeArray());
      editor.putString("value_char_type_array",temp);
    }  else  {
      editor.remove("value_char_type_array");
    }

    if (bean.getValueCurrency()!=null)  {
      editor.putString("value_currency",CurrencyUtils.write(bean.getValueCurrency()));
    } else {
      editor.remove("valueCurrency");
    }

    if (bean.getValueDate()!=null)  {
      editor.putString("value_date",DateUtils.write(bean.getValueDate()));
    } else {
      editor.remove("valueDate");
    }

    if (bean.getValueDouble()!=null)  {
      editor.putString("value_double",String.valueOf(bean.getValueDouble()));
    } else {
      editor.remove("valueDouble");
    }

    editor.putString("value_double_type",String.valueOf(bean.getValueDoubleType()));

    if (bean.getValueEnumType()!=null)  {
      editor.putString("value_enum_type",bean.getValueEnumType().toString() );
    } else {
      editor.remove("valueEnumType");
    }

    if (bean.getValueFloat()!=null)  {
      editor.putFloat("value_float",bean.getValueFloat());
    }

    editor.putFloat("value_float_type",bean.getValueFloatType());

    if (bean.getValueInt()!=null)  {
      editor.putInt("value_int",(int)bean.getValueInt());
    }

    editor.putInt("value_int_type",(int)bean.getValueIntType());

    if (bean.getValueLocale()!=null)  {
      editor.putString("value_locale",LocaleUtils.write(bean.getValueLocale()));
    } else {
      editor.remove("valueLocale");
    }

    if (bean.getValueLong()!=null)  {
      editor.putLong("value_long",bean.getValueLong());
    }

    if (bean.getValueLongArray()!=null)  {
      String temp=serializeValueLongArray(bean.getValueLongArray());
      editor.putString("value_long_array",temp);
    }  else  {
      editor.remove("value_long_array");
    }

    if (bean.getValueLongList()!=null)  {
      String temp=serializeValueLongList(bean.getValueLongList());
      editor.putString("value_long_list",temp);
    }  else  {
      editor.remove("value_long_list");
    }

    editor.putLong("value_long_type",bean.getValueLongType());

    if (bean.getValueLongTypeArray()!=null)  {
      String temp=serializeValueLongTypeArray(bean.getValueLongTypeArray());
      editor.putString("value_long_type_array",temp);
    }  else  {
      editor.remove("value_long_type_array");
    }

    if (bean.getValueShort()!=null)  {
      editor.putInt("value_short",(int)bean.getValueShort());
    }

    editor.putInt("value_short_type",(int)bean.getValueShortType());

    editor.putString("value_string",bean.getValueString());

    if (bean.getValueStringArray()!=null)  {
      String temp=serializeValueStringArray(bean.getValueStringArray());
      editor.putString("value_string_array",temp);
    }  else  {
      editor.remove("value_string_array");
    }

    if (bean.getValueStrinList()!=null)  {
      String temp=serializeValueStrinList(bean.getValueStrinList());
      editor.putString("value_strin_list",temp);
    }  else  {
      editor.remove("value_strin_list");
    }

    if (bean.getValueTime()!=null)  {
      editor.putString("value_time",SQLTimeUtils.write(bean.getValueTime()));
    } else {
      editor.remove("valueTime");
    }

    if (bean.getValueTimeList()!=null)  {
      String temp=serializeValueTimeList(bean.getValueTimeList());
      editor.putString("value_time_list",temp);
    }  else  {
      editor.remove("value_time_list");
    }

    if (bean.getValueTimeZone()!=null)  {
      editor.putString("value_time_zone",TimeZoneUtils.write(bean.getValueTimeZone()));
    } else {
      editor.remove("valueTimeZone");
    }

    if (bean.getValueUrl()!=null)  {
      editor.putString("value_url",UrlUtils.write(bean.getValueUrl()));
    } else {
      editor.remove("valueUrl");
    }


    editor.commit();
  }

  /**
   * read property id
   *
   * @return property id value
   */
  public long getId() {
    return prefs.getLong("id", defaultBean.getId());}

  /**
   * read property valueBean
   *
   * @return property valueBean value
   */
  public Bean2 getValueBean() {
    String temp=prefs.getString("value_bean", null);
    return StringUtils.hasText(temp) ? parseValueBean(temp): defaultBean.getValueBean();
  }

  /**
   * read property valueBeanArray
   *
   * @return property valueBeanArray value
   */
  public Bean2[] getValueBeanArray() {
    String temp=prefs.getString("value_bean_array", null);
    return StringUtils.hasText(temp) ? parseValueBeanArray(temp): defaultBean.getValueBeanArray();
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
   * read property valueBigInteger
   *
   * @return property valueBigInteger value
   */
  public BigInteger getValueBigInteger() {
    String temp=prefs.getString("value_big_integer", "0");
    return (StringUtils.hasText(temp)) ? new BigInteger(temp): null;
  }

  /**
   * read property valueBool
   *
   * @return property valueBool value
   */
  public Boolean getValueBool() {
    return (boolean)prefs.getBoolean("value_bool", (boolean)(defaultBean.getValueBool()==null?false:defaultBean.getValueBool()));}

  /**
   * read property valueBoolType
   *
   * @return property valueBoolType value
   */
  public boolean getValueBoolType() {
    return (boolean)prefs.getBoolean("value_bool_type", (boolean)defaultBean.isValueBoolType());}

  /**
   * read property valueByte
   *
   * @return property valueByte value
   */
  public Byte getValueByte() {
    return (byte)prefs.getInt("value_byte", (byte)(defaultBean.getValueByte()==null?(byte)0:defaultBean.getValueByte()));}

  /**
   * read property valueByteArray
   *
   * @return property valueByteArray value
   */
  public byte[] getValueByteArray() {
    String temp=prefs.getString("value_byte_array", null);
    return StringUtils.hasText(temp) ? parseValueByteArray(temp): defaultBean.getValueByteArray();
  }

  /**
   * read property valueByteType
   *
   * @return property valueByteType value
   */
  public byte getValueByteType() {
    return (byte)prefs.getInt("value_byte_type", (byte)defaultBean.getValueByteType());}

  /**
   * read property valueCalendar
   *
   * @return property valueCalendar value
   */
  public Calendar getValueCalendar() {
    String temp=prefs.getString("value_calendar", null);
    return (StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;}

  /**
   * read property valueChar
   *
   * @return property valueChar value
   */
  public Character getValueChar() {
    return (char)prefs.getInt("value_char", (char)(defaultBean.getValueChar()==null?(char)0:defaultBean.getValueChar()));}

  /**
   * read property valueCharArray
   *
   * @return property valueCharArray value
   */
  public Character[] getValueCharArray() {
    String temp=prefs.getString("value_char_array", null);
    return StringUtils.hasText(temp) ? parseValueCharArray(temp): defaultBean.getValueCharArray();
  }

  /**
   * read property valueCharList
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> getValueCharList() {
    String temp=prefs.getString("value_char_list", null);
    return StringUtils.hasText(temp) ? parseValueCharList(temp): defaultBean.getValueCharList();
  }

  /**
   * read property valueCharType
   *
   * @return property valueCharType value
   */
  public char getValueCharType() {
    return (char)prefs.getInt("value_char_type", (char)defaultBean.getValueCharType());}

  /**
   * read property valueCharTypeArray
   *
   * @return property valueCharTypeArray value
   */
  public char[] getValueCharTypeArray() {
    String temp=prefs.getString("value_char_type_array", null);
    return StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): defaultBean.getValueCharTypeArray();
  }

  /**
   * read property valueCurrency
   *
   * @return property valueCurrency value
   */
  public Currency getValueCurrency() {
    String temp=prefs.getString("value_currency", null);
    return (StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;}

  /**
   * read property valueDate
   *
   * @return property valueDate value
   */
  public Date getValueDate() {
    String temp=prefs.getString("value_date", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): null;}

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
   * read property valueDoubleType
   *
   * @return property valueDoubleType value
   */
  public double getValueDoubleType() {
    String temp=prefs.getString("value_double_type", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;
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
   * read property valueFloat
   *
   * @return property valueFloat value
   */
  public Float getValueFloat() {
    return prefs.getFloat("value_float", (defaultBean.getValueFloat()==null?0F:defaultBean.getValueFloat()));}

  /**
   * read property valueFloatType
   *
   * @return property valueFloatType value
   */
  public float getValueFloatType() {
    return prefs.getFloat("value_float_type", defaultBean.getValueFloatType());}

  /**
   * read property valueInt
   *
   * @return property valueInt value
   */
  public Integer getValueInt() {
    return (int)prefs.getInt("value_int", (int)(defaultBean.getValueInt()==null?0:defaultBean.getValueInt()));}

  /**
   * read property valueIntType
   *
   * @return property valueIntType value
   */
  public int getValueIntType() {
    return (int)prefs.getInt("value_int_type", (int)defaultBean.getValueIntType());}

  /**
   * read property valueLocale
   *
   * @return property valueLocale value
   */
  public Locale getValueLocale() {
    String temp=prefs.getString("value_locale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;}

  /**
   * read property valueLong
   *
   * @return property valueLong value
   */
  public Long getValueLong() {
    return prefs.getLong("value_long", (defaultBean.getValueLong()==null?0L:defaultBean.getValueLong()));}

  /**
   * read property valueLongArray
   *
   * @return property valueLongArray value
   */
  public Long[] getValueLongArray() {
    String temp=prefs.getString("value_long_array", null);
    return StringUtils.hasText(temp) ? parseValueLongArray(temp): defaultBean.getValueLongArray();
  }

  /**
   * read property valueLongList
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> getValueLongList() {
    String temp=prefs.getString("value_long_list", null);
    return StringUtils.hasText(temp) ? parseValueLongList(temp): defaultBean.getValueLongList();
  }

  /**
   * read property valueLongType
   *
   * @return property valueLongType value
   */
  public long getValueLongType() {
    return prefs.getLong("value_long_type", defaultBean.getValueLongType());}

  /**
   * read property valueLongTypeArray
   *
   * @return property valueLongTypeArray value
   */
  public long[] getValueLongTypeArray() {
    String temp=prefs.getString("value_long_type_array", null);
    return StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): defaultBean.getValueLongTypeArray();
  }

  /**
   * read property valueShort
   *
   * @return property valueShort value
   */
  public Short getValueShort() {
    return (short)prefs.getInt("value_short", (short)(defaultBean.getValueShort()==null?(short)0:defaultBean.getValueShort()));}

  /**
   * read property valueShortType
   *
   * @return property valueShortType value
   */
  public short getValueShortType() {
    return (short)prefs.getInt("value_short_type", (short)defaultBean.getValueShortType());}

  /**
   * read property valueString
   *
   * @return property valueString value
   */
  public String getValueString() {
    return prefs.getString("value_string", defaultBean.getValueString());}

  /**
   * read property valueStringArray
   *
   * @return property valueStringArray value
   */
  public String[] getValueStringArray() {
    String temp=prefs.getString("value_string_array", null);
    return StringUtils.hasText(temp) ? parseValueStringArray(temp): defaultBean.getValueStringArray();
  }

  /**
   * read property valueStrinList
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> getValueStrinList() {
    String temp=prefs.getString("value_strin_list", null);
    return StringUtils.hasText(temp) ? parseValueStrinList(temp): defaultBean.getValueStrinList();
  }

  /**
   * read property valueTime
   *
   * @return property valueTime value
   */
  public Time getValueTime() {
    String temp=prefs.getString("value_time", null);
    return (StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;}

  /**
   * read property valueTimeList
   *
   * @return property valueTimeList value
   */
  public List<Time> getValueTimeList() {
    String temp=prefs.getString("value_time_list", null);
    return StringUtils.hasText(temp) ? parseValueTimeList(temp): defaultBean.getValueTimeList();
  }

  /**
   * read property valueTimeZone
   *
   * @return property valueTimeZone value
   */
  public TimeZone getValueTimeZone() {
    String temp=prefs.getString("value_time_zone", null);
    return (StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;}

  /**
   * read property valueUrl
   *
   * @return property valueUrl value
   */
  public URL getValueUrl() {
    String temp=prefs.getString("value_url", null);
    return (StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;}

  /**
   * for attribute valueBean serialization
   */
  protected String serializeValueBean(Bean2 value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        bean2BindMap.serializeOnJackson(value, jacksonSerializer);
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
  protected Bean2 parseValueBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean2 result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=bean2BindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray serialization
   */
  protected String serializeValueBeanArray(Bean2[] value) {
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
        Bean2 item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            bean2BindMap.serializeOnJackson(item, jacksonSerializer);
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
  protected Bean2[] parseValueBeanArray(String input) {
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
      Bean2[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Bean2> collection=new ArrayList<>();
        Bean2 item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=bean2BindMap.parseOnJackson(jacksonParser);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new Bean2[collection.size()]);
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
   * get instance of shared preferences
   */
  public static synchronized BindBean2SharedPreferences instance() {
    if (instance==null) {
      instance=new BindBean2SharedPreferences();
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
     * modifier for property id
     */
    public BindEditor putId(long value) {
      editor.putLong("id",value);

      return this;
    }

    /**
     * modifier for property valueBean
     */
    public BindEditor putValueBean(Bean2 value) {
      if (value!=null)  {
        String temp=serializeValueBean(value);
        editor.putString("value_bean",temp);
      }  else  {
        editor.remove("value_bean");
      }

      return this;
    }

    /**
     * modifier for property valueBeanArray
     */
    public BindEditor putValueBeanArray(Bean2[] value) {
      if (value!=null)  {
        String temp=serializeValueBeanArray(value);
        editor.putString("value_bean_array",temp);
      }  else  {
        editor.remove("value_bean_array");
      }

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
     * modifier for property valueBigInteger
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("value_big_integer",value.toString()); else editor.remove("value_big_integer");
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
     * modifier for property valueBoolType
     */
    public BindEditor putValueBoolType(boolean value) {
      editor.putBoolean("value_bool_type",(boolean)value);

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
     * modifier for property valueByteType
     */
    public BindEditor putValueByteType(byte value) {
      editor.putInt("value_byte_type",(int)value);

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
     * modifier for property valueChar
     */
    public BindEditor putValueChar(Character value) {
      if (value!=null)  {
        editor.putInt("value_char",(char)value);
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
     * modifier for property valueCharType
     */
    public BindEditor putValueCharType(char value) {
      editor.putInt("value_char_type",(char)value);

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
     * modifier for property valueDoubleType
     */
    public BindEditor putValueDoubleType(double value) {
      editor.putString("value_double_type",String.valueOf(value));

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
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(Float value) {
      if (value!=null)  {
        editor.putFloat("value_float",value);
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
     * modifier for property valueInt
     */
    public BindEditor putValueInt(Integer value) {
      if (value!=null)  {
        editor.putInt("value_int",(int)value);
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
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("value_long",value);
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
     * modifier for property valueLongType
     */
    public BindEditor putValueLongType(long value) {
      editor.putLong("value_long_type",value);

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
     * modifier for property valueShort
     */
    public BindEditor putValueShort(Short value) {
      if (value!=null)  {
        editor.putInt("value_short",(int)value);
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
     * modifier for property valueString
     */
    public BindEditor putValueString(String value) {
      editor.putString("value_string",value);

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
  }
}
