package sqlite.feature.javadoc.delete.bean;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import sqlite.feature.javadoc.Person;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDao</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class PersonDaoImpl extends AbstractDao implements PersonDao {
  public PersonDaoImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL delete:</h2>
   * <pre>DELETE person where id=${bean.student}</pre>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>${bean.student}</dt><dd>is mapped to method's parameter <strong>bean.student</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as ${bean}
   */
  @Override
  public void deleteFromSelectAllBeansJQL(Person bean) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(bean.isStudent()));

    StringBuilder _sqlBuilder=new StringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" name=? and surname=? and student = (select student from person where id=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM person WHERE name=? and surname=? and student = (select student from person where id=?)");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("person", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));;
  }
}
