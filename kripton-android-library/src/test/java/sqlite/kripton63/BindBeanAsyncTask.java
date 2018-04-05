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
package sqlite.kripton63;

import android.os.AsyncTask;
import com.abubusoft.kripton.android.BindAsyncTaskType;
import com.abubusoft.kripton.android.Logger;
import java.util.concurrent.Executor;

// TODO: Auto-generated Javadoc
/**
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
 * @param <I> the generic type
 * @param <U> the generic type
 * @param <R> the generic type
 * @see BindBeanDaoFactory
 * @see BindBeanDataSource
 * @see BindAsyncTaskType
 */
public abstract class BindBeanAsyncTask<I, U, R> {
  /**
   * Allows to specify how async task interacts with data source.
   *
   */
  protected BindAsyncTaskType mode;

  /** Async task wrapped by this class. */
  protected AsyncTask<I, U, R> asyncTask;

  /**
   * <p>
   * With this constructor, a read only database connection will be used
   * </p>.
   */
  public BindBeanAsyncTask() {
    this(BindAsyncTaskType.READ);
  }

  /**
   * <p>
   * With this constructor it is possible to specify which type of database use in async task
   * </p>.
   *
   * @param mode allows to specify if and how open a data source connection
   */
  public BindBeanAsyncTask(BindAsyncTaskType mode) {
    this.mode = mode;}

  /**
   * Use this method for operations on UI-thread before start execution.
   */
  public void onPreExecute() {
  }

  /**
   * Method used to encapsulate operations on datasource.
   *
   * @param dataSource 	use it to retrieve DAO
   * @return 	result of operation (list, bean, etc) and execute transactions.
   * @throws Throwable the throwable
   */
  public abstract R onExecute(BindBeanDataSource dataSource) throws Throwable;

  /**
   * Use this method for operations on UI-thread after execution.
   *
   * @param result the result
   */
  public abstract void onFinish(R result);

  /**
   * Override this method to KRIPTON_DEBUG operation progress on UI-Thread.
   *
   * @param update the update
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
   * @param params the params
   */
  public void execute(@SuppressWarnings("unchecked") I... params) {
    executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, params);
  }

  /**
   * Method to start operations.
   *
   * @param executor used executor
   * @param params the params
   */
  public void executeOnExecutor(Executor executor, @SuppressWarnings("unchecked") I... params) {
    asyncTask=new AsyncTask<I, U, R>() {
      @Override
      public void onPreExecute() {
        BindBeanAsyncTask.this.onPreExecute();
      }

      @Override
      public R doInBackground(@SuppressWarnings("unchecked") I... params) {
        BindBeanDataSource dataSource=BindBeanDataSource.instance();
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
        BindBeanAsyncTask.this.onProgressUpdate(values);
      }

      @Override
      public void onPostExecute(R result) {
        BindBeanAsyncTask.this.onFinish(result);
      }
    };
    asyncTask.executeOnExecutor(executor, params);
  }

  /**
   * Simple implementation of async task. It uses read only database.
   *
   * @param <R> the generic type
   * @see BindBeanDaoFactory
   * @see BindBeanDataSource
   */
  public abstract static class Simple<R> extends BindBeanAsyncTask<Void, Void, R> {
    
    /**
     * Create an simple async task allowing user to decide which kind of operation can be done on datasource.
     *
     * @param mode the mode
     */
    public Simple(BindAsyncTaskType mode) {
      super(mode);
    }

    /**
     * Create an simple async task for data source read only operation.
     */
    public Simple() {
      super(BindAsyncTaskType.READ);
    }
  }
}
