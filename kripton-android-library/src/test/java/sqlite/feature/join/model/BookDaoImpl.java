/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.join.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * DAO implementation for entity <code>Book</code>, based on interface <code>BookDao</code>
 * </p>.
 *
 * @see Book
 * @see BookDao
 * @see BookTable
 */
public class BookDaoImpl extends Dao implements BookDao {
  
  /** The insert prepared statement 0. */
  private static SQLiteStatement insertPreparedStatement0;

  /** The Constant FIND_BOOKS_BORROWED_BY_USER_SQL1. */
  private static final String FIND_BOOKS_BORROWED_BY_USER_SQL1 = "SELECT * FROM book INNER JOIN loan ON loan.book_id == book.id WHERE loan.user_id == ? ";

  /**
   * Instantiates a new book dao impl.
   *
   * @param context the context
   */
  public BookDaoImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO book (title) VALUES (${title})</pre>
   *
   * <p><code>entity.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>title</dt><dd>is mapped to <strong>${entity.title}</strong></dd>
   * </dl>
   *
   * @param entity
   * 	is mapped to parameter <strong>entity</strong>
   *
   */
  @Override
  public void insert(Book entity) {
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO book (title) VALUES (?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("title", entity.title);

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO book (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    entity.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM book INNER JOIN loan ON loan.book_id == book.id WHERE loan.user_id == ${userId} </pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>title</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${userId}</dt><dd>is binded to method's parameter <strong>userId</strong></dd>
   * </dl>
   *
   * @param userId
   * 	is binded to <code>${userId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Book> findBooksBorrowedByUser(long userId) {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BOOKS_BORROWED_BY_USER_SQL1;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(userId));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END

      ArrayList<Book> resultList=new ArrayList<Book>(_cursor.getCount());
      Book resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");

        do
         {
          resultBean=new Book();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.title=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * Clear compiled statements.
   */
  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
