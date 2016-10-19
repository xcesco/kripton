package com.abubusoft.kripton.processor.kripton49.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DaoBase;
import com.abubusoft.kripton.common.StringUtil;
import com.abubusoft.kripton.processor.kripton49.entities.Bean01Entity;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Bean01Entity</code>, based on interface <code>DaoBean01</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton49.entities.Bean01Entity
 *  @see com.abubusoft.kripton.processor.kripton49.persistence.DaoBean01
 *  @see com.abubusoft.kripton.processor.kripton49.entities.Bean01EntityTable
 */
public class BindDaoBean01 extends DaoBase implements DaoBean01 {
  public BindDaoBean01(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, text]</pre>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01Entity selectOne(Long id) {
    // build where condition
    String[] args={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("SELECT id, text FROM bean01 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, text FROM bean01 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean01Entity resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      resultBean=new Bean01Entity();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, text]</pre>
   *
   * @param id
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<Bean01Entity> selectById(Long id) {
    // build where condition
    String[] args={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("SELECT id, text FROM bean01 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, text FROM bean01 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<Bean01Entity> resultList=new LinkedList<Bean01Entity>();
    Bean01Entity resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      do
       {
        resultBean=new Bean01Entity();

        if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET text=${text} WHERE  id=${id}</pre>
   *
   * @param text
   * 	used as updated field
   * @param id
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long updateOne(String text, Long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (text!=null) {
      contentValues.put("text", text);
    } else {
      contentValues.putNull("text");
    }

    String[] whereConditions={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET text='"+StringUtil.checkSize(contentValues.get("text"))+"' WHERE  id=%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, " id=?", whereConditions);
    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean01 WHERE  id=${id}</pre>
   *
   * @param id
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(Long id) {
    String[] whereConditions={(id==null?null:String.valueOf(id))};

    Logger.info(StringUtil.formatSQL("DELETE bean01 WHERE  id=%s"), (Object[])whereConditions);
    int result = database().delete("bean01", " id=?", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (id) VALUES (${id})</pre>
   *
   * @param id
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertOne(Long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (id!=null) {
      contentValues.put("id", id);
    } else {
      contentValues.putNull("id");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (id) VALUES ('"+StringUtil.checkSize(contentValues.get("id"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (text) VALUES (${bean.text})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insertOne(Bean01Entity bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getText()!=null) {
      contentValues.put("text", bean.getText());
    } else {
      contentValues.putNull("text");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (text) VALUES ('"+StringUtil.checkSize(contentValues.get("text"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    bean.setId(result);

    return result;
  }
}
