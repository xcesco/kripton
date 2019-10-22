package sqlite.kripton62;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;
import com.abubusoft.kripton.android.sqlite.OnReadCursorListener;
import com.abubusoft.kripton.common.BigDecimalUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean2</code>, based on interface <code>BeanDao2</code>
 * </p>
 *
 *  @see Bean2
 *  @see BeanDao2
 *  @see Bean2Table
 */
public class BeanDao2Impl extends Dao implements BeanDao2 {
  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL1 = "SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL2 = "SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE id = ?";

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL3 = "SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE id = ?";

  /**
   * SQL definition for method selectList
   */
  private static final String SELECT_LIST_SQL4 = "SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE id = ?";

  private static SupportSQLiteStatement updateOnePreparedStatement0;

  private static SupportSQLiteStatement insertPreparedStatement1;

  private static SupportSQLiteStatement insertPreparedStatement2;

  /**
   * SQL definition for method selectOne
   */
  private static final String SELECT_ONE_SQL5 = "SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE value=?";

  private static SupportSQLiteStatement deletePreparedStatement3;

  private static SupportSQLiteStatement updateOnePreparedStatement4;

  public BeanDao2Impl(BindBean2DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean2}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean2 selectOne() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean2 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_bean_set");
        int index3=_cursor.getColumnIndex("value_big_decimal_set");
        int index4=_cursor.getColumnIndex("value_byte_set");
        int index5=_cursor.getColumnIndex("value_character_set");
        int index6=_cursor.getColumnIndex("value_double_set");
        int index7=_cursor.getColumnIndex("value_enum_type_set");
        int index8=_cursor.getColumnIndex("value_float_set");
        int index9=_cursor.getColumnIndex("value_integer_set");
        int index10=_cursor.getColumnIndex("value_short_set");
        int index11=_cursor.getColumnIndex("value_string_set");

        resultBean=new Bean2();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.setValueBeanSet(Bean2Table.parseValueBeanSet(_cursor.getBlob(index2))); }
        if (!_cursor.isNull(index3)) { resultBean.setValueBigDecimalSet(Bean2Table.parseValueBigDecimalSet(_cursor.getBlob(index3))); }
        if (!_cursor.isNull(index4)) { resultBean.setValueByteSet(Bean2Table.parseValueByteSet(_cursor.getBlob(index4))); }
        if (!_cursor.isNull(index5)) { resultBean.setValueCharacterSet(Bean2Table.parseValueCharacterSet(_cursor.getBlob(index5))); }
        if (!_cursor.isNull(index6)) { resultBean.setValueDoubleSet(Bean2Table.parseValueDoubleSet(_cursor.getBlob(index6))); }
        if (!_cursor.isNull(index7)) { resultBean.setValueEnumTypeSet(Bean2Table.parseValueEnumTypeSet(_cursor.getBlob(index7))); }
        if (!_cursor.isNull(index8)) { resultBean.setValueFloatSet(Bean2Table.parseValueFloatSet(_cursor.getBlob(index8))); }
        if (!_cursor.isNull(index9)) { resultBean.setValueIntegerSet(Bean2Table.parseValueIntegerSet(_cursor.getBlob(index9))); }
        if (!_cursor.isNull(index10)) { resultBean.setValueShortSet(Bean2Table.parseValueShortSet(_cursor.getBlob(index10))); }
        if (!_cursor.isNull(index11)) { resultBean.setValueStringSet(Bean2Table.parseValueStringSet(_cursor.getBlob(index11))); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE id = ${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean2}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the Bean2 listener
   */
  @Override
  public void selectOne(int id, OnReadBeanListener<Bean2> listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListenerHelper - BEGIN
      Bean2 resultBean=new Bean2();
      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_bean_set");
        int index3=_cursor.getColumnIndex("value_big_decimal_set");
        int index4=_cursor.getColumnIndex("value_byte_set");
        int index5=_cursor.getColumnIndex("value_character_set");
        int index6=_cursor.getColumnIndex("value_double_set");
        int index7=_cursor.getColumnIndex("value_enum_type_set");
        int index8=_cursor.getColumnIndex("value_float_set");
        int index9=_cursor.getColumnIndex("value_integer_set");
        int index10=_cursor.getColumnIndex("value_short_set");
        int index11=_cursor.getColumnIndex("value_string_set");

        int rowCount=_cursor.getCount();
        do
         {
          // reset mapping
          // id does not need reset (it will be taken from db)
          resultBean.value=null;
          resultBean.setValueBeanSet(null);
          resultBean.setValueBigDecimalSet(null);
          resultBean.setValueByteSet(null);
          resultBean.setValueCharacterSet(null);
          resultBean.setValueDoubleSet(null);
          resultBean.setValueEnumTypeSet(null);
          resultBean.setValueFloatSet(null);
          resultBean.setValueIntegerSet(null);
          resultBean.setValueShortSet(null);
          resultBean.setValueStringSet(null);

          // generate mapping
          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.setValueBeanSet(Bean2Table.parseValueBeanSet(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.setValueBigDecimalSet(Bean2Table.parseValueBigDecimalSet(_cursor.getBlob(index3))); }
          if (!_cursor.isNull(index4)) { resultBean.setValueByteSet(Bean2Table.parseValueByteSet(_cursor.getBlob(index4))); }
          if (!_cursor.isNull(index5)) { resultBean.setValueCharacterSet(Bean2Table.parseValueCharacterSet(_cursor.getBlob(index5))); }
          if (!_cursor.isNull(index6)) { resultBean.setValueDoubleSet(Bean2Table.parseValueDoubleSet(_cursor.getBlob(index6))); }
          if (!_cursor.isNull(index7)) { resultBean.setValueEnumTypeSet(Bean2Table.parseValueEnumTypeSet(_cursor.getBlob(index7))); }
          if (!_cursor.isNull(index8)) { resultBean.setValueFloatSet(Bean2Table.parseValueFloatSet(_cursor.getBlob(index8))); }
          if (!_cursor.isNull(index9)) { resultBean.setValueIntegerSet(Bean2Table.parseValueIntegerSet(_cursor.getBlob(index9))); }
          if (!_cursor.isNull(index10)) { resultBean.setValueShortSet(Bean2Table.parseValueShortSet(_cursor.getBlob(index10))); }
          if (!_cursor.isNull(index11)) { resultBean.setValueStringSet(Bean2Table.parseValueStringSet(_cursor.getBlob(index11))); }

          listener.onRead(resultBean, _cursor.getPosition(), rowCount);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectBeanListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE id = ${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean2}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @param listener
   * 	is the cursor listener
   */
  @Override
  public void selectOne(long id, OnReadCursorListener listener) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL3;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectRawListenerHelper - BEGIN

      if (_cursor.moveToFirst()) {

        do
         {
          listener.onRead(_cursor);
        } while (_cursor.moveToNext());
      }
    }
    // Specialized part - SelectRawListenerHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE id = ${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean2}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Bean2> selectList(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_LIST_SQL4;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Bean2> resultList=new ArrayList<Bean2>(_cursor.getCount());
      Bean2 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_bean_set");
        int index3=_cursor.getColumnIndex("value_big_decimal_set");
        int index4=_cursor.getColumnIndex("value_byte_set");
        int index5=_cursor.getColumnIndex("value_character_set");
        int index6=_cursor.getColumnIndex("value_double_set");
        int index7=_cursor.getColumnIndex("value_enum_type_set");
        int index8=_cursor.getColumnIndex("value_float_set");
        int index9=_cursor.getColumnIndex("value_integer_set");
        int index10=_cursor.getColumnIndex("value_short_set");
        int index11=_cursor.getColumnIndex("value_string_set");

        do
         {
          resultBean=new Bean2();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.setValueBeanSet(Bean2Table.parseValueBeanSet(_cursor.getBlob(index2))); }
          if (!_cursor.isNull(index3)) { resultBean.setValueBigDecimalSet(Bean2Table.parseValueBigDecimalSet(_cursor.getBlob(index3))); }
          if (!_cursor.isNull(index4)) { resultBean.setValueByteSet(Bean2Table.parseValueByteSet(_cursor.getBlob(index4))); }
          if (!_cursor.isNull(index5)) { resultBean.setValueCharacterSet(Bean2Table.parseValueCharacterSet(_cursor.getBlob(index5))); }
          if (!_cursor.isNull(index6)) { resultBean.setValueDoubleSet(Bean2Table.parseValueDoubleSet(_cursor.getBlob(index6))); }
          if (!_cursor.isNull(index7)) { resultBean.setValueEnumTypeSet(Bean2Table.parseValueEnumTypeSet(_cursor.getBlob(index7))); }
          if (!_cursor.isNull(index8)) { resultBean.setValueFloatSet(Bean2Table.parseValueFloatSet(_cursor.getBlob(index8))); }
          if (!_cursor.isNull(index9)) { resultBean.setValueIntegerSet(Bean2Table.parseValueIntegerSet(_cursor.getBlob(index9))); }
          if (!_cursor.isNull(index10)) { resultBean.setValueShortSet(Bean2Table.parseValueShortSet(_cursor.getBlob(index10))); }
          if (!_cursor.isNull(index11)) { resultBean.setValueStringSet(Bean2Table.parseValueStringSet(_cursor.getBlob(index11))); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean2 SET value=:value, value_bean_set=:valueBeanSet, value_big_decimal_set=:valueBigDecimalSet, value_byte_set=:valueByteSet, value_character_set=:valueCharacterSet, value_double_set=:valueDoubleSet, value_enum_type_set=:valueEnumTypeSet, value_float_set=:valueFloatSet, value_integer_set=:valueIntegerSet, value_short_set=:valueShortSet, value_string_set=:valueStringSet WHERE id=${value.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>:value.value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is mapped to <strong>:value.valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is mapped to <strong>:value.valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is mapped to <strong>:value.valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is mapped to <strong>:value.valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is mapped to <strong>:value.valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is mapped to <strong>:value.valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is mapped to <strong>:value.valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is mapped to <strong>:value.valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is mapped to <strong>:value.valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is mapped to <strong>:value.valueStringSet</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:value.id</dt><dd>is mapped to method's parameter <strong>value.id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as <code>:value</code>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean2 value) {
    if (updateOnePreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean2 SET value=?, value_bean_set=?, value_big_decimal_set=?, value_byte_set=?, value_character_set=?, value_double_set=?, value_enum_type_set=?, value_float_set=?, value_integer_set=?, value_short_set=?, value_string_set=? WHERE id=?";
      updateOnePreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement0);
    _contentValues.put("value", value.value);
    _contentValues.put("value_bean_set", Bean2Table.serializeValueBeanSet(value.getValueBeanSet()));
    _contentValues.put("value_big_decimal_set", Bean2Table.serializeValueBigDecimalSet(value.getValueBigDecimalSet()));
    _contentValues.put("value_byte_set", Bean2Table.serializeValueByteSet(value.getValueByteSet()));
    _contentValues.put("value_character_set", Bean2Table.serializeValueCharacterSet(value.getValueCharacterSet()));
    _contentValues.put("value_double_set", Bean2Table.serializeValueDoubleSet(value.getValueDoubleSet()));
    _contentValues.put("value_enum_type_set", Bean2Table.serializeValueEnumTypeSet(value.getValueEnumTypeSet()));
    _contentValues.put("value_float_set", Bean2Table.serializeValueFloatSet(value.getValueFloatSet()));
    _contentValues.put("value_integer_set", Bean2Table.serializeValueIntegerSet(value.getValueIntegerSet()));
    _contentValues.put("value_short_set", Bean2Table.serializeValueShortSet(value.getValueShortSet()));
    _contentValues.put("value_string_set", Bean2Table.serializeValueStringSet(value.getValueStringSet()));

    _contentValues.addWhereArgs(String.valueOf(value.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean2 SET value=:value, value_bean_set=:value_bean_set, value_big_decimal_set=:value_big_decimal_set, value_byte_set=:value_byte_set, value_character_set=:value_character_set, value_double_set=:value_double_set, value_enum_type_set=:value_enum_type_set, value_float_set=:value_float_set, value_integer_set=:value_integer_set, value_short_set=:value_short_set, value_string_set=:value_string_set WHERE id=?");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateOnePreparedStatement0, _contentValues);
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean2 (value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set) VALUES (:bean.value, :bean.valueBeanSet, :bean.valueBigDecimalSet, :bean.valueByteSet, :bean.valueCharacterSet, :bean.valueDoubleSet, :bean.valueEnumTypeSet, :bean.valueFloatSet, :bean.valueIntegerSet, :bean.valueShortSet, :bean.valueStringSet)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is mapped to <strong>:bean.value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is mapped to <strong>:bean.valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is mapped to <strong>:bean.valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is mapped to <strong>:bean.valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is mapped to <strong>:bean.valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is mapped to <strong>:bean.valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is mapped to <strong>:bean.valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is mapped to <strong>:bean.valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is mapped to <strong>:bean.valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is mapped to <strong>:bean.valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is mapped to <strong>:bean.valueStringSet</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean2 bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean2 (value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      insertPreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement1);
    _contentValues.put("value", bean.value);
    _contentValues.put("value_bean_set", Bean2Table.serializeValueBeanSet(bean.getValueBeanSet()));
    _contentValues.put("value_big_decimal_set", Bean2Table.serializeValueBigDecimalSet(bean.getValueBigDecimalSet()));
    _contentValues.put("value_byte_set", Bean2Table.serializeValueByteSet(bean.getValueByteSet()));
    _contentValues.put("value_character_set", Bean2Table.serializeValueCharacterSet(bean.getValueCharacterSet()));
    _contentValues.put("value_double_set", Bean2Table.serializeValueDoubleSet(bean.getValueDoubleSet()));
    _contentValues.put("value_enum_type_set", Bean2Table.serializeValueEnumTypeSet(bean.getValueEnumTypeSet()));
    _contentValues.put("value_float_set", Bean2Table.serializeValueFloatSet(bean.getValueFloatSet()));
    _contentValues.put("value_integer_set", Bean2Table.serializeValueIntegerSet(bean.getValueIntegerSet()));
    _contentValues.put("value_short_set", Bean2Table.serializeValueShortSet(bean.getValueShortSet()));
    _contentValues.put("value_string_set", Bean2Table.serializeValueStringSet(bean.getValueStringSet()));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO bean2 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement1, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean2 (value_big_decimal_set) VALUES (:valueBigDecimalSet)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>valueBigDecimalSet</dt><dd>is binded to query's parameter <strong>:valueBigDecimalSet</strong> and method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param valueBigDecimalSet
   * 	is binded to column value <strong>value_big_decimal_set</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(HashSet<BigDecimal> valueBigDecimalSet) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean2 (value_big_decimal_set) VALUES (?)";
      insertPreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement2);

    _contentValues.put("value_big_decimal_set", serializer1(valueBigDecimalSet));

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO bean2 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement2, _contentValues);
    return result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, value, value_bean_set, value_big_decimal_set, value_byte_set, value_character_set, value_double_set, value_enum_type_set, value_float_set, value_integer_set, value_short_set, value_string_set FROM bean2 WHERE value=${valueBigDecimalSet}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Bean2}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>value</dt><dd>is associated to bean's property <strong>value</strong></dd>
   * 	<dt>value_bean_set</dt><dd>is associated to bean's property <strong>valueBeanSet</strong></dd>
   * 	<dt>value_big_decimal_set</dt><dd>is associated to bean's property <strong>valueBigDecimalSet</strong></dd>
   * 	<dt>value_byte_set</dt><dd>is associated to bean's property <strong>valueByteSet</strong></dd>
   * 	<dt>value_character_set</dt><dd>is associated to bean's property <strong>valueCharacterSet</strong></dd>
   * 	<dt>value_double_set</dt><dd>is associated to bean's property <strong>valueDoubleSet</strong></dd>
   * 	<dt>value_enum_type_set</dt><dd>is associated to bean's property <strong>valueEnumTypeSet</strong></dd>
   * 	<dt>value_float_set</dt><dd>is associated to bean's property <strong>valueFloatSet</strong></dd>
   * 	<dt>value_integer_set</dt><dd>is associated to bean's property <strong>valueIntegerSet</strong></dd>
   * 	<dt>value_short_set</dt><dd>is associated to bean's property <strong>valueShortSet</strong></dd>
   * 	<dt>value_string_set</dt><dd>is associated to bean's property <strong>valueStringSet</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:valueBigDecimalSet</dt><dd>is binded to method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param valueBigDecimalSet
   * 	is binded to <code>:valueBigDecimalSet</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean2 selectOne(HashSet<BigDecimal> valueBigDecimalSet) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ONE_SQL5;
    // add where arguments
    _contentValues.addWhereArgs((valueBigDecimalSet==null?"":new String(serializer1(valueBigDecimalSet),StandardCharsets.UTF_8)));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      Bean2 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("value");
        int index2=_cursor.getColumnIndex("value_bean_set");
        int index3=_cursor.getColumnIndex("value_big_decimal_set");
        int index4=_cursor.getColumnIndex("value_byte_set");
        int index5=_cursor.getColumnIndex("value_character_set");
        int index6=_cursor.getColumnIndex("value_double_set");
        int index7=_cursor.getColumnIndex("value_enum_type_set");
        int index8=_cursor.getColumnIndex("value_float_set");
        int index9=_cursor.getColumnIndex("value_integer_set");
        int index10=_cursor.getColumnIndex("value_short_set");
        int index11=_cursor.getColumnIndex("value_string_set");

        resultBean=new Bean2();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.value=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.setValueBeanSet(Bean2Table.parseValueBeanSet(_cursor.getBlob(index2))); }
        if (!_cursor.isNull(index3)) { resultBean.setValueBigDecimalSet(Bean2Table.parseValueBigDecimalSet(_cursor.getBlob(index3))); }
        if (!_cursor.isNull(index4)) { resultBean.setValueByteSet(Bean2Table.parseValueByteSet(_cursor.getBlob(index4))); }
        if (!_cursor.isNull(index5)) { resultBean.setValueCharacterSet(Bean2Table.parseValueCharacterSet(_cursor.getBlob(index5))); }
        if (!_cursor.isNull(index6)) { resultBean.setValueDoubleSet(Bean2Table.parseValueDoubleSet(_cursor.getBlob(index6))); }
        if (!_cursor.isNull(index7)) { resultBean.setValueEnumTypeSet(Bean2Table.parseValueEnumTypeSet(_cursor.getBlob(index7))); }
        if (!_cursor.isNull(index8)) { resultBean.setValueFloatSet(Bean2Table.parseValueFloatSet(_cursor.getBlob(index8))); }
        if (!_cursor.isNull(index9)) { resultBean.setValueIntegerSet(Bean2Table.parseValueIntegerSet(_cursor.getBlob(index9))); }
        if (!_cursor.isNull(index10)) { resultBean.setValueShortSet(Bean2Table.parseValueShortSet(_cursor.getBlob(index10))); }
        if (!_cursor.isNull(index11)) { resultBean.setValueStringSet(Bean2Table.parseValueStringSet(_cursor.getBlob(index11))); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM bean2 WHERE value=:valueBigDecimalSet</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:valueBigDecimalSet</dt><dd>is mapped to method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param valueBigDecimalSet
   * 	is used as where parameter <strong>:valueBigDecimalSet</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashSet<BigDecimal> valueBigDecimalSet) {
    if (deletePreparedStatement3==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM bean2 WHERE value=?";
      deletePreparedStatement3 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deletePreparedStatement3);
    _contentValues.addWhereArgs((valueBigDecimalSet==null?"":new String(serializer1(valueBigDecimalSet),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM bean2 WHERE value=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(deletePreparedStatement3, _contentValues);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean2 SET id=:id WHERE value=:valueBigDecimalSet</pre>
   *
   * <h2>Updated columns:</h2>
   * <ul>
   * 	<li>id</li>
   * </ul>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:valueBigDecimalSet</dt><dd>is mapped to method's parameter <strong>valueBigDecimalSet</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as updated field <strong>id</strong>
   * @param valueBigDecimalSet
   * 	is used as where parameter <strong>:valueBigDecimalSet</strong>
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(long id, HashSet<BigDecimal> valueBigDecimalSet) {
    if (updateOnePreparedStatement4==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean2 SET id=? WHERE value=?";
      updateOnePreparedStatement4 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updateOnePreparedStatement4);
    _contentValues.put("id", id);

    _contentValues.addWhereArgs((valueBigDecimalSet==null?"":new String(serializer1(valueBigDecimalSet),StandardCharsets.UTF_8)));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean2 SET id=:id WHERE value=?");

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseHelper.updateDelete(updateOnePreparedStatement4, _contentValues);
    return result;
  }

  /**
   * for param serializer1 serialization
   */
  private byte[] serializer1(HashSet<BigDecimal> value) {
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
        for (BigDecimal item: value) {
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(BigDecimalUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for param parser1 parsing
   */
  private HashSet<BigDecimal> parser1(byte[] input) {
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
      HashSet<BigDecimal> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashSet<BigDecimal> collection=new HashSet<>();
        BigDecimal item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=BigDecimalUtils.read(jacksonParser.getText());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  public static void clearCompiledStatements() {
    try {
      if (updateOnePreparedStatement0!=null) {
        updateOnePreparedStatement0.close();
        updateOnePreparedStatement0=null;
      }
      if (insertPreparedStatement1!=null) {
        insertPreparedStatement1.close();
        insertPreparedStatement1=null;
      }
      if (insertPreparedStatement2!=null) {
        insertPreparedStatement2.close();
        insertPreparedStatement2=null;
      }
      if (deletePreparedStatement3!=null) {
        deletePreparedStatement3.close();
        deletePreparedStatement3=null;
      }
      if (updateOnePreparedStatement4!=null) {
        updateOnePreparedStatement4.close();
        updateOnePreparedStatement4=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
