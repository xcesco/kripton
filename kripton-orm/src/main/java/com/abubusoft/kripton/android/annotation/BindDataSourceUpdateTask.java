/**
 * 
 */
package com.abubusoft.kripton.android.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;

/**
 * <p>
 * Database schema migrations are managed by migration tasks that are defined by
 * SQLiteUpdateTask derived classes. You can define an update tasks set:
 * </p>
 * <ul>
 * <li>By code, typically in Application#onCreate method.</li>
 * <li>By annotation, in &#64;BindDataSourceOptions annotation used in a data
 * source definition.</li>
 * </ul>
 * 
 * <pre>
 * &#64;BindContentProvider(authority = "com.abubusoft.contentprovidersample.provider")
 * &#64;BindDataSourceOptions(updateTasks = {
 * 		&#64;BindDataSourceUpdateTask(version = 2, task = PersonUpdateTask.class) }, populator = SamplePopulator.class)
 * &#64;BindDataSource(daoSet = { PersonDao.class }, fileName = "sample.db", version = 1)
 * public interface SampleDataSource {
 * 
 * }
 * </pre>
 * 
 * <p>
 * <code>&#64;BindDataSourceUpdateTask(version = 2, task = PersonUpdateTask.class)</code> will
 * use the class <code>PersonUpdateTask</code> to migrate from version 1 to version 2.
 * Migration tasks support database migration from version n to version n+1. No
 * gap versions are allowed. For this reason, you need only to specify target
 * version, since star version is always defined as target version - 1.
 * </p>
 * 
 * <pre>
 public class PersonUpdateTask implements SQLiteUpdateTask {
  &#64;Override
  public void execute(SQLiteDatabase database, int previousVersion, int currentVersion) {
    ...		
  }
}
</pre>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Retention(CLASS)
@Target(ANNOTATION_TYPE)
public @interface BindDataSourceUpdateTask {

	/**
	 * Version.
	 *
	 * @return the int
	 */
	int version();

	/**
	 * Task.
	 *
	 * @return the class&lt;? extends SQ lite update task&gt;
	 */
	Class<? extends SQLiteUpdateTask> task();
}
