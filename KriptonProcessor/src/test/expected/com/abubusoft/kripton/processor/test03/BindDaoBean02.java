package com.abubusoft.kripton.processor.test03;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.DaoHelper;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean02</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.test03.Bean01
 *  @see com.abubusoft.kripton.processor.test03.DaoBean02
 *  @see com.abubusoft.kripton.processor.test03.Bean01Table
 */
public class BindDaoBean02 extends AbstractDao implements DaoBean02 {
  public BindDaoBean02(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (lista, message_date, message_text, bean_list, value) VALUES (${bean.lista}, ${bean.messageDate}, ${bean.messageText}, ${bean.beanList}, ${bean.value})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getLista()!=null) {
      contentValues.put("lista", DaoHelper.toByteArray(bean.getLista()));
    } else {
      contentValues.putNull("lista");
    }

    contentValues.put("message_date", bean.getMessageDate());

    if (bean.getMessageText()!=null) {
      contentValues.put("message_text", bean.getMessageText());
    } else {
      contentValues.putNull("message_text");
    }

    if (bean.getBeanList()!=null) {
      contentValues.put("bean_list", DaoHelper.toByteArray(bean.getBeanList()));
    } else {
      contentValues.putNull("bean_list");
    }

    contentValues.put("value", bean.getValue());

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (lista, message_date, message_text, bean_list, value) VALUES ('"+StringUtil.checkSize(contentValues.get("lista"))+"', '"+StringUtil.checkSize(contentValues.get("message_date"))+"', '"+StringUtil.checkSize(contentValues.get("message_text"))+"', '"+StringUtil.checkSize(contentValues.get("bean_list"))+"', '"+StringUtil.checkSize(contentValues.get("value"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    bean.setId(result);

    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO bean01 (value, message_date) VALUES (${value}, ${messageDate})</pre>
   *
   * @param value
   * 	used as updated field and in where condition
   * @param messageDate
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public long insert(long value, long messageDate) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("value", value);

    contentValues.put("message_date", messageDate);

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO bean01 (value, message_date) VALUES ('"+StringUtil.checkSize(contentValues.get("value"))+"', '"+StringUtil.checkSize(contentValues.get("message_date"))+"')"));
    long result = database().insert("bean01", null, contentValues);
    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean01 WHERE id=${id}</pre>
   *
   * @param id
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(long id) {
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE bean01 WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("bean01", "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean01 WHERE id=${bean.id}</pre>
   *
   * @param bean
   * 	used as updated field and in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long delete(Bean01 bean) {
    String[] whereConditions={String.valueOf(bean.getId())};

    Logger.info(StringUtil.formatSQL("id=%s"), (Object[])whereConditions);
    int result = database().delete("bean01", "id=?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET value=${value} WHERE id>${id}</pre>
   *
   * @param value
   * 	used as updated field
   * @param id
   * 	used in where condition
   *
   * @return number of updated records
   */
  @Override
  public long update(long value, long id) {
    ContentValues contentValues=contentValues();
    contentValues.clear();
    contentValues.put("value", value);

    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE id>%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, "id>?", whereConditions);
    return result;
  }

  /**
   * <p>Update query:</p>
   * <pre>UPDATE bean01 SET lista=${bean.lista}, message_date=${bean.messageDate}, message_text=${bean.messageText}, bean_list=${bean.beanList}, value=${bean.value} WHERE value=${bean.value}</pre>
   *
   * @param bean
   * 	used as updated field and in where condition
   *
   * @return number of updated records
   */
  @Override
  public long update(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getLista()!=null) {
      contentValues.put("lista", DaoHelper.toByteArray(bean.getLista()));
    } else {
      contentValues.putNull("lista");
    }

    contentValues.put("message_date", bean.getMessageDate());

    if (bean.getMessageText()!=null) {
      contentValues.put("message_text", bean.getMessageText());
    } else {
      contentValues.putNull("message_text");
    }

    if (bean.getBeanList()!=null) {
      contentValues.put("bean_list", DaoHelper.toByteArray(bean.getBeanList()));
    } else {
      contentValues.putNull("bean_list");
    }

    contentValues.put("value", bean.getValue());

    String[] whereConditions={String.valueOf(bean.getValue())};

    Logger.info(StringUtil.formatSQL("UPDATE bean01 SET lista='"+StringUtil.checkSize(contentValues.get("lista"))+"', message_date='"+StringUtil.checkSize(contentValues.get("message_date"))+"', message_text='"+StringUtil.checkSize(contentValues.get("message_text"))+"', bean_list='"+StringUtil.checkSize(contentValues.get("bean_list"))+"', value='"+StringUtil.checkSize(contentValues.get("value"))+"' WHERE value=%s"), (Object[])whereConditions);
    int result = database().update("bean01", contentValues, "value=?", whereConditions);
    return result;
  }
}
