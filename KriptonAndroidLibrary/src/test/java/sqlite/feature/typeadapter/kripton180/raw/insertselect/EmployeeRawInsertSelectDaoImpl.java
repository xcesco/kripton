package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;

/**
 * <p>
 * DAO implementation for entity <code>Employee</code>, based on interface <code>EmployeeRawInsertSelectDao</code>
 * </p>
 *
 *  @see sqlite.feature.typeadapter.kripton180.Employee
 *  @see EmployeeRawInsertSelectDao
 *  @see sqlite.feature.typeadapter.kripton180.EmployeeTable
 */
public class EmployeeRawInsertSelectDaoImpl extends AbstractDao implements EmployeeRawInsertSelectDao {
  public EmployeeRawInsertSelectDaoImpl(BindKripton180RawInsertSelectDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=${fieldBoolean} and field_byte=${fieldByte} and field_character=${fieldCharacter} and field_short=${fieldShort} and field_integer=${fieldInteger} and field_long=${fieldLong} and field_float=${fieldFloat} and field_double=${fieldDouble} and field_string=${fieldString} and field_byte_array=${fieldByteArray}</pre>
   *
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>fieldBoolean</dt><dd>is binded to query's parameter <strong>${fieldBoolean}</strong></dd>
   * 	<dt>fieldByte</dt><dd>is binded to query's parameter <strong>${fieldByte}</strong></dd>
   * 	<dt>fieldCharacter</dt><dd>is binded to query's parameter <strong>${fieldCharacter}</strong></dd>
   * 	<dt>fieldShort</dt><dd>is binded to query's parameter <strong>${fieldShort}</strong></dd>
   * 	<dt>fieldInteger</dt><dd>is binded to query's parameter <strong>${fieldInteger}</strong></dd>
   * 	<dt>fieldLong</dt><dd>is binded to query's parameter <strong>${fieldLong}</strong></dd>
   * 	<dt>fieldFloat</dt><dd>is binded to query's parameter <strong>${fieldFloat}</strong></dd>
   * 	<dt>fieldDouble</dt><dd>is binded to query's parameter <strong>${fieldDouble}</strong></dd>
   * 	<dt>fieldString</dt><dd>is binded to query's parameter <strong>${fieldString}</strong></dd>
   * 	<dt>fieldByteArray</dt><dd>is binded to query's parameter <strong>${fieldByteArray}</strong></dd>
   * </dl>
   *
   * @param fieldBoolean
   * 	is used as parameter
   * @param fieldByte
   * 	is used as parameter
   * @param fieldCharacter
   * 	is used as parameter
   * @param fieldShort
   * 	is used as parameter
   * @param fieldInteger
   * 	is used as parameter
   * @param fieldLong
   * 	is used as parameter
   * @param fieldFloat
   * 	is used as parameter
   * @param fieldDouble
   * 	is used as parameter
   * @param fieldString
   * 	is used as parameter
   * @param fieldByteArray
   * 	is used as parameter
   *
   */
  @Override
  public void insertJQL(String fieldBoolean, String fieldByte, String fieldCharacter,
      String fieldShort, String fieldInteger, String fieldLong, String fieldFloat,
      String fieldDouble, String fieldString, String fieldByteArray) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // build where condition
    _sqlWhereParams.add((fieldBoolean==null?"":fieldBoolean));
    _sqlWhereParams.add((fieldByte==null?"":fieldByte));
    _sqlWhereParams.add((fieldCharacter==null?"":fieldCharacter));
    _sqlWhereParams.add((fieldShort==null?"":fieldShort));
    _sqlWhereParams.add((fieldInteger==null?"":fieldInteger));
    _sqlWhereParams.add((fieldLong==null?"":fieldLong));
    _sqlWhereParams.add((fieldFloat==null?"":fieldFloat));
    _sqlWhereParams.add((fieldDouble==null?"":fieldDouble));
    _sqlWhereParams.add((fieldString==null?"":fieldString));
    _sqlWhereParams.add((fieldByteArray==null?"":fieldByteArray));

    Logger.info("INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=${param0} and field_byte=${param1} and field_character=${param2} and field_short=${param3} and field_integer=${param4} and field_long=${param5} and field_float=${param6} and field_double=${param7} and field_string=${param8} and field_byte_array=${param9}");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    database().execSQL("INSERT INTO employees (field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array) select field_boolean, field_byte, field_character, field_short, field_integer, field_long, field_float, field_double, field_string, field_byte_array  from employees where field_boolean=? and field_byte=? and field_character=? and field_short=? and field_integer=? and field_long=? and field_float=? and field_double=? and field_string=? and field_byte_array=?", _sqlWhereParams.toArray(new Object[_sqlWhereParams.size()]));
  }
}
