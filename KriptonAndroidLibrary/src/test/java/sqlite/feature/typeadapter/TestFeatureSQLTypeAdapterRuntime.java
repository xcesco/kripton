/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.typeadapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.OnReadBeanListener;

import base.BaseAndroidTest;

/**
 * @author xcesco
 *
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestFeatureSQLTypeAdapterRuntime extends BaseAndroidTest {

	@Test
	public void testSelect() {
		try (BindContactDataSource ds=BindContactDataSource.open()) {
			Contact bean=new Contact();
			bean.setId(1);
			bean.setPassword("test");
			ds.getContactDao().selectAll(bean, new OnReadBeanListener<Contact>() {
				
				@Override
				public void onRead(Contact bean, int row, int rowCount) {
					log("found row %s", row);
					
				}
			});
		}

	}

}
