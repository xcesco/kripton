package sqlite.kripton84;

import android.os.AsyncTask;
import java.lang.Override;
import java.lang.SuppressWarnings;

/**
 *
 * <p>
 * Specialized async task to make async database operation on activity
 * </p>
 *
 * <p>
 * Unlike standard async task, for an instance of this class can be used many time.
 * </p>
 *
 * <p>
 * When method <code>execute</code> is invoked, an inner async task is created.
 * </p>
 *
 * @see BindBean84BDaoFactory
 * @see BindBean84BDataSource
 */
public abstract class BindBean84BAsyncTask<I, U, R> {
  /**
   * If <code>true</code> indicates database operations are only read operations
   *
   */
  protected boolean readOnlyTask;

  /**
   * Async task wrapped by this class
   *
   */
  protected AsyncTask<I, U, R> asyncTask;

  /**
   * <p>
   * With this constructor, a read only database connection will be used
   * </p>
   */
  public BindBean84BAsyncTask() {
    this(true);}

  /**
   * <p>
   * With this constructor it is possible to specify which type of database use in async task
   * </p>
   *
   * @param readOnlyTask if true, force async task to use read only database connection
   */
  public BindBean84BAsyncTask(boolean readOnlyTask) {
    this.readOnlyTask = readOnlyTask;}

  /**
   * Use this method for operations on UI-thread before start execution
   */
  public void onPreExecute() {
  }

  /**
   * Method used to encapsulate operations on datasource
   *
   * @param daoFactory
   * 	dao factory. Use it to retrieve DAO
   * @return
   * 	result of operation (list, bean, etc)
   */
  public abstract R onExecute(BindBean84BDaoFactory daoFactory);

  /**
   * Use this method for operations on UI-thread after execution
   */
  public abstract void onFinish(R result);

  /**
   * Override this method to display operation progress on UI-Thread
   */
  public void onProgressUpdate(U... update) {
  }

  /**
   * Method to start operations.
   *
   * @param
   * 	data input
   */
  public void execute(@SuppressWarnings("unchecked") I... params) {
    asyncTask=new AsyncTask<I, U, R>() {
      @Override
      public void onPreExecute() {
        BindBean84BAsyncTask.this.onPreExecute();
      }

      @Override
      public R doInBackground(@SuppressWarnings("unchecked") I... params) {
        BindBean84BDataSource dataSource=BindBean84BDataSource.instance();
        R result=null;
        if (readOnlyTask) dataSource.openReadOnlyDatabase(); else dataSource.openWritableDatabase();
        try {
          result=onExecute(dataSource);
        } catch(Exception e) {
        } finally {
          if (dataSource.isOpen()) {
            dataSource.close();
          }
        }
        return result;
      }

      @Override
      public void onProgressUpdate(@SuppressWarnings("unchecked") U... values) {
        BindBean84BAsyncTask.this.onProgressUpdate(values);
      }

      @Override
      public void onPostExecute(R result) {
        BindBean84BAsyncTask.this.onFinish(result);
      }
    };
    asyncTask.execute(params);
  }

  /**
   * Simple implementation of async task. It uses read only database.
   *
   * @see BindBean84BDaoFactory
   * @see BindBean84BDataSource
   */
  public abstract static class Simple<R> extends BindBean84BAsyncTask<Void, Void, R> {
  }
}
