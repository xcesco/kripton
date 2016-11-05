package com.abubusoft.kripton.processor.kripton62;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean</code>, based on interface <code>BeanDao</code>
 * </p>
 *  @see Bean
 *  @see BeanDao
 *  @see Bean$Table
 */
public class BeanDaoImpl extends AbstractDao implements BeanDao {
  public BeanDaoImpl(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set]</pre>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_byte_set");
      int index2=cursor.getColumnIndex("value_short_set");
      int index3=cursor.getColumnIndex("value_integer_set");
      int index4=cursor.getColumnIndex("value_string_set");
      int index5=cursor.getColumnIndex("value_character_set");
      int index6=cursor.getColumnIndex("value_float_set");
      int index7=cursor.getColumnIndex("value_double_set");
      int index8=cursor.getColumnIndex("value_big_decimal_set");
      int index9=cursor.getColumnIndex("value_bean_set");
      int index10=cursor.getColumnIndex("value_enum_type_set");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.valueByteSet=ProcessorHelper.asCollection(new HashSet<java.lang.Byte>(), Byte.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.valueShortSet=ProcessorHelper.asCollection(new HashSet<java.lang.Short>(), Short.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueIntegerSet=ProcessorHelper.asCollection(new LinkedHashSet<java.lang.Integer>(), Integer.class, cursor.getBlob(index3)); }
      if (!cursor.isNull(index4)) { resultBean.valueStringSet=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index4)); }
      if (!cursor.isNull(index5)) { resultBean.valueCharacterSet=ProcessorHelper.asCollection(new HashSet<java.lang.Character>(), Character.class, cursor.getBlob(index5)); }
      if (!cursor.isNull(index6)) { resultBean.valueFloatSet=ProcessorHelper.asCollection(new HashSet<java.lang.Float>(), Float.class, cursor.getBlob(index6)); }
      if (!cursor.isNull(index7)) { resultBean.valueDoubleSet=ProcessorHelper.asCollection(new HashSet<java.lang.Double>(), Double.class, cursor.getBlob(index7)); }
      if (!cursor.isNull(index8)) { resultBean.valueBigDecimalSet=ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, cursor.getBlob(index8)); }
      if (!cursor.isNull(index9)) { resultBean.valueBeanSet=ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, cursor.getBlob(index9)); }
      if (!cursor.isNull(index10)) { resultBean.valueEnumTypeSet=ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, cursor.getBlob(index10)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set]</pre>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(int id, ReadBeanListener<Bean> listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean resultBean=new Bean();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value_byte_set");
        int index2=cursor.getColumnIndex("value_short_set");
        int index3=cursor.getColumnIndex("value_integer_set");
        int index4=cursor.getColumnIndex("value_string_set");
        int index5=cursor.getColumnIndex("value_character_set");
        int index6=cursor.getColumnIndex("value_float_set");
        int index7=cursor.getColumnIndex("value_double_set");
        int index8=cursor.getColumnIndex("value_big_decimal_set");
        int index9=cursor.getColumnIndex("value_bean_set");
        int index10=cursor.getColumnIndex("value_enum_type_set");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.valueByteSet=null;
          resultBean.valueShortSet=null;
          resultBean.valueIntegerSet=null;
          resultBean.valueStringSet=null;
          resultBean.valueCharacterSet=null;
          resultBean.valueFloatSet=null;
          resultBean.valueDoubleSet=null;
          resultBean.valueBigDecimalSet=null;
          resultBean.valueBeanSet=null;
          resultBean.valueEnumTypeSet=null;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.valueByteSet=ProcessorHelper.asCollection(new HashSet<java.lang.Byte>(), Byte.class, cursor.getBlob(index1)); }
          if (!cursor.isNull(index2)) { resultBean.valueShortSet=ProcessorHelper.asCollection(new HashSet<java.lang.Short>(), Short.class, cursor.getBlob(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueIntegerSet=ProcessorHelper.asCollection(new LinkedHashSet<java.lang.Integer>(), Integer.class, cursor.getBlob(index3)); }
          if (!cursor.isNull(index4)) { resultBean.valueStringSet=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index4)); }
          if (!cursor.isNull(index5)) { resultBean.valueCharacterSet=ProcessorHelper.asCollection(new HashSet<java.lang.Character>(), Character.class, cursor.getBlob(index5)); }
          if (!cursor.isNull(index6)) { resultBean.valueFloatSet=ProcessorHelper.asCollection(new HashSet<java.lang.Float>(), Float.class, cursor.getBlob(index6)); }
          if (!cursor.isNull(index7)) { resultBean.valueDoubleSet=ProcessorHelper.asCollection(new HashSet<java.lang.Double>(), Double.class, cursor.getBlob(index7)); }
          if (!cursor.isNull(index8)) { resultBean.valueBigDecimalSet=ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, cursor.getBlob(index8)); }
          if (!cursor.isNull(index9)) { resultBean.valueBeanSet=ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, cursor.getBlob(index9)); }
          if (!cursor.isNull(index10)) { resultBean.valueEnumTypeSet=ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, cursor.getBlob(index10)); }

          listener.onRead(resultBean, cursor.getPosition(), rowCount);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set]</pre>
   *
   * @param id
   * @param listener
   */
  @Override
  public void selectOne(long id, ReadCursorListener listener) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    try {
      if (cursor.moveToFirst()) {

        do
         {
          listener.onRead(cursor);
        } while (cursor.moveToNext());
      }
    } finally {
      if (cursor!=null)
       {
        cursor.close();
      }
    }
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set]</pre>
   *
   * @param id
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean> selectList(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean> resultList=new LinkedList<Bean>();
    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_byte_set");
      int index2=cursor.getColumnIndex("value_short_set");
      int index3=cursor.getColumnIndex("value_integer_set");
      int index4=cursor.getColumnIndex("value_string_set");
      int index5=cursor.getColumnIndex("value_character_set");
      int index6=cursor.getColumnIndex("value_float_set");
      int index7=cursor.getColumnIndex("value_double_set");
      int index8=cursor.getColumnIndex("value_big_decimal_set");
      int index9=cursor.getColumnIndex("value_bean_set");
      int index10=cursor.getColumnIndex("value_enum_type_set");

      do
       {
        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.valueByteSet=ProcessorHelper.asCollection(new HashSet<java.lang.Byte>(), Byte.class, cursor.getBlob(index1)); }
        if (!cursor.isNull(index2)) { resultBean.valueShortSet=ProcessorHelper.asCollection(new HashSet<java.lang.Short>(), Short.class, cursor.getBlob(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueIntegerSet=ProcessorHelper.asCollection(new LinkedHashSet<java.lang.Integer>(), Integer.class, cursor.getBlob(index3)); }
        if (!cursor.isNull(index4)) { resultBean.valueStringSet=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index4)); }
        if (!cursor.isNull(index5)) { resultBean.valueCharacterSet=ProcessorHelper.asCollection(new HashSet<java.lang.Character>(), Character.class, cursor.getBlob(index5)); }
        if (!cursor.isNull(index6)) { resultBean.valueFloatSet=ProcessorHelper.asCollection(new HashSet<java.lang.Float>(), Float.class, cursor.getBlob(index6)); }
        if (!cursor.isNull(index7)) { resultBean.valueDoubleSet=ProcessorHelper.asCollection(new HashSet<java.lang.Double>(), Double.class, cursor.getBlob(index7)); }
        if (!cursor.isNull(index8)) { resultBean.valueBigDecimalSet=ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, cursor.getBlob(index8)); }
        if (!cursor.isNull(index9)) { resultBean.valueBeanSet=ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, cursor.getBlob(index9)); }
        if (!cursor.isNull(index10)) { resultBean.valueEnumTypeSet=ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, cursor.getBlob(index10)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET value_byte_set=${value.valueByteSet}, value_short_set=${value.valueShortSet}, value_integer_set=${value.valueIntegerSet}, value_string_set=${value.valueStringSet}, value_character_set=${value.valueCharacterSet}, value_float_set=${value.valueFloatSet}, value_double_set=${value.valueDoubleSet}, value_big_decimal_set=${value.valueBigDecimalSet}, value_bean_set=${value.valueBeanSet}, value_enum_type_set=${value.valueEnumTypeSet} WHERE id=${value.id}</pre>
   *
   * @param value
   * 	used as updated field and in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(Bean value) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (value.valueByteSet!=null) {
      contentValues.put("value_byte_set", ProcessorHelper.asByteArray(value.valueByteSet));
    } else {
      contentValues.putNull("value_byte_set");
    }

    if (value.valueShortSet!=null) {
      contentValues.put("value_short_set", ProcessorHelper.asByteArray(value.valueShortSet));
    } else {
      contentValues.putNull("value_short_set");
    }

    if (value.valueIntegerSet!=null) {
      contentValues.put("value_integer_set", ProcessorHelper.asByteArray(value.valueIntegerSet));
    } else {
      contentValues.putNull("value_integer_set");
    }

    if (value.valueStringSet!=null) {
      contentValues.put("value_string_set", ProcessorHelper.asByteArray(value.valueStringSet));
    } else {
      contentValues.putNull("value_string_set");
    }

    if (value.valueCharacterSet!=null) {
      contentValues.put("value_character_set", ProcessorHelper.asByteArray(value.valueCharacterSet));
    } else {
      contentValues.putNull("value_character_set");
    }

    if (value.valueFloatSet!=null) {
      contentValues.put("value_float_set", ProcessorHelper.asByteArray(value.valueFloatSet));
    } else {
      contentValues.putNull("value_float_set");
    }

    if (value.valueDoubleSet!=null) {
      contentValues.put("value_double_set", ProcessorHelper.asByteArray(value.valueDoubleSet));
    } else {
      contentValues.putNull("value_double_set");
    }

    if (value.valueBigDecimalSet!=null) {
      contentValues.put("value_big_decimal_set", ProcessorHelper.asByteArray(value.valueBigDecimalSet));
    } else {
      contentValues.putNull("value_big_decimal_set");
    }

    if (value.valueBeanSet!=null) {
      contentValues.put("value_bean_set", ProcessorHelper.asByteArray(value.valueBeanSet));
    } else {
      contentValues.putNull("value_bean_set");
    }

    if (value.valueEnumTypeSet!=null) {
      contentValues.put("value_enum_type_set", ProcessorHelper.asByteArray(value.valueEnumTypeSet));
    } else {
      contentValues.putNull("value_enum_type_set");
    }

    String[] whereConditions={String.valueOf(value.id)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET value_byte_set='"+StringUtil.checkSize(contentValues.get("value_byte_set"))+"', value_short_set='"+StringUtil.checkSize(contentValues.get("value_short_set"))+"', value_integer_set='"+StringUtil.checkSize(contentValues.get("value_integer_set"))+"', value_string_set='"+StringUtil.checkSize(contentValues.get("value_string_set"))+"', value_character_set='"+StringUtil.checkSize(contentValues.get("value_character_set"))+"', value_float_set='"+StringUtil.checkSize(contentValues.get("value_float_set"))+"', value_double_set='"+StringUtil.checkSize(contentValues.get("value_double_set"))+"', value_big_decimal_set='"+StringUtil.checkSize(contentValues.get("value_big_decimal_set"))+"', value_bean_set='"+StringUtil.checkSize(contentValues.get("value_bean_set"))+"', value_enum_type_set='"+StringUtil.checkSize(contentValues.get("value_enum_type_set"))+"' WHERE id=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set) VALUES (${bean.valueByteSet}, ${bean.valueShortSet}, ${bean.valueIntegerSet}, ${bean.valueStringSet}, ${bean.valueCharacterSet}, ${bean.valueFloatSet}, ${bean.valueDoubleSet}, ${bean.valueBigDecimalSet}, ${bean.valueBeanSet}, ${bean.valueEnumTypeSet})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(Bean bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.valueByteSet!=null) {
      contentValues.put("value_byte_set", ProcessorHelper.asByteArray(bean.valueByteSet));
    } else {
      contentValues.putNull("value_byte_set");
    }

    if (bean.valueShortSet!=null) {
      contentValues.put("value_short_set", ProcessorHelper.asByteArray(bean.valueShortSet));
    } else {
      contentValues.putNull("value_short_set");
    }

    if (bean.valueIntegerSet!=null) {
      contentValues.put("value_integer_set", ProcessorHelper.asByteArray(bean.valueIntegerSet));
    } else {
      contentValues.putNull("value_integer_set");
    }

    if (bean.valueStringSet!=null) {
      contentValues.put("value_string_set", ProcessorHelper.asByteArray(bean.valueStringSet));
    } else {
      contentValues.putNull("value_string_set");
    }

    if (bean.valueCharacterSet!=null) {
      contentValues.put("value_character_set", ProcessorHelper.asByteArray(bean.valueCharacterSet));
    } else {
      contentValues.putNull("value_character_set");
    }

    if (bean.valueFloatSet!=null) {
      contentValues.put("value_float_set", ProcessorHelper.asByteArray(bean.valueFloatSet));
    } else {
      contentValues.putNull("value_float_set");
    }

    if (bean.valueDoubleSet!=null) {
      contentValues.put("value_double_set", ProcessorHelper.asByteArray(bean.valueDoubleSet));
    } else {
      contentValues.putNull("value_double_set");
    }

    if (bean.valueBigDecimalSet!=null) {
      contentValues.put("value_big_decimal_set", ProcessorHelper.asByteArray(bean.valueBigDecimalSet));
    } else {
      contentValues.putNull("value_big_decimal_set");
    }

    if (bean.valueBeanSet!=null) {
      contentValues.put("value_bean_set", ProcessorHelper.asByteArray(bean.valueBeanSet));
    } else {
      contentValues.putNull("value_bean_set");
    }

    if (bean.valueEnumTypeSet!=null) {
      contentValues.put("value_enum_type_set", ProcessorHelper.asByteArray(bean.valueEnumTypeSet));
    } else {
      contentValues.putNull("value_enum_type_set");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set) VALUES ('"+StringUtil.checkSize(contentValues.get("value_byte_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_short_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_integer_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_string_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_character_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_float_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_double_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_big_decimal_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_bean_set"))+"', '"+StringUtil.checkSize(contentValues.get("value_enum_type_set"))+"')"));
    long result = database().insert("bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_big_decimal_set) VALUES (${valueBigDecimalSet})</pre>
   *
   * @param valueBigDecimalSet
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(HashSet<BigDecimal> valueBigDecimalSet) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBigDecimalSet!=null) {
      contentValues.put("value_big_decimal_set", ProcessorHelper.asByteArray(valueBigDecimalSet));
    } else {
      contentValues.putNull("value_big_decimal_set");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_big_decimal_set) VALUES ('"+StringUtil.checkSize(contentValues.get("value_big_decimal_set"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE value=${valueBigDecimalSet}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimalSet]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set]</pre>
   *
   * @param valueBigDecimalSet
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(HashSet<BigDecimal> valueBigDecimalSet) {
    // build where condition
    String[] args={(valueBigDecimalSet==null?null:String.valueOf(ProcessorHelper.asByteArray(valueBigDecimalSet)))};

    Logger.info(StringUtil.formatSQL("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE value='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_byte_set, value_short_set, value_integer_set, value_string_set, value_character_set, value_float_set, value_double_set, value_big_decimal_set, value_bean_set, value_enum_type_set FROM bean WHERE value=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_byte_set");
      int index2=cursor.getColumnIndex("value_short_set");
      int index3=cursor.getColumnIndex("value_integer_set");
      int index4=cursor.getColumnIndex("value_string_set");
      int index5=cursor.getColumnIndex("value_character_set");
      int index6=cursor.getColumnIndex("value_float_set");
      int index7=cursor.getColumnIndex("value_double_set");
      int index8=cursor.getColumnIndex("value_big_decimal_set");
      int index9=cursor.getColumnIndex("value_bean_set");
      int index10=cursor.getColumnIndex("value_enum_type_set");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.valueByteSet=ProcessorHelper.asCollection(new HashSet<java.lang.Byte>(), Byte.class, cursor.getBlob(index1)); }
      if (!cursor.isNull(index2)) { resultBean.valueShortSet=ProcessorHelper.asCollection(new HashSet<java.lang.Short>(), Short.class, cursor.getBlob(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueIntegerSet=ProcessorHelper.asCollection(new LinkedHashSet<java.lang.Integer>(), Integer.class, cursor.getBlob(index3)); }
      if (!cursor.isNull(index4)) { resultBean.valueStringSet=ProcessorHelper.asCollection(new HashSet<String>(), String.class, cursor.getBlob(index4)); }
      if (!cursor.isNull(index5)) { resultBean.valueCharacterSet=ProcessorHelper.asCollection(new HashSet<java.lang.Character>(), Character.class, cursor.getBlob(index5)); }
      if (!cursor.isNull(index6)) { resultBean.valueFloatSet=ProcessorHelper.asCollection(new HashSet<java.lang.Float>(), Float.class, cursor.getBlob(index6)); }
      if (!cursor.isNull(index7)) { resultBean.valueDoubleSet=ProcessorHelper.asCollection(new HashSet<java.lang.Double>(), Double.class, cursor.getBlob(index7)); }
      if (!cursor.isNull(index8)) { resultBean.valueBigDecimalSet=ProcessorHelper.asCollection(new HashSet<BigDecimal>(), BigDecimal.class, cursor.getBlob(index8)); }
      if (!cursor.isNull(index9)) { resultBean.valueBeanSet=ProcessorHelper.asCollection(new LinkedHashSet<Bean>(), Bean.class, cursor.getBlob(index9)); }
      if (!cursor.isNull(index10)) { resultBean.valueEnumTypeSet=ProcessorHelper.asCollection(new HashSet<EnumType>(), EnumType.class, cursor.getBlob(index10)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE value=${valueBigDecimalSet}</pre>
   *
   * @param valueBigDecimalSet
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(HashSet<BigDecimal> valueBigDecimalSet) {
    String[] whereConditions={(valueBigDecimalSet==null?null:String.valueOf(ProcessorHelper.asByteArray(valueBigDecimalSet)))};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE value=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE value=${valueBigDecimalSet}</pre>
   *
   * @param valueBigDecimalSet
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(HashSet<BigDecimal> valueBigDecimalSet) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    String[] whereConditions={(valueBigDecimalSet==null?null:String.valueOf(ProcessorHelper.asByteArray(valueBigDecimalSet)))};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE value=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value=?", whereConditions);
    return result;
  }
}
