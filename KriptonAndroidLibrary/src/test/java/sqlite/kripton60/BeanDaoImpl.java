package sqlite.kripton60;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * <p>
 * DAO implementation for entity <code>Bean</code>, based on interface <code>BeanDao</code>
 * </p>
 *
 *  @see Bean
 *  @see BeanDao
 *  @see BeanTable
 */
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  /**
   * BeanBindMap */
  private BeanBindMap beanBindMap = AbstractContext.mapperFor(Bean.class);

  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueBigDecimal=${valueBigDecimal}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimal}</dt><dd>is mapped to method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param valueBigDecimal
   * 	is used as where parameter <strong>${valueBigDecimal}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(BigDecimal valueBigDecimal) {
    String[] whereConditionsArray={(valueBigDecimal==null?"":valueBigDecimal.toPlainString())};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueBigDecimal=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_big_decimal=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueBigDecimal=${valueBigDecimal}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimal}</dt><dd>is mapped to method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param valueBigDecimal
   * 	is used as where parameter <strong>${valueBigDecimal}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(BigInteger valueBigDecimal) {
    String[] whereConditionsArray={(valueBigDecimal==null?"":valueBigDecimal.toString())};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueBigDecimal=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_big_decimal=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueBoolType=${valueBoolType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBoolType}</dt><dd>is mapped to method's parameter <strong>valueBoolType</strong></dd>
   * </dl>
   *
   * @param valueBoolType
   * 	is used as where parameter <strong>${valueBoolType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(boolean valueBoolType) {
    String[] whereConditionsArray={String.valueOf(valueBoolType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueBoolType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_bool_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueBool=${valueBool}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBool}</dt><dd>is mapped to method's parameter <strong>valueBool</strong></dd>
   * </dl>
   *
   * @param valueBool
   * 	is used as where parameter <strong>${valueBool}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Boolean valueBool) {
    String[] whereConditionsArray={(valueBool==null?"":String.valueOf(valueBool))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueBool=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_bool=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueBeanArray=${valueBeanArray}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBeanArray}</dt><dd>is mapped to method's parameter <strong>valueBeanArray</strong></dd>
   * </dl>
   *
   * @param valueBeanArray
   * 	is used as where parameter <strong>${valueBeanArray}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteArrayBeanType(Bean[] valueBeanArray) {
    String[] whereConditionsArray={(valueBeanArray==null?"":new String(serializer1(valueBeanArray),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueBeanArray=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_bean_array=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueLongArray=${valueLongArray}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongArray}</dt><dd>is mapped to method's parameter <strong>valueLongArray</strong></dd>
   * </dl>
   *
   * @param valueLongArray
   * 	is used as where parameter <strong>${valueLongArray}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteArrayLong(Long[] valueLongArray) {
    String[] whereConditionsArray={(valueLongArray==null?"":new String(serializer2(valueLongArray),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueLongArray=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_long_array=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueLongTypeArray=${valueLongTypeArray}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongTypeArray}</dt><dd>is mapped to method's parameter <strong>valueLongTypeArray</strong></dd>
   * </dl>
   *
   * @param valueLongTypeArray
   * 	is used as where parameter <strong>${valueLongTypeArray}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteArrayLongType(long[] valueLongTypeArray) {
    String[] whereConditionsArray={(valueLongTypeArray==null?"":new String(serializer3(valueLongTypeArray),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueLongTypeArray=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_long_type_array=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueByte=${valueByte}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueByte}</dt><dd>is mapped to method's parameter <strong>valueByte</strong></dd>
   * </dl>
   *
   * @param valueByte
   * 	is used as where parameter <strong>${valueByte}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteByte(Byte valueByte) {
    String[] whereConditionsArray={(valueByte==null?"":String.valueOf(valueByte))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueByte=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_byte=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueByteType=${valueByteType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueByteType}</dt><dd>is mapped to method's parameter <strong>valueByteType</strong></dd>
   * </dl>
   *
   * @param valueByteType
   * 	is used as where parameter <strong>${valueByteType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteByteType(boolean valueByteType) {
    String[] whereConditionsArray={String.valueOf(valueByteType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueByteType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_byte_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueCalendar=${valueCalendar}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueCalendar}</dt><dd>is mapped to method's parameter <strong>valueCalendar</strong></dd>
   * </dl>
   *
   * @param valueCalendar
   * 	is used as where parameter <strong>${valueCalendar}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteCalendar(Date valueCalendar) {
    String[] whereConditionsArray={(valueCalendar==null?"":DateUtils.write(valueCalendar))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueCalendar=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_calendar=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueCharType=${valueChar}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueChar}</dt><dd>is mapped to method's parameter <strong>valueChar</strong></dd>
   * </dl>
   *
   * @param valueChar
   * 	is used as where parameter <strong>${valueChar}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteChar(Character valueChar) {
    String[] whereConditionsArray={(valueChar==null?"":String.valueOf((int)valueChar))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueCharType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_char_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueCharType=${valueCharType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueCharType}</dt><dd>is mapped to method's parameter <strong>valueCharType</strong></dd>
   * </dl>
   *
   * @param valueCharType
   * 	is used as where parameter <strong>${valueCharType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteCharType(char valueCharType) {
    String[] whereConditionsArray={String.valueOf((int)valueCharType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueCharType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_char_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueCurrency=${valueCurrency}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueCurrency}</dt><dd>is mapped to method's parameter <strong>valueCurrency</strong></dd>
   * </dl>
   *
   * @param valueCurrency
   * 	is used as where parameter <strong>${valueCurrency}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteCurrency(Currency valueCurrency) {
    String[] whereConditionsArray={(valueCurrency==null?"":CurrencyUtils.write(valueCurrency))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueCurrency=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_currency=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueDate=${valueDate}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueDate}</dt><dd>is mapped to method's parameter <strong>valueDate</strong></dd>
   * </dl>
   *
   * @param valueDate
   * 	is used as where parameter <strong>${valueDate}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteDate(Date valueDate) {
    String[] whereConditionsArray={(valueDate==null?"":DateUtils.write(valueDate))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueDate=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_date=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueDouble=${valueDouble}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueDouble}</dt><dd>is mapped to method's parameter <strong>valueDouble</strong></dd>
   * </dl>
   *
   * @param valueDouble
   * 	is used as where parameter <strong>${valueDouble}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteDouble(Double valueDouble) {
    String[] whereConditionsArray={(valueDouble==null?"":String.valueOf(valueDouble))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueDouble=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_double=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueDoubleType=${valueDoubleType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueDoubleType}</dt><dd>is mapped to method's parameter <strong>valueDoubleType</strong></dd>
   * </dl>
   *
   * @param valueDoubleType
   * 	is used as where parameter <strong>${valueDoubleType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteDoubleType(double valueDoubleType) {
    String[] whereConditionsArray={String.valueOf(valueDoubleType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueDoubleType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_double_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueEnumType=${valueEnumType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueEnumType}</dt><dd>is mapped to method's parameter <strong>valueEnumType</strong></dd>
   * </dl>
   *
   * @param valueEnumType
   * 	is used as where parameter <strong>${valueEnumType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteEnumType(EnumType valueEnumType) {
    String[] whereConditionsArray={(valueEnumType==null?"":valueEnumType.toString())};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueEnumType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_enum_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueFloat=${valueFloat}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueFloat}</dt><dd>is mapped to method's parameter <strong>valueFloat</strong></dd>
   * </dl>
   *
   * @param valueFloat
   * 	is used as where parameter <strong>${valueFloat}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteFloat(Float valueFloat) {
    String[] whereConditionsArray={(valueFloat==null?"":String.valueOf(valueFloat))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueFloat=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_float=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueFloatType=${valueFloatType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueFloatType}</dt><dd>is mapped to method's parameter <strong>valueFloatType</strong></dd>
   * </dl>
   *
   * @param valueFloatType
   * 	is used as where parameter <strong>${valueFloatType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteFloatType(float valueFloatType) {
    String[] whereConditionsArray={String.valueOf(valueFloatType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueFloatType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_float_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueInt=${valueInt}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueInt}</dt><dd>is mapped to method's parameter <strong>valueInt</strong></dd>
   * </dl>
   *
   * @param valueInt
   * 	is used as where parameter <strong>${valueInt}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteInt(Integer valueInt) {
    String[] whereConditionsArray={(valueInt==null?"":String.valueOf(valueInt))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueInt=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_int=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueIntType=${valueIntType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueIntType}</dt><dd>is mapped to method's parameter <strong>valueIntType</strong></dd>
   * </dl>
   *
   * @param valueIntType
   * 	is used as where parameter <strong>${valueIntType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteIntType(int valueIntType) {
    String[] whereConditionsArray={String.valueOf(valueIntType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueIntType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_int_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueLongList=${valueLongList}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongList}</dt><dd>is mapped to method's parameter <strong>valueLongList</strong></dd>
   * </dl>
   *
   * @param valueLongList
   * 	is used as where parameter <strong>${valueLongList}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteListLong(LinkedList<Long> valueLongList) {
    String[] whereConditionsArray={(valueLongList==null?"":new String(serializer4(valueLongList),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueLongList=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_long_list=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueLocale=${valueLocale}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLocale}</dt><dd>is mapped to method's parameter <strong>valueLocale</strong></dd>
   * </dl>
   *
   * @param valueLocale
   * 	is used as where parameter <strong>${valueLocale}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteLocale(Date valueLocale) {
    String[] whereConditionsArray={(valueLocale==null?"":DateUtils.write(valueLocale))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueLocale=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_locale=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueLong=${valueLong}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLong}</dt><dd>is mapped to method's parameter <strong>valueLong</strong></dd>
   * </dl>
   *
   * @param valueLong
   * 	is used as where parameter <strong>${valueLong}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteLong(Long valueLong) {
    String[] whereConditionsArray={(valueLong==null?"":String.valueOf(valueLong))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueLong=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_long=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueLongType=${valueLongType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongType}</dt><dd>is mapped to method's parameter <strong>valueLongType</strong></dd>
   * </dl>
   *
   * @param valueLongType
   * 	is used as where parameter <strong>${valueLongType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteLongType(long valueLongType) {
    String[] whereConditionsArray={String.valueOf(valueLongType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueLongType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_long_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueShort=${valueShort}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueShort}</dt><dd>is mapped to method's parameter <strong>valueShort</strong></dd>
   * </dl>
   *
   * @param valueShort
   * 	is used as where parameter <strong>${valueShort}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteShort(Short valueShort) {
    String[] whereConditionsArray={(valueShort==null?"":String.valueOf((int)valueShort))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueShort=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_short=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueShortType=${valueShortType}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueShortType}</dt><dd>is mapped to method's parameter <strong>valueShortType</strong></dd>
   * </dl>
   *
   * @param valueShortType
   * 	is used as where parameter <strong>${valueShortType}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteShortType(short valueShortType) {
    String[] whereConditionsArray={String.valueOf((int)valueShortType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueShortType=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_short_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueString=${valueString}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueString}</dt><dd>is mapped to method's parameter <strong>valueString</strong></dd>
   * </dl>
   *
   * @param valueString
   * 	is used as where parameter <strong>${valueString}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteString(String valueString) {
    String[] whereConditionsArray={(valueString==null?"":valueString)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueString=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_string=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueTime=${valueTime}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueTime}</dt><dd>is mapped to method's parameter <strong>valueTime</strong></dd>
   * </dl>
   *
   * @param valueTime
   * 	is used as where parameter <strong>${valueTime}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteTime(Time valueTime) {
    String[] whereConditionsArray={(valueTime==null?"":TimeUtils.write(valueTime))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueTime=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_time=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueTimeZone=${valueTimeZone}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueTimeZone}</dt><dd>is mapped to method's parameter <strong>valueTimeZone</strong></dd>
   * </dl>
   *
   * @param valueTimeZone
   * 	is used as where parameter <strong>${valueTimeZone}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteTimeZone(TimeZone valueTimeZone) {
    String[] whereConditionsArray={(valueTimeZone==null?"":TimeZoneUtils.write(valueTimeZone))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueTimeZone=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_time_zone=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean WHERE valueUrl=${valueUrl}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueUrl}</dt><dd>is mapped to method's parameter <strong>valueUrl</strong></dd>
   * </dl>
   *
   * @param valueUrl
   * 	is used as where parameter <strong>${valueUrl}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteURL(URL valueUrl) {
    String[] whereConditionsArray={(valueUrl==null?"":UrlUtils.write(valueUrl))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean WHERE valueUrl=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean", "value_url=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string) VALUES (${bean.valueBoolType}, ${bean.valueBool}, ${bean.valueByteType}, ${bean.valueByte}, ${bean.valueShortType}, ${bean.valueShort}, ${bean.valueIntType}, ${bean.valueInt}, ${bean.valueString}, ${bean.valueCharType}, ${bean.valueChar}, ${bean.valueFloatType}, ${bean.valueFloat}, ${bean.valueBigInteger}, ${bean.valueBigDecimal}, ${bean.valueEnumType}, ${bean.valueLongType}, ${bean.valueLong}, ${bean.valueDoubleType}, ${bean.valueDouble}, ${bean.valueLocale}, ${bean.valueCalendar}, ${bean.valueDate}, ${bean.valueUrl}, ${bean.valueTime}, ${bean.valueCurrency}, ${bean.valueTimeZone}, ${bean.valueTimeList}, ${bean.valueStrinList}, ${bean.valueLongList}, ${bean.valueByteArray}, ${bean.valueLongTypeArray}, ${bean.valueLongArray}, ${bean.valueBeanArray}, ${bean.valueStringArray}, ${bean.valueCharList}, ${bean.valueCharTypeArray}, ${bean.valueCharArray}, ${bean.valueMapStringBean}, ${bean.valueLinkedMapStringBean}, ${bean.valueSetString})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is mapped to <strong>${bean.valueBoolType}</strong></dd>
   * 	<dt>value_bool</dt><dd>is mapped to <strong>${bean.valueBool}</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is mapped to <strong>${bean.valueByteType}</strong></dd>
   * 	<dt>value_byte</dt><dd>is mapped to <strong>${bean.valueByte}</strong></dd>
   * 	<dt>value_short_type</dt><dd>is mapped to <strong>${bean.valueShortType}</strong></dd>
   * 	<dt>value_short</dt><dd>is mapped to <strong>${bean.valueShort}</strong></dd>
   * 	<dt>value_int_type</dt><dd>is mapped to <strong>${bean.valueIntType}</strong></dd>
   * 	<dt>value_int</dt><dd>is mapped to <strong>${bean.valueInt}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${bean.valueString}</strong></dd>
   * 	<dt>value_char_type</dt><dd>is mapped to <strong>${bean.valueCharType}</strong></dd>
   * 	<dt>value_char</dt><dd>is mapped to <strong>${bean.valueChar}</strong></dd>
   * 	<dt>value_float_type</dt><dd>is mapped to <strong>${bean.valueFloatType}</strong></dd>
   * 	<dt>value_float</dt><dd>is mapped to <strong>${bean.valueFloat}</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is mapped to <strong>${bean.valueBigInteger}</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is mapped to <strong>${bean.valueBigDecimal}</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is mapped to <strong>${bean.valueEnumType}</strong></dd>
   * 	<dt>value_long_type</dt><dd>is mapped to <strong>${bean.valueLongType}</strong></dd>
   * 	<dt>value_long</dt><dd>is mapped to <strong>${bean.valueLong}</strong></dd>
   * 	<dt>value_double_type</dt><dd>is mapped to <strong>${bean.valueDoubleType}</strong></dd>
   * 	<dt>value_double</dt><dd>is mapped to <strong>${bean.valueDouble}</strong></dd>
   * 	<dt>value_locale</dt><dd>is mapped to <strong>${bean.valueLocale}</strong></dd>
   * 	<dt>value_calendar</dt><dd>is mapped to <strong>${bean.valueCalendar}</strong></dd>
   * 	<dt>value_date</dt><dd>is mapped to <strong>${bean.valueDate}</strong></dd>
   * 	<dt>value_url</dt><dd>is mapped to <strong>${bean.valueUrl}</strong></dd>
   * 	<dt>value_time</dt><dd>is mapped to <strong>${bean.valueTime}</strong></dd>
   * 	<dt>value_currency</dt><dd>is mapped to <strong>${bean.valueCurrency}</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is mapped to <strong>${bean.valueTimeZone}</strong></dd>
   * 	<dt>value_time_list</dt><dd>is mapped to <strong>${bean.valueTimeList}</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is mapped to <strong>${bean.valueStrinList}</strong></dd>
   * 	<dt>value_long_list</dt><dd>is mapped to <strong>${bean.valueLongList}</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is mapped to <strong>${bean.valueByteArray}</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is mapped to <strong>${bean.valueLongTypeArray}</strong></dd>
   * 	<dt>value_long_array</dt><dd>is mapped to <strong>${bean.valueLongArray}</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is mapped to <strong>${bean.valueBeanArray}</strong></dd>
   * 	<dt>value_string_array</dt><dd>is mapped to <strong>${bean.valueStringArray}</strong></dd>
   * 	<dt>value_char_list</dt><dd>is mapped to <strong>${bean.valueCharList}</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is mapped to <strong>${bean.valueCharTypeArray}</strong></dd>
   * 	<dt>value_char_array</dt><dd>is mapped to <strong>${bean.valueCharArray}</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is mapped to <strong>${bean.valueMapStringBean}</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is mapped to <strong>${bean.valueLinkedMapStringBean}</strong></dd>
   * 	<dt>value_set_string</dt><dd>is mapped to <strong>${bean.valueSetString}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_bool_type", bean.valueBoolType);

    if (bean.valueBool!=null) {
      contentValues.put("value_bool", bean.valueBool);
    } else {
      contentValues.putNull("value_bool");
    }

    contentValues.put("value_byte_type", bean.valueByteType);

    if (bean.valueByte!=null) {
      contentValues.put("value_byte", bean.valueByte);
    } else {
      contentValues.putNull("value_byte");
    }

    contentValues.put("value_short_type", (int)bean.valueShortType);

    if (bean.valueShort!=null) {
      contentValues.put("value_short", (int)bean.valueShort);
    } else {
      contentValues.putNull("value_short");
    }

    contentValues.put("value_int_type", bean.valueIntType);

    if (bean.valueInt!=null) {
      contentValues.put("value_int", bean.valueInt);
    } else {
      contentValues.putNull("value_int");
    }

    if (bean.valueString!=null) {
      contentValues.put("value_string", bean.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    contentValues.put("value_char_type", (int)bean.valueCharType);

    if (bean.valueChar!=null) {
      contentValues.put("value_char", (int)bean.valueChar);
    } else {
      contentValues.putNull("value_char");
    }

    contentValues.put("value_float_type", bean.valueFloatType);

    if (bean.valueFloat!=null) {
      contentValues.put("value_float", bean.valueFloat);
    } else {
      contentValues.putNull("value_float");
    }

    if (bean.valueBigInteger!=null) {
      contentValues.put("value_big_integer", bean.valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    if (bean.valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", bean.valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    if (bean.valueEnumType!=null) {
      contentValues.put("value_enum_type", bean.valueEnumType.toString());
    } else {
      contentValues.putNull("value_enum_type");
    }

    contentValues.put("value_long_type", bean.valueLongType);

    if (bean.valueLong!=null) {
      contentValues.put("value_long", bean.valueLong);
    } else {
      contentValues.putNull("value_long");
    }

    contentValues.put("value_double_type", bean.valueDoubleType);

    if (bean.valueDouble!=null) {
      contentValues.put("value_double", bean.valueDouble);
    } else {
      contentValues.putNull("value_double");
    }

    if (bean.valueLocale!=null) {
      contentValues.put("value_locale", LocaleUtils.write(bean.valueLocale));
    } else {
      contentValues.putNull("value_locale");
    }

    if (bean.valueCalendar!=null) {
      contentValues.put("value_calendar", CalendarUtils.write(bean.valueCalendar));
    } else {
      contentValues.putNull("value_calendar");
    }

    if (bean.valueDate!=null) {
      contentValues.put("value_date", DateUtils.write(bean.valueDate));
    } else {
      contentValues.putNull("value_date");
    }

    if (bean.valueUrl!=null) {
      contentValues.put("value_url", UrlUtils.write(bean.valueUrl));
    } else {
      contentValues.putNull("value_url");
    }

    if (bean.valueTime!=null) {
      contentValues.put("value_time", TimeUtils.write(bean.valueTime));
    } else {
      contentValues.putNull("value_time");
    }

    if (bean.valueCurrency!=null) {
      contentValues.put("value_currency", CurrencyUtils.write(bean.valueCurrency));
    } else {
      contentValues.putNull("value_currency");
    }

    if (bean.valueTimeZone!=null) {
      contentValues.put("value_time_zone", TimeZoneUtils.write(bean.valueTimeZone));
    } else {
      contentValues.putNull("value_time_zone");
    }

    if (bean.valueTimeList!=null) {
      contentValues.put("value_time_list", BeanTable.serializeValueTimeList(bean.valueTimeList));
    } else {
      contentValues.putNull("value_time_list");
    }

    if (bean.valueStrinList!=null) {
      contentValues.put("value_strin_list", BeanTable.serializeValueStrinList(bean.valueStrinList));
    } else {
      contentValues.putNull("value_strin_list");
    }

    if (bean.valueLongList!=null) {
      contentValues.put("value_long_list", BeanTable.serializeValueLongList(bean.valueLongList));
    } else {
      contentValues.putNull("value_long_list");
    }

    if (bean.valueByteArray!=null) {
      contentValues.put("value_byte_array", BeanTable.serializeValueByteArray(bean.valueByteArray));
    } else {
      contentValues.putNull("value_byte_array");
    }

    if (bean.valueLongTypeArray!=null) {
      contentValues.put("value_long_type_array", BeanTable.serializeValueLongTypeArray(bean.valueLongTypeArray));
    } else {
      contentValues.putNull("value_long_type_array");
    }

    if (bean.valueLongArray!=null) {
      contentValues.put("value_long_array", BeanTable.serializeValueLongArray(bean.valueLongArray));
    } else {
      contentValues.putNull("value_long_array");
    }

    if (bean.valueBeanArray!=null) {
      contentValues.put("value_bean_array", BeanTable.serializeValueBeanArray(bean.valueBeanArray));
    } else {
      contentValues.putNull("value_bean_array");
    }

    if (bean.valueStringArray!=null) {
      contentValues.put("value_string_array", BeanTable.serializeValueStringArray(bean.valueStringArray));
    } else {
      contentValues.putNull("value_string_array");
    }

    if (bean.valueCharList!=null) {
      contentValues.put("value_char_list", BeanTable.serializeValueCharList(bean.valueCharList));
    } else {
      contentValues.putNull("value_char_list");
    }

    if (bean.valueCharTypeArray!=null) {
      contentValues.put("value_char_type_array", BeanTable.serializeValueCharTypeArray(bean.valueCharTypeArray));
    } else {
      contentValues.putNull("value_char_type_array");
    }

    if (bean.valueCharArray!=null) {
      contentValues.put("value_char_array", BeanTable.serializeValueCharArray(bean.valueCharArray));
    } else {
      contentValues.putNull("value_char_array");
    }

    if (bean.valueMapStringBean!=null) {
      contentValues.put("value_map_string_bean", BeanTable.serializeValueMapStringBean(bean.valueMapStringBean));
    } else {
      contentValues.putNull("value_map_string_bean");
    }

    if (bean.valueLinkedMapStringBean!=null) {
      contentValues.put("value_linked_map_string_bean", BeanTable.serializeValueLinkedMapStringBean(bean.valueLinkedMapStringBean));
    } else {
      contentValues.putNull("value_linked_map_string_bean");
    }

    if (bean.valueSetString!=null) {
      contentValues.put("value_set_string", BeanTable.serializeValueSetString(bean.valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string) VALUES ('"+StringUtils.checkSize(contentValues.get("value_bool_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_bool"))+"', '"+StringUtils.checkSize(contentValues.get("value_byte_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_byte"))+"', '"+StringUtils.checkSize(contentValues.get("value_short_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_short"))+"', '"+StringUtils.checkSize(contentValues.get("value_int_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_int"))+"', '"+StringUtils.checkSize(contentValues.get("value_string"))+"', '"+StringUtils.checkSize(contentValues.get("value_char_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_char"))+"', '"+StringUtils.checkSize(contentValues.get("value_float_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_float"))+"', '"+StringUtils.checkSize(contentValues.get("value_big_integer"))+"', '"+StringUtils.checkSize(contentValues.get("value_big_decimal"))+"', '"+StringUtils.checkSize(contentValues.get("value_enum_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_long_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_long"))+"', '"+StringUtils.checkSize(contentValues.get("value_double_type"))+"', '"+StringUtils.checkSize(contentValues.get("value_double"))+"', '"+StringUtils.checkSize(contentValues.get("value_locale"))+"', '"+StringUtils.checkSize(contentValues.get("value_calendar"))+"', '"+StringUtils.checkSize(contentValues.get("value_date"))+"', '"+StringUtils.checkSize(contentValues.get("value_url"))+"', '"+StringUtils.checkSize(contentValues.get("value_time"))+"', '"+StringUtils.checkSize(contentValues.get("value_currency"))+"', '"+StringUtils.checkSize(contentValues.get("value_time_zone"))+"', '"+StringUtils.checkSize(contentValues.get("value_time_list"))+"', '"+StringUtils.checkSize(contentValues.get("value_strin_list"))+"', '"+StringUtils.checkSize(contentValues.get("value_long_list"))+"', '"+StringUtils.checkSize(contentValues.get("value_byte_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_long_type_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_long_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_bean_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_string_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_char_list"))+"', '"+StringUtils.checkSize(contentValues.get("value_char_type_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_char_array"))+"', '"+StringUtils.checkSize(contentValues.get("value_map_string_bean"))+"', '"+StringUtils.checkSize(contentValues.get("value_linked_map_string_bean"))+"', '"+StringUtils.checkSize(contentValues.get("value_set_string"))+"')"));
    long result = database().insert("bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_big_decimal) VALUES (${valueBigDecimal})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_big_decimal</dt><dd>is binded to query's parameter <strong>${valueBigDecimal}</strong> and method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param valueBigDecimal
   * 	is binded to column <strong>value_big_decimal</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(BigDecimal valueBigDecimal) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_big_decimal) VALUES ('"+StringUtils.checkSize(contentValues.get("value_big_decimal"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_big_integer) VALUES (${valueBigInteger})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_big_integer</dt><dd>is binded to query's parameter <strong>${valueBigInteger}</strong> and method's parameter <strong>valueBigInteger</strong></dd>
   * </dl>
   *
   * @param valueBigInteger
   * 	is binded to column <strong>value_big_integer</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(BigInteger valueBigInteger) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBigInteger!=null) {
      contentValues.put("value_big_integer", valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_big_integer) VALUES ('"+StringUtils.checkSize(contentValues.get("value_big_integer"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_bool_type) VALUES (${valueBoolType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is binded to query's parameter <strong>${valueBoolType}</strong> and method's parameter <strong>valueBoolType</strong></dd>
   * </dl>
   *
   * @param valueBoolType
   * 	is binded to column <strong>value_bool_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(boolean valueBoolType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_bool_type", valueBoolType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_bool_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_bool_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_bool) VALUES (${valueBool})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_bool</dt><dd>is binded to query's parameter <strong>${valueBool}</strong> and method's parameter <strong>valueBool</strong></dd>
   * </dl>
   *
   * @param valueBool
   * 	is binded to column <strong>value_bool</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Boolean valueBool) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBool!=null) {
      contentValues.put("value_bool", valueBool);
    } else {
      contentValues.putNull("value_bool");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_bool) VALUES ('"+StringUtils.checkSize(contentValues.get("value_bool"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_bean_array) VALUES (${valueBeanArray})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_bean_array</dt><dd>is binded to query's parameter <strong>${valueBeanArray}</strong> and method's parameter <strong>valueBeanArray</strong></dd>
   * </dl>
   *
   * @param valueBeanArray
   * 	is binded to column <strong>value_bean_array</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertArrayBeanType(Bean[] valueBeanArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBeanArray!=null) {
      contentValues.put("value_bean_array", serializer1(valueBeanArray));
    } else {
      contentValues.putNull("value_bean_array");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_bean_array) VALUES ('"+StringUtils.checkSize(contentValues.get("value_bean_array"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_long_array) VALUES (${valueLongArray})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_long_array</dt><dd>is binded to query's parameter <strong>${valueLongArray}</strong> and method's parameter <strong>valueLongArray</strong></dd>
   * </dl>
   *
   * @param valueLongArray
   * 	is binded to column <strong>value_long_array</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertArrayLong(Long[] valueLongArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLongArray!=null) {
      contentValues.put("value_long_array", serializer2(valueLongArray));
    } else {
      contentValues.putNull("value_long_array");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_long_array) VALUES ('"+StringUtils.checkSize(contentValues.get("value_long_array"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_long_type_array) VALUES (${valueLongTypeArray})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_long_type_array</dt><dd>is binded to query's parameter <strong>${valueLongTypeArray}</strong> and method's parameter <strong>valueLongTypeArray</strong></dd>
   * </dl>
   *
   * @param valueLongTypeArray
   * 	is binded to column <strong>value_long_type_array</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertArrayLongType(long[] valueLongTypeArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLongTypeArray!=null) {
      contentValues.put("value_long_type_array", serializer3(valueLongTypeArray));
    } else {
      contentValues.putNull("value_long_type_array");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_long_type_array) VALUES ('"+StringUtils.checkSize(contentValues.get("value_long_type_array"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_byte) VALUES (${valueByte})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_byte</dt><dd>is binded to query's parameter <strong>${valueByte}</strong> and method's parameter <strong>valueByte</strong></dd>
   * </dl>
   *
   * @param valueByte
   * 	is binded to column <strong>value_byte</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertByte(Byte valueByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueByte!=null) {
      contentValues.put("value_byte", valueByte);
    } else {
      contentValues.putNull("value_byte");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_byte) VALUES ('"+StringUtils.checkSize(contentValues.get("value_byte"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_byte_type) VALUES (${valueByteType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_byte_type</dt><dd>is binded to query's parameter <strong>${valueByteType}</strong> and method's parameter <strong>valueByteType</strong></dd>
   * </dl>
   *
   * @param valueByteType
   * 	is binded to column <strong>value_byte_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertByteType(byte valueByteType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_byte_type", valueByteType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_byte_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_byte_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_calendar) VALUES (${valueCalendar})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_calendar</dt><dd>is binded to query's parameter <strong>${valueCalendar}</strong> and method's parameter <strong>valueCalendar</strong></dd>
   * </dl>
   *
   * @param valueCalendar
   * 	is binded to column <strong>value_calendar</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCalendar(Calendar valueCalendar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueCalendar!=null) {
      contentValues.put("value_calendar", CalendarUtils.write(valueCalendar));
    } else {
      contentValues.putNull("value_calendar");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_calendar) VALUES ('"+StringUtils.checkSize(contentValues.get("value_calendar"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_char) VALUES (${valueChar})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_char</dt><dd>is binded to query's parameter <strong>${valueChar}</strong> and method's parameter <strong>valueChar</strong></dd>
   * </dl>
   *
   * @param valueChar
   * 	is binded to column <strong>value_char</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertChar(Character valueChar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueChar!=null) {
      contentValues.put("value_char", (int)valueChar);
    } else {
      contentValues.putNull("value_char");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_char) VALUES ('"+StringUtils.checkSize(contentValues.get("value_char"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_char_type) VALUES (${valueCharType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_char_type</dt><dd>is binded to query's parameter <strong>${valueCharType}</strong> and method's parameter <strong>valueCharType</strong></dd>
   * </dl>
   *
   * @param valueCharType
   * 	is binded to column <strong>value_char_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCharType(char valueCharType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_char_type", (int)valueCharType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_char_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_char_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_currency) VALUES (${valueCurrency})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_currency</dt><dd>is binded to query's parameter <strong>${valueCurrency}</strong> and method's parameter <strong>valueCurrency</strong></dd>
   * </dl>
   *
   * @param valueCurrency
   * 	is binded to column <strong>value_currency</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertCurrency(Currency valueCurrency) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueCurrency!=null) {
      contentValues.put("value_currency", CurrencyUtils.write(valueCurrency));
    } else {
      contentValues.putNull("value_currency");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_currency) VALUES ('"+StringUtils.checkSize(contentValues.get("value_currency"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_date) VALUES (${valueDate})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_date</dt><dd>is binded to query's parameter <strong>${valueDate}</strong> and method's parameter <strong>valueDate</strong></dd>
   * </dl>
   *
   * @param valueDate
   * 	is binded to column <strong>value_date</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertDate(Date valueDate) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueDate!=null) {
      contentValues.put("value_date", DateUtils.write(valueDate));
    } else {
      contentValues.putNull("value_date");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_date) VALUES ('"+StringUtils.checkSize(contentValues.get("value_date"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_double) VALUES (${valueDouble})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_double</dt><dd>is binded to query's parameter <strong>${valueDouble}</strong> and method's parameter <strong>valueDouble</strong></dd>
   * </dl>
   *
   * @param valueDouble
   * 	is binded to column <strong>value_double</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertDouble(Double valueDouble) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueDouble!=null) {
      contentValues.put("value_double", valueDouble);
    } else {
      contentValues.putNull("value_double");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_double) VALUES ('"+StringUtils.checkSize(contentValues.get("value_double"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_double_type) VALUES (${valueDoubleType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_double_type</dt><dd>is binded to query's parameter <strong>${valueDoubleType}</strong> and method's parameter <strong>valueDoubleType</strong></dd>
   * </dl>
   *
   * @param valueDoubleType
   * 	is binded to column <strong>value_double_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertDoubleType(double valueDoubleType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_double_type", valueDoubleType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_double_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_double_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_enum_type) VALUES (${valueEnumType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_enum_type</dt><dd>is binded to query's parameter <strong>${valueEnumType}</strong> and method's parameter <strong>valueEnumType</strong></dd>
   * </dl>
   *
   * @param valueEnumType
   * 	is binded to column <strong>value_enum_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertEnumType(EnumType valueEnumType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueEnumType!=null) {
      contentValues.put("value_enum_type", valueEnumType.toString());
    } else {
      contentValues.putNull("value_enum_type");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_enum_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_enum_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_float) VALUES (${valueFloat})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_float</dt><dd>is binded to query's parameter <strong>${valueFloat}</strong> and method's parameter <strong>valueFloat</strong></dd>
   * </dl>
   *
   * @param valueFloat
   * 	is binded to column <strong>value_float</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertFloat(Float valueFloat) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueFloat!=null) {
      contentValues.put("value_float", valueFloat);
    } else {
      contentValues.putNull("value_float");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_float) VALUES ('"+StringUtils.checkSize(contentValues.get("value_float"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_float_type) VALUES (${valueFloatType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_float_type</dt><dd>is binded to query's parameter <strong>${valueFloatType}</strong> and method's parameter <strong>valueFloatType</strong></dd>
   * </dl>
   *
   * @param valueFloatType
   * 	is binded to column <strong>value_float_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertFloatType(float valueFloatType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_float_type", valueFloatType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_float_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_float_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_int) VALUES (${valueInt})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_int</dt><dd>is binded to query's parameter <strong>${valueInt}</strong> and method's parameter <strong>valueInt</strong></dd>
   * </dl>
   *
   * @param valueInt
   * 	is binded to column <strong>value_int</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertInt(Integer valueInt) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueInt!=null) {
      contentValues.put("value_int", valueInt);
    } else {
      contentValues.putNull("value_int");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_int) VALUES ('"+StringUtils.checkSize(contentValues.get("value_int"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_int_type) VALUES (${valueIntType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_int_type</dt><dd>is binded to query's parameter <strong>${valueIntType}</strong> and method's parameter <strong>valueIntType</strong></dd>
   * </dl>
   *
   * @param valueIntType
   * 	is binded to column <strong>value_int_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertIntType(int valueIntType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_int_type", valueIntType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_int_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_int_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_long_list) VALUES (${valueLongList})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_long_list</dt><dd>is binded to query's parameter <strong>${valueLongList}</strong> and method's parameter <strong>valueLongList</strong></dd>
   * </dl>
   *
   * @param valueLongList
   * 	is binded to column <strong>value_long_list</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertListLong(LinkedList<Long> valueLongList) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLongList!=null) {
      contentValues.put("value_long_list", serializer4(valueLongList));
    } else {
      contentValues.putNull("value_long_list");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_long_list) VALUES ('"+StringUtils.checkSize(contentValues.get("value_long_list"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_locale) VALUES (${valueLocale})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_locale</dt><dd>is binded to query's parameter <strong>${valueLocale}</strong> and method's parameter <strong>valueLocale</strong></dd>
   * </dl>
   *
   * @param valueLocale
   * 	is binded to column <strong>value_locale</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertLocale(Locale valueLocale) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLocale!=null) {
      contentValues.put("value_locale", LocaleUtils.write(valueLocale));
    } else {
      contentValues.putNull("value_locale");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_locale) VALUES ('"+StringUtils.checkSize(contentValues.get("value_locale"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_long) VALUES (${valueLong})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_long</dt><dd>is binded to query's parameter <strong>${valueLong}</strong> and method's parameter <strong>valueLong</strong></dd>
   * </dl>
   *
   * @param valueLong
   * 	is binded to column <strong>value_long</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertLong(Long valueLong) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueLong!=null) {
      contentValues.put("value_long", valueLong);
    } else {
      contentValues.putNull("value_long");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_long) VALUES ('"+StringUtils.checkSize(contentValues.get("value_long"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_long_type) VALUES (${valueLongType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_long_type</dt><dd>is binded to query's parameter <strong>${valueLongType}</strong> and method's parameter <strong>valueLongType</strong></dd>
   * </dl>
   *
   * @param valueLongType
   * 	is binded to column <strong>value_long_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertLongType(long valueLongType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_long_type", valueLongType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_long_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_long_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_short) VALUES (${valueShort})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_short</dt><dd>is binded to query's parameter <strong>${valueShort}</strong> and method's parameter <strong>valueShort</strong></dd>
   * </dl>
   *
   * @param valueShort
   * 	is binded to column <strong>value_short</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertShort(Short valueShort) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueShort!=null) {
      contentValues.put("value_short", (int)valueShort);
    } else {
      contentValues.putNull("value_short");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_short) VALUES ('"+StringUtils.checkSize(contentValues.get("value_short"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_short_type) VALUES (${valueShortType})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_short_type</dt><dd>is binded to query's parameter <strong>${valueShortType}</strong> and method's parameter <strong>valueShortType</strong></dd>
   * </dl>
   *
   * @param valueShortType
   * 	is binded to column <strong>value_short_type</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertShortType(short valueShortType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_short_type", (int)valueShortType);

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_short_type) VALUES ('"+StringUtils.checkSize(contentValues.get("value_short_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_string) VALUES (${valueString})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_string</dt><dd>is binded to query's parameter <strong>${valueString}</strong> and method's parameter <strong>valueString</strong></dd>
   * </dl>
   *
   * @param valueString
   * 	is binded to column <strong>value_string</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertString(String valueString) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueString!=null) {
      contentValues.put("value_string", valueString);
    } else {
      contentValues.putNull("value_string");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_string) VALUES ('"+StringUtils.checkSize(contentValues.get("value_string"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_time) VALUES (${valueTime})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_time</dt><dd>is binded to query's parameter <strong>${valueTime}</strong> and method's parameter <strong>valueTime</strong></dd>
   * </dl>
   *
   * @param valueTime
   * 	is binded to column <strong>value_time</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertTime(Time valueTime) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueTime!=null) {
      contentValues.put("value_time", TimeUtils.write(valueTime));
    } else {
      contentValues.putNull("value_time");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_time) VALUES ('"+StringUtils.checkSize(contentValues.get("value_time"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_time_zone) VALUES (${valueTimeZone})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_time_zone</dt><dd>is binded to query's parameter <strong>${valueTimeZone}</strong> and method's parameter <strong>valueTimeZone</strong></dd>
   * </dl>
   *
   * @param valueTimeZone
   * 	is binded to column <strong>value_time_zone</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertTimeZone(TimeZone valueTimeZone) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueTimeZone!=null) {
      contentValues.put("value_time_zone", TimeZoneUtils.write(valueTimeZone));
    } else {
      contentValues.putNull("value_time_zone");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_time_zone) VALUES ('"+StringUtils.checkSize(contentValues.get("value_time_zone"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean (value_url) VALUES (${valueUrl})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value_url</dt><dd>is binded to query's parameter <strong>${valueUrl}</strong> and method's parameter <strong>valueUrl</strong></dd>
   * </dl>
   *
   * @param valueUrl
   * 	is binded to column <strong>value_url</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insertURL(URL valueUrl) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueUrl!=null) {
      contentValues.put("value_url", UrlUtils.write(valueUrl));
    } else {
      contentValues.putNull("value_url");
    }

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean (value_url) VALUES ('"+StringUtils.checkSize(contentValues.get("value_url"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean> selectList(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = '%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Bean> resultList=new LinkedList<Bean>();
      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        do
         {
          resultBean=new Bean();

          if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
          if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
          if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
          if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
          if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
          if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
          if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
          if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
          if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
          if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
          if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
          if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
          if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
          if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
          if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
          if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
          if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
          if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
          if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
          if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
          if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
          if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
          if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
          if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
          if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
          if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
          if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
          if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
          if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
          if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
          if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
          if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
          if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
          if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
          if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
          if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
          if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
          if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
          if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
          if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
          if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
          if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimal}</dt><dd>is binded to method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param valueBigDecimal
   * 	is binded to <code>${valueBigDecimal}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(BigDecimal valueBigDecimal) {
    // build where condition
    String[] args={(valueBigDecimal==null?"":valueBigDecimal.toPlainString())};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimal}</dt><dd>is binded to method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param valueBigDecimal
   * 	is binded to <code>${valueBigDecimal}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(BigInteger valueBigDecimal) {
    // build where condition
    String[] args={(valueBigDecimal==null?"":valueBigDecimal.toString())};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_big_decimal=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBoolType=${valueBoolType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueBoolType}</dt><dd>is binded to method's parameter <strong>valueBoolType</strong></dd>
   * </dl>
   *
   * @param valueBoolType
   * 	is binded to <code>${valueBoolType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(boolean valueBoolType) {
    // build where condition
    String[] args={String.valueOf(valueBoolType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBool=${valueBool}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueBool}</dt><dd>is binded to method's parameter <strong>valueBool</strong></dd>
   * </dl>
   *
   * @param valueBool
   * 	is binded to <code>${valueBool}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(Boolean valueBool) {
    // build where condition
    String[] args={(valueBool==null?"":String.valueOf(valueBool))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bool=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the Bean listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = '%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Bean resultBean=new Bean();
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.valueBoolType=false;
          resultBean.valueBool=null;
          resultBean.valueByteType=0;
          resultBean.valueByte=0;
          resultBean.valueShortType=0;
          resultBean.valueShort=null;
          resultBean.valueIntType=0;
          resultBean.valueInt=null;
          resultBean.valueString=null;
          resultBean.valueCharType=0;
          resultBean.valueChar=0;
          resultBean.valueFloatType=0f;
          resultBean.valueFloat=null;
          resultBean.valueBigInteger=null;
          resultBean.valueBigDecimal=null;
          resultBean.valueEnumType=null;
          resultBean.valueLongType=0L;
          resultBean.valueLong=null;
          resultBean.valueDoubleType=0;
          resultBean.valueDouble=null;
          resultBean.valueLocale=null;
          resultBean.valueCalendar=null;
          resultBean.valueDate=null;
          resultBean.valueUrl=null;
          resultBean.valueTime=null;
          resultBean.valueCurrency=null;
          resultBean.valueTimeZone=null;
          resultBean.valueTimeList=null;
          resultBean.valueStrinList=null;
          resultBean.valueLongList=null;
          resultBean.valueByteArray=null;
          resultBean.valueLongTypeArray=null;
          resultBean.valueLongArray=null;
          resultBean.valueBeanArray=null;
          resultBean.valueStringArray=null;
          resultBean.valueCharList=null;
          resultBean.valueCharTypeArray=null;
          resultBean.valueCharArray=null;
          resultBean.valueMapStringBean=null;
          resultBean.valueLinkedMapStringBean=null;
          resultBean.valueSetString=null;
          resultBean.id=0L;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
          if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
          if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
          if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
          if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
          if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
          if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
          if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
          if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
          if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
          if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
          if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
          if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
          if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
          if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
          if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
          if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
          if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
          if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
          if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
          if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
          if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
          if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
          if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
          if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
          if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
          if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
          if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
          if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
          if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
          if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
          if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
          if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
          if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
          if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
          if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
          if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
          if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
          if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
          if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
          if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
          if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_bool</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_byte_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_byte</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_short_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_short</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_int_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_int</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_string</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_char_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_char</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_float_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_float</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_big_integer</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_big_decimal</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_enum_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_long_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_long</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_double_type</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_double</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_locale</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_calendar</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_date</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_url</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_time</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_currency</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_time_zone</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_time_list</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_strin_list</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_long_list</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_byte_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_long_type_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_long_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_bean_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_string_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_char_list</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_char_type_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_char_array</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_map_string_bean</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>no bean's property is associated</dd>
   * 	<dt>value_set_string</dt><dd>no bean's property is associated</dd>
   * 	<dt>id</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = '%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE id = ?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueBeanArray=${valueBeanArray}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueBeanArray}</dt><dd>is binded to method's parameter <strong>valueBeanArray</strong></dd>
   * </dl>
   *
   * @param valueBeanArray
   * 	is binded to <code>${valueBeanArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneArrayBeanType(Bean[] valueBeanArray) {
    // build where condition
    String[] args={(valueBeanArray==null?"":new String(serializer1(valueBeanArray),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bean_array='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_bean_array=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongArray=${valueLongArray}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueLongArray}</dt><dd>is binded to method's parameter <strong>valueLongArray</strong></dd>
   * </dl>
   *
   * @param valueLongArray
   * 	is binded to <code>${valueLongArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneArrayLong(Long[] valueLongArray) {
    // build where condition
    String[] args={(valueLongArray==null?"":new String(serializer2(valueLongArray),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_array='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_array=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongTypeArray=${valueLongTypeArray}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueLongTypeArray}</dt><dd>is binded to method's parameter <strong>valueLongTypeArray</strong></dd>
   * </dl>
   *
   * @param valueLongTypeArray
   * 	is binded to <code>${valueLongTypeArray}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneArrayLongType(long[] valueLongTypeArray) {
    // build where condition
    String[] args={(valueLongTypeArray==null?"":new String(serializer3(valueLongTypeArray),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type_array='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type_array=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueByte=${valueByte}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueByte}</dt><dd>is binded to method's parameter <strong>valueByte</strong></dd>
   * </dl>
   *
   * @param valueByte
   * 	is binded to <code>${valueByte}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneByte(Byte valueByte) {
    // build where condition
    String[] args={(valueByte==null?"":String.valueOf(valueByte))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueByteType=${valueByteType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueByteType}</dt><dd>is binded to method's parameter <strong>valueByteType</strong></dd>
   * </dl>
   *
   * @param valueByteType
   * 	is binded to <code>${valueByteType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneByteType(boolean valueByteType) {
    // build where condition
    String[] args={String.valueOf(valueByteType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_byte_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCalendar=${valueCalendar}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueCalendar}</dt><dd>is binded to method's parameter <strong>valueCalendar</strong></dd>
   * </dl>
   *
   * @param valueCalendar
   * 	is binded to <code>${valueCalendar}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneCalendar(Calendar valueCalendar) {
    // build where condition
    String[] args={(valueCalendar==null?"":CalendarUtils.write(valueCalendar))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_calendar='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_calendar=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCharType=${valueChar}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueChar}</dt><dd>is binded to method's parameter <strong>valueChar</strong></dd>
   * </dl>
   *
   * @param valueChar
   * 	is binded to <code>${valueChar}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneChar(Character valueChar) {
    // build where condition
    String[] args={(valueChar==null?"":String.valueOf((int)valueChar))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCharType=${valueCharType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueCharType}</dt><dd>is binded to method's parameter <strong>valueCharType</strong></dd>
   * </dl>
   *
   * @param valueCharType
   * 	is binded to <code>${valueCharType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneCharType(char valueCharType) {
    // build where condition
    String[] args={String.valueOf((int)valueCharType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_char_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueCurrency=${valueCurrency}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueCurrency}</dt><dd>is binded to method's parameter <strong>valueCurrency</strong></dd>
   * </dl>
   *
   * @param valueCurrency
   * 	is binded to <code>${valueCurrency}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneCurrencye(Currency valueCurrency) {
    // build where condition
    String[] args={(valueCurrency==null?"":CurrencyUtils.write(valueCurrency))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_currency='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_currency=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueDate=${valueDate}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueDate}</dt><dd>is binded to method's parameter <strong>valueDate</strong></dd>
   * </dl>
   *
   * @param valueDate
   * 	is binded to <code>${valueDate}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneDate(Date valueDate) {
    // build where condition
    String[] args={(valueDate==null?"":DateUtils.write(valueDate))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_date='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_date=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueDouble=${valueDouble}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueDouble}</dt><dd>is binded to method's parameter <strong>valueDouble</strong></dd>
   * </dl>
   *
   * @param valueDouble
   * 	is binded to <code>${valueDouble}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneDouble(Double valueDouble) {
    // build where condition
    String[] args={(valueDouble==null?"":String.valueOf(valueDouble))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueDoubleType=${valueDoubleType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueDoubleType}</dt><dd>is binded to method's parameter <strong>valueDoubleType</strong></dd>
   * </dl>
   *
   * @param valueDoubleType
   * 	is binded to <code>${valueDoubleType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneDoubleType(double valueDoubleType) {
    // build where condition
    String[] args={String.valueOf(valueDoubleType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_double_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueEnumType=${valueEnumType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueEnumType}</dt><dd>is binded to method's parameter <strong>valueEnumType</strong></dd>
   * </dl>
   *
   * @param valueEnumType
   * 	is binded to <code>${valueEnumType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneEnumType(EnumType valueEnumType) {
    // build where condition
    String[] args={(valueEnumType==null?"":valueEnumType.toString())};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_enum_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_enum_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueFloat=${valueFloat}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueFloat}</dt><dd>is binded to method's parameter <strong>valueFloat</strong></dd>
   * </dl>
   *
   * @param valueFloat
   * 	is binded to <code>${valueFloat}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneFloat(Float valueFloat) {
    // build where condition
    String[] args={(valueFloat==null?"":String.valueOf(valueFloat))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueFloatType=${valueFloatType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueFloatType}</dt><dd>is binded to method's parameter <strong>valueFloatType</strong></dd>
   * </dl>
   *
   * @param valueFloatType
   * 	is binded to <code>${valueFloatType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneFloatType(float valueFloatType) {
    // build where condition
    String[] args={String.valueOf(valueFloatType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_float_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueInt=${valueInt}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueInt}</dt><dd>is binded to method's parameter <strong>valueInt</strong></dd>
   * </dl>
   *
   * @param valueInt
   * 	is binded to <code>${valueInt}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneInt(Integer valueInt) {
    // build where condition
    String[] args={(valueInt==null?"":String.valueOf(valueInt))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueIntType=${valueIntType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueIntType}</dt><dd>is binded to method's parameter <strong>valueIntType</strong></dd>
   * </dl>
   *
   * @param valueIntType
   * 	is binded to <code>${valueIntType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneIntType(int valueIntType) {
    // build where condition
    String[] args={String.valueOf(valueIntType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_int_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongList=${valueLongList}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueLongList}</dt><dd>is binded to method's parameter <strong>valueLongList</strong></dd>
   * </dl>
   *
   * @param valueLongList
   * 	is binded to <code>${valueLongList}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneListLong(LinkedList<Long> valueLongList) {
    // build where condition
    String[] args={(valueLongList==null?"":new String(serializer4(valueLongList),StandardCharsets.UTF_8))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_list='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_list=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLocale=${valueLocale}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueLocale}</dt><dd>is binded to method's parameter <strong>valueLocale</strong></dd>
   * </dl>
   *
   * @param valueLocale
   * 	is binded to <code>${valueLocale}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneLocale(Calendar valueLocale) {
    // build where condition
    String[] args={(valueLocale==null?"":CalendarUtils.write(valueLocale))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_locale='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_locale=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLong=${valueLong}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueLong}</dt><dd>is binded to method's parameter <strong>valueLong</strong></dd>
   * </dl>
   *
   * @param valueLong
   * 	is binded to <code>${valueLong}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneLong(Long valueLong) {
    // build where condition
    String[] args={(valueLong==null?"":String.valueOf(valueLong))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueLongType=${valueLongType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueLongType}</dt><dd>is binded to method's parameter <strong>valueLongType</strong></dd>
   * </dl>
   *
   * @param valueLongType
   * 	is binded to <code>${valueLongType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneLongType(long valueLongType) {
    // build where condition
    String[] args={String.valueOf(valueLongType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_long_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueShort=${valueShort}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueShort}</dt><dd>is binded to method's parameter <strong>valueShort</strong></dd>
   * </dl>
   *
   * @param valueShort
   * 	is binded to <code>${valueShort}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneShort(Short valueShort) {
    // build where condition
    String[] args={(valueShort==null?"":String.valueOf((int)valueShort))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueShortType=${valueShortType}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueShortType}</dt><dd>is binded to method's parameter <strong>valueShortType</strong></dd>
   * </dl>
   *
   * @param valueShortType
   * 	is binded to <code>${valueShortType}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneShortType(short valueShortType) {
    // build where condition
    String[] args={String.valueOf((int)valueShortType)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short_type='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_short_type=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueString=${valueString}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueString}</dt><dd>is binded to method's parameter <strong>valueString</strong></dd>
   * </dl>
   *
   * @param valueString
   * 	is binded to <code>${valueString}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneString(String valueString) {
    // build where condition
    String[] args={(valueString==null?"":valueString)};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_string='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_string=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueTime=${valueTime}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueTime}</dt><dd>is binded to method's parameter <strong>valueTime</strong></dd>
   * </dl>
   *
   * @param valueTime
   * 	is binded to <code>${valueTime}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneTime(Time valueTime) {
    // build where condition
    String[] args={(valueTime==null?"":TimeUtils.write(valueTime))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueTimeZone=${valueTimeZone}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueTimeZone}</dt><dd>is binded to method's parameter <strong>valueTimeZone</strong></dd>
   * </dl>
   *
   * @param valueTimeZone
   * 	is binded to <code>${valueTimeZone}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneTimeZone(TimeZone valueTimeZone) {
    // build where condition
    String[] args={(valueTimeZone==null?"":TimeZoneUtils.write(valueTimeZone))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time_zone='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_time_zone=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE valueUrl=${valueUrl}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is associated to bean's property <strong>valueBoolType</strong></dd>
   * 	<dt>value_bool</dt><dd>is associated to bean's property <strong>valueBool</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is associated to bean's property <strong>valueByteType</strong></dd>
   * 	<dt>value_byte</dt><dd>is associated to bean's property <strong>valueByte</strong></dd>
   * 	<dt>value_short_type</dt><dd>is associated to bean's property <strong>valueShortType</strong></dd>
   * 	<dt>value_short</dt><dd>is associated to bean's property <strong>valueShort</strong></dd>
   * 	<dt>value_int_type</dt><dd>is associated to bean's property <strong>valueIntType</strong></dd>
   * 	<dt>value_int</dt><dd>is associated to bean's property <strong>valueInt</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * 	<dt>value_char_type</dt><dd>is associated to bean's property <strong>valueCharType</strong></dd>
   * 	<dt>value_char</dt><dd>is associated to bean's property <strong>valueChar</strong></dd>
   * 	<dt>value_float_type</dt><dd>is associated to bean's property <strong>valueFloatType</strong></dd>
   * 	<dt>value_float</dt><dd>is associated to bean's property <strong>valueFloat</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is associated to bean's property <strong>valueBigInteger</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is associated to bean's property <strong>valueBigDecimal</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is associated to bean's property <strong>valueEnumType</strong></dd>
   * 	<dt>value_long_type</dt><dd>is associated to bean's property <strong>valueLongType</strong></dd>
   * 	<dt>value_long</dt><dd>is associated to bean's property <strong>valueLong</strong></dd>
   * 	<dt>value_double_type</dt><dd>is associated to bean's property <strong>valueDoubleType</strong></dd>
   * 	<dt>value_double</dt><dd>is associated to bean's property <strong>valueDouble</strong></dd>
   * 	<dt>value_locale</dt><dd>is associated to bean's property <strong>valueLocale</strong></dd>
   * 	<dt>value_calendar</dt><dd>is associated to bean's property <strong>valueCalendar</strong></dd>
   * 	<dt>value_date</dt><dd>is associated to bean's property <strong>valueDate</strong></dd>
   * 	<dt>value_url</dt><dd>is associated to bean's property <strong>valueUrl</strong></dd>
   * 	<dt>value_time</dt><dd>is associated to bean's property <strong>valueTime</strong></dd>
   * 	<dt>value_currency</dt><dd>is associated to bean's property <strong>valueCurrency</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is associated to bean's property <strong>valueTimeZone</strong></dd>
   * 	<dt>value_time_list</dt><dd>is associated to bean's property <strong>valueTimeList</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is associated to bean's property <strong>valueStrinList</strong></dd>
   * 	<dt>value_long_list</dt><dd>is associated to bean's property <strong>valueLongList</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is associated to bean's property <strong>valueByteArray</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is associated to bean's property <strong>valueLongTypeArray</strong></dd>
   * 	<dt>value_long_array</dt><dd>is associated to bean's property <strong>valueLongArray</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is associated to bean's property <strong>valueBeanArray</strong></dd>
   * 	<dt>value_string_array</dt><dd>is associated to bean's property <strong>valueStringArray</strong></dd>
   * 	<dt>value_char_list</dt><dd>is associated to bean's property <strong>valueCharList</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is associated to bean's property <strong>valueCharTypeArray</strong></dd>
   * 	<dt>value_char_array</dt><dd>is associated to bean's property <strong>valueCharArray</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is associated to bean's property <strong>valueMapStringBean</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is associated to bean's property <strong>valueLinkedMapStringBean</strong></dd>
   * 	<dt>value_set_string</dt><dd>is associated to bean's property <strong>valueSetString</strong></dd>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${valueUrl}</dt><dd>is binded to method's parameter <strong>valueUrl</strong></dd>
   * </dl>
   *
   * @param valueUrl
   * 	is binded to <code>${valueUrl}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOneURL(URL valueUrl) {
    // build where condition
    String[] args={(valueUrl==null?"":UrlUtils.write(valueUrl))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_url='%s'",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type, value_bool, value_byte_type, value_byte, value_short_type, value_short, value_int_type, value_int, value_string, value_char_type, value_char, value_float_type, value_float, value_big_integer, value_big_decimal, value_enum_type, value_long_type, value_long, value_double_type, value_double, value_locale, value_calendar, value_date, value_url, value_time, value_currency, value_time_zone, value_time_list, value_strin_list, value_long_list, value_byte_array, value_long_type_array, value_long_array, value_bean_array, value_string_array, value_char_list, value_char_type_array, value_char_array, value_map_string_bean, value_linked_map_string_bean, value_set_string, id FROM bean WHERE value_url=?", args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("value_bool_type");
        int index1=cursor.getColumnIndex("value_bool");
        int index2=cursor.getColumnIndex("value_byte_type");
        int index3=cursor.getColumnIndex("value_byte");
        int index4=cursor.getColumnIndex("value_short_type");
        int index5=cursor.getColumnIndex("value_short");
        int index6=cursor.getColumnIndex("value_int_type");
        int index7=cursor.getColumnIndex("value_int");
        int index8=cursor.getColumnIndex("value_string");
        int index9=cursor.getColumnIndex("value_char_type");
        int index10=cursor.getColumnIndex("value_char");
        int index11=cursor.getColumnIndex("value_float_type");
        int index12=cursor.getColumnIndex("value_float");
        int index13=cursor.getColumnIndex("value_big_integer");
        int index14=cursor.getColumnIndex("value_big_decimal");
        int index15=cursor.getColumnIndex("value_enum_type");
        int index16=cursor.getColumnIndex("value_long_type");
        int index17=cursor.getColumnIndex("value_long");
        int index18=cursor.getColumnIndex("value_double_type");
        int index19=cursor.getColumnIndex("value_double");
        int index20=cursor.getColumnIndex("value_locale");
        int index21=cursor.getColumnIndex("value_calendar");
        int index22=cursor.getColumnIndex("value_date");
        int index23=cursor.getColumnIndex("value_url");
        int index24=cursor.getColumnIndex("value_time");
        int index25=cursor.getColumnIndex("value_currency");
        int index26=cursor.getColumnIndex("value_time_zone");
        int index27=cursor.getColumnIndex("value_time_list");
        int index28=cursor.getColumnIndex("value_strin_list");
        int index29=cursor.getColumnIndex("value_long_list");
        int index30=cursor.getColumnIndex("value_byte_array");
        int index31=cursor.getColumnIndex("value_long_type_array");
        int index32=cursor.getColumnIndex("value_long_array");
        int index33=cursor.getColumnIndex("value_bean_array");
        int index34=cursor.getColumnIndex("value_string_array");
        int index35=cursor.getColumnIndex("value_char_list");
        int index36=cursor.getColumnIndex("value_char_type_array");
        int index37=cursor.getColumnIndex("value_char_array");
        int index38=cursor.getColumnIndex("value_map_string_bean");
        int index39=cursor.getColumnIndex("value_linked_map_string_bean");
        int index40=cursor.getColumnIndex("value_set_string");
        int index41=cursor.getColumnIndex("id");

        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.valueBoolType=cursor.getInt(index0)==0?false:true; }
        if (!cursor.isNull(index1)) { resultBean.valueBool=cursor.getInt(index1)==0?false:true; }
        if (!cursor.isNull(index2)) { resultBean.valueByteType=(byte)cursor.getInt(index2); }
        if (!cursor.isNull(index3)) { resultBean.valueByte=(byte)cursor.getInt(index3); }
        if (!cursor.isNull(index4)) { resultBean.valueShortType=cursor.getShort(index4); }
        if (!cursor.isNull(index5)) { resultBean.valueShort=cursor.getShort(index5); }
        if (!cursor.isNull(index6)) { resultBean.valueIntType=cursor.getInt(index6); }
        if (!cursor.isNull(index7)) { resultBean.valueInt=cursor.getInt(index7); }
        if (!cursor.isNull(index8)) { resultBean.valueString=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.valueCharType=(char)cursor.getInt(index9); }
        if (!cursor.isNull(index10)) { resultBean.valueChar=(char)cursor.getInt(index10); }
        if (!cursor.isNull(index11)) { resultBean.valueFloatType=cursor.getFloat(index11); }
        if (!cursor.isNull(index12)) { resultBean.valueFloat=cursor.getFloat(index12); }
        if (!cursor.isNull(index13)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index13)); }
        if (!cursor.isNull(index14)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index14)); }
        if (!cursor.isNull(index15)) { resultBean.valueEnumType=EnumType.valueOf(cursor.getString(index15)); }
        if (!cursor.isNull(index16)) { resultBean.valueLongType=cursor.getLong(index16); }
        if (!cursor.isNull(index17)) { resultBean.valueLong=cursor.getLong(index17); }
        if (!cursor.isNull(index18)) { resultBean.valueDoubleType=cursor.getDouble(index18); }
        if (!cursor.isNull(index19)) { resultBean.valueDouble=cursor.getDouble(index19); }
        if (!cursor.isNull(index20)) { resultBean.valueLocale=LocaleUtils.read(cursor.getString(index20)); }
        if (!cursor.isNull(index21)) { resultBean.valueCalendar=CalendarUtils.read(cursor.getString(index21)); }
        if (!cursor.isNull(index22)) { resultBean.valueDate=DateUtils.read(cursor.getString(index22)); }
        if (!cursor.isNull(index23)) { resultBean.valueUrl=UrlUtils.read(cursor.getString(index23)); }
        if (!cursor.isNull(index24)) { resultBean.valueTime=TimeUtils.read(cursor.getString(index24)); }
        if (!cursor.isNull(index25)) { resultBean.valueCurrency=CurrencyUtils.read(cursor.getString(index25)); }
        if (!cursor.isNull(index26)) { resultBean.valueTimeZone=TimeZoneUtils.read(cursor.getString(index26)); }
        if (!cursor.isNull(index27)) { resultBean.valueTimeList=BeanTable.parseValueTimeList(cursor.getBlob(index27)); }
        if (!cursor.isNull(index28)) { resultBean.valueStrinList=BeanTable.parseValueStrinList(cursor.getBlob(index28)); }
        if (!cursor.isNull(index29)) { resultBean.valueLongList=BeanTable.parseValueLongList(cursor.getBlob(index29)); }
        if (!cursor.isNull(index30)) { resultBean.valueByteArray=BeanTable.parseValueByteArray(cursor.getBlob(index30)); }
        if (!cursor.isNull(index31)) { resultBean.valueLongTypeArray=BeanTable.parseValueLongTypeArray(cursor.getBlob(index31)); }
        if (!cursor.isNull(index32)) { resultBean.valueLongArray=BeanTable.parseValueLongArray(cursor.getBlob(index32)); }
        if (!cursor.isNull(index33)) { resultBean.valueBeanArray=BeanTable.parseValueBeanArray(cursor.getBlob(index33)); }
        if (!cursor.isNull(index34)) { resultBean.valueStringArray=BeanTable.parseValueStringArray(cursor.getBlob(index34)); }
        if (!cursor.isNull(index35)) { resultBean.valueCharList=BeanTable.parseValueCharList(cursor.getBlob(index35)); }
        if (!cursor.isNull(index36)) { resultBean.valueCharTypeArray=BeanTable.parseValueCharTypeArray(cursor.getBlob(index36)); }
        if (!cursor.isNull(index37)) { resultBean.valueCharArray=BeanTable.parseValueCharArray(cursor.getBlob(index37)); }
        if (!cursor.isNull(index38)) { resultBean.valueMapStringBean=BeanTable.parseValueMapStringBean(cursor.getBlob(index38)); }
        if (!cursor.isNull(index39)) { resultBean.valueLinkedMapStringBean=BeanTable.parseValueLinkedMapStringBean(cursor.getBlob(index39)); }
        if (!cursor.isNull(index40)) { resultBean.valueSetString=BeanTable.parseValueSetString(cursor.getBlob(index40)); }
        if (!cursor.isNull(index41)) { resultBean.id=cursor.getLong(index41); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Boolean selectValueBool() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Boolean result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getInt(0)==0?false:true;
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_bool_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public boolean selectValueBoolType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_bool_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_bool_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      boolean result=false;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return false; }
        result=cursor.getInt(0)==0?false:true;
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_byte FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_byte</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Byte selectValueByte() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_byte FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_byte FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Byte result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=(byte)cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_byte_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_byte_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public byte selectValueByteType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_byte_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_byte_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      byte result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=(byte)cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_char FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_char</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Character selectValueChar() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_char FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_char FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Character result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=(char)cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_char_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_char_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public char selectValueCharType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_char_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_char_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      char result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=(char)cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_double FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_double</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Double selectValueDouble() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_double FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_double FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Double result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getDouble(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_double_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_double_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public double selectValueDoubleType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_double_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_double_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      double result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getDouble(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_float FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_float</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Float selectValueFloat() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_float FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_float FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Float result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getFloat(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_float_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_float_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public float selectValueFloatType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_float_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_float_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      float result=0f;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0f; }
        result=cursor.getFloat(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_int FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_int</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Integer selectValueInt() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_int FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_int FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Integer result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_int_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_int_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public int selectValueIntType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_int_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_int_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      int result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getInt(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_long FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_long</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Long selectValueLong() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_long FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_long FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Long result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getLong(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_long_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_long_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public long selectValueLongType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_long_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_long_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      long result=0L;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0L; }
        result=cursor.getLong(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_short FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_short</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public Short selectValueShort() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_short FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_short FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      Short result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getShort(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_short_type FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_short_type</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public short selectValueShortType() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_short_type FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_short_type FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      short result=0;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return 0; }
        result=cursor.getShort(0);
      }
      return result;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT value_string FROM bean</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>value_string</dt><dd>no bean's property is associated</dd>
   * </dl>
   *
   * @return single value extracted with query.
   */
  @Override
  public String selectValueString() {
    // build where condition
    String[] args={};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT value_string FROM bean",(Object[])args));
    try (Cursor cursor = database().rawQuery("SELECT value_string FROM bean", args)) {
      Logger.info("Rows found: %s",cursor.getCount());
      String result=null;

      if (cursor.moveToFirst()) {

        if (cursor.isNull(0)) { return null; }
        result=cursor.getString(0);
      }
      return result;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET value_bool_type=${value.valueBoolType}, value_bool=${value.valueBool}, value_byte_type=${value.valueByteType}, value_byte=${value.valueByte}, value_short_type=${value.valueShortType}, value_short=${value.valueShort}, value_int_type=${value.valueIntType}, value_int=${value.valueInt}, value_string=${value.valueString}, value_char_type=${value.valueCharType}, value_char=${value.valueChar}, value_float_type=${value.valueFloatType}, value_float=${value.valueFloat}, value_big_integer=${value.valueBigInteger}, value_big_decimal=${value.valueBigDecimal}, value_enum_type=${value.valueEnumType}, value_long_type=${value.valueLongType}, value_long=${value.valueLong}, value_double_type=${value.valueDoubleType}, value_double=${value.valueDouble}, value_locale=${value.valueLocale}, value_calendar=${value.valueCalendar}, value_date=${value.valueDate}, value_url=${value.valueUrl}, value_time=${value.valueTime}, value_currency=${value.valueCurrency}, value_time_zone=${value.valueTimeZone}, value_time_list=${value.valueTimeList}, value_strin_list=${value.valueStrinList}, value_long_list=${value.valueLongList}, value_byte_array=${value.valueByteArray}, value_long_type_array=${value.valueLongTypeArray}, value_long_array=${value.valueLongArray}, value_bean_array=${value.valueBeanArray}, value_string_array=${value.valueStringArray}, value_char_list=${value.valueCharList}, value_char_type_array=${value.valueCharTypeArray}, value_char_array=${value.valueCharArray}, value_map_string_bean=${value.valueMapStringBean}, value_linked_map_string_bean=${value.valueLinkedMapStringBean}, value_set_string=${value.valueSetString} WHERE id=${value.id}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>value_bool_type</dt><dd>is mapped to <strong>${value.valueBoolType}</strong></dd>
   * 	<dt>value_bool</dt><dd>is mapped to <strong>${value.valueBool}</strong></dd>
   * 	<dt>value_byte_type</dt><dd>is mapped to <strong>${value.valueByteType}</strong></dd>
   * 	<dt>value_byte</dt><dd>is mapped to <strong>${value.valueByte}</strong></dd>
   * 	<dt>value_short_type</dt><dd>is mapped to <strong>${value.valueShortType}</strong></dd>
   * 	<dt>value_short</dt><dd>is mapped to <strong>${value.valueShort}</strong></dd>
   * 	<dt>value_int_type</dt><dd>is mapped to <strong>${value.valueIntType}</strong></dd>
   * 	<dt>value_int</dt><dd>is mapped to <strong>${value.valueInt}</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>${value.valueString}</strong></dd>
   * 	<dt>value_char_type</dt><dd>is mapped to <strong>${value.valueCharType}</strong></dd>
   * 	<dt>value_char</dt><dd>is mapped to <strong>${value.valueChar}</strong></dd>
   * 	<dt>value_float_type</dt><dd>is mapped to <strong>${value.valueFloatType}</strong></dd>
   * 	<dt>value_float</dt><dd>is mapped to <strong>${value.valueFloat}</strong></dd>
   * 	<dt>value_big_integer</dt><dd>is mapped to <strong>${value.valueBigInteger}</strong></dd>
   * 	<dt>value_big_decimal</dt><dd>is mapped to <strong>${value.valueBigDecimal}</strong></dd>
   * 	<dt>value_enum_type</dt><dd>is mapped to <strong>${value.valueEnumType}</strong></dd>
   * 	<dt>value_long_type</dt><dd>is mapped to <strong>${value.valueLongType}</strong></dd>
   * 	<dt>value_long</dt><dd>is mapped to <strong>${value.valueLong}</strong></dd>
   * 	<dt>value_double_type</dt><dd>is mapped to <strong>${value.valueDoubleType}</strong></dd>
   * 	<dt>value_double</dt><dd>is mapped to <strong>${value.valueDouble}</strong></dd>
   * 	<dt>value_locale</dt><dd>is mapped to <strong>${value.valueLocale}</strong></dd>
   * 	<dt>value_calendar</dt><dd>is mapped to <strong>${value.valueCalendar}</strong></dd>
   * 	<dt>value_date</dt><dd>is mapped to <strong>${value.valueDate}</strong></dd>
   * 	<dt>value_url</dt><dd>is mapped to <strong>${value.valueUrl}</strong></dd>
   * 	<dt>value_time</dt><dd>is mapped to <strong>${value.valueTime}</strong></dd>
   * 	<dt>value_currency</dt><dd>is mapped to <strong>${value.valueCurrency}</strong></dd>
   * 	<dt>value_time_zone</dt><dd>is mapped to <strong>${value.valueTimeZone}</strong></dd>
   * 	<dt>value_time_list</dt><dd>is mapped to <strong>${value.valueTimeList}</strong></dd>
   * 	<dt>value_strin_list</dt><dd>is mapped to <strong>${value.valueStrinList}</strong></dd>
   * 	<dt>value_long_list</dt><dd>is mapped to <strong>${value.valueLongList}</strong></dd>
   * 	<dt>value_byte_array</dt><dd>is mapped to <strong>${value.valueByteArray}</strong></dd>
   * 	<dt>value_long_type_array</dt><dd>is mapped to <strong>${value.valueLongTypeArray}</strong></dd>
   * 	<dt>value_long_array</dt><dd>is mapped to <strong>${value.valueLongArray}</strong></dd>
   * 	<dt>value_bean_array</dt><dd>is mapped to <strong>${value.valueBeanArray}</strong></dd>
   * 	<dt>value_string_array</dt><dd>is mapped to <strong>${value.valueStringArray}</strong></dd>
   * 	<dt>value_char_list</dt><dd>is mapped to <strong>${value.valueCharList}</strong></dd>
   * 	<dt>value_char_type_array</dt><dd>is mapped to <strong>${value.valueCharTypeArray}</strong></dd>
   * 	<dt>value_char_array</dt><dd>is mapped to <strong>${value.valueCharArray}</strong></dd>
   * 	<dt>value_map_string_bean</dt><dd>is mapped to <strong>${value.valueMapStringBean}</strong></dd>
   * 	<dt>value_linked_map_string_bean</dt><dd>is mapped to <strong>${value.valueLinkedMapStringBean}</strong></dd>
   * 	<dt>value_set_string</dt><dd>is mapped to <strong>${value.valueSetString}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${value.id}</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as ${value}
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value_bool_type", value.valueBoolType);

    if (value.valueBool!=null) {
      contentValues.put("value_bool", value.valueBool);
    } else {
      contentValues.putNull("value_bool");
    }

    contentValues.put("value_byte_type", value.valueByteType);

    if (value.valueByte!=null) {
      contentValues.put("value_byte", value.valueByte);
    } else {
      contentValues.putNull("value_byte");
    }

    contentValues.put("value_short_type", (int)value.valueShortType);

    if (value.valueShort!=null) {
      contentValues.put("value_short", (int)value.valueShort);
    } else {
      contentValues.putNull("value_short");
    }

    contentValues.put("value_int_type", value.valueIntType);

    if (value.valueInt!=null) {
      contentValues.put("value_int", value.valueInt);
    } else {
      contentValues.putNull("value_int");
    }

    if (value.valueString!=null) {
      contentValues.put("value_string", value.valueString);
    } else {
      contentValues.putNull("value_string");
    }

    contentValues.put("value_char_type", (int)value.valueCharType);

    if (value.valueChar!=null) {
      contentValues.put("value_char", (int)value.valueChar);
    } else {
      contentValues.putNull("value_char");
    }

    contentValues.put("value_float_type", value.valueFloatType);

    if (value.valueFloat!=null) {
      contentValues.put("value_float", value.valueFloat);
    } else {
      contentValues.putNull("value_float");
    }

    if (value.valueBigInteger!=null) {
      contentValues.put("value_big_integer", value.valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    if (value.valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", value.valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    if (value.valueEnumType!=null) {
      contentValues.put("value_enum_type", value.valueEnumType.toString());
    } else {
      contentValues.putNull("value_enum_type");
    }

    contentValues.put("value_long_type", value.valueLongType);

    if (value.valueLong!=null) {
      contentValues.put("value_long", value.valueLong);
    } else {
      contentValues.putNull("value_long");
    }

    contentValues.put("value_double_type", value.valueDoubleType);

    if (value.valueDouble!=null) {
      contentValues.put("value_double", value.valueDouble);
    } else {
      contentValues.putNull("value_double");
    }

    if (value.valueLocale!=null) {
      contentValues.put("value_locale", LocaleUtils.write(value.valueLocale));
    } else {
      contentValues.putNull("value_locale");
    }

    if (value.valueCalendar!=null) {
      contentValues.put("value_calendar", CalendarUtils.write(value.valueCalendar));
    } else {
      contentValues.putNull("value_calendar");
    }

    if (value.valueDate!=null) {
      contentValues.put("value_date", DateUtils.write(value.valueDate));
    } else {
      contentValues.putNull("value_date");
    }

    if (value.valueUrl!=null) {
      contentValues.put("value_url", UrlUtils.write(value.valueUrl));
    } else {
      contentValues.putNull("value_url");
    }

    if (value.valueTime!=null) {
      contentValues.put("value_time", TimeUtils.write(value.valueTime));
    } else {
      contentValues.putNull("value_time");
    }

    if (value.valueCurrency!=null) {
      contentValues.put("value_currency", CurrencyUtils.write(value.valueCurrency));
    } else {
      contentValues.putNull("value_currency");
    }

    if (value.valueTimeZone!=null) {
      contentValues.put("value_time_zone", TimeZoneUtils.write(value.valueTimeZone));
    } else {
      contentValues.putNull("value_time_zone");
    }

    if (value.valueTimeList!=null) {
      contentValues.put("value_time_list", BeanTable.serializeValueTimeList(value.valueTimeList));
    } else {
      contentValues.putNull("value_time_list");
    }

    if (value.valueStrinList!=null) {
      contentValues.put("value_strin_list", BeanTable.serializeValueStrinList(value.valueStrinList));
    } else {
      contentValues.putNull("value_strin_list");
    }

    if (value.valueLongList!=null) {
      contentValues.put("value_long_list", BeanTable.serializeValueLongList(value.valueLongList));
    } else {
      contentValues.putNull("value_long_list");
    }

    if (value.valueByteArray!=null) {
      contentValues.put("value_byte_array", BeanTable.serializeValueByteArray(value.valueByteArray));
    } else {
      contentValues.putNull("value_byte_array");
    }

    if (value.valueLongTypeArray!=null) {
      contentValues.put("value_long_type_array", BeanTable.serializeValueLongTypeArray(value.valueLongTypeArray));
    } else {
      contentValues.putNull("value_long_type_array");
    }

    if (value.valueLongArray!=null) {
      contentValues.put("value_long_array", BeanTable.serializeValueLongArray(value.valueLongArray));
    } else {
      contentValues.putNull("value_long_array");
    }

    if (value.valueBeanArray!=null) {
      contentValues.put("value_bean_array", BeanTable.serializeValueBeanArray(value.valueBeanArray));
    } else {
      contentValues.putNull("value_bean_array");
    }

    if (value.valueStringArray!=null) {
      contentValues.put("value_string_array", BeanTable.serializeValueStringArray(value.valueStringArray));
    } else {
      contentValues.putNull("value_string_array");
    }

    if (value.valueCharList!=null) {
      contentValues.put("value_char_list", BeanTable.serializeValueCharList(value.valueCharList));
    } else {
      contentValues.putNull("value_char_list");
    }

    if (value.valueCharTypeArray!=null) {
      contentValues.put("value_char_type_array", BeanTable.serializeValueCharTypeArray(value.valueCharTypeArray));
    } else {
      contentValues.putNull("value_char_type_array");
    }

    if (value.valueCharArray!=null) {
      contentValues.put("value_char_array", BeanTable.serializeValueCharArray(value.valueCharArray));
    } else {
      contentValues.putNull("value_char_array");
    }

    if (value.valueMapStringBean!=null) {
      contentValues.put("value_map_string_bean", BeanTable.serializeValueMapStringBean(value.valueMapStringBean));
    } else {
      contentValues.putNull("value_map_string_bean");
    }

    if (value.valueLinkedMapStringBean!=null) {
      contentValues.put("value_linked_map_string_bean", BeanTable.serializeValueLinkedMapStringBean(value.valueLinkedMapStringBean));
    } else {
      contentValues.putNull("value_linked_map_string_bean");
    }

    if (value.valueSetString!=null) {
      contentValues.put("value_set_string", BeanTable.serializeValueSetString(value.valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    String[] whereConditionsArray={String.valueOf(value.id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET value_bool_type='"+StringUtils.checkSize(contentValues.get("value_bool_type"))+"', value_bool='"+StringUtils.checkSize(contentValues.get("value_bool"))+"', value_byte_type='"+StringUtils.checkSize(contentValues.get("value_byte_type"))+"', value_byte='"+StringUtils.checkSize(contentValues.get("value_byte"))+"', value_short_type='"+StringUtils.checkSize(contentValues.get("value_short_type"))+"', value_short='"+StringUtils.checkSize(contentValues.get("value_short"))+"', value_int_type='"+StringUtils.checkSize(contentValues.get("value_int_type"))+"', value_int='"+StringUtils.checkSize(contentValues.get("value_int"))+"', value_string='"+StringUtils.checkSize(contentValues.get("value_string"))+"', value_char_type='"+StringUtils.checkSize(contentValues.get("value_char_type"))+"', value_char='"+StringUtils.checkSize(contentValues.get("value_char"))+"', value_float_type='"+StringUtils.checkSize(contentValues.get("value_float_type"))+"', value_float='"+StringUtils.checkSize(contentValues.get("value_float"))+"', value_big_integer='"+StringUtils.checkSize(contentValues.get("value_big_integer"))+"', value_big_decimal='"+StringUtils.checkSize(contentValues.get("value_big_decimal"))+"', value_enum_type='"+StringUtils.checkSize(contentValues.get("value_enum_type"))+"', value_long_type='"+StringUtils.checkSize(contentValues.get("value_long_type"))+"', value_long='"+StringUtils.checkSize(contentValues.get("value_long"))+"', value_double_type='"+StringUtils.checkSize(contentValues.get("value_double_type"))+"', value_double='"+StringUtils.checkSize(contentValues.get("value_double"))+"', value_locale='"+StringUtils.checkSize(contentValues.get("value_locale"))+"', value_calendar='"+StringUtils.checkSize(contentValues.get("value_calendar"))+"', value_date='"+StringUtils.checkSize(contentValues.get("value_date"))+"', value_url='"+StringUtils.checkSize(contentValues.get("value_url"))+"', value_time='"+StringUtils.checkSize(contentValues.get("value_time"))+"', value_currency='"+StringUtils.checkSize(contentValues.get("value_currency"))+"', value_time_zone='"+StringUtils.checkSize(contentValues.get("value_time_zone"))+"', value_time_list='"+StringUtils.checkSize(contentValues.get("value_time_list"))+"', value_strin_list='"+StringUtils.checkSize(contentValues.get("value_strin_list"))+"', value_long_list='"+StringUtils.checkSize(contentValues.get("value_long_list"))+"', value_byte_array='"+StringUtils.checkSize(contentValues.get("value_byte_array"))+"', value_long_type_array='"+StringUtils.checkSize(contentValues.get("value_long_type_array"))+"', value_long_array='"+StringUtils.checkSize(contentValues.get("value_long_array"))+"', value_bean_array='"+StringUtils.checkSize(contentValues.get("value_bean_array"))+"', value_string_array='"+StringUtils.checkSize(contentValues.get("value_string_array"))+"', value_char_list='"+StringUtils.checkSize(contentValues.get("value_char_list"))+"', value_char_type_array='"+StringUtils.checkSize(contentValues.get("value_char_type_array"))+"', value_char_array='"+StringUtils.checkSize(contentValues.get("value_char_array"))+"', value_map_string_bean='"+StringUtils.checkSize(contentValues.get("value_map_string_bean"))+"', value_linked_map_string_bean='"+StringUtils.checkSize(contentValues.get("value_linked_map_string_bean"))+"', value_set_string='"+StringUtils.checkSize(contentValues.get("value_set_string"))+"' WHERE id='%s'", (Object[]) whereConditionsArray));
    int result = database().update("bean", contentValues, "UPDATE bean SET value_bool_type='"+StringUtils.checkSize(contentValues.get("value_bool_type"))+"', value_bool='"+StringUtils.checkSize(contentValues.get("value_bool"))+"', value_byte_type='"+StringUtils.checkSize(contentValues.get("value_byte_type"))+"', value_byte='"+StringUtils.checkSize(contentValues.get("value_byte"))+"', value_short_type='"+StringUtils.checkSize(contentValues.get("value_short_type"))+"', value_short='"+StringUtils.checkSize(contentValues.get("value_short"))+"', value_int_type='"+StringUtils.checkSize(contentValues.get("value_int_type"))+"', value_int='"+StringUtils.checkSize(contentValues.get("value_int"))+"', value_string='"+StringUtils.checkSize(contentValues.get("value_string"))+"', value_char_type='"+StringUtils.checkSize(contentValues.get("value_char_type"))+"', value_char='"+StringUtils.checkSize(contentValues.get("value_char"))+"', value_float_type='"+StringUtils.checkSize(contentValues.get("value_float_type"))+"', value_float='"+StringUtils.checkSize(contentValues.get("value_float"))+"', value_big_integer='"+StringUtils.checkSize(contentValues.get("value_big_integer"))+"', value_big_decimal='"+StringUtils.checkSize(contentValues.get("value_big_decimal"))+"', value_enum_type='"+StringUtils.checkSize(contentValues.get("value_enum_type"))+"', value_long_type='"+StringUtils.checkSize(contentValues.get("value_long_type"))+"', value_long='"+StringUtils.checkSize(contentValues.get("value_long"))+"', value_double_type='"+StringUtils.checkSize(contentValues.get("value_double_type"))+"', value_double='"+StringUtils.checkSize(contentValues.get("value_double"))+"', value_locale='"+StringUtils.checkSize(contentValues.get("value_locale"))+"', value_calendar='"+StringUtils.checkSize(contentValues.get("value_calendar"))+"', value_date='"+StringUtils.checkSize(contentValues.get("value_date"))+"', value_url='"+StringUtils.checkSize(contentValues.get("value_url"))+"', value_time='"+StringUtils.checkSize(contentValues.get("value_time"))+"', value_currency='"+StringUtils.checkSize(contentValues.get("value_currency"))+"', value_time_zone='"+StringUtils.checkSize(contentValues.get("value_time_zone"))+"', value_time_list='"+StringUtils.checkSize(contentValues.get("value_time_list"))+"', value_strin_list='"+StringUtils.checkSize(contentValues.get("value_strin_list"))+"', value_long_list='"+StringUtils.checkSize(contentValues.get("value_long_list"))+"', value_byte_array='"+StringUtils.checkSize(contentValues.get("value_byte_array"))+"', value_long_type_array='"+StringUtils.checkSize(contentValues.get("value_long_type_array"))+"', value_long_array='"+StringUtils.checkSize(contentValues.get("value_long_array"))+"', value_bean_array='"+StringUtils.checkSize(contentValues.get("value_bean_array"))+"', value_string_array='"+StringUtils.checkSize(contentValues.get("value_string_array"))+"', value_char_list='"+StringUtils.checkSize(contentValues.get("value_char_list"))+"', value_char_type_array='"+StringUtils.checkSize(contentValues.get("value_char_type_array"))+"', value_char_array='"+StringUtils.checkSize(contentValues.get("value_char_array"))+"', value_map_string_bean='"+StringUtils.checkSize(contentValues.get("value_map_string_bean"))+"', value_linked_map_string_bean='"+StringUtils.checkSize(contentValues.get("value_linked_map_string_bean"))+"', value_set_string='"+StringUtils.checkSize(contentValues.get("value_set_string"))+"' WHERE id='%s'", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimal}</dt><dd>is mapped to method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBigDecimal
   * 	is used as where parameter <strong>${valueBigDecimal}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, BigDecimal valueBigDecimal) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueBigDecimal==null?"":valueBigDecimal.toPlainString())};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueBigDecimal=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_big_decimal=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueBigDecimal=${valueBigDecimal}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBigDecimal}</dt><dd>is mapped to method's parameter <strong>valueBigDecimal</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBigDecimal
   * 	is used as where parameter <strong>${valueBigDecimal}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, BigInteger valueBigDecimal) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueBigDecimal==null?"":valueBigDecimal.toString())};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueBigDecimal=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_big_decimal=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueBoolType=${valueBoolType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBoolType}</dt><dd>is mapped to method's parameter <strong>valueBoolType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBoolType
   * 	is used as where parameter <strong>${valueBoolType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, boolean valueBoolType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(valueBoolType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueBoolType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_bool_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueBool=${valueBool}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBool}</dt><dd>is mapped to method's parameter <strong>valueBool</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBool
   * 	is used as where parameter <strong>${valueBool}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, Boolean valueBool) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueBool==null?"":String.valueOf(valueBool))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueBool=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_bool=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET valueSetString=${valueSetString} WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>value_set_string</dt><dd>is binded to query's parameter <strong>${valueSetString}</strong> and method's parameter <strong>valueSetString</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   * @param valueSetString
   * 	is used as updated field <strong>valueSetString</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, Set<String> valueSetString) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    if (valueSetString!=null) {
      contentValues.put("value_set_string", serializer5(valueSetString));
    } else {
      contentValues.putNull("value_set_string");
    }

    String[] whereConditionsArray={String.valueOf(id)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET valueSetString='"+StringUtils.checkSize(contentValues.get("value_set_string"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "id=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueBeanArray=${valueBeanArray}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueBeanArray}</dt><dd>is mapped to method's parameter <strong>valueBeanArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBeanArray
   * 	is used as where parameter <strong>${valueBeanArray}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneArrayBean(long id, Bean[] valueBeanArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueBeanArray==null?"":new String(serializer1(valueBeanArray),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueBeanArray=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_bean_array=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueLongArray=${valueLongArray}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongArray}</dt><dd>is mapped to method's parameter <strong>valueLongArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueLongArray
   * 	is used as where parameter <strong>${valueLongArray}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneArrayLong(long id, Long[] valueLongArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueLongArray==null?"":new String(serializer2(valueLongArray),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueLongArray=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_long_array=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueLongTypeArray=${valueLongTypeArray}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongTypeArray}</dt><dd>is mapped to method's parameter <strong>valueLongTypeArray</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueLongTypeArray
   * 	is used as where parameter <strong>${valueLongTypeArray}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneArrayLongType(long id, long[] valueLongTypeArray) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueLongTypeArray==null?"":new String(serializer3(valueLongTypeArray),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueLongTypeArray=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_long_type_array=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueByte=${valueByte}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueByte}</dt><dd>is mapped to method's parameter <strong>valueByte</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueByte
   * 	is used as where parameter <strong>${valueByte}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneByte(long id, Byte valueByte) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueByte==null?"":String.valueOf(valueByte))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueByte=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_byte=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueByteType=${valueByteType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueByteType}</dt><dd>is mapped to method's parameter <strong>valueByteType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueByteType
   * 	is used as where parameter <strong>${valueByteType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneByteType(long id, boolean valueByteType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(valueByteType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueByteType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_byte_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueCalendar=${valueCalendar}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueCalendar}</dt><dd>is mapped to method's parameter <strong>valueCalendar</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueCalendar
   * 	is used as where parameter <strong>${valueCalendar}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneCalendar(long id, Calendar valueCalendar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueCalendar==null?"":CalendarUtils.write(valueCalendar))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueCalendar=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_calendar=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueCharType=${valueChar}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueChar}</dt><dd>is mapped to method's parameter <strong>valueChar</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueChar
   * 	is used as where parameter <strong>${valueChar}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneChar(long id, Character valueChar) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueChar==null?"":String.valueOf((int)valueChar))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueCharType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_char_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueCharType=${valueCharType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueCharType}</dt><dd>is mapped to method's parameter <strong>valueCharType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueCharType
   * 	is used as where parameter <strong>${valueCharType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneCharType(long id, char valueCharType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf((int)valueCharType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueCharType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_char_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueCurrency=${valueCurrency}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueCurrency}</dt><dd>is mapped to method's parameter <strong>valueCurrency</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueCurrency
   * 	is used as where parameter <strong>${valueCurrency}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneCurrency(long id, Currency valueCurrency) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueCurrency==null?"":CurrencyUtils.write(valueCurrency))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueCurrency=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_currency=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueDate=${valueDate}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueDate}</dt><dd>is mapped to method's parameter <strong>valueDate</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueDate
   * 	is used as where parameter <strong>${valueDate}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneDate(long id, Date valueDate) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueDate==null?"":DateUtils.write(valueDate))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueDate=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_date=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueDouble=${valueDouble}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueDouble}</dt><dd>is mapped to method's parameter <strong>valueDouble</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueDouble
   * 	is used as where parameter <strong>${valueDouble}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneDouble(long id, Double valueDouble) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueDouble==null?"":String.valueOf(valueDouble))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueDouble=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_double=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueDoubleType=${valueDoubleType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueDoubleType}</dt><dd>is mapped to method's parameter <strong>valueDoubleType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueDoubleType
   * 	is used as where parameter <strong>${valueDoubleType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneDoubleType(long id, double valueDoubleType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(valueDoubleType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueDoubleType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_double_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueEnumType=${valueEnumType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueEnumType}</dt><dd>is mapped to method's parameter <strong>valueEnumType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueEnumType
   * 	is used as where parameter <strong>${valueEnumType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneEnumType(long id, EnumType valueEnumType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueEnumType==null?"":valueEnumType.toString())};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueEnumType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_enum_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueFloat=${valueFloat}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueFloat}</dt><dd>is mapped to method's parameter <strong>valueFloat</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueFloat
   * 	is used as where parameter <strong>${valueFloat}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneFloat(long id, Float valueFloat) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueFloat==null?"":String.valueOf(valueFloat))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueFloat=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_float=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueFloatType=${valueFloatType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueFloatType}</dt><dd>is mapped to method's parameter <strong>valueFloatType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueFloatType
   * 	is used as where parameter <strong>${valueFloatType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneFloatType(long id, float valueFloatType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(valueFloatType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueFloatType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_float_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueInt=${valueInt}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueInt}</dt><dd>is mapped to method's parameter <strong>valueInt</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueInt
   * 	is used as where parameter <strong>${valueInt}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneInt(long id, Integer valueInt) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueInt==null?"":String.valueOf(valueInt))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueInt=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_int=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueIntType=${valueIntType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueIntType}</dt><dd>is mapped to method's parameter <strong>valueIntType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueIntType
   * 	is used as where parameter <strong>${valueIntType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneIntType(long id, int valueIntType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(valueIntType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueIntType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_int_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueLongList=${valueLongList}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongList}</dt><dd>is mapped to method's parameter <strong>valueLongList</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueLongList
   * 	is used as where parameter <strong>${valueLongList}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneListLong(long id, LinkedList<Long> valueLongList) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueLongList==null?"":new String(serializer4(valueLongList),StandardCharsets.UTF_8))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueLongList=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_long_list=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueLocale=${valueLocale}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLocale}</dt><dd>is mapped to method's parameter <strong>valueLocale</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueLocale
   * 	is used as where parameter <strong>${valueLocale}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneLocale(long id, Locale valueLocale) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueLocale==null?"":LocaleUtils.write(valueLocale))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueLocale=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_locale=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueLong=${valueLong}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLong}</dt><dd>is mapped to method's parameter <strong>valueLong</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueLong
   * 	is used as where parameter <strong>${valueLong}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneLong(long id, Long valueLong) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueLong==null?"":String.valueOf(valueLong))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueLong=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_long=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueLongType=${valueLongType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueLongType}</dt><dd>is mapped to method's parameter <strong>valueLongType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueLongType
   * 	is used as where parameter <strong>${valueLongType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneLongType(long id, long valueLongType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf(valueLongType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueLongType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_long_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueShort=${valueShort}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueShort}</dt><dd>is mapped to method's parameter <strong>valueShort</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueShort
   * 	is used as where parameter <strong>${valueShort}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneShort(long id, Short valueShort) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueShort==null?"":String.valueOf((int)valueShort))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueShort=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_short=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueShortType=${valueShortType}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueShortType}</dt><dd>is mapped to method's parameter <strong>valueShortType</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueShortType
   * 	is used as where parameter <strong>${valueShortType}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneShortType(long id, short valueShortType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={String.valueOf((int)valueShortType)};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueShortType=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_short_type=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueString=${valueString}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueString}</dt><dd>is mapped to method's parameter <strong>valueString</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueString
   * 	is used as where parameter <strong>${valueString}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneString(long id, Double valueString) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueString==null?"":String.valueOf(valueString))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueString=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_string=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueTime=${valueTime}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueTime}</dt><dd>is mapped to method's parameter <strong>valueTime</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueTime
   * 	is used as where parameter <strong>${valueTime}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneTime(long id, Time valueTime) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueTime==null?"":TimeUtils.write(valueTime))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueTime=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_time=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueTimeZone=${valueTimeZone}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueTimeZone}</dt><dd>is mapped to method's parameter <strong>valueTimeZone</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueTimeZone
   * 	is used as where parameter <strong>${valueTimeZone}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneTimeZone(long id, TimeZone valueTimeZone) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueTimeZone==null?"":TimeZoneUtils.write(valueTimeZone))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueTimeZone=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_time_zone=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean SET id=${id} WHERE valueUrl=${valueUrl}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${valueUrl}</dt><dd>is mapped to method's parameter <strong>valueUrl</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueUrl
   * 	is used as where parameter <strong>${valueUrl}</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOneURL(long id, URL valueUrl) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("id", id);

    String[] whereConditionsArray={(valueUrl==null?"":UrlUtils.write(valueUrl))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean SET id='"+StringUtils.checkSize(contentValues.get("id"))+"' WHERE valueUrl=%s", (Object[])whereConditionsArray));
    int result = database().update("bean", contentValues, "value_url=?", whereConditionsArray);
    return result;
  }

  /**
   * write
   */
  private byte[] serializer1(Bean[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        Bean item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Bean[] parser1(byte[] input) {
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
   * write
   */
  private byte[] serializer2(Long[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Long[] parser2(byte[] input) {
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
   * write
   */
  private byte[] serializer4(LinkedList<Long> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.size();
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private LinkedList<Long> parser4(byte[] input) {
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
   * write
   */
  private byte[] serializer3(long[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        int n=value.length;
        long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private long[] parser3(byte[] input) {
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
   * write
   */
  private byte[] serializer5(Set<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      jacksonSerializer.writeStartObject();
      if (value!=null)  {
        // write wrapper tag
        jacksonSerializer.writeFieldName("element");
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
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Set<String> parser5(byte[] input) {
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
}
