package sqlite.feature.typeadapter;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SQLTypeAdapterUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;

/**
 * <p>
 * DAO implementation for entity <code>Contact</code>, based on interface <code>ContactDao</code>
 * </p>
 *
 *  @see Contact
 *  @see ContactDao
 *  @see ContactTable
 */
public class ContactDaoImpl extends AbstractDao implements ContactDao {
  public ContactDaoImpl(BindContactDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL update:</h2>
   * <pre>UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE id=${bean.id}  and type=${bean.type}</pre>
   *
   * <h2>Updated columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * 	<dt>password</dt><dd>is mapped to <strong>${bean.password}</strong></dd>
   * 	<dt>type</dt><dd>is mapped to <strong>${bean.type}</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.id}</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * 	<dt>${bean.type}</dt><dd>is mapped to method's parameter <strong>bean.type</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(Contact bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.birthDay!=null) {
      contentValues.put("birth_day", SQLTypeAdapterUtils.toData(DateAdapterType.class, bean.birthDay));
    } else {
      contentValues.putNull("birth_day");
    }
    if (bean.getPassword()!=null) {
      contentValues.put("password", SQLTypeAdapterUtils.toData(PasswordAdapterType.class, bean.getPassword()));
    } else {
      contentValues.putNull("password");
    }
    if (bean.type!=null) {
      contentValues.put("type", SQLTypeAdapterUtils.toData(EnumAdapterType.class, bean.type));
    } else {
      contentValues.putNull("type");
    }

    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.getId()));
    _sqlWhereParams.add(SQLTypeAdapterUtils.toString(EnumAdapterType.class, bean.type));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?  and type=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("UPDATE contact SET birth_day=:birthDay, password=:password, type=:type WHERE id=?  and type=?");

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().update("contact", contentValues, _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
    return result;
  }
}
