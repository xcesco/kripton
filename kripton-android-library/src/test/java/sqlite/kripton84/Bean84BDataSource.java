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
package sqlite.kripton84;

import com.abubusoft.kripton.android.annotation.BindDataSource;


/**
 * The Interface Bean84BDataSource.
 */
@BindDataSource(daoSet=Bean84BDao.class, fileName = "dummy" , version=1, asyncTask=true, cursorWrapper=true)
public interface Bean84BDataSource {

}
