package sqlite.kripton49.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.LinkedList;
import java.util.List;
import sqlite.kripton49.entities.Bean01Entity;

/**
 * <p>
 * DAO implementation for entity <code>Bean01Entity</code>, based on interface <code>DaoBean01</code>
 * </p>
 *
 *  @see Bean01Entity
 *  @see DaoBean01
 *  @see sqlite.kripton49.entities.Bean01EntityTable
 */
public class DaoBean01Impl extends AbstractDao implements DaoBean01 {
  public DaoBean01Impl(BindDummy01DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean01Entity selectOne(Long id) {
    // build where condition
    String[] _args={(id==null?"":String.valueOf(id))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, text FROM bean01 WHERE id='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, text FROM bean01 WHERE id=?", _args)) {
      Logger.info("Rows found: %s",cursor.getCount());

      Bean01Entity resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("text");

        resultBean=new Bean01Entity();

        if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
        if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, text FROM bean01 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>text</dt><dd>is associated to bean's property <strong>text</strong></dd>
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
  public List<Bean01Entity> selectById(Long id) {
    // build where condition
    String[] _args={(id==null?"":String.valueOf(id))};

    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    Logger.info(SqlUtils.formatSQL("SELECT id, text FROM bean01 WHERE id='%s'",(Object[])_args));
    try (Cursor cursor = database().rawQuery("SELECT id, text FROM bean01 WHERE id=?", _args)) {
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

      return resultList;
    }
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE bean01 SET text=${text} WHERE id=${id}</pre>
   *
   * <h2>Updated columns:</strong></h2>
   * <dl>
   * 	<dt>text</dt><dd>is binded to query's parameter <strong>${text}</strong> and method's parameter <strong>text</strong></dd>
   * </dl>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param text
   * 	is used as updated field <strong>text</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
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

    String[] whereConditionsArray={(id==null?"":String.valueOf(id))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("UPDATE bean01 SET text='"+StringUtils.checkSize(contentValues.get("text"))+"' WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().update("bean01", contentValues, "id=?", whereConditionsArray);
    return result;
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE bean01 WHERE id=${id}</pre></pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(Long id) {
    String[] whereConditionsArray={(id==null?"":String.valueOf(id))};

    //StringUtils and SqlUtils will be used to format SQL
    Logger.info(SqlUtils.formatSQL("DELETE bean01 WHERE id=%s", (Object[])whereConditionsArray));
    int result = database().delete("bean01", "id=?", whereConditionsArray);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (id) VALUES (${id})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is binded to query's parameter <strong>${id}</strong> and method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to column <strong>id</strong>
   *
   * @return <strong>id</strong> of inserted record
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean01 (id) VALUES ('"+StringUtils.checkSize(contentValues.get("id"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (text) VALUES (${bean.text})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>text</dt><dd>is mapped to <strong>${bean.text}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
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

    //StringUtils and SqlUtils will be used to format SQL
    // log
    Logger.info(SqlUtils.formatSQL("INSERT INTO bean01 (text) VALUES ('"+StringUtils.checkSize(contentValues.get("text"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    bean.setId(result);

    return result;
  }
}
