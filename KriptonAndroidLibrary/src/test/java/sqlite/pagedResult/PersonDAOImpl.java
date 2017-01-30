package sqlite.pagedResult;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.PagedResult;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  public PersonDAOImpl(BindPersonDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name LIMIT 20</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return paged result.
   */
  @Override
  public PagedResult<Person> selectPagedStatic1() {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name LIMIT 20",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name LIMIT 20", args);
    Logger.info("Rows found: %s",cursor.getCount());

    List<Person> resultList=new ArrayList<Person>();
    Person resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("name");
      int index2=cursor.getColumnIndex("surname");
      int index3=cursor.getColumnIndex("birth_city");
      int index4=cursor.getColumnIndex("birth_day");

      do
       {
        resultBean=new Person();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return null;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name LIMIT #{pageSize}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * <h2>Dynamic parts:</h2>
   * <dl>
   * <dt>#{pageSize}</dt>is part of limit statement resolved at runtime.</dd>
   * </dl>
   *
   * @param pageSize
   * 	is used as <strong>dynamic LIMIT statement</strong> and it is formatted by ({@link StringUtils#format})
   * @return paged result.
   */
  @Override
  public PagedResult<Person> selectPagedStatic2(int pageSize) {
    // build where condition
    String[] args={};

    //StringUtils will be used in case of dynamic parts of SQL
    Logger.info(StringUtils.formatSQL("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name LIMIT #{pageSize}",(Object[])args));
    Cursor cursor = database().rawQuery("SELECT id, name, surname, birth_city, birth_day FROM person ORDER BY name LIMIT #{pageSize}", args);
    Logger.info("Rows found: %s",cursor.getCount());

    List<Person> resultList=new ArrayList<Person>();
    Person resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("name");
      int index2=cursor.getColumnIndex("surname");
      int index3=cursor.getColumnIndex("birth_city");
      int index4=cursor.getColumnIndex("birth_day");

      do
       {
        resultBean=new Person();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return null;
  }

  class PagedResult0 extends PagedResult<Person> {
    PagedResult0() {
    }
  }
  
  protected ThreadLocal<PagedResult1> pagedResult1=new ThreadLocal<PagedResult1>() {

	@Override
	protected PagedResult1 initialValue() {
		return new PagedResult1();		
	} };

  public class PagedResult1 extends PagedResult<Person> {
    int pageSize;

    PagedResult1(int pageSize) {
      this.pageSize=pageSize;
    }
    
    public void execute()
    {
    	PersonDAOImpl.this.selectPagedStatic1()
    }
  }
}
