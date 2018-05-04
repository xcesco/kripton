package sqlite.kripton84;

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
 * @see BindBean84ADaoFactory
 * @see BindBean84ADataSource
 * @see BindAsyncTaskType
 */
public abstract class BindBean84AAsyncTask<I, U, R> {
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
  public BindBean84AAsyncTask() {
    this(BindAsyncTaskType.READ);
  }

  /**
   * <p>
   * With this constructor it is possible to specify which type of database use in async task
   * </p>
   *
   * @param mode allows to specify if and how open a data source connection
   */
  public BindBean84AAsyncTask(BindAsyncTaskType mode) {
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
  public abstract R onExecute(BindBean84ADataSource dataSource) throws Throwable;

  /**
   * Use this method for operations on UI-thread after execution
   */
  public abstract void onFinish(R result);

  /**
   * Override this method to KRIPTON_DEBUG operation progress on UI-Thread
   */
  public void onProgressUpdate(@SuppressWarnings("unchecked") U... update) {
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
        BindBean84AAsyncTask.this.onPreExecute();
      }

      @Override
      public R doInBackground(@SuppressWarnings("unchecked") I... params) {
        BindBean84ADataSource dataSource=BindBean84ADataSource.instance();
        R result=null;
        boolean needToOpened=false;
        if (mode==BindAsyncTaskType.READ) { needToOpened=true; dataSource.openReadOnlyDatabase(); } else if (mode==BindAsyncTaskType.READ_WRITE) { needToOpened=true; dataSource.openWritableDatabase();}
        try {
          result=onExecute(dataSource);
        } catch(Throwable e) {
          onError(e);
        } finally {
          if (needToOpened) {
            dataSource.close();
          }
        }
        return result;
      }

      @Override
      public void onProgressUpdate(@SuppressWarnings("unchecked") U... values) {
        BindBean84AAsyncTask.this.onProgressUpdate(values);
      }

      @Override
      public void onPostExecute(R result) {
        BindBean84AAsyncTask.this.onFinish(result);
      }
    };
    asyncTask.executeOnExecutor(executor, params);
  }

  /**
   * Simple implementation of async task. It uses read only database.
   *
   * @see BindBean84ADaoFactory
   * @see BindBean84ADataSource
   */
  public abstract static class Simple<R> extends BindBean84AAsyncTask<Void, Void, R> {
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
