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
package sqlite.feature.contentprovider.kripton213.case1;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindDataSourceUpdateTask;


/**
 * The Interface SampleDataSource.
 */
@BindContentProvider(authority = "com.abubusoft.contentprovidersample.provider")
@BindDataSourceOptions(updateTasks = { @BindDataSourceUpdateTask(version = 1, task = SampleUpdate02.class) }, populator = SamplePopulator.class)
@BindDataSource(daoSet = { CheeseDao.class }, fileName = "sample.db", version = 1)
public interface SampleDataSource {

}
