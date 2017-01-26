package sqlite.dynamic.update;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import sqlite.dynamic.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>Err2UpdateDAO</code>
 * </p>
 *
 *  @see Person
 *  @see Err2UpdateDAO
 *  @see sqlite.dynamic.PersonTable
 */
public class Err2UpdateDAOImpl extends AbstractDao implements Err2UpdateDAO {
  public Err2UpdateDAOImpl(BindErr2UpdateDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE person SET name=${bean.name}, surname=${bean.surname}, birth_city=${bean.birthCity}, birth_day=${bean.birthDay} WHERE 1=1</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void update(Person bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.surname!=null) {
      contentValues.put("surname", bean.surname);
    } else {
      contentValues.putNull("surname");
    }

    if (bean.birthCity!=null) {
      contentValues.put("birth_city", bean.birthCity);
    } else {
      contentValues.putNull("birth_city");
    }

    if (bean.birthDay!=null) {
      contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    } else {
      contentValues.putNull("birth_day");
    }

    String[] whereConditions={};

    Logger.info(StringUtils.formatSQL("UPDATE person SET name='"+StringUtils.checkSize(contentValues.get("name"))+"', surname='"+StringUtils.checkSize(contentValues.get("surname"))+"', birth_city='"+StringUtils.checkSize(contentValues.get("birth_city"))+"', birth_day='"+StringUtils.checkSize(contentValues.get("birth_day"))+"' WHERE 1=1"), (Object[])whereConditions);
    int result = database().update("person", contentValues, "1=1", whereConditions);
  }
}
