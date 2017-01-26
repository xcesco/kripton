package sqlite.kripton84;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.AbstractContext;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * DAO implementation for entity <code>Bean84B</code>, based on interface <code>Bean84BDao</code>
 * </p>
 *
 *  @see Bean84B
 *  @see Bean84BDao
 *  @see Bean84BTable
 */
public class Bean84BDaoImpl extends AbstractDao implements Bean84BDao {
  /**
   * Bean84B2BindMap */
  private Bean84B2BindMap bean84B2BindMap = AbstractContext.mapperFor(Bean84B2.class);

  public Bean84BDaoImpl(BindBean84BDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, column_bean FROM bean84_b WHERE id = ${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to <code>${id}</code>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean84B selectById(long param1) {
    // build where condition
    String[] args={String.valueOf(param1)};

    Logger.info(StringUtils.formatSQL("SELECT id, column_bean FROM bean84_b WHERE id = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, column_bean FROM bean84_b WHERE id = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean84B resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("column_bean");

      resultBean=new Bean84B();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.columnBean=Bean84BTable.parseColumnBean(cursor.getBlob(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, column_bean FROM bean84_b WHERE cast(columnBean as TEXT) = ${param1}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>column_bean</dt><dd>is associated to bean's property <strong>columnBean</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${param1}</dt><dd>is binded to method's parameter <strong>param1</strong></dd>
   * </dl>
   *
   * @param param1
   * 	is binded to <code>${param1}</code>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean84B selectByBean(Bean84B2 param1) {
    // build where condition
    String[] args={(param1==null?"":new String(serializer1(param1),StandardCharsets.UTF_8))};

    Logger.info(StringUtils.formatSQL("SELECT id, column_bean FROM bean84_b WHERE cast(column_bean as TEXT) = '%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, column_bean FROM bean84_b WHERE cast(column_bean as TEXT) = ?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean84B resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("column_bean");

      resultBean=new Bean84B();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.columnBean=Bean84BTable.parseColumnBean(cursor.getBlob(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean84_b (column_bean) VALUES (${bean.columnBean})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <code>true</code> if record is inserted, <code>false</code> otherwise
   */
  @Override
  public boolean insert(Bean84B bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnBean!=null) {
      contentValues.put("column_bean", Bean84BTable.serializeColumnBean(bean.columnBean));
    } else {
      contentValues.putNull("column_bean");
    }

    // log
    Logger.info(StringUtils.formatSQL("INSERT INTO bean84_b (column_bean) VALUES ('"+StringUtils.checkSize(contentValues.get("column_bean"))+"')"));
    long result = database().insert("bean84_b", null, contentValues);
    bean.id=result;

    return result!=-1;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE bean84_b SET column_bean=${bean.columnBean} WHERE 1=1</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>column_bean</dt><dd>is mapped to <strong>${bean.columnBean}</strong></dd>
   * </dl>
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is updated, <code>false</code> otherwise
   */
  @Override
  public boolean updateAll(Bean84B bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.columnBean!=null) {
      contentValues.put("column_bean", Bean84BTable.serializeColumnBean(bean.columnBean));
    } else {
      contentValues.putNull("column_bean");
    }

    String[] whereConditionsArray={};

    Logger.info(StringUtils.formatSQL("UPDATE bean84_b SET column_bean='"+StringUtils.checkSize(contentValues.get("column_bean"))+"' WHERE 1=1"), (Object[]) whereConditionsArray);
    int result = database().update("bean84_b", contentValues, "1=1", whereConditionsArray);
    return result!=0;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE bean84_b WHERE 1=1</pre>
   * @param bean
   * 	is used as ${bean}
   *
   * @return <code>true</code> if record is deleted, <code>false</code> otherwise
   */
  @Override
  public boolean deleteAll(Bean84B bean) {
    String[] whereConditionsArray={};

    Logger.info(StringUtils.formatSQL("1=1"), (Object[]) whereConditionsArray);
    int result = database().delete("bean84_b", "1=1", whereConditionsArray);
    return result!=0;
  }

  /**
   * write
   */
  private byte[] serializer1(Bean84B2 value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        bean84B2BindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toByteArray();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * parse
   */
  private Bean84B2 parser1(byte[] input) {
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
      Bean84B2 result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=bean84B2BindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }
}
