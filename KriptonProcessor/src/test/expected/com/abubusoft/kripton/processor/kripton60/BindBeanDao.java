package com.abubusoft.kripton.processor.kripton60;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.ReadBeanListener;
import com.abubusoft.kripton.android.sqlite.ReadCursorListener;
import com.abubusoft.kripton.common.StringUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean</code>, based on interface <code>BeanDao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton60.Bean
 *  @see com.abubusoft.kripton.processor.kripton60.BeanDao
 *  @see com.abubusoft.kripton.processor.kripton60.BeanTable
 */
public class BindBeanDao extends AbstractDao implements BeanDao {
  public BindBeanDao(BindBeanDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE 1=1</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_big_decimal, value_big_integer, value_bool_type]</pre>
   *
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE 1=1"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE 1=1", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_big_decimal");
      int index2=cursor.getColumnIndex("value_big_integer");
      int index3=cursor.getColumnIndex("value_bool_type");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index1)); }
      if (!cursor.isNull(index2)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueBoolType=cursor.getInt(index3)==0?false:true; }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimal, valueBigInteger, valueBoolType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_big_decimal, value_big_integer, value_bool_type]</pre>
   *
   * @param valueBigDecimal
   * @param valueBigInteger
   * @param valueBoolType
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean selectOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType) {
    // build where condition
    String[] args={(valueBigDecimal==null?null:valueBigDecimal.toPlainString()), (valueBigInteger==null?null:String.valueOf(valueBigInteger.toString())), String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal='%s' and value_big_integer='%s' and value_bool_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal=? and value_big_integer=? and value_bool_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_big_decimal");
      int index2=cursor.getColumnIndex("value_big_integer");
      int index3=cursor.getColumnIndex("value_bool_type");

      resultBean=new Bean();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index1)); }
      if (!cursor.isNull(index2)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index2)); }
      if (!cursor.isNull(index3)) { resultBean.valueBoolType=cursor.getInt(index3)==0?false:true; }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimal, valueBigInteger, valueBoolType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_big_decimal, value_big_integer, value_bool_type]</pre>
   *
   * @param valueBigDecimal
   * @param valueBigInteger
   * @param valueBoolType
   * @param listener
   */
  @Override
  public void selectOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType, ReadBeanListener<Bean> listener) {
    // build where condition
    String[] args={(valueBigDecimal==null?null:valueBigDecimal.toPlainString()), (valueBigInteger==null?null:String.valueOf(valueBigInteger.toString())), String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal='%s' and value_big_integer='%s' and value_bool_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal=? and value_big_integer=? and value_bool_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());
    Bean resultBean=new Bean();

    try {
      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("value_big_decimal");
        int index2=cursor.getColumnIndex("value_big_integer");
        int index3=cursor.getColumnIndex("value_bool_type");

        int rowCount=cursor.getCount();
        do
         {
          // reset mapping
          resultBean.id=0L;
          resultBean.valueBigDecimal=null;
          resultBean.valueBigInteger=null;
          resultBean.valueBoolType=false;

          // generate mapping
          if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
          if (!cursor.isNull(index1)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index1)); }
          if (!cursor.isNull(index2)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index2)); }
          if (!cursor.isNull(index3)) { resultBean.valueBoolType=cursor.getInt(index3)==0?false:true; }

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
   * <pre>SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimal, valueBigInteger, valueBoolType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_big_decimal, value_big_integer, value_bool_type]</pre>
   *
   * @param valueBigDecimal
   * @param valueBigInteger
   * @param valueBoolType
   * @param listener
   */
  @Override
  public void selectOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType, ReadCursorListener listener) {
    // build where condition
    String[] args={(valueBigDecimal==null?null:valueBigDecimal.toPlainString()), (valueBigInteger==null?null:String.valueOf(valueBigInteger.toString())), String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal='%s' and value_big_integer='%s' and value_bool_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal=? and value_big_integer=? and value_bool_type=?", args);
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
   * <pre>SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[valueBigDecimal, valueBigInteger, valueBoolType]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, value_big_decimal, value_big_integer, value_bool_type]</pre>
   *
   * @param valueBigDecimal
   * @param valueBigInteger
   * @param valueBoolType
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean> selectList(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType) {
    // build where condition
    String[] args={(valueBigDecimal==null?null:valueBigDecimal.toPlainString()), (valueBigInteger==null?null:String.valueOf(valueBigInteger.toString())), String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal='%s' and value_big_integer='%s' and value_bool_type='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, value_big_decimal, value_big_integer, value_bool_type FROM bean WHERE value_big_decimal=? and value_big_integer=? and value_bool_type=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean> resultList=new LinkedList<Bean>();
    Bean resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("value_big_decimal");
      int index2=cursor.getColumnIndex("value_big_integer");
      int index3=cursor.getColumnIndex("value_bool_type");

      do
       {
        resultBean=new Bean();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.valueBigDecimal=new BigDecimal(cursor.getString(index1)); }
        if (!cursor.isNull(index2)) { resultBean.valueBigInteger=new BigInteger(cursor.getString(index2)); }
        if (!cursor.isNull(index3)) { resultBean.valueBoolType=cursor.getInt(index3)==0?false:true; }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean SET  WHERE valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}</pre>
   *
   * @param valueBigDecimal
   * 	used in where condition
   * @param valueBigInteger
   * 	used in where condition
   * @param valueBoolType
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();


    String[] whereConditions={(valueBigDecimal==null?null:valueBigDecimal.toPlainString()), (valueBigInteger==null?null:String.valueOf(valueBigInteger.toString())), String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("UPDATE bean SET  WHERE valueBigDecimal=%s and valueBigInteger=%s and valueBoolType=%s"), (Object[])whereConditions);
    int result = database().update("bean", contentValues, "value_big_decimal=? and value_big_integer=? and value_bool_type=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_big_decimal, value_big_integer, value_bool_type) VALUES (${valueBigDecimal}, ${valueBigInteger}, ${valueBoolType})</pre>
   *
   * @param valueBigDecimal
   * 	used as updated field and in where condition
   * @param valueBigInteger
   * 	used as updated field and in where condition
   * @param valueBoolType
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    if (valueBigInteger!=null) {
      contentValues.put("value_big_integer", valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    contentValues.put("value_bool_type", valueBoolType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_big_decimal, value_big_integer, value_bool_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_big_decimal"))+"', '"+StringUtil.checkSize(contentValues.get("value_big_integer"))+"', '"+StringUtil.checkSize(contentValues.get("value_bool_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean (value_big_decimal, value_big_integer, value_bool_type) VALUES (${bean.valueBigDecimal}, ${bean.valueBigInteger}, ${bean.valueBoolType})</pre>
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

    if (bean.valueBigDecimal!=null) {
      contentValues.put("value_big_decimal", bean.valueBigDecimal.toPlainString());
    } else {
      contentValues.putNull("value_big_decimal");
    }

    if (bean.valueBigInteger!=null) {
      contentValues.put("value_big_integer", bean.valueBigInteger.toString());
    } else {
      contentValues.putNull("value_big_integer");
    }

    contentValues.put("value_bool_type", bean.valueBoolType);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean (value_big_decimal, value_big_integer, value_bool_type) VALUES ('"+StringUtil.checkSize(contentValues.get("value_big_decimal"))+"', '"+StringUtil.checkSize(contentValues.get("value_big_integer"))+"', '"+StringUtil.checkSize(contentValues.get("value_bool_type"))+"')"));
    long result = database().insert("bean", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean WHERE valueBigDecimal=${valueBigDecimal} and valueBigInteger=${valueBigInteger} and valueBoolType=${valueBoolType}</pre>
   *
   * @param valueBigDecimal
   * 	used in where condition
   * @param valueBigInteger
   * 	used in where condition
   * @param valueBoolType
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(BigDecimal valueBigDecimal, BigInteger valueBigInteger, boolean valueBoolType) {
    String[] whereConditions={(valueBigDecimal==null?null:valueBigDecimal.toPlainString()), (valueBigInteger==null?null:String.valueOf(valueBigInteger.toString())), String.valueOf(valueBoolType)};

    Logger.info(StringUtil.formatSQL("DELETE bean WHERE valueBigDecimal=%s and valueBigInteger=%s and valueBoolType=%s"), (Object[])whereConditions);
    int result = database().delete("bean", "value_big_decimal=? and value_big_integer=? and value_bool_type=?", whereConditions);
    return result;
  }
}
