package sqlite.feature.many2many.case5.persistence;

import android.os.AsyncTask;
import com.abubusoft.kripton.android.BindAsyncTaskType;
import com.abubusoft.kripton.android.Logger;
import java.util.concurrent.Executor;

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
 * @see BindXenoDaoFactory
 * @see BindXenoDataSource
 * @see BindAsyncTaskType
 */
public abstract class BindXenoAsyncTask<I, U, R> {
  /**
   * Allows to specify how async task interacts with data source.
   *
   */
  protected BindAsyncTaskType mode;

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
  public BindXenoAsyncTask() {
    this(BindAsyncTaskType.READ);
  }

  /**
   * <p>
   * With this constructor it is possible to specify which type of database use in async task
   * </p>
   *
   * @param mode allows to specify if and how open a data source connection
   */
  public BindXenoAsyncTask(BindAsyncTaskType mode) {
    this.mode = mode;}

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
  public abstract R onExecute(BindXenoDataSource dataSource) throws Throwable;

  /**
   * Use this method for operations on UI-thread after execution
   */
  public abstract void onFinish(R result);

  /**
   * Override this method to KRIPTON_DEBUG operation progress on UI-Thread
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
   * @param executor used executor
   * @param data input
   */
  public void execute(@SuppressWarnings("unchecked") I... params) {
    executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, params);
  }

  /**
   * Method to start operations.
   *
   * @param executor used executor
   * @param data input
   */
  public void executeOnExecutor(Executor executor, @SuppressWarnings("unchecked") I... params) {
    asyncTask=new AsyncTask<I, U, R>() {
      @Override
      public void onPreExecute() {
        BindXenoAsyncTask.this.onPreExecute();
      }

      @Override
      public R doInBackground(@SuppressWarnings("unchecked") I... params) {
        BindXenoDataSource dataSource=BindXenoDataSource.instance();
        R result=null;
        if (mode==BindAsyncTaskType.READ) dataSource.openReadOnlyDatabase(); else if (mode==BindAsyncTaskType.READ_WRITE) dataSource.openWritableDatabase();
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
        BindXenoAsyncTask.this.onProgressUpdate(values);
      }

      @Override
      public void onPostExecute(R result) {
        BindXenoAsyncTask.this.onFinish(result);
      }
    };
    asyncTask.executeOnExecutor(executor, params);
  }

  /**
   * Simple implementation of async task. It uses read only database.
   *
   * @see BindXenoDaoFactory
   * @see BindXenoDataSource
   */
  public abstract static class Simple<R> extends BindXenoAsyncTask<Void, Void, R> {
    /**
     * Create an simple async task allowing user to decide which kind of operation can be done on datasource */
    public Simple(BindAsyncTaskType mode) {
      super(mode);
    }

    /**
     * Create an simple async task for data source read only operation */
    public Simple() {
      super(BindAsyncTaskType.READ);
    }
  }
}
