package test03;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.ProcessorHelper;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean01</code>, based on interface <code>DaoBean02</code>
 * </p>
 *
 *  @see Bean01
 *  @see DaoBean02
 *  @see Bean01Table
 */
public class DaoBean02Impl extends AbstractDao implements DaoBean02 {
  public DaoBean02Impl(BindDummy02DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (lista, message_date, message_text, bean_list, value) VALUES (${bean.lista}, ${bean.messageDate}, ${bean.messageText}, ${bean.beanList}, ${bean.value})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>lista</dt><dd>is mapped to <strong>${bean.lista}</strong></dd>
   * 	<dt>message_date</dt><dd>is mapped to <strong>${bean.messageDate}</strong></dd>
   * 	<dt>message_text</dt><dd>is mapped to <strong>${bean.messageText}</strong></dd>
   * 	<dt>bean_list</dt><dd>is mapped to <strong>${bean.beanList}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getLista()!=null) {
      contentValues.put("lista", ProcessorHelper.asByteArray(bean.getLista()));
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
      contentValues.put("bean_list", ProcessorHelper.asByteArray(bean.getBeanList()));
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
   * <p>SQL insert:</p>
   * <pre>INSERT INTO bean01 (value, message_date) VALUES (${value}, ${messageDate})</pre>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * 	<dt>message_date</dt><dd>is binded to query's parameter <strong>${messageDate}</strong> and method's parameter <strong>messageDate</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to column <strong>value</strong>
   * @param messageDate
   * 	is binded to column <strong>message_date</strong>
   *
   * @return <strong>id</strong> of inserted record
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
   * <p>SQL delete:</p>
   * <pre>DELETE bean01 WHERE id=${id}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
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
  public long delete(long id) {
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE bean01 WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("bean01", "id=?", whereConditions);
    return result;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE bean01 WHERE id=${bean.id}</pre>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
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
   * <p>SQL update:</p>
   * <pre>UPDATE bean01 SET value=${value} WHERE id>${id}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>value</dt><dd>is binded to query's parameter <strong>${value}</strong> and method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * <p><strong>Where parameters:</strong></p>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param value
   * 	is used as updated field <strong>value</strong>
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
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
   * <p>SQL Update:</p>
   * <pre>UPDATE bean01 SET lista=${bean.lista}, message_date=${bean.messageDate}, message_text=${bean.messageText}, bean_list=${bean.beanList}, value=${bean.value} WHERE value=${bean.value}</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>lista</dt><dd>is mapped to <strong>${bean.lista}</strong></dd>
   * 	<dt>message_date</dt><dd>is mapped to <strong>${bean.messageDate}</strong></dd>
   * 	<dt>message_text</dt><dd>is mapped to <strong>${bean.messageText}</strong></dd>
   * 	<dt>bean_list</dt><dd>is mapped to <strong>${bean.beanList}</strong></dd>
   * 	<dt>value</dt><dd>is mapped to <strong>${bean.value}</strong></dd>
   * </dl>
   *
   * <p><strong>Parameters used in where conditions:</strong></p>
   * <dl>
   * 	<dt>${bean.value}</dt><dd>is mapped to method's parameter <strong>bean.value</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(Bean01 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.getLista()!=null) {
      contentValues.put("lista", ProcessorHelper.asByteArray(bean.getLista()));
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
      contentValues.put("bean_list", ProcessorHelper.asByteArray(bean.getBeanList()));
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
