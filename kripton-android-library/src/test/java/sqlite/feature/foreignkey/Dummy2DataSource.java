/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.feature.foreignkey;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.feature.foreignkey.DaoBeanA_3;
import sqlite.feature.foreignkey.DaoBeanA_4;


/**
 * The Interface Dummy2DataSource.
 */
@BindDataSource(fileName="test.db", daoSet = { DaoBeanA_3.class, DaoBeanA_4.class })
public interface Dummy2DataSource {

}