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
package sqlite.feature.time;

import org.junit.Test;
import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.time.case1.*;

import java.io.IOException;

public class TimeSQLCompileTest extends AbstractBindSQLiteProcessorTest {
  @Test
  public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
    buildDataSourceProcessorTest(AppDataSource.class,
            DaoImmutableBean.class, DaoBeanWithAccessors.class, DaoBean.class, DaoBase.class,
            Bean.class, BeanWithAccessors.class, BeanImmutable.class);
  }
}
