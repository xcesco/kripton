package sqlite.kripton51.persistence;

import android.os.AsyncTask;
import com.abubusoft.kripton.android.Logger;
import java.lang.Override;
import java.lang.SuppressWarnings;
import java.lang.Throwable;

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
 * @param I input param
 * @param U update param
 * @param R result param
 *
 * @see BindWhisperDaoFactory
 * @see BindWhisperDataSource
 */
public abstract class BindWhisperAsyncTask<I, U, R> {
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
  public BindWhisperAsyncTask() {
    this(true);}

  /**
   * <p>
   * With this constructor it is possible to specify which type of database use in async task
   * </p>
   *
   * @param readOnlyTask if true, force async task to use read only database connection
   */
  public BindWhisperAsyncTask(boolean readOnlyTask) {
    this.readOnlyTask = readOnlyTask;}

  /**
   * Use this method for operations on UI-thread before start execution
   */
  public void onPreExecute() {
  }

  /**
   * Method used to encapsulate operations on datasource
   *
   * @param dataSource
   * 	use it to retrieve DAO
   * @return
   * 	result of operation (list, bean, etc) and execute transactions.
   */
  public abstract R onExecute(BindWhisperDataSource dataSource) throws Throwable;

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
   * This method is invoked when <code>onExecute</code> method generate an exception.
   * @param exception exception generated
   */
  public void onError(Throwable exception) {
    Logger.error(exception.getMessage());
    exception.printStackTrace();
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
        BindWhisperAsyncTask.this.onPreExecute();
      }

      @Override
      public R doInBackground(@SuppressWarnings("unchecked") I... params) {
        BindWhisperDataSource dataSource=BindWhisperDataSource.instance();
        R result=null;
        if (readOnlyTask) dataSource.openReadOnlyDatabase(); else dataSource.openWritableDatabase();
        try {
          result=onExecute(dataSource);
        } catch(Throwable e) {
          onError(e);
        } finally {
          if (dataSource.isOpen()) {
            dataSource.close();
          }
        }
        return result;
      }

      @Override
      public void onProgressUpdate(@SuppressWarnings("unchecked") U... values) {
        BindWhisperAsyncTask.this.onProgressUpdate(values);
      }

      @Override
      public void onPostExecute(R result) {
        BindWhisperAsyncTask.this.onFinish(result);
      }
    };
    asyncTask.execute(params);
  }

  /**
   * Simple implementation of async task. It uses read only database.
   *
   * @see BindWhisperDaoFactory
   * @see BindWhisperDataSource
   */
  public abstract static class Simple<R> extends BindWhisperAsyncTask<Void, Void, R> {
  }
}
