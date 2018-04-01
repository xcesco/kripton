package sqlite.feature.datasourceoptions.kripton234;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindDataSourceUpdateTask;

@BindDataSourceOptions(logEnabled = true, populator = PersonPopulator.class, cursorFactory = PersonCursorFactory.class, databaseLifecycleHandler=PersonLifecycleHandler.class, updateTasks = {
		@BindDataSourceUpdateTask(version = 2, task = PersonUpdateTask.class) })
@BindDataSource(daoSet = { DaoPerson.class }, fileName = "app.db")
public interface AppWithConfigDataSource {

}
